package com.edgar.util.lifecycle;

/**
 * Defines an entity that can be started.
 *
 * @author ypujante@linkedin.com
 */
public interface Startable {
  /**
   * This method must NOT block and should start the entity and return right away.
   */
  void start();
}