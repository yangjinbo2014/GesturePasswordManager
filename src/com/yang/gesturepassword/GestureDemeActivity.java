package com.yang.gesturepassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class GestureDemeActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		setContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		Button setGestureBtn = new Button(this);
		setGestureBtn.setText("设置手势密码");
		setGestureBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GestureDemeActivity.this,SetGesturePasswordActivity.class));
			}
		});
		
		Button verifyGestureBtn = new Button(this);
		verifyGestureBtn.setText("验证手势密码");
		verifyGestureBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GestureDemeActivity.this,GesturePasswordActivity.class));
			}
		});
		
		Button testBtn = new Button(this);
		testBtn.setText("启动自动锁屏界面");
		testBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(GestureDemeActivity.this,TestActivity.class));
			}
		});
		
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(setGestureBtn);
		layout.addView(verifyGestureBtn);
		layout.addView(testBtn);
		
		GesturePasswordManager.getInstance().startWatch(getApplication());
//		GesturePasswordManager.getInstance().setShowGestureImmediately(false);
		
	}
}
