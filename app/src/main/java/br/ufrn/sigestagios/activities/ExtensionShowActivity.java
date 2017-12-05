package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.models.Extension;

/**
 * Created by Joao on 02/12/2017.
 */

public class ExtensionShowActivity extends AppCompatActivity {
    Extension offer;
    TextView description, term, email, year, cpf_cnpj, responsible, positionsRemunerated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extension_show_activity);

        offer = (Extension) getIntent().getSerializableExtra("OFFER");

//        offer = new Extension (
//                "desc",
//                "INSTITUTO METROPOLE DIGITAL",
//                0 ,
//                "exemplo@gmail.com",
//                2017 ,
//                22312345678L,
//                "JOAO EMMANUEL",
//                2,
//                121,
//                1);

        description = (TextView) findViewById(R.id.eDesc);
        description.setText("Descrição: " + offer.getDescription());

        term = (TextView) findViewById(R.id.eTerm);
        term.setText(offer.getUnit());

        email = (TextView) findViewById(R.id.eEmail);
        email.setText(offer.getEmail());

        year = (TextView) findViewById(R.id.eYear);
        year.setText("Ano: " + offer.getYear());

        cpf_cnpj = (TextView) findViewById(R.id.eCpf_cnpj);
        cpf_cnpj.setText(String.valueOf(offer.getCpf_cnpj()));

        responsible = (TextView) findViewById(R.id.eResponsible);
        responsible.setText(offer.getResponsible());

        positionsRemunerated = (TextView) findViewById(R.id.ePositionsRemunerated);
        positionsRemunerated.setText("Vagas Remuneradas: " + offer.getPositionsRemunerated());
    }
}
