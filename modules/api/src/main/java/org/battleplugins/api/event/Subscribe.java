package org.battleplugins.api.event;

import lombok.AllArgsConstructor;
import net.kyori.event.PostOrders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation used to signify the given method is
 * an {@link Event}. Only should be applied to methods
 * where the class containing them is designated for
 * events specifically.
 *
 * When using {@link EventBus#subscribe}, this annotation should
 * not be applied whatsoever as it will have no use and potentially
 * throw errors due to it being used wrongly.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

    /**
     * The {@link Priority} of the event
     *
     * @return the priority of the event
     */
    Priority priority() default Priority.NORMAL;

    /**
     * If the event should be automatically identifiable
     * by the event annotation processor. Requires that the
     * class containing the event has an empty default
     * constructor.
     *
     * Should you decide not to use an empty constructor,
     * an instance of this class needs to be provided with
     * {@link EventBus#provideInstance(Class, Object)} or
     * the event will fail to be called.
     *
     * @return if the event is automatically identifiable
     */
    boolean autoIdentifiable() default true;

    /**
     * Represents the priority of an event.
     */
    @AllArgsConstructor
    enum Priority {

        /**
         * The lowest priority. Called first to
         * allow for other events to customize
         * the outcome
         */
        LOWEST(PostOrders.FIRST),

        /**
         * The second lowest priority.
         */
        LOW(PostOrders.EARLY),

        /**
         * Normal priority. Event is neither
         * important nor unimportant
         */
        NORMAL(PostOrders.NORMAL),

        /**
         * The second highest priority
         */
        HIGH(PostOrders.LATE),

        /**
         * The highest of importance! Event is called
         * last and has the final say in the outcome
         */
        HIGHEST(PostOrders.LAST);

        int postOrder;
    }
}
