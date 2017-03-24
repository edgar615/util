package com.edgar.util.command;

import java.util.List;

/**
 * 命令调度类的接口.
 *
 * @author Edgar
 * @version 1.0
 */
public interface CommandBus {

    /**
     * 调度命令.
     *
     * @param command 命令
     * @param <T>     返回的结果类型
     * @return {@link CommandResult}
     */
    <T> CommandResult<T> executeCommand(Command command);

    /**
     * 调度一组命令.
     *
     * @param commands 命令的集合
     * @return {@link CommandResult}
     */
    List<CommandResult> executeCommands(List<Command> commands);

}
