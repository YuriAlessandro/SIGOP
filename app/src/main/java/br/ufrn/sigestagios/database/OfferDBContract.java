package br.ufrn.sigestagios.database;

import android.provider.BaseColumns;

/**
 * Created by Gustavo on 27/10/2017.
 */

public class OfferDBContract {
    private OfferDBContract() {}

    public class OfferEntry implements BaseColumns {
        public static final String TABLE_NAME = "oferta";
        public static final String DESCRICAO = "descricao";
        public static final String UNIDADE = "unidade";
        public static final String ID_UNIDADE = "id_unidade";
        public static final String EMAIL = "email_responsavel";
    }
}
