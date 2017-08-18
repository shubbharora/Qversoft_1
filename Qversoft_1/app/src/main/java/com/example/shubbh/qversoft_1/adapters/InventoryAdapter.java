package com.example.shubbh.qversoft_1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.shubbh.qversoft_1.R;
import com.example.shubbh.qversoft_1.models.InventoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Shubbh on 7/31/2017.
 */

public class InventoryAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private ArrayList<InventoryModel> mInventoryModelsList;
    ValueFilter valueFilter;
    ArrayList<InventoryModel> mStringFilterList;

    public InventoryAdapter(Context mContext, ArrayList<InventoryModel> mInventoryModelsList) {
        this.mContext = mContext;
        this.mInventoryModelsList = mInventoryModelsList;
        mStringFilterList = mInventoryModelsList;
    }

    public void refreshData() {
        notifyDataSetChanged();
    }

    public void deleteListItem(String itemName) {
        for (InventoryModel inventoryModel : mInventoryModelsList) {
            if (inventoryModel.getmProductName().equalsIgnoreCase(itemName)) {
                mInventoryModelsList.remove(inventoryModel);
                refreshData();
                break;
            }
        }
    }

    public int updateData(String itemName)
    { int x=1;
        for (InventoryModel inventoryModel : mInventoryModelsList) {
            if (inventoryModel.getmProductName().equalsIgnoreCase(itemName)) {
                // updating array list
                refreshData();
                x=0;
            }
        }
        return x;
    }

    @Override
    public int getCount() {
        return mInventoryModelsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInventoryModelsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        InventoryAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.inventory_item_shape, null);
            holder = new InventoryAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (InventoryAdapter.ViewHolder) convertView.getTag();
        }

        holder.mProductName.setText(mInventoryModelsList.get(position).getmProductName());
        holder.mAvailableStock.setText(mInventoryModelsList.get(position).getmAvailableStock());
        holder.mUnitCost.setText(mInventoryModelsList.get(position).getmUnitCost());
        holder.mGST.setText(mInventoryModelsList.get(position).getmGST());

        return convertView;
    }

    class ViewHolder {
        public TextView mProductName;
        public TextView mAvailableStock;
        public TextView mUnitCost;
        public TextView mGST;

        public ViewHolder(View view) {
            mProductName = (TextView) view.findViewById(R.id.product_name);
            mAvailableStock = (TextView) view.findViewById(R.id.available_stock);
            mUnitCost = (TextView) view.findViewById(R.id.unit_cost);
            mGST = (TextView) view.findViewById(R.id.GST);
        }
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<InventoryModel> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getmProductName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mInventoryModelsList = (ArrayList<InventoryModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
