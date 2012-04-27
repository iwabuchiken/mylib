package mylib;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
//import org.apache.commons.net.ftp.FTPReply;

//import myapp.homebase2.FTPManager;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPManager {
	
	public static FTPClient getFTPClient() {
		return new FTPClient();
	}//public static FTPClient getFTPClient()

	/** ftpConnect()
	 * 
	 * @param ftpc
	 * @param remoteName
	 * @return
	 */
	public static boolean ftpConnect(FTPClient ftpc, String remoteName) {
		// connect to remote
		try {
			ftpc.connect(remoteName, 21);
		} catch (SocketException e) {
			// TODO 自動生成された catch ブロック
			return false;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			return false;
		}
		
		// check if connected
		return FTPReply.isPositiveCompletion(ftpc.getReplyCode());
		
//		return true;
		
	}//ftpConnect()
	
	public static boolean ftpLogin(FTPClient ftpc, String username, String password) {
		try {
			return ftpc.login(username, password);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			return false;
		}
	}//ftpLogin()

	public static boolean ftpLogout(FTPClient ftpc) {
		try {
			ftpc.logout();
			return true;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
			return false;
		}
	}//ftpLogout()
	
	public static boolean ftpDisconnect(FTPClient ftpc) {
		try {
			ftpc.disconnect();
			return true;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
			return false;
		}
	}//ftpDisconnect()
	
	public static boolean writeText(FTPClient ftpc, String text) {
		// enter passive
		ftpc.enterLocalPassiveMode();
		
		// get a stream
		ByteArrayInputStream in = new ByteArrayInputStream(text.getBytes());
		
		// store file
		try {
			ftpc.storeUniqueFile("/", in);
			// close stream
			in.close();
			return true;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
			try {
				in.close();
				return false;
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
//				e1.printStackTrace();
				return false;
			}
		}//try
		
	}//writeText()

	public static boolean writeBinary(FTPClient ftpc, String filePath) {
//	public static boolean writeBinary(FTPClient ftpc, InputStream in) {
		// open a stread
		FileInputStream in;
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e2) {
			// TODO 自動生成された catch ブロック
//			e2.printStackTrace();
			return false;
		}
		
		// set file type
		try {
			ftpc.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e2) {
			// TODO 自動生成された catch ブロック
//			e2.printStackTrace();
			return false;
		}
		
		// enter passive
		ftpc.enterLocalPassiveMode();
		
//		// get a stream
//		FileInputStream in;
//		try {
//			in = new FileInputStream(new File(filePath));
//		} catch (FileNotFoundException e2) {
//			// TODO 自動生成された catch ブロック
//			return false;
//		}
		
		// store file
		try {
//			ftpc.storeUniqueFile("/", in);
//			ftpc.storeUniqueFile("/", in);
//			ftpc.storeUniqueFile("/abc.jpg", in);
			ftpc.storeUniqueFile(in);
			
			// close stream
//			in.close();
			in.close();
			return true;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
			try {
//				in.close();
				in.close();
				return false;
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
//				e1.printStackTrace();
				return false;
			}
		}//try
		
	}//writeBinary()

}
