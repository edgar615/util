package com.edgar.util.id;

/**
 * 默认的ID生成类.
 * 64 bits组成：41bits的时间戳+22bits的自增序列
 * <p>
 * 该ID的生成策略只能保证在同一台机器上，ID不重复。
 * 每毫秒可以生成4194304个ID，足够一般的项目使用
 * <p>
 * * <b>注意:第一个bit并没有使用,实际上也可以作为long的符号位</b>
 * 单例类。
 *
 * @author Edgar
 */
class DefaultIdFactory implements IdFactory, IdExtracter {

    /**
     * 自增序列的位数
     */
    private static final int SEQ_BIT = 22;

    /**
     * 最大序列号
     */
    private static final int SEQ_MASK = -1 ^ (-1 << SEQ_BIT);

    /**
     * 时间的左移位数
     */
    private static final int TIME_LEFT_BIT = SEQ_BIT;

    /**
     * 每次初始化对序列值.
     */
    private static final int INIT_SEQ = 0;

    /**
     * 单例对象
     */
    private static final DefaultIdFactory INSTANCE = new DefaultIdFactory();
    /**
     * 上次事件戳
     */
    private volatile long lastTime = -1l;
    /**
     * 自增序列
     */
    private volatile long seqId = INIT_SEQ;

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
     * 64 bits组成：41bits的时间戳+22bits的自增序列
     *
     * @return ID
     */
    @Override
    public synchronized long nextId() {
        long time = currentTime();
        if (time < lastTime) {//当前时间小于上次时间，说明时钟不对
            throw new IllegalStateException("Clock moved backwards.");
        }

        if (time == lastTime) {
            System.out.println(seqId);
            seqId = (seqId + 1) & SEQ_MASK;
            if (seqId == 0) {//说明该毫秒下对序列已经自增完毕，等待下一个毫秒
                tilNextMillis(lastTime);
            }
        } else {
            //SEQ.set(0);
            System.out.println("reset");
            seqId = INIT_SEQ;
        }
        lastTime = time;
//        long seqId = SEQ.incrementAndGet();
//        if (seqId > SEQ_MASK) {
//            SEQ.compareAndSet(seqId, 0);
//            seqId = SEQ.incrementAndGet();
//        }
        System.out.println(time);
        System.out.println(seqId);
        long id = time << TIME_LEFT_BIT;
        id |= seqId & SEQ_MASK;
        return id;
    }

    /**
     * 循环直到下一个毫秒
     *
     * @param lastTime 原毫秒数
     * @return
     */
    private long tilNextMillis(long lastTime) {
        long timestamp = currentTime();
        while (timestamp <= lastTime) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    private long currentTime() {
        return System.currentTimeMillis();
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
        return id >> TIME_LEFT_BIT;
    }


    /**
     * 从主键中提取自增ID.
     *
     * @param id 主键
     * @return 自增ID
     */
    @Override
    public long fetchSeqId(long id) {
        return (id ^ (fetchTime(id) << TIME_LEFT_BIT));
    }
}
