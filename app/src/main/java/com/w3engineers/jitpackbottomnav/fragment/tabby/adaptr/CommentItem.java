package com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr;


import static com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr.CommentExpandAdapter.TYPE_PERSON;
import static com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr.ExpandableRecyclerAdapter.TYPE_HEADER;

/**
 * Created by Lzx on 2016/9/30.
 */

public class CommentItem extends ExpandableRecyclerAdapter.ListItem {

    public String Text;

    public CommentItem(String group) {
        super(TYPE_HEADER);
        Text = group;
    }

    public CommentItem(String first, String last) {
        super(TYPE_PERSON);
        Text = first + " " + last;
    }
}
