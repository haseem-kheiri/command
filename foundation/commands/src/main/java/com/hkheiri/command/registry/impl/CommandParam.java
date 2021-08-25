package com.hkheiri.command.registry.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a parameter passed to a command.
 * 
 * @author Haseem Kheiri
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CommandParam {

  /**
   * Name of a command parameter.
   * 
   * @return name
   */
  String value();
}
