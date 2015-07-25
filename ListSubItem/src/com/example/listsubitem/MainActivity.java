package com.example.listsubitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{

	Button btn_add, btn_delete;
	ListView listView1;
	List<ListViewItem> listItems;
	CustomListViewAdapter lvAdapter;
	Animation an;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        System.out.println("onCreate");
        
        an = new AlphaAnimation(1, 0); // change alpha from fully vis to invis
        an.setDuration(300);
        
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);
		
		listView1 = (ListView) findViewById(R.id.listView1);
		listItems = new ArrayList<ListViewItem>();
        
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String listItemsString = "";
        listItemsString = prefs.getString("listItems", listItemsString);
        
        //JSONArray jArray;
        //JSONObject jObj;
        //JsonParser parser = new JsonParser();
        //JsonElement element = parser.parse(listItemsString);
        //JsonArray jArray = element.getAsJsonArray();
        
        //if (jArray.size() >0){
        //	JsonObject jObj = jArray.get(0).getAsJsonObject();
        //	System.out.println(jObj.get("title"));
        //}
        /*
        if (jArray != null)
        	System.out.println("jArray size: " + jArray.size()/2);
        	System.out.println(jArray.get(0).toString() + " " + jArray.get(1));
        */
        try {
        	JSONArray jArray = new JSONArray(listItemsString);
        	//JSONObject jParent = jArray.getJSONObject(0);
			//JSONObject jParent = new JSONObject(listItemsString);
			//JSONArray jArray = jObjArr.getJSONObject("listItems");
        	//JSONArray jArray = jParent.getJSONArray("listItems");
			//JSONObject jListItems = jParent.getJSONObject("listItems");
			//String[] names = JSONObject.getNames(jParent);
			//JSONArray jArray = jParent.getJSONArray("listItems");
			JSONObject jObj;
			
			System.out.println("jArray size: " + jArray.length());		
			
			for (int i = 0; i < jArray.length(); i++){
				//System.out.println(jArray.getString(0));
				
				jObj = jArray.getJSONObject(i);
				
				ListViewItem lvi = new ListViewItem();
				lvi.title = jObj.getString("title");
				lvi.subtitle = jObj.getString("subTitle");
				
				listItems.add(lvi);
	       }
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
        /**/
        
        
       System.out.println("prefs: ");
       System.out.println(listItemsString);
        
		
		
		/*
		listItems.add(new ListViewItem()
		{{
			title = "title" + count;
			subtitle = "subtitle" + count;
		}});
		*/
		
		lvAdapter = new CustomListViewAdapter(this, listItems);
		listView1.setAdapter(lvAdapter);
		
    }

    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//initButtons();
    	
		View view = inflater.inflate(R.layout.activity_main, container, false);
		btn_add = (Button) view.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);
		listView1 = (ListView) view.findViewById(R.id.listView1);
		listItems = new ArrayList<ListViewItem>();
		lvAdapter = new CustomListViewAdapter(this, listItems);
		listView1.setAdapter(lvAdapter);
		
		return view;
		
	}
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    List<Integer> titleList = new ArrayList<Integer>();
    List<Integer> subtitleList = new ArrayList<Integer>();
    int title = 0, subtitle = 0;
    static List<Map<String, String>> listWithSub = new ArrayList<Map<String,String>>();
    */
    
    int count = 0;
	@Override
	public void onClick(View v) {
		if(v == btn_add){
			btn_add.startAnimation(an);
			count++;
			listItems.add(new ListViewItem()
			{{
				title = "title" + count;
				subtitle = "subtitle" + count;
			}});
			listView1.setAdapter(lvAdapter);
			Toast.makeText(this, "added!", Toast.LENGTH_SHORT).show();
		}
		if(v==btn_delete){
			btn_delete.startAnimation(an);
			listItems.clear();
			listView1.setAdapter(lvAdapter);
		}
	}
		
	
	
	public void onActivityCreated(Bundle savedInstanceState){
		//super.onActivityCreated(savedInstanceState);
		/*
		SimpleAdapter arrayAdapter = new SimpleAdapter(this, 
													listWithSub, 
													android.R.layout.simple_list_item_2, 
													new String[] {"title", "subtitle"}, 
													new int[] {android.R.id.text1, android.R.id.text2
													
		});
		listView1.setAdapter(arrayAdapter);
		listView1.setOnItemClickListener(this);
		*/
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
		
	}
	
	public void onStop(){
		super.onStop();
		
		System.out.println("onStop");
		
		JSONArray jArray;
		
		jArray = new JSONArray();
		
		JSONObject jObj = new JSONObject();
		
		for (int i = 0; i < listItems.size(); i++)
		{
			try {
				jObj.put("title", listItems.get(i).title);
				jObj.put("subTitle", listItems.get(i).subtitle);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			jArray.put(jObj);
			jObj = new JSONObject();
		}
		
		//Gson gson = new Gson();
		//String json = gson.toJson(listItems);
		
		String jsonString = jArray.toString();
		
		SharedPreferences pref;
		pref = getSharedPreferences("data", MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString("listItems", jsonString);
		editor.commit();
		
		//System.out.println("jArray:");
		//System.out.println(jsonString);
		/*
		for (int i = 0; i < jArray.length(); i++){
			try {
				System.out.println(jArray.getJSONObject(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		listView1.setAdapter(lvAdapter);			
	}
	
	class ListViewItem
	{
		public String title, subtitle;
	}
}
