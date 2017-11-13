package com.rinkosen.mygank.util;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.rinkosen.mygank.entity.Gank;

/**
 * Created by rinkousen on 17/10/26.
 */

public class StringUtil {

    public static SpannableString getGankStyleStr(Gank gank) {
        String gankStr = gank.desc + " @" + gank.who;
        SpannableString spannableString = new SpannableString(gankStr);
        spannableString.setSpan(new RelativeSizeSpan(0.8f), gank.desc.length() + 1, gankStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), gank.desc.length() + 1, gankStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
