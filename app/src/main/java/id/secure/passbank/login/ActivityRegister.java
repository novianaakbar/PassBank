package id.secure.passbank.login;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.secure.passbank.MainActivity;
import id.secure.passbank.R;
import id.secure.passbank.login.sqlite.DatabaseHandler;
import id.secure.passbank.login.sqlite.DatabaseHelper;
import id.secure.passbank.login.utility.Utility;

public class ActivityRegister extends AppCompatActivity {

    EditText edt_name,
            edt_username,
            edt_password;
    Button btn_simpan;

    DatabaseHandler dbHandler;
    DatabaseHelper dbHelp;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHandler=new DatabaseHandler(getApplicationContext());

        context=getApplicationContext();
        edt_name =(EditText)findViewById(R.id.editName);
        edt_username=(EditText)findViewById(R.id.editUsername);
        edt_password=(EditText)findViewById(R.id.editPassword);
        btn_simpan=(Button)findViewById(R.id.btnSimpan);


        btn_simpan = (Button) findViewById(R.id.btnSimpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GetEditText()){

                    try {
                        dbHandler.insertHistory(edt_name.getText().toString(),
                                edt_username.getText().toString(),edt_password.getText().toString());

                        Utility.toast("Data Berhasil Disimpan, Silahkan Login Untuk Masuk Aplikasi",getApplicationContext());

                        Intent i=new Intent(ActivityRegister.this, ActivityLogin.class);
                        startActivity(i);
                        ActivityRegister.this.finish();

                    } catch (Exception e) {
                        Utility.toast("Data Gagal Disimpan",getApplicationContext());
                    }

                }else{
                    Utility.toast("Form mohon dilengkapi",getApplicationContext());
                }


            }

            private boolean GetEditText() {
                if(!edt_name.getText().toString().equals("")
                        && !edt_username.getText().toString().equals("")
                        && !edt_password.getText().toString().equals("") ){

                    return true;

                }else{

                    return false;
                }
            }
        });
        };


}
