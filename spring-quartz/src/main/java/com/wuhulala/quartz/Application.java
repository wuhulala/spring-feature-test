package com.wuhulala.quartz;

import com.wuhulala.quartz.logic.QuartzJobLogic;
import com.wuhulala.quartz.pojo.JobDto;
import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/13<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Application {


    ///////////////////////////// 方法区 ////////////////////////////////////
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:quartz-beans.xml");
        context.start();

        JobDto jobDto = new JobDto();
        jobDto.setExpression("* * 12 * * ?");
        jobDto.setJobName("wuhulala-test1");;
        jobDto.setRemark("lallala");
        jobDto.setFunctionId("100001");
        jobDto.setExchangeId("1023");

        try {
            QuartzJobLogic logic = context.getBean(QuartzJobLogic.class);
            logic.resumeJob(jobDto);
            Thread.sleep(10000);
            System.out.println("删除定时任务");
            logic.pauseJob(jobDto);
            logic.deleteJob(jobDto);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
