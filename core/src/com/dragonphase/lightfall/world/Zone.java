/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.dragonphase.lightfall.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Zone implements Sector {

    // TODO: Set viewport bounds to current area bounds or use a new viewport for each Zone.

    private List<Area> areas;

    private List<Entity> entities;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    public Zone() {
        areas = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public List<Area> getAreas() {
        return areas;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public void update(float delta) {
        for (Entity entity : getEntities()) {
            entity.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        for (Entity entity : getEntities()) {
            entity.draw(spriteBatch, delta);
        }
        
        /*for (MapLayer layer : tiledMap.getLayers()) {
            tiledMapRenderer.renderTileLayer((TiledMapTileLayer) layer);
        }*/

        /**
         *  TODO: Silhouettes with shaders, using topmost MapLayer(s) as a texture mask:
         *      http://www.java-gaming.org/index.php/topic,26286
         *      http://stackoverflow.com/questions/24099103/libgdx-changing-sprite-color-while-hurt
         */
    }
}
