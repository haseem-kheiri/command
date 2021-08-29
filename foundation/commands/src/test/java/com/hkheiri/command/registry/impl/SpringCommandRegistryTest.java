package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hkheiri.command.registry.ICommandResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Haseem Kheiri
 *
 */
@SpringBootTest(classes = TestConfig.class)
public class SpringCommandRegistryTest extends AbstractTestNGSpringContextTests {
  @Autowired
  private CommandRegistry registry;

  @Test
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
