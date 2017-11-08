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
        values.put(OfferEntry.DESCRICAO, offer.getDescription());
        values.put(OfferEntry.ID_UNIDADE, offer.getIdTerm());
        values.put(OfferEntry.UNIDADE, offer.getTerm());
        values.put(OfferEntry.EMAIL, offer.getEmail());

        return db.insert(OfferEntry.TABLE_NAME, null, values);
    }

    public Cursor retrieveOffers() {
        String[] campos = {OfferEntry.DESCRICAO, OfferEntry.ID_UNIDADE,
                OfferEntry.UNIDADE, OfferEntry.EMAIL};
        db = offerDB.getReadableDatabase();

        return db.query(OfferEntry.TABLE_NAME, campos, null, null, null, null, null, null);
    }
}
