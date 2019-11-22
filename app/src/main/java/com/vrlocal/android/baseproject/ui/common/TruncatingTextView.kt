package com.vrlocal.android.baseproject.ui.common

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TruncatingTextView : AppCompatTextView {
    private var truncateAfter = Int.MAX_VALUE
    private var suffix: String? = null
    private val truncateTextSpan = RelativeSizeSpan(0.75f)
    private val viewMoreTextSpan =
        ForegroundColorSpan(Color.BLUE)

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    fun setText(
        fullText: CharSequence?,
        afterTruncation: CharSequence?,
        truncateAfterLineCount: Int
    ) {
        suffix =
            TWO_SPACES + MORE_STRING
        if (!TextUtils.isEmpty(afterTruncation)) {
            suffix += TWO_SPACES + afterTruncation
        }
        // Don't call setMaxLines() unless we have to, since it does a redraw.
        if (truncateAfter != truncateAfterLineCount) {
            truncateAfter = truncateAfterLineCount
            maxLines = truncateAfter
        }
        text = fullText
    }

    override fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (layout != null && layout.lineCount > truncateAfter) {
            val lastCharToShowOfFullTextAfterTruncation =
                layout.getLineVisibleEnd(truncateAfter - 1) - suffix!!.length - ELLIPSIS.length
            if (text.length <= lastCharToShowOfFullTextAfterTruncation) { // No idea why this would be the case, but to prevent a crash, here it is. Besides, if this is true, we should be less than our maximum lines and thus good to go.
                return
            }
            val startIndexOfMoreString =
                lastCharToShowOfFullTextAfterTruncation + TWO_SPACES.length + 1
            val truncatedSpannableString = SpannableString(
                text.subSequence(
                    0,
                    lastCharToShowOfFullTextAfterTruncation
                ).toString() + ELLIPSIS + suffix
            )
            truncatedSpannableString.setSpan(
                truncateTextSpan,
                startIndexOfMoreString,
                truncatedSpannableString.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            truncatedSpannableString.setSpan(
                viewMoreTextSpan,
                startIndexOfMoreString,
                startIndexOfMoreString + MORE_STRING.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            text = truncatedSpannableString
        }
    }

    companion object {
        const val TWO_SPACES = "  "
        private const val MORE_STRING = "see more"
        private const val ELLIPSIS = "..."
    }
}