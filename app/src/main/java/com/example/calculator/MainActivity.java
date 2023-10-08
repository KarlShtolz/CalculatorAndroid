package com.example.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button bSinus;
    private Button bMemory;
    private Button bCosinus;
    private Button bTangens;
    private EditText etInp;
    private EditText etOut;
    private Button bPlus;
    private Button bClear;
    private Button bMinus;
    private Button bMultiply;
    private Button bDivision;
    private Button bOpenBracket;
    private Button bCloseBracket;
    private Button bPoint;
    private Button bBackspace;
    private Button bEqual;
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    public int screenWidth;
    public int screenHeight;
    private static final int BUTTON_COLUMNS = 4;
    private static final int BUTTON_ROWS = 6;//5
    private static final int ALL_ROWS = 8;//7
    private static int buttonWidth;
    private static int buttonHeight;
    private static int etHeight;
    private static final String EMPTY_STR = "";
    private static final String LVL_ERROR = "<<ERROR>>";
    private static final String LVL_INFO  = "<<INFO_>>";
    private static final String LVL_OK    = "<<OK___>>";
    private static final String LVL_DEBUG = "<<DEBUG>>";
    private char [] numbers = {'0','1','2','3','4','5','6','7','8','9'};
    private char [] sign = {'/','*','-','+','(',')',','};
    private char [] signWithNoOpenBracket = {'/','*','-','+',')',','};
    private long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViewParams();
    }
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
    public void initViewParams () {
        etInp = findViewById(R.id.et_inp);
        etOut = findViewById(R.id.et_out);
        bPlus = findViewById(R.id.b_plus);
        bMinus = findViewById(R.id.b_minus);
        bMultiply = findViewById(R.id.b_multiply);
        bDivision = findViewById(R.id.b_division);
        bOpenBracket = findViewById(R.id.b_open_bracket);
        bCloseBracket = findViewById(R.id.b_close_bracket);
        bPoint = findViewById(R.id.b_point);
        bClear = findViewById(R.id.b_c);
        bSinus = findViewById(R.id.b_sin);//
        bCosinus = findViewById(R.id.b_cos);
        bTangens = findViewById(R.id.b_tan);
        bMemory = findViewById(R.id.b_memory);//
        b0 = findViewById(R.id.b_0);
        b1 = findViewById(R.id.b_1);
        b2 = findViewById(R.id.b_2);
        b3 = findViewById(R.id.b_3);
        b4 = findViewById(R.id.b_4);
        b5 = findViewById(R.id.b_5);
        b6 = findViewById(R.id.b_6);
        b7 = findViewById(R.id.b_7);
        b8 = findViewById(R.id.b_8);
        b9 = findViewById(R.id.b_9);
        bBackspace = findViewById(R.id.b_backspace);
        bEqual = findViewById(R.id.b_equal);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        buttonWidth = (int) (screenWidth / BUTTON_COLUMNS);
        buttonHeight = (int) (screenHeight / ALL_ROWS);
        etHeight = (int) (((screenHeight - (buttonHeight * BUTTON_ROWS)) / 2));
        LinearLayout.LayoutParams lpEt = new LinearLayout.LayoutParams(screenWidth, etHeight);
        etOut.setLayoutParams(lpEt);
        etInp.setLayoutParams(lpEt);
        LinearLayout.LayoutParams lpButt = new LinearLayout.LayoutParams(buttonWidth, buttonHeight);
        b0.setLayoutParams(lpButt);
        b1.setLayoutParams(lpButt);
        b2.setLayoutParams(lpButt);
        b3.setLayoutParams(lpButt);

        bSinus.setLayoutParams(lpButt);
        bCosinus.setLayoutParams(lpButt);
        bTangens.setLayoutParams(lpButt);
        bMemory.setLayoutParams(lpButt);

        b4.setLayoutParams(lpButt);
        b5.setLayoutParams(lpButt);
        b6.setLayoutParams(lpButt);
        b7.setLayoutParams(lpButt);
        b8.setLayoutParams(lpButt);
        b9.setLayoutParams(lpButt);
        bPlus.setLayoutParams(lpButt);
        bClear.setLayoutParams(lpButt);
        bMinus.setLayoutParams(lpButt);
        bMultiply.setLayoutParams(lpButt);
        bDivision.setLayoutParams(lpButt);
        bOpenBracket.setLayoutParams(lpButt);
        bCloseBracket.setLayoutParams(lpButt);
        bPoint.setLayoutParams(lpButt);
        bBackspace.setLayoutParams(lpButt);
        bEqual.setLayoutParams(lpButt);
    }
    public void onClickClear(View view) {
        etInp.setText(EMPTY_STR);
        etOut.setText(EMPTY_STR);
    }
    public void onClick7(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('7');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                || inpArr[inpArr.length - 1] != '(') {
            inp.append('7');
            etInp.setText(inp);
        }
    }
    public void onClick8(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('8');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                || inpArr[inpArr.length - 1] != '(') {
            inp.append('8');
            etInp.setText(inp);
        }
    }
    public void onClick9(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('9');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                || inpArr[inpArr.length - 1] != '(') {
            inp.append('9');
            etInp.setText(inp);
        }
    }
    public void onClick4(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('4');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                 || inpArr[inpArr.length - 1] != '(') {
            inp.append('4');
            etInp.setText(inp);
        }
    }
    public void onClick5(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('5');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
               || inpArr[inpArr.length - 1] != '(') {
            inp.append('5');
            etInp.setText(inp);
        }
    }
    public void onClick6(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('6');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                 || inpArr[inpArr.length - 1] != '(') {
            inp.append('6');
            etInp.setText(inp);
        }
    }
    public void onClick1(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('1');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                || inpArr[inpArr.length - 1] != '(') {
            inp.append('1');
            etInp.setText(inp);
        }
    }
    public void onClick2(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('2');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
                 || inpArr[inpArr.length - 1] != '(') {
            inp.append('2');
            etInp.setText(inp);
        }
    }
    public void onClick3(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('3');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] != '/' || inpArr[inpArr.length - 1] != '*' || inpArr[inpArr.length - 1] != '-' || inpArr[inpArr.length - 1] != '+'
               || inpArr[inpArr.length - 1] != '(') {
            inp.append('3');
            etInp.setText(inp);
        }
    }
    public void onClick0(View view) {
        boolean flag0 = false;
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr.length == 0) {
            inp.append('0');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '.' || inpArr[inpArr.length - 1] == '/' || inpArr[inpArr.length - 1] == '*'
                || inpArr[inpArr.length - 1] == '-' || inpArr[inpArr.length - 1] == '+' || inpArr[inpArr.length - 1] == '(') {
            inp.append('0');
            etInp.setText(inp);
            return;
        }
        if (inpArr[inpArr.length - 1] == '0') {
            for (int i = 1; i < inpArr.length; i++) {
                if (inpArr[inpArr.length - i - 1] == '1' || inpArr[inpArr.length - i - 1] == '2' || inpArr[inpArr.length - i - 1] == '3' || inpArr[inpArr.length - i - 1] == '4'
                        || inpArr[inpArr.length - i - 1] == '5' || inpArr[inpArr.length - i - 1] == '6' || inpArr[inpArr.length - i - 1] == '7' || inpArr[inpArr.length - i - 1] == '8'
                        || inpArr[inpArr.length - i - 1] == '9' || inpArr[inpArr.length - i - 1] == '.' || inpArr[inpArr.length - i - 1] == '/' || inpArr[inpArr.length - i - 1] == '*'
                        || inpArr[inpArr.length - i - 1] == '-' || inpArr[inpArr.length - i - 1] == '+' || i ==  inpArr.length - 1) {
                    flag0 = true;
                }
            }
            if (flag0) {
                inp.append('0');
                etInp.setText(inp);
            }
        }
    }
    public void onClickPoint(View view) {
        boolean flagPoint = false;
        boolean flagOperand = false;
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] arrInp = inp.toString().toCharArray();
        if (arrInp[arrInp.length - 1] == '/' || arrInp[arrInp.length - 1] == '*' || arrInp[arrInp.length - 1] == '-' || arrInp[arrInp.length - 1] == '+'
                || arrInp[arrInp.length - 1] == ')' || arrInp[arrInp.length - 1] == '(' || arrInp[arrInp.length - 1] == '.'){
            return;
        }
        for (int i = 0; i < arrInp.length; i++) {
            if (((arrInp[arrInp.length - i - 1] == '/' || arrInp[arrInp.length - i - 1] == '*' || arrInp[arrInp.length - i - 1] == '-' || arrInp[arrInp.length - i - 1] == '+'
                    || arrInp[arrInp.length - i - 1] == ')' || arrInp[arrInp.length - i - 1] == '(') && !flagPoint) || i == arrInp.length - 1){
                inp.append('.');
                etInp.setText(inp);
                return;
            }
            if (arrInp[arrInp.length - i - 1] == '.') {
                flagPoint = true;
                return;
            }
        }
    }
    public void onClickPlus(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '0' || inpArr[inpArr.length - 1] == ')'){
            inp.append('+');
            etInp.setText(inp);
        }
    }
    public void onClickMultiply(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '0' || inpArr[inpArr.length - 1] == ')'){
            inp.append('*');
            etInp.setText(inp);
        }
    }
    public void onClickDivision(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '0' || inpArr[inpArr.length - 1] == ')'){
            inp.append('/');
            etInp.setText(inp);
        }
    }
    public void onClickOpenBracket(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            etInp.setText(inp.append('('));
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr[inpArr.length - 1] == '(' || inpArr[inpArr.length - 1] == '*' || inpArr[inpArr.length - 1] == '-' || inpArr[inpArr.length - 1] == '/' || inpArr[inpArr.length - 1] == '+') {
            etInp.setText(inp.append('('));
        }
    }
    public void onClickCloseBracket(View view) {
        int countOpen = 0;
        int countClose = 0;
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        for (int i = 0; i < inpArr.length; i++) {
            if (inpArr[i] == '(') {
                countOpen++;
            }
            if (inpArr[i] == ')') {
                countClose++;
            }
        }
        if ((inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '0') || (inpArr[inpArr.length - 1] == ')' && countOpen > countClose)){
            etInp.setText(inp.append(')'));
        }
    }
    public void onClickBackspace(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            return;
        }
        char [] arr = inp.toString().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        if (isContainCharInArr(numbers, arr[arr.length - 1]) || isContainCharInArr(signWithNoOpenBracket, arr[arr.length - 1])) {
            //и последний символ - цифра или знак
            for (int i = 0; i < arr.length - 1; i++) {
                stringBuilder.append(arr[i]);
            }
        } else {
            StringBuilder tmp = new StringBuilder();
            //если последний символ sin(, cos(, tan(
            for (int i = arr.length - 4; i < arr.length; i++) {
                tmp.append(arr[i]);
            }
            if (tmp.toString().equals("sin(") || tmp.toString().equals("cos(") || tmp.toString().equals("tan(")) {
                for (int i = 0; i < arr.length - 4; i++) {
                    stringBuilder.append(arr[i]);
                }
            } else {
                for (int i = 0; i < arr.length - 1; i++) {
                    stringBuilder.append(arr[i]);
                }
            }
        }
        etInp.setText(stringBuilder);
    }
    public void onClickEqual(View view) throws Exception {
        String strInp = etInp.getText().toString();
        if (strInp.isEmpty()) {
            return;
        }
        MathPars p = new MathPars();
        try {
            String strAns = String.valueOf(p.Parse(strInp));
            etOut.setText(strAns);
        } catch (Exception e) {
            etOut.setText(LVL_ERROR);
        }
        String strOut = etOut.getText().toString();
        StringBuilder memoryStr = new StringBuilder();
        SharedPreferences memory = getSharedPreferences(MemoryActivity.LAST_OPERATION, MODE_PRIVATE);
        String tvmem = memory.getString(MemoryActivity.OPERATION, "");
        memoryStr.append(tvmem).append("\n").append(strInp).append("=").append(strOut);
        SharedPreferences.Editor editor = memory.edit();
        editor.putString(MemoryActivity.OPERATION, memoryStr.toString());
        editor.apply();
        editor.commit();
    }

    public void onClickMinus(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        if (inp.length() == 0) {
            inp.append('-');
            etInp.setText(inp);
            return;
        }
        char [] inpArr = inp.toString().toCharArray();
        if (inpArr[inpArr.length - 1] == '1' || inpArr[inpArr.length - 1] == '2' || inpArr[inpArr.length - 1] == '3' || inpArr[inpArr.length - 1] == '4'
                || inpArr[inpArr.length - 1] == '5' || inpArr[inpArr.length - 1] == '6' || inpArr[inpArr.length - 1] == '7' || inpArr[inpArr.length - 1] == '8'
                || inpArr[inpArr.length - 1] == '9' || inpArr[inpArr.length - 1] == '0' || inpArr[inpArr.length - 1] == ')' || inpArr[inpArr.length - 1] == '('){
            inp.append('-');
            etInp.setText(inp);
        }
    }

    public void onClickSinus(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] arr = etInp.getText().toString().toCharArray();
        if (inp.length() == 0) {
            inp.append("sin(");
            etInp.setText(inp);
            return;
        }
        if (inp.length() != 0) {
            if (isContainCharInArr(numbers, arr[arr.length - 1]) || arr[arr.length - 1] == sign[5]) {
                return;
            } else {
                inp.append("sin(");
                etInp.setText(inp);
                return;
            }
        }
    }
    public void onClickCosinus(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] arr = etInp.getText().toString().toCharArray();
        if (inp.length() == 0) {
            inp.append("cos(");
            etInp.setText(inp);
            return;
        }
        if (inp.length() != 0) {
            if (isContainCharInArr(numbers, arr[arr.length - 1]) || arr[arr.length - 1] == sign[5]) {
                return;
            } else {
                inp.append("cos(");
                etInp.setText(inp);
                return;
            }
        }
    }
    public void onClickTangens(View view) {
        StringBuilder inp = new StringBuilder(etInp.getText().toString());
        char [] arr = etInp.getText().toString().toCharArray();
        if (inp.length() == 0) {
            inp.append("tan(");
            etInp.setText(inp);
            return;
        }
        if (inp.length() != 0) {
            if (isContainCharInArr(numbers, arr[arr.length - 1]) || arr[arr.length - 1] == sign[5]) {
                return;
            } else {
                inp.append("tan(");
                etInp.setText(inp);
                return;
            }
        }
    }
    public void onClickMemory(View view) {
        //StringBuilder inp = new StringBuilder(etInp.getText().toString());
        Intent toMemory = new Intent(this, MemoryActivity.class);
        startActivity(toMemory);
    }
    public boolean isContainCharInArr(char [] arr, char a) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == a) {
                return true;
            }
        }
        return false;
    }
}