package info.umer.carhakeem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.umer.carhakeem.Holders.ProvidersBaseViewHolder;
import info.umer.carhakeem.Interfaces.IProviderRecycle;
import info.umer.carhakeem.Models.ApiCalls.internals.Providers;
import info.umer.carhakeem.R;

public class MyServicesRecycleAdapter extends RecyclerView.Adapter<ProvidersBaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private List<Providers> mPostItems;
    IProviderRecycle _IProviderRecycle;
    Context con;
    public MyServicesRecycleAdapter(List<Providers> postItems,Context con, IProviderRecycle _IProviderRecycle) {
        this.mPostItems = postItems;
        this.con =con;
        this._IProviderRecycle = _IProviderRecycle;
    }
    @NonNull
    @Override
    public ProvidersBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new MyServicesRecycleAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_my_services, parent, false));
            case VIEW_TYPE_LOADING:
                return new MyServicesRecycleAdapter.ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ProvidersBaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mPostItems.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }
    @Override
    public int getItemCount() {
        return mPostItems == null ? 0 : mPostItems.size();
    }
    public void addItems(List<Providers> postItems) {
        mPostItems.addAll(postItems);
        notifyDataSetChanged();
    }
    public void addLoading() {
        isLoaderVisible = true;
        mPostItems.add(new Providers());
        notifyItemInserted(mPostItems.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPostItems.size() - 1;
        Providers item = getItem(position);
        if (item != null) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear() {
        mPostItems.clear();
        notifyDataSetChanged();
    }
    Providers getItem(int position) {
        return mPostItems.get(position);
    }
    public class ViewHolder extends ProvidersBaseViewHolder {

        TextView textViewTitle;
        TextView textViewCategory;
        TextView textViewRate;


        ImageView btnCall;
        ImageView btnBookOnline;
        ImageView btnLoc;
        TextView textViewDescription;
        ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRate = itemView.findViewById(R.id.textViewRate);

            btnCall = itemView.findViewById(R.id.btnCall);
            btnBookOnline = itemView.findViewById(R.id.btnBookOnline);
            btnLoc = itemView.findViewById(R.id.btnLocationOnMap);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);





        }
        protected void clear() {
        }
        public void onBind(int position) {
            super.onBind(position);
            Providers item = mPostItems.get(position);

            textViewTitle.setText(item.getProviderName());
            textViewDescription.setText(item.getAddress());
            textViewCategory.setText(item.getCategory().replace("·",""));
            textViewRate.setText("Rs"+ item.getRate() +" /Hr");


        }
    }
    public class ProgressHolder extends ProvidersBaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);

        }
        @Override
        protected void clear() {
        }
    }
}