package jp.viewflipper.sample;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ViewFlipper;

public class ViewFlipper01 extends Activity implements OnItemClickListener,
        OnItemLongClickListener {
    //前・今・次ページのインスタンス
    GridView mGridView01;
    GridView mGridView02;
    GridView mGridView03;

    // ViewFlipperのインスタンス
    ViewFlipper mViewFlipper;

    // ジェスチャー
    GestureDetector mGestureDetector;

    // アニメーション
    Animation mInFromLeft;
    Animation mOutToRight;
    Animation mInFromRight;
    Animation mOutToLeft;

    // タッチ処理リスナー
    OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (mGestureDetector.onTouchEvent(event))
                return true;
			return false;
		}
    };

    // ジェスチャーリスナー
    OnGestureListener mGestureListener = new OnGestureListener() {
        public boolean onDown(MotionEvent arg0) {
            return false;
        }

        // フリック処理を実装
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            float dx = Math.abs(velocityX);
            float dy = Math.abs(velocityY);
            if (dx > dy && dx > 150) {
                if (e1.getX() < e2.getX()) {
                    mViewFlipper.setInAnimation(mInFromLeft);
                    mViewFlipper.setOutAnimation(mOutToRight);
                    mViewFlipper.showPrevious();

                    setDate(mFromDate);
                } else {
                    mViewFlipper.setInAnimation(mInFromRight);
                    mViewFlipper.setOutAnimation(mOutToLeft);
                    mViewFlipper.showNext();

                    setDate(mToDate);
                }
                searchList();
                return true;
            }
            return false;
        }

		@Override
		public void onGesture(GestureOverlayView overlay, MotionEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGestureCancelled(GestureOverlayView overlay,
				MotionEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGestureStarted(GestureOverlayView overlay,
				MotionEvent event) {
			// TODO Auto-generated method stub
			
		}
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 画面オブジェクトを取得
        mGridView01 = (GridView) findViewById(R.id.GridView01);
        mGridView02 = (GridView) findViewById(R.id.GridView02);
        mGridView03 = (GridView) findViewById(R.id.GridView03);
        mViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        // ジェスチャーを生成
        mGestureDetector = new GestureDetector(this, (android.view.GestureDetector.OnGestureListener) mGestureListener);

        // アニメーションを生成
        mInFromLeft = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        mOutToRight = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        mInFromRight = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        mOutToLeft = AnimationUtils.loadAnimation(this, R.anim.out_to_left);

        // リストの選択リスナーを設定
        mGridView01.setOnItemClickListener(this);
        mGridView02.setOnItemClickListener(this);
        mGridView03.setOnItemClickListener(this);
        mGridView01.setOnItemLongClickListener(this);
        mGridView02.setOnItemLongClickListener(this);
        mGridView03.setOnItemLongClickListener(this);

        // フリック処理のためのイベント設定
        mGridView01.setOnTouchListener(mTouchListener);
        mGridView02.setOnTouchListener(mTouchListener);
        mGridView03.setOnTouchListener(mTouchListener);

        // 初期データ読み込み
        searchList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {

        // 選択したアイテムから日付を取得
        CursorAdapter adapter = (CursorAdapter) parent.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null) {
//            // MemoActivityを呼び出すIntentを生成
//            Intent intent = new Intent(this, MemoActivity.class);
//            // パラメーターに選択した日付を設定
//            intent.putExtra(Defines.KEY_DATE, cursor.getLong(cursor
//                    .getColumnIndex(DatabaseHelper.FIELD_DATE)));
//            // Intent呼び出しを実行
//            startActivity(intent);
        }
    }

    private void searchList() {
            // 検索したデータをもとにアダプターを生成
//            DiaryAdapter adapter = new DiaryAdapter(this, mCursor);
//
//            // アダプターをListViewに設定
//            GridView gridview = (GridView) mViewFlipper.getCurrentView();
//            gridview.setAdapter(adapter);
        }

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
