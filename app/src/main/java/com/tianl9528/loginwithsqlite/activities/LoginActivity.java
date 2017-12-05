package com.tianl9528.loginwithsqlite.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tianl9528.loginwithsqlite.R;
import com.tianl9528.loginwithsqlite.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = LoginActivity.this;

    private EditText username = null;
    private EditText password = null;
    private TextView deadline;
    private TextView counter;
    private Button sign_up;
    private Button sign_in;
    private Button exit_all;
    int count = 3;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        username = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        deadline = findViewById(R.id.textView4);
        counter = findViewById(R.id.textView5);
        counter.setText(Integer.toString(count));
        sign_up = findViewById(R.id.button1);
        sign_in = findViewById(R.id.button2);
        exit_all = findViewById(R.id.button3);

    }

    private void initListeners() {
        sign_up.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        exit_all.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                verifyFromSQLite();
                break;
            case R.id.button2:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }

    }

    private void verifyFromSQLite() {
        if ((username.getText() == null) & (password.getText() == null)) {
            Toast.makeText(getApplicationContext(),"Please enter the complete information",Toast.LENGTH_LONG).show();
        }

        if (databaseHelper.checkUser(username.getText().toString().trim(), password.getText().toString().trim())) {
            username.setText(null);
            password.setText(null);
            count = 3;
            counter.setText(Integer.toString(count));
            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
//            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
//            accountsIntent.putExtra("NAME", username.getText().toString().trim());
//            startActivity(accountsIntent);
        } else {
            Toast.makeText(getApplicationContext(), "no no no", Toast.LENGTH_SHORT).show();
            deadline.setTextColor(Color.RED);
            count--;
            counter.setText(Integer.toString(count));
            password.setText("");
            if (count == 0) {
                sign_in.setEnabled(false);
            }

        }
    }
}
