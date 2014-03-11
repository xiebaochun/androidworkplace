package com.example.draganddrop;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.cocos2d.nodes.CCLabel;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.util.Xml;

public class Question {
	
	CCLabel text_;
	MyQuestion myQuestion;
    public Question(MainActivity activity)
    {
        myQuestion=new MyQuestion();    	
    	//xmlpullparser parser = Xml.newPullParser();
    	XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();
			InputStream in_s = activity.getApplicationContext().getAssets().open("questions.xml");
	        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            Log.v("question","load question.xml success!");
            parseXML(parser);
		} catch (XmlPullParserException e) {
			Log.v("question","load question.xml error1:"+e);
			e.printStackTrace();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			Log.v("question","load question.xml error2:"+e);
			e.printStackTrace();
		}
    }
	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
	//	ArrayList<product> products = null;
        int eventType = parser.getEventType();
//      //  Product currentProduct = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
//            //    	products = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
//                    if (name == "product"){
//                        //currentProduct = new Product();
//                    } else if (currentProduct != null){
//                        if (name == "productname"){
//                            currentProduct.name = parser.nextText();
//                        } else if (name == "productcolor"){
//                        	currentProduct.color = parser.nextText();
//                        } else if (name == "productquantity"){
//                            currentProduct.quantity= parser.nextText();
//                        }  
//                    }
//                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
//                    if (name.equalsIgnoreCase("product") && currentProduct != null){
//                    	products.add(currentProduct);
                    } 
//            }
            eventType = parser.next();
        }
//
//        printProducts(products);
	}
	//inner class
	 class MyQuestion{

		 level level1;
		 level level2;
		 level level3;
	    class level
	    {
	       String redCardText;
	       String blueCardText;
	       String greenCardText;
	    }
	} 
    
}

