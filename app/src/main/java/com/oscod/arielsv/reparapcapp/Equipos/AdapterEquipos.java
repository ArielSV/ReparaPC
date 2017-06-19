package com.oscod.arielsv.reparapcapp.Equipos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oscod.arielsv.reparapcapp.R;
import com.oscod.arielsv.reparapcapp.Users.Adapter;
import com.oscod.arielsv.reparapcapp.Users.Usuario;

import java.util.List;

/**
 * Created by ariel on 19/06/17.
 */

class AdapterEquipos extends RecyclerView.Adapter<AdapterEquipos.UsersHolder> {
    List<Equipos> equiposList;
    View.OnClickListener listener;

    public AdapterEquipos(List<Equipos> equiposList, View.OnClickListener listener) {
        this.equiposList = equiposList;
        this.listener = listener;
    }

    @Override
    public AdapterEquipos.UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_equipos_recycler, parent, false);
        AdapterEquipos.UsersHolder holder = new AdapterEquipos.UsersHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterEquipos.UsersHolder holder, int position) {
        Equipos equipos = equiposList.get(position);
        holder.equipo.setText(equipos.getEquipo());
        holder.marca.setText(equipos.getMarca());


        holder.container.setOnClickListener(listener);
        holder.container.setTag(equiposList.get(position));

    }

    @Override
    public int getItemCount() {
        return equiposList.size();
    }

    public static class UsersHolder extends RecyclerView.ViewHolder {

        TextView equipo, marca;
        private LinearLayout container;

        public UsersHolder(View itemView) {
            super(itemView);
           equipo=(TextView) itemView.findViewById(R.id.equipo_ok);
            marca= (TextView) itemView.findViewById(R.id.marca_equipo_ok);
            container = (LinearLayout) itemView.findViewById(R.id.container_equipos);
        }
    }
}