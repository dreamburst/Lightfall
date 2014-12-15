/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import java.util.HashSet;
import java.util.Set;

public class InputHandler<T extends InputType> {

    private Set<T> currentInput, previousInput;
    private Set<T> savedCurrentInput, savedPreviousInput;

    public InputHandler() {
        currentInput = new HashSet<>();
        previousInput = new HashSet<>();
    }

    public Set<T> getCurrentInput() {
        return currentInput;
    }

    public Set<T> getPreviousInput() {
        return previousInput;
    }

    public void saveInputState() {
        savedCurrentInput = new HashSet<>(currentInput);
        savedPreviousInput = new HashSet<>(previousInput);
    }

    public void loadInputState() {
        currentInput = new HashSet<>(savedCurrentInput);
        previousInput = new HashSet<>(savedPreviousInput);
    }

    public void update() {
        previousInput = currentInput == null ? new HashSet<T>() : new HashSet<>(currentInput);
        currentInput = getCurrentInput();
    }
}
