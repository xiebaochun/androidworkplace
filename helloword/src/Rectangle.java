
public class Rectangle {
     protected int Width;
     protected int Height;
	 point origin;
	
    public Rectangle(int w,int h){
    	this.origin=new point(0,0);
    	this.Width=w;
    	this.Height=h;
    }
   public void setOrigin(int x,int y){
	   this.origin.setXY(x, y);
   }
   public void setWidth(int w){
	   this.Width=w;
   }
   public void setHeight(int h){
	   this.Height=h;
   }
   public int getWidth(){
	   return this.Width;
   }
   public int getHeight(){
	   return this.Height;
   }
   public int getArea(){
	   return this.Height*this.Width;
   }
}
