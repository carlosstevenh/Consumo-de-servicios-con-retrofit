package com.spn.consumoapi.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.spn.consumoapi.R;
import com.spn.consumoapi.core.common.EndPoint;
import com.spn.consumoapi.core.inta.request.PostGet;
import com.spn.consumoapi.core.inta.response.Post;
import com.spn.consumoapi.databinding.ActivityMainBinding;
import com.spn.consumoapi.helpers.Consutar;
import com.spn.consumoapi.viewModel.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Consutar{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMainActivityModel(new MainActivityViewModel());
        binding.executePendingBindings();
        binding.setClik(this);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public void getPost(View v) {
        PostGet postGet = EndPoint.RETROFIT.create(PostGet.class);
        Call<List<Post>> post = postGet.getPost();
        post.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                switch (response.code()){
                    case 200:
                        List<Post> p = response.body();
                        for (Post item:p) {
                            Log.d(""+item.getId(),"-"+item.getTitle());
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
}
