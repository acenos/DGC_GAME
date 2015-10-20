package net.dgcgame;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class Game_View extends GLSurfaceView {
   
	public Game_Renderer _renderer;
	//private GRenderer _renderer;
    private float _x = 0;
    private float _y = 0;
    float distanciaAnterior = 0;
    // Esto hace la magia, para poder ser invocado desde el layaout tiene que haber un constructor con Context y AttributeSet y pasarlo a super
    public Game_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		setDebugFlags(DEBUG_CHECK_GL_ERROR);
    	_renderer = new Game_Renderer(context, 10, 10, 10);
    	setEGLConfigChooser(false);
        setRenderer(_renderer);
	}
   /* 
    public Game_View(Context context, int anchura, int altura, int npersonajes) 
    {
        super(context);
        setDebugFlags(DEBUG_CHECK_GL_ERROR);
        _renderer = new Game_Renderer(context, anchura, altura, npersonajes);
        //_renderer = new GRenderer(context);
        setEGLConfigChooser(false);
        setRenderer(_renderer);
    }
    */
    public boolean onTouchEvent(final MotionEvent event) 
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN) 
        {
        	if (event.getPointerCount() == 1) 
        	{
        		//Log.v("dgcgame","Game_View::Dedo detectado");
	            _x = event.getX() * event.getXPrecision();
	            _y = event.getY() * event.getYPrecision();
        	}
        }
        
        else if (event.getAction() == MotionEvent.ACTION_UP)
        {
        	//Log.v("dgcgame","Game_View::Dedo levantado");
        	//distanciaAnterior = 0;
        }
    	if (event.getPointerCount() == 2) 
    	{
			final float distancia = (float) Math.sqrt(Math.pow((_x - event.getX(1) * event.getXPrecision()) , 2) + Math.pow((_y - event.getY(1) * event.getYPrecision()) , 2))/1000;
			if(distanciaAnterior == 0) distanciaAnterior = distancia;
			Log.v("dgcgame","Game_View::Evento dos dedos, resize: "+(_renderer.getResize()+distancia-distanciaAnterior)+": D "+distancia+",DA "+distanciaAnterior +", D-DA "+ (distancia-distanciaAnterior));
			queueEvent(new Runnable() 
			{
				public void run() 
	            {
	               	_renderer.setResize(_renderer.getResize()+distancia-distanciaAnterior);
	            }
	        });
			distanciaAnterior = distancia;
		} 
		else
		{
			distanciaAnterior = 0;
            final float xdiff = (_x - event.getX()* event.getXPrecision());
            final float ydiff = (_y - event.getY()* event.getYPrecision());
            //Log.v("dgcgame","Game_View::Evento un dedo: " +xdiff+", "+ydiff);
            queueEvent(new Runnable() {
                public void run() {
                    _renderer.setX(_renderer.getX() - xdiff/2000);
                    _renderer.setY(_renderer.getY() + ydiff/2000);
                }
            });
  		}
        
        return true;
    } 
}
