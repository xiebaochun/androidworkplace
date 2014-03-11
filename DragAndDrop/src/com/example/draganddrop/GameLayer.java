package com.example.draganddrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import com.example.draganddrop.CommonItem.TouchState;

import android.util.Log;
import android.view.MotionEvent;
<<<<<<< HEAD

public class GameLayer extends CCColorLayer {
    
	public enum GameState{start,play,over};
	public GameState gamestate=GameState.start;
	public int playerPositionX=0;
	public int timeTick=0;
	public int timeSeconedTick=0;
	public int eachStageTime=60;
	public int stage=1;
	public int level=1;
	public int score=0;
	public int target=10;
	public final CGPoint QUESTION_POSITION=CGPoint.make(150,450);
	public final CGPoint TIME_BAR_POSITION=CGPoint.make(280,605);
	public final CGPoint GAME_START_POSITION=CGPoint.make(430,216);
	public final CGPoint CONFIRM_BUTTON_POSITION=CGPoint.make(1000,23);
	
=======

public class GameLayer extends CCColorLayer {
    
	public enum GameState{start,play,over};
	public GameState gamestate=GameState.start;
	public int playerPositionX=0;
	public int timeTick=0;
	public int timeSeconedTick=0;
	public int eachStageTime=60;
	public int stage=1;
	public int level=1;
	public int score=0;
	public int target=10;
	public final CGPoint QUESTION_POSITION=CGPoint.make(150,450);
	public final CGPoint TIME_BAR_POSITION=CGPoint.make(280,605);
	public final CGPoint GAME_START_POSITION=CGPoint.make(430,216);

>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	public final int TIME_BAR_HEIGHT=40;
	public final int TIME_BAR_WIDTH=768;
	 public final int EACH_CARDS_COUNT=5;
	public CGPoint touchPoint;
<<<<<<< HEAD
	public Boolean isGamePause=false;
=======
	public Boolean isGamePause=true;
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	public Boolean goToPrepare=true;
	MySprite backGround;
	MySprite stateBar;
	MySprite TimeBar;
	//MySprite player;
	MySprite question_image;
	MySprite timeBar;
<<<<<<< HEAD
//	Button gameStart;
//	Button replay;
//	Button pass;
	Button confirm_bt;
=======
	MySprite gameStart;
	MySprite replay;
	MySprite pass;
    
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	//MySprite number;
	MySprite[] answers=new MySprite[5];
    Number score_num;
    Number stage_num;
    Number target_num;
    //CCLabel test_label;
    
<<<<<<< HEAD
    
=======
    Card[]  redCards=new Card[5];
    Card[]  blueCards=new Card[5];
    Card[]  greenCards=new Card[5];
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
    CGPoint[] CardsPosition=new CGPoint[10];
    CGPoint[] AnswersPosition=new CGPoint[5];
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
	    this.setIsTouchEnabled(true);
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    CommonItem.SIZE_RATE_Y=(float)(winSize.height/CommonItem.GAME_HEIGHT);
	    CommonItem.SIZE_RATE_X=(float)(winSize.width/CommonItem.GAME_WIDTH);
	    CommonItem.gameLayer=this;
	    Log.v("log","winSize.height:"+winSize.height);
	    Log.v("log","SIZE_RATE_Y"+ CommonItem.SIZE_RATE_Y);
	   // player = new MySprite("Player.png",true,CGPoint.zero(),0);
	   // player.sprite.setOpacity(90);
	    question_image= new MySprite("question.png",true,QUESTION_POSITION,-4);    
	    question_image.fixedSizeRate((float)1280/2500);
	    backGround=new MySprite("bg_b.png",true,CGPoint.zero(),-5);
	    stateBar=new MySprite("StateBar.png",true,CGPoint.make(15, 555),-4);
	    
	    score_num=new Number(0,16,CGPoint.make(200, 650));
	    score_num.setNumber(score);
	    stage_num=new Number(0,16,CGPoint.make(630, 650));
	    stage_num.setNumber(stage);
	    target_num=new Number(0,16,CGPoint.make(960, 650));
	    target_num.setNumber(target);
	    timeBar=new MySprite("TimeBar.png",true,TIME_BAR_POSITION,-3);
	    
<<<<<<< HEAD
//	    gameStart=new Button("GameStart.png",true,GAME_START_POSITION,0);
//	    replay=new Button("replay.png",false,GAME_START_POSITION,0);
//	    pass=new Button("Continue.png",false,GAME_START_POSITION,0);
	    
	    confirm_bt=new Button("confirm.png","confirm.png",CONFIRM_BUTTON_POSITION,true);
	    confirm_bt.fixedSizeRate(CommonItem.fixedSizeRate);
=======
	    gameStart=new MySprite("GameStart.png",true,GAME_START_POSITION,0);
	    replay=new MySprite("replay.png",false,GAME_START_POSITION,0);
	    pass=new MySprite("Continue.png",false,GAME_START_POSITION,0);
	    
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	   // number=new MySprite("stageNumber.png",true,CGPoint.zero(),11,1,-3);
	  //preapare cards position
	    for(int i=0;i<2*5;i++)
	    {
	       CardsPosition[i]=CGPoint.make(530+(i%5)*150, 300-150*(i/5));
	    }
	  //prepare answers position
	    for(int i=0;i<5;i++)
	    {
	    	AnswersPosition[i]=CGPoint.make(480+i*150, 400);
	    }
	    Log.v("log","my");
	    try{
	    loadCards();
	    }
	    catch(Exception e){
	    	Log.v("log","loadcards error");
	    }
	    
		
	    
	   
	   // test_label=CCLabel.makeLabel("currenTouch:"+CommonItem.currenTouchState+"preCurrentTouch:"+CommonItem.preTouchState+"touchState:"+CommonItem.touchState+"xy:"+touchPoint,"Marker Felt", 30);
	    //test_label.setPosition(1000, 100);
	   // addChild(test_label,0);
	    //	    addChild(player.sprite);
//	    addChild(backGround.sprite,-5);
//	    addChild(stateBar.sprite,-4);
//	    addChild(question.sprite,-4);
//	    addChild(timeBar.sprite,-3);
//	    addChild(gameStart.sprite,-3);
//	    addChild(number.sprite,-3);
//	    addChild(replay.sprite,-3);
//	    addChild(pass.sprite,-3);
	    Timer timer=new Timer();
	    timer.scheduleAtFixedRate( new TimerTask(){
			public void run() {
				gameUpdate();
			}
		}, 0, (long)(1000/60) );
	   // schedule("gameUpdate");
	}
	
	
	private void loadCards() {
		
		 for(int i=0;i<EACH_CARDS_COUNT;i++)
	   	 {
		    Card cardSprite=new Card("red-"+(i+1)+".png",false,CGPoint.zero());
		    cardSprite.fixedSizeRate(CommonItem.fixedSizeRate);
		    
	   		try{
<<<<<<< HEAD
	   			CommonItem.redCards[i]=cardSprite;
=======
	   		redCards[i]=cardSprite;
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	   		}catch(Exception e){
	   			Log.v("log","error:"+e);
	   		}
	   		
	   		
	   	 }
	   	 for(int i=0;i<EACH_CARDS_COUNT;i++)
	   	 {
	   		Card cardSprite=new Card("blue-"+(i+1)+".png",false,CGPoint.zero());
<<<<<<< HEAD
	   		CommonItem.blueCards[i]=cardSprite;
=======
	   		blueCards[i]=cardSprite;
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	   		cardSprite.fixedSizeRate(CommonItem.fixedSizeRate);
	   		
	   	 }
	   	 for(int i=0;i<EACH_CARDS_COUNT;i++)
	   	 {
	   		Card cardSprite=new Card("green-"+(i+1)+".png",false,CGPoint.zero());
<<<<<<< HEAD
	   		CommonItem.greenCards[i]=cardSprite;
=======
	   		greenCards[i]=cardSprite;
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
	   		cardSprite.fixedSizeRate(CommonItem.fixedSizeRate);
	   		
	   	 }
	   	 for(int i=0;i<5;i++)
	   	 {
	   		 
	   		 MySprite answer=new MySprite("answer_frame.png",false,AnswersPosition[i],-4);
	   		 answers[i]=answer;
	   		 answer.fixedSizeRate(CommonItem.fixedSizeRate);
	   	 }
		
	}
	public void gameUpdate() {	
		
		CommonItem.currenTouchState=CommonItem.touchState;
		touchPoint=CommonItem.touchPoint;
		spriteRectAndeventUpdate();
		//test_label.setString("currenTouch: "+CommonItem.currenTouchState+"  preCurrentTouch:"+CommonItem.preTouchState+"  touchState: "+CommonItem.touchState+"  xy: "+touchPoint);

		
<<<<<<< HEAD
//		if(tap())
//		{
//			stage++;
//			stage_num.setNumber(stage);
//		}
=======
		if(tap())
		{
			stage++;
			stage_num.setNumber(stage);
		}
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
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
				   if(goToPrepare==true)
				   {
					   goToPrepare=false;
				     prepareQuestion();
				     prepareCards();
				   }
				   cardsPositionUpdate();
			   }
			   
			   break;
		   case over:
			   break;
		   
		}
		CommonItem.preTouchState=CommonItem.currenTouchState;
//		playerPositionX+=CommonItem.dt;
//		player.setPosition(playerPositionX, 0);
//		if(playerPositionX>=1500)CommonItem.dt=-1;
//		if(playerPositionX<0)CommonItem.dt=1;
		
		//CommonItem.preTouchState=CommonItem.currenTouchState;
		//CommonItem.touchState=CommonItem.TouchState.none;
	}
	private void cardsPositionUpdate() {
		
<<<<<<< HEAD
		for(int i=0;i<CommonItem.redCards.length;i++)
		{
			if(CommonItem.redCards[i].getVisible()==true)
			{
				CommonItem.redCards[i].collisionRectUpdate();
				

				if(CommonItem.redCards[i].isTouched==true)
				{
					
					if(CommonItem.redCards[i].collisionRect.contains(CommonItem.touchPoint)&&CommonItem.currenTouchState==CommonItem.TouchState.move)
					{
					///redCards[i].setPosition(CommonItem.touchPoint.x-50,CommonItem.touchPoint.y-50);
						CommonItem.redCards[i].setPosition((CommonItem.touchPoint.x),(CommonItem.touchPoint.y));
=======
		for(int i=0;i<redCards.length;i++)
		{
			if(redCards[i].getVisible()==true)
			{
				redCards[i].collisionRectUpdate();
				

				if(redCards[i].isTouched==true)
				{
					
					if(redCards[i].collisionRect.contains(CommonItem.touchPoint)&&CommonItem.currenTouchState==CommonItem.TouchState.move)
					{
					///redCards[i].setPosition(CommonItem.touchPoint.x-50,CommonItem.touchPoint.y-50);
					  redCards[i].setPosition((CommonItem.touchPoint.x),(CommonItem.touchPoint.y));
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
				    
					}
				}
		   }
		
		}
	}
	private void prepareCards() {
		for(int i=0;i<=4;i++)
		{
<<<<<<< HEAD
			CommonItem.redCards[i].setPosition_ds(CardsPosition[i]);
			CommonItem.redCards[i].setVisible(true);
			Log.v("log","redCards[i].getVisible:"+CommonItem.redCards[i].getVisible());
=======
			redCards[i].setPosition_ds(CardsPosition[i]);
			redCards[i].setVisible(true);
			Log.v("log","redCards[i].getVisible:"+redCards[i].getVisible());
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
		}
		for(MySprite sp:answers)
		{
			sp.setVisible(true);
		}
<<<<<<< HEAD
	}
	private void prepareQuestion() {
		// TODO Auto-generated method stub
		
	}
	public void spriteRectAndeventUpdate() {
//		if(gameStart.getVisible()==true)
//		{
//			 gameStart.isClicked=false;
//			  gameStart.setVisible(false);
//			  isGamePause=false;
//		  if(gameStart.isClicked==true)
//		  {
//			  gameStart.isClicked=false;
//			  gameStart.setVisible(false);
//			  isGamePause=false;
//		  }
//		}
//		if(pass.getVisible()==true)
//		{
//			
//		  if(pass.isClicked==true)
//		  {
//			  pass.isClicked=false;
//			  pass.setVisible(false);
//			  isGamePause=false;
//			  stage++;
//			  stage_num.setNumber(stage);
//			  score=0;
//			  score_num.setNumber(score);
//			  target++;
//			  target_num.setNumber(target);
//			  goToNextStage();
//		  }
//		}
//		if(replay.getVisible()==true)
//		{
//			
//		  if(replay.isClicked==true)
//		  {
//			  replay.isClicked=false;
//			  replay.setVisible(false);
//			  isGamePause=false;
//			  score=0;
//			  score_num.setNumber(score);
//		  }
//		}
=======
	}
	private void prepareQuestion() {
		// TODO Auto-generated method stub
		
	}
	public void spriteRectAndeventUpdate() {
		if(gameStart.getVisible()==true)
		{
			
		  if(tap()&&gameStart.collisionRect.contains(touchPoint))
		  {
			  
			  gameStart.setVisible(false);
			  isGamePause=false;
		  }
		}
		if(pass.getVisible()==true)
		{
			
		  if(tap()&&pass.collisionRect.contains(touchPoint))
		  {
			  
			  pass.setVisible(false);
			  isGamePause=false;
			  stage++;
			  stage_num.setNumber(stage);
			  score=0;
			  score_num.setNumber(score);
			  target++;
			  target_num.setNumber(target);
			  goToNextStage();
		  }
		}
		if(replay.getVisible()==true)
		{
			
		  if(tap()&&replay.collisionRect.contains(touchPoint))
		  {
			  
			  replay.setVisible(false);
			  isGamePause=false;
			  score=0;
			  score_num.setNumber(score);
		  }
		}
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
		
	}
	//ready next stage things 
	private void goToNextStage() {
		
		
	}
	//if is tap in screen
<<<<<<< HEAD
//	private boolean tap() {
//		if(CommonItem.currenTouchState==CommonItem.TouchState.up&&CommonItem.preTouchState==CommonItem.TouchState.down)//&&CommonItem.currenTouchState==CommonItem.TouchState.up)
//		{
//			score++;
//			score_num.setNumber(score);
//			Log.v("touchState","tap");
//			Log.v("touchState","pisition:"+touchPoint.toString());
//			return true;
//		}
//		return false;
//	}
=======
	private boolean tap() {
		if(CommonItem.currenTouchState==CommonItem.TouchState.up&&CommonItem.preTouchState==CommonItem.TouchState.down)//&&CommonItem.currenTouchState==CommonItem.TouchState.up)
		{
			score++;
			score_num.setNumber(score);
			Log.v("touchState","tap");
			Log.v("touchState","pisition:"+touchPoint.toString());
			return true;
		}
		return false;
	}
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
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
			   if(score>=target)
			   {
<<<<<<< HEAD
//				   pass.setVisible(true);
			   }
			   else
			   {
//				   replay.setVisible(true);
=======
				   pass.setVisible(true);
			   }
			   else
			   {
				   replay.setVisible(true);
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
			   }
		   }
		   timeBar.sprite.setTextureRect(0, 0, ((float)(eachStageTime-timeSeconedTick)/eachStageTime)*TIME_BAR_WIDTH,40 , false);
		}
	}
<<<<<<< HEAD
	
=======
>>>>>>> 1b6f8192eddc9df3280975cd17f47dc7feb92817
		//@Override
        public boolean ccTouchesBegan(MotionEvent event) {
            CGPoint convertedLocation = CCDirector.sharedDirector()
            	.convertToGL(CGPoint.make(event.getX(), event.getY()));

            target++;
            target_num.setNumber(target);
           // CommonItem.touchState=CommonItem.TouchState.down;
           // CCNode s = getChildByTag(kTagSprite);
            //s.stopAllActions();
            //s.runAction(CCMoveTo.action(1.0f, convertedLocation));
           
            //CGPoint pnt = s.getPosition();

           // float at = CGPoint.ccpCalcRotate(pnt, convertedLocation);

            //s.runAction(CCRotateTo.action(1, at));
            
            //progressTimer.setPercentage(10.0f + progressTimer.getPercentage());

            return CCTouchDispatcher.kEventHandled;
        }
        public boolean ccTouchesMoved(MotionEvent event) {
        	//CommonItem.touchState=CommonItem.TouchState.move;
            return CCTouchDispatcher.kEventIgnored;  // TODO Auto-generated method stub
        }

        public boolean ccTouchesEnded(MotionEvent event) {
        	//CommonItem.touchState=CommonItem.TouchState.up;
            return CCTouchDispatcher.kEventIgnored;  // TODO Auto-generated method stub
        }
	
	
}
