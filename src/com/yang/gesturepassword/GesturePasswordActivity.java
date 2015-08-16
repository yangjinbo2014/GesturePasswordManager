package com.yang.gesturepassword;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.yang.gesturepassword.LockPatternView.Cell;

public class GesturePasswordActivity extends Activity {

	private LockPatternView lockPatternView;

	private LockPatternView.OnPatternListener unlockListener = new LockPatternView.OnPatternListener() {

		@Override
		public void onPatternStart() {

		}

		@Override
		public void onPatternCleared() {

		}

		@Override
		public void onPatternCellAdded(List<Cell> pattern) {

		}

		@Override
		public void onPatternDetected(List<Cell> pattern) {
			String password = LockPatternView.patternToString(pattern);
			if (GesturePasswordManager.getInstance().verifyGesturePassword(
					GesturePasswordActivity.this, password)) {
				Toast.makeText(GesturePasswordActivity.this, "密码正确",
						Toast.LENGTH_SHORT).show();
				GesturePasswordManager.getInstance().setShowGestureImmediately(
						false);
				finish();
			} else {
				lockPatternView.setInStealthMode(false);
				lockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Wrong);
				lockPatternView.invalidate();
				Toast.makeText(GesturePasswordActivity.this, "密码错误",
						Toast.LENGTH_SHORT).show();
			}
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_gesture_password);
		lockPatternView = (LockPatternView) findViewById(R.id.lockPatternView);
		// lockPatternView.setTactileFeedbackEnabled(false);
		lockPatternView.setOnPatternListener(unlockListener);
		lockPatternView.setDiameterFactor(0.1f);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
