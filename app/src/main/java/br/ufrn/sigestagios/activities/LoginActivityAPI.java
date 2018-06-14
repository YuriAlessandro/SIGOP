package br.ufrn.sigestagios.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.User;
import br.ufrn.sigestagios.utils.HttpHandler;

public class LoginActivityAPI extends AppCompatActivity {
    private String TAG = "LoginActivityAPI";
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_api);

        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPwd);
    }

    public void Login(View v){
        Toast.makeText(this, "Carregando...", Toast.LENGTH_LONG)
             .show();
        new DoLogin().execute(username.getText().toString(), password.getText().toString());
    }

    private class DoLogin extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            String url = "https://sigop-api-yurialessandro.c9users.io/auth";
            final String uuid = UUID.randomUUID().toString().replace("-", "");

            url += "?username=" + strings[0];
            url += "&password=" + strings[1];

            HttpHandler sh = new HttpHandler();

            String req_url = url;
            String jsonStr = sh.makeServiceCall(req_url, "POST");
            if (jsonStr != null) {
                try {
                    JSONObject resp = new JSONObject(jsonStr);
                    return resp;
                } catch (JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject resp) {
            super.onPostExecute(resp);
            Log.i(TAG, resp.toString());

            try {
                if (!resp.getBoolean("success")){
                    Toast.makeText(getApplicationContext(), resp.getString("msg"), Toast.LENGTH_LONG).show();
                }else{
                    JSONObject user = resp.getJSONObject("user");
                    User loggedUser;

                    String email = user.getString("email");
                    String firstName = user.getString("first_name");
                    String lastName = user.getString("last_name");
                    String login = user.getString("login");
                    String type = user.getString("type");
                    int idUser = user.getInt("idUser");
                    int unity = user.getInt("unity");

                    loggedUser = new User(idUser, unity, login, firstName, lastName, email, type);

                    Intent startOffersActivity = new Intent(LoginActivityAPI.this, OfferActivity.class);
                    startOffersActivity.putExtra("user", loggedUser);
                    startOffersActivity.putExtra("isFromAPI", true);
                    LoginActivityAPI.this.startActivity(startOffersActivity);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
