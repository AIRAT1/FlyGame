package de.android.ayrathairullin.tools;


import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int ALPHA = 1;

    @Override
    public int getValues(Sprite sprite, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                floats[0] = sprite.getColor().a;
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void setValues(Sprite sprite, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                sprite.setColor(1, 1, 1, floats[0]);
        }
    }
}
