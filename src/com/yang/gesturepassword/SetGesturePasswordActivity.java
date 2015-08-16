package com.yang.gesturepassword;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.gesturepassword.LockPatternView.Cell;

public class SetGesturePasswordActivity extends Activity {

	private TextView gestureExplainText;
	private LockPatternThumbailView lockPatternThumbail;
	private LockPatternView lockPatternView;
	private String gesturePassword;

	private LockPatternView.OnPatternListener setListener = new LockPatternView.OnPatternListener() {

		@Override
		public void onPatternStart() {

		}

		@Override
		public void onPatternCleared() {
			lockPatternThumbail.resetPattern();
		}

		@Override
		public void onPatternCellAdded(List<Cell> pattern) {
			lockPatternThumbail.setPattern(pattern);
		}

		@Override
		public void onPatternDetected(List<Cell> pattern) {
	
			String str = LockPatternView.patternToString(pattern);
			lockPatternView.clearPattern();
			if (gesturePassword == null) {
				gesturePassword = str;
				gestureExplainText.setText(R.string.set_lockpatter_again);
				return;
			}
			if (gesturePassword.equals(str)) {
				Toast.makeText(SetGesturePasswordActivity.this, "设置成功", Toast.LENGTH_SHORT)
						.show();
				GesturePasswordManager.getInstance().saveGesturePassword(SetGesturePasswordActivity.this, gesturePassword);
				gestureExplainText.setText(R.string.input_gesture_password);
			} else {
				lockPatternView.setInStealthMode(false);
				lockPatternView
						.setDisplayMode(LockPatternView.DisplayMode.Wrong);
				lockPatternView.invalidate();
				Toast.makeText(SetGesturePasswordActivity.this, "两次密码不一样，请重试", Toast.LENGTH_SHORT)
						.show();
			}
		}

	};

	/**
	 * Find the Views in the layout<br />
	 * <br />
	 * Auto-created on 2015-08-07 09:45:21 by Android Layout Finder
	 * (http://www.buzzingandroid.com/tools/android-layout-finder)
	 */
	private void initViews() {
		gestureExplainText = (TextView) findViewById(R.id.gestureExplainText);
		lockPatternThumbail = (LockPatternThumbailView) findViewById(R.id.lockPatternThumbail);
		lockPatternView = (LockPatternView) findViewById(R.id.lockPatternView);
		lockPatternView.setDiameterFactor(0.1f);
		lockPatternView.setTactileFeedbackEnabled(true);
		lockPatternView.setOnPatternListener(setListener);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_set_gesture_password);
		initViews();
	}

}
