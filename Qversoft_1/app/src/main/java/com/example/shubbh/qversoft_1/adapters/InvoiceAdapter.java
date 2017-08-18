package com.example.shubbh.qversoft_1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shubbh.qversoft_1.R;
import com.example.shubbh.qversoft_1.models.InvoicesModel;

import java.util.ArrayList;

public class InvoiceAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<InvoicesModel> mInvoicesModelsList;

    public InvoiceAdapter(Context mContext, ArrayList<InvoicesModel> mInvoicesModelsList) {
        this.mContext = mContext;
        this.mInvoicesModelsList = mInvoicesModelsList;
    }

    @Override
    public int getCount() {
        return mInvoicesModelsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInvoicesModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.invoice_item_shape, null);  //what parameter is null here - parent?
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mInvoicerNumber.setText(mInvoicesModelsList.get(position).getInvoiceNumber());
        holder.mInvoiceDate.setText(mInvoicesModelsList.get(position).getInvoiceDate());
        holder.mAmount.setText(mInvoicesModelsList.get(position).getInvoiceAmount());

        return convertView;
    }

    class ViewHolder {
        public TextView mInvoicerNumber;
        public TextView mInvoiceDate;
        public TextView mAmount;

        public ViewHolder(View view) {
            mInvoicerNumber = (TextView) view.findViewById(R.id.invoice_number);
            mInvoiceDate = (TextView) view.findViewById(R.id.invoice_date);
            mAmount = (TextView) view.findViewById(R.id.invoice_amount);
        }

    }
}