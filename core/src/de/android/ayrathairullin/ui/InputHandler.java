package de.android.ayrathairullin.ui;


import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;

import de.android.ayrathairullin.game.GameWorld;
import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.objects.Fly;

public class InputHandler implements InputProcessor{
    private Fly fly;
    private List<PlayButton> menuButtons;
    private PlayButton playButton;
    private GameWorld gameWorld;
    private float scaleFactorX, scaleFactorY;

    public InputHandler(GameWorld gameWorld, float scaleFactorX, float scaleFactorY) {
        this.gameWorld = gameWorld;
        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        fly = gameWorld.getFly();
        int midPointX = gameWorld.getMidPointX();
        int midPointY = gameWorld.getMidPointY();
        menuButtons = new ArrayList<PlayButton>();
        playButton = new PlayButton(midPointX - 14.5f,
                midPointY + 10, 29, 29, ResourceLoader.playButtonUp, ResourceLoader.playButtonDown);
        menuButtons.add(playButton);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (gameWorld.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        }else if (gameWorld.isReady()) {
            gameWorld.start();
            fly.onClick();
        }else if (gameWorld.isRunning()) {
            fly.onClick();
        }

        if (gameWorld.isGameOver() || gameWorld.isHighScore()) {
            gameWorld.restart();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (gameWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                gameWorld.ready();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private int scaleX(int screenX) {
        return (int)(screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int)(screenY / scaleFactorY);
    }

    public List<PlayButton> getMenuButtons() {
        return menuButtons;
    }
}
