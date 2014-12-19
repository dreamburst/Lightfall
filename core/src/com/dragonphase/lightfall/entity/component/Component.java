/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.component;

import com.dragonphase.lightfall.entity.Entity;

public abstract class Component {

    private Entity owner;

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public abstract void update(float delta);
}
