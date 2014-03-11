package com.example.draganddrop;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.types.CGPoint;

import android.util.Log;


public class Card extends CCNode{
	 CCMenuItemImage sprite;
	 CCMenu menu;
     int touchCount=0;
     Boolean isTouched=false;
     public int textTureWidth;
 	public int textTureHeight;
 	public float fixSizeRate=1;
 	public Rectangle collisionRect;
     public Card(String filePath,Boolean isVisible,CGPoint position)
     {
    	 //super( filePath,isVisible, position,-4);
    	 sprite=CCMenuItemImage.item(filePath, filePath,this, "clickCallback");
    	 this.textTureWidth=(int)sprite.getContentSize().width;
 		 this.textTureHeight=(int)sprite.getContentSize().height;
 		this.sprite.setScaleY(CommonItem.SIZE_RATE_Y*fixSizeRate);
		this.sprite.setScaleX(CommonItem.SIZE_RATE_X*fixSizeRate);
 		sprite.setContentSize(sprite.getContentSize().width  , sprite.getContentSize().height);
    	 
    	 menu= CCMenu.menu() ;
    	 menu.setContentSize(sprite.getContentSize()) ;
    	 menu.setPosition(0,0);
		 menu.addChild(sprite,1,1);
		 
		this.setContentSize(sprite.getContentSize());
		this.addChild(menu,1,1);
		this.setPosition(position.x*CommonItem.SIZE_RATE_X, position.y*CommonItem.SIZE_RATE_Y);
		this.setVisible(isVisible);
		this.collisionRect=new Rectangle(this.getPosition().x,this.getPosition().y,this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate,this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate);
		CommonItem.gameLayer.addChild(this,-3);
		Log.v("log","createCard.getVisible:"+this.getVisible()+filePath);
     }
     public void clickCallback(Object sender)
     {
    	 for(Card card:CommonItem.redCards)
    	 {
    		 if(card.getVisible()==true&&card!=this)
    		 {
    			card.touchCount=0;
    			card.isTouched=false;
    			card.fixedSizeRate(CommonItem.fixedSizeRate);
    		 }
    	 }
    	 this.touchCount++;
			if(touchCount%2==1)
				{
				this.isTouched=true;
				this.fixedSizeRate(0.6f);
				}
			else
			{
				this.isTouched=false;
				this.fixedSizeRate(CommonItem.fixedSizeRate);
				this.touchCount=0;
			}
     }
     public void fixedSizeRate(float rate) {
 		fixSizeRate=rate;
 		this.sprite.setScaleY(CommonItem.SIZE_RATE_Y*fixSizeRate);
 		this.sprite.setScaleX(CommonItem.SIZE_RATE_X*fixSizeRate);
 		this.collisionRect=new Rectangle(this.getPosition().x-this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate/2,this.getPosition().y-this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate/2,this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate,this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate);
     }
     public void collisionRectUpdate()
 	{
 		this.collisionRect=new Rectangle(this.getPosition().x-this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate/2,this.getPosition().y-this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate/2,this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate,this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate);
 	   
 	}
     public void setPosition_ds(CGPoint position) {
 		int x=(int)(position.x*CommonItem.SIZE_RATE_X);
 		int y=(int)(position.y*CommonItem.SIZE_RATE_Y);
 		this.setPosition(x, y);
 		this.collisionRect=new Rectangle(x,y,this.textTureWidth*CommonItem.SIZE_RATE_X*fixSizeRate,this.textTureHeight*CommonItem.SIZE_RATE_X*fixSizeRate);
     }
}
