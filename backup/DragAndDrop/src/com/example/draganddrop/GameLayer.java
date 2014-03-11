package com.example.draganddrop;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

public class GameLayer extends CCColorLayer {

	CCSprite backGround;
	CCSprite stateBar;
	CCSprite TimeBar;
	CCSprite player;
	CCSprite question;
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
	    player = CCSprite.sprite("Player.png");
	    question= CCSprite.sprite("question.png");
	    backGround=CCSprite.sprite("bg_b.png");
	    stateBar=CCSprite.sprite("StateBar.png");
	    
	    backGround.setAnchorPoint(0,0);
	    player.setAnchorPoint(0,0);
	    stateBar.setAnchorPoint(0,0);
	    //player.setPosition(CGPoint.ccp(winSize.width / 2.0f, winSize.height / 2.0f));
	    
	    player.setPosition(0, 0);
	    backGround.setPosition(0,0);
	    stateBar.setPosition(0,winSize.getHeight()-stateBar.getTexture().getHeight());
	    
	    backGround.setScaleY(winSize.getHeight()/backGround.getTexture().getHeight());
	    backGround.setScaleX(winSize.getWidth()/backGround.getTexture().getWidth());
	    stateBar.setScaleY(winSize.getHeight()/stateBar.getTexture().getHeight());
	    stateBar.setScaleX(winSize.getWidth()/stateBar.getTexture().getWidth());
	    
	    addChild(player);
	    addChild(backGround,-5);
	    addChild(stateBar,-4);
	    this.schedule("gameUpdate");
	    //player.setVisible(false);  
	}
	public void gameUpdate(){
		player.setPosition(20,50);
		player.setPosition(player.getPositionRef().x+1,player.getPositionRef().y);
		    
	}
}
