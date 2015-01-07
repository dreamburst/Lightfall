/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.living;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.entity.component.ControlComponent;
import com.dragonphase.lightfall.entity.component.HealthComponent;
import com.dragonphase.lightfall.entity.component.MovementComponent;
import com.dragonphase.lightfall.input.InputController;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Direction;
import com.dragonphase.lightfall.util.Size;
import com.dragonphase.lightfall.util.Vector;

public class Player extends LivingEntity implements Controllable {

    private InputController controller;

    public Player(
            String texture, Size size, Vector<Float> position, EntityState state, Direction direction, float health) {
        super(texture, size, position, state, direction, new HealthComponent(health) {
            @Override
            public void onDeath() {

            }
        });

        addComponent(new ControlComponent());
        addComponent(new MovementComponent(Assets.PLAYER_SPEED));
    }

    @Override
    public InputController getController() {
        return controller;
    }

    @Override
    public void setController(InputController controller) {
        this.controller = controller;
    }
}
