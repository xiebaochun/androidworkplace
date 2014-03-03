package com.example.draganddrop;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class GameLayer extends CCLayer {

	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCLayer layer = new GameLayer();
	 
	    scene.addChild(layer);
	 
	    return scene;
	}
	protected GameLayer()
	{
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    CCSprite player = CCSprite.sprite("fps_images.png");
	 
	    player.setPosition(CGPoint.ccp(player.getContentSize().width / 2.0f, winSize.height / 2.0f));
	 
	    addChild(player);
	}
}
