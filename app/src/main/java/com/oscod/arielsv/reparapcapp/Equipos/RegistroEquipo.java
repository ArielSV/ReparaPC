package com.oscod.arielsv.reparapcapp.Equipos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oscod.arielsv.reparapcapp.FirebaseReferences;
import com.oscod.arielsv.reparapcapp.R;
import com.oscod.arielsv.reparapcapp.Users.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariel on 18/06/17.
 */

public class RegistroEquipo extends Fragment implements View.OnClickListener {

    EditText descripcion;
    EditText equipo;
    EditText estado;
    EditText marca;
    EditText observaciones;
    EditText password;
    FloatingActionButton btnRegister;
    int aux = 0;
    int userid=1;
    String usermail;
    static String id;
    List<Equipos> equiposlist;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("ID");
        }
        equiposlist = new ArrayList<>();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.EQUIPOSREFERENCES);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                equiposlist.removeAll(equiposlist);
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Equipos equipos = dataSnapshot1.getValue(Equipos.class);
                    dataSnapshot.getKey();
                    userid = Integer.parseInt(equipos.getId());
                    //usermail = equipos.getEmail();
                    if (!(userid == 0)) {
                        if (aux < userid) {
                            aux = userid;
                        }
                        userid=aux+1;
                    }
                    equiposlist.add(equipos);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_equipos_registro,null);
        /*Bundle b = getActivity().getIntent().getExtras();
        id = b.getString("id"); */
        //String name=this.getArguments().getString("id").toString();
        descripcion = (EditText) view.findViewById(R.id.descripcion);
        equipo = (EditText) view.findViewById(R.id.equipo);
        estado = (EditText) view.findViewById(R.id.estado_equipo);
        marca = (EditText) view.findViewById(R.id.marca_equipo);
        observaciones = (EditText) view.findViewById(R.id.observaciones);
        password = (EditText) view.findViewById(R.id.password);
        btnRegister = (FloatingActionButton) view.findViewById(R.id.btnRegisterEquipo);
        btnRegister.setOnClickListener(this);

        return view;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegisterEquipo:
                InserEquipo();
        }

    }

    public void InserEquipo() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.EQUIPOSREFERENCES);
        Equipos equipos = new Equipos(descripcion.getText().toString(),equipo.getText().toString(),estado.getText().toString(),String.valueOf(userid),id,marca.getText().toString(),observaciones.getText().toString(),password.getText().toString());
        myRef.child(FirebaseReferences.EQUIPOS+""+userid).setValue(equipos);
    }

}
