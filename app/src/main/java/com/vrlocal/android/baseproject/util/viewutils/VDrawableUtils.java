package com.vrlocal.android.baseproject.util.viewutils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

import com.vrlocal.uicontrolmodule.common.VUtil;

public class VDrawableUtils
{
    private VDrawableUtils() {}

    public static final byte BORDER_TOP = 0;
    public static final byte BORDER_BOTTOM = 1;
    public static final byte BORDER_LEFT = 2;
    public static final byte BORDER_RIGHT = 3;

    public static final byte TOP_TO_BOTTOM = 0;
    public static final byte BOTTOM_TO_TOP = 1;
    public static final byte LEFT_TO_RIGHT = 2;
    public static final byte RIGHT_TO_LEFT = 3;

    public static Drawable getLinearDrawable(int iBackgroundColor, int iLayerColor, int iLayerType)
    {

        GradientDrawable.Orientation iOrientation = null;
        iOrientation = iLayerType == TOP_TO_BOTTOM ? GradientDrawable.Orientation.TOP_BOTTOM :
                iLayerType == BOTTOM_TO_TOP ? GradientDrawable.Orientation.BOTTOM_TOP :
                        iLayerType == LEFT_TO_RIGHT ? GradientDrawable.Orientation.LEFT_RIGHT :
                                iLayerType == RIGHT_TO_LEFT ? GradientDrawable.Orientation.RIGHT_LEFT : null;

        GradientDrawable clBackground = null;
        if (iOrientation != null)
        {
            clBackground = new GradientDrawable(iOrientation, new int[]{iBackgroundColor, iBackgroundColor, iLayerColor});//new int[]{startColor, centerColor, endColor}
            clBackground.setGradientCenter(0.5f, 0.5f);
            clBackground.setShape(GradientDrawable.RECTANGLE);
        }


        return clBackground;

    }


    public static Drawable getLayerListDrawable(Context clContext, int iBackgroundColor, int iLayerColor, int iLayerType, int iLayerHeight)
    {


        if(!(iLayerHeight>0))
            iLayerHeight = VUtil.dpToPx(10);

        GradientDrawable clBackground = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{iBackgroundColor, iLayerColor});
        clBackground.setShape(GradientDrawable.RECTANGLE);
        clBackground.setColor(iBackgroundColor);

        GradientDrawable clBackgroundLayer = new GradientDrawable();
        clBackgroundLayer.setShape(GradientDrawable.RECTANGLE);
        clBackgroundLayer.setColor(iLayerColor);

        Drawable[] clLayerList = {clBackgroundLayer, clBackground};
        LayerDrawable clLinearDrawable = new LayerDrawable(clLayerList);

        //setLayerInset(layer, leftOffset, topOffset, rightOffset, bottomOffset)

        clLinearDrawable.setLayerInset(0, 0, 0, 0, 0);
        clLinearDrawable.setLayerInset(1,
                iLayerType == BORDER_LEFT ? iLayerHeight : 0,
                iLayerType == BORDER_TOP ? iLayerHeight : 0,
                iLayerType == BORDER_RIGHT ? iLayerHeight : 0,
                iLayerType == BORDER_BOTTOM ? iLayerHeight : 0);


        return clLinearDrawable;

    }



    public static Drawable getLayerListDrawable(Context clContext, int iBackgroundColor, ColorStateList clColorStateList, int iLayerType, int iBorderWidth)
    {

        if(iBorderWidth<0)
            iBorderWidth=VUtil.dpToPx( 5);

        GradientDrawable clBackground = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{iBackgroundColor, clColorStateList.getDefaultColor()});
        clBackground.setShape(GradientDrawable.RECTANGLE);
        clBackground.setColor(iBackgroundColor);

        GradientDrawable clBackgroundLayer = new GradientDrawable();
        clBackgroundLayer.setShape(GradientDrawable.RECTANGLE);
        clBackgroundLayer.setColor(clColorStateList);

        Drawable[] clLayerList = {clBackgroundLayer, clBackground};
        LayerDrawable clLinearDrawable = new LayerDrawable(clLayerList);

        //setLayerInset(layer, leftOffset, topOffset, rightOffset, bottomOffset)

        clLinearDrawable.setLayerInset(0, 0, 0, 0, 0);
        clLinearDrawable.setLayerInset(1,
                iLayerType == BORDER_LEFT ? iBorderWidth : 0,
                iLayerType == BORDER_TOP ? iBorderWidth : 0,
                iLayerType == BORDER_RIGHT ? iBorderWidth : 0,
                iLayerType == BORDER_BOTTOM ? iBorderWidth : 0);


        return clLinearDrawable;

    }



    public static StateListDrawable getSelectorDrawable(int color) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(color));
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(color));
        res.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(color));
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }

    public static StateListDrawable getRPLEffectPreLOP1(int iSelectedColor,int iNormalColor) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_checked}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{}, new ColorDrawable(iNormalColor));
        return res;
    }

    public static StateListDrawable getRPLEffectPreLOP(int iSelectedColor,int iNormalColor)
    {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{android.R.attr.state_activated}, new ColorDrawable(iSelectedColor));
        res.addState(new int[]{}, new ColorDrawable(iNormalColor));
        return res;
    }



}
