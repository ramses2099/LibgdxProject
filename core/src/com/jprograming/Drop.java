package com.jprograming;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Drop extends GameObject {


    Command command;
    public Drop(Texture texture, Vector2 position, Vector2 size, Command command) {
        super(texture);
        this.command = command;
        setPosition(position);
        setGameObjectType(GameObjectType.ENEMY);
        setSize(size);
        setRectangle(new Rectangle(position.x, position.y, size.x, size.y));
    }

    @Override
    protected boolean overlaps(GameObject other) {
        return getRectangle().overlaps(other.getRectangle());
    }

    @Override
    protected void update(float delta) {
      this.command.execute(this, delta);
    }

    @Override
    protected void draw(Batch batch) {
        batch.draw(getSprite(), getPosition().x, getPosition().y);
    }
}
