package com.hkheiri.command.registry;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;

/**
 * Response of a command.
 * 
 * @author Haseem Kheiri
 *
 */
public interface ICommandResponse {

  <T> T get(TypeReference<T> typeReference) throws IOException;

}
