package com.vrlocal.android.baseproject.ui.common.paginationview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo;

import java.util.ArrayList;
import java.util.List;

public class PageList {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Photo> results = new ArrayList<Photo>();
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Photo> getPhotos() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setPhotos(List<Photo> results) {
        this.results = results;
    }

    /**
     *
     * @return
     * The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     *
     * @param totalResults
     * The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     *
     * @return
     * The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     *
     * @param totalPages
     * The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
