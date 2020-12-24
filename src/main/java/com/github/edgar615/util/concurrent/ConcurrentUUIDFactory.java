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
