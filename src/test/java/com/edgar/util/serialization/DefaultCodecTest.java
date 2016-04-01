package com.edgar.util.serialization;

import com.edgar.util.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edgar on 16-4-1.
 */
public class DefaultCodecTest {

  @Test
  public void testCodec() throws IOException {
    Map<String, String> map = new HashMap<>();
    map.put("foo", "bar");
    Codec<Map<String, String>> codec = new DefaultCodec<>();
    FileOutputStream fos = new FileOutputStream(new File("testcodec"));
    codec.serialize(map, fos);
    FileInputStream fis = new FileInputStream(new File("testcodec"));
    Map<String, String> newMap = codec.deserialize(fis);
    Assertions.assertThat(newMap).hasSize(1).containsKeys("foo");
    IOUtils.deleteFile(new File("testcodec"));

  }
}
