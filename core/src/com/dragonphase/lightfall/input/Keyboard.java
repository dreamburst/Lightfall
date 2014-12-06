/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.badlogic.gdx.InputProcessor;

import java.util.HashSet;
import java.util.LinkedList;

public class Keyboard implements InputProcessor {

    private HashSet<Keys> activeKeys;
    private LinkedList<Keys> activeSequence;

    private int sequenceInterval, sequenceTimer;

    public Keyboard(int sequenceInterval) {
        activeKeys = new HashSet<>();
        activeSequence = new LinkedList<>();

        this.sequenceInterval = sequenceInterval;
        sequenceTimer = 0;
    }

    public HashSet<Keys> getActiveKeys() {
        return activeKeys;
    }

    public LinkedList<Keys> getActiveSequence() {
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

    public boolean keyDown(int key) {
        getActiveKeys().add(Keys.match(key));
        getActiveSequence().add(Keys.match(key));

        sequenceTimer = 0;

        return true;
    }

    public boolean keyUp(int key) {
        getActiveKeys().remove(Keys.match(key));

        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
}
