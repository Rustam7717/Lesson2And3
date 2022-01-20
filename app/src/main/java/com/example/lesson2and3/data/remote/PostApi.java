package com.example.lesson2and3.data.remote;

import com.example.lesson2and3.data.models.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts(int groupId);

    @POST("/posts")
    Call<Post> createPost(
            @Body Post post);


    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") int id);

    @PUT("/posts/{id}")
    Call<Post> update(@Path("id") int id, @Body Post post);



}
