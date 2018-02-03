package de.android.ayrathairullin.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.objects.Fly;
import de.android.ayrathairullin.objects.Grass;
import de.android.ayrathairullin.objects.MoveHandler;
import de.android.ayrathairullin.objects.Web;
import de.android.ayrathairullin.tools.Value;
import de.android.ayrathairullin.tools.ValueAccessor;

public class GameRender {
    private int midPointY;
    private int midPointX;

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Fly fly;
    private MoveHandler moveHandler;
    private Grass frontGrass, backGrass;
    private Web web1, web2, web3;
    private Sprite background, grass, flyMid, spider, webUp, webDown, ready, flyLogo, gameOver,
            highScore, scoreboard, starOn, starOff, retry;
    private Animation flyAnimation;
    private Music music;

    private TweenManager manager;
    private Value alpha = new Value();
    private Color transitionColor;

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

        transitionColor = new Color();
        prepareTransition(255, 255, 255, 0.5f);
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
        drawGrass();
        drawWebs();
        drawSpiders();
        batch.end();

        drawTransition(delta);
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
        moveHandler = world.getMoveHandler();
        frontGrass = moveHandler.getFrontGrass();
        backGrass = moveHandler.getBackGrass();
        web1 = moveHandler.getWeb1();
        web2 = moveHandler.getWeb2();
        web3 = moveHandler.getWeb3();
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

    private void drawGrass() {
        batch.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
        batch.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawWebs() {
        batch.draw(webUp, web1.getX(), web1.getY(), web1.getWidth(),web1.getHeight());
        batch.draw(webDown, web1.getX(), web1.getY() + web1.getHeight() + 45, web1.getWidth(),
                midPointY + 66 - (web1.getHeight() + 45));

        batch.draw(webUp, web2.getX(), web2.getY(), web2.getWidth(),web2.getHeight());
        batch.draw(webDown, web2.getX(), web2.getY() + web2.getHeight() + 45, web2.getWidth(),
                midPointY + 66 - (web2.getHeight() + 45));

        batch.draw(webUp, web3.getX(), web3.getY(), web3.getWidth(),web3.getHeight());
        batch.draw(webDown, web3.getX(), web3.getY() + web3.getHeight() + 45, web3.getWidth(),
                midPointY + 66 - (web3.getHeight() + 45));
    }

    private void drawSpiders() {
        batch.draw(spider, web1.getX() - 1, web1.getY() + web1.getHeight() - 14, 24, 14);
        batch.draw(spider, web2.getX() - 1, web2.getY() + web2.getHeight() - 14, 24, 14);
        batch.draw(spider, web3.getX() - 1, web3.getY() + web3.getHeight() - 14, 24, 14);
    }

    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r/255f, g/255f, b/255f, 1);
        alpha.setVal(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, - 1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }

    public void drawTransition(float delta) {
        if (alpha.getVal() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(transitionColor.r, transitionColor.g, transitionColor.b, alpha.getVal());
            shapeRenderer.rect(0, 0, 136, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }
}
