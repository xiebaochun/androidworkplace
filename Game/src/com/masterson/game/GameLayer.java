package com.masterson.game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.menus.CCMenuItemLabel;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCBitmapFontAtlas;
import org.cocos2d.opengl.CCTextureAtlas;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;
import org.cocos2d.utils.CCFormatter;

import android.content.Context;

import com.vidicorp.puzzle.R;

public class GameLayer extends CCLayer {
	CCTextureAtlas textureAtlas;
	private CCBitmapFontAtlas bestScoreLabel;
	private int toppoint;

	public static  ArrayList<CCNodeExt> spriteList = new ArrayList<CCNodeExt>();
	public static  int[] tileNumbers ;  
	public static  int  tileIndex ;  
	
	private static CGPoint emptyPosition  ;
	public static int score = 0 ;
	private static CGSize screenSize;
	private static int TILE_SQUARE_SIZE = 150 ;
	private static int NUM_ROWS = 0 ;
	private static int NUM_COLUMNS = 0 ;
	private static Context appcontext ;
	public static Timer timer ; 
	private static float time = 0 ;
	private static int moves = 0 ;
	
 
	private static int PAUSE_OVERLAY_TAG = 322 ;
	private static int SCROLL_TOP_TAG = 321 ;
	private static int LANG_LAYER_TAG = 349;
	private static int PAUSE_MENU_TAG = 348;
	private static int RESUME_MENU_TAG = 347;
	
	private static int SCORE_LABEL_TAG = 100 ;
	private static int BEST_SCORE_LABEL_TAG = 102 ;
	private static int TIMER_LABEL_TAG = 103 ;
	private static int TILE_NODE_TAG = 105 ;
	private static int MOVES_LABEL_TAG = 106 ;
	
	CCSprite bg1a;
	CCSprite bg1b;
	CCSprite bg2a;
	CCSprite bg2b;
	CCSprite bg3a;
	CCSprite bg3b;
	CCSprite bg4a;
	CCSprite bg4b;
	
	
	public GameLayer() {
		super();


		screenSize = CCDirector.sharedDirector().winSize();

		//Add Background Sprite Image
//		CCSprite background = CCSprite.sprite("background.jpg");
//		background.setPosition(CGPoint.ccp(screenSize.width / 2.0f, screenSize.height / 2.0f));
//		background.setScaleX(screenSize.getWidth()/background.getTexture().getWidth());
//		background.setScaleY(screenSize.getHeight()/background.getTexture().getHeight());
//		addChild(background,-5);
		
//		CCSprite bg = CCSprite.sprite("background.jpg");
//		bg.setAnchorPoint(0,0);
//		bg.setScaleX(bg.getTexture().getWidth()/screenSize.getWidth());
//	    bg.setScaleY(screenSize.getHeight()/bg.getTexture().getHeight());
	    
		bg1a = CCSprite.sprite("bg1.png");
		bg1b = CCSprite.sprite("bg1.png");
		bg2a = CCSprite.sprite("bg2.png");
		bg2b = CCSprite.sprite("bg2.png");
		bg3a = CCSprite.sprite("bg3.png");
		bg3b = CCSprite.sprite("bg3.png");
		bg4a = CCSprite.sprite("bg4.png");
		bg4b = CCSprite.sprite("bg4.png");
		
		bg1a.setAnchorPoint(0,0);
		bg1b.setAnchorPoint(0,0);
		bg2a.setAnchorPoint(0,0);
		bg2b.setAnchorPoint(0,0);
		bg3a.setAnchorPoint(0,0);
		bg3b.setAnchorPoint(0,0);
		bg4a.setAnchorPoint(0,0);
		bg4b.setAnchorPoint(0,0);
		
		
//		bg1a.setScaleX(screenSize.getWidth()/bg1a.getTexture().getWidth());
//		bg1a.setScaleX(screenSize.getWidth()/bg1a.getTexture().getWidth());
	    bg1a.setScaleY(screenSize.getHeight()/bg1a.getTexture().getHeight());
	    bg1b.setScaleY(screenSize.getHeight()/bg1b.getTexture().getHeight());
//	    bg2a.setScaleX(screenSize.getWidth()/bg2a.getTexture().getWidth());
//	    bg2b.setScaleX(screenSize.getWidth()/bg2b.getTexture().getWidth());
	    bg2a.setScaleY(screenSize.getHeight()/bg2a.getTexture().getHeight());
	    bg2b.setScaleY(screenSize.getHeight()/bg2b.getTexture().getHeight());
//	    bg3a.setScaleX(screenSize.getWidth()/bg3a.getTexture().getWidth());
//	    bg3b.setScaleX(screenSize.getWidth()/bg3b.getTexture().getWidth());
	    bg3a.setScaleY(screenSize.getHeight()/bg3a.getTexture().getHeight());
	    bg3b.setScaleY(screenSize.getHeight()/bg3b.getTexture().getHeight());
//	    bg4a.setScaleX(screenSize.getWidth()/bg4a.getTexture().getWidth());
//	    bg4b.setScaleX(screenSize.getWidth()/bg4b.getTexture().getWidth());
	    bg4a.setScaleY(screenSize.getHeight()/bg4a.getTexture().getHeight());
	    bg4b.setScaleY(screenSize.getHeight()/bg4b.getTexture().getHeight());
	    
		bg1a.setPosition(0, 0);
		bg1b.setPosition(bg1b.getTexture().getWidth()-5, 0);
		bg2a.setPosition(0, 0);
		bg2b.setPosition(bg2b.getTexture().getWidth()-1, 0);
		bg3a.setPosition(0, 0);
		bg3b.setPosition(bg3b.getTexture().getWidth()-1, 0);
		bg4a.setPosition(0, 0);
		bg4b.setPosition(bg4b.getTexture().getWidth()-1, 0);
		
		schedule("scroll");
		
		addChild(bg1a, -7).addChild(bg1b, -7);
		addChild(bg2a, -6).addChild(bg2b, -6);
		addChild(bg3a, -5).addChild(bg3b, -5);
		addChild(bg4a, -5).addChild(bg4b, -5);
		

		//Add Score Label

		CCBitmapFontAtlas scoreLabel = CCBitmapFontAtlas.bitmapFontAtlas ("Tap Tiles to Begin", "bionic.fnt");
		scoreLabel.setScale(1.3f);
		scoreLabel.setAnchorPoint(CGPoint.ccp(0,1));
		scoreLabel.setPosition( CGPoint.ccp( 25, screenSize.height - 10));
		addChild(scoreLabel,-2, SCORE_LABEL_TAG);


		//Add Best Label
	    bestScoreLabel = CCBitmapFontAtlas.bitmapFontAtlas ("BEST : 00:00", "bionic.fnt");
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
		//generateTiles();
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
	
	
	public void scroll(float dt){
		
		
		bg1a.setPosition(bg1a.getPosition().x - 10*dt, bg1a.getPosition().y);
		bg1b.setPosition(bg1b.getPosition().x - 10*dt, bg1b.getPosition().y);
		bg2a.setPosition(bg2a.getPosition().x - 50*dt, bg2a.getPosition().y);
		bg2b.setPosition(bg2b.getPosition().x - 50*dt, bg2b.getPosition().y);
		bg3a.setPosition(bg3a.getPosition().x - 100*dt, bg3a.getPosition().y);
		bg3b.setPosition(bg3b.getPosition().x - 100*dt, bg3b.getPosition().y);
		bg4a.setPosition(bg4a.getPosition().x - 150*dt, bg4a.getPosition().y);
		bg4b.setPosition(bg4b.getPosition().x - 150*dt, bg4b.getPosition().y);
		
		if(bg1a.getPosition().x + bg1b.getTexture().getWidth() < 0){
			bg1a.setPosition(0, 0);
			bg1b.setPosition(bg1b.getTexture().getWidth()-5, 0);
		}
		if(bg2a.getPosition().x + bg2b.getTexture().getWidth() < 0){
			bg2a.setPosition(0, 0);
			bg2b.setPosition(bg2b.getTexture().getWidth()-1, 0);
		}
		if(bg3a.getPosition().x + bg3b.getTexture().getWidth() < 0){
			bg3a.setPosition(0, 0);
			bg3b.setPosition(bg3b.getTexture().getWidth()-1, 0);
		}
		if(bg4a.getPosition().x + bg4b.getTexture().getWidth() < 0){
			bg4a.setPosition(0, 0);
			bg4b.setPosition(bg4b.getTexture().getWidth()-1, 0);
		}
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

		//Add Pause Sprite Button
		CCMenuItemImage pausebtn = CCMenuItemImage.item("pause.png", "pause.png",this, "pauseCallback");
		CCMenu pausemenu = CCMenu.menu();
		pausemenu.addChild(pausebtn,1,1);
		pausemenu.setContentSize(pausebtn.getContentSize());
		pausemenu.setPosition(CGPoint.make(screenSize.width - pausemenu.getContentSize().width - 10, pausemenu.getContentSize().height));
		addChild(pausemenu, 100,PAUSE_MENU_TAG);
		
		CCNode tilesNode = CCNode.node();
		tilesNode.setTag(TILE_NODE_TAG);
		addChild(tilesNode);
		
		int useableheight  ;
		float scalefactor ;
		
		toppoint =  (int) (bestScoreLabel.getPosition().y - bestScoreLabel.getContentSize().height * 2.0f - 20) ;
		useableheight = toppoint - 10  ;
		TILE_SQUARE_SIZE = (int) (useableheight/NUM_ROWS) ;
	   scalefactor = TILE_SQUARE_SIZE / 150.0f ;
		

		for (int j = toppoint ; j > toppoint - (TILE_SQUARE_SIZE * NUM_ROWS); j-= TILE_SQUARE_SIZE){
			for (int i = 100 ; i < 95 + (TILE_SQUARE_SIZE * NUM_COLUMNS); i+= TILE_SQUARE_SIZE){
				
				 if (tileIndex >= (NUM_ROWS * NUM_COLUMNS)) {
					 break ;
				 }
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
				eachNode.setScale(scalefactor);
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
			scorelabel.setString("Congratulations !!!!!!!!!!");
			SoundEngine.sharedEngine().playEffect(appcontext, R.raw.cheer);
			//timer.cancel();
			time = 0 ;
		}
	}
	public boolean checkCorrect(){
		CCNode tileNode = (CCNode) getChildByTag(TILE_NODE_TAG);
		int nodeindex = 1 ;
		boolean result = false;

		for (int j = toppoint ; j > toppoint - (TILE_SQUARE_SIZE * NUM_ROWS); j-= TILE_SQUARE_SIZE){
			for (int i = 100 ; i < 95 + (TILE_SQUARE_SIZE * NUM_COLUMNS); i+= TILE_SQUARE_SIZE){
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

	
	public void pauseCallback(Object sender) {
		unschedule("launchSprite");
		
		CCMenu pausemenu = (CCMenu) getChildByTag(PAUSE_MENU_TAG);
		pausemenu.setVisible(false);
		
		CCColorLayer pauseOverlay = CCColorLayer.node(ccColor4B.ccc4(25, 25, 25, 255));
		pauseOverlay.setOpacity(100);
		addChild(pauseOverlay,200,PAUSE_OVERLAY_TAG);
		
	
		//Add scroll to the Layer
		CCSprite topscroll = CCSprite.sprite("darktranstop.png");
		topscroll.setPosition(CGPoint.ccp(screenSize.width / 2.0f, screenSize.height + topscroll.getContentSize().height));
		pauseOverlay.addChild(topscroll,1,SCROLL_TOP_TAG);
		
		topscroll.runAction(CCSequence.actions(
				CCMoveTo.action(0.2f, CGPoint.make(screenSize.width / 2.0f,screenSize.height - topscroll.getContentSize().height/2)), 
				CCMoveTo.action(0.2f, CGPoint.make(screenSize.width / 2.0f,screenSize.height - topscroll.getContentSize().height/2 + 20)) 
				));
		
		// Add Pause Menu Buttons
		CCMenuItemImage playbtn = CCMenuItemImage.item("play.png", "play.png",this, "playCallback");
		CCMenuItemImage refreshbtn = CCMenuItemImage.item("refresh.png", "refresh.png",this, "refreshCallback");
		CCMenuItemImage menubtn = CCMenuItemImage.item("menu.png", "menu.png",this, "menuCallback");
		
		CCMenu resumemenu = CCMenu.menu(playbtn, refreshbtn, menubtn);
		resumemenu.setPosition(CGPoint.make(0, 0));
		menubtn.setPosition(CGPoint.make(screenSize.width - menubtn.getContentSize().width - 10, menubtn.getContentSize().height));
		refreshbtn.setPosition(CGPoint.make(screenSize.width - refreshbtn.getContentSize().width - menubtn.getContentSize().width - 15, refreshbtn.getContentSize().height));
		playbtn.setPosition(CGPoint.make(screenSize.width - refreshbtn.getContentSize().width - playbtn.getContentSize().width - menubtn.getContentSize().width - 20, menubtn.getContentSize().height));
		
		//resumemenu.setPosition(CGPoint.make(screenSize.width - resumemenu.getContentSize().width - 50, resumemenu.getContentSize().height));
		pauseOverlay.addChild(resumemenu, 2,RESUME_MENU_TAG) ;
		
	}



}
