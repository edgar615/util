/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.util.validation;

import com.github.edgar615.util.validation.LongRule.Parser;
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
   * Parser
   */
  private static final RuleParser ALPHA_NUMBER_PARSE = new AlphaNumberRule.Parser();
  private static final RuleParser ALPHA_PARSE = new AlphaRule.Parser();
  private static final RuleParser ALPHA_SPACE_PARSE = new AlphaSpaceRule.Parser();
  private static final RuleParser ALPHA_UNDERSCORE_PARSE = new AlphaUnderscoreRule.Parser();
  private static final RuleParser BOOL_PARSE = new BoolRule.Parser();
  private static final RuleParser BYTE_PARSE = new ByteRule.Parser();
  private static final RuleParser DATETIME_PARSE = new DateTimeRule.Parser();
  private static final RuleParser DECIMAL_PARSE = new DecimalRule.Parser();
  private static final RuleParser DIGITS_PARSE = new DigitsRule.Parser();
  private static final RuleParser DOUBLE_PARSE = new DoubleRule.Parser();
  private static final RuleParser EMAIL_PARSE = new EmailRule.Parser();
  private static final RuleParser EQUALS_PARSE = new EqualsRule.Parser();
  private static final RuleParser FIX_LENGTH_PARSE = new FixLengthRule.Parser();
  private static final RuleParser FLOAT_PARSE = new FloatRule.Parser();
  private static final RuleParser Int_PARSE = new IntRule.Parser();
  private static final RuleParser ISO8601_DATE_PARSE = new ISO8601DateRule.Parser();
  private static final RuleParser ISO8601_DATETIME_PARSE = new ISO8601DateTimeRule.Parser();
  private static final RuleParser ISO8601_TIME_PARSE = new ISO8601TimeRule.Parser();
  private static final RuleParser LIST_PARSE = new ListRule.Parser();
  private static final RuleParser LONG_PARSE = new Parser();
  private static final RuleParser MAP_PARSE = new MapRule.Parser();
  private static final RuleParser MAX_LENGTH_PARSE = new MaxLengthRule.Parser();
  private static final RuleParser MAX_PARSE = new MaxRule.Parser();
  private static final RuleParser MIN_LENGTH_PARSE = new MinLengthRule.Parser();
  private static final RuleParser MIN_PARSE = new MinRule.Parser();
  private static final RuleParser OPTIONAL_RULE = new OptionalRule.Parser();
  private static final RuleParser PROHIBITED_PARSE = new ProhibitedRule.Parser();
  private static final RuleParser REGEX_PARSE = new RegexRule.Parser();
  private static final RuleParser REQUIRED_PARSE = new RequiredRule.Parser();
  private static final RuleParser SHORT_PARSE = new ShortRule.Parser();

  private static final RuleManager INSTANCE = new RuleManager();
  private final List<RuleParser> parses;

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
      for (RuleParser parse : parses) {
        List<String> keyAndValue = parse.toParsableString(rule);
        if (!keyAndValue.isEmpty()) {
          parsableString.add(KEY_VALUE_JOINER.join(keyAndValue));
        }
      }
    }
    return KEYS_JOINER.join(parsableString);
  }

  private List<RuleParser> parses(RuleParser... parses) {
    List<RuleParser> arr = new ArrayList<>(parses.length);
    for (RuleParser parse : parses) {
      arr.add(parse);
    }
    return arr;
  }
}
