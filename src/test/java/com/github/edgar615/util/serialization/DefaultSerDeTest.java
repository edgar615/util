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

package com.github.edgar615.util.serialization;

import com.github.edgar615.util.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by edgar on 16-4-1.
 */
public class DefaultSerDeTest {

  @Test
  public void testCodec() throws IOException {
    Map<String, String> map = new HashMap<>();
    map.put("foo", "bar");
    SerDe<Map<String, String>> serDe = new DefaultSerDe<>();
    FileOutputStream fos = new FileOutputStream(new File("testcodec"));
    serDe.serialize(map, fos);
    FileInputStream fis = new FileInputStream(new File("testcodec"));
    Map<String, String> newMap = serDe.deserialize(fis);
    Assertions.assertThat(newMap).hasSize(1).containsKeys("foo");
    IOUtils.deleteFile(new File("testcodec"));

  }
}
