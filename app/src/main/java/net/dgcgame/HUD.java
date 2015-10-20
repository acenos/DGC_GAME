package net.dgcgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class HUD extends View implements OnTouchListener 
{
	public HUD(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
	}
	
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		//canvas.drawLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2, paint);
		//canvas.drawLine(canvas.getWidth()/2, 0, canvas.getWidth()/2,canvas.getHeight() , paint);
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
