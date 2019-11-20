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

import java.util.List;

/**
 * Rule的解析.
 *
 * @author Edgar
 * @create 2018-09-05 20:23
 **/
public interface RuleParser {

  /**
   * 将字符串定义转换为rule.
   *
   * @param keyAndValue 两个字符串组成的定义，一个是key，第二个是参数.
   * @return 校验规则
   */
  Rule parse(List<String> keyAndValue);

  /**
   * 将Rule转换为字符串定义.
   *
   * @param rule 校验规则
   * @return 两个字符串组成的定义
   */
  List<String> toParsableString(Rule rule);
}
