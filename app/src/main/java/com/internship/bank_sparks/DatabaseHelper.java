package com.internship.bank_sparks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 + " (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9105218584,'Sunidhi',15000.00,'sunidhi@gmail.com','XXXXXXXXXXXX8584','ABANK9876543')");
        db.execSQL("insert into user_table values(9205218579,'Priyanshi',18582.67,'priyanshi@gmail.com','XXXXXXXXXXXX8579','ABANK8765432')");
        db.execSQL("insert into user_table values(9295218583,'Shubham',17459.56,'shubham@gmail.com','XXXXXXXXXXXX8583','ABANK7654321')");
        db.execSQL("insert into user_table values(9305218591,'Saurabh',15000.01,'saurabh@gmail.com','XXXXXXXXXXXX8591','ABANK6543210')");
        db.execSQL("insert into user_table values(9505218598,'Chandan',26003.48,'chandan@gmail.com','XXXXXXXXXXXX1875','ABANK5432109')");
        db.execSQL("insert into user_table values(9805218590,'Digant',9445.16,'digant@gmail.com','XXXXXXXXXXXX3772','ABANK4321098')");
        db.execSQL("insert into user_table values(9805218575,'Jalaj',5936.00,'jalaj@gmail.com','XXXXXXXXXXXX5256','ABANK3210987')");
        db.execSQL("insert into user_table values(9305218737,'Abhinav',8577.22,'abhinav@gmail.com','XXXXXXXXXXXX2734','ABANK2109876')");
        db.execSQL("insert into user_table values(9705218565,'Lavneesh',4398.46,'lavneesh@gmail.com','XXXXXXXXXXXX5761','ABANK1098765')");
        db.execSQL("insert into user_table values(9805218587,'Atul',4273.90,'atul@gmail.com','XXXXXXXXXXXX4741','ABANK0987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " + phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " + phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " + phonenumber);
    }

    public Cursor readtransferdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date, String from_name, String to_name, String amount, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}