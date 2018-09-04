package com.github.edgar615.util.serialization;

import com.github.edgar615.util.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
