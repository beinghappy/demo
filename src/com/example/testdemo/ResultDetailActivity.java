package com.example.testdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testdemo.bean.ResultInfo;

public class ResultDetailActivity extends Activity {

    EditText srcTextView1, srcTextView2, srcTextView3;
    EditText srcEditTextfilter1;
    EditText srcEditTextfilter2;
    EditText srcEditTextfilter3;
    EditText input1;
    EditText input2;
    EditText input3;
    TextView srcTextViewresult1, srcTextViewresult2, srcTextViewresult3;

    private static final String TAG = "ResultDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ResultInfo info = (ResultInfo) getIntent().getSerializableExtra("data");


        srcTextView1 = (EditText) findViewById(R.id.textView1);
        srcTextView2 = (EditText) findViewById(R.id.textView2);
        srcTextView3 = (EditText) findViewById(R.id.textView3);

        input1 = (EditText) findViewById(R.id.result_input1);
        input2 = (EditText) findViewById(R.id.result_input2);
        input3 = (EditText) findViewById(R.id.result_input3);

        srcEditTextfilter1 = (EditText) findViewById(R.id.textView_fiter1);
        srcTextViewresult1 = (TextView) findViewById(R.id.textView_result1);

        srcEditTextfilter2 = (EditText) findViewById(R.id.textView_fiter2);
        srcTextViewresult2 = (TextView) findViewById(R.id.textView_result2);

        srcEditTextfilter3 = (EditText) findViewById(R.id.textView_fiter3);
        srcTextViewresult3 = (TextView) findViewById(R.id.textView_result3);

//        srcTextViewresult1.setVisibility(View.GONE);
//        srcTextViewresult2.setVisibility(View.GONE);
//        srcTextViewresult3.setVisibility(View.GONE);
//
//        input1.setVisibility(View.GONE);
//        input2.setVisibility(View.GONE);
//        input3.setVisibility(View.GONE);

        initView(info);

    }



    private void initView(ResultInfo resultInfo) {
        // TODO Auto-generated method stub

        srcTextView1.setText(resultInfo.getFlag1());
        setHightColor(srcTextView1, 3, 4);
        setHightColor(srcTextView1, 7, 8);
        setHightColor(srcTextView1, 9, 10);
        srcTextView2.setText(resultInfo.getFlag2());
        setHightColor(srcTextView2, 3, 4);
        setHightColor(srcTextView2, 7, 8);
        setHightColor(srcTextView2, 9, 10);
        srcTextView3.setText(resultInfo.getFlag3());
        setHightColor(srcTextView3, 3, 4);
        setHightColor(srcTextView3, 7, 8);
        setHightColor(srcTextView3, 9, 10);

        srcEditTextfilter1.setText(resultInfo.getFliter1());
        srcEditTextfilter2.setText(resultInfo.getFliter2());
        srcEditTextfilter3.setText(resultInfo.getFliter3());

        srcTextViewresult1.setText(resultInfo.getResult1());
        srcTextViewresult2.setText(resultInfo.getResult2());
        srcTextViewresult3.setText(resultInfo.getResult3());

        input1.setText(resultInfo.getResult_input1());
        input2.setText(resultInfo.getResult_input2());
        input3.setText(resultInfo.getResult_input3());

    }

    public void setHightColor(EditText textView, int startIndex, int endIndex) {
        SpannableStringBuilder styled = new SpannableStringBuilder(
                textView.getText());
        // i 未起始字符索引，j 为结束字符索引
        styled.setSpan(new ForegroundColorSpan(Color.RED), startIndex,
                endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(styled);
    }

}
