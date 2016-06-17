package com.edgar.util.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 默认的ID生成类.
 * 64 bits组成：41bits的时间戳+23bits的自增序列
 * <p>
 * 该ID的生成策略只能保证在同一台机器上，不重复。
 * 没毫秒可以生成8388608个ID，足够使用
 * <p>
 * 单例类。
 *
 * @author Edgar
 */
class DefaultIdFactory implements IdFactory, IdExtracter {
  private static final DefaultIdFactory INSTANCE = new DefaultIdFactory();

  /**
   * 自增序列
   */
  private static final AtomicLong seq = new AtomicLong(1);

  private static final AtomicLong currentTime = new AtomicLong(0);

  private DefaultIdFactory() {
  }

  /**
   * 返回DefaultIdFactory的单例对象
   *
   * @return IdFactory
   */
  static DefaultIdFactory instance() {
    return INSTANCE;
  }

  /**
   * 64 bits组成：32bits的时间戳+32bits的自增序列
   *
   * @return ID
   */
  @Override
  public long generateId() {
    long seqId = seq.incrementAndGet();
    long time = System.currentTimeMillis();
    long current =     currentTime.getAndSet(time);
    if (time > current) {//当前时间被替换//重新设置序列号
      System.out.println("current:" + current + ",time:" + time);
    }

    System.out.println("time:" + time +",seq:" + seqId);
    long id = time << 22;
    id |= (seqId % 8388608);
    return id;
  }

  /**
   * 从ID中提取时间.
   * 将ID左移23位，提取出时间
   *
   * @param id ID
   * @return 时间
   */
  @Override
  public long fetchTime(long id) {
    return id >> 22;
  }


  /**
   * 从主键中提取自增ID.
   *
   * @param id 主键
   * @return 自增ID
   */
  @Override
  public long fetchSeqId(long id) {
    return (id ^ (fetchTime(id) << 22));
  }
}
