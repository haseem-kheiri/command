package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkheiri.command.registry.ICommandResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;

/**
 * Helper class for building a command request.
 * 
 * @author Haseem Kheiri
 *
 */
public class CommandCallBuilder {

  private final Map<String, String> params = new TreeMap<>();
  private final CommandCall command;
  private final ObjectMapper objectMapper;

  public <T> CommandCallBuilder(CommandCall command, ObjectMapper objectMapper) {
    this.command = command;
    this.objectMapper = objectMapper;
  }

  /**
   * Add a parameter to this call.
   * 
   * @param key is a required string.
   * @param value can be null.
   * @return this object.
   * @throws IOException
   * @throws IllegalArgumentException is key is blank.
   */
  public CommandCallBuilder addParam(String key, Object value) throws IOException {
    if (StringUtils.isBlank(key)) {
      throw new IllegalArgumentException("key is blank");
    }
    params.put(key, objectMapper.writeValueAsString(value));
    return this;
  }

  /**
   * Runs a command.
   * 
   * @return command response.
   * @throws IOException if response cannot be JSON serialized.
   */
  public ICommandResponse run() throws IOException {
    try {
      Object r = command.run(new CommandRequest(objectMapper, params));
      return new CommandResponse(objectMapper, objectMapper.writeValueAsString(r));
    } catch (JsonProcessingException e) {
      throw e;
    } catch (Exception e) {
      return new CommandResponse(objectMapper, e);
    }
  }
}
