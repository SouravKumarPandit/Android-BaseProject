package com.vrlocal.android.baseproject.ui.common;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class TruncatingTextView extends AppCompatTextView {
    public static final String TWO_SPACES = "  ";

    private int truncateAfter = Integer.MAX_VALUE;

    private String suffix;
    private RelativeSizeSpan truncateTextSpan = new RelativeSizeSpan(0.75f);
    private ForegroundColorSpan viewMoreTextSpan = new ForegroundColorSpan(Color.BLUE);
    private static final String MORE_STRING = "see more";

    private static final String ELLIPSIS = "...";

    public TruncatingTextView(Context context) {
        super(context);
    }

    public TruncatingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TruncatingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(CharSequence fullText, @Nullable CharSequence afterTruncation, int truncateAfterLineCount) {
        this.suffix = TWO_SPACES + MORE_STRING;

        if (!TextUtils.isEmpty(afterTruncation)) {
            suffix += TWO_SPACES + afterTruncation;
        }

        // Don't call setMaxLines() unless we have to, since it does a redraw.
        if (this.truncateAfter != truncateAfterLineCount) {
            this.truncateAfter = truncateAfterLineCount;
            setMaxLines(truncateAfter);
        }

        setText(fullText);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (getLayout() != null && getLayout().getLineCount() > truncateAfter) {
            int lastCharToShowOfFullTextAfterTruncation = getLayout().getLineVisibleEnd(truncateAfter - 1) - suffix.length() - ELLIPSIS.length();

            if (getText().length() <= lastCharToShowOfFullTextAfterTruncation) {
                // No idea why this would be the case, but to prevent a crash, here it is. Besides, if this is true, we should be less than our maximum lines and thus good to go.
                return;
            }

            int startIndexOfMoreString = lastCharToShowOfFullTextAfterTruncation + TWO_SPACES.length() + 1;

            SpannableString truncatedSpannableString = new SpannableString(getText().subSequence(0, lastCharToShowOfFullTextAfterTruncation) + ELLIPSIS + suffix);
            truncatedSpannableString.setSpan(truncateTextSpan, startIndexOfMoreString, truncatedSpannableString.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            truncatedSpannableString.setSpan(viewMoreTextSpan, startIndexOfMoreString, startIndexOfMoreString + MORE_STRING.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(truncatedSpannableString);
        }
    }
}