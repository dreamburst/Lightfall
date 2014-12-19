/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.component;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.entity.Entity;
import com.dragonphase.lightfall.entity.EntityController;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.Direction;

public class ControlComponent extends Component {

    public Controllable getControllableOwner() {
        return getOwner() instanceof Controllable ? (Controllable) getOwner() : null;
    }

    public void checkInput() {
        if (getControllableOwner() == null || getControllableOwner().getController().getControllable() != getOwner()) {
            return;
        }

        // ENTITY STATE:

        if (getControllableOwner().getController().getControlMap().controlDown(
                Controls.MOVE_UP, Controls.MOVE_DOWN, Controls.MOVE_LEFT, Controls.MOVE_RIGHT)) {
            getOwner().setState(EntityState.MOVING);
        } else {
            getOwner().setState(EntityState.IDLE);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.ATTACK)) {
            //getOwner().setState(EntityState.ATTACKING);
        }

        // MOVEMENT:

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_UP)) {
            getOwner().getVelocity().set(getOwner().getVelocity().getX(), getOwner().getSpeed().getY());
            getOwner().setDirection(Direction.UP);
        } else if (getOwner().getVelocity().getY() > 0) {
            getOwner().getVelocity().set(getOwner().getVelocity().getX(), 0f);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_DOWN)) {
            getOwner().getVelocity().set(getOwner().getVelocity().getX(), -getOwner().getSpeed().getY());
            getOwner().setDirection(Direction.DOWN);
        } else if (getOwner().getVelocity().getY() < 0) {
            getOwner().getVelocity().set(getOwner().getVelocity().getX(), 0f);
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_LEFT)) {
            getOwner().getVelocity().set(-getOwner().getSpeed().getX(), getOwner().getVelocity().getY());
            getOwner().setDirection(Direction.LEFT);
        } else if (getOwner().getVelocity().getX() < 0) {
            getOwner().getVelocity().set(0f, getOwner().getVelocity().getY());
        }

        if (getControllableOwner().getController().getControlMap().controlDown(Controls.MOVE_RIGHT)) {
            getOwner().getVelocity().set(getOwner().getSpeed().getX(), getOwner().getVelocity().getY());
            getOwner().setDirection(Direction.RIGHT);
        } else if (getOwner().getVelocity().getX() > 0) {
            getOwner().getVelocity().set(0f, getOwner().getVelocity().getY());
        }
    }

    public void update(float delta) {
        checkInput();
    }

}
