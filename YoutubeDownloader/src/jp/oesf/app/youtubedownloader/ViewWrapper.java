package jp.oesf.app.youtubedownloader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 行Viewのラッパークラス
 */
public class ViewWrapper {
	/** baseのView */
	private final View mBase;
	/** TitleのView */
	private TextView mTitle = null;
	/** SummaryのView */
	private TextView mSummary = null;
	/** ImageのView */
	private ImageView mImageView = null;

	/**
	 * コンストラクタ。 基本となるViewを定義します。
	 * 
	 * @param base
	 *            基本となるView
	 */
	public ViewWrapper(View base) {
		this.mBase = base;
	}

	/**
	 * TitleViewの取得します。
	 * 
	 * @return TitleView
	 */
	public final TextView getTitle() {
		if (mTitle == null) {
			mTitle = (TextView) mBase.findViewById(R.id.title_text);
		}

		return (mTitle);
	}

	/**
	 * SummaryViewの取得します。
	 * 
	 * @return SummaryView
	 */
	public final TextView getSummary() {
		if (mSummary == null) {
			mSummary = (TextView) mBase.findViewById(R.id.summary_text);
		}

		return (mSummary);
	}

	/**
	 * ImageViewの取得します。
	 * 
	 * @return ImageView
	 */
	public final ImageView getImageView() {
		if (mImageView == null) {
			mImageView = (ImageView) mBase.findViewById(R.id.thumbnail_image);
		}

		return (mImageView);
	}

}
