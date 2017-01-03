package com.edgar.util.net;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import org.junit.Test;

/**
 * Created by edgar on 16-11-26.
 */
public class NetUtilsTest {

  @Test
  public void testIp() {
    System.out.println(NetUtils.getMac());
    System.out.println(NetUtils.getIpv4());
    String ip = NetUtils.getIpv4();
    //32bit 4*8
    System.out.println(Integer.toBinaryString(255));
    StringBuilder hexIp = new StringBuilder();
    Splitter.on(".").omitEmptyStrings().trimResults().split(ip)
            .forEach(s -> hexIp
                    .append(Strings.padStart(Integer.toBinaryString(Integer.parseInt(s)), 8, '0')));

    System.out.println(hexIp.substring(22));
    System.out.println(Integer.parseInt(hexIp.substring(22), 2));

  }
}
