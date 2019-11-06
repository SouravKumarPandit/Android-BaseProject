package com.vrlocal.android.baseproject.util.viewutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.vrlocal.android.baseproject.R;
import com.vrlocal.uicontrolmodule.common.VUtil;

import java.util.Objects;

public class VAlertDialog extends AlertDialog.Builder
{

    String sPositiveBtnText = "Ok";
    String sNegativeBtnText = "Cancel";
    int iPositiveButtonColor = 0;
    int iNegativeBtnColor = Color.LTGRAY;
    int iTitleColor = Color.LTGRAY;

    public VAlertDialog(Context context)
    {
        super(context);
    }

    public VAlertDialog(Context context, int iResTitle, String sMessage, String sPositiveBtnText)
    {
        this(context, iResTitle, sMessage, sPositiveBtnText, 0);
    }

    public VAlertDialog(Context context, int iResTitle, String sMessage, String sPositiveBtnText, int iButtonColor)
    {
        this(context);
        initDialog(context, iResTitle, null, sMessage, sPositiveBtnText, iButtonColor);
    }

    public VAlertDialog(Context context, String sTitle, String sMessage, String sPositiveBtnText)
    {
        this(context, sTitle, sMessage, sPositiveBtnText, 0);
    }

    public VAlertDialog(Context context, String sTitle, String sMessage, String sPositiveBtnText, int iButtonColor)
    {
        this(context);
        initDialog(context, 0, sTitle, sMessage, sPositiveBtnText, iButtonColor);
    }

    private void initDialog(Context context, int iResTitle, String sTitle, String sMessage, String sPositiveBtnText, int iPositiveButtonColor)
    {
//        setTitle(Html.fromHtml("<font color="+"'"+iPositiveButtonColor+"'>"+context.getString(iResTitle)+"</font>"));

        if (iResTitle > 0)
            setTitle(iResTitle);
        else if (sTitle != null)
            setTitle(sTitle);

        if (sMessage != null)
            setMessage(Html.fromHtml(sMessage));

        this.iPositiveButtonColor = iPositiveButtonColor;
        this.iTitleColor = iPositiveButtonColor;

        if (sPositiveBtnText != null)
            this.sPositiveBtnText = sPositiveBtnText;

    }


    public void setCancelAction(String sNegativeBtnText, DialogInterface.OnCancelListener clOnCancelListener)
    {
        this.sNegativeBtnText = sNegativeBtnText;
        setCancelable(true);
        setOnCancelListener(clOnCancelListener);
    }

    public void showDialog(boolean bPositiveButton, boolean bNegattveButton, DialogInterface.OnClickListener clOnClickListener)
    {

        if (clOnClickListener != null)
        {
            if (bPositiveButton)
                setPositiveButton(sPositiveBtnText, clOnClickListener);

            if (bNegattveButton)
                setNegativeButton(sNegativeBtnText, clOnClickListener);
        }
        show();
    }


    @SuppressLint("ObsoleteSdkInt")
    @Override
    public AlertDialog show()
    {

        AlertDialog clDialog = super.show();
        Button clPoisitive = clDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button clNegative = clDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        if (clPoisitive != null && iPositiveButtonColor != 0)
            clPoisitive.setTextColor(iPositiveButtonColor);
        if (clNegative != null)
            clNegative.setTextColor(iNegativeBtnColor);

        try
        {
            int iAlertTitleId = clDialog.getContext().getResources().getIdentifier("alertTitle", "id", "android");
            TextView titleView;

            if (iAlertTitleId > 0)
                titleView = (TextView) clDialog.findViewById(iAlertTitleId);
            else
                titleView = (TextView) clDialog.findViewById(R.id.alertTitle);

            if (titleView != null && iTitleColor != 0)
            {
                titleView.setTextColor(iTitleColor);
            }

//            LinearLayout clViewGroup=(LinearLayout)titleView.getParent();
//            clViewGroup.setGravity(Gravity.CENTER_VERTICAL);
//            clViewGroup.setBackgroundColor(Color.LTGRAY);


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
            Objects.requireNonNull(clDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        else {
            GradientDrawable gradientDrawable=new GradientDrawable();
            gradientDrawable.setCornerRadius(VUtil.dpToPx(10));
            gradientDrawable.setColor(Color.LTGRAY);
            Objects.requireNonNull(clDialog.getWindow()).setBackgroundDrawable(gradientDrawable);
        }


        return clDialog;
    }

    public int getPositiveButtonColor()
    {
        return iPositiveButtonColor;
    }

    public void setPositiveButtonColor(int iPositiveButtonColor)
    {
        this.iPositiveButtonColor = iPositiveButtonColor;
    }

    public int getNegativeBtnColor()
    {
        return iNegativeBtnColor;
    }

    public void setNegativeBtnColor(int iNegativeBtnColor)
    {
        this.iNegativeBtnColor = iNegativeBtnColor;
    }

    public int getTitleColor()
    {
        return iTitleColor;
    }

    public void setTitleColor(int iTitleColor)
    {
        this.iTitleColor = iTitleColor;
    }
}
