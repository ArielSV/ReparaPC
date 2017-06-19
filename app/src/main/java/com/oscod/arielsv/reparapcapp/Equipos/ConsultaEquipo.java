package com.oscod.arielsv.reparapcapp.Equipos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oscod.arielsv.reparapcapp.FirebaseReferences;
import com.oscod.arielsv.reparapcapp.R;
import com.oscod.arielsv.reparapcapp.Users.Adapter;
import com.oscod.arielsv.reparapcapp.Users.DetalleUsers;
import com.oscod.arielsv.reparapcapp.Users.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ariel on 18/06/17.
 */

public class ConsultaEquipo extends Fragment {


    RecyclerView rv;
    List<Equipos> usuarioList;
    AdapterEquipos adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioList = new ArrayList<>();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        adapter = new AdapterEquipos(usuarioList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.container:
                        Equipos lista = (Equipos) v.getTag();
                        Toast.makeText(getContext(), ""+lista.getIdusuario(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        DatabaseReference myRef = database.getReference(FirebaseReferences.EQUIPOSREFERENCES);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usuarioList.removeAll(usuarioList);
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    // easy
                    Equipos equipos = dataSnapshot1.getValue(Equipos.class);
                    String idd= RegistroEquipo.id;
                    if (idd.equals(equipos.getIdusuario().toString())) {
                        usuarioList.add(equipos);
                    }
                }
                adapter.notifyDataSetChanged();



//                usuarioList.removeAll(usuarioList);
//              //  for (DataSnapshot snapshot:
//                  //      dataSnapshot.getChildren()){
//                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
//                    dataSnapshot.getKey();
//                    usuarioList.add(usuario);
//                //}
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_equipos_consulta,null);
        rv=(RecyclerView) view.findViewById(R.id.recyclerEquiposDetalle);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        return view;


    }
}
