/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.component;

import com.dragonphase.lightfall.entity.Entity;

public abstract class HealthComponent extends Component {

    private float health;

    public HealthComponent(float health) {
        setHealth(health);
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public abstract void onDeath();

    @Override
    public void update(float delta) {
        if (getHealth() <= 0) {
            onDeath();
        }
    }
}
