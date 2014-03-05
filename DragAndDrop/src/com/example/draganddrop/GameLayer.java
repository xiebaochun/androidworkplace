package com.example.draganddrop;

import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import android.util.Log;
import android.view.MotionEvent;

public class GameLayer extends CCColorLayer {
    
	public enum GameState{start,play,over};
	public GameState gamestate=GameState.start;
	public int playerPositionX=0;
	public int dt=1;
	public int timeTick=0;
	public int timeSeconedTick=0;
	public int eachStageTime=60;
	public final int QUESTION_POSITION_X=150;
	public final int QUESTION_POSITION_Y=450;
	public final int TIME_BAR_POSITION_X=280;
	public final int TIME_BAR_POSITION_Y=595;
	public final int GAME_START_POSITION_X=430;
	public final int GAME_START_POSITION_Y=216;
	public final int TIME_BAR_HEIGHT=40;
	public final int TIME_BAR_WIDTH=768;
	public Boolean isGamePause=false;
	MySprite backGround;
	MySprite stateBar;
	MySprite TimeBar;
	MySprite player;
	MySprite question;
	MySprite timeBar;
	MySprite gameStart;
	
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new GameLayer(ccColor4B.ccc4(255, 255, 255, 0));

	 
	    scene.addChild(layer);
	 
	    return scene;
	}
	protected GameLayer(ccColor4B color)
	{
		
	    super(color);
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    CommonItem.SIZE_RATE_Y=(float)(winSize.height/CommonItem.GAME_HEIGHT);
	    CommonItem.SIZE_RATE_X=(float)(winSize.width/CommonItem.GAME_WIDTH);
	    Log.v("log","winSize.height:"+winSize.height);
	    Log.v("log","SIZE_RATE_Y"+ CommonItem.SIZE_RATE_Y);
	    player = new MySprite("Player.png",true,0,0);
	    
	    question= new MySprite("question.png",true,QUESTION_POSITION_X,QUESTION_POSITION_Y);    
	    question.fixedSizeRate((float)1280/2500);
	    backGround=new MySprite("bg_b.png",true,0,0);
	    stateBar=new MySprite("StateBar.png",true,15,555);
	    
	    timeBar=new MySprite("TimeBar.png",true,TIME_BAR_POSITION_X,TIME_BAR_POSITION_Y);
	    gameStart=new MySprite("GameStart.png",true,GAME_START_POSITION_X,GAME_START_POSITION_Y);
	    Log.v("log","my");
	    
	    addChild(player.sprite);
	    addChild(backGround.sprite,-5);
	    addChild(stateBar.sprite,-4);
	    addChild(question.sprite,-4);
	    addChild(timeBar.sprite,-3);
	    addChild(gameStart.sprite,-3);
	    Timer timer=new Timer();
	    timer.scheduleAtFixedRate( new TimerTask(){
			public void run() {
				gameUpdate();
			}
		}, 0, (long)(1000/60) );
	   // schedule("gameUpdate");
	}
	
	
	public void gameUpdate() {	
		spriteRectAndeventUpdate();
		switch(gamestate)
		{
		   case start:
			   if(isGamePause==false)
			   {
				   gamestate=GameState.play;
			   }
			   break;
		   case play:
			   if(isGamePause==false)
			   {
				   timeUpdate();
			   }
			   
			   break;
		   case over:
			   break;
		   default: 
               break;
		}
		
		playerPositionX+=CommonItem.dt;
		player.setPosition(playerPositionX, 0);
		if(playerPositionX>=1500)CommonItem.dt=-1;
		if(playerPositionX<0)CommonItem.dt=1;
		
	}
	public void spriteRectAndeventUpdate() {
		
		
	}
	public void timeUpdate() {
		timeTick++;
		
		if(timeTick>=60)
		{
			
		   timeTick=0;
		   timeSeconedTick++;
			Log.v("log",""+timeSeconedTick);//log
		   if(timeSeconedTick>=eachStageTime)
		   {
			   timeSeconedTick=0;
			   isGamePause=true;
		   }
		   timeBar.sprite.setTextureRect(0, 0, ((float)(eachStageTime-timeSeconedTick)/eachStageTime)*TIME_BAR_WIDTH,50 , false);
		}
		
	}
	public boolean ccTouchesBegan(MotionEvent event) {
		dt*=-1;
	    assert false:"Layer#ccTouchBegan override me";
        return CCTouchDispatcher.kEventHandled;  // TODO Auto-generated method stub
    }

    public boolean ccTouchesMoved(MotionEvent event) {
        return CCTouchDispatcher.kEventIgnored;  // TODO Auto-generated method stub
    }

    public boolean ccTouchesEnded(MotionEvent event) {
        return CCTouchDispatcher.kEventIgnored;  // TODO Auto-generated method stub
    }

    public boolean ccTouchesCancelled(MotionEvent event) {
        return CCTouchDispatcher.kEventIgnored;  // TODO Auto-generated method stub
    }

    public void ccAccelerometerChanged(float accelX, float accelY, float accelZ) {
		// Override to process accelerometer events.
    }
}
