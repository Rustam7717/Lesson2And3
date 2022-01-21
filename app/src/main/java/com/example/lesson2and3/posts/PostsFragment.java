package com.example.lesson2and3.posts;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.lesson2and3.databinding.FragmentPostsBinding;
import com.example.lesson2and3.utils.OnItemClick;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment {

    private FragmentPostsBinding binding;
    private PostsAdapter adapter;
    private NavController navController;
    private List<Post> models = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsAdapter();
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
        getData();
        setupListener();
        delete();
    }

    private void setupRecycler() {
        binding.rvPost.setAdapter(adapter);

    }

    private void getData() {
        App.api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                adapter.setList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void setupListener() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_postsFragment_to_formFragment);
            }
        });
    }

    private void delete() {
        adapter.setItemClick(new OnItemClick() {
            @Override
            public void onLongClickListener(int position) {
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setMessage("You want to delete selected POST?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        App.api.deletePost(position).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                adapter.remove(position);

                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }

            @Override
            public void onItemClickListener(int position) {

            }


        });
        {


        }


    }
}
