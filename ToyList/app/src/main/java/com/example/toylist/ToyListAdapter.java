package com.example.toylist;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ToyListAdapter extends RecyclerView.Adapter<ToyListAdapter.ToyListAdapterrViewHolder>{

    //private int mNumItems;
    private String[] mToyItems;

    public ToyListAdapter(String[] toyItems){
        mToyItems = toyItems;
    }

    @NonNull
    @Override
    public ToyListAdapterrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.toy_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToLayoutImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToLayoutImmediately);
        ToyListAdapterrViewHolder viewHolder = new ToyListAdapterrViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToyListAdapterrViewHolder holder, final int position) {
        holder.toyListItemView.setText(mToyItems[position]);


        if(position % 2 == 0){
            holder.toyListItemView.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else{
            holder.toyListItemView.setBackgroundColor(Color.parseColor("#0000FF"));
        }

        holder.toyListItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), (position+1) + " Item was clicked", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", (position+1) + " Item was clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToyItems.length;
    }

    //View Holder
    public class ToyListAdapterrViewHolder extends RecyclerView.ViewHolder{

        TextView toyListItemView;

        public ToyListAdapterrViewHolder(@NonNull View itemView) {
            super(itemView);

            toyListItemView = (TextView) itemView.findViewById(R.id.tv_item_toy);
        }
    }

}
