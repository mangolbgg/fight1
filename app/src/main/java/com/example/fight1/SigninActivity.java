package com.example.fight1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fight1.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.backTextView.setOnClickListener(v ->{
            Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        binding.signInButton.setOnClickListener(v -> {
            validationWhenSignin();
        });

    }

    private void validationWhenSignin(){
        boolean emailIsValid = false;
        //empty
        if(binding.emailInPut.getText().toString().isEmpty()){
            //getResources().getString(R.string.email_empty_error) 这一串是调用string里面的字符串 来显示
            binding.emailInPutWrong.setError(getResources().getString(R.string.email_empty_error));
            emailIsValid = false;
        }
        //格式
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInPut.getText().toString()).matches()){
            binding.emailInPutWrong.setError(getResources().getString(R.string.email_format_error));
            emailIsValid = false;
        }
        else{
            binding.emailInPutWrong.setErrorEnabled(false);
            emailIsValid = true;
        }
        //这里同时加入其他的验证 用&& 相连接
        if (emailIsValid){
            binding.signinProgressBar.setVisibility(View.VISIBLE); //把那个转圈的加载小圈 变成可见的 如果不想要可见 可以把 visible改成gone.

            mAuth.createUserWithEmailAndPassword(binding.emailInPut.getText().toString(), binding.passwordInPut.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                binding.signinProgressBar.setVisibility(View.GONE);
                                Toast.makeText(SigninActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(SigninActivity.this, LoginActivity.class));
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SigninActivity.this, "Sign up failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });



//            new Thread(() -> { //新建一个线程，避免了 ui 和后端 双线程导致显示顺序的错误
//                try {
//                    Thread.sleep(5000); //5000是5秒， 系统会在执行完上一条代码后 在这里停留5秒 需要try catch不然会报错
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//                runOnUiThread(() -> {
//                    //一个pop up 在所有验证成功之后在这个界面弹出一个小气泡显示text内容
//                    Toast.makeText(SigninActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
//                    binding.signinProgressBar.setVisibility(View.GONE);
//                });
//            }).start(); //start 开启这个线程



        }
    }
}