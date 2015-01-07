/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonphase.lightfall.core.LogicBase;
import com.dragonphase.lightfall.entity.Entity;

public class ScreenViewport implements LogicBase {

    private OrthographicCamera camera;
    private Viewport viewport;

    private Vector<Float> target;
    private Vector<Float> velocity;

    private Rectangle bounds;

    private Size size;

    private float zoom;

    public ScreenViewport() {
        this(new Rectangle(0, 0, Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight()), 1);
    }

    public ScreenViewport(Rectangle bounds, float zoom) {
        camera = new OrthographicCamera(Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight());

        setZoom(zoom);

        getCamera().setToOrtho(false, Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight());
        getCamera().zoom = getZoom();

        setBounds(bounds);

        setSize(new Size(
                (int) (Display.VIEWPORT_SIZE.getWidth() * getZoom()),
                (int) (Display.VIEWPORT_SIZE.getHeight() * getZoom())
        ));

        viewport = new ExtendViewport(Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight(), getCamera());
        viewport.update(Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight(), true);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Vector<Float> getTarget() {
        return target;
    }

    public void setTarget(Vector<Float> target) {
        this.target = target;
    }

    public void setTarget(Entity target) {
        this.target = target.getCenterPosition();
    }

    public Vector<Float> getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector<Float> velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = 1 / zoom;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void update(float delta) {
        if (getTarget() != null) {
            getCamera().position.x += getTarget().getX() - getCamera().position.x;
            getCamera().position.y += getTarget().getY() - getCamera().position.y;

            if (getCamera().position.x - getSize().getWidth() / 2 <= 0) {
                getCamera().position.x = getSize().getWidth() / 2;
            }

            if (getCamera().position.x + getSize().getWidth() / 2 >= getBounds().getWidth()) {
                getCamera().position.x = getBounds().getWidth() - getSize().getWidth() / 2;
            }

            if (getCamera().position.y - getSize().getHeight() / 2 <= 0) {
                getCamera().position.y = getSize().getHeight() / 2;
            }

            if (getCamera().position.y + getSize().getHeight() / 2 >= getBounds().getHeight()) {
                getCamera().position.y = getBounds().getHeight() - getSize().getHeight() / 2;
            }
        }

        getCamera().update(true);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        Gdx.gl.glViewport(0, 0, viewport.getScreenWidth(), viewport.getScreenHeight());
        spriteBatch.setProjectionMatrix(camera.combined);
    }
}
