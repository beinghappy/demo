package com.example.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
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
    EditText srcEditTextfilter1;
    EditText srcEditTextfilter2;
    EditText srcEditTextfilter3;
    EditText input1;
    EditText input2;
    EditText input3;
    TextView srcTextViewresult1, srcTextViewresult2, srcTextViewresult3;
    //add on 2021-4-8  for one click ten records
    private final static int RECORD_NUM = 5000;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //random generate 16 number , first number is different
        srcTextView1 = (EditText) findViewById(R.id.textView1);
        srcTextView2 = (EditText) findViewById(R.id.textView2);
        srcTextView3 = (EditText) findViewById(R.id.textView3);

        //user can input , edit the number
        srcEditTextfilter1 = (EditText) findViewById(R.id.textView_fiter1);
        srcEditTextfilter2 = (EditText) findViewById(R.id.textView_fiter2);
        srcEditTextfilter3 = (EditText) findViewById(R.id.textView_fiter3);

        //0,8,+
        srcTextViewresult1 = (TextView) findViewById(R.id.textView_result1);
        srcTextViewresult2 = (TextView) findViewById(R.id.textView_result2);
        srcTextViewresult3 = (TextView) findViewById(R.id.textView_result3);
        srcTextViewresult1.setTextColor(Color.BLUE);
        srcTextViewresult2.setTextColor(Color.BLUE);
        srcTextViewresult3.setTextColor(Color.BLUE);


        //a bit number for the input
        input1 = (EditText) findViewById(R.id.result_input1);
        input2 = (EditText) findViewById(R.id.result_input2);
        input3 = (EditText) findViewById(R.id.result_input3);

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

    /**
     * get the 3,6,9 number form the three char[]
     */
    private void initList() {
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

    private void reflashList() {
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
        char[][] tmpdata = Tools.randomNumbers(16);

        src1 = tmpdata[0];
        src2 = tmpdata[1];
        src3 = tmpdata[2];

        srcTextView1.setText(new String(src1));
        srcTextView2.setText(new String(src2));
        srcTextView3.setText(new String(src3));

        setHightColor(srcTextView1, 3, 4);
        setHightColor(srcTextView1, 7, 8);
        setHightColor(srcTextView1, 9, 10);
        setHightColor(srcTextView2, 3, 4);
        setHightColor(srcTextView2, 7, 8);
        setHightColor(srcTextView2, 9, 10);
        setHightColor(srcTextView3, 3, 4);
        setHightColor(srcTextView3, 7, 8);
        setHightColor(srcTextView3, 9, 10);

        initList();

       /* srcTextView1.addTextChangedListener(new TextWatcher() {

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
                Log.e("tag", "srcTextView1 before == " + s);
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
                Log.e("tag", "srcTextView2 before == " + s);
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
                Log.e("tag", "srcTextView3 before == " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });*/

        srcEditTextfilter1.addTextChangedListener(new TextWatcher() {

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
                    String tmp = srcEditTextfilter1.getText().toString();
                    //fliter1输入的第一个数字
                    int choose = Integer.parseInt(tmp);
                    String head = getRandomId(choose);
                    String text = head + head.substring(0, 6);
                    Log.e(TAG, "生成的fliter1的text : " + text);
                    String check1 = srcTextView1.getText().toString()
                            .substring(0, 1);
                    String check2 = srcTextView2.getText().toString()
                            .substring(0, 1);
                    String check3 = srcTextView3.getText().toString()
                            .substring(0, 1);
                    Log.e(TAG, "check1 : " + check1);
                    Log.e(TAG, "check2 : " + check2);
                    Log.e(TAG, "check3 : " + check3);
                    List<String> checkList = new ArrayList<String>();
                    checkList.add(check1);
                    checkList.add(check2);
                    checkList.add(check3);

                    if (!tmp.isEmpty()) {
						if (checkList.contains(tmp)) {
                            srcEditTextfilter1.setText(text);
                            checkList.remove(tmp);
                            for (int i = 0; i < checkList.size(); i++) {
                                //从flag的首字在head里的序号，head 10位数
                                int index = head.indexOf(checkList.get(i));
                                String head2 = head.substring(index, 10)
                                        + head.substring(0, index);
                                //以序号中分，拿到前面来
                                String text2 = head2 + head2.substring(0, 6);
                                if (i == 0) {
                                    srcEditTextfilter2.setText(text2);
                                } else {
                                    srcEditTextfilter3.setText(text2);
                                }
                            }

                            doResultDirect();
                            ThreadPoolUtil.execute(new Runnable() {
                                @Override
                                public void run() {
                                    generateOtherNineRecord();
                                }
                            });

                        } else {
                            srcEditTextfilter1.setText(text);
                            srcEditTextfilter2.setText(text);
                            srcEditTextfilter3.setText(text);
                        }
                    }

                }

                if (!toDelete && len == 10) {
                    String tmp = srcEditTextfilter1.getText().toString();
                    String text = s.toString() + tmp.substring(0, 6);
                    srcEditTextfilter1.setText(text);
                    // srcEditTextfilter2.setText(text);
                    // srcEditTextfilter3.setText(text);
                }

                if (srcEditTextfilter1.length() >= 10) {
                    input1.setText(srcEditTextfilter1.getText().toString()
                            .substring(9, 10));
                }

                if (srcEditTextfilter2.length() >= 10) {
                    input2.setText(srcEditTextfilter2.getText().toString()
                            .substring(9, 10));
                }

                if (srcEditTextfilter3.length() >= 10) {
                    input3.setText(srcEditTextfilter3.getText().toString()
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
                    int index = srcEditTextfilter1.getSelectionStart();
                    Log.e("tag", "after =index= " + index);
                    if (index > 0 && index <= 10) {
                        newInput = s.toString().substring(index - 1, index);
                        Log.e("tag", "after =newInput= " + newInput);

                        String comStr;
                        if (srcEditTextfilter1.getText().length() > 10) {
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
        srcEditTextfilter2.addTextChangedListener(new TextWatcher() {

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
                    // String tmp = srcEditTextfilter2.getText().toString();
                    // int choose = Integer.parseInt(tmp);
                    // String head = getRandomId(choose);
                    // String text = head + head.substring(0, 6);
                    // srcEditTextfilter2.setText(text);
                    // }

                    if (len == 10) {
                        String tmp = srcEditTextfilter2.getText().toString();
                        String text = s.toString() + tmp.substring(0, 6);
                        srcEditTextfilter2.setText(text);

                        // setHightColor(srcEditTextfilter1, 3, 4);
                        // setHightColor(srcEditTextfilter1, 7, 8);
                        // setHightColor(srcEditTextfilter1, 9, 10);

                    }
                }
                Log.i("tag", "输入文字" + s + "中的状态，" + count + ",start==" + start
                        + ",before == " + before);
                if (srcEditTextfilter2.length() >= 10) {
                    input2.setText(srcEditTextfilter2.getText().toString()
                            .substring(9, 10));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });
        srcEditTextfilter3.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                Log.i("tag", "输入文字" + s + "中的状态，" + count + ",start==" + start
                        + ",before == " + before);
                if (s.length() > 0) {
                    // add it on 20170106
                    // if(srcEditTextfilter3.getText().length() == 1){
                    // String tmp = srcEditTextfilter3.getText().toString();
                    // int choose = Integer.parseInt(tmp);
                    // String head = getRandomId(choose);
                    // String text = head + head.substring(0, 6);
                    // srcEditTextfilter3.setText(text);
                    // }

                    if (srcEditTextfilter3.getText().length() == 10) {
                        String tmp = srcEditTextfilter3.getText().toString();
                        String text = s.toString() + tmp.substring(0, 6);
                        srcEditTextfilter3.setText(text);
                        // setHightColor(srcEditTextfilter3, 3, 4);
                        // setHightColor(srcEditTextfilter3, 7, 8);
                        // setHightColor(srcEditTextfilter3, 9, 10);

                    }
                }

                if (srcEditTextfilter3.length() >= 10) {
                    input3.setText(srcEditTextfilter3.getText().toString()
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



    public void generateOtherNineRecord() {
        List<ResultInfo> list = new ArrayList<>();
        char[] str_flag1 = src1;
        char[] str_flag2 = src2;
        char[] str_flag3 = src3;

        //第一区域首字
        String check1 = String.valueOf(str_flag1[0]);
        String check2 = String.valueOf(str_flag2[0]);
        String check3 = String.valueOf(str_flag3[0]);

        List<String> checkList = new ArrayList<String>();
        checkList.add(check1);
        checkList.add(check2);
        checkList.add(check3);


        for (int k = 0; k < RECORD_NUM; k++) {
            //radmon generate a tmp to get more numbers
            int tmpIndex = k % 3;
            String tmp = checkList.get(tmpIndex);
            String head = getRandomId(Integer.parseInt(tmp));
            String text = head + head.substring(0, 6);
            if (checkList.contains(tmp)) {
                //第二区域，过滤的，可输入的
                String strfilter1 = text;
                String strfilter2 = text;
                String strfilter3 = text;

                for (int i = 0; i < checkList.size(); i++) {
                    int index = head.indexOf(checkList.get(i));
                    if (i == 0) {
                        String head1 = head.substring(index, 10)
                                + head.substring(0, index);
                        strfilter1 = head1 + head1.substring(0, 6);;
                    }else if(i == 1){
                        String head2 = head.substring(index, 10)
                                + head.substring(0, index);
                        strfilter2 = head2 + head2.substring(0, 6);
                    }else{
                        String head3 = head.substring(index, 10)
                                + head.substring(0, index);
                        strfilter3 = head3 + head3.substring(0, 6);
                    }
                }

                char[] filter1 = getResulString(strfilter1.toCharArray());
                char[] filter2 = getResulString(strfilter2.toCharArray());
                char[] filter3 = getResulString(strfilter3.toCharArray());
                String[] filterTmp = dealresult(filter1, filter2, filter3);


                String input1 = "";
                String input2 = "";
                String input3 = "";

                if (strfilter1.length() >= 10) {
                    input1 = strfilter1.substring(9, 10);
                }

                if (strfilter2.length() >= 10) {
                    input2 = strfilter2
                            .substring(9, 10);
                }

                if (strfilter3.length() >= 10) {
                    input3 = strfilter3
                            .substring(9, 10);
                }

                String inputStr1 = input1.isEmpty() ? "" : "," + input1;
                String inputStr2 = input2.isEmpty() ? "" : "," + input2;
                String inputStr3 = input3.isEmpty() ? "" : "," + input3;

                String result = filterTmp[0] + inputStr1 + ";\n"
                        + filterTmp[1] + inputStr2 + ";\n"
                        + filterTmp[2] + inputStr3;

                ResultInfo mResultInfo = new ResultInfo();
                mResultInfo.setResult(result);
                mResultInfo.setFlag1(String.valueOf(str_flag1));
                mResultInfo.setFlag2(String.valueOf(str_flag2));
                mResultInfo.setFlag3(String.valueOf(str_flag3));
                mResultInfo.setFliter1(strfilter1);
                mResultInfo.setFliter2(strfilter2);
                mResultInfo.setFliter3(strfilter3);
                mResultInfo.setResult1(filterTmp[0]);
                mResultInfo.setResult2(filterTmp[1]);
                mResultInfo.setResult3(filterTmp[2]);
                mResultInfo.setResult_input1(input1);
                mResultInfo.setResult_input2(input2);
                mResultInfo.setResult_input3(input3);
                mResultInfo.setTime(System.currentTimeMillis());
                Log.e(TAG, "generateOtherNineRecord: " + mResultInfo.toString());
                list.add(mResultInfo);

            }
        }

        boolean result = DBHelper.getInstance(this).addUserInfoList(list);
        Log.e(TAG, "generateOtherNineRecord: " + result);

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

    String[] data = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

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
        srcEditTextfilter3.setText("");
        srcEditTextfilter2.setText("");
        srcEditTextfilter1.setText("");
    }


    public void doResultDirect() {
        src1 = srcTextView1.getText().toString().toCharArray();
        src2 = srcTextView2.getText().toString().toCharArray();
        src3 = srcTextView3.getText().toString().toCharArray();
        char[] filter1 = getResulString(srcEditTextfilter1.getText().toString()
                .toCharArray());
        char[] filter2 = getResulString(srcEditTextfilter2.getText().toString()
                .toCharArray());
        char[] filter3 = getResulString(srcEditTextfilter3.getText().toString()
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
            mResultInfo.setFlag1(String.valueOf(src1));
            mResultInfo.setFlag2(String.valueOf(src2));
            mResultInfo.setFlag3(String.valueOf(src3));
            mResultInfo.setFliter1(srcEditTextfilter1.getText().toString());
            mResultInfo.setFliter2(srcEditTextfilter2.getText().toString());
            mResultInfo.setFliter3(srcEditTextfilter3.getText().toString());
            mResultInfo.setResult1(tmp[0]);
            mResultInfo.setResult2(tmp[1]);
            mResultInfo.setResult3(tmp[2]);
            mResultInfo.setResult_input1(input1.getText().toString());
            mResultInfo.setResult_input2(input2.getText().toString());
            mResultInfo.setResult_input3(input3.getText().toString());
            mResultInfo.setTime(System.currentTimeMillis());
            DBHelper.getInstance(this).addUserInfo(mResultInfo);

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

    /**
     * fliter与flag对比，转化成0和+得到result
     * @param filter
     * @return
     */
    public char[] getResulString(char[] filter) {
        char[] result = new char[filter.length];
        for (int i = 0; i < filter.length; i++) {
            if (filter[i] == src1[i] || filter[i] == src2[i]
                    || filter[i] == src3[i]) {
                result[i] = '0';
            } else {
                result[i] = '+';
            }
        }
        result[0] = 'O';
        Log.e("tag", "result == " + result.toString());
        return result;

    }

    /**
     * 将fliter全部转化为0,8，+
     * @param filter1
     * @param filter2
     * @param filter3
     * @return
     */
    public String[] dealresult(char[] filter1, char[] filter2, char[] filter3) {
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
        Log.e(TAG, "dealresult filter1==" + tmp[0]);
        Log.e(TAG, "dealresult filter2==" + tmp[1]);
        Log.e(TAG, "dealresult filter3==" + tmp[2]);
        return tmp;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
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


}
