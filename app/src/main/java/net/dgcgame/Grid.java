package net.dgcgame;

import javax.microedition.khronos.opengles.GL10;
import android.util.Log;

public class Grid 
{
	private int filtro = 0; // Proximidad de textura
	public Casilla[][] Casillas;
	public int altura, anchura;
    float coords[];
    float Centro[];
    
    private final static float iTexturas[][] = new float[][] 
	{
		{0, 0},
		{1, 0},
		{0, 1},
		{1, 1},
	};
    
    public float[] calcularCentro()
    {
    	float resultado[] = new float[]
    	    	{ 
    	    		0, 0, 0,
    	    	};
    	for(int i=0; i<altura; i++)
    	{
    		for(int j=0; j<anchura; j++)
    		{
    			resultado[0] += Casillas[i][j].Centro[0];
    			resultado[1] += Casillas[i][j].Centro[1];
    			resultado[2] += Casillas[i][j].Centro[2];
    		}
    	}
    	resultado[0] /= (anchura*altura);
    	resultado[1] /= (anchura*altura);
    	resultado[2] /= (anchura*altura);
    	return resultado;
    }
    
    /*
     *  Grid contendra H x W casillas. El minimo debe ser 1 x 1, si no se cumple se arreglara en vez de lanzar un error
     */
	public Grid(int h, int w)
    {
		if (h > 0)
		{
			altura = h;
		}
		else 
		{
			altura = 1;
		}
		if (w > 0)
		{
			anchura = w;
		}
		else
		{
			anchura = 1;
		}
		
		Casillas = new Casilla[h][w];
		coords = new float[(altura+1)*(anchura+1)*3];
    	for(int i=0; i<=altura; i++)
    	{
    		for(int j=0; j<=anchura; j++)
    		{
	    		coords[(i*(anchura+1)+j)*3]   = j;
	    		coords[(i*(anchura+1)+j)*3+1] = i;
	    		coords[(i*(anchura+1)+j)*3+2] = 0;
    		}
    	}
		String temp2 = "[";
		for(int k = 0; k<coords.length; k++) 
		{
			temp2 += coords[k];
			if(k == coords.length-1) temp2 +="]";
			else if(k%3 == 2)
			{
				temp2 +="][";
			}
			else
			{
				temp2 +=", ";
			}
		}
		//Log.v("dgcgame","Grid::Iniciando Coordenadas -> : "+ temp2);
	    float[] vertex = new float[18];
	    short[] indices = new short[7];
	    float[] indexTex = new float[12];
	    Casillas = new Casilla[altura][anchura];
	    
	    /* 	  Teoria      Pantalla
	     *    3---2 6    2 6---5
	     *    |  / /|    |\\   |
	     *    | / / |    | \\  |
	     *    |/ /  |    |  \\ |
	     *    1 4---5	 3---1 4
	     */
    	for(int i=0; i<altura; i++)
    	{
    		for(int j=0; j<anchura; j++)
    		{
    			indices[0]		= (short)((anchura+1)*i+j);
    			indices[1]		= (short)((anchura+1)*i+j+1);
    			indices[2]		= (short)((anchura+1)*(i+1)+j);
    			indices[3]		= (short)((anchura+1)*(i+1)+j);
    			indices[4]		= (short)((anchura+1)*i+j+1);
    			indices[5]		= (short)((anchura+1)*(i+1)+j+1);
    			
    			// Vertice 1
    			vertex[0] 		= coords[((anchura+1)*(i+1)+j)*3];
    			vertex[1] 		= coords[((anchura+1)*(i+1)+j)*3+1];
    			vertex[2] 		= coords[((anchura+1)*(i+1)+j)*3+2];
    			
    			// Vertice 2
    			vertex[3]		= coords[((anchura+1)*i+(j+1))*3];
    			vertex[4]		= coords[((anchura+1)*i+(j+1))*3+1];
    			vertex[5]		= coords[((anchura+1)*i+(j+1))*3+2];
    			
    			// Vertice 3
    			vertex[6]		= coords[((anchura+1)*i+j)*3];
    			vertex[7]		= coords[((anchura+1)*i+j)*3+1];
    			vertex[8]		= coords[((anchura+1)*i+j)*3+2];
    			
    			// Vertice 4
    			vertex[9]		= vertex[0];
    			vertex[10]		= vertex[1];
    			vertex[11]		= vertex[2];
    			
    			// Vertice 5
    			vertex[12]		= coords[((anchura+1)*(i+1)+(j+1))*3];
    			vertex[13]		= coords[((anchura+1)*(i+1)+(j+1))*3+1];
    			vertex[14]		= coords[((anchura+1)*(i+1)+(j+1))*3+2];
    			
    			// Vertice 6
    			vertex[15]		= vertex[3];
    			vertex[16]		= vertex[4];
    			vertex[17]		= vertex[5];
    			/*
    			indexTex[0] 	= iTexturas[0];
    			indexTex[1] 	= iTexturas[1];
    			indexTex[2] 	= iTexturas[2];
    			indexTex[3] 	= iTexturas[3];
    			*/
    			/*
    			indexTex[0] 	= iTexturas[1];
    			indexTex[1] 	= iTexturas[2];
    			indexTex[2] 	= iTexturas[0];
    			indexTex[3] 	= iTexturas[0];
    			indexTex[4] 	= iTexturas[2];
    			indexTex[5] 	= iTexturas[3];
    			*/

    			indexTex[0] 	= iTexturas[0][0];
    			indexTex[1] 	= iTexturas[0][1];
    			indexTex[2] 	= iTexturas[3][0];
    			indexTex[3] 	= iTexturas[3][1];
    			indexTex[4] 	= iTexturas[2][0];
    			indexTex[5] 	= iTexturas[2][1];
    			
    			indexTex[6] 	= iTexturas[0][0];
    			indexTex[7] 	= iTexturas[0][1];
    			indexTex[8] 	= iTexturas[1][0];
    			indexTex[9] 	= iTexturas[1][1];
    			indexTex[10] 	= iTexturas[3][0];
    			indexTex[11] 	= iTexturas[3][1];
    			
    			String temp = "[";
    			for(int k = 0; k<vertex.length; k++) 
    			{
    				
    				if((k%3)==0)
    					temp += "][";
    				else 
    					temp += ", ";
    				temp += vertex[k];
    			}
    			//Log.v("dgcgame","Grid::Iniciando Casilla["+i+"]["+j+"] Coordenadas -> : "+ temp);
    			
    			//indices[6] = 0;
    			Casillas[i][j] = new Casilla(vertex,indices,indexTex,0, false);
    			
    		}
    		
    	}
    	Centro = calcularCentro();
    	//Log.v("dgcgame","Grid::Centro ["+Centro[0]+"],["+Centro[1]+"],["+Centro[2]+"]");
    	
    }
	
	public void refresh()
	{
		for(int i=0; i<altura; i++)
		{
			for(int j=0; j<anchura; j++)
				Casillas[i][j].refresh();
		}
	}
	
	public void draw(GL10 gl)
	{
		for(int i=0; i<altura; i++)
    	{			
    		for(int j=0; j<anchura; j++)
    		{
    			Texturas._indicesTexturas.elementAt(Casillas[i][j].texturaActual()).rewind();
    			gl.glBindTexture(GL10.GL_TEXTURE_2D, Texturas._indicesTexturas.elementAt(Casillas[i][j].texturaActual()).get(filtro));
    			Casillas[i][j].draw(gl);
    		}
    	}
	}
}
