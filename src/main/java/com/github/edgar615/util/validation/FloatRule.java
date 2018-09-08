package com.github.edgar615.util.validation;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * 校验是否是float.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 * <p>
 * //Float.MIN_VALUE是正数，不是负数 //      无穷大 Infinity //      在Float类中，有两个静态常量： //      public static
 * final float POSITIVE_INFINITY = 1.0f / 0.0f; //      public static final float NEGATIVE_INFINITY
 * = -1.0f / 0.0f; //      这两个常量一个表示正无穷，一个表示负无穷。在计算上，有一点一定要特别注意： //      无穷大加上一个数还是无穷大。 // //
 * 如果你要初始化一个数为无穷大，那么可以用任何被计算为无穷大的浮点算术表达式来初始化，或者使用Float提供的常量： //      float infinity = 1.0 / 0.0; //
 * float infinity  = Float.POSITIVE_INFINITY; // //      不是数字的数 NaN //      NaN，not a
 * number，对于所有没有良好的数字定义的浮点计算，如0.0/0.0，结果都是NaN。在Float类中，提供了一个常量来表示NaN： //      public static final
 * float NaN = 0.0f / 0.0f; //      初始化一个数字为NaN，可以像Float一样用任何计算结果为NaN 的浮点算术表达式或直接用Float提供的这个常量： //
 * //      float nan= 0.0 / 0.0; //      float nan  = Float.NaN ; //      NaN 有一些奇葩的性质，在计算时需要特别注意：
 * //      NaN 不等于任何浮点数值，包括它自身在内；即它与任何数比较均返回false。 //      任何浮点操作，只要它的一个或多个操作数为NaN，那么其结果为NaN。 //
 * 虽然NaN与任何数比较均返回false，但是使用Float.compare()这个方法来比较两个NaN时，却会得到相等的结果。如下： //      Float 中的其他常量： // float
 * POSITIVE_INFINITY = 1.0f / 0.0f;正无穷大 //      float NEGATIVE_INFINITY = -1.0f / 0.0f;负无穷大 // float
 * NaN = 0.0f / 0.0f;表示不是一个数字 //      float MAX_VALUE = 0x1.fffffeP+127f;
 * float能表示的最大正值：3.4028235e+38f //      float MIN_NORMAL = 0x1.0p-126f; 1.17549435E-38f // float
 * MIN_VALUE = 0x0.000002P-126f; float能表示的最小正值 1.4e-45f //      final int MAX_EXPONENT = 127;
 * 一个有限float数值的最大指数值 //      final int MIN_EXPONENT = -126; 一个有限float数值的最小指数值 //      final int SIZE
 * = 32; 一个float类型数值占用的比特数 //      final int BYTES = SIZE / Byte.SIZE; 一个float类型数值占用的字节数 // // 另外，在
 * Java 提供的另一个浮点类Double类中，也存在相同意义的常量，只是值不同而已。
 * <p>
 * //    long整型数，在内存中占用8个字节共64位，它表示的数值有2的64次方，平分正负，数值范围是负2的63次方到正2的63次方-1。 //
 * 而float在内存中占4个字节，共32位，但是浮点数在内存中是这样的：V=(-1)^s * M * 2^E //    其中第1位，符号位，即S。    　　接下来的8位，指数域，即E。
 * 　　剩下的23位，小数域，即M，M的取值范围为[1，2）或[0，1）。 //    　　也就是说，浮点数在内存中的二进制值不是直接转换为十进制数值的，而是按照上述公式计算而来，通过这个公式，虽然只用到了4个字节，但是浮点数却比长整型的最大值要大
 *
 * @author Edgar  Date 2016/1/6
 */
class FloatRule implements Rule {

  private static final String KEY = "float";

  private static final String TRUE = "true";

  private FloatRule() {
  }

  static Rule create() {
    return new FloatRule();
  }

  @Override
  public String message() {
    return "Float Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Float) {
      return true;
    }
    if (property instanceof Double) {
      return true;
    }
    if (property instanceof Integer) {
      return true;
    }
    if (property instanceof Long) {
      return true;
    }
    if (property instanceof Short) {
      return true;
    }
    if (property instanceof Byte) {
      return true;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      try {
        Float.parseFloat(str);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("FloatRule")
        .toString();
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new FloatRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new FloatRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof FloatRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
