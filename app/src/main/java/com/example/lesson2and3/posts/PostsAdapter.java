package com.example.lesson2and3.posts;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
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
    private ItemPostBinding binding;
    private OnItemClickListener onItemClickListener;

    public PostsAdapter() {
        posts = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPosts(List<Post> posts) {
        this.posts = posts;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemPostBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false);
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
    public void removeItem(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
    }

    public Post getPost(int position) {
        return posts.get(position);
    }


    public void removePost(Post post) {
        posts.remove(post);
        notifyDataSetChanged();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;
        public PostsViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(view -> {
                onItemClickListener.onClick(getAdapterPosition());
            });
            itemView.setOnLongClickListener(view -> {
                onItemClickListener.onLongClickListener(posts.get(getAdapterPosition()));
                return true;
            });
        }

        public void onBind(Post post) {
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            binding.tvUserId.setText(String.valueOf(post.getUserId()));


        }
    }
}
