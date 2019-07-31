package org.fedapay.sample.Uils;

public class Constants {
    public static final String IS_RESULT_ACTIVITY = "isResultActivity";
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

    public static final String DATA = "data";
    public static final String TRANSACTION_DATA = "transaction_data";
    public static final String ISUSERLOGGEDIN = "isUserLoggedIn";
    public static final String TITLE = "title";
    public static final String SELECTED_ITEM = "selected";
    public static final String TOKEN = "token";
    public static final int REQUEST_KEY_PMT = 100;

    public static final boolean  STATUS_AVAILABLE = true;
    public static final boolean  STATUS_UNAVAILABLE = false;

    public static final int  MTN_MOMO = 1;
    public static final int  MOOV_MOMO = 2;
    public static final int  VISA = 3;

}
