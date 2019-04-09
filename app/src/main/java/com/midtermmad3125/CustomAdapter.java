package com.midtermmad3125;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.midtermmad3125.ui.WeatherDetailsActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> date ;
    ArrayList<Double> minimumTemp ;
    ArrayList<Double> maxTemp ;
    ArrayList<String> wdetails ;
    ArrayList<String> wdescription ;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> date, ArrayList<Double> minimumTemp, ArrayList<Double> maxTemp, ArrayList<String> wdetails, ArrayList<String> wdescription){
        this.context = context;
        this.date = date;
        this.minimumTemp = minimumTemp;
        this.maxTemp = maxTemp;
        this.wdetails = wdetails;
        this.wdescription = wdescription;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        holder.dt.setText(date.get(i));
        holder.mintp.setText(minimumTemp.get(i).toString());
        holder.maxtp.setText(maxTemp.get(i).toString());
        holder.wdet.setText(wdetails.get(i));
        holder.wdes.setText(wdescription.get(i));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, wdetails.get(i), Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(v.getContext(), WeatherDetailsActivity.class);
                mIntent.putExtra("date", date.get(i));
                mIntent.putExtra("min", minimumTemp.get(i).toString());
                mIntent.putExtra("max", maxTemp.get(i).toString());
                mIntent.putExtra("wdetail", wdetails.get(i));
                mIntent.putExtra("wdesc", wdescription.get(i));
                v.getContext().startActivity(mIntent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dt, mintp, maxtp, wdet, wdes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dt = (TextView) itemView.findViewById(R.id.dateTime);
            mintp = (TextView) itemView.findViewById(R.id.minTemp);
            maxtp = (TextView) itemView.findViewById(R.id.maxTemp);
            wdet = (TextView) itemView.findViewById(R.id.wDetails);
            wdes = (TextView) itemView.findViewById(R.id.wDescription);
        }
    }
}