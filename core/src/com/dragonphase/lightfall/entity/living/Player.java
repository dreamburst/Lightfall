/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.living;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.entity.Entity;
import com.dragonphase.lightfall.entity.EntityController;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.entity.component.ControlComponent;
import com.dragonphase.lightfall.entity.component.HealthComponent;
import com.dragonphase.lightfall.input.control.ControlMap;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.Direction;
import com.dragonphase.lightfall.util.Size;
import com.dragonphase.lightfall.util.Vector;

public class Player extends LivingEntity implements Controllable {

    private EntityController controller;

    public Player(
            String texture, Size size, Vector<Float> position, EntityState state, Direction direction, float health) {
        super(texture, size, position, state, direction, new HealthComponent(health) {
            @Override
            public void onDeath() {

            }
        });

        addComponent(new ControlComponent());
    }

    @Override
    public EntityController getController() {
        return controller;
    }

    @Override
    public void setController(EntityController controller) {
        this.controller = controller;
    }
}
