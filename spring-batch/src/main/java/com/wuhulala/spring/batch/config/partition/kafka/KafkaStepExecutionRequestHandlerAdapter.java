package com.wuhulala.spring.batch.config.partition.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.integration.partition.StepExecutionRequest;
import org.springframework.batch.integration.partition.StepExecutionRequestHandler;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * kafka Step执行器 适配器
 *
 * @author wuhulala<br>
 * @date 2019/4/3<br>
 * @description o_o<br>
 * @since v1.0<br>
 */

public class KafkaStepExecutionRequestHandlerAdapter {


    private final StepExecutionRequestHandler stepExecutionRequestHandler;

    public KafkaStepExecutionRequestHandlerAdapter(StepExecutionRequestHandler stepExecutionRequestHandler) {
        this.stepExecutionRequestHandler = stepExecutionRequestHandler;
    }

    @KafkaListener(topics = {"hello-batch"})
    public String doHandle(String content){
        System.out.println("slave接收到消息：:" + content);
        JSONObject jsonObject = JSON.parseObject(content);

        StepExecutionRequest request = new StepExecutionRequest(jsonObject.getString("stepName"),
                jsonObject.getLong("jobExecutionId"),
                jsonObject.getLong("stepExecutionId"));
        StepExecution stepExecution = stepExecutionRequestHandler.handle(request);

        return stepExecution.getStatus().equals(BatchStatus.COMPLETED) ? "success" : stepExecution.getStatus().toString();
    }
}
