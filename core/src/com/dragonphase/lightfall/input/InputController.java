/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.dragonphase.lightfall.entity.Controllable;
import com.dragonphase.lightfall.input.control.ControlMap;
import com.dragonphase.lightfall.util.Assets;

public class InputController {

    private Controllable controllable;
    private ControlMap controlMap;
    
    private static final InputController instance = new InputController();
    
    protected InputController() {
        setControlMap(Assets.DEFAULT_CONTROLS);
    }

    public InputController(ControlMap controlMap) {
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
    
    public static InputController getInstance() {
        return instance;
    }
}
