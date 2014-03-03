/**
	 * File: Utility.java
	 * Author:  Victor Dibia 
	 * Date last modified: Feb 10, 2012
	 * Model tiles with extra field NodeText 
	 */

package com.masterson.game;

import org.cocos2d.nodes.CCNode;

public class CCNodeExt extends CCNode{
	public  String nodeText ;
	
	public CCNodeExt(){
		super();
					
	}
	
	public void setNodeText(String nText){
		this.nodeText = nText; 
	}
	
	public String getNodeText(){
		return this.nodeText ;
	}

}
