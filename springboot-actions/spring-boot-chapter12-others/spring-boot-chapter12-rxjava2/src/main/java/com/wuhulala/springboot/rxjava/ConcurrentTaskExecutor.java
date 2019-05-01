package com.wuhulala.springboot.rxjava;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/24<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Slf4j
public class ConcurrentTaskExecutor implements ITask {

    private final int numberOfCurrentThreads;

    private final Collection<ITask> tasks;

    /**
     * 调度器类型
     */
    private final String schedulerType;

    public ConcurrentTaskExecutor(int numberOfCurrentThreads, String schedulerType, Collection<ITask> tasks) {
        if (numberOfCurrentThreads < 0) {
            throw new RuntimeException("Amount of threads must be higher than zero.");
        }
        this.schedulerType = schedulerType;
        this.numberOfCurrentThreads = numberOfCurrentThreads;
        this.tasks = tasks;
    }


    @Override
    public void execute() {
        if (isTaskCollectionEmpty()) {
            log.warn("There are no tasks to be executed. ");
            return;
        }
        log.debug("Executing #{} tasks concurrent way.", tasks.size());
        Completable.merge(getAsConcurrentTasks()).blockingAwait();
    }

    private Iterable<? extends CompletableSource> getAsConcurrentTasks() {
        final Scheduler scheduler = createScheduler();
        return tasks.stream()
                .filter(Objects::nonNull)
                .map(task -> Completable.fromAction(task::execute).subscribeOn(scheduler))
                .collect(Collectors.toList());
    }

    private Scheduler createScheduler() {
        switch (schedulerType) {
            case "single":
                // 返回一个单线程处理器
                return Schedulers.single();
            case "newThread":
                // 每次都启用新线程
                return Schedulers.newThread();
            case "io":
                // 内部是一个无上限的线程池，因此多数情况下，io比newThread要有效率
                return Schedulers.io();
            case "computation":
                // 生成一个固定大小为CPU核心数的线程池，适合于CPU密集型计算
                return Schedulers.computation();
            case "trampoline":
                // 直接在当前线程执行，如果当前线程正在执行任务，则会现在暂停正在执行的任务
                return Schedulers.trampoline();
            case "from":
                // 将Executor 转化为一个调度器
                return Schedulers.from(Executors.newFixedThreadPool(numberOfCurrentThreads));
            default:
                throw new RuntimeException(String.format("不支持的调度器类型 #%s", schedulerType));
        }

    }

    private boolean isTaskCollectionEmpty() {
        return CollectionUtils.isEmpty(tasks);
    }
}
