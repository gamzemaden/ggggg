# 2D Graphics #

怎么用Android drawing API 画2D图:
  1. New drawable android xml file. rect.xml
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
	android:shape="rectangle">
	
	<gradient 
		android:startColor="#ffff0000"
		android:endColor="#80ff00ff"
		android:angle="45"/>
	
	<padding
		android:left="7dp"
		android:top="7dp"
		android:right="7dp"
		android:bottom="7dp"/>
	
	<corners android:radius="10dp"/>
	
</shape>
```
  1. 把画的图片作为button的背景
  1. 源代码： https://ggggg.googlecode.com/svn/trunk/AndroidbyLynda/Graphics_2D/