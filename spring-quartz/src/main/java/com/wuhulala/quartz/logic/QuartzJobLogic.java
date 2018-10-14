package com.wuhulala.quartz.logic;

import com.wuhulala.quartz.pojo.JobDto;
import com.wuhulala.quartz.pojo.QuartzJob;
import com.wuhulala.quartz.pojo.TimerJob;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class QuartzJobLogic {

    private static final String GROUP_NAME_DEFAULT = Key.DEFAULT_GROUP;

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    private Scheduler getScheduler() {
        Scheduler scheduler = schedulerFactory.getScheduler();
        if (scheduler == null) {
            System.out.println("定时任务调度器为空，请确认是否开启定时任务功能！");
        }
        return scheduler;
    }

    public void addJob(JobDto job) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                job.getTriggerKey(), GROUP_NAME_DEFAULT);
        CronTrigger trigger =
                (CronTrigger) getScheduler().getTrigger(triggerKey);
        if (trigger == null) {
            /**Trigger不存在，则新建*/

            JobDataMap jobDataMap = new JobDataMap();
            job.setJobState(TriggerState.NORMAL.toString());
            jobDataMap.put("jobDto", job);

            //新建任务
            JobKey jobKey = new JobKey(
                    job.getJobDetailKey(), GROUP_NAME_DEFAULT);
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                    .setJobData(jobDataMap)
                    .withIdentity(jobKey)
                    .requestRecovery(true)
                    .storeDurably(true)
                    .build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder =
                    CronScheduleBuilder.cronSchedule(job.getExpression());

            //构建触发器
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder)
                    .build();

            //装配任务
            System.out.println("暂停ing-------------");
            getScheduler().scheduleJob(jobDetail, trigger);
            getScheduler().pauseTrigger(triggerKey);

            //getScheduler().getListenerManager().addJobListener();

        }
    }

    public List<TimerJob> queryJobs(final TimerJob timerJob) throws SchedulerException {
        List<TimerJob> ls = new ArrayList<TimerJob>();

        Set<TriggerKey> triggerKeys =
                getScheduler().getTriggerKeys(GroupMatcher.triggerGroupEquals(GROUP_NAME_DEFAULT));
        for (TriggerKey triggerKey : triggerKeys) {
            Trigger trigger = getScheduler().getTrigger(triggerKey);
            TriggerState triggerState = getScheduler().getTriggerState(triggerKey);

            JobDto jobDto = (JobDto) getScheduler().getJobDetail(trigger.getJobKey())
                    .getJobDataMap()
                    .get("jobDto");
            jobDto.setJobState(triggerState.toString());
            jobDto.setNextFireTime(trigger.getNextFireTime());
            ls.add(jobDto);

        }

        //条件查询
        CollectionUtils.filter(ls, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                if (object == null
                        || !(object instanceof TimerJob)) {
                    return false;
                }
                TimerJob temp = (TimerJob) object;


                if (StringUtils.isNotBlank(timerJob.getJobState())
                        && !StringUtils.equals(timerJob.getJobState(), temp.getJobState())) {
                    return false;
                }

                return true;
            }
        });
        return ls;
    }

    /**
     * 描述：暂停定时任务<br>
     * 参数：@param object<br>
     * 返回值：void<br>
     *
     * @throws SchedulerException
     */
    public void pauseJob(JobDto job) throws SchedulerException {
        getScheduler().pauseJob(JobKey.jobKey(job.getJobDetailKey()));
        getScheduler().pauseTrigger(TriggerKey.triggerKey(job.getTriggerKey()));
    }

    /**
     * 描述： 启动定时任务<br>
     * 参数：@param job<br>
     * 返回值：void<br>
     *
     * @throws SchedulerException
     * @throws SchedulerException
     */
    public void resumeJob(JobDto job) throws SchedulerException {
        getScheduler().resumeTrigger(TriggerKey.triggerKey(job.getTriggerKey()));
        getScheduler().resumeJob(JobKey.jobKey(job.getJobDetailKey()));
    }

    /**
     * 描述：<br>
     * 参数：@param job<br>
     * 返回值：void<br>
     *
     * @throws SchedulerException
     */
    public void deleteJob(JobDto job) throws SchedulerException {
        if (getScheduler().checkExists(JobKey.jobKey(job.getJobDetailKey()))) {
            getScheduler().deleteJob(JobKey.jobKey(job.getJobDetailKey()));
        }
    }

    /**
     * 描述：<br>
     * 参数：@param job<br>
     * 返回值：void<br>
     *
     * @throws SchedulerException
     */
    public void updateJob(JobDto job) throws SchedulerException {
        deleteJob(job);
        addJob(job);
    }

    /**
     * 描述：判断任务是否存在<br>
     * 参数：@param job
     * 参数：@return<br>
     * 返回值：boolean<br>
     *
     * @throws SchedulerException
     */
    public boolean isExistJob(JobDto job) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                job.getTriggerKey(), GROUP_NAME_DEFAULT);
        CronTrigger trigger =
                (CronTrigger) getScheduler().getTrigger(triggerKey);
        return trigger != null;
    }

    /**
     * 描述：<br>
     * 参数：@param job<br>
     * 返回值：void<br>
     *
     * @return
     * @throws SchedulerException
     */
    public TimerJob detailJob(JobDto job) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerKey());
        Trigger trigger = getScheduler().getTrigger(triggerKey);
        TriggerState triggerState = getScheduler().getTriggerState(triggerKey);

        JobDto jobDto = (JobDto) getScheduler().getJobDetail(JobKey.jobKey(job.getJobDetailKey()))
                .getJobDataMap()
                .get("jobDto");
        jobDto.setJobState(triggerState.toString());
        jobDto.setNextFireTime(trigger.getNextFireTime());
        return jobDto;

    }

    /**
     * 描述：<br>
     * 参数：@param job
     * 参数：@return<br>
     * 返回值：boolean<br>
     *
     * @throws SchedulerException
     */
    public boolean isPausedJob(JobDto job) throws SchedulerException {
        TriggerState triggerState = getScheduler().getTriggerState(
                TriggerKey.triggerKey(job.getTriggerKey()));
        return triggerState.toString().equals(
                TriggerState.PAUSED.toString());
    }

}
