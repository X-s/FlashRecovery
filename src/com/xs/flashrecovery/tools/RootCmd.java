package com.xs.flashrecovery.tools;

import java.io.DataOutputStream;
import android.util.Log;

public class RootCmd {
	private static final String LOG_TAG = "StockSettings";

	public static int RunRootCmd(String paramString) {
		try {
			Log.i(LOG_TAG, paramString);
			Process localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(
					localProcess.getOutputStream());
			String str = paramString + "\n";
			localDataOutputStream.writeBytes(str);
			localDataOutputStream.flush();
			localDataOutputStream.writeBytes("exit\n");
			localDataOutputStream.flush();
			localProcess.waitFor();
			return localProcess.exitValue();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 1;
	}
}
