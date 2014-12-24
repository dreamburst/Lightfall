/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragonphase.lightfall.core.LogicBase;
import com.dragonphase.lightfall.entity.Entity;

public class CenterCamera implements LogicBase {

    private OrthographicCamera camera;

    private Vector<Float> target;
    private Vector<Float> velocity;

    private Rectangle bounds;

    private Size size;

    public CenterCamera(Rectangle bounds, float zoom) {
        camera = new OrthographicCamera(Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight());

        getCamera().setToOrtho(false, Display.VIEWPORT_SIZE.getWidth(), Display.VIEWPORT_SIZE.getHeight());
        getCamera().zoom = zoom;

        setBounds(bounds);

        setSize(new Size(
                (int) (Display.VIEWPORT_SIZE.getWidth() * zoom),
                (int) (Display.VIEWPORT_SIZE.getHeight() * zoom)
        ));
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

    @Override
    public void update(float delta) {
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

        getCamera().update(true);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        spriteBatch.setProjectionMatrix(getCamera().combined);
    }
}
