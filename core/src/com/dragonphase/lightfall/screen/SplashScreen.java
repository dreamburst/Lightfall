/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.dragonphase.lightfall.util.Assets;

public class SplashScreen extends Screen {

    private SplashScreenEndEvent endEvent;

    private Texture splashTexture;
    private Image image;

    private float alpha = 0;
    private float speed = 0.02f;
    private float interval = 0;
    private float delay = 2;

    private float initialInterval = 0;
    private float initialDelay = 0.5f;

    public SplashScreen(ScreenManager screenManager, String ref) {
        super(screenManager);

        splashTexture = new Texture(Gdx.files.internal("textures/screens/" + ref + ".png"));
        image = new Image(splashTexture);
        image.setX(Assets.SCREEN_SIZE.getWidth() / 2 - (image.getScaleX() * image.getWidth()) / 2);
        image.setY(Assets.SCREEN_SIZE.getHeight() / 2 - (image.getScaleY() * image.getHeight()) / 2);
    }

    public boolean hasEnded() {
        return alpha <= 0 + speed && interval >= delay;
    }

    public void onEnd(SplashScreenEndEvent endEvent) {
        this.endEvent = endEvent;
    }

    public void update(float delta) {
        if (!hasEnded()) {
            if (initialInterval + speed < initialDelay) {
                initialInterval += speed;
                return;
            }

            if (alpha + speed < 1 && interval + speed < delay) {
                alpha += speed;
            } else {
                interval += speed;
            }

            if (alpha - speed > 0 && interval >= delay) {
                alpha -= speed;
                if (alpha - speed <= 0) {
                    endEvent.end(this);
                }
            }
        }
    }

    public void draw(SpriteBatch spriteBatch, float delta) {
        if (!hasEnded()) {
            image.draw(spriteBatch, alpha);
        }
    }
}
