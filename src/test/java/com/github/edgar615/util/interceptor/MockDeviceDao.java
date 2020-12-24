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
import com.github.edgar615.util.search.Example;
import java.util.List;
import java.util.Map;

/**
 * @author Edgar
 * @create 2018-09-14 16:32
 **/
public class MockDeviceDao implements DeviceDao {


  public  int deleteByExample(Class<Device> elementType, Example example) {
    System.out.println("deleteByExample");
    return 0;
  }

  @Override
  public int deleteById(Class<Device> elementType, Long id) {
    System.out.println("deleteById");
    return deleteByExample(elementType, Example.create());
  }
}
