package net.dgcgame;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;


/*
 *  TODO: Completar delTextura
 *  TODO: Anadir IdtoName y visceversa
 */
public class Texturas 
{	
	public static Vector<IntBuffer> _indicesTexturas = new Vector<IntBuffer>();
	private static int numTexturas = 0;
	private static HashMap<Integer, Integer> indices_Id = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> id_Indices = new HashMap<Integer, Integer>();	
	
	public int indiceToId(int indice)
	{
		return indices_Id.get(indice);
	}
	
	public int idToIndice(int id)
	{
		return id_Indices.get(id);
	}
	
	public int addTextura(GL10 gl, Context context, int id) 
	{
		_indicesTexturas.add(IntBuffer.allocate(3));
		// loading texture
		
		//Log.v("dgcgame","Grid::loadTexture: Iniciando");
    	Bitmap texture = BitmapFactory.decodeResource(context.getResources(), id);
    	gl.glEnable(GL10.GL_TEXTURE_2D);
    	//Log.v("dgcgame","Grid::loadTexture: GL_TEXTURE_2D activado");
    	// generate one texture pointer
    	gl.glGenTextures(3, _indicesTexturas.elementAt(numTexturas));
    	//Log.v("dgcgame","Grid::loadTexture: Puntero generado");
    	
    	// setup texture 0
        gl.glBindTexture(GL10.GL_TEXTURE_2D, _indicesTexturas.elementAt(numTexturas).get(0));
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, texture, 0);
        //Log.v("dgcgame","Grid::loadTexture: Textura 0 creada");
        
        // setup texture 1
        gl.glBindTexture(GL10.GL_TEXTURE_2D, _indicesTexturas.elementAt(numTexturas).get(1));
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, texture, 0);
        //Log.v("dgcgame","Grid::loadTexture: Textura 1 creada");
        
        // setup texture 2
        gl.glBindTexture(GL10.GL_TEXTURE_2D, _indicesTexturas.elementAt(numTexturas).get(2));
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_NEAREST);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, texture, 0);
        
        Utils.generateMipmapsForBoundTexture(texture);
        //Log.v("dgcgame","Grid::loadTexture: Textura 2 creada");
    	texture.recycle();
    	indices_Id.put(numTexturas, id);
    	id_Indices.put(id, numTexturas);
    	numTexturas++;
    	return (numTexturas-1);
    }

	/*
	 * Problema: Como hace que al borrar una textura este cambio se propague
	 */
	public boolean delTextura(GL10 gl ,int Id)
	{		
		gl.glDeleteTextures(3, _indicesTexturas.elementAt(numTexturas));
		return true;
	}

}
