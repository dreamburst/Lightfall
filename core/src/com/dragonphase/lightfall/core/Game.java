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
import com.dragonphase.lightfall.input.*;
import com.dragonphase.lightfall.input.control.ControlMap;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Axis;
import com.dragonphase.lightfall.input.type.Buttons;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.input.type.MouseButtons;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.ScreenViewport;

public class Game extends ApplicationAdapter implements LogicBase {

    public static boolean DEBUG;

    private static InputManager input;
    public static InputManager getInput() {
        return input;
    }

    private Lightfall lightfall;

    private SpriteBatch spriteBatch;

    private ScreenViewport viewport;

    private boolean globalPaused;

    private ControlMap map;

    public void registerInputManager() {
        Keyboard keyboard = new Keyboard(128);
        Gamepad gamepad = new Gamepad(128);

        Gdx.input.setInputProcessor(keyboard);
        Controllers.addListener(gamepad);

        input = new InputManager(keyboard, gamepad);

        map = new ControlMap();

        map.setControl(Controls.MOVE_UP, Keys.W, Axis.L_UP);
        map.setControl(Controls.MOVE_DOWN, Keys.S, Axis.L_DOWN);
        map.setControl(Controls.MOVE_LEFT, Keys.A, Axis.L_LEFT);
        map.setControl(Controls.MOVE_RIGHT, Keys.D, Axis.L_RIGHT);
        map.setControl(Controls.SPRINT, Keys.SPACE, Buttons.L3);
        map.setControl(Controls.ATTACK, MouseButtons.LEFT, Axis.RT);
        map.setControl(Controls.INTERACT, Keys.E, Buttons.A);
        map.setControl(Controls.USE_ITEM, MouseButtons.RIGHT, Buttons.RB);
        map.setControl(Controls.OPEN_INVENTORY, Keys.Q, Buttons.Y);
        map.setControl(Controls.OPEN_MAP, Keys.C, Buttons.BACK);
        map.setControl(Controls.PAUSE, Keys.ESCAPE, Buttons.START);
    }

    @Override
    public void create () {
        spriteBatch = new SpriteBatch();

        viewport = new ScreenViewport();

        lightfall = new Lightfall();

        registerInputManager();

        controlMap = new ControlMap();
    }

    ControlMap controlMap;

    @Override
    public void resize(int width, int height) {
        viewport.resize(width, height);
    }

    @Override
    public void render () {
        update(Gdx.graphics.getDeltaTime());
        draw(spriteBatch, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        globalPaused = true;
    }

    @Override
    public void resume() {
        globalPaused = false;
    }

    @Override
    public void update(float delta) {
        viewport.update(delta);

        if (!globalPaused) {
            lightfall.update(delta);

            if (map.controlDown(Controls.ATTACK)) {
                System.out.println("yes - " + getInput().getMouse().getPosition());
            }

            /*
            This is used for debugging purposes and will be removed in the final game; options to
            change the game resolution and toggle fullscreen will be added in the final game.
              */
            if (getInput().inputReleased(Keys.F11)) {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setDisplayMode(Assets.VIEWPORT_SIZE.getWidth(), Assets.VIEWPORT_SIZE.getHeight(), false);
                } else {
                    Gdx.graphics.setDisplayMode(
                            Gdx.graphics.getDesktopDisplayMode().width,
                            Gdx.graphics.getDesktopDisplayMode().height,
                            true
                    );
                }
            }

            if (getInput().inputReleased(Keys.F3, Buttons.L3)) {
                DEBUG = !DEBUG;
            }

            getInput().update();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        viewport.draw(spriteBatch, delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        lightfall.draw(spriteBatch, delta);

        spriteBatch.end();
    }
}
