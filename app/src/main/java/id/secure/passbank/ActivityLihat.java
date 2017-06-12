package id.secure.passbank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityLihat extends AppCompatActivity {

        protected Cursor cursor;
        DataHelper dbHelper;
        Button btnkembali;
        TextView txtno, txtname, txtemail, txtpassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lihat);
            setTitle("Account");

            dbHelper = new DataHelper(this);
            txtno = (TextView) findViewById(R.id.txtNo);
            txtname = (TextView) findViewById(R.id.txtName);
            txtemail = (TextView) findViewById(R.id.txtEmail);
            txtpassword = (TextView) findViewById(R.id.txtPassword);
            btnkembali = (Button) findViewById(R.id.btnKembali);

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM account WHERE name = '" +
                    getIntent().getStringExtra("name") + "'",null);
            cursor.moveToFirst();
            if (cursor.getCount()>0)
            {
                cursor.moveToPosition(0);
                txtno.setText(cursor.getString(0).toString());
                txtname.setText(cursor.getString(1).toString());
                txtemail.setText(cursor.getString(2).toString());
                txtpassword.setText(cursor.getString(3).toString());


            }

            btnkembali.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
    }
}
