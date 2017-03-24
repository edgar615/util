package com.edgar.util.command;

public class ChainGetSysUserHandler implements CommandHandler<ChainGetSysUser> {

    @Override
    public CommandResult<String> execute(ChainGetSysUser command) {
        System.out.println(command.getClass());
        return CommandResult.newInstance("hello");
    }

}
