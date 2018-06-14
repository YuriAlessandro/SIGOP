package br.ufrn.sigestagios.activities;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.UUID;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.utils.Constants;
import br.ufrn.sigestagios.utils.HttpHandler;

public class SignupActivity extends AppCompatActivity {
    private String TAG = "SIGNUP";
    private RelativeLayout errorArea;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        errorArea = findViewById(R.id.errorArea);
        errorMsg = findViewById(R.id.errorMsg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Criar novo usuário");
    }

    public void createUser(View v){

        errorArea.getLayoutTransition()
                .enableTransitionType(LayoutTransition.APPEARING);

        EditText first_name, last_name, username, password, email;
        first_name = findViewById(R.id.firstName);
        last_name = findViewById(R.id.lastName);
        username = findViewById(R.id.username);
        password = findViewById(R.id.signupPassword);
        email = findViewById(R.id.signupEmail);

        Boolean errors = false;
        if (username.getText().toString().equals("")
                || first_name.getText().toString().equals("")
                || last_name.getText().toString().equals("")
                || email.getText().toString().equals("")){
            errorArea.setVisibility(View.VISIBLE);
            errorMsg.setText("Todos os campos são obrigatórios");
            errors = true;
        }

        if (password.getText().length() < 8){
            errorArea.setVisibility(View.VISIBLE);
            errorMsg.setText("Escreva pelo menos 8 dígitos na senha.");
            errors = true;
        }

        if (!errors){
            new CreateUser().execute(first_name.getText().toString(),
                    last_name.getText().toString(),
                    username.getText().toString(),
                    password.getText().toString(),
                    email.getText().toString());
        }
        return;
    }
    private class CreateUser extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            String url = "https://sigop-api-yurialessandro.c9users.io/users";
            final String uuid = UUID.randomUUID().toString().replace("-", "");

            url += "?user_id=0";
            url += "&first_name=" + params[0];
            url += "&last_name=" + params[1];
            url += "&status=ativo";
            url += "&username=" + params[2];
            url += "&password=" + params[3];
            url += "&email=" + params[4];
            url += "&type=concedente";
            url += "&unity=0";

            HttpHandler sh = new HttpHandler();

            String req_url = url;
            String jsonStr = sh.makeServiceCall(req_url, "POST");
            if (jsonStr != null) {
                try {
                    JSONObject resp = new JSONObject(jsonStr);
                    if (resp.getBoolean("success")){
                        return true;
                    }else{
                        return false;
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Context ctx = getApplicationContext();
            if (aBoolean){
                Toast.makeText(ctx, "Usuário criado com sucesso. Faça login para continuar", Toast.LENGTH_LONG);
                finish();
            }else{
                errorArea.setVisibility(View.VISIBLE);
                errorMsg.setText("Nome de usuário ou email já estão sendo utilizados.");
                Toast.makeText(ctx, "Ocorreu um problema ao criar o usuário.", Toast.LENGTH_LONG);
            }
        }
    }

}
