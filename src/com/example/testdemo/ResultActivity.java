package com.example.testdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdemo.bean.ResultInfo;
import com.example.testdemo.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 123
 *
 */
@SuppressLint("NewApi")
public class ResultActivity extends Activity implements
        SearchView.OnQueryTextListener {

    ListView mListView;
    DBHelper mDbHelper;
    MyAdapter mAdapter;
    List<ResultInfo> resultInfoList = new ArrayList<ResultInfo>();
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
        initListView(resultInfoList);
        Toast.makeText(getApplicationContext(),"数据量:"+resultInfoList.size(),Toast.LENGTH_LONG).show();


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

	public void initListView(final List<ResultInfo> list){
		mAdapter = new MyAdapter(this, list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
										   int arg2, long arg3) {
				// TODO Auto-generated method stub
				showEditDialog(list.get(arg2), arg2);
				return false;
			}
		});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ResultActivity.this,ResultDetailActivity.class);
				intent.putExtra("data", list.get(position));
				startActivity(intent);
			}
		});
	}

	EditText mEditText;
	public void showEditDialog(final ResultInfo info, final int index) {
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
		mBuilder.setTitle("编辑结果");
		mBuilder.setIcon(android.R.drawable.ic_dialog_info);
		mEditText = new EditText(this);
		mEditText.setText(info.getResult());
		mBuilder.setView(mEditText);
		mBuilder.setPositiveButton("确定", new OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int id) {
                // TODO Auto-generated method stub
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "修改不能为空",
                            Toast.LENGTH_LONG).show();
                } else {
                    info.setResult(mEditText.getText().toString());
                    info.setTime(info.getTime());
                    mAdapter.notifyDataSetInvalidated();
                    updateItem(info);

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

    private void updateItem(final ResultInfo mInfo){
        ThreadPoolUtil.execute(new Runnable() {
            @Override
            public void run() {
                boolean b = mDbHelper.updateResult(mInfo);
                if (b) {
                	//edit之后保持搜索结果
//                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "edit",
                        Toast.LENGTH_LONG).show();
                initListView(resultInfoList);
            }else if(msg.what == 1){
                Toast.makeText(getApplicationContext(), "edit fail",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    /**
     * 用户输入字符时激发该方法
     */

	String searchText = "";
	@Override
	public boolean onQueryTextChange(String newText) {
		List<ResultInfo> obj = searchItem(newText);
		initListView(obj);
		return false;
	}


	public List<ResultInfo> searchItem(String name) {
		searchText = name;
		if(TextUtils.isEmpty(name)){
			return resultInfoList;
		}
		List<ResultInfo> mSearchList = new ArrayList<ResultInfo>();
		String[] hello = new String[2];
		if(name.trim().indexOf(" ") > 0){
			hello = name.split(" ");

			for (int i = 0; i < resultInfoList.size(); i++) {
				int index = resultInfoList.get(i).getResult().indexOf(hello[0]);
				int index2 = resultInfoList.get(i).getResult().indexOf(hello[1]);
				// 存在匹配的数据
				if (index != -1 && index2!= -1) {
					mSearchList.add(resultInfoList.get(i));
				}
			}

		}else{
			for (int i = 0; i < resultInfoList.size(); i++) {
				int index = resultInfoList.get(i).getResult().indexOf(name);
				// 存在匹配的数据
				if (index != -1) {
					mSearchList.add(resultInfoList.get(i));
				}
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
		if (srv1.isShown()){
			ResultActivity.this.finish();
		} else{
			super.onBackPressed();
		}
	}

	class MyAdapter extends BaseAdapter{
		private Context context;
		private List<ResultInfo> list;
		public MyAdapter(Context context , List<ResultInfo> list){
			this.context = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list!=null ? list.size() : 0;
		}

		@Override
		public ResultInfo getItem(int arg0) {
			// TODO Auto-generated method stub
			return list!=null ? list.get(arg0):null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

        @Override
        public View getView(int arg0, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            LayoutInflater inflater = LayoutInflater.from(context);
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(
                        R.layout.textview_item, null);
                holder.tv = (TextView) convertView
                        .findViewById(R.id.textView1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(list.get(arg0).getResult());
            return convertView;
        }

    }


    public class ViewHolder {
        private TextView tv;
    }


}
