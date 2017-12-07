package br.ufrn.sigestagios.database;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by Gustavo on 27/10/2017.
 */

public class OfferDBContract {
    private OfferDBContract() {}

    public class OfferEntry implements BaseColumns {
        public static final String TABLE_NAME = "oferta";
        public static final String DESCRICAO = "descricao";
        public static final String UNIDADE = "unidade";
        public static final String EMAIL = "email_responsavel";
        public static final String TITULO = "titulo";
        public static final String RESPONSAVEL = "responsavel";
        public static final String VAGAS = "vagas";
        public static final String VALOR = "valor";
        public static final String AUXTRANSP = "auxtransp";
        public static final String FIMOFERTA = "fimoferta";
    }
}
