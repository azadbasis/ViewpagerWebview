package com.server.sync.azhar.sqlitesyncdemoone;

/**
 * Created by Azhar on 3/31/2017.
 */

public class DbContact {

    public static  final int SYNC_STATUS_OK = 0;
    public static  final int SYNC_STATUS_FAILED = 1;
    public  static final String SERVER_URL = "http://10.0.2.2/configinfo.php";
    public  static final String UI_UPDATE_BROADCAST = "com.server.sync.azhar.sqlitesyncdemoone.USER_ACTION";

    public  static final String DB_NAME ="contactdb";
    public  static final String TABLE_NAME ="contactinfo";
    public  static final String NAME ="name";
    public  static final String ID ="id";
    public  static final String SYNC_STATUS ="sync_status";
}
