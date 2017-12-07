package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.Internship;

/**
 * Created by Joao on 02/12/2017.
 */

public class InternshipShowActivity extends AppCompatActivity {
    Internship offer;
    TextView description, term, email, grantValue, auxTransport, responsible, numbersPositions, date, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internship_action_show_activity);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        offer = (Internship) getIntent().getSerializableExtra("OFFER");

        description = (TextView) findViewById(R.id.iDesc);
        description.setText("Descrição: " + offer.getDescription());

        email = (TextView) findViewById(R.id.iEmail);
        email.setText(offer.getEmail());

        term = (TextView) findViewById(R.id.iTerm);
        term.setText(offer.getUnit());

        responsible = (TextView) findViewById(R.id.iResponsible);
        responsible.setText(offer.getResponsible());

        numbersPositions = (TextView) findViewById(R.id.iPositionsRemunerated);
        numbersPositions.setText("Vagas Remuneradas: " + offer.getNumberPositions());

        grantValue = (TextView) findViewById(R.id.iGrantValue);
        grantValue.setText("Valor do estágio: R$ " + offer.getGrantValue());

        auxTransport = (TextView) findViewById(R.id.iAuxTransport);
        auxTransport.setText("Auxilio Transporte: R$ " + offer.getAuxTransport());

        date = (TextView) findViewById(R.id.iEndOffer);
        date.setText("Fim da oferta: " + df.format(offer.getEndOffer()));

        title = (TextView) findViewById(R.id.iTitle);
        title.setText("Título: " + offer.getTitle());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Estágio");
    }
}
