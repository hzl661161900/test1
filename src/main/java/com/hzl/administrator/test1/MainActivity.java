package com.hzl.administrator.test1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.hzl.administrator.test1.iflytek.voicedemo.IatDemo;
import com.iflytek.cloud.SpeechUtility;

import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    MaterialDialog materialDialog;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SpeechUtility.createUtility(MainActivity.this, "appid=" + getString(R.string.app_id));
        button = (Button) findViewById(R.id.button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDialog.setTitle("Two").setMessage("点击").setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击OK", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击Cancel", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });

    }


    public void init(View v) {
        materialDialog = new MaterialDialog(this);

        Toast.makeText(getApplicationContext(), "Initializes successfully.",
                Toast.LENGTH_SHORT).show();
    }

    public void show(View v) {
        if (materialDialog != null) {
            materialDialog.setTitle("MaterialDialog").setMessage("这是一个materialdialog").setPositiveButton("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("CANCEL", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    materialDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Cancal", Toast.LENGTH_LONG).show();
                }
            }).setCanceledOnTouchOutside(true)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Toast.makeText(MainActivity.this,
                                    "onDismiss",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        } else {
            Toast.makeText(getApplicationContext(), "You should init firstly!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    static int i = 0;

    public void setView(View v) {
        switch (v.getId()) {
            case R.id.button_prog:
//                materialDialog = new MaterialDialog(this);
//                View view = LayoutInflater.from(this)
//                        .inflate(R.layout.progressbar_item,
//                                null);
//                materialDialog.setCanceledOnTouchOutside(true);
//                materialDialog.setView(view).show();
//                break;
//                ClientThread clientThread=new ClientThread(100);
//                new Thread(clientThread).start();
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, IatDemo.class);
                startActivity(intent1);

                break;
            case R.id.button_set_cont:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollingActivity.class);
                startActivity(intent);

                break;
            case R.id.button_set_contentViewById:
                materialDialog = new MaterialDialog(this);
                if (materialDialog != null) {
                    if (i % 2 != 0) {
                        materialDialog.setBackgroundResource(
                                R.drawable.background);
                    } else {
                        Resources res = getResources();
                        Bitmap bmp = BitmapFactory.decodeResource(res,
                                R.drawable.background2);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(
                                getResources(), bmp);
                        materialDialog.setBackground(bitmapDrawable);
                    }
                    materialDialog.setCanceledOnTouchOutside(true).show();
                    i++;
                    Toast.makeText(getApplicationContext(),
                            "Try to click again~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "You should init firstly!", Toast.LENGTH_SHORT)
                            .show();
                }
                break;

            case R.id.button_set_notitile:
                InputMethodManager imm = (InputMethodManager) this.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
