package com.w3engineers.jitpackbottomnav.fragment.tabby;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.w3engineers.jitpackbottomnav.R;

/**
 * RecyclerView的HeaderView，简单的展示一个TextView
 */
public class SampleHeader extends RelativeLayout {

    public SampleHeader(Context context) {
        super(context);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.sample_header, this);
    }
}