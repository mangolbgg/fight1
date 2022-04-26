package com.example.fight1;

import retrofit2.Call;
import retrofit2.http.GET;


public interface WeatherApiInterface {
    @GET("weather?lat=-37.813629&lon=144.963058&appid=6e1414b69f791c0220f72ab0982eb365")
    Call<Root> getWeather();  //listRepos(@Path("user") String user)
}
