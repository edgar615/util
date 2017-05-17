package com.edgar.util.db;

import com.edgar.jdbc.codegen.gen.CodegenOptions;
import com.edgar.jdbc.codegen.gen.Generator;

public class DomainGenerateTest {

  public static void main(String[] args) throws Exception {
    CodegenOptions options = new CodegenOptions().setUsername("admin")
            .setPassword("csst")
            .setIgnoreTablesStr("*io,his*")
            .setIgnoreColumnsStr("created*,updated_on")
            .setJdbcUrl(
                    "jdbc:mysql://test.ihorn.com.cn:3306/device")
            .setTableNamePattern("device")
            .setSrcFolderPath("src/test/java")
            .setIgnoreColumnsStr("photo_path,degree");

    new Generator(options).generate();
  }

}