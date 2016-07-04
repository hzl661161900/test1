package com.hzl.administrator.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Administrator
 * @time 2016/5/25 11:16
 */
public abstract class BaseAdataper extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public  void next(View view){
        showNext();
    }
    public  void prev(View view){
        showPrev();
    }

    public void intent_on(Class t){
        Intent intent=new Intent();
        intent.setClass(this,t);
        startActivity(intent);
    }

    public abstract void showPrev();

    public abstract void showNext();

}
