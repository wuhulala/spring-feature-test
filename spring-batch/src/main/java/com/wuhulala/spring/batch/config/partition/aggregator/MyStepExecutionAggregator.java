package com.wuhulala.spring.batch.config.partition.aggregator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.partition.support.StepExecutionAggregator;

import java.util.Collection;

/**
 * 是在所有的step执行完成之后再进行操作的
 *
 * @author wuhulala<br>
 * @date 2019/4/3<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class MyStepExecutionAggregator implements StepExecutionAggregator {

    private static final Logger logger = LoggerFactory.getLogger(MyStepExecutionAggregator.class);

    /**
     * 聚合
     *
     * @param masterExecution masterStep 结果
     * @param slaveExecutions slaveStep 结果集合
     */
    @Override
    public void aggregate(StepExecution masterExecution, Collection<StepExecution> slaveExecutions) {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.debug("masterStep = {}", masterExecution.toString());
        slaveExecutions.forEach(slaveExecution -> logger.debug(slaveExecution.toString()));
        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
