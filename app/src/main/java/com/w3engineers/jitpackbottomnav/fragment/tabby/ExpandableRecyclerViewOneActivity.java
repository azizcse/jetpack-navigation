package com.w3engineers.jitpackbottomnav.fragment.tabby;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.w3engineers.jitpackbottomnav.R;
import com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr.CommentExpandAdapter;
import com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr.CommentItem;
import com.w3engineers.jitpackbottomnav.fragment.tabby.adaptr.ExpandableRecyclerAdapter;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 带HeaderView的分页加载LinearLayout RecyclerView
 */
public class ExpandableRecyclerViewOneActivity extends AppCompatActivity {
    private static final String TAG = "lzx";

    /**
     * 服务器端一共多少条数据
     */
    private static final int TOTAL_COUNTER = 2040;

    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;

    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;

    private LRecyclerView mRecyclerView = null;

    private CommentExpandAdapter mDataAdapter = null;

    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private boolean isRefresh = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_ll_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (LRecyclerView) findViewById(R.id.list);


        mDataAdapter = new CommentExpandAdapter(this, mRecyclerView);
        mDataAdapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        mLRecyclerViewAdapter.addHeaderView(new SampleHeader(this));

        mLRecyclerViewAdapter.addFooterView(new SampleFooter(this));

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentCounter = 0;
                isRefresh = true;
                requestData(mCurrentCounter);
            }
        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onScrollUp() {
                showToast("onScrollUp");
            }

            @Override
            public void onScrollDown() {
                showToast("onScrollDown");
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
                showToast("onScrolled: " + ",distanceX " + distanceX + ",distanceY" + distanceY);
            }

            @Override
            public void onScrollStateChanged(int state) {
                showToast("onScrollStateChanged " + state);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                Toast.makeText(ExpandableRecyclerViewOneActivity.this, "OnLoadMoreListener " + mCurrentCounter, Toast.LENGTH_LONG).show();
                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading data
                    requestData(mCurrentCounter);
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);


        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.split)
                .build();

        mRecyclerView.addItemDecoration(divider);

        //不要在调用下面代码
        /*mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //ItemModel item = mDataAdapter.getDataList().get(position);
                //AppToast.showShortText(ExpandableRecyclerViewOneActivity.this, item.title);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //ItemModel item = mDataAdapter.getDataList().get(position);
                //AppToast.showShortText(ExpandableRecyclerViewOneActivity.this, "onItemLongClick - " + item.title);
            }
        });*/

        mRecyclerView.refresh();
    }

    private void showToast(String txt) {

        //Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<CommentItem> list) {

        mDataAdapter.setItems(list);
        mCurrentCounter += list.size();
    }

    /**
     * 模拟请求网络
     */
    private void requestData(final int currentItemCount) {
        Log.d(TAG, "requestData: " + currentItemCount);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                addItems(mDataAdapter.getSampleItems(currentItemCount));

                mRecyclerView.refreshComplete(10);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        this.getMenuInflater().inflate(R.menu.menu_main_expand, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_expand_all:
                mDataAdapter.expandAll();
                return true;
            case R.id.action_collapse_all:
                mDataAdapter.collapseAll();
                return true;
            default:
                return true;
        }


    }

}