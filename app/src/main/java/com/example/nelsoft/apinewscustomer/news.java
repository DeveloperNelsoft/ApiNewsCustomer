package com.example.nelsoft.apinewscustomer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Adapter;
import android.widget.ListAdapter;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class news extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vi = inflater.inflate(R.layout.news, container, false);

        final  ListView view =  vi.findViewById(R.id.listViewNews);
        loadNewsJSON(view);
        return vi;
    }

    private void loadNewsJSON(final ListView view){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IApiNewsCustomer restClient = retrofit.create(IApiNewsCustomer.class);
        Call<ApiNewsCustomer> call = restClient.getArticles("us", "abd3d114bf6548cea6ee1bcf1ee41830");

        call.enqueue(new Callback<ApiNewsCustomer>() {
            @Override
            public void onResponse(Call<ApiNewsCustomer> call, Response<ApiNewsCustomer> response) {
                switch (response.code()) {
                    case 200:
                        ApiNewsCustomer data = response.body();
                        List<Article> ListNews = data.getArticles();
                        loadNewsList(view, ListNews);
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<ApiNewsCustomer> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void loadNewsList(final ListView view, List<Article> artList){
        final ArrayList<String> newTitleList = new ArrayList<String>();

        getURLNews(newTitleList, artList);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, newTitleList);
        view.setAdapter(arrayAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
               String selectedNew =  newTitleList.get(position);
               Toast.makeText(getActivity().getApplicationContext(), "Selected News: "+ selectedNew,   Toast.LENGTH_LONG).show();

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("urlNews_" + Integer.toString(position), selectedNew);
                editor.commit();

                Intent myWebView = new Intent( getActivity().getApplicationContext() , webviewloader.class);
                myWebView.putExtra("urlNews", selectedNew);
                startActivity(myWebView);
            }
        });
    }

    void getURLNews(ArrayList<String> newTitleList, List<Article> artList)
    {
        for(int i = 0; i < artList.size(); i++)
        {
            newTitleList.add(artList.get(i).getUrl());
        }
    }
}