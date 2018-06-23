package com.example.nelsoft.apinewscustomer;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiNewsCustomer {
    //@GET("v2/top-headlines?country=us&apiKey=abd3d114bf6548cea6ee1bcf1ee41830")
    @GET("top-headlines")
    Call<ApiNewsCustomer> getArticles(@Query("country")  String country, @Query("apiKey")  String apiKey);
}
