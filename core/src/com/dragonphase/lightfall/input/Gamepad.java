/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.dragonphase.lightfall.input.type.Axis;
import com.dragonphase.lightfall.input.type.Buttons;
import com.dragonphase.lightfall.util.Assets;

import java.util.HashMap;

public class Gamepad extends SequenceHandler<Buttons> implements ControllerListener {

    private HashMap<Axis, Float> currentAxis, previousAxis;
    private HashMap<Axis, Float> savedCurrentAxis, savedPreviousAxis;

    public Gamepad(int sequenceInterval) {
        super(sequenceInterval);

        currentAxis = new HashMap<>();
        previousAxis = new HashMap<>();
    }

    public HashMap<Axis, Float> getCurrentAxis() {
        return currentAxis;
    }

    public HashMap<Axis, Float> getPreviousAxis() {
        return previousAxis;
    }

    @Override
    public void connected(Controller controller) {
        System.out.println(String.format("Controller %s connected!", controller.getName()));
    }

    @Override
    public void disconnected(Controller controller) {
        System.out.println(String.format("Controller %s disconnected!", controller.getName()));
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        getCurrentInput().add(Buttons.match(buttonCode));
        getActiveSequence().add(Buttons.match(buttonCode));

        setSequenceTimer(0);

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        getCurrentInput().remove(Buttons.match(buttonCode));

        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if (Math.abs(value) >= Assets.CONTROLLER_DEADZONE) {
            getCurrentAxis().put(Axis.match(axisCode), value);
            switch (Axis.match(axisCode)) {
                case LY:
                    getCurrentAxis().put(value < 0 ? Axis.L_UP : Axis.L_DOWN, Math.abs(value));
                break;
                case LX:
                    getCurrentAxis().put(value < 0 ? Axis.L_LEFT : Axis.L_RIGHT, Math.abs(value));
                    break;
                case RY:
                    getCurrentAxis().put(value < 0 ? Axis.R_UP : Axis.R_DOWN, Math.abs(value));
                    break;
                case RX:
                    getCurrentAxis().put(value < 0 ? Axis.R_LEFT : Axis.R_RIGHT, Math.abs(value));
                    break;
                case Trigger:
                    getCurrentAxis().put(value < 0 ? Axis.RT : Axis.LT, Math.abs(value));
                    break;
                default:
                    break;
            }
        } else {
            getCurrentAxis().remove(Axis.match(axisCode));
            switch (Axis.match(axisCode)) {
                case LY:
                    getCurrentAxis().remove(Axis.L_UP);
                    getCurrentAxis().remove(Axis.L_DOWN);
                    break;
                case LX:
                    getCurrentAxis().remove(Axis.L_LEFT);
                    getCurrentAxis().remove(Axis.L_RIGHT);
                    break;
                case RY:
                    getCurrentAxis().remove(Axis.R_UP);
                    getCurrentAxis().remove(Axis.R_DOWN);
                    break;
                case RX:
                    getCurrentAxis().remove(Axis.R_LEFT);
                    getCurrentAxis().remove(Axis.R_RIGHT);
                    break;
                case Trigger:
                    getCurrentAxis().remove(value < 0 ? Axis.RT : Axis.LT);
                    break;
                default:
                    break;
            }
        }

        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        switch (value) {
            case north:
                getCurrentInput().add(Buttons.UP);
                getActiveSequence().add(Buttons.UP);
                setSequenceTimer(0);
                break;
            case east:
                getCurrentInput().add(Buttons.RIGHT);
                getActiveSequence().add(Buttons.RIGHT);
                setSequenceTimer(0);
                break;
            case south:
                getCurrentInput().add(Buttons.DOWN);
                getActiveSequence().add(Buttons.DOWN);
                setSequenceTimer(0);
                break;
            case west:
                getCurrentInput().add(Buttons.LEFT);
                getActiveSequence().add(Buttons.LEFT);
                setSequenceTimer(0);
                break;
            default:
                getCurrentInput().remove(Buttons.UP);
                getCurrentInput().remove(Buttons.RIGHT);
                getCurrentInput().remove(Buttons.DOWN);
                getCurrentInput().remove(Buttons.LEFT);
                break;
        }

        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    @Override
    public void saveInputState() {
        super.saveInputState();

        savedCurrentAxis = new HashMap<>(currentAxis);
        savedPreviousAxis = new HashMap<>(previousAxis);
    }

    @Override
    public void loadInputState() {
        super.loadInputState();

        currentAxis = new HashMap<>(savedCurrentAxis);
        previousAxis = new HashMap<>(savedPreviousAxis);
    }

    @Override
    public void update() {
        super.update();

        previousAxis = currentAxis == null ? new HashMap<Axis, Float>() : new HashMap<>(currentAxis);
        currentAxis = getCurrentAxis();
    }
}
