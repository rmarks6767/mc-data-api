package org.csh.models;

public class PlayerData {

    private String username;
    private double health;
    private long armor;
    private long hunger;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public long getArmor() { return armor; }
    public void setArmor(long armor) { this.armor = armor; }

    public double getHealth() { return health; }
    public void setHealth(double health) { this.health = health; }

    public long getHunger() { return hunger; }
    public void setHunger(long hunger) { this.hunger = hunger; }

    @Override
    public String toString() {
        return "\nName: " + username + "\nHealth: " + health + "\nArmor: " + armor + "\nHunger: " + hunger;
    }

}
