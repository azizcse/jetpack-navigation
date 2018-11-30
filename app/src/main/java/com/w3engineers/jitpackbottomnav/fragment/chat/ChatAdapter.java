package com.w3engineers.jitpackbottomnav.fragment.chat;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/30/2018 at 6:43 PM.
 *  * Email : azizul@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Azizul Islam on 11/30/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.w3engineers.jitpackbottomnav.R;
import com.w3engineers.jitpackbottomnav.data.model.Message;

public class ChatAdapter extends PagedListAdapter<Message, RecyclerView.ViewHolder> {
    private final int TEXT_IN = 1;
    private final int TEXT_OUT = 2;
    private Context context;

    public ChatAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Message item = getItem(position);

        if (item.incoming) {
            return TEXT_IN;
        } else {
            return TEXT_OUT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TEXT_IN) {
            view = LayoutInflater.from(context).inflate(R.layout.item_text_message_in, parent, false);
            return new TextInHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_text_message_out, parent, false);
            return new TextOutHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Message item = getItem(position);

        if(item == null) return;

        if(type == TEXT_IN){
            ((TextInHolder)holder).bind(item);
        }else {
            ((TextOutHolder)holder).bind(item);
        }
    }


    private class TextInHolder extends RecyclerView.ViewHolder {
        TextView message;
        public TextInHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.text_view_message);
        }

        void bind(Message msg){
            message.setText(msg.message);
        }
    }

    private class TextOutHolder extends RecyclerView.ViewHolder {
        TextView message;
        public TextOutHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.text_view_message);
        }
        void bind(Message msg){
            message.setText(msg.message);
        }
    }


    private static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(Message oldItem, Message newItem) {
            return oldItem.id == newItem.id;

        }

        @Override
        public boolean areContentsTheSame(Message oldItem, Message newItem) {
            return oldItem == newItem;
        }
    };
}
