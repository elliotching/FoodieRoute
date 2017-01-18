package fcsit.foodieroute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elliot on 26-Aug-16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FoodDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_NAME = "foods";
    public static final String KEY_ID = "id";
    public static final String KEY_FOOD_NAME = "food_name";
    public static final String KEY_FOOD_SHOP = "food_shop";

    public static final String[] COLUMN_NAMES_IN_THE_TABLE = {
            KEY_ID,
            KEY_FOOD_NAME,
            KEY_FOOD_SHOP
    };

    public void putFoodIntoContentValues(ContentValues contentValuesForSQLite, Food food){
        contentValuesForSQLite.put ( KEY_FOOD_NAME , food.getFoodName() ); // get food name
        contentValuesForSQLite.put ( KEY_FOOD_SHOP , food.getFoodShop() ); // get food shop
    }

    public Food getFoodFromQuery(Cursor cursor, Food food){
        food.setId( Integer.parseInt ( cursor.getString(0) ) );
        food.setFoodName(cursor.getString(1));
        food.setFoodShop(cursor.getString(2));
        return food;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create food table
        String CREATE_FOOD_TABLE = rawQueryCreateTable();

        // create foods table
        db.execSQL(CREATE_FOOD_TABLE);
    }

    public final String rawQueryCreateTable(){
        return "CREATE TABLE " + TABLE_NAME + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FOOD_NAME + " TEXT, " +
                KEY_FOOD_SHOP + " TEXT )";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older foods table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create fresh foods table
        this.onCreate(db);
    }

    public void addBook(Food food) {
        //for logging
        System.out.print("elliot: addBook: " + food.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        putFoodIntoContentValues(values, food);

        // 3. insert
        db.insert(TABLE_NAME, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }


    public Food getFood(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_NAME, // a. table
                        COLUMN_NAMES_IN_THE_TABLE, // b. column names
                        KEY_ID + " = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. column selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Food food = new Food();//
        food = getFoodFromQuery(cursor, food);

        //log
        System.out.println("elliot: getBook(" + id + "): " + food.toString());

        // 5. return book
        return food;
    }

    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<Food>();

        String query = "SELECT * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Food food = null;
        if (cursor.moveToFirst()) {
            do {
                food = new Food();
                food = getFoodFromQuery(cursor,food);

                // Add book to books
                foods.add(food);
            } while (cursor.moveToNext());
        }

        System.out.println("elliot: getAllBooks(): " + foods.toString());

        // return books
        return foods;
    }

    public int updateFood(Food to_be_update_food) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        putFoodIntoContentValues(values, to_be_update_food);

        // 3. updating row
        int i = db.update(TABLE_NAME, //table
                values, // column/value
                KEY_ID + " = ?", // selections
                new String[]{
                        String.valueOf(to_be_update_food.getId())
                }); //column selection args

        // 4. close
        db.close();

        System.out.println("elliot: updateFood: Success, " + i + (i > 1 ? " rows " : " row ") + "affected.");

        return i;
    }

    public void deleteFood(Food food) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_NAME, //table name
                KEY_ID + " = ?",  // selections
                new String[]{
                        String.valueOf(food.getId())
                }); //column selections args

        // 3. close
        db.close();

        //log
        System.out.println("elliot: deleteBook: " + food.toString());
    }


}