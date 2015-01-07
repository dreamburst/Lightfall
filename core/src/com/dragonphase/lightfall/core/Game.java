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
import com.dragonphase.lightfall.input.Input;
import com.dragonphase.lightfall.input.InputController;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Buttons;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.util.Display;
import com.dragonphase.lightfall.util.ScreenViewport;

import java.util.Random;

public class Game extends ApplicationAdapter implements LogicBase {

    public static boolean DEBUG;

    private SpriteBatch spriteBatch;

    private Lightfall lightfall;

    private boolean globalPaused;

    private Input input;

    public void registerInput() {
        input = Input.getInstance();

        Controllers.addListener(input.getInputManager().getGamepad());
        Gdx.input.setInputProcessor(input.getInputManager().getKeyboard());
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        lightfall = new Lightfall();

        registerInput();

        Display.VIEWPORT = new ScreenViewport();
    }

    @Override
    public void resize(int width, int height) {
        Display.VIEWPORT.resize(width, height);
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
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        draw(spriteBatch, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void update(float delta) {
        Display.VIEWPORT.update(delta);

        if (!globalPaused) {
            lightfall.update(delta);

            /*
            This is used for debugging purposes and will be removed in the final game; options to
            change the game resolution and toggle fullscreen will be added in the final game.
              */
            if (input.getInputManager().inputReleased(Keys.F11)) {
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

            if (input.getInputManager().hasSequence("up up down down left right left right b a")) {
                System.out.println("Yes" + new Random().nextInt());
            }

            if (input.getInputManager().inputReleased(Keys.F3, Buttons.L3)) {
                DEBUG = !DEBUG;
            }

            input.update();
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        Display.VIEWPORT.draw(spriteBatch, delta);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        lightfall.draw(spriteBatch, delta);

        spriteBatch.end();
    }
}
