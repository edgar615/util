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

package com.github.edgar615.util.reflect;

import com.google.common.base.MoreObjects;
import java.util.Date;

/**
 * This class is generated by DeviceDao code generator.
 * <p>
 * Table : device remarks: 设备表
 *
 * @author DeviceDao Code Generator Date
 */
public class Device {

  private static final long serialVersionUID = 1L;

  /**
   * Column : device_id remarks: default: isNullable: false isAutoInc: true isPrimary: true type: 4
   * size: 10
   */
  private Integer deviceId;

  /**
   * Column : user_id remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 4
   * size: 10
   */
  private Integer userId;

  /**
   * Column : parent_id remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 4
   * size: 10
   */
  private Integer parentId;

  /**
   * Column : username remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 12
   * size: 60
   */
  private String username;

  /**
   * Column : is_virtual remarks: default: isNullable: true isAutoInc: false isPrimary: false type:
   * -6 size: 3
   */
  private Integer isVirtual;

  /**
   * Column : company_code remarks: default: isNullable: false isAutoInc: false isPrimary: false
   * type: 4 size: 10
   */
  private Integer companyCode;

  /**
   * Column : name remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 12
   * size: 64
   */
  private String name;

  /**
   * Column : barcode remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 1
   * size: 19
   */
  private String barcode;

  /**
   * Column : mac_address remarks: default: isNullable: true isAutoInc: false isPrimary: false type:
   * 1 size: 12
   */
  private String macAddress;

  /**
   * Column : encrypt_key remarks: default: isNullable: false isAutoInc: false isPrimary: false
   * type: 1 size: 16
   */
  private String encryptKey;

  /**
   * Column : type remarks: default: isNullable: true isAutoInc: false isPrimary: false type: 4
   * size: 10
   */
  private Integer type;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getIsVirtual() {
    return isVirtual;
  }

  public void setIsVirtual(Integer isVirtual) {
    this.isVirtual = isVirtual;
  }

  public Integer getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(Integer companyCode) {
    this.companyCode = companyCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public String getEncryptKey() {
    return encryptKey;
  }

  public void setEncryptKey(String encryptKey) {
    this.encryptKey = encryptKey;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
