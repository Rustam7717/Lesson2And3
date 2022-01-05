package com.example.lesson2and3.posts;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.lesson2and3.App;
import com.example.lesson2and3.R;
import com.example.lesson2and3.data.models.Post;
import com.example.lesson2and3.databinding.FragmentPostsBinding;
import com.example.lesson2and3.form.FormFragment;
import com.example.lesson2and3.utils.OnItemClickListener;
import java.io.Serializable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment implements OnItemClickListener {

    private FragmentPostsBinding binding;
    private PostsAdapter adapter;
    private NavController controller;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvPost.setAdapter(adapter);
        App.api.getPosts(FormFragment.GROUP_ID).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

        binding.fab.setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.formFragment);
        });


        App.api.getPosts(FormFragment.GROUP_ID).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(int position) {
        Post post = adapter.getPost(position);
        if (post.getUserId() == FormFragment.USER_ID) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("post", (Serializable) post);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.formFragment, bundle);
        } else {
            Toast.makeText(requireActivity(), "couldn't overwrite", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLongClickListener(Post post) {
        if (post.getUserId() == FormFragment.USER_ID) {
            App.api.deletePost(post.getId()).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    adapter.deletePost(post);
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(requireActivity(), "Couldn't delete post", Toast.LENGTH_SHORT).show();
        }
    }
}