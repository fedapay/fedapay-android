package org.fedapay.fedalib.Utils;

public class Constants {

    public static final String TRANSACTION_DATA = "transaction_data";
    public static final String FEDA_SP_KEY = "feda_sp_key";
    public static final String FEDA_TRANSACTION_PENDDING = "pendding";
    public static final String FEDA_TRANSACTION_SUCCESS = "success";
    public static final String FEDA_TRANSACTION_CANCELED = "canceled";
    public static final int MTN_MOMO = 1;
    public static final int MOOV_MOMO = 2;
    public static final int VISA = 3;
    public enum Errors {
        CATEGORY_FAILED(1), FEATURED(2), PROVIDER(3), SEARCH(4), COUNTRY_LOAD_FAILED(5), APPOINTMENT_FAILED(6), FAILED(7);

        private int value;

        Errors(int value) {
            this.value = value;
        }

        public static int value(String s) {
            return Errors.valueOf(s).value;
        }

    }

}
