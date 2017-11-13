package com.rinkosen.mygank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rinkosen.mygank.R;
import com.rinkosen.mygank.ui.widget.BezierView;
import com.rinkosen.mygank.ui.widget.CircleCheck;
import com.rinkosen.mygank.ui.widget.RulerView;

/**
 * Created by rinkousen on 17/11/2.
 */

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RulerView check = (RulerView) findViewById(R.id.circle);
//        check.start();
    }
}
