package com.edgar.util.id;

import com.edgar.util.net.NetUtils;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

/**
 * 获取IP的二进制表示的后10位，设置成workerId。
 * Created by edgar on 16-11-26.
 */
public class IpWorkerIdCreator implements WorkerIdCreator {
  @Override
  public int create() {
    String ip = NetUtils.getIpv4();
    StringBuilder hexIp = new StringBuilder();
    Splitter.on(".").omitEmptyStrings().trimResults().split(ip)
        .forEach(s -> hexIp.append(Strings.padStart(Integer.toBinaryString(Integer.parseInt(s)), 8, '0')));
    return Integer.parseInt(hexIp.substring(22), 2);
  }
}
