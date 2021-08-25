package com.hkheiri.command.registry.impl;

import com.hkheiri.command.registry.CommandRoute;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapping of command method definition to its object.
 * 
 * @author Haseem Kheiri
 *
 */
public class CommandCall {

  private final Object object;
  private final Method method;

  public CommandCall(Object object, Method method) {
    this.object = object;
    this.method = method;
  }

  public String getRoute() {
    return method.getAnnotation(CommandRoute.class).value();
  }

  /**
   * Runs the command.
   * 
   * @param commandRequest with request parameters
   * @return result of the command run.
   * @throws Exception in case of JSON serialization error or invocation error. 
   */
  public Object run(CommandRequest commandRequest) throws Exception {
    final Parameter[] params = method.getParameters();
    final List<Object> list = new ArrayList<>();
    for (Parameter param : params) {
      CommandParam commandParam = param.getAnnotation(CommandParam.class);
      if (commandParam == null) {
        throw new IllegalArgumentException(
            "All command params should be annotated using @CommandParam.");
      } else {
        list.add(commandRequest.getParam(commandParam.value(), param.getType()));
      }
    }
    return method.invoke(object, list.toArray());
  }
}
