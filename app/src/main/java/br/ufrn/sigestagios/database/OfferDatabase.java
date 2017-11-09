package br.ufrn.sigestagios.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufrn.sigestagios.database.OfferDBContract.OfferEntry;

/**
 * Created by Gustavo on 27/10/2017.
 */

public class OfferDatabase extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "Ofertas";
    private static final int VERSAO = 2;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + OfferEntry.TABLE_NAME + " ("
                    + OfferEntry._ID + " INTEGER PRIMARY KEY, " + OfferEntry.DESCRICAO + ", " + OfferEntry.UNIDADE + ", " + OfferEntry.ID_UNIDADE
                    + ", " + OfferEntry.EMAIL +  ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OfferEntry.TABLE_NAME;

    public OfferDatabase(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}