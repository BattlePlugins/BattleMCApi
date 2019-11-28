package org.battleplugins.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginProperties {

    String id();
    String name();
    String version() default "1.0";
    String description() default "";
    String url() default "";
    String[] authors() default {""};
}
