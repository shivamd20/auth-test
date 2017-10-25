package com.jaison.authtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.hasura.sdk.Hasura;
import io.hasura.sdk.HasuraUser;
import io.hasura.sdk.ProjectConfig;
import io.hasura.sdk.exception.HasuraException;
import io.hasura.sdk.responseListener.AuthResponseListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView tv = (TextView) findViewById(R.id.textview);

        ProjectConfig config = new ProjectConfig.Builder()
                .setProjectName("avatar99")
                .build();

        Hasura.setProjectConfig(config)
                .enableLogs() // not included by default
                .initialise(this);

        if (Hasura.getClient().getUser().isLoggedIn()) {
            tv.setText("Logged In");
            Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
        } else {
            tv.setText("Logged In");
            Toast.makeText(this, "NOT Logged In", Toast.LENGTH_SHORT).show();
            HasuraUser user = Hasura.getClient().getUser();
            user.setUsername("username");
            user.setPassword("password");
            user.login(new AuthResponseListener() {
                @Override
                public void onSuccess(String s) {
                    tv.setText("Logged In");
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(HasuraException e) {
                    tv.setText("Logged In failed");
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
