package jp.gr.java_conf.akabeko.testimagegallery.widget;

import android.view.View;
import android.view.animation.Animation;

/**
 * View の表示をトグル方式で切り替える機能を提供します。
 */
public class ToggleChangeVisibleBehavior {
	/**
	 * フェード イン用のアニメーション。
	 */
	private Animation mAnimFadeIn;

	/**
	 * フェード アウト用のアニメーション。
	 */
	private Animation mAnimFadeOut;

	/**
	 * 管理対象となる View。
	 */
	private View mView;

	/**
	 * インスタンスを初期化します。
	 *
	 * @param context コンテキスト。
	 */
	public ToggleChangeVisibleBehavior( View view ) {
		this.mView = view;
	}

	/**
	 * View の表示状態を取得します。
	 *
	 * @return 表示状態を示す値。
	 */
	public int getVisibility() {
		return this.mView.getVisibility();
	}

	/**
	 * View の表示が切り替わる時のアニメーションを設定します。
	 *
	 * @param in  表示される時のアニメーション。
	 * @param out 非表示になる時のアニメーション。
	 */
	public void setFadeAnimation( Animation in, Animation out ) {
		this.mAnimFadeIn  = in;
		this.mAnimFadeOut = out;
	}

	/**
	 * 表示・非表示を切り替えます。
	 */
	public void toggle() {
		if( this.mView.getVisibility() == View.VISIBLE ) {
			if( this.mAnimFadeOut != null ) {
				this.mView.startAnimation( this.mAnimFadeOut );
			}

			this.mView.setVisibility( View.GONE );

		} else {
			if( this.mAnimFadeIn != null ) {
				this.mView.startAnimation( this.mAnimFadeIn );
			}

			this.mView.setVisibility( View.VISIBLE );
		}
	}
}
