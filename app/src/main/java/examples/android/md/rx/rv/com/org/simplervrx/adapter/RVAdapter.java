package examples.android.md.rx.rv.com.org.simplervrx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import examples.android.md.rx.rv.com.org.simplervrx.R;

/**
 * Created by User on 11/4/2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
    public static final String TAG = RVAdapter.class.getName();
    public Context context;
    public ArrayList<String> lstRes;

    public RVAdapter(Context context, ArrayList<String> lstRes){
        Log.d(TAG, "RVAdapter");
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick" + view.getClass().toString());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.mTextViewTitle.setText(lstRes.get(position));
        Glide.with(context)
                .load("http://www.freemagebank.com/wp-content/uploads/edd/2015/06/GL0000280LR.jpg")
                .centerCrop()
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }

    public void setListContent(ArrayList<String>lstRes){
        this.lstRes = lstRes;
        notifyItemRangeChanged(0, lstRes.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mImageViewThumbnail;
        TextView mTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewThumbnail = (ImageView) itemView.findViewById(R.id.mImageViewThumbnail);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.mTextViewTitle);
            //new..
            itemView.setTag(itemView);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick");
        }
    }

}
