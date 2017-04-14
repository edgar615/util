package com.edgar.util.command;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 命令调度类的实现.
 *
 * @author Edgar
 * @version 1.0
 */
public class CommandBusImpl implements CommandBus {

  private static final Logger LOGGER = LoggerFactory
          .getLogger(CommandBusImpl.class);

  /**
   * Spring的上下文
   */
  private final Map<String, CommandHandler> map = new HashMap<>();

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public <T> CommandResult<T> executeCommand(Command command) {
    CommandHandler commandHandler;
    commandHandler = getCommandHandler(command);
//        LOGGER.debug("request command is {}", ToStringBuilder
//                .reflectionToString(command, ToStringStyle.SHORT_PREFIX_STYLE));
    return commandHandler.execute(command);
  }

  @Override
  public List<CommandResult> executeCommands(List<Command> commands) {
    LOGGER.debug("batch execute {} commands", commands.size());
    List<CommandResult> results = new ArrayList<CommandResult>(commands.size());
    for (Command command : commands) {
      results.add(executeCommand(command));
    }
    return results;
  }

  /**
   * 根据命令对象获取处理类
   *
   * @param command 命令对象
   * @return 命令处理类
   */
  @SuppressWarnings("rawtypes")
  private CommandHandler getCommandHandler(Command command) {
    Preconditions.checkNotNull(command, "command cannot be null");
    Preconditions.checkNotNull(!(command instanceof UnResolvedCommand),
                               "UnResolvedCommand donot has hander");
    String handlerId = command.getClass().getSimpleName() + "Handler";
//        handlerId = StringUtils.uncapitalize(handlerId);
    return map.get(command.getClass().getName());
  }

  @Override
  public void registerHandler(Class<? extends Command> clazz, CommandHandler handler) {
        map.put(clazz.getName(), handler);
  }

}
