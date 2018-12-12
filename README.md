# SlidingMeun
## 类似QQ 侧滑菜单

<div align="center">
<image src="https://github.com/Golabe/SlidingMeun/blob/master/gifs/a.gif?raw=true" width="400"/>
</div>
<div align="center">
<image src="https://github.com/Golabe/SlidingMeun/blob/master/gifs/b.gif?raw=true" width="400"/>
</div>
<div align="center">
<image src="https://github.com/Golabe/SlidingMeun/blob/master/gifs/c.gif?raw=true" width="400"/>
</div>
## 使用
### GRADLE 
``xml
implementation 'com.github.golabe.slidingmeun:library:1.0.0'
```
### XML
```xml
  <com.github.golabe.slidingmenu.library.SlidingMenu
        android:layout_width="match_parent"
        app:sliding_menu_default_show="right"
        app:sliding_menu_padding="40dp"
        app:sliding_menu_animation="true"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">
            <!--菜单布局-->
            <include layout="@layout/layout_menu" />
            <!--主布局-->
            <include layout="@layout/layout_main" />
        </LinearLayout>
    </com.github.golabe.slidingmenu.library.SlidingMenu>
```

### ATTRS
```xml
<declare-styleable name="SlidingMenu">
        <attr name="sliding_menu_padding" format="dimension" />
        <attr name="sliding_menu_default_show" format="flags">
            <flag name="left" value="0x0001" />
            <flag name="right" value="0x0002" />
        </attr>
        <attr name="sliding_menu_animation" format="boolean" />
    </declare-styleable>
```

