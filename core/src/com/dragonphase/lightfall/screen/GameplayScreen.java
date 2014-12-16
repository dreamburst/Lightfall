/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.util.Assets;

public class GameplayScreen extends Screen {

    private boolean paused;

    public GameplayScreen(ScreenManager screenManager) {
        super(screenManager);
    }

    @Override
    public void update(float delta) {
        if (Assets.CONTROLS.controlPressed(Controls.PAUSE)) {
            paused = !paused;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {

    }
}
