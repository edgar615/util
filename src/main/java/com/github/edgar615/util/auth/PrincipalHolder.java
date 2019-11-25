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

import com.github.edgar615.util.exception.DefaultErrorCode;
import com.github.edgar615.util.exception.SystemException;

public class PrincipalHolder {

  private static final ThreadLocal<Principal> PRINCIPAL_HOLDER = new ThreadLocal<>();

  private PrincipalHolder() {
    throw new AssertionError("Not instantiable: " + PrincipalHolder.class);
  }

  public static void set(Principal principal) {
    PRINCIPAL_HOLDER.set(principal);
  }

  public static Principal get() {
    return PRINCIPAL_HOLDER.get();
  }

  public static Principal getAndCheck() {
    Principal principal = PRINCIPAL_HOLDER.get();
    if (principal == null) {
      throw SystemException.create(DefaultErrorCode.UNKOWN_ACCOUNT);
    }
    return principal;
  }

  public static void clear() {
    PRINCIPAL_HOLDER.remove();
  }

}
