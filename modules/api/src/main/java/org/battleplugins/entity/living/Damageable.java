package org.battleplugins.entity.living;

import org.battleplugins.entity.Entity;

public interface Damageable {

    void damage(double amount);
    void damage(double amount, Entity source);

    double getHealth();
    void setHealth(double health);
}
