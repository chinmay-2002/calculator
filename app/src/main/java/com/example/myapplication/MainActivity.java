package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result, data;
    MaterialButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bc,bac,bequal,binto,bdivide,bplus,bminus,bca,bcaa,bdot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.Data);
        result = findViewById(R.id.result);

        assignId(b0, R.id.b0);
        assignId(b1, R.id.b1);
        assignId(b2, R.id.b2);
        assignId(b3, R.id.b3);
        assignId(b4, R.id.b4);
        assignId(b5, R.id.b5);
        assignId(b6, R.id.b6);
        assignId(b7, R.id.b7);
        assignId(b8, R.id.b8);
        assignId(b9, R.id.b9);
        assignId(bac, R.id.bac);
        assignId(bc, R.id.bc);
        assignId(bequal, R.id.bequals);
        assignId(binto, R.id.b7into);
        assignId(bdivide, R.id.bcdiv);
        assignId(bplus, R.id.bplus);
        assignId(bminus, R.id.bminus);
        assignId(bcaa, R.id.bcaa);
        assignId(bca, R.id.bca);
        assignId(bdot, R.id.bdot);



    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btntext = btn.getText().toString();
        String datatocal = data.getText().toString();


        if (btntext.equals("AC")) {
            result.setText("0");
            data.setText("");
            return;
        }
        if (btntext.equals("=")) {
            data.setText(result.getText());
            return;
        }
        if (btntext.equals("C")) {
            datatocal = datatocal.substring(0, datatocal.length() - 1);

        } else {
            datatocal += btntext;

        }
        data.setText(datatocal);
        String finalr = getResult(datatocal);


        if(!finalr.equals("err")){
            result.setText(finalr);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "javasript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }
        catch (Exception e){
            return "err";
        }
    }
}