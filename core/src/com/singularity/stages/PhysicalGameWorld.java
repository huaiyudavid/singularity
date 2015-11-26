package com.singularity.stages;

import com.singularity.actors.Actor;
import com.singularity.gamemechanics.CollisionHandler;
public class PhysicalGameWorld {

	private Actor activeMoveableActors[];
	private CollisionHandler collisionHandler;
	
	public PhysicalGameWorld() {
		
	}
	
	public void updateMoveableActors(int delta)
	{
		for(Actor a : activeMoveableActors)
		{
			a.update(delta);
		}
	}
}
