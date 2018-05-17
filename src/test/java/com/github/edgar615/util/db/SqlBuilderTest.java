package com.github.edgar615.util.db;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import com.github.edgar615.util.base.Randoms;
import com.github.edgar615.util.search.Example;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    SQLBindings sqlBindings = SqlBuilder.updateById(device, 1);
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
    Assert.assertEquals("update device set barcode = ?,company_code = ?,parent_id = parent_id + 1,device_code = device_code - 2,manufacturer_name = null where device_id = ?",
                        sqlBindings.sql());
    Assert.assertEquals("barcode", sqlBindings.bindings().get(0));
    Assert.assertEquals(0, sqlBindings.bindings().get(1));
    Assert.assertEquals(1, sqlBindings.bindings().get(2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateEmptyShouldFailed() {
    Device device = new Device();
    SqlBuilder.updateById(device, 1);
    Assert.fail();
  }

  @Test
  public void testFindById() {
    SQLBindings sqlBindings = SqlBuilder.findById(Device.class, 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select * from device where device_id = ?", sqlBindings.sql());
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
    Assert.assertEquals("select device_id,company_code from device where device_id = ?",
                        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test
  public void testIgnoreNullValue() {
    Example example = Example.create()
            .equalsTo("foo", "bar")
            .equalsTo("companyCode", null)
            .notEqualsTo("companyCode", null)
            .lessThan("companyCode", null)
            .lessThanOrEqualTo("companyCode", null)
            .greaterThan("companyCode", null)
            .greaterThanOrEqualTo("companyCode", null)
            .between("companyCode", null, null)
            .between("companyCode", null, 1)
            .between("companyCode", 1, null)
            .startsWith("companyCode", null)
            .endsWtih("companyCode", null)
            .contains("companyCode", null)
            .in("companyCode", null)
            .notIn("companyCode", null)
            .in("companyCode", Lists.newArrayList())
            .notIn("companyCode", Lists.newArrayList());
    System.out.println(SqlBuilder.whereSql(example.criteria()).sql());
    System.out.println(SqlBuilder.whereSql(example.criteria()).bindings());
  }

  @Test
  public void testSetNull() {
    SQLBindings sqlBindings = SqlBuilder.setNullById(Device.class, Lists.newArrayList("abc",
                                                                                      "userId",
                                                                                      "parentId")
            , 1);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("update device set user_id = null,parent_id = null where device_id = ?",
                        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }
}
