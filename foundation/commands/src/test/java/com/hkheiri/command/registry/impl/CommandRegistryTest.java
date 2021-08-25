package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkheiri.command.registry.CommandController;
import com.hkheiri.command.registry.CommandRoute;
import com.hkheiri.command.registry.ICommandResponse;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Haseem Kheiri
 *
 */
public class CommandRegistryTest {
  private final CommandRegistry registry = initRegistry();

  @Test(expectedExceptions = IllegalArgumentException.class,
      expectedExceptionsMessageRegExp = "Command is null.")
  public void testRegisterNull() {
    registry.register(null);
  }

  private CommandRegistry initRegistry() {
    final CommandRegistry commandRegistry = new CommandRegistry();
    commandRegistry.setObjectMapper(new ObjectMapper());
    return commandRegistry;
  }

  @Test(expectedExceptions = IllegalArgumentException.class,
      expectedExceptionsMessageRegExp = "Not a registration bean.")
  public void testRegisterWithBlankCommandKey() {
    registry.register(new Object());
  }

  @Test
  public void testRegister() {
    TestCommand1 command = new TestCommand1();
    registry.register(command);
  }

  @Test(dependsOnMethods = "testRegister", expectedExceptions = IllegalArgumentException.class)
  public void testRegisterDuplicate() {
    TestCommand1 command = new TestCommand1();
    registry.register(command);
  }


  @Test(dependsOnMethods = "testRegister")
  public void testGet() throws IOException {
    ICommandResponse response = registry.get("hkheiri.com/test1").addParam("name", "Haseem").run();

    Assert.assertNotNull(response);
    String actual = response.get(new TypeReference<String>() {});
    Assert.assertEquals(actual, "Hello Haseem");

    response = registry.get("hkheiri.com/test2").addParam("a", 1).addParam("b", 1).run();
    Integer n = response.get(new TypeReference<Integer>() {});
    Assert.assertEquals(n.intValue(), 2);
  }
}


@CommandController
class TestCommand1 {
  @CommandRoute("hkheiri.com/test1")
  public String test(@CommandParam("name") String name) {
    return String.format("Hello %s", name);
  }

  @CommandRoute("hkheiri.com/test2")
  public int test2(@CommandParam("a") int a, @CommandParam("b") int b) {
    return a + b;
  }
}
