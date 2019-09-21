package com.example.android.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {

    // Kontrat sınıfının örneğinin oluşturulmasını engellemek için private.
    private WaitlistContract() {

    }


    // TODO (COMPLETED) Create an inner class named WaitlistEntry class that implements the BaseColumns interface
    // TODO (COMPLETED) Inside create a static final members for the table name and each of the db columns

    // Uygulama ile Veritabanı arasında ki kontrat sınıfı. Kolon isimlerini ve tablo adını belirtir.
    // BaseColumns ile otomatik _ID oluşturulur.
    public static final class WaitListEntry implements BaseColumns {

        public static final String TABLE_NAME = "waitlist";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_PARTY_SIZE = "partySize";
        public static final String COLUMN_TIMESTAMP = "timestamp";


    }




}
