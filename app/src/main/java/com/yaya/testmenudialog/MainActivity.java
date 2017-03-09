package com.yaya.testmenudialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.yaya.testmenudialog.MenuUtils.XMMenuDialog;

import java.util.ArrayList;
import java.util.HashMap;

import static com.yaya.testmenudialog.MenuUtils.XMMenuDialog.ARROW_IN_LEFT;
import static com.yaya.testmenudialog.MenuUtils.XMMenuDialog.ARROW_IN_MIDDLE;
import static com.yaya.testmenudialog.MenuUtils.XMMenuDialog.ARROW_IN_RIGHT;

public class MainActivity extends Activity {

    private ArrayList<HashMap<String, Integer>> hashMapArrayList;
    HashMap<String, Integer> map;
    private XMMenuDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        hashMapArrayList = new ArrayList<>();

    }


    //position表示箭头位置，左、中、右
    public void showDialogNow(int position, boolean hasIcon) {
        dialog = new XMMenuDialog(this, R.style.MenuDialog, hasIcon, hashMapArrayList);
        dialog.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(MainActivity.this,"这是1",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"这是2",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"这是3",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        dialog.showDialog(position);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.button1:
                hashMapArrayList.clear();
                fillData(R.string.chat_list_add_friend);
                fillData(R.string.chat_list_close_message_notify);
                fillData(R.string.chat_list_create_group_chat);
                showDialogNow(ARROW_IN_LEFT, false);
                break;
            case R.id.button2:
                hashMapArrayList.clear();
                fillData(R.string.chat_list_add_friend);
                fillData(R.string.chat_list_close_message_notify);
                fillData(R.string.chat_list_create_group_chat);
                showDialogNow(ARROW_IN_MIDDLE, false);
                break;
            case R.id.button3:
                hashMapArrayList.clear();
                fillDataIcon(R.string.chat_list_add_friend, R.drawable.ic_create_group_chat);
                fillDataIcon(R.string.chat_list_close_message_notify, R.drawable.ic_create_group_chat);
                fillDataIcon(R.string.chat_list_create_group_chat, R.drawable.ic_create_group_chat);
                showDialogNow(ARROW_IN_RIGHT, true);
                break;
            default:
                break;
        }
    }

    private void fillData(int textId) {
        map = new HashMap<>();
        map.put("text", textId);
        hashMapArrayList.add(map);
    }

    private void fillDataIcon(int textId, int iconId) {
        map = new HashMap<>();
        map.put("text", textId);
        map.put("icon", iconId);
        hashMapArrayList.add(map);
    }

//    private void fillText(int resId) {
//        arrayListText.add(resId);
//    }
//
//    private void fillTextIcon(int textId, int iconId) {
//        HashMap<String, Object> hashMap = new HashMap();
//        hashMap.put("drawable", iconId);
//        hashMap.put("text", textId);
//        hashMapArrayList.add(hashMap);
//    }

}
