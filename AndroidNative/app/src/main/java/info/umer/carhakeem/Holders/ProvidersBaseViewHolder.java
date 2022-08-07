package info.umer.carhakeem.Holders;


import android.view.View;
        import androidx.recyclerview.widget.RecyclerView;

public abstract class ProvidersBaseViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;
    public ProvidersBaseViewHolder(View itemView) {
        super(itemView);
    }
    protected abstract void clear();
    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}