package com.example.lesson2and3.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lesson2and3.data.models.Post;
import com.example.lesson2and3.databinding.ItemPostBinding;
import com.example.lesson2and3.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> posts = new ArrayList<>();

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,false);
        return new PostsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;
        public PostsViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBind(Post post) {
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            binding.tvUserId.setText(String.valueOf(post.getUserId()));
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onLongClickListener(getAdapterPosition());
                    return false;
                }
            });


        }
    }
}
