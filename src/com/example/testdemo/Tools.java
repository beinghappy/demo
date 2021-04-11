package com.example.testdemo;

import android.content.Context;
import android.util.Log;

import com.example.testdemo.bean.ResultInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Tools {

    private static final int RECORD_NUM = 9;

    /**
     * @param choose
     * @return
     */
    public static String getRandomId(int choose) {
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

    /**
     * @param length generate number
     * @return
     */
    public static char[] randomNumber(int length) {
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

    /**
     * @param length generate number
     * @return
     */
    public static char[][] randomNumbers(int length) {
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

        char[][] result = new char[3][length];
        char[] esp3 = getRandomId(0).substring(0, 3).toCharArray();
        System.out.println(Arrays.toString(esp3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < length; j++) {
                if (j == 0) {
                    result[i][j] = esp3[i];
                } else {
                    result[i][j] = numbersAndLetters[randGen.nextInt(10)];
                }

            }
        }
        return result;
    }

    public static List<ResultInfo> generateOtherNineRecord(Context context, int originNum) {
        List<ResultInfo> list = new ArrayList<>();
        for (int k = 0; k < RECORD_NUM; k++) {
            originNum++;
            int choose = originNum % 10;
            String head = getRandomId(choose);
            String text = head + head.substring(0, 6);


            char[] src1 = randomNumber(16);
            char[] src2 = randomNumber(16);
            char[] src3 = randomNumber(16);
            String check1 = src1.toString().substring(0, 1);
            String check2 = src2.toString().substring(0, 1);
            String check3 = src3.toString().substring(0, 1);

            List<String> checkList = new ArrayList<String>();
            checkList.add(check1);
            checkList.add(check2);
            checkList.add(check3);

            //radmon generate a tmp to get more numbers
            int tmpIndex = k % 3;
            String tmp = checkList.get(tmpIndex);

            if (checkList.contains(tmp)) {
                String strfilter1 = text;
                String strfilter2 = text;
                String strfilter3 = text;
                checkList.remove(tmp);
                for (int i = 0; i < checkList.size(); i++) {
                    int index = head.indexOf(checkList.get(i));
                    String head2 = head.substring(index, 10)
                            + head.substring(0, index);
                    String text2 = head2 + head2.substring(0, 6);
                    if (i == 0) {
                        strfilter2 = text2;
                    } else {
                        strfilter3 = text2;
                    }
                }

//				char[] filter1 = getResulString(strfilter1.toCharArray(),src1,src2,src3);
//				char[] filter2 = getResulString(strfilter2.toCharArray(),src1,src2,src3);
//				char[] filter3 = getResulString(strfilter3.toCharArray(),src1,src2,src3);
//				String[] filterTmp = dealresult(filter1, filter2, filter3);


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
                    input3 = strfilter1
                            .substring(9, 10);
                }

                String inputStr1 = input1.isEmpty() ? "" : "," + input1;
                String inputStr2 = input2.isEmpty() ? "" : "," + input2;
                String inputStr3 = input3.isEmpty() ? "" : "," + input3;

                String result = src1.toString() + inputStr1
                        + ";\n" + src1.toString()
                        + inputStr2 + ";\n"
                        + src1.toString() + inputStr3;

                ResultInfo mResultInfo = new ResultInfo();
                mResultInfo.setResult(result);
                mResultInfo.setTime(System.currentTimeMillis());
                Log.e(TAG, "generateOtherNineRecord: " + mResultInfo.toString());
                list.add(mResultInfo);

            }
        }

        return list;

//		boolean result = DBHelper.getInstance(this).addUserInfoList(list);
//		Log.e(TAG, "generateOtherNineRecord: " + result);

    }

    public char[] getResulString(char[] filter, char[] src1, char[] src2, char[] src3) {
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

//		if(result[0]=='0'){
        result[0] = 'O';
//		}

        Log.e("tag", "result == " + result.toString());
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

    /**
     * add space
     *
     * @param res
     * @return
     */
    public static String formatTo19(String res) {
        if (res.length() == 16) {
            return null;
        } else {
            return res;
        }
    }

    public static String formatTo19(char[] tmp) {
        String res = new String(tmp);
        if (res.length() == 16) {
            return null;
        } else {
            return res;
        }
    }

    /**
     * delete space
     *
     * @param res
     * @return
     */
    public static String formatToTrim(String res) {
        if (res.length() == 16) {
            return null;
        } else {
            return res;
        }
    }

    public static String formatToTrim(char[] tmp) {
        String res = new String(tmp);
        if (res.length() == 16) {
            return null;
        } else {
            return res;
        }
    }

}
