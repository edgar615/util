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

package com.github.edgar615.util.exception;

import org.junit.Test;

/**
 * Created by Administrator on 2015/11/18.
 */
public class SystemExceptionTest {

  @Test
  public void test() {
    try {
      throw SystemException.create(DefaultErrorCode.INVALID_TOKEN)
          .set("foo", "bar");
    } catch (Exception e) {
      System.out.println(e.toString());
      e.printStackTrace();
    }
  }

  @Test(expected = SystemException.class)
  public void testEx() {
    throwEx();
  }

  @Test(expected = SystemException.class)
  public void testExWithProperties() {
    throwExWithProperties();
  }

  @Test(expected = SystemException.class)
  public void testExWithThrowable() {
    throwExWithThrowable();
  }

  @Test(expected = SystemException.class)
  public void testExWithSystemEx() {
    throwExWithSystemEx();
  }

  @Test(expected = SystemException.class)
  public void testExWithSystemEx2() {
    throwExWithSystemEx2();
  }

  public void throwEx() {
    throw SystemException.create(DefaultErrorCode.NULL);
  }

  public void throwExWithProperties() {
    throw SystemException.create(DefaultErrorCode.NULL).set("foo", "bar");
  }

  public void throwExWithThrowable() {
    throw SystemException.wrap(DefaultErrorCode.NULL, new RuntimeException("no record")).set("foo",
        "bar");
  }

  public void throwExWithSystemEx() {
    throw SystemException
        .wrap(DefaultErrorCode.NULL, SystemException.create(DefaultErrorCode.INVALID_TOKEN))
        .set("foo",
            "bar");
  }

  public void throwExWithSystemEx2() {
    throw SystemException.wrap(DefaultErrorCode.NULL, SystemException.create(DefaultErrorCode.NULL))
        .set("foo",
            "bar");
  }
}
