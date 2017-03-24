package com.edgar.util.command;

public class GetSysUserHandler implements CommandHandler<GetSysUser> {

  @Override
  public CommandResult<String> execute(GetSysUser command) {
    System.out.println(command.getClass());
    return CommandResult.newInstance(null);
  }

}