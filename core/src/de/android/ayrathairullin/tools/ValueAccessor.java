package de.android.ayrathairullin.tools;


import aurelienribon.tweenengine.TweenAccessor;

public class ValueAccessor implements TweenAccessor<Value>{

    @Override
    public int getValues(Value value, int i, float[] floats) {
        floats[0] = value.getVal();
        return 1;
    }

    @Override
    public void setValues(Value value, int i, float[] floats) {
        value.setVal(floats[0]);
    }
}
