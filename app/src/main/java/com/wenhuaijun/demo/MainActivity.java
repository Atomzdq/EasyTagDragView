package com.wenhuaijun.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wenhuaijun.easytagdragview.EasyTipDragView;
import com.wenhuaijun.easytagdragview.bean.SimpleTitleTip;
import com.wenhuaijun.easytagdragview.bean.Tip;
import com.wenhuaijun.easytagdragview.R;
import com.wenhuaijun.easytagdragview.widget.TipItemView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity /*implements AbsTipAdapter.DragDropListener, TipItemView.OnSelectedListener, TipItemView.OnDeleteClickListener */{
    private EasyTipDragView easyTipDragView;
   // private Toolbar toolbar;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  //      toolbar =(Toolbar)findViewById(R.id.toolbar);
     // tableLayout =(TableLayout)findViewById(R.id.tabLayout);
    //    setSupportActionBar(toolbar);
        easyTipDragView =(EasyTipDragView)findViewById(R.id.easy_tip_drag_view);
        btn =(Button)findViewById(R.id.btn);
        easyTipDragView.setAddData(obtainAddData());
        easyTipDragView.setDragData(obtainData());
        easyTipDragView.setSelectedListener(new TipItemView.OnSelectedListener() {
            @Override
            public void onTileSelected(Tip entity, int position, View view) {
                toast(((SimpleTitleTip) entity).getTip());
            }
        });
        easyTipDragView.setDataResultCallback(new EasyTipDragView.OnDataChangeResultCallback() {
            @Override
            public void onDataChangeResult(ArrayList<Tip> tips) {
                Log.i("heheda", tips.toString());
            }
        });
        easyTipDragView.setOnCompleteCallback(new EasyTipDragView.OnCompleteCallback() {
            @Override
            public void onComplete(ArrayList<Tip> tips) {
                toast("最终数据：" + tips.toString());
                btn.setVisibility(View.VISIBLE);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyTipDragView.open();
                btn.setVisibility(View.GONE);
            }
        });
    }

    private List<Tip> obtainData() {
        List<Tip> list = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            SimpleTitleTip entry = new SimpleTitleTip();
            entry.setId(i);
            entry.setTip(i + " Item");
            list.add(entry);
        }
        return list;
    }

    private List<Tip> obtainAddData() {
        List<Tip> list = new ArrayList<>();
        for (int i = 25; i <= 35; i++) {
            SimpleTitleTip entry = new SimpleTitleTip();
            entry.setId(i);
            entry.setTip(i + " Item");
            list.add(entry);
        }
        return list;
    }
    public void toast(String str){
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
