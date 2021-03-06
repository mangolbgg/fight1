 package com.example.fight1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fight1.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


 public class MainActivity extends AppCompatActivity {

     private ActivityMainBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<String> movielist = new ArrayList<String>();
        movielist.add("toy story");
        movielist.add("up");
        movielist.add("Shrek");

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movielist);
        binding.movieSpinner.setAdapter(spinnerAdapter);

        binding.addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String newMovie = binding.editText.getText().toString();
                spinnerAdapter.add(newMovie);
                spinnerAdapter.notifyDataSetChanged();
                binding.movieSpinner.setSelection(spinnerAdapter.getPosition(newMovie));
            }
        });
         binding.clearButton.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 binding.editText.setText("");
             }
         });

         binding.movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String selectedMovie = parent.getItemAtPosition(position).toString();
                 if(selectedMovie != null){
                     Toast.makeText(parent.getContext(), "Movie selected is " + selectedMovie,
                             Toast.LENGTH_LONG).show();
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {
             }
         });

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://api.openweathermap.org/data/2.5/")
                 .addConverterFactory(GsonConverterFactory.create()) //??????jason??????
                 .build();
         
         WeatherApiInterface weatherApiInterface = retrofit.create(WeatherApiInterface.class);

         Call<Root> call = weatherApiInterface.getWeather();

         call.enqueue(new Callback<Root>() {
             @Override
             public void onResponse(Call<Root> call, Response<Root> response) {
                 Root root = response.body();
                 double temp = root.getMain().getTemp() - 273.15;
                 binding.tempPrintOut.setText(String.valueOf((int)temp));

             }

             @Override
             public void onFailure(Call<Root> call, Throwable t) {
                 System.out.println(t.getMessage());

             }
         });

    }
}

//https://api.openweathermap.org/data/2.5/weather?lat=-37.813629&lon=144.963058&appid=6e1414b69f791c0220f72ab0982eb365