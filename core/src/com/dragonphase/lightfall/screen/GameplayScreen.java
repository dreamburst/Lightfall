/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragonphase.lightfall.entity.EntityController;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.entity.living.Player;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.*;

public class GameplayScreen extends Screen {

    private EntityController controller;

    private Player player;

    private boolean paused;

    private CenterCamera camera;

    public GameplayScreen(ScreenManager screenManager) {
        super(screenManager);

        // TEST DATA - Create Player; camera:

        controller = new EntityController(Assets.CONTROLS);

        player = new Player("drifter", new Size(32, 32), new Vector<>(64f, 64f), EntityState.IDLE, Direction.UP, 3f);

        controller.setControllable(player);

        camera = new CenterCamera(new Rectangle(0, 0, 500, 500), 1);
    }

    @Override
    public void update(float delta) {
        if (controller.getControlMap().controlPressed(Controls.PAUSE)) {
            paused = !paused;
        }

        if (!paused) {
            player.update(delta);
        }

        camera.setTarget(player.getCenterPosition());
        camera.update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        camera.draw(spriteBatch, delta);
        player.draw(spriteBatch, delta);
    }
}
