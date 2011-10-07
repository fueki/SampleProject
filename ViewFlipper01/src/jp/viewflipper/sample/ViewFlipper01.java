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
    //�O�E���E���y�[�W�̃C���X�^���X
    GridView mGridView01;
    GridView mGridView02;
    GridView mGridView03;

    // ViewFlipper�̃C���X�^���X
    ViewFlipper mViewFlipper;

    // �W�F�X�`���[
    GestureDetector mGestureDetector;

    // �A�j���[�V����
    Animation mInFromLeft;
    Animation mOutToRight;
    Animation mInFromRight;
    Animation mOutToLeft;

    // �^�b�`�������X�i�[
    OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (mGestureDetector.onTouchEvent(event))
                return true;
			return false;
		}
    };

    // �W�F�X�`���[���X�i�[
    OnGestureListener mGestureListener = new OnGestureListener() {
        public boolean onDown(MotionEvent arg0) {
            return false;
        }

        // �t���b�N����������
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
        // ��ʃI�u�W�F�N�g���擾
        mGridView01 = (GridView) findViewById(R.id.GridView01);
        mGridView02 = (GridView) findViewById(R.id.GridView02);
        mGridView03 = (GridView) findViewById(R.id.GridView03);
        mViewFlipper = (ViewFlipper) findViewById(R.id.ViewFlipper01);
        // �W�F�X�`���[�𐶐�
        mGestureDetector = new GestureDetector(this, (android.view.GestureDetector.OnGestureListener) mGestureListener);

        // �A�j���[�V�����𐶐�
        mInFromLeft = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        mOutToRight = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        mInFromRight = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        mOutToLeft = AnimationUtils.loadAnimation(this, R.anim.out_to_left);

        // ���X�g�̑I�����X�i�[��ݒ�
        mGridView01.setOnItemClickListener(this);
        mGridView02.setOnItemClickListener(this);
        mGridView03.setOnItemClickListener(this);
        mGridView01.setOnItemLongClickListener(this);
        mGridView02.setOnItemLongClickListener(this);
        mGridView03.setOnItemLongClickListener(this);

        // �t���b�N�����̂��߂̃C�x���g�ݒ�
        mGridView01.setOnTouchListener(mTouchListener);
        mGridView02.setOnTouchListener(mTouchListener);
        mGridView03.setOnTouchListener(mTouchListener);

        // �����f�[�^�ǂݍ���
        searchList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {

        // �I�������A�C�e��������t���擾
        CursorAdapter adapter = (CursorAdapter) parent.getAdapter();
        Cursor cursor = adapter.getCursor();
        if (cursor != null) {
//            // MemoActivity���Ăяo��Intent�𐶐�
//            Intent intent = new Intent(this, MemoActivity.class);
//            // �p�����[�^�[�ɑI���������t��ݒ�
//            intent.putExtra(Defines.KEY_DATE, cursor.getLong(cursor
//                    .getColumnIndex(DatabaseHelper.FIELD_DATE)));
//            // Intent�Ăяo�������s
//            startActivity(intent);
        }
    }

    private void searchList() {
            // ���������f�[�^�����ƂɃA�_�v�^�[�𐶐�
//            DiaryAdapter adapter = new DiaryAdapter(this, mCursor);
//
//            // �A�_�v�^�[��ListView�ɐݒ�
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
