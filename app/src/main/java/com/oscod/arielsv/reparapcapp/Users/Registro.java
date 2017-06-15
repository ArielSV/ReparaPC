package com.oscod.arielsv.reparapcapp.Users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oscod.arielsv.reparapcapp.FirebaseReferences;
import com.oscod.arielsv.reparapcapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ArielSV on 28/04/17.
 */

public class Registro extends Fragment implements View.OnClickListener {

    EditText nombre;
    EditText apellidoP;
    EditText apellidoM;
    EditText telefono;
    EditText ciudad;
    EditText email;
    FloatingActionButton btnRegister;
    int aux = 0;
    int userid=1;
    List<Usuario> usuarioList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioList = new ArrayList<>();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuarioList.removeAll(usuarioList);
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Usuario usuario = dataSnapshot1.getValue(Usuario.class);
                    dataSnapshot.getKey();
                     userid = usuario.getId();
                    if (!(userid == 0)) {
                        if (aux < userid) {
                            aux = userid;
                        }
                        userid=aux+1;
                    }




                    usuarioList.add(usuario);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_registro, null);
        btnRegister = (FloatingActionButton) view.findViewById(R.id.btnRegister);
        nombre = (EditText) view.findViewById(R.id.nombre);
        apellidoP = (EditText) view.findViewById(R.id.apellidop);
        apellidoM = (EditText) view.findViewById(R.id.apellidom);
        telefono = (EditText) view.findViewById(R.id.telefono);
        ciudad = (EditText) view.findViewById(R.id.ciudad);
        email = (EditText) view.findViewById(R.id.email);
        btnRegister.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:

                InsertUser();
                //Toast.makeText(getContext(), ""+usuarioList.size(), Toast.LENGTH_SHORT).show();
                break;

        }
    }


    public void InsertUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
        Usuario usuario = new Usuario(nombre.getText().toString(), apellidoP.getText().toString(), apellidoM.getText().toString(), telefono.getText().toString(), ciudad.getText().toString(), email.getText().toString(),userid);
        myRef.child(FirebaseReferences.USER+""+userid).setValue(usuario);
    }


}
