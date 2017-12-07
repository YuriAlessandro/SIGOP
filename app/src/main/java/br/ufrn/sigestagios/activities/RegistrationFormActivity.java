package br.ufrn.sigestagios.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.ufrn.sigestagios.R;
import br.ufrn.sigestagios.activities.OfferActivity;
import br.ufrn.sigestagios.models.Internship;
import br.ufrn.sigestagios.models.Internship;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationFormActivity extends AppCompatActivity {
    Internship internship;
    String data;
    private AlertDialog alerta;
    TextView endOffer;
    EditText title, description, responsible, email, companyName, numberPositions, grantValue, auxTransport;
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
        endOffer = (TextView) findViewById(R.id.endOffer);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        String sToday = df.format(today);
        endOffer.setText(sToday);
        data = sToday;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void register(View v) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date myDate = df.parse(data);
        Calendar calendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
        Date today = calendar.getTime();


        if ((today.compareTo(myDate) < 0) && (!numberPositions.getText().toString().equals("") ) && (!grantValue.getText().toString().equals("")) && (!auxTransport.getText().toString().equals("")) ) {
            internship = new Internship(
                    description.getText().toString(),
                    email.getText().toString(),
                    companyName.getText().toString(),
                    responsible.getText().toString(),
                    Integer.parseInt(numberPositions.getText().toString()),
                    Integer.parseInt(grantValue.getText().toString()),
                    Integer.parseInt(auxTransport.getText().toString()),
                    data,
                    title.getText().toString());

            Intent resultIntent = new Intent(this, OfferActivity.class);
            resultIntent.putExtra("offerRegistered", internship);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Erro ao cadastrar");
            builder.setMessage("Campos inválidas");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alerta = builder.create();
            alerta.show();
        }

    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public String getDate() {
            return data;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            data = String.valueOf(day) + "/"
                    + String.valueOf(month+1) + "/" + String.valueOf(year);
            endOffer.setText(data);
        }
    }

    public void showDatePickerDialog(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(ft, "datePicker");
    }
}
