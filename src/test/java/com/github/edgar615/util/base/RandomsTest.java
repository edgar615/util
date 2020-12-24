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

package com.github.edgar615.util.base;

import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/31.
 *
 * @author Edgar  Date 2016/3/31
 */
public class RandomsTest {

  @Test
  public void generateRandomString() {
    System.out.println(UUID.randomUUID().toString().replace("-", ""));
    System.out.println(UUID.randomUUID().toString().replace("-", ""));
    String s1 = Randoms.randomString(10, "abc");
    Assertions.assertThat(s1).hasSize(10).doesNotContain("d").doesNotContain("e");

  }
}
