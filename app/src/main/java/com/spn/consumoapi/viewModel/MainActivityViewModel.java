package com.spn.consumoapi.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.spn.consumoapi.BR;
import com.spn.consumoapi.R;
import com.spn.consumoapi.core.common.EndPoint;
import com.spn.consumoapi.core.inta.request.PostGet;
import com.spn.consumoapi.core.inta.request.PostPost;
import com.spn.consumoapi.core.inta.response.Post;
import com.spn.consumoapi.model.Parametro;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends BaseObservable {

    private Parametro parametro;

    public MainActivityViewModel() {
        this.parametro = new Parametro("");
    }

    public void changeParametro(String parametro){
        this.parametro.setParametro(parametro);
    }
    public void getPost(){
        PostGet postGet = EndPoint.RETROFIT.create(PostGet.class);
        Call<List<Post>> post = postGet.getPost();
        post.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                switch (response.code()){
                    case 200:
                        List<Post> p = response.body();
                        for (Post item:p) {
                            Log.d("Con view model",item.getId()+" - "+item.getTitle());
                        }
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });


    }

    public void getPostParametro(){
        Log.e("ete","parametro" + parametro);
        PostGet postGet = EndPoint.RETROFIT.create(PostGet.class);
        Call<Post> call = postGet.getPost(5);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                switch (response.code()){
                    case 200:
                        Post p = response.body();
                        Log.e("Con parametro",p.getId()+" - "+p.getTitle());
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public void setPost(){
        Post p = new Post();
        p.setId(101);
        p.setUserId(111);
        p.setTitle("Metodo post");
        p.setBody("Este es un post con retrofit");
        PostPost post = EndPoint.RETROFIT.create(PostPost.class);
        Call<Post> call = post.savePost(p);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.e("entro","aqui" + response.code());
                if(response.isSuccessful()) {
                    Log.i("registro", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("error", "Unable to submit post to API.");
            }
        });
    }
}
