package com.fanqu.main.widget;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nineoldandroids.view.ViewHelper;



public class SearchBarLayout extends RelativeLayout{
	

	private EditText mEditText;
	private MyTextView mTextView;
	private TextView cancelTextView;

	private int mEditTextOffsetWidth;
	private float mLeftEdge;
	private String mHint;

	private int mPadding10;

	private  Drawable imgX;
	
	
	private boolean hasAnimtion;

	/**
	 * 在绘制较卡的界面,调用该方法,已关闭动画效果
	 * @param hasAnimtion
     */
	public void setHasAnimtion(boolean hasAnimtion) {
		this.hasAnimtion = hasAnimtion;
	}
	public EditText getEditor(){
		return mEditText;
	}

	
	public SearchBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs,defStyleAttr);
	}

	public SearchBarLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs,0);
	}

	public SearchBarLayout(Context context) {
		super(context);
		init(null,0);
	}

	private void init(AttributeSet attrs, int defStyleAttr) {
		setHasAnimtion(true);
		if(attrs!=null&&defStyleAttr>0){
			mEditText=new EditText(getContext(), attrs, defStyleAttr);
			mTextView=new MyTextView(getContext(), attrs, defStyleAttr);
		}else if(attrs!=null){
			mEditText=new EditText(getContext(), attrs);
			mTextView=new MyTextView(getContext(), attrs);
		}else{
			mEditText=new EditText(getContext());
			mTextView=new MyTextView(getContext());
		}
		cancelTextView=new CancelTextView(getContext());
		this.setBackgroundColor(Color.parseColor("#FFFFFF"));
		mPadding10 = dip2px(10);
		//EditText
		if(!isInEditMode()){
			if(!TextUtils.isEmpty(mTextView.getHint())){
				mHint=mTextView.getHint().toString();
			}
		}
		mEditText.setHint("");
		mEditText.setSingleLine(true);
		mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		mEditText.setBackgroundResource(0);
		imgX= ContextCompat.getDrawable(getContext(), R.mipmap.common_icon_close);
		imgX.setBounds(0, 0, imgX.getIntrinsicWidth(),imgX.getIntrinsicHeight());
		
        manageClearButton();
		mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					cancelFocus();
				} else {
					focused();
				}
				manageClearButton();
			}
		});
		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				if (s.length() <= 0) {
					mTextView.setHint(mHint);
				} else {
					mTextView.setHint("");
				}
				manageClearButton();
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {

			}
			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		//TextView
		mTextView.setText("");
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setBackgroundColor(Color.TRANSPARENT);
		final Drawable search=ContextCompat.getDrawable(getContext(),R.mipmap.ic_search);
		search.setBounds(0, 0, search.getIntrinsicWidth(),search.getIntrinsicHeight());
		mTextView.setCompoundDrawables(search, null, null, null);
		mTextView.setCompoundDrawablePadding(mPadding10);
		mTextView.setEnabled(false);
        int left= (int) getResources().getDimension(R.dimen.dimen_20px);
		mEditText.setPadding(search.getIntrinsicWidth()+(int)(mPadding10*1.75f),mPadding10 ,(int)(mPadding10*1.5f),mPadding10);
		LayoutParams reparams =new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		reparams.setMargins(left,0,0,0);
		addView(mEditText, reparams);
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		addView(mTextView, params);
		
		cancelTextView.setPadding(mPadding10, 0, (int) getResources().getDimension(R.dimen.dimen_20px), 0);
		//CancelTextView
		cancelTextView.setText(R.string.cancel);
		float size=mTextView.getTextSize()/(getContext().getResources().getDisplayMetrics().density);
		cancelTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
		cancelTextView.setTextColor(getContext().getResources().getColor(R.color.transparent));
		if(!isInEditMode()){
			addClickEffect(cancelTextView).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelFocus();
			}
			});
		}
		LayoutParams params2=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params2.addRule(RelativeLayout.CENTER_VERTICAL);
		//addView(cancelTextView, params2);
		
		setFocusable(true);
		setFocusableInTouchMode(true);
		requestFocusFromTouch();
		
		
		mEditText.setOnTouchListener(new OnTouchListener() {
			boolean result=false;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					if (event.getX() > mEditText.getWidth() - mEditText.getPaddingRight() - imgX.getIntrinsicWidth()) {
						mEditText.setText("");
                        removeClearButton();
						
						//还原
						if(ViewHelper.getTranslationX(cancelTextView)!=0){
							setFocusable(true);
							setFocusableInTouchMode(true);
							requestFocusFromTouch();
							if(hasAnimtion){
								ObjectAnimator.ofFloat(mTextView,"translationX",ViewHelper.getTranslationX(mTextView),0).start();
							}else{
								ViewHelper.setTranslationX(mTextView,0);
							}
							if(cancelSearchLayout!=null)
								cancelSearchLayout.OnCancel();
							return true;
						}
						result=true;
					 }else{
						 result=false;
					 }
					break;
				}
				return result;
			}
		});
	}
	private boolean translationAnimator;
	
	private void focused() {
		
		if(ViewHelper.getTranslationX(cancelTextView)!=0){//不等于0 说明没有显示到屏幕内
			ObjectAnimator animator=ObjectAnimator.ofFloat(cancelTextView,"translationX",ViewHelper.getTranslationX(cancelTextView),0);
			final int mWidth=mEditText.getWidth();
			int mEditTextViewRealWidth=mWidth-cancelTextView.getWidth();

			mEditTextOffsetWidth=mWidth-mEditTextViewRealWidth-mPadding10;//需要减少的宽度
			final LayoutParams params=(LayoutParams) mEditText.getLayoutParams();
			animator.addUpdateListener(new AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator arg0) {
					params.width=(int) (mWidth-arg0.getAnimatedFraction()*mEditTextOffsetWidth);
					mEditText.requestLayout();
				}
			});
			if(hasAnimtion){
				animator.start();
			}else{
				params.width=(int) (mWidth-1.0f*mEditTextOffsetWidth);
				ViewHelper.setTranslationX(cancelTextView, 0);
			}
		}
		if(ViewHelper.getTranslationX(mTextView)==-mLeftEdge||translationAnimator){
			return;
		}
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"translationX",ViewHelper.getTranslationX(mTextView),-mLeftEdge);
		objectAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				translationAnimator = false;
			}

			@Override
			public void onAnimationStart(Animator animation) {
				translationAnimator = true;
			}
		});
		if(hasAnimtion){
			objectAnimator.start();
		}else{
			ViewHelper.setTranslationX(mTextView,-mLeftEdge);
		}
	}
	private boolean cancelAnimator;
	
	public void cancelFocus() {
		if (mEditText.isFocused() && !cancelAnimator) {
			if(TextUtils.isEmpty(mEditText.getText().toString())){
				ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTextView,"translationX",ViewHelper.getTranslationX(mTextView),0);
				objectAnimator.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						cancelAnimator = false;
					}
					@Override
					public void onAnimationStart(Animator animation) {
						cancelAnimator = true;
					}
				});
				if(hasAnimtion){
					objectAnimator.start();
				}else{
					ViewHelper.setTranslationX(mTextView, 0);
				}
				if(cancelSearchLayout!=null)
					cancelSearchLayout.OnCancel();
			}
		}
		if(ViewHelper.getTranslationX(cancelTextView)==0){//说明已经出屏幕
			ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(cancelTextView,"translationX",0,cancelTextView.getWidth());
			final int mWidth=mEditText.getWidth();
			final LayoutParams params=(LayoutParams) mEditText.getLayoutParams();
			objectAnimator.addUpdateListener(new AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator arg0) {
					params.width=(int) (mWidth+arg0.getAnimatedFraction()*mEditTextOffsetWidth);
					mEditText.requestLayout();
				}
			});
			if(hasAnimtion){
				objectAnimator.start();
			}else{
				ViewHelper.setTranslationX(cancelTextView, cancelTextView.getWidth());
				params.width=(int) (mWidth+1.0f*mEditTextOffsetWidth);
			}
			
			this.setFocusable(true);
			this.setFocusableInTouchMode(true);
			this.requestFocus();
			hiddenKeybord();
		}
	}

	private void hiddenKeybord() {
		InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
		boolean isOpen = manager.isActive(); // 判断软键盘是否打开
		if (isOpen && manager != null) {
			manager.hideSoftInputFromWindow(mEditText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
		}else{
			manager.showSoftInputFromInputMethod(mEditText.getWindowToken(), 0);
		}
	}
	private int dip2px(float dpValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	public void manageClearButton() {
		if (TextUtils.isEmpty(mEditText.getText().toString()))
			removeClearButton();
		else
			addClearButton();
	}

	private void addClearButton() {
		mEditText.setCompoundDrawables(mEditText.getCompoundDrawables()[0],mEditText.getCompoundDrawables()[1], imgX,mEditText.getCompoundDrawables()[3]);
	}

	private void removeClearButton() {
		mEditText.setCompoundDrawables(mEditText.getCompoundDrawables()[0],mEditText.getCompoundDrawables()[1], null,mEditText.getCompoundDrawables()[3]);
	}
	private class MyTextView extends TextView{
		private int mWidth;
		public int getViewWidth() {
			return mWidth;
		}
		public MyTextView(Context context, AttributeSet attrs,int defStyleAttr) {
			super(context, attrs,defStyleAttr);
		}
		public MyTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		public MyTextView(Context context) {
			super(context);
		}
		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			mWidth=w;
			mLeftEdge=this.getLeft()-mPadding10*1.5f;
			mTextView.setMinWidth(mTextView.getViewWidth());
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
	}
	private class CancelTextView extends TextView{
		public CancelTextView(Context context, AttributeSet attrs,int defStyleAttr) {
			super(context, attrs,defStyleAttr);
		}
		public CancelTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}
		public CancelTextView(Context context) {
			super(context);
		}
		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			ViewHelper.setTranslationX(this,w);
		}
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
	}
	private OnCancelSearchLayout cancelSearchLayout;
	public interface OnCancelSearchLayout{
		public void OnCancel();
	}
	public void setCancelSearchLayout(OnCancelSearchLayout cancelSearchLayout) {
		this.cancelSearchLayout = cancelSearchLayout;
	}

	/**
	 * 添加点击效果
	 * @param v	需要触发的View
	 * @return view自身
	 */
	public View addClickEffect(View v){
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
						ObjectAnimator.ofFloat(v, "alpha",0.3f,1.0f).start();
						break;
					case MotionEvent.ACTION_DOWN:
						ObjectAnimator.ofFloat(v, "alpha",1.0f,0.3f).start();
						break;
				}
				return false;
			}
		});
		return v;
	}
	
}
