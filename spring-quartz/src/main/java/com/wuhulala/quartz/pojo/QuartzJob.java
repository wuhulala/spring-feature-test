package com.wuhulala.quartz.pojo;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    private JobDto jobDto;

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {

        String functionId = jobDto.getFunctionId();


        logger.debug(" ---------  执行开始 --------- ");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug(" ---------  执行结束 --------- ");


    }

    public JobDto getJobDto() {
        return jobDto;
    }

    public void setJobDto(JobDto jobDto) {
        this.jobDto = jobDto;
    }


}
