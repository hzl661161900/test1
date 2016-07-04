package com.hzl.administrator.test1;

import android.os.Bundle;

public class SetActivity extends BaseAdataper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
    }

    @Override
    public void showPrev() {
        intent_on(Se2tActivity.class);
    }

    @Override
    public void showNext() {
        intent_on(Se2tActivity.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
