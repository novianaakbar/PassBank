package id.secure.passbank.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.secure.passbank.MainActivity;
import id.secure.passbank.R;
import id.secure.passbank.login.sqlite.DatabaseHandler;
import id.secure.passbank.login.utility.Utility;

public class ActivityLogin extends AppCompatActivity {
    DatabaseHandler dbHandler;
    EditText username, password;
    Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DatabaseHandler(getApplicationContext());
        Layout();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ActivityRegister.class);
                startActivity(i);
                ActivityLogin.this.finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GetEditText()) {
                    if (dbHandler.getCount(username.getText().toString(),
                            password.getText().toString()) > 0) {

                        Utility.toast("Login Sukses", getApplicationContext());

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        ActivityLogin.this.finish();
                    } else {
                        Utility.toast("Login Gagal silahkan coba lagi", getApplicationContext());
                    }
                } else {
                    Utility.toast("Form mohon dilengkapi", getApplicationContext());
                }
            }





            private boolean GetEditText() {
                if (!username.getText().toString().equals("")
                        && !password.getText().toString().equals("")) {

                    return true;

                } else {

                    return false;
                }

            }

        });
    }

    private void Layout() {
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        btn_login= (Button)   findViewById(R.id.btnLogin);
        btn_register= (Button)   findViewById(R.id.btnRegister);
    }


}
