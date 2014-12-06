/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.*;

public class InputManager {

    private Keyboard activeKeyboard;
    private Gamepad activeGamepad;

    private HashSet<Keys> currentKeys, previousKeys;
    private HashSet<Keys> savedCurrentKeys, savedPreviousKeys;

    private HashSet<Buttons> currentButtons, previousButtons;
    private HashSet<Buttons> savedCurrentButtons, savedPreviousButtons;

    private Map<Axis, Float> currentAxis, previousAxis;
    private Map<Axis, Float> savedCurrentAxis, savedPreviousAxis;

    private Map<String, LinkedList<Keys>> registeredKeySequences;
    private Map<String, LinkedList<Buttons>> registeredButtonSequences;

    public InputManager(Keyboard keyboard, Gamepad gamepad) {
        activeKeyboard = keyboard;
        activeGamepad = gamepad;

        registeredKeySequences = new HashMap<>();
        registeredButtonSequences = new HashMap<>();

        update();
    }

    //Save and Load the state of the InputManager

    public void saveInputState() {
        savedCurrentKeys = new HashSet<>(currentKeys);
        savedPreviousKeys = new HashSet<>(previousKeys);

        savedCurrentButtons = new HashSet<>(currentButtons);
        savedPreviousButtons = new HashSet<>(previousButtons);

        savedCurrentAxis = new HashMap<>(currentAxis);
        savedPreviousAxis = new HashMap<>(previousAxis);
    }

    public void loadInputState() {
        currentKeys = new HashSet<>(savedCurrentKeys);
        previousKeys = new HashSet<>(savedPreviousKeys);

        currentButtons = new HashSet<>(savedCurrentButtons);
        previousButtons = new HashSet<>(savedPreviousButtons);

        currentAxis = new HashMap<>(savedCurrentAxis);
        previousAxis = new HashMap<>(savedPreviousAxis);
    }

    /**
     * Update the state of the InputManager
     */
    public void update() {
        previousKeys = currentKeys == null ? new HashSet<Keys>() : new HashSet<>(currentKeys);
        currentKeys = activeKeyboard.getActiveKeys();

        previousButtons = currentButtons == null ? new HashSet<Buttons>() : new HashSet<>(currentButtons);
        currentButtons = activeGamepad.getActiveButtons();

        previousAxis = currentAxis == null ? new HashMap<Axis, Float>() : new HashMap<>(currentAxis);
        currentAxis = activeGamepad.getActiveAxis();

        activeKeyboard.updateSequenceInterval();
        activeGamepad.updateSequenceInterval();
    }

    //Input Down

    public boolean inputDown(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (keyDown((Keys) type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (buttonDown((Buttons) type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (axeDown((Axis) type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean keyDown(Keys... keys) {
        for (Keys key : keys) {
            if (currentKeys.contains(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean buttonDown(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (currentButtons.contains(button)) {
                return true;
            }
        }

        return false;
    }

    public boolean axeDown(Axis... axis) {
        for (Axis axe : axis) {
            if (currentAxis.containsKey(axe)) {
                return true;
            }
        }

        return false;
    }

    //Multiple Input Down

    public boolean inputsDown(InputType... types) {
        for (InputType type : types) {
            if (!inputDown(type)) {
                return false;
            }
        }

        return true;
    }

    public boolean keysDown(Keys... keys) {
        for (Keys key : keys) {
            if (!keyDown(key)) {
                return false;
            }
        }

        return true;
    }

    public boolean buttonsDown(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!buttonDown(button)) {
                return false;
            }
        }

        return true;
    }

    public boolean axisDown(Axis... axis) {
        for (Axis axe : axis) {
            if (!axeDown(axe)) {
                return false;
            }
        }

        return true;
    }

    //Input Up

    public boolean inputUp(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (keyUp((Keys) type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (buttonUp((Buttons) type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (axeUp((Axis) type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean keyUp(Keys... keys) {
        for (Keys key : keys) {
            if (!currentKeys.contains(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean buttonUp(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!currentButtons.contains(button)) {
                return true;
            }
        }

        return false;
    }

    public boolean axeUp(Axis... axis) {
        for (Axis axe : axis) {
            if (!currentAxis.containsKey(axe)) {
                return true;
            }
        }

        return false;
    }

    //Multiple Input Up

    public boolean inputsUp(InputType... types) {
        for (InputType type : types) {
            if (!inputUp(type)) {
                return false;
            }
        }

        return true;
    }

    public boolean keysUp(Keys... keys) {
        for (Keys key : keys) {
            if (!keyUp(key)) {
                return false;
            }
        }

        return true;
    }

    public boolean buttonsUp(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!buttonUp(button)) {
                return false;
            }
        }

        return true;
    }

    public boolean axisUp(Axis... axis) {
        for (Axis axe : axis) {
            if (!axeUp(axe)) {
                return false;
            }
        }

        return true;
    }

    //Input Pressed (During one update interval)

    public boolean inputPressed(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (keyPressed((Keys) type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (buttonPressed((Buttons) type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (axePressed((Axis) type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean keyPressed(Keys... keys) {
        for (Keys key : keys) {
            if (currentKeys.contains(key) && !previousKeys.contains(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean buttonPressed(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (currentButtons.contains(button) && !previousButtons.contains(button)) {
                return true;
            }
        }

        return false;
    }

    public boolean axePressed(Axis... axis) {
        for (Axis axe : axis) {
            if (currentAxis.containsKey(axe) && !previousAxis.containsKey(axe)) {
                return true;
            }
        }

        return false;
    }

    //Multiple Input Pressed (During one update interval)

    public boolean inputsPressed(InputType... types) {
        for (InputType type : types) {
            if (!inputPressed(type)) {
                return false;
            }
        }

        return true;
    }

    public boolean keysPressed(Keys... keys) {
        for (Keys key : keys) {
            if (!keyPressed(key)) {
                return false;
            }
        }

        return true;
    }

    public boolean buttonsPressed(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!buttonPressed(button)) {
                return false;
            }
        }

        return true;
    }

    public boolean axisPressed(Axis... axis) {
        for (Axis axe : axis) {
            if (!axePressed(axe)) {
                return false;
            }
        }

        return true;
    }

    //Input Released (During one update interval)

    public boolean inputReleased(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (keyReleased((Keys) type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (buttonReleased((Buttons) type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (axeReleased((Axis) type)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean keyReleased(Keys... keys) {
        for (Keys key : keys) {
            if (!currentKeys.contains(key) && previousKeys.contains(key)) {
                return true;
            }
        }

        return false;
    }

    public boolean buttonReleased(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!currentButtons.contains(button) && previousButtons.contains(button)) {
                return true;
            }
        }

        return false;
    }

    public boolean axeReleased(Axis... axis) {
        for (Axis axe : axis) {
            if (!currentAxis.containsKey(axe) && previousAxis.containsKey(axe)) {
                return true;
            }
        }

        return false;
    }

    //Multiple Input Released (During one update interval)

    public boolean inputsReleased(InputType... types) {
        for (InputType type : types) {
            if (!inputReleased(type)) {
                return false;
            }
        }

        return true;
    }

    public boolean keysReleased(Keys... keys) {
        for (Keys key : keys) {
            if (!keyReleased(key)) {
                return false;
            }
        }

        return true;
    }

    public boolean buttonsReleased(Buttons... buttons) {
        for (Buttons button : buttons) {
            if (!buttonReleased(button)) {
                return false;
            }
        }

        return true;
    }

    public boolean axisReleased(Axis... axis) {
        for (Axis axe : axis) {
            if (!axeReleased(axe)) {
                return false;
            }
        }

        return true;
    }

    public float getAxisStrength(Axis axis) {
        if (inputsDown(axis)) {
            return currentAxis.get(axis);
        }

        return 0f;
    }

    public boolean hasSequence(InputType... types) {
        for (InputType type : types) {

        }

        return false;
    }

    public boolean hasSequence(Keys... keys) {
        if (activeKeyboard.getActiveSequence().size() < 1) {
            return false;
        }

        if (keys.length != activeKeyboard.getActiveSequence().size()) {
            return false;
        }

        for (int i = 0; i < keys.length; i++) {
            if (keys[i].getCode() != activeKeyboard.getActiveSequence().get(i).getCode()) {
                return false;
            }
        }

        activeKeyboard.getActiveSequence().clear();

        return true;
    }

    public boolean hasSequence(Buttons... buttons) {
        if (activeGamepad.getActiveSequence().size() < 1) {
            return false;
        }

        if (buttons.length != activeGamepad.getActiveSequence().size()) {
            return false;
        }

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getCode() != activeGamepad.getActiveSequence().get(i).getCode()) {
                return false;
            }
        }

        activeGamepad.getActiveSequence().clear();

        return true;
    }

    public boolean hasSequence(String sequence) {
        String[] splitSequence = sequence.split(" ");

        Keys[] keys = new Keys[splitSequence.length];
        Buttons[] buttons = new Buttons[splitSequence.length];

        for (int i = 0; i < splitSequence.length; i++) {
            keys[i] = Keys.match(splitSequence[i]);
            buttons[i] = Buttons.match(splitSequence[i]);
        }

        return hasSequence(keys) || hasSequence(buttons);
    }

    public boolean hasKeySequence(LinkedList<Keys> keys) {
        return hasSequence(keys.toArray(new Keys[keys.size()]));
    }

    public boolean hasButtonSequence(LinkedList<Buttons> buttons) {
        return hasSequence(buttons.toArray(new Buttons[buttons.size()]));
    }

    public void registerKeySequence(String name, LinkedList<Keys> keys) {
        registeredKeySequences.put(name, keys);
    }

    public void registerButtonSequence(String name, LinkedList<Buttons> buttons) {
        registeredButtonSequences.put(name, buttons);
    }

    public void registerKeySequence(String name, Keys... keys) {
        LinkedList<Keys> sequence = new LinkedList<>();
        Collections.addAll(sequence, keys);

        registerKeySequence(name, sequence);
    }

    public void registerButtonSequence(String name, Buttons... buttons) {
        LinkedList<Buttons> sequence = new LinkedList<>();
        Collections.addAll(sequence, buttons);

        registerButtonSequence(name, sequence);
    }

    public void registerSequence(String name, String sequence) {
        String[] splitSequence = sequence.split(" ");

        Keys[] keys = new Keys[splitSequence.length];
        Buttons[] buttons = new Buttons[splitSequence.length];

        for (int i = 0; i < splitSequence.length; i++) {
            keys[i] = Keys.match(splitSequence[i]);
            buttons[i] = Buttons.match(splitSequence[i]);
        }

        registerKeySequence(name, keys);
        registerButtonSequence(name, buttons);
    }

    public void unregisterSequence(String name) {
        registeredKeySequences.remove(name);
    }

    public LinkedList<Keys> getRegisteredKeySequence(String name) {
        if (registeredKeySequences.containsKey(name)) {
            return registeredKeySequences.get(name);
        }

        return null;
    }

    public LinkedList<Buttons> getRegisteredButtonSequence(String name) {
        if (registeredButtonSequences.containsKey(name)) {
            return registeredButtonSequences.get(name);
        }

        return null;
    }
}
