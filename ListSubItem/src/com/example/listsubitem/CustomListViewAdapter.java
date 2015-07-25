package com.example.listsubitem;

import java.util.List;

import com.example.listsubitem.MainActivity.ListViewItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter{

	LayoutInflater inflater;
	List<ListViewItem> listItems;
	
	public CustomListViewAdapter(Activity context, List<ListViewItem> listItems){
		super();
		
		this.listItems = listItems;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
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
		
		ListViewItem item = listItems.get(position);
		View view = convertView;
		
		if (convertView == null){
			view = inflater.inflate(R.layout.item_row, null);
		}
		
		TextView txtTitle = (TextView) view.findViewById(R.id.textView1);
		TextView txtSubtitle = (TextView) view.findViewById(R.id.textView2);
		
		txtTitle.setText(item.title);
		txtSubtitle.setText(item.subtitle);
		return view;
	}
	
	

}
