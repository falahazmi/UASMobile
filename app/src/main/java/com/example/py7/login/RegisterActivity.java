package com.example.py7.login;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText TxNama, TxEmail,TxPassword, TxConPassword, TxInstansi, TxStPekerjaan, TxNomorTlpn;
    Button BtnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        TxNama = (EditText)findViewById(R.id.txNamaReg);
        TxEmail = (EditText)findViewById(R.id.txEmailReg);
        TxPassword = (EditText)findViewById(R.id.txPasswordReg);
        TxConPassword = (EditText)findViewById(R.id.txConPassword);
        TxInstansi = (EditText)findViewById(R.id.txInstansiReg);
        TxStPekerjaan = (EditText)findViewById(R.id.txStatusPekerjaanReg);
        TxNomorTlpn = (EditText)findViewById(R.id.txNomorTlpnReg);
        BtnRegister = (Button)findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView)findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to " +
                "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = TxNama.getText().toString().trim();
                String email = TxEmail.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String instansi = TxInstansi.getText().toString().trim();
                String stPekerjaan = TxStPekerjaan.getText().toString().trim();
                String nomorTlpn = TxNomorTlpn.getText().toString().trim();

                String conPassword = TxConPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (!password.equals(conPassword)){
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || email.equals("")){
                    Toast.makeText(RegisterActivity.this, "Email or Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBHelper.row_nama, nama);
                    values.put(DBHelper.row_email, email);
                    values.put(DBHelper.row_password, password);
                    values.put(DBHelper.row_instansi, instansi);
                    values.put(DBHelper.row_stpekerjaan, stPekerjaan);
                    values.put(DBHelper.row_noTlpn, nomorTlpn);

                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
