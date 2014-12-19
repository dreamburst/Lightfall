/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonphase.lightfall.core.LogicBase;
import com.dragonphase.lightfall.entity.component.Component;
import com.dragonphase.lightfall.util.Assets;
import com.dragonphase.lightfall.util.Direction;
import com.dragonphase.lightfall.util.Size;
import com.dragonphase.lightfall.util.Vector;

import java.util.*;

public abstract class Entity implements LogicBase {

    private Vector<Float> position;
    private Vector<Float> velocity;
    private Vector<Float> speed;

    private Map<EntityState, Map<Direction, Animation>> animations;

    private Set<Component> components;

    private Size size;

    private EntityState state;
    private Direction direction;

    private float elapsedTime = 0;

    public Entity(String texture, Size size) {
        this(texture, size, new Vector<>(0f, 0f), EntityState.NONE, Direction.UP);
    }

    public Entity(String texture, Size size, Vector<Float> position, EntityState state, Direction direction) {
        setSize(size);
        setPosition(position);
        setVelocity(new Vector<>(0f, 0f));
        setSpeed(new Vector<>(1f, 1f));

        components = new HashSet<>();

        loadStates(texture, state, direction);
    }

    private void loadStates(String texture, EntityState defaultState, Direction defaultDirection) {
        Map<EntityState, TextureAtlas.AtlasRegion> states = Assets.getSprite(texture);

        if (states.isEmpty()) {
            states.put(EntityState.NONE, Assets.getSpriteSheet(texture));
        }

        animations = new EnumMap<>(EntityState.class);

        for (Map.Entry<EntityState, TextureAtlas.AtlasRegion> entry : states.entrySet()) {
            Map<Direction, Animation> directions = new EnumMap<>(Direction.class);

            TextureRegion[][] regions = entry.getValue().split(size.getWidth(), size.getHeight());

            for (Direction direction : Direction.values()) {
                if (direction.getId() > regions.length - 1) {
                    break;
                }
                directions.put(direction, new Animation(Assets.ANIMATION_FRAME_DURATION, regions[direction.getId()]));
            }

            animations.put(entry.getKey(), directions);
        }

        setState(!states.containsKey(EntityState.NONE) ? defaultState : EntityState.IDLE);
        setDirection(defaultDirection);
    }

    public Vector<Float> getPosition() {
        return position;
    }

    public void setPosition(Vector<Float> position) {
        this.position = position;
    }

    public Vector<Float> getCenterPosition() {
        return new Vector<>(
                getPosition().getX() + getSize().getWidth() / 2,
                getPosition().getY() + getSize().getHeight() / 2
        );
    }

    public Vector<Float> getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector<Float> velocity) {
        this.velocity = velocity;
    }

    public Vector<Float> getSpeed() {
        return speed;
    }

    public void setSpeed(Vector<Float> speed) {
        this.speed = speed;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Animation getAnimation() {
        try {
            return animations.get(getState()).get(getDirection());
        } catch (Exception exception) {
            return animations.get(EntityState.NONE).get(Direction.UP);
        }
    }

    public Set<Component> getComponents() {
        return components;
    }

    public <T extends Component> T getComponent(Class<T> clazz) {
        for (Component component : getComponents()) {
            if (component.getClass() == clazz) {
                return clazz.cast(component);
            }
        }

        return null;
    }

    public void addComponent(Component component) {
        component.setOwner(this);
        components.add(component);
    }

    public <T extends Component> void removeComponent(Class<T> clazz) {
        Iterator<Component> components = getComponents().iterator();
        while (components.hasNext()) {
            Component component = components.next();
            if (component.getClass().isInstance(clazz)) {
                components.remove();
            }
        }
    }

    @Override
    public void update(float delta) {
        elapsedTime += delta;

        for (Component component : getComponents()) {
            component.update(delta);
        }

        getPosition().set(getPosition().getX() + getVelocity().getX(), getPosition().getY() + getVelocity().getY());
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        spriteBatch.draw(
                getAnimation().getKeyFrame(elapsedTime, true),
                getPosition().getX(),
                getPosition().getY()
        );
    }
}
