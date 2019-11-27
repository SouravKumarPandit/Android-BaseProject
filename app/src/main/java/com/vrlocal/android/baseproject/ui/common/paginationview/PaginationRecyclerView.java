//package com.vrlocal.android.baseproject.ui.common.paginationview;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.vrlocal.android.baseproject.R;
//import com.vrlocal.android.baseproject.data.VResult;
//import com.vrlocal.android.baseproject.ui.base.BaseActivity;
//import com.vrlocal.android.baseproject.ui.screens.photos.PhotosViewModel;
//import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo;
//import com.vrlocal.uicontrolmodule.common.VNetworkUtils;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.List;
//import java.util.concurrent.TimeoutException;
//
//import retrofit2.Response;
//
//public class PaginationRecyclerView extends FrameLayout implements PaginationAdapterCallback {
//    Context mContext;
//    PaginationAdapter adapter;
//    LinearLayoutManager linearLayoutManager;
//    RecyclerView rvRecyclerView;
//    ProgressBar progressBar;
//    LinearLayout errorLayout;
//    Button btnRetry;
//    TextView txtError;
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    private static final int PAGE_START = 1;
//
//    private boolean isLoading = false;
//    private boolean isLastPage = false;
//    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
//    private static final int TOTAL_PAGES = 5;
//    private int currentPage = PAGE_START;
//
//
//    //    private PhotosService movieService;
//    private PhotosViewModel viewModel;
//
//
//    public PaginationRecyclerView(Context context) {
//        super(context);
//        initLayout(context);
//    }
//
//    public PaginationRecyclerView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initLayout(context);
//
//    }
//
//    public PaginationRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initLayout(context);
//    }
//
//    public void initLayout(Context mContext) {
//        this.mContext = mContext;
//        getContentLayout();
//
//        adapter = new PaginationAdapter(mContext, this);
//        linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
//        rvRecyclerView.setLayoutManager(linearLayoutManager);
//        rvRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        rvRecyclerView.setAdapter(adapter);
//
//        rvRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage += 1;
//
//                loadNextPage();
//            }
//
//            @Override
//            public int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });
//
//        //init service and load data
////        movieService = MovieApi.getClient(mContext).create(MovieService.class);
////        movieService.getPhotos();
//        swipeRefreshLayout.setOnRefreshListener(this::doRefresh);
//
//    }
//
//    private void getContentLayout() {
//
//        swipeRefreshLayout = new SwipeRefreshLayout(mContext);
//        swipeRefreshLayout.setOnRefreshListener(this::doRefresh);
//        swipeRefreshLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        swipeRefreshLayout.setId(R.id.main_swiperefresh);
//
//        rvRecyclerView = new RecyclerView(mContext);
//        rvRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        rvRecyclerView.setClipToPadding(false);
//        rvRecyclerView.setId(R.id.main_recycler);
//        rvRecyclerView.setPadding(0, 15, 0, 100);
//        swipeRefreshLayout.addView(rvRecyclerView);
//
//        progressBar = new ProgressBar(mContext);
//        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.gravity = Gravity.CENTER;
//        progressBar.setLayoutParams(layoutParams);
//        progressBar.setVisibility(GONE);
//        progressBar.setId(R.id.main_progress);
//
//
//        this.addView(getErrorLayout());
//
//
//        this.addView(progressBar);
//        this.addView(swipeRefreshLayout);
//
//    }
//
//    private LinearLayout getErrorLayout() {
//        errorLayout = new LinearLayout(mContext);
//        errorLayout.setGravity(Gravity.CENTER);
//        errorLayout.setPadding(45, 0, 45, 0);
//        errorLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        LayoutParams errorParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        errorParam.gravity = Gravity.CENTER;
//        errorLayout.setVisibility(GONE);
//        errorLayout.setOrientation(LinearLayout.VERTICAL);
//
//        AppCompatTextView sorryText = new AppCompatTextView(mContext);
//        sorryText.setGravity(Gravity.CENTER);
//        sorryText.setText("Sorry! couldn't fetch movies.");
//        sorryText.setTypeface(Typeface.DEFAULT_BOLD);
//        sorryText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//
//
//        txtError = new AppCompatTextView(mContext);
//        txtError.setText("The server took too long to respond.");
//        txtError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
//        txtError.setGravity(Gravity.CENTER);
//
//
//        btnRetry = new AppCompatButton(mContext);
//        btnRetry.setBackground(null);
//        btnRetry.setOnClickListener(v -> doRefresh());
//        btnRetry.setText("Retry");
//
//
//        errorLayout.addView(sorryText);
//        errorLayout.addView(txtError);
//        errorLayout.addView(btnRetry);
//
//
//        errorLayout.setLayoutParams(errorParam);
//        return errorLayout;
//    }
//
//    private void doRefresh() {
//        progressBar.setVisibility(View.VISIBLE);
//        requestFirstLoad();
//        adapter.getMovies().clear();
//        adapter.notifyDataSetChanged();
//        swipeRefreshLayout.setRefreshing(false);
//    }
//
//    private void loadFirstPage() {
//
//        // To ensure list is visible when retry button in error view is clicked
//        hideErrorView();
//        currentPage = PAGE_START;
//
//     /*   callTopRatedMoviesApi().enqueue(new Callback<PageList>() {
//            @Override
//            public void onResponse(@NotNull Call<PageList> call, @NotNull Response<PageList> response) {
//                hideErrorView();
//
////                Log.i(TAG, "onResponse: " + (response.raw().cacheResponse() != null ? "Cache" : "Network"));
//
//                // Got data. Send it to adapter
//                List<Photo> results = fetchPhotos(response);
//                progressBar.setVisibility(View.GONE);
//                adapter.addAll(results);
//
//                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
//                else isLastPage = true;
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<PageList> call, @NotNull Throwable t) {
//                t.printStackTrace();
//                showErrorView(t);
//            }
//        });*/
//    }
//
//
//    private List<Photo> fetchPhotos(Response<PageList> response) {
////        PageList pageList = response.body();
////        assert pageList != null;
////        return pageList.getPhotos();
//        return  null;
//    }
//
//    private void loadNextPage() {
//
//      /*  callTopRatedMoviesApi().enqueue(new Callback<PageList>() {
//            @Override
//            public void onResponse(@NotNull Call<PageList> call, @NotNull Response<PageList> response) {
////                Log.i(TAG, "onResponse: " + currentPage
////                        + (response.raw().cacheResponse() != null ? "Cache" : "Network"));
//
//                adapter.removeLoadingFooter();
//                isLoading = false;
//
//                List<Photo> results = fetchPhotos(response);
//                adapter.addAll(results);
//
//                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
//                else isLastPage = true;
//            }
//
//            @Override
//            public void onFailure(Call<PageList> call, Throwable t) {
//                t.printStackTrace();
//                adapter.showRetry(true, fetchErrorMessage(t));
//            }
//        });*/
//    }
//
//    public void setHolderListener(@NotNull PhotosViewModel viewModel) {
//        this.viewModel = viewModel;
//        requestFirstLoad();
//
//    }
//
//    private void requestFirstLoad() {
//        this.viewModel.getPhotos().observe((BaseActivity) mContext, listVResult -> {
//            if (listVResult.getStatus() == VResult.Status.SUCCESS) {
//                hideErrorView();
//                List<Photo> results = listVResult.getData();
//
//                progressBar.setVisibility(View.GONE);
//                if (results != null) {
//                    adapter.addAll(results);
//                    if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
//                    else isLastPage = true;
//
//                }
//
//            } else if (listVResult.getStatus() == VResult.Status.LOADING) {
//                progressBar.setVisibility(VISIBLE);
//
//            } else if (listVResult.getStatus() == VResult.Status.ERROR) {
//                showErrorView(listVResult.getMessage());
//            }
//
//
//        });
//    }
//
//    @Override
//    public void retryPageLoad() {
//        loadNextPage();
//    }
//
//
//    private void showErrorView(String errorMessage) {
//
//        if (errorLayout.getVisibility() == View.GONE) {
//            errorLayout.setVisibility(View.VISIBLE);
//            progressBar.setVisibility(View.GONE);
//
//            txtError.setText(errorMessage);
//        }
//    }
//
//    /**
//     * @param throwable to identify the type of error
//     * @return appropriate error message
//     */
//    private String fetchErrorMessage(Throwable throwable) {
//        String errorMsg = getResources().getString(R.string.error_msg_unknown);
//
//        if (!VNetworkUtils.isAvailable()) {
//            errorMsg = getResources().getString(R.string.error_msg_no_internet);
//        } else if (throwable instanceof TimeoutException) {
//            errorMsg = getResources().getString(R.string.error_msg_timeout);
//        }
//
//        return errorMsg;
//    }
//
//    // Helpers -------------------------------------------------------------------------------------
//
//
//    private void hideErrorView() {
//        if (errorLayout.getVisibility() == View.VISIBLE) {
//            errorLayout.setVisibility(View.GONE);
//            progressBar.setVisibility(View.VISIBLE);
//        }
//    }
//
//}
