package jp.android.viewflipper;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationHelper {
    /**
     * 右から描画領域に入ってくる動作を表現するアニメーションを生成します
     * @return Animation 生成したアニメーションが戻ります
     */
    public static Animation inFromRightAnimation() {

        Animation inFromRight = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , +1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromRight.setDuration(350);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
    /**
     * 描画領域の左へ出ていく動作を表現するアニメーションを生成します
     * @return Animation 生成したアニメーションが戻ります
     */
    public static Animation outToLeftAnimation() {
        Animation outtoLeft = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , -1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        outtoLeft.setDuration(350);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }    
    /**
     * 左から描画領域に入ってくる動作を表現するアニメーションを生成します
     * @return Animation 生成したアニメーションが戻ります
     */
    public static Animation inFromLeftAnimation() {
        Animation inFromLeft = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , -1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f);
        inFromLeft.setDuration(350);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
    /**
     * 描画領域の右へ出ていく動作を表現するアニメーションを生成します
     * @return Animation 生成したアニメーションが戻ります
     */
    public static Animation outToRightAnimation() {
        Animation outtoRight = 
            new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , +1.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f
                    , Animation.RELATIVE_TO_PARENT
                    , 0.0f );
        outtoRight.setDuration(350);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }           
}