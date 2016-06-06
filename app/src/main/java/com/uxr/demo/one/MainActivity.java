package com.uxr.demo.one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.uxr.demo.one.models.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                               @Override
                               public final void onCompleted() {
                                   // do nothing
                               }

                               @Override
                               public final void onError(Throwable throwable) {
                                   Toast.makeText(MainActivity.this, "Make sure you are connected to the network.", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public final void onNext(List<Task> tasks) {
                                   refresh(tasks);
                               }
                           }
                );
    }

    private void refresh(List<Task> tasks) {
        mAdapter.setNewList(tasks);
    }
}
