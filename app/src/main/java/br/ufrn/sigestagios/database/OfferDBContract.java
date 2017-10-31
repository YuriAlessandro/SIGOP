package br.ufrn.sigestagios.database;

import android.provider.BaseColumns;

/**
 * Created by Gustavo on 27/10/2017.
 */

public class OfferDBContract {
    private OfferDBContract() {}

    public class OfferEntry implements BaseColumns {
        public static final String TABLE_NAME = "oferta";
        public static final String ANO = "ano";
        public static final String DESCRICAO = "descricao";
        public static final String RESPONSAVEL = "responsavel";
        public static final String UNIDADE = "unidade";
        public static final String VAGAS_REMUNERADAS = "vagas_remuneradas";
        public static final String VAGAS_VOLUNTARIAS = "vagas_voluntarias";
    }
}
