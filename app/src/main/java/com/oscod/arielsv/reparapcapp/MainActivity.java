package com.oscod.arielsv.reparapcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oscod.arielsv.reparapcapp.Users.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity  {
    List<Usuario> usuarioList;
    EditText user;
    EditText pass;
    Button button;
    static boolean login = true;
    boolean error;
    ProgressDialog progressDialog;
    boolean start=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        user.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               user.setText("admin@msn.com");
                return false;
            }
        });
        usuarioList = new ArrayList<>();
        button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start) {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                    finish();
                }
                error = true;
               ProcessData p = new ProcessData();
                p.execute(10);
            }
        });
    }
    public class ProcessData extends AsyncTask<Integer,String,String> {
        @Override
        protected String doInBackground(Integer... params) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Usuario usuario = dataSnapshot1.getValue(Usuario.class);
                        //usuarioList.add(dataSnapshot.getValue(Usuario.class));
                        if (login) {
                            if (user.getText().toString().equals(usuario.getEmail()) && pass.getText().toString().equals(usuario.getTelefono())) {
                                Intent intent = new Intent(MainActivity.this, Home.class);
                                intent.putExtra(FirebaseReferences.NAME, usuario.getNombre());
                                intent.putExtra(FirebaseReferences.EMAIL, usuario.getEmail());
                                intent.putExtra(FirebaseReferences.APELLIDOP, usuario.getApaterno());
                                intent.putExtra(FirebaseReferences.APELLIDOM, usuario.getAmaterno());
                                startActivity(intent);
                                finish();
                                error = true;
                                break;
                            } else {
                                error = false;
                            }
                        }
                    }
                    if (error == false) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            return "DONE";
        }
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this,ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setProgressStyle(ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setTitle("Autenticando");
            progressDialog.setMessage("Cargando... por favor espere.");
           // progressDialog.setIndeterminate(true);
           // progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(String s) {

        }
    }
}
