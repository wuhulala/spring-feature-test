package com.wuhulala.spring.batch.config.partition;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.StepExecutionSplitter;

import java.util.Collection;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class KafkaPartitionHandler implements PartitionHandler{


    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public Collection<StepExecution> handle(StepExecutionSplitter stepSplitter, StepExecution stepExecution) throws Exception {
        return null;
    }
}
