/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.dragonphase.lightfall.input.type.*;

import java.util.*;

public class InputManager {

    private Keyboard keyboard;
    private Gamepad gamepad;

    private Map<String, LinkedList<Keys>> registeredKeySequences;
    private Map<String, LinkedList<Buttons>> registeredButtonSequences;

    public InputManager(Keyboard keyboard, Gamepad gamepad) {
        this.keyboard = keyboard;
        this.gamepad = gamepad;

        registeredKeySequences = new HashMap<>();
        registeredButtonSequences = new HashMap<>();

        update();
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return getKeyboard().getMouse();
    }

    public Gamepad getGamepad() {
        return gamepad;
    }

    /**
     * Saves the current state of the InputManager.
     */
    public void saveState() {
        getKeyboard().saveInputState();
        getGamepad().saveInputState();
    }

    /**
     * Loads the previous state of the InputManager.
     */
    public void loadState() {
        getKeyboard().loadInputState();
        getGamepad().loadInputState();
    }

    public void update() {
        getKeyboard().update();
        getGamepad().update();

        getKeyboard().updateSequenceInterval();
        getGamepad().updateSequenceInterval();
    }

    /**
     * Gets whether at least one of the specified {@link com.dragonphase.lightfall.input.type.InputType} is down.
     * @param types The {@link com.dragonphase.lightfall.input.type.InputType} to check.
     * @return true if at least one of the specified {@link com.dragonphase.lightfall.input.type.InputType} is down.
     */
    public boolean inputDown(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (getKeyboard().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof MouseButtons) {
                if (getMouse().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (getGamepad().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (getGamepad().getCurrentAxis().containsKey(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link InputType} are down.
     * @param types The {@link InputType} to check.
     * @return true if all of the specified {@link InputType} are down.
     */
    public boolean inputsDown(InputType... types) {
        for (InputType type : types) {
            if (!inputDown(type)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets whether at least one of the specified {@link InputType} is up.
     * @param types The {@link InputType} to check.
     * @return true if at least one of the specified {@link InputType} is up.
     */
    public boolean inputUp(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (!getKeyboard().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof MouseButtons) {
                if (!getMouse().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (!getGamepad().getCurrentInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (!getGamepad().getCurrentAxis().containsKey(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link InputType} are up.
     * @param types The {@link InputType} to check.
     * @return true if all of the specified {@link InputType} are up.
     */
    public boolean inputsUp(InputType... types) {
        for (InputType type : types) {
            if (!inputUp(type)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets whether at least one of the specified {@link InputType} was pressed.
     * @param types The {@link InputType} to check.
     * @return true if at least one of the specified {@link InputType} was pressed.
     */
    public boolean inputPressed(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (getKeyboard().getCurrentInput().contains(type) && !getKeyboard().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof MouseButtons) {
                if (getMouse().getCurrentInput().contains(type) && !getMouse().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (getGamepad().getCurrentInput().contains(type) && !getGamepad().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (getGamepad().getCurrentAxis().containsKey(type) && !getGamepad().getPreviousAxis().containsKey(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link InputType} were pressed.
     * @param types The {@link InputType} to check.
     * @return true if all of the specified {@link InputType} were pressed.
     */
    public boolean inputsPressed(InputType... types) {
        for (InputType type : types) {
            if (!inputPressed(type)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets whether at least one of the specified {@link InputType} was released.
     * @param types The {@link InputType} to check.
     * @return true if at least one of the specified {@link InputType} was released.
     */
    public boolean inputReleased(InputType... types) {
        for (InputType type : types) {
            if (type instanceof Keys) {
                if (!getKeyboard().getCurrentInput().contains(type) && getKeyboard().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof MouseButtons) {
                if (!getMouse().getCurrentInput().contains(type) && getMouse().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Buttons) {
                if (!getGamepad().getCurrentInput().contains(type) && getGamepad().getPreviousInput().contains(type)) {
                    return true;
                }
            }
            if (type instanceof Axis) {
                if (!getGamepad().getCurrentAxis().containsKey(type) && getGamepad().getPreviousAxis().containsKey(type)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Gets whether all of the specified {@link InputType} were released.
     * @param types The {@link InputType} to check.
     * @return true if all of the specified {@link InputType} were released.
     */
    public boolean inputsReleased(InputType... types) {
        for (InputType type : types) {
            if (!inputReleased(type)) {
                return false;
            }
        }

        return true;
    }

    public float getAxisStrength(Axis axis) {
        if (inputsDown(axis)) {
            return getGamepad().getCurrentAxis().get(axis);
        }

        return 0f;
    }

    public boolean hasSequence(InputType... types) {
        if (types[0] instanceof Keys) {
            return hasSequence(keyboard, types);
        }

        if (types[0] instanceof Buttons) {
            return hasSequence(gamepad, types);
        }

        return false;
    }

    public boolean hasSequence(SequenceHandler handler, InputType... types) {
        if (handler.getActiveSequence().size() < 1) {
            return false;
        }

        if (types.length != handler.getActiveSequence().size()) {
            return false;
        }

        for (int i = 0; i < types.length; i++) {
            if (types[i].getCode() != ((InputType) handler.getActiveSequence().get(i)).getCode()) {
                return false;
            }
        }

        handler.getActiveSequence().clear();

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

        return hasSequence(keyboard, keys) || hasSequence(gamepad, buttons);
    }

    public <T extends InputType> boolean hasSequence(LinkedList<T> types) {
        return hasSequence(types.toArray(new InputType[types.size()]));
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
