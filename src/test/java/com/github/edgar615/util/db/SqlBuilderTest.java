package com.github.edgar615.util.db;

import com.github.edgar615.util.base.Randoms;
import com.github.edgar615.util.base.StringUtils;
import com.github.edgar615.util.search.Example;
import com.github.edgar615.util.search.Select;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edgar on 2017/5/18.
 *
 * @author Edgar  Date 2017/5/18
 */
public class SqlBuilderTest {

  @Test
  public void testInsert() {

    Device device = new Device();
    device.setBarcode("barcode");
    device.setCompanyCode(0);
    SQLBindings sqlBindings = SqlBuilder.insert(device);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("insert into device(barcode,company_code) values(?,?)", sqlBindings.sql());
    Assert.assertEquals("barcode", sqlBindings.bindings().get(0));
    Assert.assertEquals(0, sqlBindings.bindings().get(1));
  }

  @Test
  public void testDeleteById() {
    SQLBindings sqlBindings = SqlBuilder.deleteById(Device.class, 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("delete from device where device_id = ?", sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testUpdateById() {
    Device device = new Device();
    device.setBarcode("barcode");
    device.setCompanyCode(0);
    SQLBindings sqlBindings = SqlBuilder.updateById(device, null, null, 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("update device set barcode = ?,company_code = ? where device_id = ?",
        sqlBindings.sql());
    Assert.assertEquals("barcode", sqlBindings.bindings().get(0));
    Assert.assertEquals(0, sqlBindings.bindings().get(1));
    Assert.assertEquals(1, sqlBindings.bindings().get(2));
  }

  @Test
  public void testUpdateById2() {
    Device device = new Device();
    device.setBarcode("barcode");
    device.setCompanyCode(0);
    Map<String, Integer> addOrSub = ImmutableMap.of("parentId", 1, "deviceCode", -2);
    List<String> fiedls = Lists.newArrayList("manufacturerName");
    SQLBindings sqlBindings = SqlBuilder.updateById(device, addOrSub, fiedls, 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals(
        "update device set barcode = ?,company_code = ?,parent_id = parent_id + 1,device_code = device_code - 2,manufacturer_name = null where device_id = ?",
        sqlBindings.sql());
    Assert.assertEquals("barcode", sqlBindings.bindings().get(0));
    Assert.assertEquals(0, sqlBindings.bindings().get(1));
    Assert.assertEquals(1, sqlBindings.bindings().get(2));
  }

  @Test
  public void testUpdateEmptyShouldFailed() {
    Device device = new Device();
    SQLBindings sqlBindings = SqlBuilder.updateById(device, null, null, 1);
    Assert.assertNull(sqlBindings);
  }

  @Test
  public void testFindById() {
    SQLBindings sqlBindings = SqlBuilder.findById(Device.class, 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select " + allColumn() + " from device where device_id = ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testFindByIdWithFields() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    SQLBindings sqlBindings = SqlBuilder.findById(Device.class, 1, fields);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select device_id, company_code from device where device_id = ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testSetNull() {
    SQLBindings sqlBindings = SqlBuilder.updateById(new Device(), Maps.newHashMap(),
        Lists.newArrayList("abc", "userId", "parentId"), 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("update device set user_id = null,parent_id = null where device_id = ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testFindByExample() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Example example = Example.create().in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF")
        .desc("userId");
    SQLBindings sqlBindings = SqlBuilder.findByExample(Device.class, example);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select " + allColumn()
            + " from device where type in (?,?,?) and mac_address like ? order by user_id desc",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testFindByExampleWithField() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Example example = Example.create().in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF");
    example.addFields(fields);
    SQLBindings sqlBindings = SqlBuilder.findByExample(Device.class, example);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals(
        "select device_id, company_code from device where type in (?,?,?) and mac_address like ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testFindByExampleWithErrorField() {
    List<String> fields = new ArrayList<>();
    fields.add(Randoms.randomAlphabet(20));
    Example example = Example.create().in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF");
    example.addFields(fields);
    SQLBindings sqlBindings = SqlBuilder.findByExample(Device.class, example);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals(
        "select " + allColumn() + " from device where type in (?,?,?) and mac_address like ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testFindByExampleLimit() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Example example = Example.create().in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF");
    SQLBindings sqlBindings = SqlBuilder.findByExample(Device.class, example, 5, 10);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select " + allColumn()
            + " from device where type in (?,?,?) and mac_address like ? limit ?, ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testCountByExample() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Example example = Example.create().in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF")
        .asc("userId");
    SQLBindings sqlBindings = SqlBuilder.countByExample(Device.class, example);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select count(*) from device where type in (?,?,?) and mac_address like ?",
        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testSelect() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Select<Integer, Device> select = Select.and(Device.class)
        .in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF")
        .inner(
            Select.or(Device.class).equalsTo("companyCode", 0).equalsTo("companyCode", "999"))
        .desc("userId");
    SQLBindings sqlBindings = SqlBuilder.select(select);
    Assert.assertEquals(
        "select " + allColumn()
            + " from device where type in (?,?,?) and mac_address like ? and (company_code = ? or company_code = ?) order by user_id desc",
        sqlBindings.sql());
    Assert.assertEquals(6, sqlBindings.bindings().size());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testSelectCount() {
    List<String> fields = new ArrayList<>();
    fields.add("deviceId");
    fields.add("companyCode");
    fields.add(Randoms.randomAlphabet(20));
    Select<Integer, Device> select = Select.and(Device.class)
        .in("type", Lists.newArrayList(1, 2, 3))
        .startsWith("macAddress", "FFFF")
        .inner(
            Select.or(Device.class).equalsTo("companyCode", 0).equalsTo("companyCode", "999"))
        .desc("userId");
    SQLBindings sqlBindings = SqlBuilder.countBySelect(select);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals(
        "select count(*) from device where type in (?,?,?) and mac_address like ? and (company_code = ? or company_code = ?)",
        sqlBindings.sql());
    Assert.assertEquals(6, sqlBindings.bindings().size());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  private String allColumn() {
    Device device = new Device();
    List<String> fields = device.fields()
        .stream().map(f -> StringUtils.underscoreName(f))
        .collect(Collectors.toList());
    return Joiner.on(", ").join(fields);
  }
}
