/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.dragonphase.lightfall.core.Game;
import com.dragonphase.lightfall.event.screen.SplashScreenEndEvent;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.input.type.Keys;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Display;

public class SplashScreen extends Screen {

    private SplashScreenEndEvent endEvent;

    private final Image image;

    private float alpha = 0;
    private float speed = 0.02f;
    private float interval = 0;
    private float initialInterval = 0;

    private final float initialDelay = 0.5f;
    private final float delay = 2;

    public SplashScreen(ScreenManager screenManager, String ref) {
        super(screenManager);

        image = new Image(Assets.getScreen(ref));

        image.setX(Display.VIEWPORT_SIZE.getWidth() / 2 - (image.getScaleX() * image.getWidth()) / 2);
        image.setY(Display.VIEWPORT_SIZE.getHeight() / 2 - (image.getScaleY() * image.getHeight()) / 2);
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
                if (alpha - speed > 0 && interval + speed < delay) {
                    interval = delay;
                } else {
                    endEvent.end(this);
                }
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
            image.draw(spriteBatch, alpha);
        }
    }
}
