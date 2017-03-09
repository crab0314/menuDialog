package com.yaya.testmenudialog.MenuUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yaya.testmenudialog.R;
import com.yaya.testmenudialog.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pangjiaqi on 2017/3/8.
 */

public class XMMenuDialog extends Dialog {

    public static int ARROW_IN_LEFT = 0;
    public static int ARROW_IN_MIDDLE = 1;
    public static int ARROW_IN_RIGHT = 2;

    private Context context;
    private Activity activity;

    private int resId;

    private View dialogView;
    private ImageView imageView;
    private ListView listView;
    private MenuAdapter adapter;

    private AdapterView.OnItemClickListener mItemClickListener;

    public void setItemClickListener(AdapterView.OnItemClickListener clickListener) {
        mItemClickListener = clickListener;
    }


    public XMMenuDialog(Context context) {
        super(context);
        resId = R.layout.menu_dialog_with_arrow;
    }

    public XMMenuDialog(Activity activity, int themeId, boolean hasIcon, ArrayList arrayList) {
        super(activity, themeId);
        resId = R.layout.menu_dialog_with_arrow;
        this.context = activity;
        this.activity = activity;
        adapter = new MenuAdapter(context, hasIcon, arrayList);
    }

    public XMMenuDialog(Context context, int themeResId, int position) {
        super(context, themeResId);
        resId = R.layout.menu_dialog_with_arrow;
        this.context = context;
        this.resId = themeResId;
    }

    protected XMMenuDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        resId = R.layout.menu_dialog_with_arrow;
    }


    //初始化小箭头的位置，dialog的位置，填充数据
    public void initDialog(int position) {
        dialogView = LayoutInflater.from(context).inflate(resId, null);
        listView = (ListView) dialogView.findViewById(R.id.ll_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mItemClickListener);

        WindowManager.LayoutParams winParams = this.getWindow().getAttributes();
        winParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        winParams.dimAmount = 0.5f;
        winParams.gravity = Gravity.TOP;
        winParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        winParams.width = UiUtils.dp2px(context, 150);
        winParams.y = 100;

        imageView = (ImageView) dialogView.findViewById(R.id.chat_list_more_arrow);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (position == ARROW_IN_MIDDLE) {
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.setMargins(0, UiUtils.dp2px(context, 12), 0, 0);
        } else {
            if (position == ARROW_IN_LEFT) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.setMargins(UiUtils.dp2px(context, 22), UiUtils.dp2px(context, 12), 0, 0);
                winParams.gravity = Gravity.TOP | Gravity.LEFT;
                winParams.x = UiUtils.dp2px(activity, 10);
            } else if (position == ARROW_IN_RIGHT) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.setMargins(0, UiUtils.dp2px(context, 12), UiUtils.dp2px(context, 22), 0);
                winParams.x = activity.getWindowManager().getDefaultDisplay().getWidth() / 2 - UiUtils.dp2px(activity, 110);
            }
        }
        imageView.setLayoutParams(layoutParams);
        this.onWindowAttributesChanged(winParams);
        setContentView(dialogView);
    }

    public void showDialog(int position) {
        this.show();
        initDialog(position);
    }

    public class MenuAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private boolean hasIcon;
        public ArrayList<HashMap<String, Integer>> arrayList;


        public MenuAdapter(Context context, boolean hasIcon, ArrayList<HashMap<String, Integer>> arrayList) {
            inflater = LayoutInflater.from(context);
            this.context = context;
            this.hasIcon = hasIcon;
            this.arrayList = arrayList;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View resView = inflater.inflate(R.layout.item_menu_text, null);
            TextView text_icon = (TextView) resView.findViewById(R.id.text_icon);
            TextView text_only = (TextView) resView.findViewById(R.id.text_only);

            if (hasIcon) {
                Drawable drawable = context.getDrawable(arrayList.get(i).get("icon"));
                drawable.setBounds(0, 0, 44, 44);
                text_icon.setCompoundDrawables(drawable, null, null, null);
                text_icon.setText(arrayList.get(i).get("text"));
                text_icon.setVisibility(View.VISIBLE);
                text_only.setVisibility(View.GONE);
            } else {
                text_only.setText(arrayList.get(i).get("text"));
                text_icon.setVisibility(View.GONE);
                text_only.setVisibility(View.VISIBLE);
            }


            return resView;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
    }
}
