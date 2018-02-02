package de.android.ayrathairullin.loader;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceLoader {
    private static TextureAtlas atlas;
    private static Sprite logo, flyAndSpiders, background, grass, fly1, fly2, fly3, spider, webUp, webDown, playButtonUp, playButtonDown, ready, retry, gameOver, scoreboard, starOn, starOff, highScore;
    public static Animation flyAnimation;
    public static Sound dead, flap, coin, fall;
    public static Music fly;
    public static BitmapFont font, shadow, whiteFont;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("texture/texture.pack"), true);
        logo = new Sprite(atlas.findRegion("logo"));
        playButtonUp = new Sprite(atlas.findRegion(""));
        playButtonDown = new Sprite(atlas.findRegion(""));
        ready = new Sprite(atlas.findRegion(""));
        retry = new Sprite(atlas.findRegion(""));
        gameOver = new Sprite(atlas.findRegion(""));
        scoreboard = new Sprite(atlas.findRegion(""));
        starOn = new Sprite(atlas.findRegion(""));
        starOff = new Sprite(atlas.findRegion(""));
        highScore = new Sprite(atlas.findRegion(""));
        flyAndSpiders = new Sprite(atlas.findRegion(""));
        background = new Sprite(atlas.findRegion(""));
        grass = new Sprite(atlas.findRegion(""));
        fly1 = new Sprite(atlas.findRegion(""));
        fly2 = new Sprite(atlas.findRegion(""));
        fly3 = new Sprite(atlas.findRegion(""));
        spider = new Sprite(atlas.findRegion(""));
        webUp = new Sprite(atlas.findRegion(""));
        webDown = new Sprite(atlas.findRegion(""));
    }
}
