/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity;

import com.dragonphase.lightfall.input.InputController;

public interface Controllable {
    public InputController getController();
    public void setController(InputController controller);
}
