package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "accounts.db";
    public static String DATASTORE = "DATASTORE";
    public static String COLUMN_USERNAME = "UserName";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "UserID";
    public static String COLUMN_FOLLOWED = "Followed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + DATASTORE + "(" +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_ID + " TEXT, " +
                COLUMN_FOLLOWED + " TEXT)";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DATASTORE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the table has 20 rows
        String countQuery = "SELECT count(*) FROM " + DATASTORE;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.moveToFirst();
        int rowCount = cursor.getInt(0);
        cursor.close();
        if(rowCount != 20){
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, user.getName());
            values.put(COLUMN_DESCRIPTION, user.getDescription());
            values.put(COLUMN_ID, user.getId());
            values.put(COLUMN_FOLLOWED, user.isFollowed() ? 1 : 0);
            SQLiteDatabase db1 = this.getWritableDatabase();
            db1.insert(DATASTORE, null, values);
        }

        db.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DATASTORE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int userIDIndex = cursor.getColumnIndex(COLUMN_ID);
            int followedIndex = cursor.getColumnIndex(COLUMN_FOLLOWED);

            do {
                String username = cursor.getString(usernameIndex);
                String description = cursor.getString(descriptionIndex);
                int userID = cursor.getInt(userIDIndex);
                int followed = cursor.getInt(followedIndex);
                boolean follow = (followed == 1);

                User user = new User(username, description, userID, follow);
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.isFollowed() ? 1 : 0);

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(user.getId())};

        int rowsAffected = db.update(DATASTORE, values, whereClause, whereArgs);

        db.close();

        if (rowsAffected > 0) {
            Log.d("DB Update", "User updated successfully");
        } else {
            Log.d("DB Update", "Failed to update user");
        }
    }

    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        String selectQuery = "SELECT * FROM " + DATASTORE + " LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int userIDIndex = cursor.getColumnIndex(COLUMN_ID);
            int followedIndex = cursor.getColumnIndex(COLUMN_FOLLOWED);

            String username = cursor.getString(usernameIndex);
            String description = cursor.getString(descriptionIndex);
            int userID = cursor.getInt(userIDIndex);
            int followed = cursor.getInt(followedIndex);
            boolean follow = (followed == 1);

            user = new User(username, description, userID, follow);
        }

        cursor.close();
        db.close();

        return user;
    }
}
