package com.barryzhang.temptyviewdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment {

    int layoutRes;
    int type ;

    List<String> testData = new ArrayList<>();

    View buttonSwitch;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        layoutRes = bundle.getInt("layoutRes",0);
        type = bundle.getInt("type",0);
    }

    @NonNull
    protected static Bundle genBundle(int type, int layoutRes) {
        Bundle args = new Bundle();
        args.putInt("type",type);
        args.putInt("layoutRes",layoutRes);
        return args;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes, container, false);
        afterInflater(view);
        buttonSwitch = view.findViewById(R.id.buttonSwitch);
        if(buttonSwitch != null){
            buttonSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchData();
                }
            });
        }

        return view;
    }

    protected void switchData() {
        if(testData.isEmpty()){
            for(int i = 0 ; i < 20 ; i ++){
                testData.add("Recycler View test data, Item: " + i);
            }
        }else{
            testData.clear();
        }
    }

    public View getContentView(){

        return null;
    }

    protected abstract void afterInflater(View view) ;

}
