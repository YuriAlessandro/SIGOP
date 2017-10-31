package br.ufrn.sigestagios.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufrn.sigestagios.database.OfferDBContract.OfferEntry;
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

    public long insertOffer(Offer offer) {
        ContentValues values = new ContentValues();

        db = offerDB.getWritableDatabase();
        values.put(OfferEntry.ANO, offer.getYear());
        values.put(OfferEntry.DESCRICAO, offer.getDescription());
        values.put(OfferEntry.RESPONSAVEL, offer.getResponsible());
        values.put(OfferEntry.UNIDADE, offer.getTerm());
        values.put(OfferEntry.VAGAS_REMUNERADAS, offer.getVacanciesRemunerated());
        values.put(OfferEntry.VAGAS_VOLUNTARIAS, offer.getVacanciesVolunteers());

        return db.insert(OfferEntry.TABLE_NAME, null, values);
    }

    public Cursor retrieveOffers() {
        String[] campos = {OfferEntry.ANO, OfferEntry.DESCRICAO, OfferEntry.RESPONSAVEL,
                OfferEntry.UNIDADE, OfferEntry.VAGAS_REMUNERADAS, OfferEntry.VAGAS_VOLUNTARIAS};
        db = offerDB.getReadableDatabase();

        return db.query(OfferEntry.TABLE_NAME, campos, null, null, null, null, null, null);
    }
}
