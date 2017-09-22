package com.example.testdemo;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class EditChangedListener implements TextWatcher {  
    private CharSequence temp;//����ǰ���ı�  
    private int editStart;//��꿪ʼλ��  
    private int editEnd;//������λ��  
    private final int charMaxNum = 10;  
    private boolean DEBUG = true;
    private String TAG = "editchangelistener";
    private EditText mEditText;
    

    @Override  
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
        if (DEBUG)  
            Log.i(TAG, "�����ı�֮ǰ��״̬");  
        temp = s;  
    }  

    @Override  
    public void onTextChanged(CharSequence s, int start, int before, int count) {  
        if (DEBUG)  
            Log.i(TAG, "���������е�״̬��count��һ���������ַ���");  

    }  

    @Override  
    public void afterTextChanged(Editable s) {  
        if (DEBUG)  
            Log.i(TAG, "�������ֺ��״̬");  
        /** �õ���꿪ʼ�ͽ���λ�� ,������������¼�ճ����������������п��� */  

    }  
};  