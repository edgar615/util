package com.github.edgar615.util.net;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * IP工具.
 *
 * @author Edgar | 实际上是从网上复制的，来源忘记了
 */
public class IPUtils {

  private IPUtils() {
    throw new AssertionError("Not instantiable: " + IPUtils.class);
  }

  //功能：判断IPv4地址的正则表达式：
  private static final Pattern IPV4_REGEX =
      Pattern.compile(
          "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

  //功能：判断标准IPv6地址的正则表达式
  private static final Pattern IPV6_STD_REGEX =
      Pattern.compile(
          "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

  //功能：判断一般情况压缩的IPv6正则表达式
  private static final Pattern IPV6_COMPRESS_REGEX =
      Pattern.compile(
          "^((?:[0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((?:([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$");

  /*由于IPv6压缩规则是必须要大于等于2个全0块才能压缩
           不合法压缩 ： fe80:0000:8030:49ec:1fc6:57fa:ab52:fe69
  ->           fe80::8030:49ec:1fc6:57fa:ab52:fe69
          该不合法压缩地址直接压缩了处于第二个块的单独的一个全0块，
          上述不合法地址不能通过一般情况的压缩正则表达式IPV6_COMPRESS_REGEX判断出其不合法
          所以定义了如下专用于判断边界特殊压缩的正则表达式
  (边界特殊压缩：开头或末尾为两个全0块，该压缩由于处于边界，且只压缩了2个全0块，不会导致':'数量变少)*/
  //功能：抽取特殊的边界压缩情况
  private static final Pattern IPV6_COMPRESS_REGEX_BORDER =
      Pattern.compile(
          "^(::(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5})|((?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5}::)$");

  //判断是否为合法IPv4地址
  public static boolean isIPv4Address(final String input) {
    return IPV4_REGEX.matcher(input).matches();
  }

  //判断是否为合法IPv6地址
  public static boolean isIPv6Address(final String input) {
    int num = 0;
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == ':') {
        num++;
      }
    }
    if (num > 7) {
      return false;
    }
    if (IPV6_STD_REGEX.matcher(input).matches()) {
      return true;
    }
    if (num == 7) {
      return IPV6_COMPRESS_REGEX_BORDER.matcher(input).matches();
    } else {
      return IPV6_COMPRESS_REGEX.matcher(input).matches();
    }
  }

  /**
   * 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
   */
  public static long ipToLong(String strIp) {
    long[] ip = new long[4];
    //先找到IP地址字符串中.的位置
    int position1 = strIp.indexOf(".");
    int position2 = strIp.indexOf(".", position1 + 1);
    int position3 = strIp.indexOf(".", position2 + 1);
    //将每个.之间的字符串转换成整型
    ip[0] = Long.parseLong(strIp.substring(0, position1));
    ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
    ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
    ip[3] = Long.parseLong(strIp.substring(position3 + 1));
    return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
  }

  /**
   * 将十进制整数形式转换成127.0.0.1形式的ip地址
   */
  public static String longToIP(long longIp) {
    StringBuffer sb = new StringBuffer("");
    //直接右移24位
    sb.append(String.valueOf((longIp >>> 24)));
    sb.append(".");
    //将高8位置0，然后右移16位
    sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
    sb.append(".");
    //将高16位置0，然后右移8位
    sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
    sb.append(".");
    //将高24位置0
    sb.append(String.valueOf((longIp & 0x000000FF)));
    return sb.toString();
  }

  public static BigInteger ipv6ToNumber(String addr) {
    int startIndex=addr.indexOf("::");

    if(startIndex!=-1){


      String firstStr=addr.substring(0,startIndex);
      String secondStr=addr.substring(startIndex+2, addr.length());


      BigInteger first=ipv6ToNumber(firstStr);

      int x=countChar(addr, ':');

      first=first.shiftLeft(16*(7-x)).add(ipv6ToNumber(secondStr));

      return first;
    }


    String[] strArr = addr.split(":");

    BigInteger retValue = BigInteger.valueOf(0);
    for (int i=0;i<strArr.length;i++) {
      BigInteger bi=new BigInteger(strArr[i], 16);
      retValue = retValue.shiftLeft(16).add(bi);
    }
    return retValue;
  }


  public static String numberToIPv6(BigInteger ipNumber) {
    String ipString ="";
    BigInteger a=new BigInteger("FFFF", 16);

    for (int i=0; i<8; i++) {
      ipString=ipNumber.and(a).toString(16)+":"+ipString;

      ipNumber = ipNumber.shiftRight(16);
    }

    return ipString.substring(0, ipString.length()-1);

  }

  private static int countChar(String str, char reg){
    char[] ch=str.toCharArray();
    int count=0;
    for(int i=0; i<ch.length; ++i){
      if(ch[i]==reg){
        if(ch[i+1]==reg){
          ++i;
          continue;
        }
        ++count;
      }
    }
    return count;
  }
}
