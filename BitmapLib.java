package mylib;

//import myapp.homebase2.MyLib;
import android.content.Context;
import android.graphics.Bitmap;
//import android.view.View;
import android.widget.ImageView;

public class BitmapLib {

	/** setPicture()
	 * 
	 * @param context
	 * @param iv
	 * @param bmp
	 */
	public static void setPicture(Context context, ImageView iv, Bitmap bmp) {
		// set image
		iv.setImageBitmap(MyLib.resizePicture(bmp, 180, 180));
		
	}//setPicture()
}
