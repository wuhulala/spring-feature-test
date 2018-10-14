package com.wuhulala.spring.batch.listener.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 功能
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
@Slf4j
public class MyJobListener implements JobExecutionListener{

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //
        log.debug("job {} 开始执行 ...", jobExecution.getJobId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.debug("job {} 执行成功 ...", jobExecution.getJobId());

    }
}
