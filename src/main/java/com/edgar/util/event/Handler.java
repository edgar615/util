package com.edgar.util.event;

/**
 * Created by Edgar on 2016/7/8.
 *
 * @author Edgar  Date 2016/7/8
 */
public interface Handler<T> {

    void handle(T event);
}
