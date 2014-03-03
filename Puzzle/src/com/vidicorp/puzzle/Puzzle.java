/**
	 * File: Utility.java
	 * Author:  Victor Dibia 
	 * Date last modified: Feb 10, 2012
	 *  Main logic that controls game play, timing, tile animation and difficulty selection
	 */
package com.vidicorp.puzzle;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.sound.SoundEngine;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

// AtlasTest, there is a downloadable demo here:
// http://code.google.com/p/cocos2d-android-1/downloads/detail?name=CCTextureAtlas%20and%20CCBitmapFontAtlas.3gp&can=2&q=#makechanges
//
public class Puzzle extends Activity {
	// private static final String LOG_TAG = AtlasTest.class.getSimpleName();
	private CCGLSurfaceView mGLSurfaceView;


	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		//WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		mGLSurfaceView = new CCGLSurfaceView(this);
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(mGLSurfaceView);
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
		setContentView(mGLSurfaceView);


	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate if the process is
		// killed and restarted.
		savedInstanceState.putInt("Score", PuzzleLayer.score);	

		// etc.
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.

		PuzzleLayer.score= savedInstanceState.getInt("Score");
		//String myString = savedInstanceState.getString("MyString");
	}

	@Override
	public void onStart() {
		super.onStart();

		// show FPS
		CCDirector.sharedDirector().setDisplayFPS(true);

		// frames per second
		//CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);

		CCScene scene = CCScene.node();
		try {
			scene.addChild((CCLayer) PuzzleLayer.class.newInstance());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		CCDirector.sharedDirector().runWithScene(scene);


	}



	@Override
	public void onPause() {
		super.onPause();

		CCDirector.sharedDirector().onPause();
		SoundEngine.sharedEngine().pauseSound();
		PuzzleLayer.timer.cancel();
	}

	@Override
	public void onResume() {
		super.onResume();

		CCDirector.sharedDirector().onResume();
		SoundEngine.sharedEngine().resumeSound();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		CCDirector.sharedDirector().end();

		// CCTextureCache.sharedTextureCache().removeAllTextures();
	}

}



