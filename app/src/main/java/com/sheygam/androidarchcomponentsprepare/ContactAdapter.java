package com.sheygam.androidarchcomponentsprepare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;


public class ContactAdapter extends Adapter<ContactAdapter.ContactViewHolder> {
    private List<ContactEntity> list;
    private Context context;
    private final ItemClickListener listener;

    public ContactAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactEntity curr = list.get(position);
        holder.nameTxt.setText(curr.getName());
        holder.phoneTxt.setText(curr.getPhone());
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setContacts(List<ContactEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<ContactEntity> getContacts() {
        return list;
    }

    class ContactViewHolder extends ViewHolder implements View.OnClickListener {
        TextView nameTxt;
        TextView phoneTxt;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            phoneTxt = itemView.findViewById(R.id.phoneTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = list.get(getAdapterPosition()).getId();
            listener.onItemClick(id);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int itemId);
    }
}
