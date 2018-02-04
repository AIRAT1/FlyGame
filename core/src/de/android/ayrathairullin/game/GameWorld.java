package de.android.ayrathairullin.game;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.objects.Fly;
import de.android.ayrathairullin.objects.MoveHandler;

public class GameWorld {
    private Fly fly;
    private MoveHandler moveHandler;
    private Rectangle ground;

    private int midPointY;
    private int midPointX;
    private int score = 0;
    private float runTime = 0;

    private GameRender render;
    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY, int midPointX) {
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        currentState = GameState.MENU;
        fly = new Fly(33, midPointY - 5, 17, 12);
        moveHandler = new MoveHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        runTime += delta;
        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        fly.updateReady(runTime);
        moveHandler.updateReady(delta);
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        fly.update(delta);
        moveHandler.update(delta);

        if (moveHandler.collides(fly) && fly.isAlive()) {
            moveHandler.stop();
            fly.die();
            fly.cling();
            ResourceLoader.fall.play();
            render.prepareTransition(255, 255, 255, 0.3f);
            currentState = GameState.GAMEOVER;
            highScore();
        }

        if (Intersector.overlaps(fly.getCircle(), ground)) {
            if (fly.isAlive()) {
                ResourceLoader.dead.play();
                fly.die();
                render.prepareTransition(255, 255, 255, 0.3f);
            }
            moveHandler.stop();
            fly.cling();
            currentState = GameState.GAMEOVER;
            highScore();
        }
    }

    private void highScore() {
        if (score > ResourceLoader.getHighScore()) {
            ResourceLoader.setHighScore(score);
        }
        currentState = GameState.HIGHSCORE;
    }

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }

    public Fly getFly() {
        return fly;
    }

    public void setRender(GameRender render) {
        this.render = render;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public void ready() {
        currentState = GameState.READY;
        render.prepareTransition(0, 0, 0, 1f);
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        score = 0;
        fly.onRestart(midPointY - 5);
        moveHandler.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public int getMidPointY() {
        return midPointY;
    }

    public int getMidPointX() {
        return midPointX;
    }
}
