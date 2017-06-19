package com.oscod.arielsv.reparapcapp.Users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oscod.arielsv.reparapcapp.R;

import java.util.List;

/**
 * Created by ArielSV on 07/06/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.UsersHolder> {

    List<Usuario> usuarioList;
    View.OnClickListener listener;

    public Adapter(List<Usuario> usuarioList, View.OnClickListener listener) {
        this.usuarioList = usuarioList;
        this.listener = listener;
    }

    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users_recycler,parent,false);
        UsersHolder holder = new UsersHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);
        holder.nombre.setText(usuario.getNombre());
        holder.apeillidop.setText(usuario.getApaterno());
        holder.apellidom.setText(usuario.getAmaterno());
        holder.telefono.setText(usuario.getTelefono());
        holder.ciudad.setText(usuario.getCiudad());
        holder.email.setText(usuario.getEmail());


        holder.container.setOnClickListener(listener);
        holder.container.setTag(usuarioList.get(position));

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public static class  UsersHolder extends RecyclerView.ViewHolder{

        TextView nombre,apeillidop,apellidom,telefono,ciudad,email;
        private LinearLayout container;

        public UsersHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.nombrerecycler);
            apeillidop = (TextView)itemView.findViewById(R.id.apellidoprecycler);
            apellidom = (TextView)itemView.findViewById(R.id.apellidomrecycler);
            ciudad = (TextView)itemView.findViewById(R.id.ciudadrecycler);
            telefono = (TextView)itemView.findViewById(R.id.telefonorecycler);
            email = (TextView)itemView.findViewById(R.id.emailrecycler);
            container = (LinearLayout) itemView.findViewById(R.id.container);
        }
    }
}
