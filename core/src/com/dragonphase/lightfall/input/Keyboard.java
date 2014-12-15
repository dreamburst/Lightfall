/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.badlogic.gdx.InputProcessor;

public class Keyboard extends SequenceHandler<Keys> implements InputProcessor {

    public Keyboard(int sequenceInterval) {
        super(sequenceInterval);
    }

    public boolean keyDown(int key) {
        getCurrentInput().add(Keys.match(key));
        getActiveSequence().add(Keys.match(key));

        setSequenceTimer(0);

        return true;
    }

    public boolean keyUp(int key) {
        getCurrentInput().remove(Keys.match(key));

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
