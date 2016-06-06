package com.uxr.demo.one.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.uxr.demo.one.MooApi;

import java.util.List;

/**
 * Created by abigail on 6/1/2016.
 */
@JsonObject
public class Company {
    @JsonField
    private long entityId;
    @JsonField
    private List<Logo> logos;
    @JsonField
    private String name;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public List<Logo> getLogos() {
        return logos;
    }

    public void setLogos(List<Logo> logos) {
        this.logos = logos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonObject
    public static class Logo {
        @JsonField
        private long entityId;

        public long getEntityId() {
            return entityId;
        }

        @Override
        public String toString() {
            return MooApi.BASE_URL + MooApi.ENDPOINT_LOAD_COMPANY_LOGO +"?id="+entityId;
        }

        public void setEntityId(long entityId) {
            this.entityId = entityId;
        }
    }
}
