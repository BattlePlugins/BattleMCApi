package mc.alk.mc.entity.player;

public enum MCGameMode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private int id;

    MCGameMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
