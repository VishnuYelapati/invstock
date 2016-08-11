package com.ptg.inventory.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "Inventory";

	// questionsData table name

	public static final String TABLE_MYSTOCK = "TBL_MYSTOCK";
	public static final String TABLE_EXP = "TBL_EXPANCES";
	public static final String TABLE_PRODUCTLIST = "TBL_PRODUCTLIST";
	public static final String TABLE_DISCOUNTS = "TBL_PROD_DISCOUNTS";

	public static final String MYSTOCK_SERIAL_NO="S_NO";
	public static final String MYSTOCK_INVOICE_NO="INVOICE_NO";
	public static final String MYSTOCK_PROD_ID="PRODUCT_ID";
	public static final String MYSTOCK_PROD_NAME="PRODUCT_NAME";
	public static final String MYSTOCK_PROD_QTY="PRODUCT_QTY";
	public static final String MYSTOCK_PROD_OPENING_STOCK="PRODUCT_OPENING_STOCK";
	public static final String MYSTOCK_PROD_CLOSING_STOCK="PRODUCT_CLOSING_STOCK";
	public static final String MYSTOCK_PROD_DISCOUNT="PRODUCT_DISCOUNT_AMOUNT";
	public static final String MYSTOCK_PROD_SERVICE_CHARGE="PRODUCT_SERVICE_CHARGE";
	public static final String MYSTOCK_PROD_TOTAL_AMOUNT="PRODUCT_TOTAL_AMOUNT";
	public static final String MYSTOCK_PROD_DATE_STAMP="PRODUCT_DATE";

// TABLE EXPENCES
	public static final String EXP_SERIAL_NO="EXP_S_NO";
	public static final String EXP_NAME="EXP_PRODUCT_NAME";
	public static final String EXP_TYPE="EXP_PRODUCT_TYPE";
	public static final String EXP_AMOUNT="EXP_PRODUCT_AMOUNT";
	public static final String EXP_DATE="EXP_PRODUCT_DATE";

	// TABLE PRODUCT_LIST
	public static final String PRODUCT_SERIAL_NO="PRODUCT_S_NO";
	public static final String PRODUCT_ID="PRODUCT_ID";
	public static final String PRODUCT_NAME="PRODUCT_NAME";
	public static final String PRODUCT_TYPE="PRODUCT_TYPE";
	public static final String PRODUCT_QTY="PRODUCT_QTY";
	public static final String PRODUCT_AMOUNT="PRODUCT_AMOUNT";
	public static final String PRODUCT_DATE="PRODUCT_DATE";

	// Table DISCOUNTS
	public static final String DISCOUNTS_SERIAL_NO="PRODUCT_S_NO";
	public static final String DISCOUNTS_PRODUCT_ID="PRODUCT_ID";
	public static final String DISCOUNTS_PRODUCT_NAME="PRODUCT_NAME";
	public static final String DISCOUNTS_PRODUCT_TYPE="PRODUCT_TYPE";
	public static final String DISCOUNTS_PRODUCT_QTY="PRODUCT_QTY";
	public static final String DISCOUNTS_AMOUNT="PRODUCT_AMOUNT";
	public static final String DISCOUNTS_DATE="PRODUCT_DATE";



	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_PRODUCT_LIST_TABLE = "CREATE TABLE "
				+ TABLE_PRODUCTLIST
				+ " (" 
				+ PRODUCT_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ PRODUCT_ID	+ " TEXT,"
				+ PRODUCT_NAME 	+ " TEXT,"
				+ PRODUCT_QTY 	+ " TEXT,"
				+ PRODUCT_TYPE 	+ " TEXT,"
				+ PRODUCT_AMOUNT	+ " TEXT,"

				+ PRODUCT_DATE + " DATE"
				+ ")";



		String CREATE_MYSTOCK_TABLE = "CREATE TABLE "
				+ TABLE_MYSTOCK
				+ " ("
				+ MYSTOCK_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ MYSTOCK_INVOICE_NO	+ " TEXT,"
				+ MYSTOCK_PROD_ID 	+ " TEXT,"
				+ MYSTOCK_PROD_NAME 	+ " TEXT,"
				+ MYSTOCK_PROD_QTY 	+ " TEXT,"
				+ MYSTOCK_PROD_OPENING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_CLOSING_STOCK	+ " TEXT,"
				+ MYSTOCK_PROD_SERVICE_CHARGE	+ " TEXT,"
				+ MYSTOCK_PROD_DISCOUNT	+ " TEXT,"
				+ MYSTOCK_PROD_TOTAL_AMOUNT	+ " TEXT,"

				+ MYSTOCK_PROD_DATE_STAMP + " DATE"
				+ ")";

		String CREATE_EXPENCES_TABLE = "CREATE TABLE "
				+ TABLE_EXP
				+ " ("
				+ EXP_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ EXP_NAME	+ " TEXT,"
				+ EXP_TYPE 	+ " TEXT,"
				+ EXP_AMOUNT 	+ " TEXT,"

				+ EXP_DATE + " DATE"
				+ ")";

		String CREATE_DISCOUNTS_TABLE = "CREATE TABLE "
				+ TABLE_DISCOUNTS
				+ " ("
				+ DISCOUNTS_SERIAL_NO 		+ " INTEGER PRIMARY KEY,"
				+ DISCOUNTS_PRODUCT_ID	+ " TEXT,"
				+ DISCOUNTS_PRODUCT_NAME 	+ " TEXT,"
				+ DISCOUNTS_PRODUCT_TYPE 	+ " TEXT,"
				+ DISCOUNTS_PRODUCT_QTY 	+ " TEXT,"
				+ DISCOUNTS_AMOUNT 	+ " TEXT,"

				+ DISCOUNTS_DATE+ " DATE"
				+ ")";

		db.execSQL(CREATE_DISCOUNTS_TABLE);

		db.execSQL(CREATE_EXPENCES_TABLE);
		db.execSQL(CREATE_PRODUCT_LIST_TABLE);
		db.execSQL(CREATE_MYSTOCK_TABLE);
	}

	

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTLIST);

		// Create tables again
		onCreate(db);
	}

	
	public long insert(String tableName,ContentValues values) {
		SQLiteDatabase db = this.getWritableDatabase();

		
		long seq=db.insert(tableName, null, values);
		db.close(); 
		return seq;
	}

	
	
	public Cursor RetriveData(String table) 
	{
		try
		{
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor curRetriveData = db.rawQuery(table,null);
			if(curRetriveData!=null)
				curRetriveData.moveToFirst();
			return curRetriveData;
		}
		catch (Exception e) 
		{  
			e.printStackTrace();
			return null;
		}
	}

}
