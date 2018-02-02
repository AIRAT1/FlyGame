package de.android.ayrathairullin;

import com.badlogic.gdx.Game;

import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.screens.SplashScreen;

public class FlyGame extends Game {
	
	@Override
	public void create () {
		ResourceLoader.load();
		setScreen(new SplashScreen(this));
	}
	
	@Override
	public void dispose () {
		super.dispose();
		ResourceLoader.dispose();
	}
}
