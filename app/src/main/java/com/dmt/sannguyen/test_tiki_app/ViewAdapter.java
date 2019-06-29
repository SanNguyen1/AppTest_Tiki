package com.dmt.sannguyen.test_tiki_app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.util.Objects.*;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.Viewholder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> Ten = new ArrayList<>();
    private Context mContext;
    public static Random random;
    public static int color;

    public ViewAdapter(Context mContext, ArrayList<String> ten) {
        Ten = ten;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG,"onCreateViewHolder: called.");
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_view_button,viewGroup,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        random = new Random();
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        viewholder.txtten.setText(Ten.get(i));
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        shape.setCornerRadius(15);
        viewholder.txtten.setBackgroundDrawable(shape);


    }

    @Override
    public int getItemCount() {
        return Ten.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView txtten;

        public Viewholder(View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.txtTen);
        }
    }
}
