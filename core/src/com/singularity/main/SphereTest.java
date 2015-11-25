package com.singularity.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;

public class SphereTest extends ApplicationAdapter {
	public PerspectiveCamera cam;
	   public CameraInputController camController;
	   public Model model;
	   public Environment environment;
	   public ModelBatch modelBatch;
	   public Array<ModelInstance> instances = new Array<ModelInstance>();
	   public Texture texture;

	   @Override
	   public void create () {
		   cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	       cam.position.set(2f, 2f, 2f);
	       cam.lookAt(0,0,0);
	       cam.near = 1f;
	       cam.far = 300f;
	       cam.update();

	       camController = new CameraInputController(cam);
	       Gdx.input.setInputProcessor(camController);

	       ModelBuilder modelBuilder = new ModelBuilder();
	       model = modelBuilder.createSphere(2f, 2f, 2f, 20, 20, 
	         new Material(),
	         Usage.Position | Usage.Normal | Usage.TextureCoordinates);
	       
	       modelBatch = new ModelBatch();
	       
	       ModelInstance sphereInstance = new ModelInstance(model);
	       instances.add(sphereInstance);
	       
	       texture = new Texture(Gdx.files.internal("wayne.png"));
	       
	       TextureAttribute textattr = new TextureAttribute(TextureAttribute.Diffuse, texture);
	       Material material = sphereInstance.materials.get(0);
	       material.set(textattr);
	   }

	   @Override
	   public void render () {
	       camController.update();

	       Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

	       modelBatch.begin(cam);
	       modelBatch.render(instances, environment);
	       modelBatch.end();
	   }

	   @Override
	   public void dispose () {
	       model.dispose();
	       modelBatch.dispose();
	       instances.clear();
	   }
}
