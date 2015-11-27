package com.singularity.stages;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{
	
	private PhysicalGameWorld physicalGameWorld;
	private PhysicalWorldRenderer physicalWorldRenderer;
	
	public GameScreen() {
		physicalGameWorld = new PhysicalGameWorld();
		physicalWorldRenderer = new PhysicalWorldRenderer(physicalGameWorld);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
