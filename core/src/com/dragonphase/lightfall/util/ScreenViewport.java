/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonphase.lightfall.core.LogicBase;

public class ScreenViewport implements LogicBase{

    private Camera camera;
    private Viewport viewport;

    public ScreenViewport() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        viewport = new ExtendViewport(Assets.VIEWPORT_SIZE.getWidth(), Assets.VIEWPORT_SIZE.getHeight(), camera);
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void update(float delta) {
        camera.update();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        Gdx.gl.glViewport(0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        spriteBatch.setProjectionMatrix(camera.combined);
    }
}
