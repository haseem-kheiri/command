package com.hkheiri.command.registry.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkheiri.command.registry.ICommandResponse;
import java.io.IOException;

/**
 * Response of a command call.
 * 
 * @author Haseem Kheiri
 *
 */
public class CommandResponse implements ICommandResponse {
  private final ObjectMapper objectMapper;
  private final String responseString;
  private final String errorMessage;
  private final boolean successful;

  public boolean isSuccessful() {
    return successful;
  }
  
  public String getErrorMessage() {
    return errorMessage;
  }
  
  /**
   * Constructs a command response.
   * 
   * @param objectMapper for serdes
   * @param responseString result of command execution.
   */
  public CommandResponse(ObjectMapper objectMapper, String responseString) {
    this.objectMapper = objectMapper;
    this.responseString = responseString;
    this.errorMessage = null;
    this.successful = true;
  }

  /**
   * Constructs a command response.
   * 
   * @param objectMapper for serdes
   * @param e exception throw by the command.
   */
  public CommandResponse(ObjectMapper objectMapper, Exception e) {
    this.objectMapper = objectMapper;
    this.responseString = null;
    this.errorMessage = e.getLocalizedMessage();
    this.successful = false;
  }

  @Override
  public <T> T get(TypeReference<T> typeReference) throws IOException {
    return objectMapper.readValue(responseString, typeReference);
  }
}
