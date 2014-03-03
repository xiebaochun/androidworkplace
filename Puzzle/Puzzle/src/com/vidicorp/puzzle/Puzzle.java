/**
	 * File: Utility.java
	 * Author:  Victor Dibia 
	 * Date last modified: Feb 10, 2012
	 *  Main logic that controls game play, timing, tile animation and difficulty selection
	 */
package com.vidicorp.puzzle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemLabel;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCBitmapFontAtlas;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.opengl.CCTextureAtlas;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.utils.CCFormatter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

// AtlasTest, there is a downloadable demo here:
// http://code.google.com/p/cocos2d-android-1/downloads/detail?name=CCTextureAtlas%20and%20CCBitmapFontAtlas.3gp&can=2&q=#makechanges
//
public class Puzzle extends Activity {
	// private static final String LOG_TAG = AtlasTest.class.getSimpleName();
	private CCGLSurfaceView mGLSurfaceView;


	public static  ArrayList<CCNodeExt> spriteList = new ArrayList<CCNodeExt>();
	public static  int[] tileNumbers ;  
	public static  int  tileIndex ;  
	private static CGPoint emptyPosition  ;
	private static int score = 0 ;
	private static CGSize screenSize;
	private static int TILE_SQUARE_SIZE = 150 ;
	private static int NUM_ROWS = 0 ;
	private static int NUM_COLUMNS = 0 ;
	private static Context appcontext ;
	private static Timer timer ; 
	private static float time = 0 ;
	private static int moves = 0 ;
	
	private static int SCORE_LABEL_TAG = 100 ;
	private static int BEST_SCORE_LABEL_TAG = 102 ;
	private static int TIMER_LABEL_TAG = 103 ;
	private static int TILE_NODE_TAG = 105 ;
	private static int MOVES_LABEL_TAG = 106 ;

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
		savedInstanceState.putInt("Score", score);	

		// etc.
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore UI state from the savedInstanceState.
		// This bundle has also been passed to onCreate.

		score= savedInstanceState.getInt("Score");
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
		timer.cancel();
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


	static class PuzzleLayer extends CCLayer {
		CCTextureAtlas textureAtlas;

		public PuzzleLayer() {
			super();


			screenSize = CCDirector.sharedDirector().winSize();

			//Add Background Sprite Image
			CCSprite background = CCSprite.sprite("background.jpg");
			background.setPosition(CGPoint.ccp(screenSize.width / 2.0f, screenSize.height / 2.0f));
			addChild(background,-5);

			//Add Score Label

			CCBitmapFontAtlas scoreLabel = CCBitmapFontAtlas.bitmapFontAtlas ("Tap Tiles to Begin", "bionic.fnt");
			scoreLabel.setScale(1.3f);
			scoreLabel.setAnchorPoint(CGPoint.ccp(0,1));
			scoreLabel.setPosition( CGPoint.ccp( 25, screenSize.height - 10));
			addChild(scoreLabel,-2, SCORE_LABEL_TAG);


			//Add Best Label
			CCBitmapFontAtlas bestScoreLabel = CCBitmapFontAtlas.bitmapFontAtlas ("BEST : 00:00", "bionic.fnt");
			bestScoreLabel.setAnchorPoint(CGPoint.ccp(0,1));
			bestScoreLabel.setScale(0.8f);
			bestScoreLabel.setColor(ccColor3B.ccc3(255, 242, 75));
			bestScoreLabel.setPosition( CGPoint.ccp(25, screenSize.height - 25 - (scoreLabel.getContentSize().height)));
			addChild(bestScoreLabel,-2, BEST_SCORE_LABEL_TAG);

			// Add Timer Label
			CCBitmapFontAtlas timerLabel = CCBitmapFontAtlas.bitmapFontAtlas ("00:00", "bionic.fnt");
			timerLabel.setScale(1.5f);
			timerLabel.setAnchorPoint(1f,1f);
			timerLabel.setColor(ccColor3B.ccc3(50, 205, 50));
			timerLabel.setPosition(CGPoint.ccp(screenSize.width - 25 , screenSize.height - 10 ));
			addChild(timerLabel,-2,TIMER_LABEL_TAG);

			// Add Moves Label
			CCBitmapFontAtlas movesLabel = CCBitmapFontAtlas.bitmapFontAtlas ("Moves : 000", "bionic.fnt");
			movesLabel.setScale(0.8f);
			movesLabel.setAnchorPoint(1f,0f);
			movesLabel.setColor(ccColor3B.ccc3(50, 205, 50));
			movesLabel.setPosition(CGPoint.ccp(screenSize.width - 25, timerLabel.getPosition().y - timerLabel.getContentSize().height - 10 -  timerLabel.getContentSize().height));
			addChild(movesLabel,-2,MOVES_LABEL_TAG);


			//Add Menu
			CCBitmapFontAtlas easyFontAtlas = CCBitmapFontAtlas.bitmapFontAtlas( "Easy"  , "bionic.fnt");
			CCMenuItemLabel easyItem = CCMenuItemLabel.item(easyFontAtlas , this, "resetTilesEasyCallback");
			easyItem.setScale(1.3f);
			CCBitmapFontAtlas hardFontAtlas = CCBitmapFontAtlas.bitmapFontAtlas( "Hard"  , "bionic.fnt");
			CCMenuItemLabel hardItem = CCMenuItemLabel.item(hardFontAtlas , this, "resetTilesHardCallback");
			hardItem.setScale(1.3f);

			CCMenu menu = CCMenu.menu() ;
			menu.addChild(easyItem);
			menu.addChild(hardItem);
			menu.alignItemsVertically(20);
			final CGPoint p = menu.getPositionRef();
			menu.setPosition(CGPoint.ccpSub(p, CGPoint.ccp(-420,0))) ;
			//menu.setPosition(CGPoint.ccp(screenSize.width - menu.getContentSize().width/2, screenSize.height/2));
			addChild(menu);

			NUM_ROWS = 3 ;
			NUM_COLUMNS = 3 ;
			generateTiles();
			timer = new Timer();
			time = 0 ;
			moves = 0 ;

			appcontext = CCDirector.sharedDirector().getActivity();
			//SoundEngine.sharedEngine().playEffect(appcontext, R.raw.tileclick);

			timer.scheduleAtFixedRate( new TimerTask(){
				public void run() {
					updateTimeLabel();
				}
			}, 0, 1000 );
		}

		public void resetTilesEasyCallback(Object sender) {

			NUM_ROWS = 3 ;
			NUM_COLUMNS = 3 ;
			CCNode tileNode = (CCNode) getChildByTag(TILE_NODE_TAG);
			tileNode.removeAllChildren(true);
			tileNode.removeSelf();
			time = 0 ;
			moves = 0 ;
			generateTiles();
		}

		public void resetTilesHardCallback(Object sender) {
			NUM_ROWS = 3 ;
			NUM_COLUMNS = 5 ;
			CCNode tileNode = (CCNode) getChildByTag(TILE_NODE_TAG);
			tileNode.removeAllChildren(true);
			tileNode.removeSelf();
			time = 0 ;
			moves = 0 ;
			generateTiles();
		}
		public void generateTiles(){
			//tileNumbers ;
			tileIndex = 0 ;
			//Generate the random but solvable number series
			tileNumbers = Utility.byteArrayToIntArray(Utility.getRandomArray((NUM_ROWS * NUM_COLUMNS), false));			//Create 12 Boxes and arrange ont the screenMenu Items and add to layer
			int nextval ;

			CCNode tilesNode = CCNode.node();
			tilesNode.setTag(TILE_NODE_TAG);
			addChild(tilesNode);

			for (int j = 420 ; j > 420 - (TILE_SQUARE_SIZE * NUM_ROWS); j-= TILE_SQUARE_SIZE){
				for (int i = 100 ; i < 100 + (TILE_SQUARE_SIZE * NUM_COLUMNS); i+= TILE_SQUARE_SIZE){
					nextval = tileNumbers[tileIndex ];
					 
					//Create a fontatlas, put it in an item, put it in a menu
					//Put the item in a Node and animate the Node.
					CCMenuItemImage eachItem= CCMenuItemImage.item("tile.png", "tile2.png",this, "tileClickCallback");
					eachItem.setContentSize(eachItem.getContentSize().width  , eachItem.getContentSize().height);
					CCMenu menu = CCMenu.menu() ;
					menu.setContentSize(eachItem.getContentSize()) ;
					//Put the an item in Menu 
					menu.setPosition(0,0);
					menu.addChild(eachItem,1,1);


					CCNodeExt eachNode =  new  CCNodeExt();
					eachNode.setContentSize(eachItem.getContentSize());
					eachNode.addChild(menu,1,1);
					eachNode.setPosition(i, j);
					eachNode.setNodeText(nextval + "");

					//Add Tile number
					CCBitmapFontAtlas tileNumber = CCBitmapFontAtlas.bitmapFontAtlas ("00", "bionic.fnt");
					tileNumber.setScale(1.4f);
					tileNumber.setString(nextval + "");
					eachNode.addChild(tileNumber,2);
					if( nextval != 0){
						tilesNode.addChild(eachNode,1,nextval);
					}else {
						emptyPosition = CGPoint.ccp(i, j);
					}

					//Add each Node to a HashMap to note its location
					tileIndex++;
				}
			}
		}
		public void updateTimeLabel() {
			time += 1;
			String string = CCFormatter.format("%02d:%02d", (int)(time /60) , (int)time % 60 );
			CCBitmapFontAtlas timerLabel = (CCBitmapFontAtlas) getChildByTag(TIMER_LABEL_TAG) ;
			timerLabel.setString(string);

		}

		/*Class Slides the tile. 
		 * Params : theNode to be slidded
		 * Boolean to move tile or not.
		 * */
		public void slideTile(String direction, CCNodeExt thenode, boolean move){
			CCBitmapFontAtlas scorelabel = (CCBitmapFontAtlas) getChildByTag(SCORE_LABEL_TAG);
			CCBitmapFontAtlas moveslabel = (CCBitmapFontAtlas) getChildByTag(MOVES_LABEL_TAG);
			
			if(move){ // Without this if .. the game becomes crazy .. tiles can be lifted from board .. possible cheatmode
			moves++ ;
			moveslabel.setString("Moves : " + CCFormatter.format("%03d", moves ));
			CGPoint nodePosition = thenode.getPosition();
			CGPoint tempPosition = emptyPosition ;
			CCMoveTo movetile = CCMoveTo.action(0.2f, tempPosition); 
			CCSequence movetileSeq = CCSequence.actions(movetile, CCCallFuncN.action(this, "handleWin"));
			thenode.runAction(movetileSeq);
			emptyPosition = nodePosition ;
			SoundEngine.sharedEngine().playEffect(appcontext, R.raw.tileclick);
			thenode.runAction(movetileSeq);
				scorelabel.setString( "Tile " + thenode.getNodeText() + " -> " + direction);
			}else{
				scorelabel.setString( "Tile " + thenode.getNodeText() + " -> " + direction);
			}
			
			
			
			
		}
		
		/* 
		 * Handles Each tile Click Call back
		 * 
		 * */
		public void tileClickCallback(Object sender) {
			CCMenuItemImage item = (CCMenuItemImage) sender ;
			CCNodeExt thenode = (CCNodeExt) item.getParent().getParent() ;
			CGPoint nodePosition = thenode.getPosition();

			if((nodePosition.x - TILE_SQUARE_SIZE)== emptyPosition.x && nodePosition.y == emptyPosition.y){
				slideTile("Left", thenode,true);
			}else if((nodePosition.x + TILE_SQUARE_SIZE) == emptyPosition.x && nodePosition.y == emptyPosition.y){
				slideTile("Right", thenode,true);
			}else if((nodePosition.x)== emptyPosition.x && nodePosition.y == (emptyPosition.y  + TILE_SQUARE_SIZE )){
				slideTile("Down", thenode,true);
			}else if((nodePosition.x )== emptyPosition.x && nodePosition.y == (emptyPosition.y  - TILE_SQUARE_SIZE)){ 
				slideTile("Up", thenode,true);
			}else{
				slideTile("Unmovable", thenode,false);
			}

		}

		public void handleWin(Object sender){
			if(checkCorrect()){
				CCBitmapFontAtlas scorelabel = (CCBitmapFontAtlas) getChildByTag(SCORE_LABEL_TAG);
				scorelabel.setString("Congratulations !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				SoundEngine.sharedEngine().playEffect(appcontext, R.raw.cheer);
				//timer.cancel();
				time = 0 ;
			}
		}
		public boolean checkCorrect(){
			CCNode tileNode = (CCNode) getChildByTag(TILE_NODE_TAG);
			int nodeindex = 1 ;
			boolean result = false;

			for (int j = 420 ; j > 420 - (TILE_SQUARE_SIZE * NUM_ROWS); j-= TILE_SQUARE_SIZE){
				for (int i = 100 ; i < 100 + (TILE_SQUARE_SIZE * NUM_COLUMNS); i+= TILE_SQUARE_SIZE){
					if(tileNode.getChildByTag(nodeindex).getPosition().x == i && tileNode.getChildByTag(nodeindex).getPosition().y == j ){
						result = true ;
					}else{
						return false ;
					}
					nodeindex++ ;
					if(nodeindex == (NUM_ROWS * NUM_COLUMNS)){
						return result ;
					}
				}
			}
			return result ;
		}




	}



}



