package myapp.tweetappsample;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//import myapp.homebase2.MyLib.FTPManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;;

public class MyLib {
	
	// String for "generatePassword()"
	static String charSource = "abcdefghijklmn";

	// context
	static Context context;
	
	// constructor
	public MyLib() {
		
	}//public MyLib()
	
	// constructor
	public MyLib(Context context) {
		this.context = context;
	}//public MyLib()
	
	// generate a password
	public static String generatePassword() {
		
		// StringBuilder
		StringBuilder sb = new StringBuilder();
		
		// get chars
		Random rd;
		int judge;
		for (int i = 0; i < 5; i++) {
			// instance
			rd = new Random((long) (Math.random()*1000000));
			
			// alphabet or number ?
			judge = rd.nextInt(2);
			
			
			// get a char
			sb.append(charSource.charAt(rd.nextInt(charSource.length())));
		}//for (int i = 0; i < array.length; i++)
		
		// get a string		
		
		return sb.toString();
		
	}//generatePassword()

	// generate a password
	public static String generatePassword(int type) {
		
		// StringBuilder
		StringBuilder sb = new StringBuilder();
		
		// Random
		Random rd =		/* used to pick up a char							*/
				new Random((long) (Math.random()*1000000));
		Random rd2 =		/* used as a flag for chars and numbers	*/
				new Random((long) (Math.random()*1000000));
		/*
		 *  build a string
		 */

		// switching
		if (type == 0) {							// alphabets only
			
			for (int i = 0; i < 5; i++) {
				sb.append(charSource.charAt(rd.nextInt(charSource.length())));
			}//for (int i = 0; i < 5; i++)
			
		} else if (type == 1){					// numbers only
			
			for (int i = 0; i < 5; i++) {
				sb.append(String.valueOf(rd.nextInt(10)));
			}//for (int i = 0; i < 5; i++)
			
		} else if (type == 2){					// mixture
			
			for (int i = 0; i < 5; i++) {
				int flag = rd2.nextInt(2);
				switch (flag) {
					case 0:
						sb.append(charSource.charAt(rd.nextInt(charSource.length())));
						break;
					case 1:
						sb.append(String.valueOf(rd.nextInt(10)));
						break;
					default:
						break;
				}//switch (flag)
			}//for (int i = 0; i < 5; i++)
			
		}//if (type == 0)
		
		return sb.toString();
		
	}//generatePassword()

	public static String generatePassword(int type, int passwdLength) {
		// StringBuilder
		StringBuilder sb = new StringBuilder();
		
		// Random
		Random rd =		/* used to pick up a char							*/
				new Random((long) (Math.random()*1000000));
		Random rd2 =		/* used as a flag for chars and numbers	*/
				new Random((long) (Math.random()*1000000));
		/*
		 *  build a string
		 */

		// switching
		if (type == 0) {							// alphabets only
			
			for (int i = 0; i < passwdLength; i++) {
				sb.append(charSource.charAt(rd.nextInt(charSource.length())));
			}//for (int i = 0; i < passwdLength; i++)
			
		} else if (type == 1){					// numbers only
			
			for (int i = 0; i < passwdLength; i++) {
				sb.append(String.valueOf(rd.nextInt(10)));
			}//for (int i = 0; i < passwdLength; i++)
			
		} else if (type == 2){					// mixture
			// flag for judging if the generated password is uniform
			boolean isUniform = true;
//			while (!isUniform) {
			while (isUniform) {
				// generate a password
				for (int i = 0; i < passwdLength; i++) {
					int flag = rd2.nextInt(2);
					switch (flag) {
						case 0:
							sb.append(charSource.charAt(rd.nextInt(charSource.length())));
							break;
						case 1:
							sb.append(String.valueOf(rd.nextInt(10)));
							break;
						default:
							break;
					}//switch (flag)
				}//for (int i = 0; i < passwdLength; i++)
				
//				//debug
//				isUniform = false;
				
				//judge
				if (isUniformString(sb.toString()) == false) {
					isUniform = false;
				} else {//if (isUniform)
					sb = new StringBuilder();
//					sb.append('*');
				}
			}//while (!isUniform)
		}//if (type == 0)
		
		return sb.toString();
		
	}//public static String generatePassword(int type, int passwdLength)
	
	public static boolean isUniformString(String targetString) {
		// get the type of the first char in the string
		int firstCharType = Character.getType(targetString.charAt(0));
		
		// flag: default => false (assumes the string is not uniform)
//		boolean isSame = false;
		
		// judge
		for (int i = 1; i < targetString.length(); i++) {
			if (Character.getType(targetString.charAt(0)) 
					!= Character.getType(targetString.charAt(i))) {
				return false;
			}//if (targetString.charAt(0) != targetString.charAt(i))
		}//for (int i = 1; i < targetString.length(); i++)
		
		/* if the rest of the chars are same as the first one
		 * 	=> return true (i.e., the string is consisted of the same type of char)
		 */
		return true;
	}

	// getStringメソッド(メッセージ取得処理)
	// source=C:\WORKS\WORKSPACES2\Sample\Chapter7\PictureJumpSample\src\jp\co\techfun\picturejump\AppUtil.java
    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }//public static String getString(Context context, int resId)

	public static Bitmap resizePicture(Bitmap picture, int maxWidth, int maxHeight) {
		// secure picture
		if (picture == null) {
            return null;
        }
		
		// 幅と高さで大きいほうに合わせてリサイズ
        int width = 0;
        int height = 0;

        if (picture.getWidth() > picture.getHeight()) {
            width = maxWidth;
            height = width * picture.getHeight() / picture.getWidth();
        } else {
            height = maxHeight;
            width = height * picture.getWidth() / picture.getHeight();
        }
		
        // 算定したサイズでビットマップ生成
        Bitmap result = Bitmap.createScaledBitmap(picture, width, height, true);
        
		return result;
	}//public static Bitmap resizePicture
	
//	public static void showTextDialog(String title, String message) {
	public void showTextDialog(String title, String message) {
    	// define dialog
	   	 AlertDialog.Builder dialog	= new AlertDialog.Builder(context);
			 
	   	 // set title
	   	 dialog.setTitle(title);
	   	 
	   	 // set message
	   	 dialog.setMessage(message);
	   	 
	   	 // set positive button
	   	 dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自動生成されたメソッド・スタブ
					// debug
					Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
				}
	   	 });//dialog.setPositiveButton()
	   	 
	   	 // show dialog
	   	 dialog.show();
	   	 
	}//showTextDialog()
	
}//public class MyLib
