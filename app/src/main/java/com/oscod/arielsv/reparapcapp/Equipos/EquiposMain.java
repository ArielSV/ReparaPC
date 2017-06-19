package com.oscod.arielsv.reparapcapp.Equipos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class EquiposMain extends AppCompatActivity {

    RecyclerView rv;
    List<Usuario> usuarioList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos_main);
        usuarioList = new ArrayList<>();


        FirebaseDatabase database =FirebaseDatabase.getInstance();
        adapter = new Adapter(usuarioList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.container:
                      /*  Usuario usuario =  (Usuario) v.getTag();
                        Intent intent = new Intent(EquiposMain.this,DetalleEquipo.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id",usuario.getID());
                        RegistroEquipo fragobj = new RegistroEquipo();
                        fragobj.setArguments(bundle);
                        startActivity(intent); */
                        Usuario usuario =  (Usuario) v.getTag();
                        RegistroEquipo myFragment = new RegistroEquipo();
                        Bundle bundle =  new Bundle();

                        bundle.putString("id", usuario.getID());
                        myFragment.setArguments(bundle);
                        //THEN NOW SHOW OUR FRAGMENT
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,myFragment).addToBackStack(null).commit();
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
                    // easy
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


        rv=(RecyclerView) findViewById(R.id.recyclerEquipos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

    }
}
