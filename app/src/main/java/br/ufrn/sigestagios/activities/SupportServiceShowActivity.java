package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.SupportService;

/**
 * Created by Joao on 02/12/2017.
 */

public class SupportServiceShowActivity extends AppCompatActivity {
    SupportService offer;
    TextView description, term, email, abrevTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_service_show_activity);

        offer = new SupportService (
                "desc",
                "INSTITUTO METROPOLE DIGITAL",
                0 ,
                "exemplo@gmail.com",
                "IMD",
                21);

        description = (TextView) findViewById(R.id.ssDesc);
        description.setText(offer.getDescription());

        term = (TextView) findViewById(R.id.ssTerm);
        term.setText(offer.getTerm());

        email = (TextView) findViewById(R.id.ssEmail);
        email.setText(offer.getEmail());

        abrevTerm = (TextView) findViewById(R.id.ssAbbrevTerm);
        abrevTerm.setText(" - " + offer.getAbbrevTerm());
    }
}
