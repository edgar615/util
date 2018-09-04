package com.github.edgar615.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Edgar on 2017/4/14.
 *
 * @author Edgar  Date 2017/4/14
 */
public class CallbackTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackTask.class);

    private final Runnable task;

    private final Runnable callback;

    public CallbackTask(Runnable task, Runnable callback) {
        this.task = task;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception e) {
            LOGGER.error("Caught unexpected Throwable", e);
        } finally {
            try {
                callback.run();
            } catch (Exception e) {
                LOGGER.error("Caught unexpected Throwable", e);
            }
        }
    }
}
