/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity;

public enum EntityState {
    NONE,
    IDLE,
    MOVING,
    ATTACKING,
    TAKING_DAMAGE,
    FALLING,
    LANDING,
    DYING,
    DEAD,
    ;

    public static EntityState match(String value) {
        return EntityState.valueOf(value.toUpperCase());
    }
}
