package com.coderpig.drysisters.data.result;

import com.coderpig.drysisters.data.dto.GankMeizi;

import java.util.List;

/**
 * 描述：Gank.io返回的结果集
 *
 * @author CoderPig on 2018/02/14 10:52.
 */

public class GankResult {
    private Boolean error;
    private List<GankMeizi> results;

    public GankResult() { }

    public GankResult(Boolean error, List<GankMeizi> results) {
        this.error = error;
        this.results = results;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<GankMeizi> getResults() {
        return results;
    }

    public void setResults(List<GankMeizi> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results=" + results.toString() +
                '}';
    }
}
