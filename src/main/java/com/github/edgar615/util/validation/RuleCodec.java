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
