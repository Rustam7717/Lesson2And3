package com.example.lesson2and3.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lesson2and3.data.models.Post;
import com.example.lesson2and3.databinding.ItemPostBinding;
import com.example.lesson2and3.utils.OnItemClick;
import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> list = new ArrayList<>();
    private OnItemClick itemClick;

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void remove(int position) {
        list.remove(list.get(position));
        notifyDataSetChanged();
    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(
                parent.getContext()
        ), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostBinding binding;

        public ViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post postModel) {
            binding.tvTitle.setText(postModel.getTitle());
            binding.tvContent.setText(postModel.getContent());
            binding.tvUserId.setText(String.valueOf(postModel.getUserId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.onItemClickListener(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemClick.onLongClickListener(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}


