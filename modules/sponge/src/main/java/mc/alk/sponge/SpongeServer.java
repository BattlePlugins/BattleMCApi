package mc.alk.sponge;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCServer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.plugin.MCPlugin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SpongeServer extends MCServer {

    @Override
    public APIType getAPIType() {
        return APIType.SPONGE;
    }

    @Override
    public MCLocation getMCLocation(String world, int x, int y, int z) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }

    @Override
    public MCWorld getMCWorld(String world) {
        return new SpongeWorld(Sponge.getServer().getWorld(world).get());
    }

    @Override
    public long scheduleSyncTask(MCPlugin plugin, Runnable runnable, long millis) {
        Sponge.getScheduler().createTaskBuilder().interval(millis, TimeUnit.MILLISECONDS).execute(runnable).submit(plugin);
        return 0; // No support for sponge task ids
    }

    @Override
    public boolean cancelMCTask(long id) {
        return false; // No support for sponge task ids
    }

    @Override
    public MCPlayer getMCPlayer(String name) {
        Optional<Player> player = Sponge.getServer().getPlayer(name);
        return new SpongePlayer(player.get());
    }
}
