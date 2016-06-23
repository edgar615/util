package com.edgar.util.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class GetNetworkAddress {

  private GetNetworkAddress() {
    throw new AssertionError("Not instantiable: " + GetNetworkAddress.class);
  }

  public static String getIpv4() {
    try {
      InetAddress lanIp = getInetAddress();
      if (lanIp == null) {
        return null;
      }
      return lanIp.toString().replaceAll("^/+", "");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getMac() {
    try {
      InetAddress lanIp = getInetAddress();
      if (lanIp == null) {
        return null;
      }
      return getMac(lanIp);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 返回第一个有效IP
   *
   * @return
   * @throws SocketException
   * @throws UnknownHostException
   */
  private static InetAddress getInetAddress() throws SocketException, UnknownHostException {
    Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
    while (net.hasMoreElements()) {
      NetworkInterface element = net.nextElement();
      Enumeration<InetAddress> addresses = element.getInetAddresses();
      while (addresses.hasMoreElements()) {
        InetAddress ip = addresses.nextElement();
        if (ip instanceof Inet4Address) {
          if (ip.isSiteLocalAddress()) {
            return InetAddress.getByName(ip.getHostAddress());
          }
        }
      }
    }
    return null;
  }

  private static String getMac(InetAddress ip) {
    String address = null;
    try {
      NetworkInterface network = NetworkInterface.getByInetAddress(ip);
      byte[] mac = network.getHardwareAddress();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < mac.length; i++) {
        sb.append(String.format("%02X", mac[i]));
//        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
      }
      address = sb.toString();

    } catch (SocketException e) {
      e.printStackTrace();
    }
    return address;
  }

}