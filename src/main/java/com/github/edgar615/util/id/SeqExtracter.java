package com.github.edgar615.util.id;

/**
 * 从ID中提取出自增序列.
 */
public interface SeqExtracter<T> {

    /**
     * 从ID中提取自增序列.
     *
     * @param id ID
     * @return 自增序列
     */
    long fetchSeq(T id);
}
