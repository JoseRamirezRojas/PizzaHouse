package com.ciencias.tarea2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ManagerBaseSQLite extends SQLiteOpenHelper {

    private String dbName;
    // User Table
    private static final String TABLA_USER = "tabla_user";
    private static final String COL_USER_ID = "id";
    private static final String COL_USER_NAME = "name";
    private static final String COL_USER_PASSWORD = "password";
    private static final String COL_USER_CARD = "card";
    private static final String GENDER = "gender";


    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLA_USER + " (" +
            COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USER_NAME + " TEXT NOT NULL, " +
            COL_USER_PASSWORD + " TEXT NOT NULL, " +
            COL_USER_CARD + " BIGINT NOT NULL, " +
            GENDER + " INTEGER NOT NULL); " ;

    //Drop table
    private static final String DROP_USER_TABLE = "DROP TABLE " + TABLA_USER;

    public ManagerBaseSQLite(Context ctx, String name, CursorFactory factory, int version){
        super(ctx, name, factory, version);
        this.dbName = name;
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_USER_TABLE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL(DROP_USER_TABLE);
        onCreate( db );
    }
}
