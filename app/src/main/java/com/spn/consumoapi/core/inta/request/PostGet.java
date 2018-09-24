package com.spn.consumoapi.core.inta.request;

import com.spn.consumoapi.core.inta.response.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostGet {
    @GET("posts")
    Call<List<Post>> getPost();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

}
