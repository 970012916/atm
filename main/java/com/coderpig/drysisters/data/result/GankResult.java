package com.coderpig.drysisters.data.result;

import com.coderpig.drysisters.data.dto.GankMeizi;

import java.util.ArrayList;

public class GankResult {

    private Boolean error;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<GankMeizi> getResults() {
        return results;
    }

    public void setResults(ArrayList<GankMeizi> results) {
        this.results = results;
    }

    private ArrayList<GankMeizi> results;

    public GankResult(){

    }

    public GankResult(Boolean error,ArrayList<GankMeizi> results) {
        this.error = error;
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


}
