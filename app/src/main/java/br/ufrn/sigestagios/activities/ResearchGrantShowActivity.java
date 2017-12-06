package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.ResearchGrant;

/**
 * Created by Joao on 02/12/2017.
 */

public class ResearchGrantShowActivity extends AppCompatActivity {

    ResearchGrant offer;
    TextView description, term, email, year, cpf_cnpj, responsible, positionRemunerated, numberPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.research_grant_show_activity);

        offer = (ResearchGrant) getIntent().getSerializableExtra("OFFER");

//        offer = new ResearchGrant (
//                "desc",
//                "INSTITUTO METROPOLE DIGITAL",
//                0 ,
//                "exemplo@gmail.com",
//                2017 ,
//                "22312345678",
//                "JOAO EMMANUEL",
//                2,
//                121,
//                5,
//                2);

        description = (TextView) findViewById(R.id.rgDesc);
        description.setText("Descrição: " + offer.getDescription());

        term = (TextView) findViewById(R.id.rgTerm);
        term.setText(offer.getUnit());

        email = (TextView) findViewById(R.id.rgEmail);
        email.setText(offer.getEmail());

        year = (TextView) findViewById(R.id.rgYear);
        year.setText("Ano: " + offer.getYear());

        cpf_cnpj = (TextView) findViewById(R.id.rgCpf_cnpj);
        cpf_cnpj.setText(offer.getCpf_cnpj());

        responsible = (TextView) findViewById(R.id.rgResponsible);
        responsible.setText(offer.getResponsible());

        positionRemunerated = (TextView) findViewById(R.id.rgPositionsRemunerated);
        positionRemunerated.setText("Vagas remuneradas: " + offer.getPositionsRemunerated());

        numberPositions = (TextView) findViewById(R.id.rgNumberPositions);
        numberPositions.setText("Total de Vagas: " + offer.getNumberPositions());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pesquisa");
    }
}
