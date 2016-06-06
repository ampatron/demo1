package com.uxr.demo.one;

import com.uxr.demo.one.models.Task;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by abigail on 6/1/2016.
 */
public interface MooApi {
    String BASE_URL = "http://mootask.com";

    String ENDPOINT_GET_TASKS = "/api/taskcontroller/tasks";
    String ENDPOINT_LOAD_TASK_IMAGE = "/taskcontroller/showtaskimagethumbnail";
    String ENDPOINT_LOAD_COMPANY_LOGO = "/profilecontroller/showlogobyid";

    @GET(ENDPOINT_GET_TASKS)
    Observable<List<Task>> getTasks(@Query("from") int from, @Query("max") int max);
}
