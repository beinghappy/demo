package com.example.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testdemo.bean.ResultInfo;
import com.example.testdemo.db.DBHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

	EditText srcTextView1, srcTextView2, srcTextView3;
	EditText srcTextViewfilter1;
	EditText srcTextViewfilter2;
	EditText srcTextViewfilter3;
	EditText input1;
	EditText input2;
	EditText input3;
	TextView srcTextViewresult1, srcTextViewresult2, srcTextViewresult3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		srcTextView1 = (EditText) findViewById(R.id.textView1);
		srcTextView2 = (EditText) findViewById(R.id.textView2);
		srcTextView3 = (EditText) findViewById(R.id.textView3);

		input1 = (EditText) findViewById(R.id.result_input1);
		input2 = (EditText) findViewById(R.id.result_input2);
		input3 = (EditText) findViewById(R.id.result_input3);

		srcTextViewfilter1 = (EditText) findViewById(R.id.textView_fiter1);
		srcTextViewresult1 = (TextView) findViewById(R.id.textView_result1);

		srcTextViewfilter2 = (EditText) findViewById(R.id.textView_fiter2);
		srcTextViewresult2 = (TextView) findViewById(R.id.textView_result2);

		srcTextViewfilter3 = (EditText) findViewById(R.id.textView_fiter3);
		srcTextViewresult3 = (TextView) findViewById(R.id.textView_result3);

		srcTextViewresult1.setTextColor(Color.BLUE);
		srcTextViewresult2.setTextColor(Color.BLUE);
		srcTextViewresult3.setTextColor(Color.BLUE);
		initView();

	}

	@Override
	public void onBackPressed() {
		if (builder != null && builder.isCustomKeyboardVisible()) {
			builder.hideCustomKeyboard();
		} else {
			this.finish();
		}
	}

	KeyboardBuilder builder;

	private void initList(){
		thirdList.add(src1[2] + "");
		if (!thirdList.contains(src2[2])) {
			thirdList.add(src2[2] + "");
		}
		if (!thirdList.contains(src3[2])) {
			thirdList.add(src3[2] + "");
		}

		sixList.add(src1[5] + "");
		if (!sixList.contains(src2[5])) {
			sixList.add(src2[5] + "");
		}
		if (!sixList.contains(src3[5])) {

			sixList.add(src3[5] + "");
		}

		eightList.add(src1[7] + "");
		if (!eightList.contains(src2[7])) {
			eightList.add(src2[7] + "");
		}
		if (!eightList.contains(src3[7])) {
			eightList.add(src3[7] + "");
		}
	}

	private void reflashList(){
		char[] src1 = srcTextView1.getText().toString().toCharArray();
		char[] src2 = srcTextView2.getText().toString().toCharArray();
		char[] src3 = srcTextView3.getText().toString().toCharArray();
		thirdList.clear();
		sixList.clear();
		eightList.clear();

		thirdList.add(src1[2] + "");
		if (!thirdList.contains(src2[2])) {
			thirdList.add(src2[2] + "");
		}
		if (!thirdList.contains(src3[2])) {
			thirdList.add(src3[2] + "");
		}

		sixList.add(src1[5] + "");
		if (!sixList.contains(src2[5])) {
			sixList.add(src2[5] + "");
		}
		if (!sixList.contains(src3[5])) {

			sixList.add(src3[5] + "");
		}

		eightList.add(src1[7] + "");
		if (!eightList.contains(src2[7])) {
			eightList.add(src2[7] + "");
		}
		if (!eightList.contains(src3[7])) {
			eightList.add(src3[7] + "");
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		src1 = randomNumber(16);
		src2 = randomNumber(16);
		src3 = randomNumber(16);

		srcTextView1.setText(new String(src1));
		setHightColor(srcTextView1, 3, 4);
		setHightColor(srcTextView1, 7, 8);
		setHightColor(srcTextView1, 9, 10);
		srcTextView2.setText(new String(src2));
		setHightColor(srcTextView2, 3, 4);
		setHightColor(srcTextView2, 7, 8);
		setHightColor(srcTextView2, 9, 10);
		srcTextView3.setText(new String(src3));
		setHightColor(srcTextView3, 3, 4);
		setHightColor(srcTextView3, 7, 8);
		setHightColor(srcTextView3, 9, 10);

		initList();

		Log.e("tag--------", "third" + thirdList.toString());
		Log.e("tag------", "sixList" + sixList.toString());
		Log.e("tag--------", "eightList" + eightList.toString());

		srcTextView1.addTextChangedListener(new TextWatcher() {

			int tmplen = 0;
			CharSequence tmpCharSequence = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

				int len = s.length();
				doHightColor(len, tmplen, srcTextView1);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				tmplen = s.length();
				tmpCharSequence = s;
				Log.e("tag", "before == " + s);
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		srcTextView2.addTextChangedListener(new TextWatcher() {

			int tmplen = 0;
			CharSequence tmpCharSequence = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

				int len = s.length();
				doHightColor(len, tmplen, srcTextView2);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				tmplen = s.length();
				tmpCharSequence = s;
				Log.e("tag", "before == " + s);
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

		srcTextView3.addTextChangedListener(new TextWatcher() {

			int tmplen = 0;
			CharSequence tmpCharSequence = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

				int len = s.length();
				doHightColor(len, tmplen, srcTextView3);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				tmplen = s.length();
				tmpCharSequence = s;
				Log.e("tag", "before == " + s);
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});

		srcTextViewfilter1.addTextChangedListener(new TextWatcher() {

			String tmp = "";
			boolean check = true;
			boolean larger = false;
			int beforeLength = 0;
			int afterLenght = 0;
			boolean toDelete = false;
			// 关标
			int startIndex = 0;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.i("tag", "输入文字==" + s);
				Log.i("tag", "输入  start" + start);
				int len = s.toString().length();
				// add it on 20170106
				if (!toDelete && len == 1) {
					String tmp = srcTextViewfilter1.getText().toString();
					int choose = Integer.parseInt(tmp);
					String head = getRandomIdNew(choose);
					String text = head + head.substring(0, 6);
					// srcTextViewfilter1.setText(text);
					// srcTextViewfilter2.setText(text);
					// srcTextViewfilter3.setText(text);

					String check1 = srcTextView1.getText().toString()
							.substring(0, 1);
					String check2 = srcTextView2.getText().toString()
							.substring(0, 1);
					String check3 = srcTextView3.getText().toString()
							.substring(0, 1);

					List<String> checkList = new ArrayList<String>();
					checkList.add(check1);
					checkList.add(check2);
					checkList.add(check3);

					if (!tmp.isEmpty()) {
						if (checkList.contains(tmp)) {
							srcTextViewfilter1.setText(text);
							checkList.remove(tmp);
							for (int i = 0; i < checkList.size(); i++) {
								int index = head.indexOf(checkList.get(i));
								String head2 = head.substring(index, 10)
										+ head.substring(0, index);
								String text2 = head2 + head2.substring(0, 6);
								if (i == 0) {
									srcTextViewfilter2.setText(text2);
								} else {
									srcTextViewfilter3.setText(text2);
								}
							}

							doResultDirect();

						} else {
							srcTextViewfilter1.setText(text);
							srcTextViewfilter2.setText(text);
							srcTextViewfilter3.setText(text);
						}
					}

				}

				if (!toDelete && len == 10) {
					String tmp = srcTextViewfilter1.getText().toString();
					String text = s.toString() + tmp.substring(0, 6);
					srcTextViewfilter1.setText(text);
					// srcTextViewfilter2.setText(text);
					// srcTextViewfilter3.setText(text);
				}

				if (srcTextViewfilter1.length() >= 10) {
					input1.setText(srcTextViewfilter1.getText().toString()
							.substring(9, 10));
				}

				if (srcTextViewfilter2.length() >= 10) {
					input2.setText(srcTextViewfilter2.getText().toString()
							.substring(9, 10));
				}

				if (srcTextViewfilter3.length() >= 10) {
					input3.setText(srcTextViewfilter3.getText().toString()
							.substring(9, 10));
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				if (s != null) {
					tmp = s.toString();
					Log.e("tag", "before tmp == " + tmp);
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String newInput = "";
				Log.e("tag", "after =s.toString()= " + s.toString());
				boolean enlarger = s.length() > tmp.length() ? true : false;
				Log.e("tag", "after =s.toString()= " + s.toString());
				if (enlarger) {
					int index = srcTextViewfilter1.getSelectionStart();
					Log.e("tag", "after =index= " + index);
					if (index > 0 && index <= 10) {
						newInput = s.toString().substring(index - 1, index);
						Log.e("tag", "after =newInput= " + newInput);

						String comStr;
						if (srcTextViewfilter1.getText().length() > 10) {
							comStr = tmp.substring(0, 10);
							Log.e("tag", "after =comStr= " + comStr);
						} else {
							comStr = tmp;
						}

						if (!newInput.equals("+") && comStr.contains(newInput)) {
							s.delete(index - 1, index);
							Toast.makeText(getApplicationContext(),
									"不能----" + newInput, Toast.LENGTH_SHORT).show();
						}
					}
				}

			}

		});
		srcTextViewfilter2.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					int len = s.length();
					// add it on 20170106
					// if(len == 1){
					// String tmp = srcTextViewfilter2.getText().toString();
					// int choose = Integer.parseInt(tmp);
					// String head = getRandomId(choose);
					// String text = head + head.substring(0, 6);
					// srcTextViewfilter2.setText(text);
					// }

					if (len == 10) {
						String tmp = srcTextViewfilter2.getText().toString();
						String text = s.toString() + tmp.substring(0, 6);
						srcTextViewfilter2.setText(text);

						// setHightColor(srcTextViewfilter1, 3, 4);
						// setHightColor(srcTextViewfilter1, 7, 8);
						// setHightColor(srcTextViewfilter1, 9, 10);

					}
				}
				Log.i("tag", "输入文字" + s + "中的状态，" + count + ",start==" + start
						+ ",before == " + before);
				if (srcTextViewfilter2.length() >= 10) {
					input2.setText(srcTextViewfilter2.getText().toString()
							.substring(9, 10));
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});
		srcTextViewfilter3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.i("tag", "输入文字" + s + "中的状态，" + count + ",start==" + start
						+ ",before == " + before);
				if (s.length() > 0) {
					// add it on 20170106
					// if(srcTextViewfilter3.getText().length() == 1){
					// String tmp = srcTextViewfilter3.getText().toString();
					// int choose = Integer.parseInt(tmp);
					// String head = getRandomId(choose);
					// String text = head + head.substring(0, 6);
					// srcTextViewfilter3.setText(text);
					// }

					if (srcTextViewfilter3.getText().length() == 10) {
						String tmp = srcTextViewfilter3.getText().toString();
						String text = s.toString() + tmp.substring(0, 6);
						srcTextViewfilter3.setText(text);
						// setHightColor(srcTextViewfilter3, 3, 4);
						// setHightColor(srcTextViewfilter3, 7, 8);
						// setHightColor(srcTextViewfilter3, 9, 10);

					}
				}

				if (srcTextViewfilter3.length() >= 10) {
					input3.setText(srcTextViewfilter3.getText().toString()
							.substring(9, 10));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	private String deal(String head) {
		List<String> list = new ArrayList<String>();
		list.add(src1[2] + "");
		list.add(src2[2] + "");
		list.add(src3[2] + "");
		String third = head.substring(2, 3);

		return head;
	}

	private boolean derectResult(String fliter) {
		String check1 = srcTextView1.getText().toString().substring(0, 1);
		String check2 = srcTextView2.getText().toString().substring(0, 1);
		String check3 = srcTextView3.getText().toString().substring(0, 1);

		if (!fliter.isEmpty()) {
			if (fliter.equals(check1) || fliter.equals(check2)
					|| fliter.equals(check3)) {
				return true;
			}
		}

		return false;

	}

	String[] data = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	/**
	 * @param choose
	 * @return
	 */
	public String getRandomId(int choose) {
		List<String> list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			if (i != choose) {
				list.add("" + i);
			}
		}

		Collections.shuffle(list);
		list.add(0, choose + "");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}

		return sb.toString();

	}

	public String getRandomIdNew(int choose) {
		reflashList();


		List<String> list = new ArrayList();

		// 选出第三个，第八个
		List<String> list3 = thirdList;
		if (list3.contains(choose)) {
			list3.remove(choose);
		}
		String third = "" ;
		if(list3.size() > 0 ){
			Collections.shuffle(list3);
			third = list3.get(0);
		}

		
		List<String> list6 = sixList;
		if (list6.contains(choose)) {
			list6.remove(choose);
		}
		if (!TextUtils.isEmpty(third) && list6.contains(third)) {
			list6.remove(third);
		}
		String six = "" ;
		if(list6.size() > 0 ){
			Collections.shuffle(list6);
			six = list6.get(0);
		}
		
		List<String> list8 = eightList;
		if (list8.contains(choose)) {
			list8.remove(choose);
		}
		if (!TextUtils.isEmpty(third) && list8.contains(third)) {
			list8.remove(third);
		}
		if (!TextUtils.isEmpty(six) && list8.contains(six)) {
			list8.remove(six);
		}
		
		String eight = "" ;
		if(list8.size() > 0 ){
			Collections.shuffle(list8);
			eight = list8.get(0);
		}
		for (int i = 0; i < 10; i++) {
			if (i == choose || (!TextUtils.isEmpty(third) && i == Integer.parseInt(third))
					|| (!TextUtils.isEmpty(six)  && i == Integer.parseInt(six))
					|| (!TextUtils.isEmpty(eight)  && i == Integer.parseInt(eight))
					) {

			} else {
				list.add("" + i);
			}

		}

		Log.e("tag---1---", list.toString());
		Collections.shuffle(list);
		list.add(0, choose + "");
		if(!TextUtils.isEmpty(third)){
			list.add(2, third);
		}
		
		if(!TextUtils.isEmpty(six)){
			list.add(5, six);
		}
		
		if(!TextUtils.isEmpty(eight)){
			list.add(7, eight);
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		return sb.toString();

	}

	private void changeThird(List<String> data, List<String> third) {
		String a = data.get(2);
		if (!third.contains(a)) {
			// 该head的第三个不是third里面的任意一个，需要再排列
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i).equals(a)) {
					// String change =
				}

			}
		}

	}

	public void autoFill() {

	}

	public void doHightColor(int len, int beforeLen, EditText textView) {
		if (len > beforeLen) {
			// add
			if (len == 10) {
				setHightColor(textView, 3, 4);
				setHightColor(textView, 7, 8);
				setHightColor(textView, 9, 10);
			} else if (len == 8) {
				setHightColor(textView, 3, 4);
				setHightColor(textView, 7, 8);
			} else if (len == 4) {
				setHightColor(textView, 3, 4);
			}
			textView.setSelection(textView.length());

		}
	}

	public void setHightColor(EditText textView, int startIndex, int endIndex) {
		SpannableStringBuilder styled = new SpannableStringBuilder(
				textView.getText());
		// i 未起始字符索引，j 为结束字符索引
		styled.setSpan(new ForegroundColorSpan(Color.RED), startIndex,
				endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView.setText(styled);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void doClear() {
		srcTextViewfilter3.setText("");
		srcTextViewfilter2.setText("");
		srcTextViewfilter1.setText("");
	}

	public void doResult() {
		src1 = srcTextView1.getText().toString().toCharArray();
		src2 = srcTextView2.getText().toString().toCharArray();
		src3 = srcTextView3.getText().toString().toCharArray();
		char[] filter1 = getResulString(srcTextViewfilter1.getText().toString()
				.toCharArray());
		char[] filter2 = getResulString(srcTextViewfilter2.getText().toString()
				.toCharArray());
		char[] filter3 = getResulString(srcTextViewfilter3.getText().toString()
				.toCharArray());
		String[] tmp = dealresult(filter1, filter2, filter3);
		srcTextViewresult1.setText(tmp[0]);
		srcTextViewresult2.setText(tmp[1]);
		srcTextViewresult3.setText(tmp[2]);

		if (!srcTextViewresult1.getText().toString().isEmpty()
				&& !srcTextViewresult2.getText().toString().isEmpty()
				&& !srcTextViewresult3.getText().toString().isEmpty()
		// && !input1.getText().toString().isEmpty()
		// && !input2.getText().toString().isEmpty()
		// && !input3.getText().toString().isEmpty()
		) {
			// String result =
			// srcTextViewresult1.getText().toString()+","+input1.getText().toString()+";\n"+
			// srcTextViewresult2.getText().toString()+","+input2.getText().toString()+";\n"+
			// srcTextViewresult3.getText().toString()+","+input3.getText().toString();
			String inputStr1 = input1.getText().toString().isEmpty() ? "" : ","
					+ input1.getText().toString();
			String inputStr2 = input2.getText().toString().isEmpty() ? "" : ","
					+ input2.getText().toString();
			String inputStr3 = input3.getText().toString().isEmpty() ? "" : ","
					+ input3.getText().toString();

			String result = srcTextViewresult1.getText().toString() + inputStr1
					+ ";\n" + srcTextViewresult2.getText().toString()
					+ inputStr2 + ";\n"
					+ srcTextViewresult3.getText().toString() + inputStr3;

			ResultInfo mResultInfo = new ResultInfo();
			mResultInfo.setResult(result);
			mResultInfo.setTime(System.currentTimeMillis());
			new DBHelper(this).addUserInfo(mResultInfo);

			srcTextViewfilter3.setText("");
			srcTextViewfilter2.setText("");
			srcTextViewfilter1.setText("");

		} else {
			Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void doResultDirect() {
		src1 = srcTextView1.getText().toString().toCharArray();
		src2 = srcTextView2.getText().toString().toCharArray();
		src3 = srcTextView3.getText().toString().toCharArray();
		char[] filter1 = getResulString(srcTextViewfilter1.getText().toString()
				.toCharArray());
		char[] filter2 = getResulString(srcTextViewfilter2.getText().toString()
				.toCharArray());
		char[] filter3 = getResulString(srcTextViewfilter3.getText().toString()
				.toCharArray());
		String[] tmp = dealresult(filter1, filter2, filter3);
		srcTextViewresult1.setText(tmp[0]);
		srcTextViewresult2.setText(tmp[1]);
		srcTextViewresult3.setText(tmp[2]);

		if (!srcTextViewresult1.getText().toString().isEmpty()
				&& !srcTextViewresult2.getText().toString().isEmpty()
				&& !srcTextViewresult3.getText().toString().isEmpty()
		// && !input1.getText().toString().isEmpty()
		// && !input2.getText().toString().isEmpty()
		// && !input3.getText().toString().isEmpty()
		) {
			// String result =
			// srcTextViewresult1.getText().toString()+","+input1.getText().toString()+";\n"+
			// srcTextViewresult2.getText().toString()+","+input2.getText().toString()+";\n"+
			// srcTextViewresult3.getText().toString()+","+input3.getText().toString();
			String inputStr1 = input1.getText().toString().isEmpty() ? "" : ","
					+ input1.getText().toString();
			String inputStr2 = input2.getText().toString().isEmpty() ? "" : ","
					+ input2.getText().toString();
			String inputStr3 = input3.getText().toString().isEmpty() ? "" : ","
					+ input3.getText().toString();

			String result = srcTextViewresult1.getText().toString() + inputStr1
					+ ";\n" + srcTextViewresult2.getText().toString()
					+ inputStr2 + ";\n"
					+ srcTextViewresult3.getText().toString() + inputStr3;

			ResultInfo mResultInfo = new ResultInfo();
			mResultInfo.setResult(result);
			mResultInfo.setTime(System.currentTimeMillis());
			new DBHelper(this).addUserInfo(mResultInfo);

		} else {
			Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_LONG)
					.show();
		}

	}

	char[] src1, src2, src3;
	char[] filter1, filter2, filter3;
	List<String> thirdList = new ArrayList<String>();
	List<String> sixList = new ArrayList<String>();
	List<String> eightList = new ArrayList<String>();

	public String getResult(char[] filter) {
		char[] result = new char[filter.length];
		for (int i = 0; i < filter.length; i++) {
			if (filter[i] == src1[i] || filter[i] == src2[i]
					|| filter[i] == src3[i]) {
				result[i] = '0';
			} else {
				result[i] = '+';
			}
		}

		return new String(result);
	}

	public char[] getResulString(char[] filter) {
		char[] result = new char[filter.length];
		for (int i = 0; i < filter.length; i++) {
			if (filter[i] == src1[i] || filter[i] == src2[i]
					|| filter[i] == src3[i]) {
				result[i] = '0';
			} else {
				// if(filter[i] != src1[i] && filter[i] != src2[i] && filter[i]
				// == src3[i]){
				// result[i]='8';
				// }else if(filter[i] == src1[i] && filter[i] != src2[i] &&
				// filter[i] != src3[i]){
				// result[i]='8';
				// }else if(filter[i] != src1[i] && filter[i] == src2[i] &&
				// filter[i] != src3[i]){
				// result[i]='8';
				// }else{
				result[i] = '+';
				// }
			}
		}

		return result;

	}

	public String[] dealresult(char[] filter1, char[] filter2, char[] filter3) {
		Log.e("tag", "filter1==" + filter1.toString());
		Log.e("tag", "filter2==" + filter2.toString());
		Log.e("tag", "filter3==" + filter3.toString());
		if (filter1.length == filter2.length
				&& filter2.length == filter3.length) {
			int len = filter1.length;
			for (int i = 0; i < len; i++) {
				int b1 = -1;
				int b2 = -1;
				int b3 = -1;
				if (filter1[i] == '+') {
					b1 = 1;
				}
				if (filter2[i] == '+') {
					b2 = 1;
				}
				if (filter3[i] == '+') {
					b3 = 1;
				}

				if (b1 * b2 * b3 == -1) {
					// 1一个,或者3 -1
					if (b1 == -1 && b2 == -1 && b3 == -1) {

					} else {
						// 1个
						if (b1 == -1) {
							filter1[i] = '8';
						}
						if (b2 == -1) {
							filter2[i] = '8';
						}
						if (b3 == -1) {
							filter3[i] = '8';
						}
					}
				} else {
					// 2个-1,或0 个
					if (b1 == 1 && b2 == 1 && b3 == 1) {
						// 0个
					}
				}

			}
		}
		String[] tmp = new String[3];
		tmp[0] = new String(filter1);
		tmp[1] = new String(filter2);
		tmp[2] = new String(filter3);

		return tmp;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			// doResult();
			// clear
			doClear();
			break;
		case R.id.action_result:
			startActivity(new Intent(MainActivity.this, ResultActivity.class));
			break;

		default:
			break;
		}
		return true;
	}

	public int[] generateNumber() {
		return null;
	}

	/**
	 * 
	 * @param length
	 *            锟斤拷锟斤拷锟轿伙拷锟�
	 * @return
	 */
	public char[] randomNumber(int length) {
		char[] numbersAndLetters = null;
		java.util.Random randGen = null;
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			if (randGen == null) {
				randGen = new java.util.Random();
				numbersAndLetters = ("0123456789").toCharArray();
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return randBuffer;
	}

}
