package com.example.draganddrop;

import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.util.Log;

public class MyLabel {
	public CCLabel label;
	public MyLabel(String string,CGPoint position)
	{
		try{
	    label=CCLabel.makeLabel(string,CGSize.make(300*CommonItem.SIZE_RATE_X, 50*CommonItem.SIZE_RATE_Y),CCLabel.TextAlignment.CENTER, "myfont.fnt", 20);
	      Log.v("log","Label load success!");
		}catch(Exception e){
			Log.v("log","Label load errror!");
		}
	    label.setAnchorPoint(0, 0);
	    label.setPosition(position.x*CommonItem.SIZE_RATE_X,position.y*CommonItem.SIZE_RATE_Y);
	    label.setColor(new ccColor3B(0,0,0));
	    CommonItem.gameLayer.addChild(label,0);
	}
	 public void setString(String seq) 
	 {  
		 try{
	     
	     label.setString(seq);
	     Log.v("log","Label load success!");
		 }catch(Exception e)
		 {
			 Log.v("log","label SetString errror!"+e);
		 }
	 }
	 public void setVisible(Boolean isVisible)
	 {
		 this.label.setVisible(isVisible);
	 }
}
