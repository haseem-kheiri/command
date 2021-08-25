package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkheiri.command.registry.CommandController;
import com.hkheiri.command.registry.CommandRoute;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

/**
 * Registry for commands.
 * 
 * @author Haseem Kheiri
 *
 */
public class CommandRegistry {
  private final Map<String, CommandCall> commands = new TreeMap<>();
  private ObjectMapper objectMapper;

  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Registers a command.
   * 
   * @param o
   * 
   * @throws IllegalArgumentException is command is null, command's name is blank, or the command
   *         keys is a duplicate.
   */
  public <T> void register(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("Command is null.");
    }

    CommandController reg = o.getClass().getAnnotation(CommandController.class);
    if (reg == null) {
      throw new IllegalArgumentException("Not a registration bean.");
    }

    Arrays.asList(o.getClass().getMethods()).stream().filter(m -> {
      return m.getAnnotation(CommandRoute.class) != null;
    }).map(m -> {
      return new CommandCall(o, m);
    }).forEach(cc -> {
      String route = cc.getRoute();
      if (route == null) {
        throw new IllegalArgumentException("Command route is blank.");
      }

      if (commands.containsKey(route)) {
        throw new IllegalArgumentException(
            String.format("Command with route '%s' already registered.", route));
      }

      commands.put(route, cc);
    });
  }

  /**
   * Get's the command associated with the supplied key.
   * 
   * @param route of a command.
   * @return a command.
   * @throws IllegalArgumentException if key is blank or command is not found for the key.
   */
  public CommandCallBuilder get(String route) {
    if (StringUtils.isBlank(route)) {
      throw new IllegalArgumentException("Command key is blank.");
    }

    final CommandCall command = commands.get(route.toLowerCase());
    if (command == null) {
      throw new IllegalArgumentException(String.format("No command found for route '%s'.", route));
    }

    return new CommandCallBuilder(command, objectMapper);
  }
}
