package net.dgcgame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

public class Personaje extends ObjetoDibujable 
{
	private String nombre; 
	private FloatBuffer _vertexBuffer;			// Buffer con los vertices que forma la figura
	private FloatBuffer _textureIndicesBuffer;	// Buffer de las coordenadas de las texturas
	private int pos[];
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * TODO: Incluir mas caracteristicas del personaje, desde atributos hasta propiedades. Esto  *
	 * 		dependera del juego.
	 * 		 Mejorar sistema de movimiento																 *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	public Personaje(int x, int y, String nomine, float[] Vertices, float[] Texturas, int IndiceTextura)
	{
		pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		setName(nomine);
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(4*Vertices.length);
	    vbb.order(ByteOrder.nativeOrder());
	    _vertexBuffer = vbb.asFloatBuffer();
	    _vertexBuffer.put(Vertices);
	    
	    vbb =  ByteBuffer.allocateDirect(4*Texturas.length);
	    vbb.order(ByteOrder.nativeOrder());
	    _textureIndicesBuffer = vbb.asFloatBuffer();
	    _textureIndicesBuffer.put(Texturas);
	    _textureIndicesBuffer.position(0);

	    this.Texturas.add(new Vector<Integer>());
	    this.Texturas.elementAt(0).add(IndiceTextura);
	}
	
	public void setPos(int x, int y, int altura, int anchura)
	{
		pos[0] += x;
		pos[1] += y;
		if(pos[0]>anchura+1)
			pos[0] = anchura+1;
		if(pos[0]<0)
			pos[0] = 0;
		if(pos[1]>altura+1)
			pos[1] = altura+1;
		if(pos[1]<0)
			pos[1] = 0;
	}
	
	public int[] getPos()
	{
		return pos;
	}
	
	public void setName(String nomine)
	{
		nombre = nomine;
	}
	
	public String getName()
	{
		return nombre;
	}
	
	/*
	 * Se dibujara la casilla almacenada usando los buffers anteriormente creados con glDrawArray
	 * Es necesario que se haya activado el buffer de textura antes de ejecutar esta funcion.
	 * 
	 */

	public void draw(GL10 gl)
	{ 
        //gl.glTranslatef(pos[0]*(float)Math.sqrt(2)/2, pos[1]*(float)Math.sqrt(2)/2, 0);
		gl.glTranslatef(pos[0], pos[1], 0);  
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); 
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
        // Se rebovinan los buffers para evitar comportamientos anomalos.
		_vertexBuffer.rewind();
        _textureIndicesBuffer.rewind();
        //_vertexBuffer.position(9);
        //_textureIndicesBuffer.position(6);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, _vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, _textureIndicesBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}	
}
