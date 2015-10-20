package net.dgcgame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class Game_Renderer implements GLSurfaceView.Renderer 
{
	// Datos basicos del mapa
	private int anchura = 20;
	private int altura = 20;
	private float resize = 0.5f;
	private float x = 0;
	private float y = 0;
	// Tamano de la pantalla
    private float _width = 480f;
    private float _height = 640f;
    public Texturas _Texturas = new Texturas();
    private Context context;
    private Grid grid;    
    public Personaje Personajes[];
    public int nPersonajes = 0;
    private int contador = 0;
    private int mooving = 0;
    
    public Game_Renderer(Context context, int width, int height, int npersonajes)
    {
    	this.context = context;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	Display display = wm.getDefaultDisplay();
    	_width = display.getWidth();  // deprecated
    	_height = display.getHeight();  // deprecated
    	//Log.i("dgcgame","Game_Renderer::Iniciado Width="+_width+ " Height="+_height);
    	anchura = width;
    	altura = height;
    	grid = new Grid(altura, anchura);
    	Personajes = new Personaje[npersonajes];
    }
        
  //@Override
  	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
  	{
  		 gl.glMatrixMode(GL10.GL_PROJECTION); 	     
  	     float ratio = _width / _height;
  	     // Proyeccion ortografica:
  	     gl.glOrthof(-1, 1, -1 / ratio, 1 / ratio, -100f, 100.0f);
  	     gl.glViewport(0, 0, (int) _width, (int) _height);
  	     gl.glMatrixMode(GL10.GL_MODELVIEW);
  	     gl.glEnable(GL10.GL_DEPTH_TEST);
  	     gl.glEnable(GL10.GL_TEXTURE_2D);
  	     // ponemos el color del fondo
  	     gl.glClearColor(0f, 0.5f, 0.5f, 1.0f);     
  	     // desactivamos la vision de las caras traceras
  	     gl.glDisable(GL10.GL_CULL_FACE);
  	     // definimos la cara que se mostrara
  	     gl.glFrontFace(GL10.GL_CCW);
  	     // definimos el lado trasero
  	     gl.glCullFace(GL10.GL_BACK);
  	     gl.glEnable(GL10.GL_BLEND );
  	     gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA );
  	     //setConexions();
  	     _Texturas.addTextura(gl, context, R.drawable.agua);
  	     _Texturas.addTextura(gl, context, R.drawable.bosque2);
  	     _Texturas.addTextura(gl, context, R.drawable.desierto);
  	     _Texturas.addTextura(gl, context, R.drawable.montanya);
  	     _Texturas.addTextura(gl, context, R.drawable.pradera);
  	     _Texturas.addTextura(gl, context, R.drawable.hierba);
  	     _Texturas.addTextura(gl, context, R.drawable.magus);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovleft1);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovleft2);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovup1);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovup2);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovright1);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovright2);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovdown1);
  	     _Texturas.addTextura(gl, context, R.drawable.magusmovdown2);
  	     _Texturas.addTextura(gl, context, R.drawable.carrot1);
  	     _Texturas.addTextura(gl, context, R.drawable.carrot2);
  	     _Texturas.addTextura(gl, context, R.drawable.carrot3);
  	}
  	
    public void setTexture(int x, int y, int textura)
    {
    	grid.Casillas[x][y].setTexturas(textura);
    }
    
    public void setPlayerTexture(int x, int textura)
    {
    	Personajes[x].setTexturas(textura);
    }
    
    public void setSolid(int x, int y, boolean solid)
    {
    	grid.Casillas[x][y].solid = solid;
    }
  	
    public void addTexture(int x, int y, int textura)
    {
    	grid.Casillas[x][y].addTextura(textura);
    }
  	
    public void addPlayerTexture(int x, int textura)
    {
    	Personajes[x].addTextura(textura);
    }
  	
    public void movePlayer(int i, int x, int y)
    {
    	int[] pos = Personajes[i].getPos();
    	if((pos[0]+x<0)||(pos[0]+x>anchura)||(pos[1]+y<0)||(pos[1]+y>altura))
    		mooving = 1;
    	else
    	{
        	//Log.i("dgcgame","Game_Renderer::movePlayer "+i+" pos["+(pos[0]+x)+ "]["+(pos[1]+y)+"] Solid="+(grid.Casillas[pos[0]+x][pos[1]+y].solid));       
	    	if(!(grid.Casillas[pos[0]+x][pos[1]+y].solid))
	    	{
	    		Personajes[i].setPos(x, y, altura+1, anchura+1);
	    		mooving = 10;
	    	}
	    	else
	    	{
	    		mooving = 1;
	    	}
    	}
    }
    
    public int getTexture(int x, int y)
    {
    	return grid.Casillas[x][y].texturaActual();
    }
  	
    //@Override
    public void onDrawFrame(GL10 gl) 
    {
    	GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
    	contador++;
    	if(contador==10)
    	{
    		grid.refresh();
    		for(int i=0; i<nPersonajes; i++)
            {
            	Personajes[i].refresh();
            }
    		contador = 0;
    	}
    	if(mooving > 1)
    	{
    		mooving--;
    	}
    	else
    	{
    		if(mooving == 1)
    		{
    			Personajes[0].setSecuencia(0);
    			Personajes[0].setFotograma(0);
    			mooving = 0;
    		}
    	}
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        //gl.glTranslatef(-grid.Centro[0],-grid.Centro[1],-grid.Centro[2]);
        gl.glScalef(resize, resize, 1);
        //gl.glTranslatef(grid.Centro[0],grid.Centro[1],grid.Centro[2]);
        gl.glTranslatef(x, y, 0);
        //gl.glTranslatef(0, 0, -5);
        gl.glScalef(0.75f, 0.75f, 0.75f);
        gl.glRotatef(65, 1f, 0f, 0f);
        gl.glRotatef(45, 0f, 0f, 1f);
        gl.glTranslatef(0, 0, -2);
        //gl.glScalef(0.05f, 0.05f, 1f);
        //gl.glRotatef(xRot++, 1f, 0f, 0f);
        grid.draw(gl);  
        for(int i=0; i<nPersonajes; i++)
        {
        	gl.glPushMatrix();
        	Texturas._indicesTexturas.elementAt(Personajes[i].texturaActual()).rewind();
        	gl.glBindTexture(GL10.GL_TEXTURE_2D, Texturas._indicesTexturas.elementAt(Personajes[i].texturaActual()).get(0));
        	Personajes[i].draw(gl);
        	gl.glPopMatrix();
        }
    }
    
   // @Override
    public void onSurfaceChanged(GL10 gl, int w, int h)
    {
    	_width = w;
        _height = h;
    	//Log.i("dgcgame","Game_Renderer::onSurfaceChanged Width="+_width+ " Height="+_height);       
        gl.glViewport(0, 0, w, h);
    }
    
    public float getX() 
    {
        return x;
    }

    public float getY() 
    {
        return y;
    }

    public void setX(float x0) 
    {
        x = x0;
    }

    public void setY(float y0) 
    {
        y = y0;
    }
    
    public void setConext(Context contexto)
    {
    	context = contexto;
    }
    public float getResize() 
    {
        return resize;
    }

    public void setResize(float R) 
    {
        resize = R;
    }

    public int getHeight() 
    {
        return altura;
    }

    public int getWidth() 
    {
        return anchura;
    }
    
}
