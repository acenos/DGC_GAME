package net.dgcgame;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import 	android.graphics.Color;

class PaqueteTexturas
{
	short nTexturas;
	int texturas[];
}
@SuppressWarnings("unused")
public class DGC_GAMEActivity extends Activity 
{	
    private Game_View _vortexView;
	private HUD _HUD;
    
    private PaqueteTexturas bosque, mar, desierto, montanya, pradera, hierba;
    
    private PaqueteTexturas[] paquete;
    
    private boolean conexiones[][];
	private int nconexiones[];
 
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        _vortexView = (Game_View) findViewById(net.dgcgame.R.id.GlView);
        _HUD = (HUD) findViewById(net.dgcgame.R.id.HUD);
        final Button buttonleft = (Button) findViewById(R.id.buttonleft);
        final TextView finish = (TextView) findViewById(R.id.finish);
        buttonleft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish.setVisibility(4);
                _vortexView._renderer.Personajes[0].setSecuencia(1);
                _vortexView._renderer.movePlayer(0, -1, 0);
                int pos[] = _vortexView._renderer.Personajes[0].getPos();
                int pos2[] = _vortexView._renderer.Personajes[1].getPos();
                if(pos[0]==pos2[0]&&pos[1]==pos2[1])
                {
                	finish.setVisibility(0);
                	finish.setBackgroundColor(Color.BLACK);
                	finish.setTextColor(Color.WHITE);
                	finish.setText("HAS GANADO");
                	reset();
                }
            }
        });
        final Button buttonup = (Button) findViewById(R.id.buttonup);
        buttonup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish.setVisibility(4);
                _vortexView._renderer.Personajes[0].setSecuencia(2);
                _vortexView._renderer.movePlayer(0, 0, 1);
                int pos[] = _vortexView._renderer.Personajes[0].getPos();
                int pos2[] = _vortexView._renderer.Personajes[1].getPos();
                if(pos[0]==pos2[0]&&pos[1]==pos2[1])
                {
                	finish.setVisibility(0);
                	finish.setBackgroundColor(Color.BLACK);
                	finish.setTextColor(Color.WHITE);
                	finish.setText("HAS GANADO");
                	reset();
                }
            }
        });
        final Button buttondown = (Button) findViewById(R.id.buttondown);
        buttondown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish.setVisibility(4);
                _vortexView._renderer.Personajes[0].setSecuencia(4);
                _vortexView._renderer.movePlayer(0, 0, -1);
                int pos[] = _vortexView._renderer.Personajes[0].getPos();
                int pos2[] = _vortexView._renderer.Personajes[1].getPos();
                if(pos[0]==pos2[0]&&pos[1]==pos2[1])
                {
                	finish.setVisibility(0);
                	finish.setBackgroundColor(Color.BLACK);
                	finish.setTextColor(Color.WHITE);
                	finish.setText("HAS GANADO");
                	reset();
                }
            }
        });
        final Button buttonright = (Button) findViewById(R.id.buttonright);
        buttonright.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish.setVisibility(4);
                _vortexView._renderer.Personajes[0].setSecuencia(3);
                _vortexView._renderer.movePlayer(0, 1, 0);
                int pos[] = _vortexView._renderer.Personajes[0].getPos();
                int pos2[] = _vortexView._renderer.Personajes[1].getPos();
                if(pos[0]==pos2[0]&&pos[1]==pos2[1])
                {
                	finish.setVisibility(0);
                	finish.setBackgroundColor(Color.BLACK);
                	finish.setTextColor(Color.WHITE);
                	finish.setText("HAS GANADO");
                	reset();
                }
            }
        });
        final Button buttonrestart = (Button) findViewById(R.id.buttonrestart);
        buttonrestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	reset();
            }
        });
        //setContentView(_vortexView);
        
        setConexions();
        
        // Definimos las texturas del mapeado
        mar = new PaqueteTexturas();
        mar.texturas = new int[6];
        mar.texturas[0] = 0;
        mar.nTexturas = 1;

        bosque = new PaqueteTexturas();
        bosque.texturas = new int[5];
        bosque.texturas[0] = 1;
        bosque.nTexturas = 1;

        desierto = new PaqueteTexturas();
        desierto.texturas = new int[5];
        desierto.texturas[0] = 2;
        desierto.nTexturas = 1;

        montanya = new PaqueteTexturas();
        montanya.texturas = new int[5];
        montanya.texturas[0] = 3;
        montanya.nTexturas = 1;

        pradera = new PaqueteTexturas();
        pradera.texturas = new int[5];
        pradera.texturas[0] = 4;
        pradera.nTexturas = 1;

        hierba = new PaqueteTexturas();
        hierba.texturas = new int[5];
        hierba.texturas[0] = 5;
        hierba.nTexturas = 1;

        paquete = new PaqueteTexturas[6];
        
        paquete[0] = mar;
        paquete[1] = bosque;
        paquete[2] = desierto;
        paquete[3] = montanya;
        paquete[4] = pradera;
        paquete[5] = hierba;
        
        //setTrivialTextures();
        setTextures();
        
        // Definimos los personajes
        float posicion[] = 
        	{
        		1.0f, 2.0f, -1.0f,
        		1.0f, 0.0f, 0.0f,
        		0.0f, 1.0f, 0.0f,
        		1.0f, 2.0f, -1.0f,
        		2.0f, 1.0f, -1.0f,
        		1.0f, 0.0f, 0.0f
        	};
        float texturas[] = 
        	{
        		0, 0,
        		1, 1,
        		0, 1,
        		
        		0, 0,
        		1, 0,
        		1, 1,
        	};
        _vortexView._renderer.Personajes[0] = new Personaje(0, 0, "Heroe82", posicion, texturas, 5);
        _vortexView._renderer.setPlayerTexture(0, 6);
        // Movimiento hacia la izquierda
        _vortexView._renderer.Personajes[0].newSecuencia();
        _vortexView._renderer.Personajes[0].addTextura(1, 7);
        _vortexView._renderer.Personajes[0].addTextura(1, 8);
        // Movimiento hacia arriba
        _vortexView._renderer.Personajes[0].newSecuencia();
        _vortexView._renderer.Personajes[0].addTextura(2, 9);
        _vortexView._renderer.Personajes[0].addTextura(2, 10);
        // Movimiento hacia la derecha
        _vortexView._renderer.Personajes[0].newSecuencia();
        _vortexView._renderer.Personajes[0].addTextura(3, 11);
        _vortexView._renderer.Personajes[0].addTextura(3, 12);
        // Movimiento hacia abajo
        _vortexView._renderer.Personajes[0].newSecuencia();
        _vortexView._renderer.Personajes[0].addTextura(4, 13);
        _vortexView._renderer.Personajes[0].addTextura(4, 14);
        _vortexView._renderer.nPersonajes++;
        // Definimos el objetivo

        float posicion2[] = 
        	{
        		1.0f, 2.0f, -1.0f,
        		1.0f, 0.0f, 0.0f,
        		0.0f, 1.0f, 0.0f,
        		1.0f, 2.0f, -1.0f,
        		2.0f, 1.0f, -1.0f,
        		1.0f, 0.0f, 0.0f
        	};
        float texturas2[] = 
        	{
        		0, 0,
        		1, 1,
        		0, 1,
        		
        		0, 0,
        		1, 0,
        		1, 1,
        	};
        
        _vortexView._renderer.Personajes[1] = new Personaje(7, 5, "Target", posicion2, texturas2, 0);
        _vortexView._renderer.setPlayerTexture(1, 15);
        _vortexView._renderer.Personajes[1].addTextura(0, 16);
        _vortexView._renderer.Personajes[1].addTextura(0, 17);
        _vortexView._renderer.Personajes[1].addTextura(0, 16);
        _vortexView._renderer.nPersonajes++;
    }
    
    public void reset()
    {
    	int pos[] = _vortexView._renderer.Personajes[0].getPos();
    	_vortexView._renderer.Personajes[0].setPos(-pos[0], -pos[1], 10, 10);
        setTextures();
    }
    
    public void setConexions()
	{
		conexiones = new boolean[][]{
				{	// MAR
					true, true, true, true, true, true
				}, 
				{	// BOSQUE
					true, true, false, true, true, true
				},
				{	// DESIERTO
					true, false, true, true, true, false
				},
				{	// MONTAnA
					true, true, true, true, false, true
				},
				{	// PRADERA
					true, true, true, false, true, true
				},
				{	// HIERBA
					true, true, false, true, true, true
				}
		};
		nconexiones = new int[]{
			6, 5, 4, 5, 5, 5
		};
	}
    
    private void setTrivialTextures()
    {
    	int altura = _vortexView._renderer.getHeight();
    	int anchura = _vortexView._renderer.getWidth();
    	int contador= 0;
    	for(int i=0; i<altura; i++)
 		{
     		for(int j=0; j<anchura; j++)
     		{
     			int l = (contador++)%6;
 				_vortexView._renderer.setTexture(i, j, paquete[1].texturas[0]);
     			for(int k=1; k<paquete[l].nTexturas; k++)
     			{
     				_vortexView._renderer.addTexture(i, j, paquete[1].texturas[k]);
     			}
     		}
     		contador++;
 		}
    }
    
    
    private void setTextures()
    {
    	int altura = _vortexView._renderer.getHeight();
    	int anchura = _vortexView._renderer.getWidth();

	    //Log.i("dgcgame","HEY");
 		for(int i=0; i<altura; i++)
 		{
     		for(int j=0; j<anchura; j++)
     		{
				_vortexView._renderer.setSolid(j, i, false);
     	    	//Log.i("dgcgame","i: "+i+" j: "+j);
     			if(j==0)
     			{
     				if(i==0)
     				{
     					Random rand = new Random();
     					int random = rand.nextInt(8)%6;
     	     	    	//Log.v("dgcgame","Random: "+random);
     	 				_vortexView._renderer.setTexture(i, j, paquete[random].texturas[0]);
     	     			for(int k=1; k<paquete[random].nTexturas; k++)
     	     			{
     	     				_vortexView._renderer.addTexture(i, j, paquete[random].texturas[k]);
     	     			}
     					if(random==0)
     					{
     						_vortexView._renderer.setSolid(0, 0, true);
     					}
     					//Log.v("dgcgame","Textura asignada");
     				}
     				else
     				{
     					int k = (int) ((Math.random()*100)%nconexiones[_vortexView._renderer.getTexture(0, i-1)]);
     					//Log.v("dgcgame","_vortexView._renderer.getTexture(0, "+(i-1)+"): "+ _vortexView._renderer.getTexture(0, i-1));
     					int texture = getTextureValue(_vortexView._renderer.getTexture(0, i-1), k);
     	 				_vortexView._renderer.setTexture(i, j, paquete[texture].texturas[0]);
     	     			for(k=1; k<paquete[texture].nTexturas; k++)
     	     			{
     	     				_vortexView._renderer.addTexture(i, j, paquete[texture].texturas[k]);
     	     			}
     					if(texture == 0)
     					{
     						_vortexView._renderer.setSolid(j, i, true);
     					}
     				}
     			}
     			else
     			{
     				if(i==0)
     				{
     					int k = (int) ((Math.random()*100)%(nconexiones[_vortexView._renderer.getTexture(j-1, 0)]));
     					//Log.v("dgcgame","_vortexView._renderer.getTexture("+(i-1)+", 0): "+ _vortexView._renderer.getTexture(j-1, 0));
     					int texture = getTextureValue(_vortexView._renderer.getTexture(j-1, 0), k);
     	 				_vortexView._renderer.setTexture(i, j, paquete[texture].texturas[0]);
     	     			for(k=1; k<paquete[texture].nTexturas; k++)
     	     			{
     	     				_vortexView._renderer.addTexture(i, j, paquete[texture].texturas[k]);
     	     			}
     					if(texture == 0)
     					{
     						_vortexView._renderer.setSolid(j, i, true);
     					}
     				}
     				else
     				{
     					int[] posibles = getMatchingValues(_vortexView._renderer.getTexture(j-1, i), 
     													   _vortexView._renderer.getTexture(j, i-1));
     					//Log.v("dgcgame","_vortexView._renderer.getTexture("+(j-1)+", "+(i)+"): "+ _vortexView._renderer.getTexture(j-1, i));
     					//Log.v("dgcgame","_vortexView._renderer.getTexture("+(j)+", "+(i-1)+"): "+ _vortexView._renderer.getTexture(j, i-1));
     					if(posibles[0]!=-1)
     					{
     						int texture = (int) ((Math.random()*100)%posibles.length);
         	 				_vortexView._renderer.setTexture(i, j, paquete[texture].texturas[0]);
         	     			for(int k=1; k<paquete[texture].nTexturas; k++)
         	     			{
         	     				_vortexView._renderer.addTexture(i, j, paquete[texture].texturas[k]);
         	     			}
         					if(texture == 0)
         					{
         						_vortexView._renderer.setSolid(j, i, true);
         					}
     					}
     				}
     			}	
     		}
     	}
    }
	

    public int getTextureValue(int indice, int n)
    {
    	int i=0, j=0;
    	while(i<n)
    	{
    		if(conexiones[indice][j])
    			i++;
    		j++;
    	}
    	return i;
    }
    
    
    public int[] getMatchingValues(int indice1, int indice2)
    {
    	int[] vector = {-1, -1, -1, -1, -1};
    	int j=0;
    	for(int i=0; i<5; i++)
    	{
    		if(conexiones[indice1][i]&&conexiones[indice2][i])
    		{
    			vector[j] = i;
    			j++;
    		}
    	}
    	return vector;
    }
}