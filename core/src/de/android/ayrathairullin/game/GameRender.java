package de.android.ayrathairullin.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.objects.Fly;

public class GameRender {
    private int midPointY;
    private int midPointX;

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Fly fly;
    private Sprite background, grass, flyMid, spider, webUp, webDown, ready, flyLogo, gameOver,
            highScore, scoreboard, starOn, starOff, retry;
    private Animation flyAnimation;
    private Music music;

    public GameRender(GameWorld world, int gameHeight, int midPointY, int midPointX) {
        this.world = world;
        this.midPointX = midPointX;
        this.midPointY = midPointY;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, gameHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(186 / 255.0f, 224 / 255.0f, 213 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);
        shapeRenderer.setColor(167 / 255.0f, 211 / 255.0f, 152 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);
        shapeRenderer.setColor(75 / 255.0f, 136 / 255.0f, 178 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 53);
        shapeRenderer.end();

        batch.begin();
        batch.disableBlending();
        batch.draw(background, 0, midPointY + 23, 136, 43);
        batch.enableBlending();
        drawFly(runTime);
        batch.end();
    }

    private void initAssets() {
        background = ResourceLoader.background;
        grass = ResourceLoader.grass;
        flyAnimation = ResourceLoader.flyAnimation;
        flyMid = ResourceLoader.fly2;
        spider = ResourceLoader.spider;
        webUp = ResourceLoader.webUp;
        webDown = ResourceLoader.webDown;
        ready = ResourceLoader.ready;
        flyLogo = ResourceLoader.flyAndSpiders;
        gameOver = ResourceLoader.gameOver;
        highScore = ResourceLoader.highScore;
        scoreboard = ResourceLoader.scoreboard;
        retry = ResourceLoader.retry;
        starOn = ResourceLoader.starOn;
        starOff = ResourceLoader.starOff;
        music = ResourceLoader.fly;
    }

    private void initGameObjects() {
        fly = world.getFly();
    }

    private void drawFly(float runTime) {
        if (fly.notFlap()) {
            batch.draw(flyMid, fly.getX(), fly.getY(), fly.getWidth() / 2f, fly.getHeight() / 2f,
                    fly.getWidth(), fly.getHeight(), 1, 1, fly.getRotation());
        }else {
            batch.draw((TextureRegion) flyAnimation.getKeyFrame(runTime), fly.getX(), fly.getY(), fly.getWidth() / 2f,
                    fly.getHeight() / 2f, fly.getWidth(), fly.getHeight(), 1, 1, fly.getRotation());
        }
    }
}
