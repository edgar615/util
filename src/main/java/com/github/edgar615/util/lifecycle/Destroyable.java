package com.github.edgar615.util.lifecycle;

/**
 * 表明这个类是可以销毁的类.
 *
 * @author ypujante@linkedin.com
 */
public interface Destroyable extends Terminable {
    /**
     * 销毁方法,尽量不要阻塞该方法.
     */
    void destroy();
}