package com.yang.gesturepassword;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity implements ISecurityGesture {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("锁屏测试,请在设置的时间内不操作，应用将会自动锁屏");
		setContentView(tv);
	}
	
	public void onUserInteraction() {
		GesturePasswordManager.getInstance().userInteraction();
	}
	
	
}
