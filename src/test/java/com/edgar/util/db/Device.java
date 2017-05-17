package com.edgar.util.db;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
* This class is generated by Jdbc code generator.
*
* Table : device
* remarks: 设备表
*
* @author Jdbc Code Generator Date 
*/
public class Device implements Persistable<Integer>  {

    private static final long serialVersionUID = 1L;
    
    /**
    * Column : device_id
    * remarks: 
    * default: 
    * isNullable: false
    * isAutoInc: true
    * isPrimary: true
    * type: 4
    * size: 10
    */
    private Integer deviceId;
    
    /**
    * Column : user_id
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer userId;
    
    /**
    * Column : parent_id
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer parentId;
    
    /**
    * Column : username
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 60
    */
    private String username;
    
    /**
    * Column : is_virtual
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: -6
    * size: 3
    */
    private Integer isVirtual;
    
    /**
    * Column : company_code
    * remarks: 
    * default: 
    * isNullable: false
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer companyCode;
    
    /**
    * Column : name
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 64
    */
    private String name;
    
    /**
    * Column : barcode
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 19
    */
    private String barcode;
    
    /**
    * Column : mac_address
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 12
    */
    private String macAddress;
    
    /**
    * Column : encrypt_key
    * remarks: 
    * default: 
    * isNullable: false
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 16
    */
    private String encryptKey;
    
    /**
    * Column : type
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer type;
    
    /**
    * Column : state
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer state;
    
    /**
    * Column : location
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 16
    */
    private String location;
    
    /**
    * Column : device_code
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer deviceCode;
    
    /**
    * Column : manufacturer_code
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer manufacturerCode;
    
    /**
    * Column : manufacturer_name
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 64
    */
    private String manufacturerName;
    
    /**
    * Column : description
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 1024
    */
    private String description;
    
    /**
    * Column : product_version
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 32
    */
    private String productVersion;
    
    /**
    * Column : zigbee_version
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 32
    */
    private String zigbeeVersion;
    
    /**
    * Column : zigbee_mac_address
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 16
    */
    private String zigbeeMacAddress;
    
    /**
    * Column : main_feature
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 2
    */
    private String mainFeature;
    
    /**
    * Column : wifi_firm
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer wifiFirm;
    
    /**
    * Column : wifi_version
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 32
    */
    private String wifiVersion;
    
    /**
    * Column : server_address
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 12
    * size: 128
    */
    private String serverAddress;
    
    /**
    * Column : public_ip
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 1
    * size: 32
    */
    private String publicIp;
    
    /**
    * Column : is_online
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: -6
    * size: 3
    */
    private Integer isOnline;
    
    /**
    * Column : add_on
    * remarks: 
    * default: 
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 4
    * size: 10
    */
    private Integer addOn;
    
    /**
    * Column : created_on
    * remarks: 
    * default: CURRENT_TIMESTAMP
    * isNullable: true
    * isAutoInc: false
    * isPrimary: false
    * type: 93
    * size: 19
    */
    private Date createdOn;
    
    public Integer getDeviceId() {
        return deviceId;
    }

    public Integer setDeviceId(Integer deviceId) {
        return this.deviceId = deviceId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public Integer setUserId(Integer userId) {
        return this.userId = userId;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    public Integer setParentId(Integer parentId) {
        return this.parentId = parentId;
    }
    
    public String getUsername() {
        return username;
    }

    public String setUsername(String username) {
        return this.username = username;
    }
    
    public Integer getIsVirtual() {
        return isVirtual;
    }

    public Integer setIsVirtual(Integer isVirtual) {
        return this.isVirtual = isVirtual;
    }
    
    public Integer getCompanyCode() {
        return companyCode;
    }

    public Integer setCompanyCode(Integer companyCode) {
        return this.companyCode = companyCode;
    }
    
    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }
    
    public String getBarcode() {
        return barcode;
    }

    public String setBarcode(String barcode) {
        return this.barcode = barcode;
    }
    
    public String getMacAddress() {
        return macAddress;
    }

    public String setMacAddress(String macAddress) {
        return this.macAddress = macAddress;
    }
    
    public String getEncryptKey() {
        return encryptKey;
    }

    public String setEncryptKey(String encryptKey) {
        return this.encryptKey = encryptKey;
    }
    
    public Integer getType() {
        return type;
    }

    public Integer setType(Integer type) {
        return this.type = type;
    }
    
    public Integer getState() {
        return state;
    }

    public Integer setState(Integer state) {
        return this.state = state;
    }
    
    public String getLocation() {
        return location;
    }

    public String setLocation(String location) {
        return this.location = location;
    }
    
    public Integer getDeviceCode() {
        return deviceCode;
    }

    public Integer setDeviceCode(Integer deviceCode) {
        return this.deviceCode = deviceCode;
    }
    
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    public Integer setManufacturerCode(Integer manufacturerCode) {
        return this.manufacturerCode = manufacturerCode;
    }
    
    public String getManufacturerName() {
        return manufacturerName;
    }

    public String setManufacturerName(String manufacturerName) {
        return this.manufacturerName = manufacturerName;
    }
    
    public String getDescription() {
        return description;
    }

    public String setDescription(String description) {
        return this.description = description;
    }
    
    public String getProductVersion() {
        return productVersion;
    }

    public String setProductVersion(String productVersion) {
        return this.productVersion = productVersion;
    }
    
    public String getZigbeeVersion() {
        return zigbeeVersion;
    }

    public String setZigbeeVersion(String zigbeeVersion) {
        return this.zigbeeVersion = zigbeeVersion;
    }
    
    public String getZigbeeMacAddress() {
        return zigbeeMacAddress;
    }

    public String setZigbeeMacAddress(String zigbeeMacAddress) {
        return this.zigbeeMacAddress = zigbeeMacAddress;
    }
    
    public String getMainFeature() {
        return mainFeature;
    }

    public String setMainFeature(String mainFeature) {
        return this.mainFeature = mainFeature;
    }
    
    public Integer getWifiFirm() {
        return wifiFirm;
    }

    public Integer setWifiFirm(Integer wifiFirm) {
        return this.wifiFirm = wifiFirm;
    }
    
    public String getWifiVersion() {
        return wifiVersion;
    }

    public String setWifiVersion(String wifiVersion) {
        return this.wifiVersion = wifiVersion;
    }
    
    public String getServerAddress() {
        return serverAddress;
    }

    public String setServerAddress(String serverAddress) {
        return this.serverAddress = serverAddress;
    }
    
    public String getPublicIp() {
        return publicIp;
    }

    public String setPublicIp(String publicIp) {
        return this.publicIp = publicIp;
    }
    
    public Integer getIsOnline() {
        return isOnline;
    }

    public Integer setIsOnline(Integer isOnline) {
        return this.isOnline = isOnline;
    }
    
    public Integer getAddOn() {
        return addOn;
    }

    public Integer setAddOn(Integer addOn) {
        return this.addOn = addOn;
    }
    
    public Date getCreatedOn() {
        return createdOn;
    }

    public Date setCreatedOn(Date createdOn) {
        return this.createdOn = createdOn;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper("Company")
            .add("deviceId",  deviceId)
            .add("userId",  userId)
            .add("parentId",  parentId)
            .add("username",  username)
            .add("isVirtual",  isVirtual)
            .add("companyCode",  companyCode)
            .add("name",  name)
            .add("barcode",  barcode)
            .add("macAddress",  macAddress)
            .add("encryptKey",  encryptKey)
            .add("type",  type)
            .add("state",  state)
            .add("location",  location)
            .add("deviceCode",  deviceCode)
            .add("manufacturerCode",  manufacturerCode)
            .add("manufacturerName",  manufacturerName)
            .add("description",  description)
            .add("productVersion",  productVersion)
            .add("zigbeeVersion",  zigbeeVersion)
            .add("zigbeeMacAddress",  zigbeeMacAddress)
            .add("mainFeature",  mainFeature)
            .add("wifiFirm",  wifiFirm)
            .add("wifiVersion",  wifiVersion)
            .add("serverAddress",  serverAddress)
            .add("publicIp",  publicIp)
            .add("isOnline",  isOnline)
            .add("addOn",  addOn)
            .add("createdOn",  createdOn)
           .toString();
    }

    @Override
    public List<String> fields() {
      return Lists.newArrayList("deviceId",
						"userId",
						"parentId",
						"username",
						"isVirtual",
						"companyCode",
						"name",
						"barcode",
						"macAddress",
						"encryptKey",
						"type",
						"state",
						"location",
						"deviceCode",
						"manufacturerCode",
						"manufacturerName",
						"description",
						"productVersion",
						"zigbeeVersion",
						"zigbeeMacAddress",
						"mainFeature",
						"wifiFirm",
						"wifiVersion",
						"serverAddress",
						"publicIp",
						"isOnline",
						"addOn",
						"createdOn");
    }

    @Override
    public String primaryKey() {
        return "deviceId";
    }

    @Override
    public Integer id () {
    return deviceId;
    }

    @Override
    public void setId(Integer id) {
        this.deviceId = id;
    }

   /* START Do not remove/edit this line. CodeGenerator will preserve any code between start and end tags.*/
	/* END Do not remove/edit this line. CodeGenerator will preserve any code between start and end tags.*/

    }