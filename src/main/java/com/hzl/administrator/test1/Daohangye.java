package com.hzl.administrator.test1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Daohangye extends Activity {

    LinearLayout ll_points;
    View v_redpoint;
    List<ImageView> mlist;
    private Button bt_startExp;
    private int	disPoints;
    public int[] guides = new int[]{
            R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4
    };
    private TextView textView85;
    ViewPager mviewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daohangye);
        mviewpage = (ViewPager) findViewById(R.id.viewFlipper1);

        v_redpoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                v_redpoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //计算点与点之间的距离
                disPoints = (ll_points.getChildAt(1).getLeft() - ll_points.getChildAt(0)
                        .getLeft());
            }
        });
        initUI();
        initView();

    }

    private void initView() {
        ll_points= (LinearLayout) findViewById(R.id.ll_guide_points);
        v_redpoint=findViewById(R.id.v_guide_redpoint);
        bt_startExp= (Button) findViewById(R.id.bt_guide_startexp);
    }

    private void initUI() {
        mlist = new ArrayList<ImageView>();
        LayoutInflater inflat = LayoutInflater.from(this);
        for (int i = 0; i < guides.length; i++){
            ImageView iv_temp = new ImageView(getApplicationContext());
            iv_temp.setBackgroundResource(guides[i]);
            mlist.add(iv_temp);
            View v_point = new View(getApplicationContext());
            v_point.setBackgroundResource(R.drawable.gray_point);
            int dip = 10;
            android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(DensityUtil.dip2px(getApplicationContext(), dip), DensityUtil.dip2px(getApplicationContext(), dip));// 注意单位是px 不是dp

            // 设置点与点直接的空隙
            // 第一个点不需要指定
            if (i != 0)// 过滤第一个点
                params.leftMargin = 10;// px
            v_point.setLayoutParams(params);// 无缝隙的挨一起

            // 添加灰色的点到线性布局中
            ll_points.addView(v_point);
        }


        MViewPageAdapter adapter = new MViewPageAdapter(mlist);
        mviewpage.setAdapter(adapter);
        Log.e("sdfsdfsd", adapter.getCount() + "");
    }

    class MViewPageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

        private List<ImageView> mViewList;

        public MViewPageAdapter(List<ImageView> views) {
            mViewList = views;
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float leftMargin = disPoints * (position + positionOffset);

            //设置红点的左边距
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v_redpoint.getLayoutParams();
            layoutParams.leftMargin = Math.round(leftMargin);//对float类型四舍五入

            //重新设置布局
            v_redpoint.setLayoutParams(layoutParams);
        }

        @Override
        public void onPageSelected(int position) {

            if (position == mlist.size() - 1) {
                bt_startExp.setVisibility(View.VISIBLE);//设置设置按钮的显示
            } else {
                //不是最后一页，隐藏该button按钮
                bt_startExp.setVisibility(View.VISIBLE);
            }


        }

    }
}
