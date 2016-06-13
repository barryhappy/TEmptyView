package com.barryzhang.temptyview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Barry on 16/5/28.
 */
public class TViewUtil {

    public static void setEmptyView(AdapterView listView){
        TSimpleEmptyView view = genSimpleEmptyView(listView);
        listView.setEmptyView(view);
    }

    @NonNull
    private static TSimpleEmptyView genSimpleEmptyView(View view) {
        TSimpleEmptyView emptyView  = new TSimpleEmptyView(view.getContext(),null);
        ViewParent parent = view.getParent();
        if(parent instanceof ViewGroup){
            ((ViewGroup) parent).addView(emptyView);
            if(parent instanceof LinearLayout) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) emptyView.getLayoutParams();
                lp.height = -1;
                lp.gravity = Gravity.CENTER;
                emptyView.setLayoutParams(lp);
            }else if(parent instanceof RelativeLayout){
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) emptyView.getLayoutParams();
                lp.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
                emptyView.setLayoutParams(lp);
            }else if(parent instanceof FrameLayout){
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) emptyView.getLayoutParams();
                lp.height = -1;
                lp.gravity = Gravity.CENTER;
                emptyView.setLayoutParams(lp);
            }
        }
        return emptyView;
    }

    public static void setEmptyView(AdapterView listView, EmptyViewBuilder builder){
        builder.bindView(listView);
    }


    public static final class EmptyViewBuilder{
        private Context mContext;
        private ViewGroup.LayoutParams layoutParams;
        private String emptyText;
        private int iconSrc;
        private Drawable iconDrawable;
        private int emptyTextColor = -1;
        private int emptyTextSize;
        private boolean mShowIcon = true;
        private boolean mShowText = true;
        private boolean mShowButton = false;
        private View.OnClickListener mAction;
        private String actionText;


        public static EmptyViewBuilder getInstance(Context context){
            return new EmptyViewBuilder(context);
        }

        public void bindView(final AdapterView listView){
            final TSimpleEmptyView emptyView = genSimpleEmptyView(listView);
            removeExistEmptyView(listView);
            listView.setEmptyView(emptyView);
            setEmptyViewStyle(emptyView);
        }

        public void bindView(final RecyclerView recyclerView){

            final TSimpleEmptyView emptyView = genSimpleEmptyView(recyclerView);
            final RecyclerView.Adapter adapter =  recyclerView.getAdapter();
            if(adapter != null) {
                RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        if (adapter.getItemCount() > 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            emptyView.setVisibility(View.GONE);
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            emptyView.setVisibility(View.VISIBLE);
                        }
                    }
                };
                adapter.registerAdapterDataObserver(observer);
                observer.onChanged();
            }else{
                throw new RuntimeException("This RecyclerView has no adapter, you must call setAdapter first!");
            }
            setEmptyViewStyle(emptyView);

        }

        private void setEmptyViewStyle(TSimpleEmptyView emptyView) {
            if(emptyTextColor != -1) {
                emptyView.setTextColor(emptyTextColor);
            }
            if(emptyTextSize != 0) {
                emptyView.setTextSize(emptyTextSize);
            }
            if(!TextUtils.isEmpty(emptyText)){
                emptyView.setEmptyText(emptyText);
            }
            emptyView.setShowButton(mShowButton);
            emptyView.setShowText(mShowText);
            emptyView.setShowIcon(mShowIcon);
            if(iconSrc != 0) {
                emptyView.setIcon(iconSrc);
            }
            if(iconDrawable != null){
                emptyView.setIcon(iconDrawable);
            }
            if(layoutParams != null){
                emptyView.setLayoutParams(layoutParams);
            }
            if(!TextUtils.isEmpty(actionText)) {
                emptyView.setActionText(actionText);
            }
            if(mAction != null){
                emptyView.setAction(mAction);
            }
        }

        private EmptyViewBuilder(Context context) {
            this.mContext = context;
        }

        public EmptyViewBuilder setEmptyText(String text){
            this.emptyText = text;
            return this;
        }

        public EmptyViewBuilder setEmptyText(int textResID){
            this.emptyText = mContext.getString(textResID);
            return this;
        }

        public EmptyViewBuilder setEmptyTextColor(int color){
            this.emptyTextColor = color;
            return this;
        }

        public EmptyViewBuilder setEmptyTextSize(int textSize){
            this.emptyTextSize = textSize;
            return this;
        }

        public EmptyViewBuilder setEmptyTextSizePX(int textSizePX){
            this.emptyTextSize = px2sp(mContext,textSizePX);
            return this;
        }

        public EmptyViewBuilder setIconSrc(int iconSrc){
            this.iconSrc = iconSrc;
            this.iconDrawable = null;
            return this;
        }

        public EmptyViewBuilder setIconDrawable(Drawable iconDrawable){
            this.iconDrawable = iconDrawable;
            this.iconSrc = 0;
            return this;
        }

        public EmptyViewBuilder setLayoutParams(ViewGroup.LayoutParams layoutParams){
            this.layoutParams = layoutParams;
            return this;
        }


        public EmptyViewBuilder setShowIcon(boolean mShowIcon) {
            this.mShowIcon = mShowIcon;
            return this;
        }

        public EmptyViewBuilder setShowText(boolean showText) {
            this.mShowText = showText;
            return this;
        }

        public EmptyViewBuilder setShowButton(boolean showButton) {
            this.mShowButton = showButton;
            return this;
        }

        public EmptyViewBuilder setAction(View.OnClickListener onClickListener){
            this.mAction = onClickListener;
            return this;
        }

        public EmptyViewBuilder setActionText(String actionText){
            this.actionText  = actionText;
            return this;
        }

    }

    private static void removeExistEmptyView(AdapterView listView) {
        if(listView.getEmptyView() != null){
            ViewParent rootView = listView.getParent();
            if(rootView instanceof ViewGroup) {
                ((ViewGroup) rootView).removeView(listView.getEmptyView());
            }
        }
    }


    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }



    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


}
