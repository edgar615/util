package com.github.edgar615.util.db;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    SQLBindings sqlBindings = SqlBuilder.findById(Device.class, 1, fields);
    System.out.println(sqlBindings.sql());
    System.out.println(sqlBindings.bindings());
    Assert.assertEquals("select device_id,company_code from device where device_id = ?",
                        sqlBindings.sql());
    Assert.assertEquals(1, sqlBindings.bindings().get(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFindByIdWithUndefiendFields() {
    List<String> fields = new ArrayList<>();
    fields.add("device_id");
    fields.add("companyCode");
    SqlBuilder.findById(Device.class, 1, fields);
    Assert.fail();
  }
}
