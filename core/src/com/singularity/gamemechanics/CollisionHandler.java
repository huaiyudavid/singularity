package com.singularity.gamemechanics;

import com.singularity.actors.Actor;
import com.singularity.stages.PhysicalGameWorld;

public class CollisionHandler {

	private PhysicalGameWorld physicalGameWorld;
	
	public CollisionHandler(PhysicalGameWorld w) {
		physicalGameWorld = w;
	}
	/*
	 * This will traverse the array of current moveable objects in the game world,
	 * see if any are colliding Actor a, and call the onCollision method for the Actor a
	 */
	public Boolean checkCollision(Actor a) {
		return false;
	}
}
