package com.zhangqie.notepad.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class LinearLayoutForListView extends ListView {

	 public LinearLayoutForListView(Context context) {
	        super(context);
	    }
	    public LinearLayoutForListView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }
	    public LinearLayoutForListView(Context context, AttributeSet attrs,
	        int defStyle) {
	        super(context, attrs, defStyle);
	    }
	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
	        MeasureSpec.AT_MOST);
	        super.onMeasure(widthMeasureSpec, expandSpec);
	    }
}
