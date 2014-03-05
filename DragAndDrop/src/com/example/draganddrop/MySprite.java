package com.example.draganddrop;

import org.cocos2d.nodes.CCSprite;

import android.util.Log;

public class MySprite{
	public CCSprite sprite;
	
	public MySprite(String filePath,Boolean isVisible,int positionX,int positionY)
	{
		
		try{
			this.sprite=CCSprite.sprite(filePath);
		}
		catch(Exception e){
			Log.v("log","filePath");
		}
		this.sprite.setAnchorPoint(0,0);
		this.sprite.setPosition(positionX*CommonItem.SIZE_RATE_X,positionY*CommonItem.SIZE_RATE_Y);
		this.sprite.setScaleY(CommonItem.SIZE_RATE_Y);
		this.sprite.setScaleX(CommonItem.SIZE_RATE_X);
		//sprite.setVisible(isVisible);
		Log.v("log","rate:"+CommonItem.SIZE_RATE_Y+"visible:"+sprite.getVisible());
	}
	public void setPosition(int x, int y) {
		this.sprite.setPosition(x, y);
    }
	public void fixedSizeRate(float rate) {
		this.sprite.setScaleY(CommonItem.SIZE_RATE_Y*rate);
		this.sprite.setScaleX(CommonItem.SIZE_RATE_X*rate);
    }
	public void setVisible(Boolean isVisible) {
		this.sprite.setVisible(isVisible);
    }
	
}
