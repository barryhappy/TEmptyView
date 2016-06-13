package com.barryzhang.temptyviewdemo;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barryzhang.temptyview.TViewUtil;

public class FragmentRecyclerView extends BaseFragment {
    RecyclerView recyclerView ;

    final RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView view = new TextView(getContext());
            RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view){};
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TextView)holder.itemView).setText(testData.get(position));
        }

        @Override
        public int getItemCount() {
            return testData.size();
        }
    };


    public static BaseFragment newInstance(int type, int layoutRes) {

        BaseFragment fragment = new FragmentRecyclerView();
        fragment.setArguments(genBundle(type,layoutRes));
        return fragment;
    }

    @Override
    protected void afterInflater(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        testData.clear();
        for(int i = 0 ; i < 20 ; i ++){
            testData.add("Recycler View test data, Item: " + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        TViewUtil.EmptyViewBuilder.getInstance(getContext())
                .setEmptyTextSize(14)
                .setEmptyTextColor(Color.GRAY)
                .setEmptyText("This is a empty view in fragment 5 \n RecyclerView")
                .setIconSrc(R.drawable.ic_menu_camera)
                .bindView(recyclerView);

    }


    @Override
    protected void switchData() {
        super.switchData();
        adapter.notifyDataSetChanged();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
