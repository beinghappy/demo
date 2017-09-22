package com.example.testdemo;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class EditChangedListener implements TextWatcher {  
    private CharSequence temp;//监听前的文本  
    private int editStart;//光标开始位置  
    private int editEnd;//光标结束位置  
    private final int charMaxNum = 10;  
    private boolean DEBUG = true;
    private String TAG = "editchangelistener";
    private EditText mEditText;
    

    @Override  
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
        if (DEBUG)  
            Log.i(TAG, "输入文本之前的状态");  
        temp = s;  
    }  

    @Override  
    public void onTextChanged(CharSequence s, int start, int before, int count) {  
        if (DEBUG)  
            Log.i(TAG, "输入文字中的状态，count是一次性输入字符数");  

    }  

    @Override  
    public void afterTextChanged(Editable s) {  
        if (DEBUG)  
            Log.i(TAG, "输入文字后的状态");  
        /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */  

    }  
};  