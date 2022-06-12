package com.mykola.hw2_android;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mykola.hw2_android.mathExpressionParser.MathExpressionParser;
import com.mykola.hw2_android.mathExpressionParser.MathHelpers;

public class MainActivity extends AppCompatActivity {
    private String expression;
    private TextView tvOperationField;
    private TextView tvResultField;
    private Button btnSin;
    private Button btnCos;
    private Button btnTan;
    private Button btnCot;
    private Button btnPi;
    private Button btnE;
    private Button btnLeftBracket;
    private Button btnRightBracket;
    private Button btnXp2;
    private Button btnXp3;
    private Button btnXp1d2;
    private Button btnXp1d3;
    private Button btnFact;
    private Button btnAbs;
    private Button btnLog;
    private Button btnLn;
    private Button btnClearAll;
    private Button btnPercent;
    private Button btnClearSymbol;
    private Button btnDivision;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnMultiplication;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSubtraction;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnAddition;
    private Button btnMathSign;
    private Button btnZero;
    private Button btnPoint;
    private Button btnEqual;

    protected void initFields() {
        tvOperationField = findViewById(R.id.tvOperationField);
        tvResultField = findViewById(R.id.tvResultField);
    }

    protected void initButtons() {
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnCot = findViewById(R.id.btnCot);
        btnPi = findViewById(R.id.btnPi);
        btnE = findViewById(R.id.btnE);
        btnLeftBracket = findViewById(R.id.btnLeftBracket);
        btnRightBracket = findViewById(R.id.btnRightBracket);
        btnXp2 = findViewById(R.id.btnXp2);
        btnXp3 = findViewById(R.id.btnXp3);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnMathSign = findViewById(R.id.btnMathSign);
        btnPoint = findViewById(R.id.btnPoint);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnClearSymbol = findViewById(R.id.btnClearSymbol);
        btnClearSymbol = findViewById(R.id.btnClearSymbol);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnPoint = findViewById(R.id.btnPoint);
        btnPercent = findViewById(R.id.btnPercent);
        btnLog = findViewById(R.id.btnLog);
        btnLn = findViewById(R.id.btnLn);
        btnEqual = findViewById(R.id.btnEqual);
        btnAbs = findViewById(R.id.btnAbs);
        btnFact = findViewById(R.id.btnFact);
        btnXp2 = findViewById(R.id.btnXp2);
        btnXp3 = findViewById(R.id.btnXp3);
        btnXp1d2 = findViewById(R.id.btnXp1d2);
        btnXp1d3 = findViewById(R.id.btnXp1d3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFields();
        initButtons();
        setListeners();
    }

    private void setListeners() {
        btnZero.setOnClickListener(this::setZero);
        btnOne.setOnClickListener(this::setOne);
        btnTwo.setOnClickListener(this::setTwo);
        btnThree.setOnClickListener(this::setThree);
        btnFour.setOnClickListener(this::setFour);
        btnFive.setOnClickListener(this::setFive);
        btnSix.setOnClickListener(this::setSix);
        btnSeven.setOnClickListener(this::setSeven);
        btnEight.setOnClickListener(this::setEight);
        btnNine.setOnClickListener(this::setNine);
        btnAddition.setOnClickListener(this::setAddition);
        btnSubtraction.setOnClickListener(this::setSubtraction);
        btnMultiplication.setOnClickListener(this::setMultiplication);
        btnDivision.setOnClickListener(this::setDivision);
        btnClearSymbol.setOnClickListener(this::setClearSymbol);
        btnClearAll.setOnClickListener(this::setClearAll);
        btnPoint.setOnClickListener(this::setPoint);
        btnSin.setOnClickListener(this::setSin);
        btnCos.setOnClickListener(this::setCos);
        btnTan.setOnClickListener(this::setTan);
        btnCot.setOnClickListener(this::setCot);
        btnPi.setOnClickListener(this::setPi);
        btnE.setOnClickListener(this::setE);
        btnLeftBracket.setOnClickListener(this::setLeftBracket);
        btnRightBracket.setOnClickListener(this::setRightBracket);
        btnLog.setOnClickListener(this::setLog);
        btnLn.setOnClickListener(this::setLn);
        btnEqual.setOnClickListener(this::setEqual);
        btnAbs.setOnClickListener(this::setAbs);
        btnFact.setOnClickListener(this::setFact);
        btnPercent.setOnClickListener(this::setPercent);
        btnXp2.setOnClickListener(this::setXp2);
        btnXp3.setOnClickListener(this::setXp3);
        btnXp1d2.setOnClickListener(this::setXp1d2);
        btnXp1d3.setOnClickListener(this::setXp1d3);
        btnMathSign.setOnClickListener(this::setMathSign);
    }

    private void setXp1d3(View view) {
        String mathExpression = tvOperationField.getText().toString();
        double result = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
        tvOperationField.setText(mathExpression + "^(1/3)");
        tvResultField.setText(MathHelpers.getStringResult(Math.cbrt(result)));
    }

    private void setXp1d2(View view) {
        String mathExpression = tvOperationField.getText().toString();
        double result = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
        tvOperationField.setText(mathExpression + "^(1/2)");
        tvResultField.setText(MathHelpers.getStringResult(Math.sqrt(result)));
    }

    private void setXp3(View view) {
        String mathExpression = tvOperationField.getText().toString();
        double result = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
        tvOperationField.setText(mathExpression + "^3");
        tvResultField.setText(MathHelpers.getStringResult(Math.pow(result, 3)));
    }

    private void setXp2(View view) {
        String mathExpression = tvOperationField.getText().toString();
        double result = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
        tvOperationField.setText(mathExpression + "^2");
        tvResultField.setText(MathHelpers.getStringResult(Math.pow(result, 2)));
    }

    private void setFact(View view) {
        String mathExpression = tvOperationField.getText().toString();

        try {
            Double value = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
            expression = MathHelpers.getStringResult(value);
            int result = MathHelpers.factorial(expression);
            tvOperationField.setText("factorial(" + getOperationFieldValue() + ")");
            tvResultField.setText(Integer.toString(result));
        } catch (Exception ex) {
        }
    }

    private void setAbs(View view) {
        String value = tvOperationField.getText().toString();
        value = MathExpressionParser.calculate(value);
        tvOperationField.setText("abs(" + value + ")");
    }

    private String setOperationField(String mathExp) {
        String result = MathHelpers.getStringResult(Double.parseDouble(mathExp));
        return result;
    }

    private void setZero(View view) {
        setTvOperationField("0");
    }

    private void setOne(View view) {
        setTvOperationField("1");
    }

    private void setTwo(View view) {
        setTvOperationField("2");
    }

    private void setThree(View view) {
        setTvOperationField("3");
    }

    private void setFour(View view) {
        setTvOperationField("4");
    }

    private void setFive(View view) {
        setTvOperationField("5");
    }

    private void setSix(View view) {
        setTvOperationField("6");
    }

    private void setSeven(View view) {
        setTvOperationField("7");
    }

    private void setEight(View view) {
        setTvOperationField("8");
    }

    private void setNine(View view) {
        setTvOperationField("9");
    }

    private void setAddition(View view) {
        checkMathExpression();
        setTvOperationField("+");
    }

    private void setSubtraction(View view) {
        checkMathExpression();
        setTvOperationField("-");
    }

    private void setMultiplication(View view) {
        checkMathExpression();
        setTvOperationField("*");
    }

    private void setDivision(View view) {
        checkMathExpression();
        setTvOperationField("/");
    }

    private void setTvOperationField(String text) {
        tvOperationField.setText(tvOperationField.getText().toString() + text);
    }

    private void setClearSymbol(View view) {
        tvResultField.setText("");
        String tvOperationFieldValue = tvOperationField.getText().toString();

        if (tvOperationFieldValue.length() > 0) {
            int size = tvOperationFieldValue.length() - 1;
            tvOperationFieldValue = tvOperationFieldValue.substring(0, size);
            tvOperationField.setText(tvOperationFieldValue);
        } else {
            tvOperationField.setText("");
        }
    }

    private void setClearAll(View view) {
        tvOperationField.setText("");
        tvResultField.setText("");
    }

    private void setMathSign(View view) {
        String value = tvOperationField.getText().toString();
        tvOperationField.setText("-(" + value + ")");
    }

    private void setPercent(View view) {
        String mathExpression = tvOperationField.getText().toString();
        checkMathExpression();

        try {
            mathExpression = MathHelpers.getStringResult(Double.parseDouble(mathExpression));
            double number = Double.parseDouble(mathExpression);
            tvOperationField.setText(MathHelpers.getStringResult(number) + "%");
            tvResultField.setText(Double.toString(number / 100));
        } catch (Exception ex) {
        }
    }

    private void setPoint(View view) {
        String mathExpression = tvOperationField.getText().toString();
        tvOperationField.setText(mathExpression + ".");
    }

    private void setSin(View view) {
        String mathExpression = tvOperationField.getText().toString();
        tvOperationField.setText("sin(" + mathExpression + ")");
    }

    private void setCos(View view) {
        String mathExpression = tvOperationField.getText().toString();
        tvOperationField.setText("cos(" + mathExpression + ")");
    }

    private void setTan(View view) {
        String mathExpression = tvOperationField.getText().toString();
        tvOperationField.setText("tan(" + mathExpression + ")");
    }

    private void setCot(View view) {
        String mathExpression = tvOperationField.getText().toString();
        tvOperationField.setText("cot(" + mathExpression + ")");
    }

    private void setPi(View view) {
        setTvOperationField("pi");
    }

    private void setE(View view) {
        setTvOperationField("e");
    }

    private void setLeftBracket(View view) {
        setTvOperationField("(");
    }

    private void setRightBracket(View view) {
        setTvOperationField(")");
    }

    private void setLn(View view) {
        String mathExpression = getOperationFieldValue();
        tvOperationField.setText("log(" + mathExpression + ")");
    }

    private void setLog(View view) {
        String mathExpression = getOperationFieldValue();
        tvOperationField.setText("log(" + mathExpression + ")");
    }

    private void setEqual(View view) {
        checkMathExpression();
        String mathExpression = tvOperationField.getText().toString();
        try {
            Double value = Double.parseDouble(MathExpressionParser.calculate(mathExpression));
            tvResultField.setText(MathHelpers.getStringResult(value));
        } catch (Exception ex) {
        }
    }

    private String getOperationFieldValue() {
        return tvOperationField.getText().toString();
    }

    private void checkMathExpression() {
        String mathExpression = tvOperationField.getText().toString();

        if(mathExpression.contains("factorial(") || mathExpression.contains("^") || mathExpression.contains("abs(")) {
            tvOperationField.setText(tvResultField.getText());
        }
    }
}