package info.umer.carhakeem.Adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.umer.carhakeem.Holders.ProvidersBaseViewHolder;
import info.umer.carhakeem.Interfaces.IProviderRecycle;
import info.umer.carhakeem.Models.ApiCalls.internals.Provider;
import info.umer.carhakeem.R;

public class ProvidersRecyclerAdapter extends RecyclerView.Adapter<ProvidersBaseViewHolder> implements Filterable {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private List<Provider> mPostItems;
    private List<Provider> mPostItemsFiltered;
    IProviderRecycle _IProviderRecycle;
    Context con;
    public ProvidersRecyclerAdapter(List<Provider> postItems,Context con, IProviderRecycle _IProviderRecycle) {
        this.mPostItems = postItems;
        this.mPostItemsFiltered = postItems;
        this.con =con;
        this._IProviderRecycle = _IProviderRecycle;

    }
    @NonNull
    @Override
    public ProvidersBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_providers, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
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
        return mPostItemsFiltered == null ? 0 : mPostItemsFiltered.size();
    }
    public void addItems(List<Provider> postItems) {
        mPostItems.addAll(postItems);
        mPostItemsFiltered.addAll(postItems);
        notifyDataSetChanged();
    }
    public void addLoading() {
        isLoaderVisible = true;
        mPostItems.add(new Provider());

        notifyItemInserted(mPostItems.size() - 1);
    }
    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPostItems.size() - 1;
        Provider item = getItem(position);
        if (item != null) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear() {
        mPostItems.clear();
        notifyDataSetChanged();
    }
    Provider getItem(int position) {
        return mPostItems.get(position);
    }
    public class ViewHolder extends ProvidersBaseViewHolder {

        TextView textViewTitle;
        TextView textViewCategory;

        RatingBar rbRating;

        ImageView btnCall;
        ImageView btnBookOnline;
        ImageView btnLoc;
        TextView textViewDescription;
        ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            rbRating = itemView.findViewById(R.id.rbRating);
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
            Provider item = mPostItemsFiltered.get(position);

            textViewTitle.setText(item.getProviders().get(0).getProviderName());
            textViewDescription.setText(item.getProviders().get(0).getAddress());
            textViewCategory.setText(item.getProviders().get(0).getCategory().replace("·",""));
            rbRating.setRating(item.getAvg().floatValue());

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+item.getProviders().get(0).getContactNo()));
                    con.startActivity(intent);
                }
            });

            btnLoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q="+ item.getProviders().get(0).getAddress());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    con.startActivity(intent);
                }
            });


            btnBookOnline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _IProviderRecycle.onClickBookOnline(item.getProviders().get(0));
                }
            });
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


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mPostItemsFiltered = mPostItems;
                } else {
                    List<Provider> filteredList = new ArrayList<>();
                    for (Provider row : mPostItems) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if(row.getProviders() != null){
                        if (row.getProviders().get(0).getProviderName().toLowerCase().contains(charString.toLowerCase())
                                ||row.getProviders().get(0).getCategory().toLowerCase().contains(charString.toLowerCase())
                                ||row.getProviders().get(0).getAddress().toLowerCase().contains(charString.toLowerCase())
                                ||row.getProviders().get(0).getArea().toLowerCase().contains(charString.toLowerCase())
                                ||row.getProviders().get(0).getDistrict().toLowerCase().contains(charString.toLowerCase())

                        ) {
                            filteredList.add(row);
                        }}
                    }

                    mPostItemsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mPostItemsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mPostItemsFiltered = (ArrayList<Provider>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}