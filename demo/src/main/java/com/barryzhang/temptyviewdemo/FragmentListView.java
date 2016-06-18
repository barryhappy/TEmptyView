package com.barryzhang.temptyviewdemo;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.barryzhang.temptyview.TViewUtil;

public class FragmentListView extends BaseFragment {

    AdapterView listView ;

    BaseAdapter adapter ;


    public static BaseFragment newInstance(int type, int layoutRes) {

        BaseFragment fragment = new FragmentListView();
        fragment.setArguments(genBundle(type,layoutRes));
        return fragment;
    }


    @Override
    protected void afterInflater(View view) {
        listView = (AdapterView) view.findViewById(R.id.listView);
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return testData.size();
            }

            @Override
            public Object getItem(int i) {
                return testData.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView tv = new TextView(getContext());
                tv.setText(testData.get(i));
                return tv;
            }
        };
        listView.setAdapter(adapter);

        if(type == 0){
            TViewUtil.setEmptyView(listView);

        }else if (type == 1){
            TViewUtil.EmptyViewBuilder.getInstance(getContext())
                    .setEmptyTextSize(12)
                    .setEmptyTextColor(Color.RED)
                    .setIconSrc(R.drawable.ic_menu_share)
                    //or
                    .bindView(listView);

        }else if(type == 2){

            TViewUtil.EmptyViewBuilder.getInstance(getContext())
                    .setEmptyTextSize(14)
                    .setIconDrawable(getResources().getDrawable(R.drawable.airplane))
                    .bindView(listView);
        }else if(type == 3){

            TViewUtil.EmptyViewBuilder.getInstance(getContext())
                    .setEmptyTextSize(14)
                    .setEmptyTextColor(Color.GRAY)
                    .setEmptyText("This is a empty view in fragment 3")
                    .setIconSrc(R.drawable.ic_menu_gallery)
                    .setShowText(true)
                    .setShowButton(true)
                    .setActionText("Click me")
                    .setAction(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(),"Yes, clicked~",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .bindView(listView);
        } else if(type == 4){

            TViewUtil.EmptyViewBuilder.getInstance(getContext())
                    .setEmptyTextSize(14)
                    .setEmptyTextColor(Color.GRAY)
                    .setEmptyText("This is a empty view in fragment 4 \n GridView")
                    .setIconSrc(R.drawable.ic_menu_camera)
                    .bindView(listView);
        }
    }

    @Override
    protected void switchData() {
        super.switchData();
        adapter.notifyDataSetChanged();
    }

    public AdapterView getListView() {
        return listView;
    }

}
