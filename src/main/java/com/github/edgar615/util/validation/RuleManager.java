package com.github.edgar615.util.validation;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rule的String定义解析.
 *
 * @author Edgar
 * @create 2018-09-05 11:06
 **/
public class RuleManager {

  /**
   * Splits each key-value pair.
   */
  private static final Splitter KEYS_SPLITTER = Splitter.on('|').trimResults().omitEmptyStrings();

  /**
   * Splits the key from the value.
   */
  private static final Splitter KEY_VALUE_SPLITTER = Splitter.on(':').trimResults()
      .omitEmptyStrings();

  /**
   * Join each key-value pair.
   */
  private static final Joiner KEYS_JOINER = Joiner.on('|').skipNulls();

  /**
   * Join the key with the value.
   */
  private static final Joiner KEY_VALUE_JOINER = Joiner.on(':').skipNulls();

  /**
   * Parse
   */
  private static final RuleParse ALPHA_NUMBER_PARSE = new AlphaNumberRule.Parse();
  private static final RuleParse ALPHA_PARSE = new AlphaRule.Parse();
  private static final RuleParse ALPHA_SPACE_PARSE = new AlphaSpaceRule.Parse();
  private static final RuleParse ALPHA_UNDERSCORE_PARSE = new AlphaUnderscoreRule.Parse();
  private static final RuleParse BOOL_PARSE = new BoolRule.Parse();
  private static final RuleParse BYTE_PARSE = new ByteRule.Parse();
  private static final RuleParse DATETIME_PARSE = new DateTimeRule.Parse();
  private static final RuleParse DECIMAL_PARSE = new DecimalRule.Parse();
  private static final RuleParse DIGITS_PARSE = new DigitsRule.Parse();
  private static final RuleParse DOUBLE_PARSE = new DoubleRule.Parse();
  private static final RuleParse EMAIL_PARSE = new EmailRule.Parse();
  private static final RuleParse EQUALS_PARSE = new EqualsRule.Parse();
  private static final RuleParse FIX_LENGTH_PARSE = new FixLengthRule.Parse();
  private static final RuleParse FLOAT_PARSE = new FloatRule.Parse();
  private static final RuleParse Int_PARSE = new IntRule.Parse();
  private static final RuleParse ISO8601_DATE_PARSE = new ISO8601DateRule.Parse();
  private static final RuleParse ISO8601_DATETIME_PARSE = new ISO8601DateTimeRule.Parse();
  private static final RuleParse ISO8601_TIME_PARSE = new ISO8601TimeRule.Parse();
  private static final RuleParse LIST_PARSE = new ListRule.Parse();
  private static final RuleParse LONG_PARSE = new LongRule.Parse();
  private static final RuleParse MAP_PARSE = new MapRule.Parse();
  private static final RuleParse MAX_LENGTH_PARSE = new MaxLengthRule.Parse();
  private static final RuleParse MAX_PARSE = new MaxRule.Parse();
  private static final RuleParse MIN_LENGTH_PARSE = new MinLengthRule.Parse();
  private static final RuleParse MIN_PARSE = new MinRule.Parse();
  private static final RuleParse OPTIONAL_RULE = new OptionalRule.Parse();
  private static final RuleParse PROHIBITED_PARSE = new ProhibitedRule.Parse();
  private static final RuleParse REGEX_PARSE = new RegexRule.Parse();
  private static final RuleParse REQUIRED_PARSE = new RequiredRule.Parse();
  private static final RuleParse SHORT_PARSE = new ShortRule.Parse();

  private static final RuleManager INSTANCE = new RuleManager();
  private final List<RuleParse> parses;

  private RuleManager() {
    this.parses = parses(ALPHA_NUMBER_PARSE, ALPHA_PARSE, ALPHA_SPACE_PARSE, ALPHA_UNDERSCORE_PARSE,
        BOOL_PARSE, BYTE_PARSE, DATETIME_PARSE, DECIMAL_PARSE, DIGITS_PARSE,
        DOUBLE_PARSE, EMAIL_PARSE, EQUALS_PARSE, FIX_LENGTH_PARSE, FLOAT_PARSE, Int_PARSE,
        ISO8601_DATE_PARSE,
        ISO8601_DATETIME_PARSE, ISO8601_TIME_PARSE, LIST_PARSE, LONG_PARSE, MAP_PARSE,
        MAX_LENGTH_PARSE, MAX_PARSE,
        MIN_LENGTH_PARSE, MIN_PARSE, OPTIONAL_RULE, PROHIBITED_PARSE, REGEX_PARSE, REQUIRED_PARSE,
        SHORT_PARSE);
  }

  public static RuleManager instance() {
    return INSTANCE;
  }

  /**
   * 将字符串解析为Rule.
   *
   * @param specification 字符串规则
   * @return Rule的集合
   */
  public List<Rule> parse(String specification) {
    List<Rule> rules = new ArrayList<>();
    if (!specification.isEmpty()) {
      for (String keyValuePair : KEYS_SPLITTER.split(specification)) {
        List<String> keyAndValue = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(keyValuePair));
        Preconditions.checkArgument(!keyAndValue.isEmpty(), "blank key-value pair");
        Preconditions.checkArgument(
            keyAndValue.size() <= 2,
            "key-value pair %s with more than one equals sign",
            keyValuePair);
        List<Rule> parsedRule = parses.stream().map(p -> p.parse(keyAndValue))
            .filter(p -> p != null).collect(Collectors.toList());
        rules.addAll(parsedRule);
      }
    }
    return rules;
  }

  /**
   * 将Rule解析为字符串
   *
   * @param rules Rule的集合
   * @return 字符串
   */
  public String toParsableString(List<Rule> rules) {
    List<String> parsableString = new ArrayList<>();
    for (Rule rule : rules) {
      for (RuleParse parse : parses) {
        List<String> keyAndValue = parse.toParsableString(rule);
        if (!keyAndValue.isEmpty()) {
          parsableString.add(KEY_VALUE_JOINER.join(keyAndValue));
        }
      }
    }
    return KEYS_JOINER.join(parsableString);
  }

  private List<RuleParse> parses(RuleParse... parses) {
    List<RuleParse> arr = new ArrayList<>(parses.length);
    for (RuleParse parse : parses) {
      arr.add(parse);
    }
    return arr;
  }
}
