package mc.alk.mc.entity;

public interface MCDamageable {

    void damage(double amount);
    void damage(double amount, MCEntity source);

    double getHealth();
    void setHealth(double health);
}
