package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

/**
 * @author Haseem Kheiri
 *
 */
public class CommandRegistryTest {
  private CommandRegistry registry = init();

  private CommandRegistry init() {
    final CommandRegistry commandRegistry = new CommandRegistry();
    commandRegistry.setObjectMapper(new ObjectMapper());
    return commandRegistry;
  }

  @Test(expectedExceptions = IllegalArgumentException.class,
      expectedExceptionsMessageRegExp = "Command is null.")
  public void testRegisterNull() {
    registry.register(null);
  }


  @Test(expectedExceptions = IllegalArgumentException.class,
      expectedExceptionsMessageRegExp = "Not a registration bean.")
  public void testRegisterWithBlankCommandKey() {
    registry.register(new Object());
  }

  @Test
  public void testRegister() {
    TestCommandController command = new TestCommandController();
    registry.register(command);
  }

  @Test(dependsOnMethods = "testRegister", expectedExceptions = IllegalArgumentException.class)
  public void testRegisterDuplicate() {
    TestCommandController command = new TestCommandController();
    registry.register(command);
  }
}
