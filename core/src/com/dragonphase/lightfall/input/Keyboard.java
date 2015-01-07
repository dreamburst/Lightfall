/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.input.type.MouseButtons;

public class Keyboard extends SequenceHandler<Keys> implements InputProcessor {

    private Mouse mouse;

    public Keyboard(int sequenceInterval) {
        super(sequenceInterval);

        mouse = new Mouse(sequenceInterval);
    }

    public Mouse getMouse() {
        return mouse;
    }

    private void setMousePosition(int x, int y) {
        getMouse().getPosition().set(x, Gdx.graphics.getHeight() - y);
    }

    @Override
    public boolean keyDown(int key) {
        getCurrentInput().add(Keys.match(key));
        getActiveSequence().add(Keys.match(key));

        setSequenceTimer(0);

        return true;
    }

    @Override
    public boolean keyUp(int key) {
        getCurrentInput().remove(Keys.match(key));

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        getMouse().getCurrentInput().add(MouseButtons.match(button));
        getMouse().getActiveSequence().add(MouseButtons.match(button));
        
        setMousePosition(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        getMouse().getCurrentInput().remove(MouseButtons.match(button));
        setMousePosition(screenX, screenY);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setMousePosition(screenX, screenY);

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        setMousePosition(screenX, screenY);

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void saveInputState() {
        super.saveInputState();

        getMouse().saveInputState();
    }

    @Override
    public void loadInputState() {
        super.loadInputState();

        getMouse().loadInputState();
    }

    @Override
    public void update() {
        super.update();

        getMouse().update();
    }
}
