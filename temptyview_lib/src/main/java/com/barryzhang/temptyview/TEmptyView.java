package com.barryzhang.temptyview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Barry on 16/5/28.
 */
public class TEmptyView extends FrameLayout {

    private static TViewUtil.EmptyViewBuilder mConfig = null;


    private float mTextSize;
    private int mTextColor;
    private String mEmptyText;
    private int mIconSrc;
    private OnClickListener mOnClickListener;
    private String actionText;

    private boolean mShowIcon = true;
    private boolean mShowText = true;
    private boolean mShowButton = false;

    private ImageView mImageView;
    private TextView mTextView;
    private Button mButton;

    public static void init(TViewUtil.EmptyViewBuilder defaultConfig){
        TEmptyView.mConfig = defaultConfig;
    }

    public static boolean hasDefaultConfig(){
        return TEmptyView.mConfig != null;
    }

    public static TViewUtil.EmptyViewBuilder getConfig() {
        return mConfig;
    }

    public TEmptyView(Context context) {
        this(context, null);
    }

    public TEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context,R.layout.simple_empty_view,this);

        mTextView = (TextView) findViewById(R.id.t_emptyTextView);
        mImageView = (ImageView) findViewById(R.id.t_emptyImageIcon);
        mButton = (Button) findViewById(R.id.t_emptyButton);

    }

    public void setShowIcon(boolean mShowIcon) {
        this.mShowIcon = mShowIcon;
        mImageView.setVisibility(mShowIcon ? VISIBLE : GONE);
    }

    public void setShowText(boolean showText) {
        this.mShowText = showText;
        mTextView.setVisibility(showText ? VISIBLE : GONE);
    }

    public void setShowButton(boolean showButton) {
        this.mShowButton = showButton;
        mButton.setVisibility(showButton ? VISIBLE : GONE);
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
        mTextView.setTextSize(mTextSize);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor ) {
        this.mTextColor = mTextColor;
        mTextView.setTextColor(mTextColor);
    }

    public String getEmptyText() {
        return mEmptyText;
    }

    public void setEmptyText(String mEmptyText) {
        this.mEmptyText = mEmptyText;
        mTextView.setText(mEmptyText);
    }

    public void setIcon(int mIconSrc) {
        this.mIconSrc = mIconSrc;
        mImageView.setImageResource(mIconSrc);
    }

    public void setIcon(Drawable drawable) {
        this.mIconSrc = 0;
        mImageView.setImageDrawable(drawable);
    }

    public void setAction(OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
        mButton.setOnClickListener(onClickListener);
    }

    public void setActionText(String actionText){
        this.actionText  = actionText;
        mButton.setText(actionText);
    }
}
