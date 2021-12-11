package com.bobomee.aop;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bobomee.aop_annotation.Check;
import com.bobomee.aop_runtime.CheckerHolder;
import com.bobomee.aop_runtime.checker.Checker;

import org.aspectj.lang.JoinPoint;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AspectJ—Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckerHolder.setChecker(new Checker() {
            @Override
            public boolean check(String[] strings, JoinPoint joinPoint) {
                if (TextUtils.equals("1", strings[0])) {
                    Log.e(TAG, "check: === intercept === " + strings[0]);
                    return true;
                } else if (TextUtils.equals("2", strings[0])) {
                    Log.e(TAG, "check: === intercept === " + strings[0]);
                    return false;
                }
                return false;
            }
        });

        findViewById(R.id.aop_checker1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeThing1();
            }
        });

        findViewById(R.id.aop_checker2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeThing2("hahaha");
            }
        });
        TextView toast = findViewById(R.id.aop_toast);

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "未更改的toast", Toast.LENGTH_SHORT).show();
                toast.setText("未更改的toast");
            }
        });
    }

    @Check({"2"})
    private void doSomeThing2(String str) {
        Log.d(TAG, "doSomeThing2: === passed == "+str);
    }

    @Check({"1"})
    private void doSomeThing1() {
        Log.d(TAG, "passed === doSomeThing1: ");
    }
}