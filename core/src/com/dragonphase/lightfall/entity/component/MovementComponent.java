/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.component;

import com.dragonphase.lightfall.util.Vector;

public class MovementComponent extends Component {

    private Vector<Float> velocity;
    private Vector<Float> speed;

    public MovementComponent(Vector<Float> speed) {
        setVelocity(new Vector<>(0f, 0f));
        setSpeed(speed);
    }

    public Vector<Float> getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector<Float> velocity) {
        this.velocity = velocity;
    }

    public Vector<Float> getSpeed() {
        return speed;
    }

    public void setSpeed(Vector<Float> speed) {
        this.speed = speed;
    }

    @Override
    public void update(float delta) {
        getOwner().getPosition().add(getVelocity());
    }
}
