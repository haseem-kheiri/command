package com.hkheiri.command.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated a command route.
 * 
 * @author Haseem Kheiri
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandRoute {
  /**
   * Unique route for a command.
   * 
   * @return route
   */
  String value();
}
