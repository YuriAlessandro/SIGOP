package br.ufrn.sigestagios.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import br.ufrn.sigestagios.database.OfferDBContract.OfferEntry;
import br.ufrn.sigestagios.models.Internship;
import br.ufrn.sigestagios.models.Offer;

/**
 * Created by Gustavo on 27/10/2017.
 */

/*
* NOTA:
* O programa não faz db.close() em nenhum momento porque ele tá agindo meio que como um singleton.
* Se der close quebra.
* Fazer um Singleton de verdade mais tarde.
* */

public class OfferDatabaseController {
    private SQLiteDatabase db;
    private OfferDatabase offerDB;

    public OfferDatabaseController(Context context) {
        offerDB = new OfferDatabase(context);
    }

    public long insertOffer(Internship intern) {
        ContentValues values = new ContentValues();

        db = offerDB.getWritableDatabase();
        values.put(OfferEntry.DESCRICAO, intern.getDescription());
        values.put(OfferEntry.UNIDADE, intern.getUnit());
        values.put(OfferEntry.EMAIL, intern.getEmail());
        values.put(OfferEntry.TITULO, intern.getTitle());
        values.put(OfferEntry.RESPONSAVEL, intern.getResponsible());
        values.put(OfferEntry.VAGAS, intern.getNumberPositions());
        values.put(OfferEntry.VALOR, intern.getAuxTransport());
        values.put(OfferEntry.AUXTRANSP, intern.getAuxTransport());
        values.put(OfferEntry.FIMOFERTA, intern.getEndOffer());

        return db.insert(OfferEntry.TABLE_NAME, null, values);
    }

    public Cursor retrieveOffers() {
        String[] campos = {OfferEntry.DESCRICAO,
            OfferEntry.UNIDADE,
            OfferEntry.EMAIL,
            OfferEntry.TITULO,
            OfferEntry.RESPONSAVEL,
            OfferEntry.VAGAS,
            OfferEntry.VALOR,
            OfferEntry.AUXTRANSP,
            OfferEntry.FIMOFERTA};
        db = offerDB.getReadableDatabase();

        return db.query(OfferEntry.TABLE_NAME, campos, null, null, null, null, null, null);
    }
}
