package com.wuhulala.springboot.rxjava;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/24<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Getter
@RequiredArgsConstructor
public class MockTask implements ITask{

    private final int delayInSeconds;

    private boolean started;

    private boolean finishedSuccessfully;

    private boolean interrupted;

    private long threadId;

    @Override
    public void execute(){
        try {
            this.threadId = Thread.currentThread().getId();
            this.started = true;
            TimeUnit.SECONDS.sleep(delayInSeconds);
            this.finishedSuccessfully = true;
        } catch (InterruptedException e) {
            this.interrupted = true;
        }
    }

    public static MockTask noDelayedTask(){
        return new MockTask(0);
    }

    public static MockTask fiveSecondDelayedTask(){
        return new MockTask(5);
    }

}
