package com.example.githubquery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GithubListAdapter extends RecyclerView.Adapter<GithubListAdapter.GithubListAdapterViewHolder>{

    private JSONObject jsonObject;
    private Context context;

    public GithubListAdapter(String s){
        Log.d("ABC", s);
        try{
            this.jsonObject = new JSONObject(s);}
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @NonNull
    @Override
    public GithubListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        int layoutForListItem = R.layout.github_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToLayoutImmediately = false;

        View view = inflater.inflate(layoutForListItem, parent, shouldAttachToLayoutImmediately);
        GithubListAdapterViewHolder viewHolder = new GithubListAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubListAdapterViewHolder holder, int position) {

        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject item = null;
        try {
            item = jsonArray.getJSONObject(position);
            String desc = item.get("description").toString();
            holder.description.setText(desc);
            final String url = item.get("html_url").toString();
            holder.github_url.setText(url);
            holder.github_url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            });
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray.length();
    }

    public class GithubListAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView github_url;

        public GithubListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.tv_description);
            github_url = itemView.findViewById(R.id.tv_github_url);
        }
    }







}
