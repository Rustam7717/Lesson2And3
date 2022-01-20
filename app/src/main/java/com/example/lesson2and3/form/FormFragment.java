package com.example.lesson2and3.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lesson2and3.App;
import com.example.lesson2and3.R;
import com.example.lesson2and3.data.models.Post;
import com.example.lesson2and3.databinding.FragmentFormBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    public static final int USER_ID = 8;
    public static final int GROUP_ID = 5;
    private NavController controller;
    private Post post2;

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

        binding.btnSend.setOnClickListener(view1 -> {
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
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                        navController.popBackStack();
                    } else {
                        String title = binding.etTitle.getText().toString();
                        String content = binding.etContent.getText().toString();
                        Post post = new Post(title, content, USER_ID, GROUP_ID);
                        App.api.update(post2.getId(), post).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                Toast.makeText(requireActivity(), "reload", Toast.LENGTH_SHORT).show();
                                NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                                controller.navigate(R.id.postsFragment);
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });

        });
    }
}
