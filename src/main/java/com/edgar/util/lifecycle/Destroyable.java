package com.edgar.util.lifecycle;

/**
 * This interface defines the API of an entity that is destroyable. The main
 * difference between <code>Destroyable</code> and <code>Shutdownable</code>
 * is that for the entity there is nothing to wait for (in other words all the
 * calls to the entity are blocking calls). It is also possible to have an
 * entity that implements both <code>Destroyable</code> and
 * <code>Shutdownable</code> in which case, the proper way would be:
 * <p>
 * <pre>
 *   entity.shutdown();
 *   entity.waitForShutdown();
 *   // here we know that all the non blocking calls are terminated
 *   entity.destroy();
 * </pre>
 *
 * @author ypujante@linkedin.com
 * @see Shutdownable
 */
public interface Destroyable extends Terminable {
  /**
   * This method destroys the entity, cleaning up any resource that needs to
   * be cleaned up, like closing files, database connection..
   */
  public void destroy();
}