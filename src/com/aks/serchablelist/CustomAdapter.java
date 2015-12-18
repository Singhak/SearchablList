package com.aks.serchablelist;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter implements Filterable{
    
    LayoutInflater inflater;
    ArrayList<String> datalist, filterlist;
    private ItemFilter itemFilter = new ItemFilter();
    
    public CustomAdapter(Activity activity, ArrayList<String> data){
        inflater = activity.getLayoutInflater();
        datalist = data;
        filterlist = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return filterlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder;
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.list_adapter, parent, false);
            holder = new Viewholder(row);
            row.setTag(holder);
        }
        holder = (Viewholder) row.getTag();
        holder.textView.setText(filterlist.get(position));
        return row;
    }
    
    public class Viewholder {
        
        TextView textView;
        public Viewholder(View view) {
            textView = (TextView) view.findViewById(R.id.txtview);
        }
    }

    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        return itemFilter;
    }
    
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            ArrayList<String> newfilteredList = new ArrayList<String>();
            
            for (int i = 0; i < datalist.size(); i++) {
                if(datalist.get(i).toLowerCase().contains(filterString)) {
                    newfilteredList.add(datalist.get(i));
                }
            }
            
            results.values = newfilteredList;
            results.count = newfilteredList.size();
            return results;

        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                FilterResults results) {    
            filterlist = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }
}
