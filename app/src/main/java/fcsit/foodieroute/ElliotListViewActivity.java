package fcsit.foodieroute;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Elliot on 19-Aug-16.
 */
public class ElliotListViewActivity extends AppCompatActivity {

    Context mContext = this;
    ListView mListView;
    RecyclerView mRecView;
    Toolbar mToolbar;
    ElliotListViewActivityAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elliot_list_view);
        mToolbar = (Toolbar) findViewById(R.id.m_list_view_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecView = (RecyclerView) findViewById(R.id.m_list_view);
        mAdapter = new ElliotListViewActivityAdapter(mContext, createList());
        mRecView.setAdapter(mAdapter);
        mRecView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecView.setOnScrollListener(new HidingScrollListener(mContext) {
            @Override
            public void onMoved(int distance) {
                mToolbar.setTranslationY(-distance);
            }

            @Override
            public void onShow() {

            }

            @Override
            public void onHide() {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private ArrayList<ElliotObject> createList(int count) {
        ArrayList<ElliotObject> arrayList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            arrayList.add(new ElliotObject("Item " + i, i % 2 == 0 ? R.color.c_yellow : R.color.c_light_cyan));
        }
        return arrayList;
    }

    private ArrayList<ElliotObject> createList(){
        ArrayList<ElliotObject> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new ElliotObject("KoloMee", R.drawable._01));
            arrayList.add(new ElliotObject("Kampua", R.drawable._02));
            arrayList.add(new ElliotObject("Tang Hung Soup", R.drawable._03));
            arrayList.add(new ElliotObject("Sarawak Laksa", R.drawable._04));
            arrayList.add(new ElliotObject("Sarawak Kompia", R.drawable._05));
        }
        return arrayList;
    }


}
