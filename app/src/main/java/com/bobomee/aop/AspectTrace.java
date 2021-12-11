package com.bobomee.aop;

import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AspectTrace {
    private final String TAG = "AspectJ—Sample";

    @Around("call(* android.widget.TextView.setText(java.lang.CharSequence))")
    public void handleSetText(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d(TAG," start handleSetText");
        proceedingJoinPoint.proceed(new Object[]{"处理过的text"});
        Log.d(TAG," end handleSetText");
    }

    @Before("call(* android.widget.Toast.show())")
    public void changeToast(JoinPoint joinPoint) throws Throwable {
        Toast toast = (Toast) joinPoint.getTarget();
        toast.setText("修改后的toast");
        Log.d(TAG, " Toast has changed");
    }

    /**
     * 在MainActivity的所有生命周期的方法中打印log
     */
    @Before("execution(* android.app.Activity.**(..))")
    public void lifeCycle(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getSimpleName();
        Log.e(TAG, "class:" + className+" method:" + methodSignature.getName());
    }

}
