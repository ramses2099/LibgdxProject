package com.jprograming;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Bucket extends GameObject {


    public Bucket(Texture texture, Vector2 position, Vector2 size) {
        super(texture);
        setPosition(position);
        setGameObjectType(GameObjectType.PLAYER);
        setSize(size);
        setVelocity(new Vector2(1.0f, -2.0f));
        setRectangle(new Rectangle(position.x, position.y, size.x, size.y));

    }


    @Override
    protected boolean overlaps(GameObject other) {
        return getRectangle().overlaps(other.getRectangle());
    }

    @Override
    protected void update(float delta) {

    }

    @Override
    protected void draw(Batch batch) {
        batch.draw(getSprite(), getPosition().x, getPosition().y, getSize().x, getSize().y);
    }
}