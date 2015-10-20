package net.dgcgame;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;

public class ObjetoDibujable {
	
	
	/*
	 *  Texturas estara compuesta por un conjunto de secuencias que a su vez estaran compuestas de fotogramas
	 *  Esto se usara para animaciones
	 */
	protected Vector<Vector<Integer>> Texturas = new Vector<Vector<Integer>>();
	protected int FotogramaActual = 0; 
	protected int SecuenciaActual = 0;
	/*
	 * Se anade una secuencia con un fotograma
	 */
	public void newSecuencia(int Fotograma)
	{
		Texturas.add(new Vector<Integer>(Fotograma));
	}
	
	public void newSecuencia()
	{
		Texturas.add(new Vector<Integer>());
	}
	
	/*
	 * Se anade un fotograma a una secuencia
	 */
	public void addTextura(int Secuencia, int Textura)
	{
		Texturas.elementAt(Secuencia).add(Textura);
	}

	public void addTextura(int Textura)
	{
		Texturas.elementAt(SecuenciaActual).add(Textura);
	}
	
	/*
	 * Cambia la textura de un Fotograma concreto
	 */
	public void setTexturas(int Secuencia, int Fotograma, int Textura)
	{
		Texturas.elementAt(Secuencia).set(Fotograma, Textura);
	}
	
	public void setTexturas(int Textura)
	{
		Texturas.elementAt(SecuenciaActual).set(FotogramaActual, Textura);
	}
	
	public void setSecuencia(int Secuencia)
	{
		if(Secuencia < Texturas.size())
			SecuenciaActual = Secuencia;
		else
			SecuenciaActual = Texturas.size()-1;
	}
	
	public void setFotograma(int Fotograma)
	{
		if(Fotograma < Texturas.elementAt(SecuenciaActual).size())
			FotogramaActual = Fotograma;
		else
			FotogramaActual = Texturas.elementAt(SecuenciaActual).size()-1;
	}
	
	public int texturaActual()
	{
		return Texturas.elementAt(SecuenciaActual).elementAt(FotogramaActual);
	}
	
	/*
	 * Se avanza en la secuencia de animacion
	 */
	public void refresh()
	{	
		FotogramaActual = (FotogramaActual+1)%Texturas.elementAt(SecuenciaActual).size();
	}
	
	/*
	 * Se dibuja el objeto
	 */
	public void draw(GL10 GL){
		
	}
}
