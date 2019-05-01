package com.wuhulala.springboot.rxjava;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/24<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Slf4j
@RestController
@RequestMapping("tasks")
public class TaskController {

    /**
     * 主线程执行
     * <p>http://localhost:8080/tasks/sequential?task=2&task=3&task=5</p>
     * <p>{"userMills":"10005"}</p>
     *
     *
     *
     *
     *
     * @param taskDelaysInSeconds 模拟线程执行时间
     * @return 总执行时间
     */
    @RequestMapping("/sequential")
    public Map<String, String> sequential(@RequestParam("task") int[] taskDelaysInSeconds) {

        StopWatch sw = new StopWatch();
        sw.start();
        IntStream.of(taskDelaysInSeconds).mapToObj(MockTask::new).forEach(MockTask::execute);
        sw.stop();
        return ImmutableMap.of("userMills", sw.getTotalTimeMillis() + "");
    }

    /**
     * 并发执行
     *
     * <p>http://localhost:8080/tasks/concurrent?task=2&task=3&task=5&threads=3</p>
     * <p>{"userMills":"5003"}</p>
     * <p>http://localhost:8080/tasks/concurrent?task=2&task=3&task=5&threads=3&type=single</p>
     * <p>{"userMills":"10064"}</p>
     * <p>http://localhost:8080/tasks/concurrent?task=2&task=3&task=5&threads=3&type=io</p>
     * <p>{"userMills":"5017"}</p>
     * <p>http://localhost:8080/tasks/concurrent?task=2&task=3&task=5&threads=3&type=computation</p>
     * <p>{"userMills":"5008"}</p>
     *
     * @param taskDelaysInSeconds       模拟线程执行时间
     * @param numberOfConcurrentThreads 线程池线程数
     * @return 总执行时间
     */
    @RequestMapping("/concurrent")
    public Map<String, String> concurrent(@RequestParam("task") int[] taskDelaysInSeconds,
                                          @RequestParam(value = "threads", defaultValue = "1") int numberOfConcurrentThreads,
                                          @RequestParam(value = "type", defaultValue = "from") String schedulerType) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<ITask> delayedTasks = IntStream.of(taskDelaysInSeconds).mapToObj(MockTask::new).collect(Collectors.toList());
        new ConcurrentTaskExecutor(numberOfConcurrentThreads, schedulerType, delayedTasks).execute();
        sw.stop();
        return ImmutableMap.of("userMills", sw.getTotalTimeMillis() + "");
    }
}
