package br.ufrn.sigestagios.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import br.ufrn.sigestagios.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void login(View v){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void loginNotSigaa(View v){
        Toast.makeText(getApplicationContext(), "Em breve", Toast.LENGTH_LONG).show();
    }

    public void register(View v){
        Intent i = new Intent(this, signup.class);
        startActivity(i);
    }
}
