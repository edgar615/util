package com.edgar.util.concurrent;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 重试任务.
 * 如果任务返回false,将重试
 */
public class RetryFutureTask<T, E extends Exception> extends FutureTask<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryFutureTask.class);

    private final ScheduledExecutorService executorService;

    /**
     * 最多重试对次数
     */
    private final int maxRetries;
    /**
     * runnable遇到exceptionClass时重试
     */
    private final Class<E> exceptionClass;

    /**
     * 延迟多长时间重试任务
     */
    private final int delay;

    /**
     * 延迟多长时间重试任务
     */
    private final TimeUnit unit;

    /**
     * 托管的任务.
     */
    private final Callable<T> callable;
    /**
     * 重试的次数
     */
    private int numRetries = 0;


    public RetryFutureTask(Callable<T> callable, ScheduledExecutorService executorService, int maxRetries, Class<E> exceptionClass, int delay, TimeUnit unit) {
        super(callable);
        this.callable = Preconditions.checkNotNull(callable);
        this.executorService = Preconditions.checkNotNull(executorService);
        this.maxRetries = maxRetries;
        this.exceptionClass = Preconditions.checkNotNull(exceptionClass);
        this.delay = delay;
        this.unit = Preconditions.checkNotNull(unit);
    }

    @Override
    public void run() {
        T result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            LOGGER.warn("Exception while executing task.", e);
            if (e.getClass().isAssignableFrom(exceptionClass)) {
                numRetries++;
                if (numRetries > maxRetries) {
                    LOGGER.debug("Task did not complete after " + maxRetries + " retries, giving up.");
                    set(null);
                } else {
                    LOGGER.debug("Task was not successful, resubmitting (num retries: " + numRetries + ")");
                    retry();
                }
            } else {
                LOGGER.debug("Giving up on task: due to unhandled exception ");
                set(null);
            }
        }
        set(result);
    }

    /**
     * 重试任务
     */
    private void retry() {
        executorService.schedule(this, delay, unit);
    }
}
