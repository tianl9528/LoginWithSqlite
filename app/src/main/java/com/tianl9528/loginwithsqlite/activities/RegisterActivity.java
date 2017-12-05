package com.tianl9528.loginwithsqlite.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tianl9528.loginwithsqlite.R;
import com.tianl9528.loginwithsqlite.model.User;
import com.tianl9528.loginwithsqlite.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText username = null;
    private EditText password = null;
    private EditText email = null;
    private Button register;
    private Button cancel;

    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        email = findViewById(R.id.editText5);
        register = findViewById(R.id.button4);
        cancel = findViewById(R.id.button5);
    }

    private void initListeners() {
        register.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                postDataToSQLite();
                break;
            case R.id.button5:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if ((username.getText() == null) & (password.getText() == null) & (email.getText() == null)) {
            Toast.makeText(getApplicationContext(), "Please enter the complete information", Toast.LENGTH_LONG).show();
        }

        if (!databaseHelper.checkUser(username.getText().toString().trim())) {

            user.setName(username.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPwd(password.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
            username.setText(null);
            email.setText(null);
            password.setText(null);
        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(getApplicationContext(), "The username has been registered", Toast.LENGTH_LONG).show();
        }

    }
}
