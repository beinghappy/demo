package com.example.testdemo;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关键词高亮显示 类的功能描述
 * Created by tanglong on 2021/8/18 22:28.
 *
 * @author tanglong
 * @since version 13
 */

public class KeyUtils {
    /**
     * 关键字高亮变色
     *
     * @param color 变化的色值
     * @param text 文字
     * @param keyword 文字中的关键字
     * @return 结果SpannableString
     */
    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        SpannableString s = new SpannableString(text);
        keyword=escapeExprSpecialWord(keyword);
        text=escapeExprSpecialWord(text);
        if (text.contains(keyword)&&!TextUtils.isEmpty(keyword)){
            try {
                Pattern p = Pattern.compile(keyword);
                Matcher m = p.matcher(s);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }catch (Exception e){
            }
        }
        return s;
    }


    /**
     *
     * @param color
     * @param text
     * @param keywords
     * @return
     */
    public static SpannableStringBuilder  matcherSearchTitles(int color, String text, String[] keywords) {
        SpannableStringBuilder spannable=new SpannableStringBuilder(text);
        CharacterStyle span=null;
        for(int i=0;i<keywords.length;i++){
            Pattern pattern=Pattern.compile(keywords[i]);
            Matcher matcher=pattern.matcher(text);
            while(matcher.find()){
                span=new ForegroundColorSpan(color);
                spannable.setSpan(span,matcher.start(),matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannable;

    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return keyword
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
