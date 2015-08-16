package com.yang.gesturepassword;

import java.util.Random;

import android.Manifest;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class Util {

	public static boolean checkPermission(Context context, String permName) {
		return context.getPackageManager().checkPermission(permName,
				context.getPackageName()) == 0;
	}

	public static String getDeviceIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String imei = "";
		if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
			imei = tm.getDeviceId();
			if (TextUtils.isEmpty(imei)) {
				imei = Settings.Secure.getString(context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}
			if (TextUtils.isEmpty(imei)) {
				imei = "1234567890";
			}
		}
		return imei;
	}

	/**
	 * 获取长度为len的字符串
	 * @param len
	 * @return
	 */
	public static String getRandomString(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(255) + 1;
			sb.append((char) number);
		}
		return sb.toString();
	}
}
