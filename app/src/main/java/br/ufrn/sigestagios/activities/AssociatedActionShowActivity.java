package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.AssociatedAction;

/**
 * Created by Joao on 02/12/2017.
 */

public class AssociatedActionShowActivity extends AppCompatActivity {
    AssociatedAction offer;
    TextView description, term, email, year, cpf_cnpj, responsible, positionsRemunerated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associated_action_show_activity);

        offer = (AssociatedAction) getIntent().getSerializableExtra("OFFER");

//        offer = new AssociatedAction (
//                "desc",
//                "INSTITUTO METROPOLE DIGITAL",
//                0 ,
//                "exemplo@gmail.com",
//                2017 ,
//                22312345678L,
//                "JOAO EMMANUEL",
//                2,
//                121);

        description = (TextView) findViewById(R.id.aaDesc);
        description.setText("Descrição: " + offer.getDescription());

        term = (TextView) findViewById(R.id.aaTerm);
        term.setText(offer.getUnit());

        email = (TextView) findViewById(R.id.aaEmail);
        email.setText(offer.getEmail());

        year = (TextView) findViewById(R.id.aaYear);
        year.setText("Ano: " + offer.getYear());

        cpf_cnpj = (TextView) findViewById(R.id.aaCpf_cnpj);
        cpf_cnpj.setText(String.valueOf(offer.getCpf_cnpj()));

        responsible = (TextView) findViewById(R.id.aaResponsible);
        responsible.setText(offer.getResponsible());

        positionsRemunerated = (TextView) findViewById(R.id.aaPositionsRemunerated);
        positionsRemunerated.setText("Vagas Remuneradas: " + offer.getPositionsRemunerated());
    }
}
