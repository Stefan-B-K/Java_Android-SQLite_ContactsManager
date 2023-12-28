package com.istef.sqlite_contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.istef.sqlite_contactmanager.ContactDetailsActivity;
import com.istef.sqlite_contactmanager.R;
import com.istef.sqlite_contactmanager.model.Contact;

import java.util.List;


public class MainRecViewAdapter extends RecyclerView.Adapter<MainRecViewAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contactList;


    public MainRecViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.contactPhone.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName;
        public TextView contactPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.txtName);
            contactPhone = itemView.findViewById(R.id.txtPhone);

            itemView.setOnClickListener(v -> {                  // or contactName.setOnClickListener
                int position = getAdapterPosition();
                Contact contact = contactList.get(position);

                Intent intent = new Intent(context, ContactDetailsActivity.class);
                intent.putExtra("name", contact.getName());
                intent.putExtra("phone", contact.getPhoneNumber());
                context.startActivity(intent);
            });

        }

    }
}
