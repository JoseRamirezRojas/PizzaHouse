package com.ciencias.tarea2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ManagerBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "manager.db";
    // User table
    private static final String TABLA_USER = "tabla_user";
    private static final String COL_USER_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_USER_NAME = "name";
    private static final int NUM_COL_USER_NAME = 1;
    private static final String COL_USER_PASSWORD = "password";
    private static final int NUM_COL_USER_PASSWORD = 2;
    private static final String COL_USER_CARD = "card";
    private static final int NUM_COL_USER_CARD = 3;
    private static final String COL_GENDER = "gender";
    private static final int NUM_COL_GENDER = 4;

    private SQLiteDatabase bdd;
    private ManagerBaseSQLite manager;

    public ManagerBDD(Context context) {
        manager = new ManagerBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = manager.getWritableDatabase();
    }

    public void openForRead() {
        bdd = manager.getReadableDatabase();
    }
    public void close() {
        bdd.close();
    }
    public SQLiteDatabase getBdd() {
        return bdd;
    }


    public long insertUser(User user){
        ContentValues content = new ContentValues();
        content.put(COL_USER_CARD, user.getTarjeta());
        content.put(COL_USER_NAME, user.getNombre());
        content.put(COL_USER_PASSWORD, user.getPassword());
        content.put(COL_GENDER, user.getGenero());
        return bdd.insert(TABLA_USER, null, content);
    }

    public User getUserByName(String username){
        Cursor c = bdd.query( TABLA_USER,
                new String[] {
                        COL_USER_ID,
                        COL_USER_NAME,
                        COL_USER_PASSWORD,
                        COL_USER_CARD,
                        COL_GENDER}, COL_USER_NAME + " LIKE \"" + username + "\"", null, null,
                null, COL_USER_NAME );
        return cursorToUser( c );
    }

    public User getUser(long id){
        Cursor c = bdd.query( TABLA_USER,
                new String[] {
                        COL_USER_ID,
                        COL_USER_NAME,
                        COL_USER_PASSWORD,
                        COL_USER_CARD,
                        COL_GENDER }, COL_USER_ID + " LIKE \"" + id + "\"", null, null,
                null, COL_USER_ID );
        return cursorToUser( c );
    }

    public long updateUser(long id, User user){
        ContentValues content = new ContentValues();
        content.put(COL_USER_NAME, user.getNombre());
        content.put(COL_USER_PASSWORD, user.getPassword());
        content.put(COL_USER_CARD, user.getTarjeta());
        content.put(COL_GENDER, user.getGenero());
        return bdd.update(TABLA_USER, content, COL_USER_ID + " = "+ id, null);
    }

        public int removeUser(int id){
        return bdd.delete( TABLA_USER, COL_USER_ID + " = " + id, null );
    }




    public User cursorToUser( Cursor c ) {
        Integer idTest = c.getCount();
        if ( c.getCount() == 0 ) {
            c.close();
            return null;
        }
        User user = new User();
        while(c.moveToNext()){
            Integer idTest2 = c.getInt(NUM_COL_ID);
            user.setId(c.getInt(NUM_COL_ID));
            user.setNombre(c.getString(NUM_COL_USER_NAME));
            user.setPassword(c.getString(NUM_COL_USER_PASSWORD));
            user.setTarjeta(c.getLong(NUM_COL_USER_CARD));
            user.setGenero(c.getInt(NUM_COL_GENDER));
        }
        c.close();
        return user;
    }



}
