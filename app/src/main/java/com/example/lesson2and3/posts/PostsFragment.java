package com.example.lesson2and3.posts;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.lesson2and3.databinding.AlertDialogBinding;
import com.example.lesson2and3.databinding.FragmentPostsBinding;
import com.example.lesson2and3.utils.OnItemClickListener;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment {

    FragmentPostsBinding binding;
    private PostsAdapter adapter;
    private NavController controller;
    java.util.HashMap<Integer, String> hashMap = new java.util.HashMap<>();
    public PostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsAdapter();
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        controller = navHostFragment.getNavController();

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
        getStudent();
        setupListener();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.action_postsFragment_to_formFragment);
            }
        });

        App.api.getPosts().enqueue(new Callback<List<Post>>() {
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

    private void setupListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onLongClickListener(int position) {
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show();
            }
        });
        private void Dialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder()
                    .setTitle(dialodOk)
                    .setMessage(delete)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Activity.setDialogResult(true);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Activity.setDialogResult(false);
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void getStudent() {
        hashMap.put(1, "Satybaldieva Adelya");
        hashMap.put(2, "Murodilova Ademi");
        hashMap.put(3, "Murodilova Adelya");
        hashMap.put(4, "Murzakmatov Rinat");
        hashMap.put(5, "Djumagulov Alym");
        hashMap.put(6, "Djakypov Aliaskar");
        hashMap.put(7, "Ryskulbekov Bayastan");
        hashMap.put(8, "Djoldoshbaev Rustam");
        hashMap.put(9, "Chyntemirov Alisher");
        hashMap.put(10, "Salbarov Tynchtyk");
        hashMap.put(11, "Osmonaliev Nurmuhammed");


    }

}