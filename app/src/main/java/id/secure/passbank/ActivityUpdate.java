package id.secure.passbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityUpdate extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnupdate, btnkembaliupdate;
    EditText editnoupdate, editnameupdate, editemailupdate, editpasswordupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle("Update Account");

        dbHelper = new DataHelper(this);

        editnoupdate = (EditText) findViewById(R.id.editNoUpdate);
        editnameupdate = (EditText) findViewById(R.id.editNameUpdate);
        editemailupdate = (EditText) findViewById(R.id.editEmailUpdate);
        editpasswordupdate = (EditText) findViewById(R.id.editPasswordUpdate);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM account WHERE name = '" +
                getIntent().getStringExtra("name") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            editnoupdate.setText(cursor.getString(0).toString());
            editnameupdate.setText(cursor.getString(1).toString());
            editemailupdate.setText(cursor.getString(2).toString());
            editpasswordupdate.setText(cursor.getString(3).toString());


        }
        btnupdate = (Button) findViewById(R.id.btnUpdate);
        btnkembaliupdate = (Button) findViewById(R.id.btnKembaliUpdate);
        // daftarkan even onClick pada btnSimpan
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update account set name='"+
                        editnameupdate.getText().toString() +"', email='" +
                        editemailupdate.getText().toString()+"', password='"+
                        editpasswordupdate.getText().toString() +"' where no='"+
                        editnoupdate.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnkembaliupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
