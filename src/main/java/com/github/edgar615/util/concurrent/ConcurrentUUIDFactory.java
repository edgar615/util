package com.github.edgar615.util.concurrent;

import com.github.edgar615.util.uuid.UUIDFactory;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * UUIDFactory implementation that will perform reasonably when used by multiple threads under
 * load.
 *
 * @author Edgar | 实际上是从网上复制的，来源忘记了
 */
public class ConcurrentUUIDFactory implements UUIDFactory {

  private ConcurrentUUIDFactory() {

  }

  public static ConcurrentUUIDFactory create() {
    return new ConcurrentUUIDFactory();
  }

  @Override
  public UUID uuid() {
    final Random rnd = ThreadLocalRandom.current();
    long mostSig = rnd.nextLong();
    long leastSig = rnd.nextLong();
// Identify this as a version 4 UUID, that is one based on a random value.
    mostSig &= 0xffffffffffff0fffL;
    mostSig |= 0x0000000000004000L;
// Set the variant identifier as specified for version 4 UUID values. The two
// high order bits of the lower word are required to be one and zero, respectively.
    leastSig &= 0x3fffffffffffffffL;
    leastSig |= 0x8000000000000000L;
    return new UUID(mostSig, leastSig);
  }
}