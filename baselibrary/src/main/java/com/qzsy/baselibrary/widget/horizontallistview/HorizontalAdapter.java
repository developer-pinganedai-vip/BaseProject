package com.qzsy.baselibrary.widget.horizontallistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qzsy.baselibrary.R;
import com.qzsy.baselibrary.widget.bean.TabInfo;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HorizontalAdapter extends BaseAdapter {
    private Context context;
    private List<TabInfo> list;
    private final LayoutInflater inflater;
    private Timer timer;
    private int checkPosition = 0;

    public HorizontalAdapter(Context context) {
        this.context = context;


        inflater = LayoutInflater.from(context);
    }

    public void setTabList(List<TabInfo> list){
        this.list = list;
    }

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView==null) {
//            holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.layout_custom_tab, null);
        TextView tabText = convertView.findViewById(R.id.custom_tab_text);
        ImageView tabIcon = convertView.findViewById(R.id.custom_tab_icon);
        final ImageView tabFrameIcon = convertView.findViewById(R.id.custom_tab_frame);
        LinearLayout tabBottomLayout = convertView.findViewById(R.id.custom_tab_layout);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
        final TabInfo tabInfo = list.get(position);
        tabText.setText(tabInfo.getmText());

        if (checkPosition == position) {
            tabIcon.setImageResource(tabInfo.getmIconPressedResId());
            tabText.setTextColor(tabInfo.getmSelectColor());
            if (checkPosition == 2 && tabInfo.isFrame()) {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        } else {
            tabIcon.setImageResource(tabInfo.getmIconNormalResId());
            tabText.setTextColor(tabInfo.getmNormalColor());
            if (tabInfo.isFrame()) {
                tabFrameIcon.setVisibility(View.VISIBLE);
                tabBottomLayout.setVisibility(View.GONE);
                tabFrameIcon.setImageResource(tabInfo.getmIconFrameResId());
                if (timer == null) {
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            ((android.app.Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (list.size() < 1) {
                                        return;
                                    }
                                    Animation animation = AnimationUtils.loadAnimation(context, tabInfo.getmIconFrameAnim());
                                    tabFrameIcon.startAnimation(animation);
                                }
                            });

                        }
                    }, 1000, 3000);
                }
            }
        }
        return convertView;
    }
}
