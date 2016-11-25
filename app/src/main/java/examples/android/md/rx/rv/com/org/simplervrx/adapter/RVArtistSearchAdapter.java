package examples.android.md.rx.rv.com.org.simplervrx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.model.Artists;
import examples.android.md.rx.rv.com.org.simplervrx.model.Item;

/**
 * Created by User on 11/24/2016.
 */

public class RVArtistSearchAdapter extends RecyclerView.Adapter<RVArtistSearchAdapter.ViewHolder>{
    private static final String TAG = RVArtistSearchAdapter.class.getSimpleName();
    private ArrayList<Item> lstRes;
    private Context context;

    public RVArtistSearchAdapter(ArrayList<Item> lstRes, Context context) {
        this.lstRes = lstRes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewTitle.setText(lstRes.get(position).getName());
        Glide.with(context)
                .load("http://www.freemagebank.com/wp-content/uploads/edd/2015/04/GL0000228-1560x1997.jpg")
                .fitCenter()
                .centerCrop()
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.mImageViewThumbnail)
        ImageView mImageViewThumbnail;

        @BindView(R.id.mTextViewTitle)
        TextView mTextViewTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
