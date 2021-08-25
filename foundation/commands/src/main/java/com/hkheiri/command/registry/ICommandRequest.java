package com.hkheiri.command.registry;

import java.io.IOException;

/**
 * Contract for a command call request.
 * @author Haseem Kheiri
 *
 */
public interface ICommandRequest {
  Object getParam(String name, Class<?> type) throws IOException;
}
