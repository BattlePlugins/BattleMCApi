package mc.alk.sponge;

import mc.alk.mc.APIType;
import mc.alk.mc.MCLocation;
import mc.alk.mc.MCOfflinePlayer;
import mc.alk.mc.MCPlayer;
import mc.alk.mc.MCServer;
import mc.alk.mc.MCWorld;
import mc.alk.mc.plugin.MCPlugin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.world.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpongeServer extends MCServer {

    @Override
    public APIType getAPIType() {
        return APIType.SPONGE;
    }

    @Override
    public MCLocation getMCLocation(String world, double x, double y, double z) {
        return new SpongeLocation(new Location<>(Sponge.getServer().getWorld(world).get(), x, y, z));
    }

    @Override
    public MCLocation getMCLocation(String world, double x, double y, double z, float pitch, float yaw) {
        return new SpongeLocation(world, x, y, z, pitch, yaw);
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

    @Override
    public MCOfflinePlayer getMCOfflinePlayer(String name) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        return new SpongeOfflinePlayer(userStorageService.get().get(name).get());
    }

    @Override
    public MCOfflinePlayer getMCOfflinePlayer(UUID uuid) {
        Optional<UserStorageService> userStorageService = Sponge.getServiceManager().provide(UserStorageService.class);
        return new SpongeOfflinePlayer(userStorageService.get().get(uuid).get());
    }

    @Override
    public Collection<MCPlayer> getMCOnlinePlayers() {
        Collection<MCPlayer> players = new ArrayList<>();
        for (Player player : Sponge.getServer().getOnlinePlayers()) {
            players.add(new SpongePlayer(player));
        }
        return players;
    }

    @Override
    public Collection<MCOfflinePlayer> getMCOfflinePlayers() {
        Collection<MCOfflinePlayer> players = new ArrayList<>();
        // TODO: Find a way to do this
        return players;
    }
}
