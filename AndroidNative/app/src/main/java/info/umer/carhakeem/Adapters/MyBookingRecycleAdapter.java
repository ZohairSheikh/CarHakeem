package info.umer.carhakeem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.Interfaces.IMyBooking;
import info.umer.carhakeem.Models.ApiCalls.internals.apiCallMyAppointmentResponseOfReponse;
import info.umer.carhakeem.R;
import info.umer.carhakeem.UI.Custom.Button_N;
import info.umer.carhakeem.UI.Custom.TextView_N;

public class MyBookingRecycleAdapter extends RecyclerView.Adapter<MyBookingRecycleAdapter.ViewHolder> {

    private List<apiCallMyAppointmentResponseOfReponse> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    IMyBooking _iMyBooking;

    public MyBookingRecycleAdapter(Context context, List<apiCallMyAppointmentResponseOfReponse> data, IMyBooking _iMyBooking) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this._iMyBooking = _iMyBooking;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rc_my_booking, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            holder.textViewTitle.setText(mData.get(position).getProviders().get(0).getProviderName());

        holder.textViewRate.setText("Rs " + mData.get(position).getProviders().get(0).getRate() + " /Hr");
        holder.tvStatus.setText(mData.get(position).getStatus());

        holder.textViewDescription.setText(mData.get(position).getProviders().get(0).getAddress());
        holder.textViewCategory.setText(mData.get(position).getProviders().get(0).getCategory().replace("·", ""));

        if (mData.get(position).getStatus().equals("pending")) {
            if (sharedPrefs.getString(Constants.userType).equals("provider")) {
                holder.btnStatusChange.setText("Confirm Booking");
            } else {
                holder.btnStatusChange.setVisibility(View.GONE);
            }
        } else if (mData.get(position).getStatus().equals("ongoing")) {
            if (sharedPrefs.getString(Constants.userType).equals("provider")) {
                holder.btnStatusChange.setText("Mark Completed");
            } else {
                holder.btnStatusChange.setVisibility(View.GONE);
            }
        }
        else if (mData.get(position).getStatus().equals("completed")) {
            if (sharedPrefs.getString(Constants.userType).equals("provider")) {
                holder.btnStatusChange.setText("View Invoice");
            } else {

                holder.btnStatusChange.setText("Rate Provider");
            }
        }

        holder.btnStatusChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _iMyBooking.onClickStatusChange(mData.get(position)

                );
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView_N tvStatus;
        private TextView textViewDescription;
        private TextView textViewCategory;
        private TextView textViewRate;

        private Button_N btnStatusChange;


        ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            btnStatusChange = (Button_N) itemView.findViewById(R.id.btnStatusChange);
            tvStatus = (TextView_N) itemView.findViewById(R.id.tvStatus);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewCategory = (TextView) itemView.findViewById(R.id.textViewCategory);
            textViewRate = (TextView) itemView.findViewById(R.id.textViewRate);


        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    apiCallMyAppointmentResponseOfReponse getItem(int id) {
        return mData.get(id);
    }



    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}