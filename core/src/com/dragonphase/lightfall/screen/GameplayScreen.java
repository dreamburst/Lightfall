/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.dragonphase.lightfall.entity.EntityState;
import com.dragonphase.lightfall.entity.living.Player;
import com.dragonphase.lightfall.input.InputController;
import com.dragonphase.lightfall.input.control.Controls;
import com.dragonphase.lightfall.util.*;
import com.dragonphase.lightfall.world.Area;
import com.dragonphase.lightfall.world.Sector;
import com.dragonphase.lightfall.world.Zone;

public class GameplayScreen extends Screen {

    private Sector sector;

    private boolean paused;

    public GameplayScreen(ScreenManager screenManager) {
        super(screenManager);

        // TEST DATA - Sectors, Areas and Area Focus:
        
        setSector(new Zone());
        
        Area area = new Area(new Rectangle(0, 0, 500, 350));
        area.focus();
        getSector(Zone.class).getAreas().add(area);

        Player player = new Player("drifter", new Size(32, 32), new Vector<>(64f, 64f), EntityState.IDLE, Direction.UP, 3f);
        getSector(Zone.class).getEntities().add(player);
        Display.VIEWPORT.setTarget(player);

        InputController.getInstance().setControllable(player);
    }
    
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    public Sector getSector() {
        return sector;
    }
    
    public <T extends Sector> T getSector(Class<T> type) {
        return type.cast(getSector());
    }

    @Override
    public void update(float delta) {
        if (InputController.getInstance().getControlMap().controlPressed(Controls.PAUSE)) {
            paused = !paused;
        }

        if (!paused) {
            getSector().update(delta);
        }
    }

    ShapeRenderer debugRenderer = new ShapeRenderer();

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        getSector().draw(spriteBatch, delta);
        
        spriteBatch.end();

        // Debug render the camera bounds.

        debugRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        debugRenderer.setColor(Color.RED);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.rect(Display.VIEWPORT.getBounds().getX(), Display.VIEWPORT.getBounds().getY(), Display.VIEWPORT.getBounds().getWidth(), Display.VIEWPORT.getBounds().getHeight());
        debugRenderer.end();

        spriteBatch.begin();
    }
}
