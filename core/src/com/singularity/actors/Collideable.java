package com.singularity.actors;

public interface Collideable {

	public void checkCollision();
	// gets called by a CollisionHandler
	public void onCollision(Actor a);
	
}
