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

package com.github.edgar615.util.auth;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/13.
 */
public class PrincipalImpl implements Principal {

  private Long userId;

  private String username;

  private String name;

  private final Map<String, Object> ext = new HashMap<>();


  @Override
  public Map<String, Object> ext() {
    return ImmutableMap.copyOf(ext);
  }

  @Override
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addExt(String name, Object value) {
    this.ext.put(name, value);
  }
}
