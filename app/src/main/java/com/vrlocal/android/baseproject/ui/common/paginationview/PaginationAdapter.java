//package com.vrlocal.android.baseproject.ui.common.paginationview;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.graphics.drawable.Drawable;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.appcompat.widget.AppCompatTextView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.RequestBuilder;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
//import com.vrlocal.android.baseproject.R;
//import com.vrlocal.android.baseproject.ui.common.GlideApp;
//import com.vrlocal.android.baseproject.ui.screens.photos.data.Photo;
//import com.vrlocal.android.baseproject.util.VConstants;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private static final int ITEM = 0;
//    private static final int LOADING = 1;
//
//
//    private List<Photo> photoList;
//    private Context context;
//
//    private boolean isLoadingAdded = false;
//    private boolean retryPageLoad = false;
//
//    private PaginationAdapterCallback mCallback;
//
//    private String errorMsg;
//
//    public PaginationAdapter(Context context, PaginationRecyclerView paginationRecyclerView) {
//        this.context = context;
//        this.mCallback = paginationRecyclerView;
//        photoList = new ArrayList<>();
//    }
//
//    public List<Photo> getMovies() {
//        return photoList;
//    }
//
//    public void setMovies(List<Photo> moviePhotoS) {
//        this.photoList = moviePhotoS;
//    }
//
//    @NotNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder = null;
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        switch (viewType) {
//            case ITEM:
//                View viewItem = inflater.inflate(R.layout.item_list, parent, false);
//                viewHolder = new MovieVH(viewItem);
//                break;
//            case LOADING:
//
//                viewHolder = new LoadingVH(getErrorLayout());
//                break;
//        }
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Photo resultDTO = photoList.get(position); // Movie
//
//        switch (getItemViewType(position)) {
//            case ITEM:
//                final MovieVH movieVH = (MovieVH) holder;
//
//                movieVH.mMovieTitle.setText(resultDTO.getTitle());
//                loadImage(resultDTO.getUrl())
//                        .listener(new RequestListener<Drawable>() {
//                            @Override
//                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                // TODO: 2/16/19 Handle failure
//                                movieVH.mProgress.setVisibility(View.GONE);
//                                return false;
//                            }
//
//                            @Override
//                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                // image ready, hide progress now
//                                movieVH.mProgress.setVisibility(View.GONE);
//                                return false;   // return false if you want Glide to handle everything else.
//                            }
//                        })
//                        .into(movieVH.mPosterImg);
//
//                break;
//
//            case LOADING:
//                LoadingVH loadingVH = (LoadingVH) holder;
//
//                if (retryPageLoad) {
//                    loadingVH.mErrorLayout.setVisibility(View.VISIBLE);
//                    loadingVH.mProgressBar.setVisibility(View.GONE);
//
//                    loadingVH.mErrorTxt.setText(
//                            errorMsg != null ?
//                                    errorMsg :
//                                    context.getString(R.string.error_msg_unknown));
//
//                } else {
//                    loadingVH.mErrorLayout.setVisibility(View.GONE);
//                    loadingVH.mProgressBar.setVisibility(View.VISIBLE);
//                }
//                break;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return photoList == null ? 0 : photoList.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
////        if (position == 0) {
////            return HERO;
////        } else {
//            return (position == photoList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
////        }
//    }
//
//
//    public void add(Photo r) {
//        photoList.add(r);
//        notifyItemInserted(photoList.size() - 1);
//    }
//
//    public void addAll(List<Photo> movePhotos) {
//        for (Photo resultDTO : movePhotos) {
//            add(resultDTO);
//        }
//    }
//
//    private RequestBuilder<Drawable> loadImage(@NonNull String posterPath) {
//        return GlideApp
//                .with(context)
//                .load(VConstants.BASE_URL + posterPath)
//                .centerCrop();
//    }
//
//
//    public void remove(Photo r) {
//        int position = photoList.indexOf(r);
//        if (position > -1) {
//            photoList.remove(position);
//            notifyItemRemoved(position);
//        }
//    }
//
//    public void clear() {
//        isLoadingAdded = false;
//        while (getItemCount() > 0) {
//            remove(getItem(0));
//        }
//    }
//
//    public boolean isEmpty() {
//        return getItemCount() == 0;
//    }
//
//
//    public void addLoadingFooter() {
//        isLoadingAdded = true;
//        add(new Photo(0, 0, "", "", ""));
//    }
//
//    public void removeLoadingFooter() {
//        isLoadingAdded = false;
//
//        int position = photoList.size() - 1;
//        Photo resultDTO = getItem(position);
//
//        if (resultDTO != null) {
//            photoList.remove(position);
//            notifyItemRemoved(position);
//        }
//    }
//
//    public Photo getItem(int position) {
//        return photoList.get(position);
//    }
//
//    /**
//     * Displays Pagination retry footer view along with appropriate errorMsg
//     *
//     * @param show
//     * @param errorMsg to display if page load fails
//     */
//    public void showRetry(boolean show, @Nullable String errorMsg) {
//        retryPageLoad = show;
//        notifyItemChanged(photoList.size() - 1);
//
//        if (errorMsg != null) this.errorMsg = errorMsg;
//    }
//
//    private FrameLayout getErrorLayout() {
//
//
//        FrameLayout frameLayout = new FrameLayout(context);
//        frameLayout.setId(R.id.loadmore_errorlayout);
//
//        ProgressBar progressBar = new ProgressBar(context);
//        progressBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        progressBar.setId(R.id.loadmore_progress);
//
//
//        LinearLayout errorLayout = new LinearLayout(context);
//        errorLayout.setGravity(Gravity.CENTER);
//        errorLayout.setPadding(45, 0, 45, 0);
//        errorLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        FrameLayout.LayoutParams errorParam = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        errorParam.gravity = Gravity.CENTER;
//        errorLayout.setVisibility(View.GONE);
//        errorLayout.setOrientation(LinearLayout.VERTICAL);
//
//        AppCompatTextView sorryText = new AppCompatTextView(context);
//        sorryText.setGravity(Gravity.CENTER);
//        sorryText.setText("Unable to Load More data");
//        sorryText.setTypeface(Typeface.DEFAULT_BOLD);
//        sorryText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//
//
//        AppCompatTextView txtError = new AppCompatTextView(context);
//        txtError.setId(R.id.loadmore_errortxt);
//        txtError.setText("Tap again to retry!");
//        txtError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
//        txtError.setGravity(Gravity.CENTER);
//
//
//        AppCompatButton appCompatButton = new AppCompatButton(context);
//        appCompatButton.setBackground(null);
//        appCompatButton.setId(R.id.loadmore_retry);
//        appCompatButton.setText("Retry");
//
//
//        errorLayout.addView(sorryText);
//        errorLayout.addView(txtError);
//        errorLayout.addView(appCompatButton);
//
//
//        errorLayout.setLayoutParams(errorParam);
//
//        frameLayout.addView(progressBar);
//        frameLayout.addView(errorLayout);
//
//        return frameLayout;
//    }
////---------------------------------------- View Holder --------------------------------------------------------
//    protected class MovieVH extends RecyclerView.ViewHolder {
//        private TextView mMovieTitle;
//        private ImageView mPosterImg;
//        private ProgressBar mProgress;
//
//        public MovieVH(View itemView) {
//            super(itemView);
//
//            mMovieTitle = itemView.findViewById(R.id.movie_title);
//            mPosterImg = itemView.findViewById(R.id.movie_poster);
//            mProgress = itemView.findViewById(R.id.movie_progress);
//        }
//    }
//
//
//    protected class LoadingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private ProgressBar mProgressBar;
//        private ImageButton mRetryBtn;
//        private TextView mErrorTxt;
//        private LinearLayout mErrorLayout;
//
//        public LoadingVH(View itemView) {
//            super(itemView);
//
//            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
//            mRetryBtn = itemView.findViewById(R.id.loadmore_retry);
//            mErrorTxt = itemView.findViewById(R.id.loadmore_errortxt);
//            mErrorLayout = itemView.findViewById(R.id.loadmore_errorlayout);
//
//            mRetryBtn.setOnClickListener(this);
//            mErrorLayout.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.loadmore_retry:
//                case R.id.loadmore_errorlayout:
//
//                    showRetry(false, null);
//                    mCallback.retryPageLoad();
//
//                    break;
//            }
//        }
//    }
//
//}