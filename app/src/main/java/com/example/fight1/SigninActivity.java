package com.example.fight1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fight1.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.backTextView.setOnClickListener(v ->{
            Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.signInButton.setOnClickListener(v -> {
            validationWhenSignin();
        });

    }

    private void validationWhenSignin(){
        binding.emailInPut.getText();
        //empty
        if(binding.emailInPut.getText().toString().isEmpty()){
            binding.emailInPutWrong.setError(getResources().getString(R.string.email_empty_error));
        }
        //格式
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInPut.getText().toString()).matches()){
            binding.emailInPutWrong.setError(getResources().getString(R.string.email_format_error));
        }
        else{
            binding.emailInPutWrong.setErrorEnabled(false);
        }
        //正确
        //测试
    }
}