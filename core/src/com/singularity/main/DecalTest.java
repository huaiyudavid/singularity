package com.singularity.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.GroupStrategy;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.singularity.utils.DecalSprite;

public class DecalTest extends ApplicationAdapter {
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public Environment environment;
	public DecalSprite wayne;
	public DecalBatch batch;
	public SpriteBatch spriteBatch;
	public GroupStrategy strategy;
	public ShapeRenderer shapes;
	public FrameBuffer frameBuffer;
	public Texture waynePic, texture;
	public TextureRegion textureRegion;
	
	@Override
	public void create() {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0f, 0f, 20f);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		
		waynePic = new Texture(Gdx.files.internal("wayne.png"));

		strategy = new CameraGroupStrategy(cam);
		batch = new DecalBatch(strategy);
		spriteBatch = new SpriteBatch();
		shapes = new ShapeRenderer();
		
		//frameBuffer = new FrameBuffer(Format.RGBA4444,waynePic.getWidth(),waynePic.getHeight(),true);
		frameBuffer = new FrameBuffer(Format.RGBA4444,200,200,true);
	}

	@Override
	public void render() {
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		frameBuffer.begin();
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


		// 3. set the function to LESS
		Gdx.gl.glDepthFunc(GL20.GL_LESS);

		// 4. enable depth writing
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

		// 5. Enable depth writing, disable RGBA color writing
		Gdx.gl.glDepthMask(true);
		Gdx.gl.glColorMask(false, false, false, false);

		// 6. render your primitive shapes
		shapes.begin(ShapeType.Filled);

		shapes.setColor(1f, 0f, 0f, 1f);
		shapes.circle(frameBuffer.getWidth()/2, frameBuffer.getHeight()/2, 100);

		shapes.end();
		
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		
		// 8. Enable RGBA color writing
		// (SpriteBatch.begin() will disable depth mask)
		Gdx.gl.glColorMask(true, true, true, true);

		// 9. Make sure testing is enabled.
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

		// 10. Now depth discards pixels outside our masked shapes
		Gdx.gl.glDepthFunc(GL20.GL_EQUAL);

		spriteBatch.begin();
		spriteBatch.draw(waynePic, 0, 0);
		
		spriteBatch.end();
		
				
		frameBuffer.end();
		
		
		texture = frameBuffer.getColorBufferTexture();
		textureRegion = new TextureRegion(texture);
        // and.... FLIP!  V (vertical) only
        textureRegion.flip(false, true);
        
		Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
		Gdx.gl.glDepthFunc(GL20.GL_LESS);

        
        spriteBatch.begin();
        spriteBatch.draw(textureRegion, Gdx.graphics.getWidth()/2 - textureRegion.getRegionWidth()/2, Gdx.graphics.getHeight()/2 - textureRegion.getRegionHeight()/2 );
        spriteBatch.end();
        
        wayne = new DecalSprite().build(textureRegion);
        wayne.sprite.setDimensions(20, 20);
        wayne.sprite.setPosition(7, 7, 0);
        
        batch.add(wayne.sprite);
        batch.flush();
	}

	@Override
	public void dispose() {
		Gdx.gl20.glDisable(GL20.GL_DEPTH_TEST);
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		batch.dispose();
		shapes.dispose();
		frameBuffer.dispose();
	}
}
