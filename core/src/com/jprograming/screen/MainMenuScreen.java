package com.jprograming.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jprograming.LibgdxProject;

public class MainMenuScreen implements Screen {

    final LibgdxProject game;
    OrthographicCamera camera;

    public MainMenuScreen(final LibgdxProject game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,450);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch,"Welcome to Drop!!", 100,150);
        game.font.draw(game.batch,"Tap anywhere to begin",100,100);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
