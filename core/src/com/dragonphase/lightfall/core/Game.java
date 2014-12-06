/*
    Copyright (C) 2014 Dragonphase || Contributing Developers

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dragonphase.lightfall.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.input.Gamepad;
import com.dragonphase.lightfall.input.InputManager;
import com.dragonphase.lightfall.input.Keyboard;
import com.dragonphase.lightfall.input.Keys;
import com.dragonphase.lightfall.util.Assets;

public class Game extends ApplicationAdapter implements LogicBase {

    private Lightfall lightfall;

    private SpriteBatch spriteBatch;

    private static InputManager input;

    public static boolean DEBUG;

    public static InputManager getInput() {
        return input;
    }

	public void create () {
        lightfall = new Lightfall();

		spriteBatch = new SpriteBatch();

        Keyboard keyboard = new Keyboard(128);
        Gamepad gamepad = new Gamepad(128);

        Gdx.input.setInputProcessor(keyboard);
        Controllers.addListener(gamepad);

        input = new InputManager(keyboard, gamepad);
	}

	public void render () {
        update(Gdx.graphics.getDeltaTime());
        draw(spriteBatch, Gdx.graphics.getDeltaTime());
	}

    public void update(float delta) {
        lightfall.update(delta);

        if (getInput().inputReleased(Keys.F11)) {
            if (Gdx.graphics.isFullscreen()) {
                Gdx.graphics.setDisplayMode(Assets.SCREEN_SIZE.getWidth(), Assets.SCREEN_SIZE.getHeight(), false);
            } else {
                Gdx.graphics.setDisplayMode(
                        Gdx.graphics.getDesktopDisplayMode().width,
                        Gdx.graphics.getDesktopDisplayMode().height,
                        true
                );
            }
        }

        if (getInput().inputPressed(Keys.F3)) {
            DEBUG = !DEBUG;
        }

        getInput().update();
    }

    public void draw(SpriteBatch spriteBatch, float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        lightfall.draw(spriteBatch, delta);

        spriteBatch.end();
    }
}
