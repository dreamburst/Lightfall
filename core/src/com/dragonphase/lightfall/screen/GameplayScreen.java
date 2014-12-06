/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.input.Keys;

public class GameplayScreen extends Screen {

    private boolean paused;

    public GameplayScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    public void update(float delta) {
        if (Game.getInput().inputReleased(Keys.ESCAPE)) {
            paused = !paused;
        }
    }

    public void draw(SpriteBatch spriteBatch, float delta) {

    }
}
