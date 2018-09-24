package com.spn.consumoapi.core.inta.request;

import com.spn.consumoapi.core.inta.response.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostPost {
    @POST("posts")
    Call<Post> savePost(@Body Post post );

}
