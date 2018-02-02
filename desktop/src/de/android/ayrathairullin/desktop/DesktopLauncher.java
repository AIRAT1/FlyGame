package de.android.ayrathairullin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.android.ayrathairullin.FlyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fly GAme";
		config.width = 360;
		config.height = 640;
		new LwjglApplication(new FlyGame(), config);
	}
}
