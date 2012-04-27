package myapp.ftpsample;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
//import org.apache.commons.net.ftp.FTPReply;

//import myapp.homebase2.FTPManager;

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
}
