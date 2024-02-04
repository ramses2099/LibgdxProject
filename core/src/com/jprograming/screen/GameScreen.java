package com.jprograming.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.jprograming.*;

import javax.sound.midi.Soundbank;
import java.util.Iterator;
import java.util.List;

public class GameScreen implements Screen {

    final LibgdxProject game;
    OrthographicCamera camera;
    Texture texDrop, texBucket;
    Sound dropSound;
    Music rainMusic;
    Bucket bucket;
    long lastDropTime;
    int dropGathered;

    GameObjectManager gameObjectManager;

    public GameScreen(final LibgdxProject game) {
        this.game = game;

        texDrop = new Texture(Gdx.files.internal("drop.png"));
        texBucket = new Texture(Gdx.files.internal("bucket.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 450);

        /*---------------------------------------------------------------
        Game object
        ---------------------------------------------------------------*/
        gameObjectManager = new GameObjectManager();
        bucket = new Bucket(texBucket, new Vector2(Gdx.graphics.getWidth() / 2f, (Gdx.graphics.getHeight() / 2f - 225)), new Vector2(64f, 64f));
        gameObjectManager.addGameObject(bucket);

        sapwnRainDrop();
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, String.format("Drop Collected : %d", dropGathered), 10, 440);

        /*---------------------------------------------------------------
        Draw Game object
        ---------------------------------------------------------------*/
        gameObjectManager.draw(game.batch);
        /*---------------------------------------------------------------
        Update Game object
        ---------------------------------------------------------------*/
        gameObjectManager.update(Gdx.graphics.getDeltaTime());

        game.batch.end();
         /*---------------------------------------------------------------
        Inputhandler bucket
        ---------------------------------------------------------------*/
        update(Gdx.graphics.getDeltaTime());




        /*---------------------------------------------------------------
        Spawn raindrop
        ---------------------------------------------------------------*/
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            sapwnRainDrop();
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
        dropSound.dispose();
        rainMusic.dispose();
        texDrop.dispose();
        texBucket.dispose();
    }

    private void sapwnRainDrop() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480f;
        Drop drop;
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
            drop = new Drop(texDrop, new Vector2(x, y),
                    new Vector2(64f, 64f), new CommandDiffMove());
            drop.setVelocity(new Vector2(3f, -2f));
            gameObjectManager.addGameObject(drop);
        }

        drop = new Drop(texDrop, new Vector2(x, y),
                new Vector2(64f, 64f), new CommandMoveDown());
        drop.setVelocity(new Vector2(0f, -2.0f));

        gameObjectManager.addGameObject(drop);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void update(float deltatime) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bucket.getPosition().x -= bucket.getPosition().x * deltatime;
            bucket.getRectangle().x -= bucket.getPosition().x * deltatime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bucket.getPosition().x += bucket.getPosition().x * deltatime;
            bucket.getRectangle().x += bucket.getPosition().x * deltatime;
        }

        if (bucket.getPosition().x <= 0) {
            bucket.getPosition().x = 0f;
            bucket.getRectangle().x = 0f;
        }

        if (bucket.getPosition().x > 800 - 64) {
            bucket.getPosition().x = 800 - 64;
            bucket.getRectangle().x = 800 - 64;
        }

        /*---------------------------------------------------------------
        Collision detected
        ---------------------------------------------------------------*/
        for (GameObject gameObject : gameObjectManager.getGameObjectList()) {

            if (gameObject.getGameObjectType() == GameObjectType.ENEMY) {

                if (gameObject.getPosition().y + 64 < 0) {
                    gameObject.setVisible(false);
                }

                if (gameObject.getRectangle().overlaps(bucket.getRectangle())) {
                    dropGathered++;
                    dropSound.play();
                    gameObject.setVisible(false);
                }

            }
        }

    }

}
