/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.dragonphase.lightfall.util.Assets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Gamepad implements ControllerListener {

    private HashMap<Axis, Float> activeAxis;

    private HashSet<Buttons> activeButtons;
    private LinkedList<Buttons> activeSequence;

    private int sequenceInterval, sequenceTimer;

    public Gamepad(int sequenceInterval) {
        activeAxis = new HashMap<>();

        activeButtons = new HashSet<>();
        activeSequence = new LinkedList<>();

        this.sequenceInterval = sequenceInterval;
        sequenceTimer = 0;
    }

    public HashMap<Axis, Float> getActiveAxis() {
        return activeAxis;
    }

    public HashSet<Buttons> getActiveButtons() {
        return activeButtons;
    }

    public LinkedList<Buttons> getActiveSequence() {
        return activeSequence;
    }

    public int getSequenceInterval() {
        return sequenceInterval;
    }

    public int getSequenceTimer() {
        return sequenceTimer;
    }

    public void updateSequenceInterval() {
        if (getActiveSequence().size() > 0) {
            if (getSequenceTimer() >= getSequenceInterval()) {
                getActiveSequence().clear();
                sequenceTimer = 0;
            } else {
                sequenceTimer++;
            }
        }
    }

    public void connected(Controller controller) {

    }

    public void disconnected(Controller controller) {

    }

    public boolean buttonDown(Controller controller, int buttonCode) {
        getActiveButtons().add(Buttons.match(buttonCode));
        getActiveSequence().add(Buttons.match(buttonCode));

        sequenceTimer = 0;

        return false;
    }

    public boolean buttonUp(Controller controller, int buttonCode) {
        getActiveButtons().remove(Buttons.match(buttonCode));

        return true;
    }

    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (Math.abs(value) >= Assets.CONTROLLER_DEADZONE) {
            getActiveAxis().put(Axis.match(axisCode), value);
            switch (Axis.match(axisCode)) {
                case LY:
                    getActiveAxis().put(value < 0 ? Axis.L_UP : Axis.L_DOWN, Math.abs(value));
                break;
                case LX:
                    getActiveAxis().put(value < 0 ? Axis.L_LEFT : Axis.L_RIGHT, Math.abs(value));
                    break;
                case RY:
                    getActiveAxis().put(value < 0 ? Axis.R_UP : Axis.R_DOWN, Math.abs(value));
                    break;
                case RX:
                    getActiveAxis().put(value < 0 ? Axis.R_LEFT : Axis.R_RIGHT, Math.abs(value));
                    break;
                case Trigger:
                    getActiveAxis().put(value < 0 ? Axis.RT : Axis.LT, Math.abs(value));
                    break;
                default:
                    break;
            }
        } else {
            getActiveAxis().remove(Axis.match(axisCode));
            switch (Axis.match(axisCode)) {
                case LY:
                    getActiveAxis().remove(Axis.L_UP);
                    getActiveAxis().remove(Axis.L_DOWN);
                    break;
                case LX:
                    getActiveAxis().remove(Axis.L_LEFT);
                    getActiveAxis().remove(Axis.L_RIGHT);
                    break;
                case RY:
                    getActiveAxis().remove(Axis.R_UP);
                    getActiveAxis().remove(Axis.R_DOWN);
                    break;
                case RX:
                    getActiveAxis().remove(Axis.R_LEFT);
                    getActiveAxis().remove(Axis.R_RIGHT);
                    break;
                case Trigger:
                    getActiveAxis().remove(value < 0 ? Axis.RT : Axis.LT);
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        switch (value) {
            case north:
                getActiveButtons().add(Buttons.UP);
                getActiveSequence().add(Buttons.UP);
                sequenceTimer = 0;
                break;
            case east:
                getActiveButtons().add(Buttons.RIGHT);
                getActiveSequence().add(Buttons.RIGHT);
                sequenceTimer = 0;
                break;
            case south:
                getActiveButtons().add(Buttons.DOWN);
                getActiveSequence().add(Buttons.DOWN);
                sequenceTimer = 0;
                break;
            case west:
                getActiveButtons().add(Buttons.LEFT);
                getActiveSequence().add(Buttons.LEFT);
                sequenceTimer = 0;
                break;
            default:
                getActiveButtons().remove(Buttons.UP);
                getActiveButtons().remove(Buttons.RIGHT);
                getActiveButtons().remove(Buttons.DOWN);
                getActiveButtons().remove(Buttons.LEFT);
                break;
        }

        return true;
    }

    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
