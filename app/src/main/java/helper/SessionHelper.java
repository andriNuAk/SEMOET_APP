package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M on 9/11/2017.
 */

public class SessionHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DataPengguna";
    private final static String TABLES[] = {"id", "namaPengguna", "kataSandi", "eMail", "hakAkses", "status" };
    private final static  String NAMA_TABEL = "tb_pengguna";


    public SessionHelper(Context context) {
        super(context ,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTabelPengguna(SQLiteDatabase db){
        db.execSQL("CREATE TABLE if not exists "+NAMA_TABEL+" (id INTEGER PRIMARY KEY , namaPengguna TEXT, kataSandi TEXT, eMail TEXT, hakAkses TEXT, status TEXT);");
    }

    public void insertAkun(SQLiteDatabase db, int id, String namaPengguna, String kataSandi, String eMail, String hakAkses, String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("namaPengguna", namaPengguna);
        contentValues.put("kataSandi", kataSandi);
        contentValues.put("eMail", eMail);
        contentValues.put("hakAkses", hakAkses);
        contentValues.put("status", status);
        db.insert(NAMA_TABEL, null, contentValues);
    }

    public void deleteDataAkun(SQLiteDatabase db, int id) {
        db.delete(NAMA_TABEL, "id =" + id, null);
    }

    public Cursor getAllAkun(SQLiteDatabase db){
        return  db.query(NAMA_TABEL, TABLES, null, null, null, null, null);
    }
}
