/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input.control;

import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.input.type.InputType;
import com.dragonphase.lightfall.util.Assets;

import java.util.*;

public class ControlMap {

    private Map<Controls, List<InputType>> controls;

    public ControlMap() {
        controls = new EnumMap<>(Controls.class);
    }

    public Map<Controls, List<InputType>> getControls() {
        return controls;
    }

    public InputType[] getInputs(Controls control) {
        return getControls().get(control).toArray(new InputType[getControls().get(control).size()]);
    }

    public void setControl(Controls control, InputType... types) {
        controls.put(control, Arrays.asList(types));
    }
    
    /**
     * Gets whether at least one of the specified {@link Controls} is down.
     * @param controls The {@link com.dragonphase.lightfall.input.control.Controls} to check.
     * @return true if at least one of the specified {@link Controls} is down.
     */
    public boolean controlDown(Controls... controls) {
        for (Controls control : controls) {
            if (Assets.INPUT.inputDown(getInputs(control))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link Controls} are down.
     * @param controls The {@link Controls} to check.
     * @return true if all of the specified {@link Controls} are down.
     */
    public boolean controlsDown(Controls... controls) {
        for (Controls control : controls) {
            if (!controlDown(control)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets whether at least one of the specified {@link Controls} is up.
     * @param controls The {@link Controls} to check.
     * @return true if at least one of the specified {@link Controls} is up.
     */
    public boolean controlUp(Controls... controls) {
        for (Controls control : controls) {
            if (Assets.INPUT.inputUp(getInputs(control))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link Controls} are up.
     * @param controls The {@link Controls} to check.
     * @return true if all of the specified {@link Controls} are up.
     */
    public boolean controlsUp(Controls... controls) {
        for (Controls control : controls) {
            if (!controlUp(control)) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Gets whether at least one of the specified {@link Controls} was pressed.
     * @param controls The {@link Controls} to check.
     * @return true if at least one of the specified {@link Controls} was pressed.
     */
    public boolean controlPressed(Controls... controls) {
        for (Controls control : controls) {
            if (Assets.INPUT.inputPressed(getInputs(control))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link Controls} were pressed.
     * @param controls The {@link Controls} to check.
     * @return true if all of the specified {@link Controls} were pressed.
     */
    public boolean controlsPressed(Controls... controls) {
        for (Controls control : controls) {
            if (!controlPressed(control)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets whether at least one of the specified {@link Controls} was released.
     * @param controls The {@link Controls} to check.
     * @return true if at least one of the specified {@link Controls} was released.
     */
    public boolean controlReleased(Controls... controls) {
        for (Controls control : controls) {
            if (Assets.INPUT.inputReleased(getInputs(control))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link Controls} were released.
     * @param controls The {@link Controls} to check.
     * @return true if all of the specified {@link Controls} were released.
     */
    public boolean controlsReleased(Controls... controls) {
        for (Controls control : controls) {
            if (!controlReleased(control)) {
                return false;
            }
        }

        return true;
    }
}
