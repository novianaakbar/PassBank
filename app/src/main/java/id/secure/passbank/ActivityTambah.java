package id.secure.passbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityTambah extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnsimpantambah, btnkembalitambah;
    EditText editnotambah, editnametambah, editemailtambah, editpasswordtambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        setTitle("Tambah Account");

        dbHelper = new DataHelper(this);
        editnotambah = (EditText) findViewById(R.id.editNoTambah);
        editnametambah = (EditText) findViewById(R.id.editNameTambah);
        editemailtambah = (EditText) findViewById(R.id.editEmailTambah);
        editpasswordtambah = (EditText) findViewById(R.id.editPasswordTambah);
        btnsimpantambah = (Button) findViewById(R.id.btnSimpanTambah);
        btnkembalitambah = (Button) findViewById(R.id.btnKembaliTambah);

        btnsimpantambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO account(no, name, email, password) values('" +
                        editnotambah.getText().toString() + "','" +
                        editnametambah.getText().toString() + "','" +
                        editemailtambah.getText().toString() + "','" +
                        editpasswordtambah.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnkembalitambah.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
