package com.edgar.util.command;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandTest {

  private CommandBus commandBus = CommandBus.create();

  @Test
  public void test() {
    commandBus.registerHandler(GetSysUser.class, new GetSysUserHandler());
    Command command = new GetSysUser(1);
    CommandResult<String> result = commandBus.executeCommand(command);
    Assert.assertEquals("1", result.getResult());
  }

  @Test
  public void testChain() {
    commandBus.registerHandler(ChainGetSysUser.class, new ChainGetSysUserHandler());
    Command command = new ChainGetSysUser(1);
    CommandResult<String> result = commandBus.executeCommand(command);
    Assert.assertNotNull(result.getResult());
  }

  @Test
  public void testBatch() {
    commandBus.registerHandler(GetSysUser.class, new GetSysUserHandler());
    commandBus.registerHandler(ChainGetSysUser.class, new ChainGetSysUserHandler());
    List<Command> commands = new ArrayList<Command>();
    commands.add(new GetSysUser(1));
    commands.add(new ChainGetSysUser(1));
    List<CommandResult> result = commandBus.executeCommands(commands);
    Assert.assertNotNull(result.get(0).getResult());
  }

  @Test(expected = RuntimeException.class)
  public void testError() {
    Command command = new DeleteSysUser(1);
    commandBus.executeCommand(command);
  }

}
