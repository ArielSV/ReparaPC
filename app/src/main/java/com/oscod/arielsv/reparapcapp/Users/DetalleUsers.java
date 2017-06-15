package com.oscod.arielsv.reparapcapp.Users;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oscod.arielsv.reparapcapp.FirebaseReferences;
import com.oscod.arielsv.reparapcapp.R;

import java.util.ArrayList;
import java.util.List;

public class DetalleUsers extends AppCompatActivity  {

    List<Usuario> usuarioList;
    EditText nombre;
    EditText apellidoP;
    EditText apellidoM;
    EditText telefono;
    EditText ciudad;
    EditText email;
    FloatingActionButton btnRegister;
    FloatingActionButton btnUpdate;
    FloatingActionButton btnConfirm;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
             id = extras.getInt("ID");
        }
        setContentView(R.layout.activity_detalle_users);

        btnRegister = (FloatingActionButton) findViewById(R.id.btnRegister);
        btnUpdate = (FloatingActionButton) findViewById(R.id.btnDelete);
        nombre = (EditText) findViewById(R.id.nombre);
        apellidoP = (EditText) findViewById(R.id.apellidop);
        apellidoM = (EditText) findViewById(R.id.apellidom);
        telefono = (EditText) findViewById(R.id.telefono);
        ciudad = (EditText) findViewById(R.id.ciudad);
        email = (EditText) findViewById(R.id.email);
        btnConfirm = (FloatingActionButton) findViewById(R.id.btnConfirm);
        nombre.setEnabled(false);
        apellidoP.setEnabled(false);
        apellidoM.setEnabled(false);
        telefono.setEnabled(false);
        ciudad.setEnabled(false);
        email.setEnabled(false);





        usuarioList = new ArrayList<>();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
        myRef.child(FirebaseReferences.USER+""+id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                try
                {
                    nombre.setText(usuario.getNombre());
                    apellidoP.setText(usuario.getApellidop());
                    apellidoM.setText(usuario.getApellidom());
                    telefono.setText(usuario.getTelefono());
                    ciudad.setText(usuario.getCiudad());
                    email.setText(usuario.getEmail());

                } catch (Exception e){
                    e.printStackTrace();
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertUser();
                Toast.makeText(DetalleUsers.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(FirebaseReferences.USER+""+id).removeValue();
                AlertDialog.Builder alert = new AlertDialog.Builder(DetalleUsers.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_ok_layout,null);
                //TextView textView = (TextView) view.findViewById(R.id.textContent);
                Button button = (Button) view.findViewById(R.id.btnOk);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
                alert.setView(view);
                AlertDialog dialog = alert.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(email.getText().toString().equals("admin@msn.com"))) {
                    nombre.setEnabled(true);
                    apellidoP.setEnabled(true);
                    apellidoM.setEnabled(true);
                    telefono.setEnabled(true);
                    ciudad.setEnabled(true);
                    email.setEnabled(true);
                    btnUpdate.setVisibility(View.GONE);
                    btnRegister.setVisibility(View.GONE);
                    btnConfirm.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(DetalleUsers.this, "Imposible actualizar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void InsertUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
        Usuario usuario = new Usuario(nombre.getText().toString(), apellidoP.getText().toString(), apellidoM.getText().toString(), telefono.getText().toString(), ciudad.getText().toString(), email.getText().toString(),id);
        myRef.child(FirebaseReferences.USER+""+id).setValue(usuario);
    }



}