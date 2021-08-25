package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkheiri.command.registry.ICommandRequest;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * Command call request.
 * 
 * @author Haseem Kheiri
 *
 */
public class CommandRequest implements ICommandRequest {
  private final ObjectMapper objectMapper;
  private final Map<String, String> params;

  public CommandRequest(ObjectMapper objectMapper, Map<String, String> params) {
    this.objectMapper = objectMapper;
    this.params = params;
  }

  @Override
  public Object getParam(String name, Class<?> type) throws IOException {
    if (StringUtils.isBlank(name)) {
      throw new IllegalArgumentException("Parameter name is blank.");
    }
    return objectMapper.readValue(params.get(name), type);
  }
}
