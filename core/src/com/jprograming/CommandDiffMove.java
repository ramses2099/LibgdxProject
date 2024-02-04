package com.jprograming;

import com.badlogic.gdx.utils.TimeUtils;

public class CommandDiffMove implements Command{
    long lastTimeMove;
    public CommandDiffMove(){
        lastTimeMove = TimeUtils.nanoTime();
    }
    @Override
    public void execute(GameObject o, float deltatime) {
        if (TimeUtils.nanoTime() - lastTimeMove > 1000000000) {
            o.getVelocity().x *= -1;
            lastTimeMove = TimeUtils.nanoTime();
        }
        o.getPosition().add(o.getVelocity());

        o.getRectangle().x = o.getPosition().x;
        o.getRectangle().y = o.getPosition().y;
    }
}
