import java.net.*;
import java.nio.channels.*;
import java.io.*;
public class helloword {
	 public static void main(String[] args) {
	        System.out.println("Hello World!\n"); // Display the string.
	        Rectangle rect1=new Rectangle(200,200);
	        square s1=new square(100);
	        
	        System.out.println(""+rect1.Height);
	        System.out.println(s1.getArea());
	        
	        try {
				URL website=new URL("http://photo.yupoo.com/qzone1618034856/Cy3lpiGy/medish.jpg");
				ReadableByteChannel rbc=Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream("C://dowload.jpg");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
