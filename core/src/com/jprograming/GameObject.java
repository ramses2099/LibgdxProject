package com.jprograming;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 size;
    protected Rectangle rectangle;
    protected Sprite sprite;
    protected boolean visible = true;
    protected GameObjectType gameObjectType;

    public GameObject(Texture texture) {
        sprite = new Sprite(texture);
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public void setGameObjectType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    protected abstract boolean overlaps(GameObject other);

    protected abstract void update(float delta);

    protected abstract void draw(Batch batch);
}
