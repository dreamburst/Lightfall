/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity;

import com.dragonphase.lightfall.input.control.ControlMap;

public class EntityController {

    private Controllable controllable;
    private ControlMap controlMap;

    public EntityController(ControlMap controlMap) {
        setControlMap(controlMap);
    }

    public Controllable getControllable() {
        return controllable;
    }

    public void setControllable(Controllable controllable) {
        this.controllable = controllable;
        controllable.setController(this);
    }

    public ControlMap getControlMap() {
        return controlMap;
    }

    public void setControlMap(ControlMap controlMap) {
        this.controlMap = controlMap;
    }
}
