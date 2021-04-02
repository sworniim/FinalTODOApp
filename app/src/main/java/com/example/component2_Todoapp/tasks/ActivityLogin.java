package com.example.component2_Todoapp.tasks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.component2_Todoapp.R;
import com.example.component2_Todoapp.database.AppDatabase;
import com.example.component2_Todoapp.database.TaskDao;
import com.example.component2_Todoapp.database.repoUser;

public class ActivityLogin extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    private EditText userEmail;
    private EditText userPassword;
    private AppDatabase database;
    private TaskDao taskDao;
    private ProgressDialog progressDialog;
    private com.example.component2_Todoapp.database.repoUser repouser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Checking user");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        database= AppDatabase.getInstance(this);
        repouser=new repoUser(database);
        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.new_register);
        userEmail = findViewById(R.id.email_id);
        userPassword = findViewById(R.id.password_id);




            signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));
            }
        });
    }

    private class AsyncLogin extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int userId = repouser.getUser(userEmail.getText().toString(), userPassword.getText().toString());
            if (userId != 0) {
                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                intent.putExtra("userId", Integer.toString(userId));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();

    }

    public void login(View view) {
        new AsyncLogin().execute();
    }
}