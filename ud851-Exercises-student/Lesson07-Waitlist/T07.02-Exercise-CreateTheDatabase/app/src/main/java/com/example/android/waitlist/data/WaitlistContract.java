package com.example.android.waitlist.data;

import android.provider.BaseColumns;

// 1. Adım , Veritabanın temel tablo ismi ve kolon isimlerini oluşturan yapının hazırlanması.

public class WaitlistContract {

    public static final class WaitlistEntry implements BaseColumns {
        public static final String TABLE_NAME = "waitlist";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_PARTY_SIZE = "partySize";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
