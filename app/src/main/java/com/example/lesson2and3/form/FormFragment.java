package com.example.lesson2and3.form;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lesson2and3.App;
import com.example.lesson2and3.R;
import com.example.lesson2and3.data.models.Post;
import com.example.lesson2and3.databinding.FragmentFormBinding;
import com.example.lesson2and3.posts.PostsFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private static final int USER_ID = 8;
    private static final int GROUP_ID = 5;
    private NavController controller;
    public FormFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        controller = navHostFragment.getNavController();

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();
                Post post = new Post(
                        title,
                        content,
                        USER_ID,
                        GROUP_ID
                );

                App.api.createPost(post).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                        if (response.isSuccessful()) {
                            controller.popBackStack();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Post> call, Throwable t) {

                    }
                });

            }
        });

        App.api.putPost().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

//        App.api.deletePost().enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if(response.isSuccessful()){
//
//
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        })
    }
}