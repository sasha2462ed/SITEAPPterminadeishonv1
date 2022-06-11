package com.example.siteapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myGestion extends RecyclerView.Adapter<myGestion.Contenet> {

    ArrayList<Gestion> items;


    public myGestion(ArrayList<Gestion> items) {

        this.items=items;
    }

    @NonNull
    @Override
    public myGestion.Contenet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gestion, parent, false);

        return new Contenet(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myGestion.Contenet holder, int position) {
        holder.cierre.setText(items.get(position).cierre);
        holder.tipo.setText(items.get(position).tipo);
        holder.fecha.setText(items.get(position).fecha);
        holder.idd.setText(items.get(position).idd);

        /***/
        /***/


        /**/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("result","Item: "+String.valueOf(id));
    }
*/
    public class Contenet extends RecyclerView.ViewHolder {

        CardView cv;
        TextView cierre;
        TextView tipo;
        TextView fecha;
        TextView idd;




        public Contenet(@NonNull View itemView) {
            super(itemView);

            cv=itemView.findViewById(R.id.cv);
            cierre=itemView.findViewById(R.id.cierreg);
            tipo=itemView.findViewById(R.id.resumeng);
            fecha=itemView.findViewById(R.id.fechag);
            idd=itemView.findViewById(R.id.idd);

            /*cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("result","CardView");
                }
            });*/



        }


    }

//    public void updateData(ArrayList<Incidencias> items) {
//        this.items = items;
////        items.clear();
////        items.addAll(items);
//
//        //notifyDataSetChanged();
//    }

}
