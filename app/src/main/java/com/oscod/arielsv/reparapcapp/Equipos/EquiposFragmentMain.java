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
 * Created by ariel on 19/06/17.
 */

public class EquiposFragmentMain  extends Fragment{

    RecyclerView rv;
    List<Usuario> usuarioList;
    Adapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new Adapter(usuarioList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.container:
                        Usuario lista = (Usuario) v.getTag();
                        Toast.makeText(getContext(), "click:" + lista.getID(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),DetalleEquipo.class);
                        intent.putExtra("ID",lista.getID());
                        startActivity(intent);
                        break;

                }
            }
        });


        DatabaseReference myRef = database.getReference(FirebaseReferences.USERSREFERENCES);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usuarioList.removeAll(usuarioList);
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Usuario usuario = dataSnapshot1.getValue(Usuario.class);
                    dataSnapshot.getKey();
                    usuarioList.add(usuario);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_equipos_main,null);
        rv=(RecyclerView) view.findViewById(R.id.recyclerEquipos);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        return view;
    }
}
