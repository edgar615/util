/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
