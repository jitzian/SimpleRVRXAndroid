package examples.android.md.rx.rv.com.org.simplervrx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;

/**
 * Created by User on 11/15/2016.
 */

public class RVSwipeAdapter extends RecyclerView.Adapter<RVSwipeAdapter.ViewHolder>{
    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private ArrayList<Result> lstRes;
    private ArrayList<Result> itemsPendingRemoval = new ArrayList<>();
    private Context context;

    int lastInsertedIndex; // so we can add some more items for testing purposes
    boolean undoOn; // is undo on, you can turn it on from the toolbar menu
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Result, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be

    public RVSwipeAdapter(ArrayList<Result> lstRes, Context context) {
        this.lstRes = lstRes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result item = lstRes.get(position);
        holder.mTextViewTitle.setText(lstRes.get(position).getName());
        Glide.with(context)
                .load(lstRes.get(position).getOwner().getAvatarUrl())
                .centerCrop()
                .fitCenter()
                .into(holder.mImageViewThumbnail);

        if(itemsPendingRemoval.contains(lstRes.get(position))){
            // we need to show the "undo" state of the row
            holder.itemView.setBackgroundColor(Color.RED);
            holder.mTextViewTitle.setVisibility(View.GONE);
            holder.undo_button.setVisibility(View.VISIBLE);
            holder.undo_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Runnable pendingRemovalRunnable = pendingRunnables.get(item);
                    pendingRunnables.remove(item);
                    if (pendingRemovalRunnable != null) handler.removeCallbacks(pendingRemovalRunnable);
                    itemsPendingRemoval.remove(item);
                    // this will rebind the row in "normal" state
                    notifyItemChanged(lstRes.indexOf(item));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lstRes.size() > 0 ? lstRes.size() : 0;
    }

    /***
     * This methods are for implement swiping funcionality in the Recyclerview
     *
     */
    public void setUndoOn(boolean undoOn) {
        this.undoOn = undoOn;
    }

    public boolean isUndoOn() {
        return undoOn;
    }

    public void pendingRemoval(int position) {
//        final String item = items.get(position);
        final Result item = lstRes.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(lstRes.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
//        String item = items.get(position);
        Result item = lstRes.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (lstRes.contains(item)) {
            lstRes.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
//        String item = items.get(position);
        Result item = lstRes.get(position);
        return itemsPendingRemoval.contains(item);
    }


    //ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageViewThumbnail;
        TextView mTextViewTitle;
        Button undo_button;
        public ViewHolder(View itemView) {
            super(itemView);
            undo_button = (Button) itemView.findViewById(R.id.undo_button);
            mImageViewThumbnail = (ImageView) itemView.findViewById(R.id.mImageViewThumbnail);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.mTextViewTitle);
        }
    }

}
