package com.singularity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.singularity.main.SphereTest;
import com.singularity.main.Andya;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Singularity";
		new LwjglApplication(new SphereTest(), config);
		config.width = 720;
		config.height = 480;
		new LwjglApplication(new Andya(), config);
	}
}
