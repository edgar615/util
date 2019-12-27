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

package com.github.edgar615.util.interceptor;

import com.github.edgar615.util.reflect.Device;

/**
 * 数据访问层的接口.
 *
 * @author Edgar  Date 2017/5/22
 */
public interface DeviceDao {

  /**
   * 根据主键删除.
   *
   * @param elementType 持久化对象
   * @param id 主键
   * @return 删除记录数
   */
  int deleteById(Class<Device> elementType, Long id);


}
