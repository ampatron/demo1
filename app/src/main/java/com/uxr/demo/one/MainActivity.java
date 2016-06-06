package com.uxr.demo.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.uxr.demo.one.models.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recycler;
    private TaskAdapter mAdapter = new TaskAdapter(new ArrayList<Task>());

    private static final int MAX_ITEMS_PER_LOAD = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setAdapter(mAdapter);
        getTasks();
    }

    private void getTasks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MooApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();

        MooApi mooTask = retrofit.create(MooApi.class);
        mooTask.getTasks(0, MAX_ITEMS_PER_LOAD)
                .subscribeOn(Schedulers.io())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.v(TAG, "");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.v(TAG, "error : " + throwable.getMessage());
                    }
                })
                .subscribe(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        refresh(tasks);
                    }
                });
    }

    private void refresh(List<Task> tasks) {
        mAdapter.setNewList(tasks);
    }
}
