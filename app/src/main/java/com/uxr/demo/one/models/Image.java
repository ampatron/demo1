package com.uxr.demo.one.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.uxr.demo.one.MooApi;

/**
 * Created by abigail on 6/1/2016.
 */
@JsonObject
public class Image {
    @JsonField
    private long entityId;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return MooApi.BASE_URL + MooApi.ENDPOINT_LOAD_TASK_IMAGE +"?id="+entityId;
    }

}
