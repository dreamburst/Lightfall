/*
    Copyright (C) 2014 Dragonphase || Contributing Developers

    All rights reserved.
 */

package com.dragonphase.lightfall.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Axis;
import com.dragonphase.lightfall.input.type.Buttons;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.input.type.MouseButtons;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Display;
import com.dragonphase.lightfall.util.ScreenViewport;

public class Game extends ApplicationAdapter implements LogicBase {

    public static boolean DEBUG;

    private Lightfall lightfall;

    private SpriteBatch spriteBatch;

    private ScreenViewport viewport;

    private boolean globalPaused;

    public void registerInput() {
        Controllers.addListener(Assets.INPUT.getGamepad());
        Gdx.input.setInputProcessor(Assets.INPUT.getKeyboard());

        Assets.CONTROLS.setControl(Controls.MOVE_UP, Keys.W, Axis.L_UP);
        Assets.CONTROLS.setControl(Controls.MOVE_DOWN, Keys.S, Axis.L_DOWN);
        Assets.CONTROLS.setControl(Controls.MOVE_LEFT, Keys.A, Axis.L_LEFT);
        Assets.CONTROLS.setControl(Controls.MOVE_RIGHT, Keys.D, Axis.L_RIGHT);
        Assets.CONTROLS.setControl(Controls.SPRINT, Keys.SPACE, Buttons.L3);
        Assets.CONTROLS.setControl(Controls.ATTACK, MouseButtons.LEFT, Axis.RT);
        Assets.CONTROLS.setControl(Controls.INTERACT, Keys.E, Buttons.A);
        Assets.CONTROLS.setControl(Controls.USE_ITEM, MouseButtons.RIGHT, Buttons.RB);
        Assets.CONTROLS.setControl(Controls.OPEN_INVENTORY, Keys.Q, Buttons.Y);
        Assets.CONTROLS.setControl(Controls.OPEN_MAP, Keys.C, Buttons.BACK);
        Assets.CONTROLS.setControl(Controls.PAUSE, Keys.ESCAPE, Buttons.START);
    }

    @Override
    public void create () {
        spriteBatch = new SpriteBatch();

        viewport = new ScreenViewport();

        lightfall = new Lightfall();

        registerInput();
    }

    @Override
    public void resize(int width, int height) {
        viewport.resize(width, height);
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
    public void render () {
        update(Gdx.graphics.getDeltaTime());
        draw(spriteBatch, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void update(float delta) {
        viewport.update(delta);

        if (!globalPaused) {
            lightfall.update(delta);

            /*
            This is used for debugging purposes and will be removed in the final game; options to
            change the game resolution and toggle fullscreen will be added in the final game.
              */
            if (Assets.INPUT.inputReleased(Keys.F11)) {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setDisplayMode(Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight(), false);
                } else {
                    Gdx.graphics.setDisplayMode(
                            Gdx.graphics.getDesktopDisplayMode().width,
                            Gdx.graphics.getDesktopDisplayMode().height,
                            true
                    );
                }
            }

            if (Assets.INPUT.inputReleased(Keys.F3, Buttons.L3)) {
                DEBUG = !DEBUG;
            }

            Assets.INPUT.update();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        viewport.draw(spriteBatch, delta);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        lightfall.draw(spriteBatch, delta);

        spriteBatch.end();
    }
}
