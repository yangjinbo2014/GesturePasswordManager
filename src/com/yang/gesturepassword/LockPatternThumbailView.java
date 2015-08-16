package com.yang.gesturepassword;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yang.gesturepassword.LockPatternView.Cell;

public class LockPatternThumbailView extends View {

	private float mSquareWidth;
	private float mSquareHeight;

	private Bitmap mBitmapBtnSelected;
	private Bitmap mBitmapBtnNormal;

	private boolean[][] mPatternSelected = new boolean[3][3];

	private Paint mPaint = new Paint();

	public LockPatternThumbailView(Context context) {
		super(context);
		init(context, null);
	}

	public LockPatternThumbailView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LockPatternThumbailView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		mBitmapBtnSelected = getBitmapFor(R.drawable.lock_thumbail_select);
		mBitmapBtnNormal = getBitmapFor(R.drawable.lock_thumbail_normal);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mPatternSelected[i][j] = false;
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int height = MeasureSpec.getSize(heightMeasureSpec);
		int viewWidth = width;
		int viewHeight = height;
		viewWidth = viewHeight = Math.min(width, height);
		setMeasuredDimension(viewWidth, viewHeight);
	}

	private Bitmap getBitmapFor(int resId) {
		return BitmapFactory.decodeResource(getContext().getResources(), resId);
	}

	public void setPattern(List<Cell> pattern) {
		if (pattern != null && pattern.size() > 0) {
			for (int i = 0; i < pattern.size(); i++) {
				Cell cell = pattern.get(i);
				mPatternSelected[cell.row][cell.column] = true;
			}
		}
		postInvalidate();
	}
	
	public void resetPattern() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mPatternSelected[i][j] = false;
			}
		}
		postInvalidate();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		final int width = w - getPaddingLeft() - getPaddingRight();
		mSquareWidth = width / 3.0f;

		final int height = h - getPaddingTop() - getPaddingBottom();
		mSquareHeight = height / 3.0f;
	}

	@Override
	public void onDraw(Canvas canvas) {
		// draw the circles
		final int paddingTop = getPaddingTop();
		final int paddingLeft = getPaddingLeft();

		final float squareWidth = mSquareWidth;
		final float squareHeight = mSquareHeight;

		for (int i = 0; i < 3; i++) {
			float topY = paddingTop + i * squareHeight;
			// float centerY = getPaddingTop() + i * mSquareHeight +
			// (mSquareHeight / 2);
			for (int j = 0; j < 3; j++) {
				float leftX = paddingLeft + j * squareWidth;
				Bitmap bitmap = null;
				if (mPatternSelected[i][j]) {
					bitmap = mBitmapBtnSelected;
				} else {
					bitmap = mBitmapBtnNormal;
				}
				final int width = bitmap.getWidth();
				final int height = bitmap.getHeight();
				int offsetX = (int) ((squareWidth - width) / 2f);
				int offsetY = (int) ((squareHeight - height) / 2f);
				canvas.drawBitmap(bitmap, leftX + offsetX, topY + offsetY,
						mPaint);
			}
		}
	}

	// private void drawPoint(Canvas,) {
	//
	// }
}
