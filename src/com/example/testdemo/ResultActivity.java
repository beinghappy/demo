package com.example.testdemo;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.testdemo.bean.ResultInfo;
import com.example.testdemo.db.DBHelper;

/**
 * 
 * @author 123
 * 
 */
@SuppressLint("NewApi") public class ResultActivity extends Activity implements
		SearchView.OnQueryTextListener {

	ListView mListView;
	DBHelper mDbHelper;
	ArrayAdapter<String> mAdapter;
	List<ResultInfo> resultInfoList = null;
	List<String> dataList = null;
	String account = null;
	String ResultInfo = null;

	private SearchView srv1;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.address);
		mListView = (ListView) findViewById(R.id.listView_address);

		srv1 = (SearchView) findViewById(R.id.searchview);
		srv1.setOnQueryTextListener(this);
		
		srv1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
		    @Override
		    public void onFocusChange(View view, boolean queryTextFocused) {
		        if(!queryTextFocused) {
		            srv1.setQuery("", false);
		        }
		    }
		});

		mDbHelper = new DBHelper(this);
		resultInfoList = mDbHelper.getResultInfos();
		dataList = new ArrayList<String>();
		if (resultInfoList != null && resultInfoList.size() > 0) {
			for (ResultInfo mResultInfo : resultInfoList) {
				dataList.add(mResultInfo.getResult());
			}
		}
		
		initListView(dataList);
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				showEditDialog(dataList.get(arg2), arg2);
				return false;
			}
		});

	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public void initListView(List<String> list){
		mAdapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.textview_item, list);
		mListView.setAdapter(mAdapter);
	}

	EditText mEditText;

	public void showEditDialog(String text, final int index) {
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
		mBuilder.setTitle("编辑结果");
		mBuilder.setIcon(android.R.drawable.ic_dialog_info);
		mEditText = new EditText(this);
		mEditText.setText(text);
		mBuilder.setView(mEditText);
		mBuilder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int id) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(mEditText.getText().toString())) {
					Toast.makeText(getApplicationContext(), "修改不能为空",
							Toast.LENGTH_LONG).show();
				} else {
					ResultInfo mInfo = new ResultInfo();
					mInfo.setResult(mEditText.getText().toString());
					mInfo.setTime(resultInfoList.get(index).getTime());
					boolean b = mDbHelper.updateResult(mInfo);
					if (b) {
						Toast.makeText(getApplicationContext(), "edit",
								Toast.LENGTH_LONG).show();
						resultInfoList = mDbHelper.getResultInfos();
						dataList = new ArrayList<String>();
						if (resultInfoList != null && resultInfoList.size() > 0) {
							for (ResultInfo mResultInfo : resultInfoList) {
								dataList.add(mResultInfo.getResult());
							}
						}
						mAdapter = new ArrayAdapter<String>(
								getApplicationContext(),
								R.layout.textview_item, dataList);
						mListView.setAdapter(mAdapter);
					} else {
						Toast.makeText(getApplicationContext(), "edit fail",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		mBuilder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.dismiss();
			}

		});
		mBuilder.show();
	}

	/**
	 * 用户输入字符时激发该方法
	 */

	@Override
	public boolean onQueryTextChange(String newText) {
		List<String> obj = searchItem(newText);
		initListView(obj);
		return false;
	}
	

	public List<String> searchItem(String name) {
		List<String> mSearchList = new ArrayList<String>();
		for (int i = 0; i < dataList.size(); i++) {
			int index = dataList.get(i).indexOf(name);
			// 存在匹配的数据
			if (index != -1) {
				mSearchList.add(dataList.get(i));
			}
		}
		return mSearchList;
	}

	/**
	 * 单击搜索按钮时激发该方法
	 */
	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
//		finish();
		if (srv1.isShown()){
//			srv1.onActionViewCollapsed();  
//			srv1.setQuery("",false);
			ResultActivity.this.finish();
        } else{
            super.onBackPressed();
        }
	}
	
//	if (searchView.isShown()){
//        searchView.onActionViewCollapsed();  //collapse your ActionView
//        searchView.setQuery("",false);       //clears your query without submit
//        isClosed = true;                     //needed to handle closed by back
//    } else{
//        super.onBackPressed();
//    }

}
