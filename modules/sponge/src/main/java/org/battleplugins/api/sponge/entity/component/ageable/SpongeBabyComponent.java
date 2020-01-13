package org.battleplugins.api.sponge.entity.component.ageable;

import org.battleplugins.api.entity.Entity;
import org.battleplugins.api.entity.component.ageable.BabyComponent;
import org.battleplugins.api.sponge.entity.SpongeEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.Ageable;

import java.util.Optional;

public class SpongeBabyComponent implements BabyComponent {

    @Override
    public void applyComponent(Entity entity, Boolean baby) {
        ((SpongeEntity) entity).getHandle().offer(Keys.IS_ADULT, !baby);
    }

    @Override
    public Optional<Boolean> getValue(Entity entity) {
        return ((SpongeEntity) entity).getHandle().get(Keys.IS_ADULT).map(adult -> !adult);
    }

    @Override
    public boolean isAppliable(Entity entity) {
        return ((SpongeEntity) entity).getHandle() instanceof Ageable;
    }
}
