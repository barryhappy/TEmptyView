
[English README is HERE](./README.md)
# TEmptyView

TEmptyView是一个小轮子，希望能够更简单地设置EmptyView，免除每次设置emptyView都要写xml之苦。
支持AdapterView(ListView/GridView等)、RecyclerView。
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TEmptyView-green.svg?style=true)](https://android-arsenal.com/details/1/3886)

## 导入 
```groovy
	compile 'com.barryzhang:temptyview:0.0.1' 
```

## 使用 

### 一：初始化： 

设置一些自定义的默认属性——这一步并不是必须的，如果没有进行初始化，下一步设置的时候会使用自带的默认属性。

```java
TEmptyView.init(TViewUtil.EmptyViewBuilder.getInstance(context)
       .setShowText(true)
       .setEmptyText("NO DATA")
       .setShowButton(false)
       ...
       .setShowIcon(true));
```

### 二：设置emptyView

#### 1.极简 
```java
    TViewUtil.setEmptyView(listView)
```
![IMAGE1](./etc/demo1.png)
#### 2.高级自定义
TEmptyView的可视控件由三部分组成：ImageView、TextView、Button，可以对每个部分进行自定义  

```java
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

![IMAGE2](./etc/demo2.png)
## 注意事项

#### 1.RecyclerView
用法基本AdapterView一样，但是需要先设置adapter。 

```
	recyclerView.setAdapter(adapter);
	TViewUtil.EmptyViewBuilder.getInstance(getContext())
           .setEmptyText("This is a empty view in RecyclerView")
           ...
           .bindListView(recyclerView);
```

#### 2.小提示：
初始化(TEmptyView.init)的时机——建议放到Application的onCreate方法中，不过实际上只需要保证在第一次设置emptyView前进行初始化即可。如果在其他地方初始化，记得传入的context值最好`不要使用Activity`，否则有可能会引起内存泄漏。




*仍在开发中……以上所有内容都随时可能会变 (笑)*  


许可 ![License](https://img.shields.io/hexpm/l/plug.svg)
-----------------------------------------------------

http://www.apache.org/licenses/LICENSE-2.0