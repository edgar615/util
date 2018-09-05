package com.github.edgar615.util.validation;

/**
 * 规则和String的相互转化.
 *
 * @author Edgar
 * @create 2018-09-05 10:34
 **/
public interface RuleCodec {

  /**
   * 将rule转换为String.
   *
   * @param rule 源数据
   * @return string
   */
  String encodeToStr(Rule rule);

  /**
   * 将string转换为rule.
   *
   * @param str 源数据
   * @return Rule
   */
  Rule decodeFromStr(String str);

  /**
   * @return 解码器名称
   */
  String name();

//  List<Rule> fromStr(String spec) {
//    List<Rule> rules = new ArrayList<>();
//    Splitter.on("|").omitEmptyStrings()
//        .trimResults()
//        .split(spec);
//    return rules;
//  }

}
