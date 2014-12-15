/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity.living;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.entity.LivingEntity;
import com.dragonphase.lightfall.input.control.ControlMap;

public class Player extends LivingEntity implements Controllable {

    private ControlMap controlMap;

    public Player() {

    }

    @Override
    public ControlMap getControlMap() {
        return controlMap;
    }
}
