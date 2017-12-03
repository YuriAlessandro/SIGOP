package br.ufrn.sigestagios.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        offer = (Internship) getIntent().getSerializableExtra("OFFER");

//        String dateStr = "04/05/2010";

//        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
//        Date myDate = null;
//        //Só tava querendo pegar com isso, enfim, depois vou fazer um DatePicker
//        try {
//            myDate = curFormater.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        offer = new Internship (
//                "desc",
//                "exemplo@gmail.com",
//                "INSTITUTO METROPOLE DIGITAL" ,
//                "JOAO EMMANUEL",
//                2 ,
//                500,
//                100,
//                 myDate,
//                "Vaga para estágio");

        description = (TextView) findViewById(R.id.iDesc);
        description.setText(offer.getDescription());

        email = (TextView) findViewById(R.id.iEmail);
        email.setText(offer.getEmail());

        term = (TextView) findViewById(R.id.iTerm);
        term.setText(offer.getTerm());

        responsible = (TextView) findViewById(R.id.iResponsible);
        responsible.setText(offer.getResponsible());

        numbersPositions = (TextView) findViewById(R.id.iPositionsRemunerated);
        numbersPositions.setText(offer.getNumberPositions());

        grantValue = (TextView) findViewById(R.id.iGrantValue);
        grantValue.setText(offer.getGrantValue());

        auxTransport = (TextView) findViewById(R.id.iAuxTransport);
        auxTransport.setText(offer.getAuxTransport());

        date = (TextView) findViewById(R.id.iEndOffer);
        date.setText(String.valueOf(offer.getEndOffer()));

        title = (TextView) findViewById(R.id.iTitle);
        title.setText(offer.getTitle());
    }
}
