package com.github.edgar615.util.base;

/**
 * Luhn算法.
 * https://zh.wikipedia.org/wiki/Luhn%E7%AE%97%E6%B3%95.
 * <p>
 * Luhn算法会通过校验码对一串数字进行验证，校验码通常会被加到这串数字的末尾处，从而得到一个完整的身份识别码。
 * <p>
 * 我们以数字“7992739871”为例，计算其校验位：
 * <p>
 * 从校验位开始，从右往左，偶数位乘2（例如，7*2=14），然后将两位数字的个位与十位相加（例如，10：1+0=1，14：1+4=5）；
 * 把得到的数字加在一起（本例中得到67）；
 * 将数字的和取模10（本例中得到7），再用10去减（本例中得到3），得到校验位。
 * <p>
 * 代码核心实现来自https://gist.github.com/mdp/9691528
 */
public class Luhn {

  public static String generate(String numString) {
    return numString + generateCheckNum(numString);
  }

  public static int generateCheckNum(String numString) {
    int sum = 0;
    boolean alternate = true;
    for (int i = numString.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(numString.substring(i, i + 1));
      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return 10 - (sum % 10);
  }

  public static boolean check(String numString) {
    int sum = 0;
    boolean alternate = true;
    for (int i = numString.length() - 2; i >= 0; i--) {
      int n = Integer.parseInt(numString.substring(i, i + 1));
      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    int checkNum =
            Integer.parseInt(numString.substring(numString.length() - 1, numString.length()));
    return ((10 - sum % 10) == checkNum);
  }
}