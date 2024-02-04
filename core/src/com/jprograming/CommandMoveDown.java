package com.jprograming;

public class CommandMoveDown implements Command {

    @Override
    public void execute(GameObject o, float deltatime) {
        o.getPosition().add(o.getVelocity());
        o.getRectangle().x = o.getPosition().x;
        o.getRectangle().y = o.getPosition().y;
    }
}
