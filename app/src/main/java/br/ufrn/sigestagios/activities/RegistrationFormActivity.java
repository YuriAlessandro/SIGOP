package br.ufrn.sigestagios.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.activities.OfferActivity;
import br.ufrn.sigestagios.models.Internship;
import br.ufrn.sigestagios.models.Internship;

public class RegistrationFormActivity extends AppCompatActivity {
    Internship Internship;
    EditText title, description, responsible, email, companyName, numberPositions, grantValue, auxTransport, endOffer;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        responsible = (EditText) findViewById(R.id.responsible);
        email = (EditText) findViewById(R.id.email);
        companyName = (EditText) findViewById(R.id.companyName);
        numberPositions = (EditText) findViewById(R.id.numberPositions);
        grantValue = (EditText) findViewById(R.id.grantValue);
        auxTransport = (EditText) findViewById(R.id.auxTransport);
        endOffer = (EditText) findViewById(R.id.endOffer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void register() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = df.parse(endOffer.getText().toString());

        Internship = new Internship(description.getText().toString(),
                          email.getText().toString(),
                          companyName.getText().toString(),
                          responsible.getText().toString(),
                          Integer.parseInt(numberPositions.getText().toString()),
                          Integer.parseInt(grantValue.getText().toString()),
                          Integer.parseInt(auxTransport.getText().toString()),
                          myDate,
                          title.getText().toString());

        Intent resultIntent = new Intent(this, OfferActivity.class);
        resultIntent.putExtra("InternshipRegistered", Internship);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
