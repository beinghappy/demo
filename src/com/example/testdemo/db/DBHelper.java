package com.example.testdemo.db;
import android.content.Context;
import android.database.Cursor;

import com.example.testdemo.bean.ResultInfo;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

public class DBHelper {

	public String name = "good_db3";
	private int version = 1;

	DbUtils mDbUtils;

	public DBHelper(Context context) {
		// TODO Auto-generated constructor stub
		if (mDbUtils == null) {
			mDbUtils = DbUtils.create(context, name, version,
					new MyDbUpdateListener());
		}
	}

	/**
	 * @param mUserInfo
	 * @return
	 */
	public boolean addUserInfo(ResultInfo mUserInfo) {
		try {
			return mDbUtils.saveBindingId(mUserInfo);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
    public List<ResultInfo> getResultInfoHead() {
        try {
            List<ResultInfo> mList = mDbUtils.findAll(Selector.from(ResultInfo.class).orderBy("time", true));
//			List<ResultInfo> mList = mDbUtils.findAll(ResultInfo.class);
            return mList;
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
	/**
	 * @return
	 */
	public List<ResultInfo> getResultInfos(){
		try {
			List<ResultInfo> mList = mDbUtils.findAll(Selector.from(ResultInfo.class).orderBy("time",true));
//			List<ResultInfo> mList = mDbUtils.findAll(ResultInfo.class);
			return mList;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

    public Cursor getResultInfoCursor() {
        try {
            String sql = "select time as _id,time,result from user_table ORDER BY time";
            Cursor cursor = mDbUtils.execQuery(sql);
            return cursor;
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Cursor getResultInfoCursor(String text) {
        try {
            String sql = "select time as _id,time,result from user_table WHERE result LIKE '%" + text + "%' ORDER BY time ";
            Cursor cursor = mDbUtils.execQuery(sql);
            return cursor;
        } catch (DbException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
	public boolean updateResult(ResultInfo mInfo){
		try {
			WhereBuilder mwhere = WhereBuilder.b("time", "=",
					mInfo.getTime());
			mDbUtils.update(mInfo, mwhere, "result");
			return true;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void deleteUserInfoByName(String name) {
		try {
			WhereBuilder mwhere = WhereBuilder.b("packageName", "=",
					name);
			mDbUtils.delete(ResultInfo.class, mwhere);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	class MyDbUpdateListener implements DbUpgradeListener {

		@Override
		public void onUpgrade(DbUtils arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}

	}

}
