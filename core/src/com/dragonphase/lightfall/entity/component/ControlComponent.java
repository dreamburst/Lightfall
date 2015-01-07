/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.component;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.Direction;

public class ControlComponent extends Component {

    public Controllable getControllableOwner() {
        return getOwner() instanceof Controllable ? (Controllable) getOwner() : null;
    }

    // TODO: Allow input to be processed for entities that do not own a MovementComponent.

    public void checkInput() {
        if (getControllableOwner() == null
                || getControllableOwner().getController().getControllable() != getOwner()
                || !getOwner().hasComponent(MovementComponent.class)) {
            return;
        }

        MovementComponent movement = getOwner().getComponent(MovementComponent.class);

        // ENTITY STATE:

        if (getControllableOwner().getController().getControlMap().controlDown(
                Controls.MOVE_UP, Controls.MOVE_DOWN, Controls.MOVE_LEFT, Controls.MOVE_RIGHT)) {
            getOwner().setState(EntityState.MOVING);
        } else {
            getOwner().setState(EntityState.IDLE);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.ATTACK)) {
            getOwner().setState(EntityState.ATTACKING);
        }

        // MOVEMENT:

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_UP)) {
            movement.getVelocity().setY(movement.getSpeed().getY());
            getOwner().setDirection(Direction.UP);
        } else if (movement.getVelocity().getY() > 0) {
            movement.getVelocity().setY(0f);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_DOWN)) {
            movement.getVelocity().setY(-movement.getSpeed().getY());
            getOwner().setDirection(Direction.DOWN);
        } else if (movement.getVelocity().getY() < 0) {
            movement.getVelocity().setY(0f);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_LEFT)) {
            movement.getVelocity().setX(-movement.getSpeed().getX());
            getOwner().setDirection(Direction.LEFT);
        } else if (movement.getVelocity().getX() < 0) {
            movement.getVelocity().setX(0f);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_RIGHT)) {
            movement.getVelocity().setX(movement.getSpeed().getX());
            getOwner().setDirection(Direction.RIGHT);
        } else if (movement.getVelocity().getX() > 0) {
            movement.getVelocity().setX(0f);
        }
    }

    public void update(float delta) {
        checkInput();
    }
}
