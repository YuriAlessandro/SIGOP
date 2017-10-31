package br.ufrn.sigestagios.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.activities.OfferActivity;
import br.ufrn.sigestagios.models.Offer;

public class RegistrationFormActivity extends AppCompatActivity {
    Offer offer;
    EditText year, description, responsible, term, vacanciesRemunerated, vacanciesVolunteers;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        year = (EditText) findViewById(R.id.year);
        description = (EditText) findViewById(R.id.description);
        responsible = (EditText) findViewById(R.id.responsible);
        term = (EditText) findViewById(R.id.term);
        vacanciesRemunerated = (EditText) findViewById(R.id.vacanciesRemunerated);
        vacanciesVolunteers = (EditText) findViewById(R.id.vacanciesVolunteers);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void register(View v){
        offer = new Offer(Integer.parseInt(year.getText().toString()),
                          description.getText().toString(),
                          responsible.getText().toString(),
                          term.getText().toString(),
                          Integer.parseInt(vacanciesRemunerated.getText().toString()),
                          Integer.parseInt(vacanciesVolunteers.getText().toString()));

        Intent resultIntent = new Intent(this, OfferActivity.class);
        resultIntent.putExtra("offerRegistered", offer);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}
