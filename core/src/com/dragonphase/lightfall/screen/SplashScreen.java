/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonphase.lightfall.event.screen.SplashScreenEndEvent;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Display;
import com.dragonphase.lightfall.util.Vector;

public class SplashScreen extends Screen {

    private SplashScreenEndEvent endEvent;

    private final TextureRegion texture;

    private Vector<Integer> position;

    private float initialInterval = 0;
    private float interval = 0;
    private float alpha = 0;

    private final float initialDelay = 0.5f;
    private final float delay = 2;
    private final float speed = 0.02f;

    public SplashScreen(ScreenManager screenManager, String ref) {
        super(screenManager);

        texture = Assets.getScreen(ref);

        position = new Vector<>(
                Display.VIEWPORT_SIZE.getWidth() / 2 - (texture.getRegionWidth() / 2),
                Display.VIEWPORT_SIZE.getHeight() / 2 - (texture.getRegionHeight() / 2)
        );
    }

    public boolean hasEnded() {
        return alpha <= 0 + speed && interval >= delay;
    }

    public void onEnd(SplashScreenEndEvent endEvent) {
        this.endEvent = endEvent;
    }

    @Override
    public void update(float delta) {
        if (!hasEnded()) {
            if (initialInterval + speed < initialDelay) {
                initialInterval += speed;
            } else if (alpha + speed < 1 && interval + speed < delay) {
                alpha += speed;
            } else {
                interval += speed;
            }

            if (Assets.CONTROLS.controlPressed(Controls.values())) {
                endEvent.end(this);
            }

            if (alpha - speed > 0 && interval >= delay) {
                alpha -= speed;
                if (alpha - speed <= 0) {
                    endEvent.end(this);
                }
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        if (!hasEnded()) {
            spriteBatch.setColor(spriteBatch.getColor().r, spriteBatch.getColor().g, spriteBatch.getColor().b, alpha);
            spriteBatch.draw(texture, position.getX(), position.getY());
            spriteBatch.setColor(spriteBatch.getColor().r, spriteBatch.getColor().g, spriteBatch.getColor().b, 1);
        }
    }
}
