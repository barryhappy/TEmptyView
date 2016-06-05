# TEmptyView
希望能够更简单地设置EmptyView，支持AdapterView(ListView/GridView)、RecyclerView
## 使用
grade:

```
	compile 'com.barryzhang.temptyview:xxx' 
	//哈哈哈，我开玩笑的啦，暂时还没法使用
```

### AdapterView（ListView、GridView 等）
#### 最简单
```
    TViewUtil.setEmptyView(listView)
```
这样就会给listView设置一个简单的默认EmptyView。
当然这样肯定不是你想要的~ 
#### 自定义
TEmptyView的可视控件由三部分组成：ImageView、TextView、Button，可以对每个部分进行自定义  

```
	TViewUtil.EmptyViewBuilder.getInstance(this)
      .setEmptyText("Nothing here~")
      .setEmptyTextSize(12)
      .setEmptyTextColor(Color.GRAY)
      .setIconDrawable(drawableAirplan)
      .setShowButton(true)
      .setActionText("Click")
      .setAction(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getApplicationContext(),
                      "Yes, clicked~",Toast.LENGTH_SHORT).show();
          }
      })
      .bindListView(listView);
```
### RecyclerView
用法基本AdapterView一样，但是需要先设置adapter。 

```
	recyclerView.setAdapter(adapter);
	TViewUtil.EmptyViewBuilder.getInstance(getContext())
           .setEmptyTextSize(14)
           .setEmptyTextColor(Color.GRAY)
           .setEmptyText("This is a empty view in RecyclerView")
           .setIconSrc(R.drawable.ic_menu_camera)
           ...
           .bindListView(recyclerView);
```

### 仍在开发中……以上所有内容都随时可能会变


