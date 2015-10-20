package net.dgcgame;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

public class Casilla extends ObjetoDibujable{
	
	//public int Textura = 0;						// Indice en el buffer de texturas de la textura deseada
	private FloatBuffer _vertexBuffer;			// Buffer con los vertices que forman las figuras
	private ShortBuffer _indicesBuffer;			// Buffer con los indices de los vertices para formar las aristas (Ya no es necesario, solo vale si se usa DrawElements)
	private FloatBuffer _textureIndicesBuffer;	// Buffer de las coordenadas de las texturas
	public float[] Centro;						// Coordenadas del centro de la figura
	public boolean solid;
	
	/*
	 *  Calculara el centro de "figura", que sera un vector de coordenadas
	 *  Usado en la creacion de la casilla
	 */
	public float[] calcularCentro(float[] figura)
    {
    	float resultado[] = new float[]
    	{ 
    		0, 0, 0,
    	};
    	for (int i = 0; i < figura.length; i++)
    	{
    		resultado[i%3] += figura[i];
    	}
    	resultado[0] /= (figura.length/3.0);
    	resultado[1] /= (figura.length/3.0);
    	resultado[2] /= (figura.length/3.0);
    	return resultado;
    }
	
	/*
	 *  Se alojaran las coordenadas de los vertices y las texturas en buffers y se iniciara indiceTextura
	 */
	 
	public Casilla(float[] Vertices, short[] Indices, float[] Texturas, int IndiceTextura, boolean solido)
	{
		solid = solido;
		Centro = calcularCentro(Vertices);
		ByteBuffer vbb = ByteBuffer.allocateDirect(4*Vertices.length);
	    vbb.order(ByteOrder.nativeOrder());
	    _vertexBuffer = vbb.asFloatBuffer();
	    _vertexBuffer.put(Vertices);
	    
	    vbb =  ByteBuffer.allocateDirect(4*Texturas.length);
	    vbb.order(ByteOrder.nativeOrder());
	    _textureIndicesBuffer = vbb.asFloatBuffer();
	    _textureIndicesBuffer.put(Texturas);
	    _textureIndicesBuffer.position(0);
	    
	    vbb =  ByteBuffer.allocateDirect(2*Indices.length);
	    vbb.order(ByteOrder.nativeOrder());
	    _indicesBuffer = vbb.asShortBuffer();
	    _indicesBuffer.put(Indices);

	    this.Texturas.add(new Vector<Integer>());
	    this.Texturas.elementAt(0).add(IndiceTextura);
	}
	
	/*
	 * Se dibujara la casilla almacenada usando los buffers anteriormente creados con glDrawArray
	 * Es necesario que se haya activado el buffer de textura antes de ejecutar esta funcion.
	 * 
	 */

	public void draw(GL10 gl)
	{ 
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); 
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
        // Se rebovinan los buffers para evitar comportamientos anomalos.
		_vertexBuffer.rewind();
        _textureIndicesBuffer.rewind();
        _indicesBuffer.rewind();
        //_vertexBuffer.position(9);
        //_textureIndicesBuffer.position(6);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, _vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, _textureIndicesBuffer);
	
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 6);
        
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
	}

}
