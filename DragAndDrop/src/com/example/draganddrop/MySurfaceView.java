package com.example.draganddrop;

import org.cocos2d.opengl.CCGLSurfaceView;

import android.content.Context;
import android.view.MotionEvent;

public class MySurfaceView extends CCGLSurfaceView{
     
	public MySurfaceView(Context context)
     {
    	 super(context);
     }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
			
			 switch (event.getAction()) {
			    case MotionEvent.ACTION_DOWN:
			    	
			    	break;
		        case MotionEvent.ACTION_MOVE:
		        	int x = (int)event.getX();
					int y = (int)event.getY();
//		                
		            //requestRender();
		            break;
		        case MotionEvent.ACTION_UP:
		        	CommonItem.dt*=-1;	
		        	break;
		    }
//			
			
		
		return super.onTouchEvent(event);
	}
}
