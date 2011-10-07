package jp.android.pinch;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;;

public class ScalableView extends ImageView implements OnTouchListener{
	
	private static final String TAG = "ScalableView";
	private static final float MAX_SCALE  = 5;
	private static final float MIN_SCALE  = 0.3f;
	private static final float MIN_LENGTH = 30f;
	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	
	/** Matrix��getValues�p*/
	private float[] values    = new float[9];
	/** �h���b�O�pMatrix*/
	private Matrix moveMatrix = new Matrix();
	/** Matrix*/
	private Matrix matrix     = new Matrix();
	/** �摜�ړ��p�̈ʒu*/
	private PointF point      = new PointF();
	/** �Y�[�����̍��W*/
	private PointF middle     = new PointF();
	/** �^�b�`���[�h�B�����Ȃ�*/
	private int mode          = NONE;
	/** Zoom�J�n���̂Q�_�ԋ���*/
	private float initLength  = 1;
	

	public ScalableView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScalableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		matrix = new Matrix();
		matrix.setScale(1, 1);
		setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		ImageView imgView = (ImageView)view;
		
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			Log.d(TAG, "mode = DRAG");
			
			mode = DRAG;
			point.set(event.getX(), event.getY());
			moveMatrix.set(matrix);
			break;
		case MotionEvent.ACTION_POINTER_2_UP:
		case MotionEvent.ACTION_UP:
			Log.d(TAG,"mode = NONE");
			
			mode = NONE;
			break;
		case MotionEvent.ACTION_POINTER_2_DOWN:
			initLength = getLength(event);
			if(initLength > MIN_LENGTH){
				Log.d(TAG, "mode = ZOOM");
				
				moveMatrix.set(matrix);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			switch (mode){
			case DRAG:
				matrix.set(moveMatrix);
				matrix.postTranslate(event.getX() - point.x, event.getY() - point.y);
				imgView.setImageMatrix(matrix);
				break;
			case ZOOM:
				if(mode == ZOOM){
					float currentLength = getLength(event);
					middle = getMiddle(event, middle);
					
					if(currentLength > MIN_LENGTH){
						matrix.set(moveMatrix);
						float scale = filter(matrix, currentLength / initLength);
						matrix.postScale(scale, scale, middle.x, middle.y);
						imgView.setImageMatrix(matrix);
					}
					break;
				}
				break;
			}
		}
		return false;
	}

	/**
	 * �g��k������
	 */
	private float filter(Matrix m, float s){
		m.getValues(values);
		float nextScale = values[0] * s;
		
		if(nextScale > MAX_SCALE){
			s = MAX_SCALE / values[0];
		} else if(nextScale < MIN_SCALE){
			s = MIN_SCALE / values[0];
		}
		return s;
	}
	
	/**
	 * ��r���v�Z
	 */
	private float getLength(MotionEvent e){
		float xx = e.getX(1) - e.getX(0);
		float yy = e.getY(1) - e.getY(0);
		return FloatMath.sqrt(xx * xx + yy * yy);
	}
	
	/**
	 * ���ԓ_�����߂�
	 */
	private PointF getMiddle(MotionEvent e, PointF p){
		float x = e.getX(0) + e.getX(1);
		float y = e.getY(0) + e.getY(1);
		p.set(x / 2, y / 2);
		return p;
	}
}
