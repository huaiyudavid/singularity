package com.singularity.actors;

import com.badlogic.gdx.math.Vector3;
import com.singularity.gamemechanics.CollisionHandler;

/*
 * Abstract class for mobile game objects
 */
public class MovableActor extends Actor implements Collideable{
	
	private CollisionHandler collisionHandler;
	private Vector3 velocity, acceleration;
	
	public MovableActor(CollisionHandler ch) {
		collisionHandler = ch;
	}
	
	public void updatePosition(int delta) {
		
	}
	
	public void draw() {
		
	}
	
	public void update(int delta) {
		updatePosition(delta);
		checkCollision();
		
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		collisionHandler.checkCollision(this);
	}

	@Override
	public void onCollision(Actor a) {
		// TODO Auto-generated method stub
		
	}
}
