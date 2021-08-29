package com.hkheiri.command.registry.impl;

import com.hkheiri.command.registry.CommandController;
import com.hkheiri.command.registry.CommandRoute;

/**
 * @author Haseem Kheiri
 *
 */
@CommandController
class TestCommandController {
  @CommandRoute("hkheiri.com/test1")
  public String test(@CommandParam("name") String name) {
    return String.format("Hello %s", name);
  }

  @CommandRoute("hkheiri.com/test2")
  public int test2(@CommandParam("a") int a, @CommandParam("b") int b) {
    return a + b;
  }
}