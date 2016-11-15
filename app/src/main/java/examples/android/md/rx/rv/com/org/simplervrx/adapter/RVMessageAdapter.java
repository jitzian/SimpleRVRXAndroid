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
import examples.android.md.rx.rv.com.org.simplervrx.model.Example;
import examples.android.md.rx.rv.com.org.simplervrx.model.Post;

/**
 * Created by User on 11/6/2016.
 */

public class RVMessageAdapter extends RecyclerView.Adapter<RVMessageAdapter.ViewHolder>{
    private static final String TAG = RVMessageAdapter.class.getName();
    private Context context;
    private ArrayList<Example> lstRes;

    public RVMessageAdapter(Context context, ArrayList<Example> lstRes) {
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.mTextViewTitle.setText(lstRes.get(position).getCategory().getTitle());
        Glide.with(context)
                .load("http://kaboompics.com/files/upload/o_1avuhbl3artq1gpb907t551pkv7_new.jpg")
                .centerCrop()
                .fitCenter()
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageViewThumbnail;
        TextView mTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageViewThumbnail = (ImageView) itemView.findViewById(R.id.mImageViewThumbnail);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.mTextViewTitle);
            itemView.setTag(itemView);
        }
    }

}
