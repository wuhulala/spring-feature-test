package com.wuhulala.spring.batch.config.partition.kafka;

import com.alibaba.fastjson.JSON;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.StepExecutionSplitter;
import org.springframework.batch.integration.partition.StepExecutionRequest;
import org.springframework.batch.poller.DirectPoller;
import org.springframework.batch.poller.Poller;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.concurrent.ListenableFuture;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.*;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Slf4j
public class KafkaPartitionHandler implements PartitionHandler {

    @Setter
    private int gridSize = 1;

    @Setter
    private KafkaTemplate kafkaTemplate;

    @Setter
    private String stepName;

    @Setter
    private String topic;




    @Setter
    private JobExplorer jobExplorer;

    @Setter
    private boolean pollRepositoryForResults = false;

    /**
     * 循环查询数据库时间
     */
    @Setter
    private long pollInterval = 1000;
    /**
     * 数据库判定结束类型 超时时间
     */
    @Setter
    private long timeout = -1;

    private DataSource dataSource;

    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public Collection<StepExecution> handle(StepExecutionSplitter stepSplitter, StepExecution masterStepExecution) throws Exception {
        final Set<StepExecution> split = stepSplitter.split(masterStepExecution, gridSize);
        if (CollectionUtils.isEmpty(split)) {
            return null;
        }

        final Map<StepExecutionRequest, ListenableFuture> futureMap = new HashMap<>(split.size());
        final Map<StepExecutionRequest, StepExecution> executionMap = new HashMap<>(split.size());
        for (StepExecution stepExecution : split) {
            StepExecutionRequest request = new StepExecutionRequest(
                    stepName, stepExecution.getJobExecutionId(), stepExecution.getId());
            log.info("Sending request: {}", request);

            String requestStr = JSON.toJSONString(request);
            ListenableFuture future = kafkaTemplate.send(topic, requestStr);

            futureMap.put(request, future);
            executionMap.put(request, stepExecution);
        }

        if (!pollRepositoryForResults) {
            return receiveReplies(masterStepExecution, futureMap, executionMap);
        } else {
            return pollReplies(masterStepExecution, split);
        }

    }

    private Collection<StepExecution> receiveReplies(StepExecution masterStepExecution, Map<StepExecutionRequest, ListenableFuture> futureMap, Map<StepExecutionRequest, StepExecution> executionMap) {
        Collection<StepExecution> result = new ArrayList<>(futureMap.size());
        for (Map.Entry<StepExecutionRequest, ListenableFuture> reply : futureMap.entrySet()) {
            StepExecution se = executionMap.get(reply.getKey());
            try {
                Object o = reply.getValue().get(timeout, TimeUnit.MILLISECONDS);
                if ("success".equals(o)) {
                    result.add(se);
                    log.info("{} invoked {} and received success", masterStepExecution.getJobExecutionId(), se.getStepName());
                } else {
                    log.error("{} received message error !", masterStepExecution.getJobExecutionId(), se.getStepName());
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                log.error("{} received message error !", masterStepExecution.getJobExecutionId(), se.getStepName(), e);
            }
        }

        // 如果成功
        if (result.size() == futureMap.size()) {
            return result;
        } else {
            return null;
        }
    }

    private Collection<StepExecution> pollReplies(final StepExecution masterStepExecution, final Set<StepExecution> split) throws Exception {
        final Collection<StepExecution> result = new ArrayList<StepExecution>(split.size());

        Callable<Collection<StepExecution>> callback = new Callable<Collection<StepExecution>>() {
            @Override
            public Collection<StepExecution> call() throws Exception {

                for (Iterator<StepExecution> stepExecutionIterator = split.iterator(); stepExecutionIterator.hasNext(); ) {
                    StepExecution curStepExecution = stepExecutionIterator.next();

                    if (!result.contains(curStepExecution)) {
                        StepExecution partitionStepExecution =
                                jobExplorer.getStepExecution(masterStepExecution.getJobExecutionId(), curStepExecution.getId());

                        if (!partitionStepExecution.getStatus().isRunning()) {
                            result.add(partitionStepExecution);
                        }
                    }
                }

                log.info("Currently waiting on {} partitions to finish", split.size());


                if (result.size() == split.size()) {
                    log.info("total partitions({}) are finished ", split.size());
                    return result;
                } else {
                    return null;
                }
            }
        };

        Poller<Collection<StepExecution>> poller = new DirectPoller<Collection<StepExecution>>(pollInterval);
        Future<Collection<StepExecution>> resultsFuture = poller.poll(callback);

        if (timeout >= 0) {
            return resultsFuture.get(timeout, TimeUnit.MILLISECONDS);
        } else {
            return resultsFuture.get();
        }
    }
}
