/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.living;

import com.dragonphase.lightfall.entity.Entity;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.entity.component.HealthComponent;
import com.dragonphase.lightfall.util.Direction;
import com.dragonphase.lightfall.util.Size;
import com.dragonphase.lightfall.util.Vector;

public abstract class LivingEntity extends Entity {

    public LivingEntity(
            String texture,
            Size size,
            Vector<Float> position,
            EntityState state,
            Direction direction,
            HealthComponent health) {
        super(texture, size, position, state, direction);

        addComponent(health);
    }


}
