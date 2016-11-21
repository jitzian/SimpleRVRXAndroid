package examples.android.md.rx.rv.com.org.simplervrx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;

/**
 * Created by User on 11/20/2016.
 */

public class RVBehaviorAdapter extends RecyclerView.Adapter<RVBehaviorAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Result> lstRes;

    public RVBehaviorAdapter(Context context, ArrayList lstRes) {
        this.context = context;
        this.lstRes = lstRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewTitle.setText(lstRes.get(position).getName());
        Glide.with(context)
                .load(lstRes.get(position).getOwner().getAvatarUrl())
                .fitCenter()
                .centerCrop()
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
        }
    }
}
