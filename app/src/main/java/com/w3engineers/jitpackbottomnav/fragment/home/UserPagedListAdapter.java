package com.w3engineers.jitpackbottomnav.fragment.home;

/*
 *  ****************************************************************************
 *  * Created by : Md. Azizul Islam on 11/30/2018 at 12:14 PM.
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
import android.graphics.Path;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavDirections;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.core.kbasekit.ui.base.BaseViewHolder;
import com.core.kbasekit.ui.base.ItemClickListener;
import com.w3engineers.jitpackbottomnav.R;
import com.w3engineers.jitpackbottomnav.data.model.User;
import com.w3engineers.jitpackbottomnav.databinding.ItemUserBinding;
import org.jetbrains.annotations.NotNull;

public class UserPagedListAdapter extends PagedListAdapter<User, UserPagedListAdapter.UserViewHolder> {
    private Context context;
    private static ItemClickListener itemClickListener;

    public UserPagedListAdapter(Context context, ItemClickListener itemClickListener) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_user, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);
        if (user != null) {
            holder.bind(user);
        }
    }


    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemUserBinding binding;

        public UserViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.deleteButton.setOnClickListener(this);
            binding.userName.setOnClickListener(this);
            binding.userId.setOnClickListener(this);
        }

        void bind(User user) {
            binding.setUser(user);
        }

        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            if (index < 0 || index >= getItemCount()) return;
            itemClickListener.onItemClick(view, getItem(getAdapterPosition()));
        }
    }

    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.id == newItem.id;

        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem == newItem;
        }
    };
}
