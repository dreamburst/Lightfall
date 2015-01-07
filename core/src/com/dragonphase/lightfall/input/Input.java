/*
    Copyright (C) 2015 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

public class Input {

    private int sequenceDelay;
    private float controllerDeadzone;

    private InputManager inputManager;

    private static final Input instance = new Input();

    protected Input() {
        setSequenceDelay(128);
        setControllerDeadzone(0.3f);

        inputManager = new InputManager(new Keyboard(getSequenceDelay()), new Gamepad(getSequenceDelay()));
    }

    public int getSequenceDelay() {
        return sequenceDelay;
    }

    public void setSequenceDelay(int sequenceDelay) {
        this.sequenceDelay = sequenceDelay;
    }

    public float getControllerDeadzone() {
        return controllerDeadzone;
    }

    public void setControllerDeadzone(float controllerDeadzone) {
        this.controllerDeadzone = controllerDeadzone;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void update() {
        getInputManager().update();
    }

    public static Input getInstance() {
        return instance;
    }
}
