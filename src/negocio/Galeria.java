package negocio;

import java.awt.Image;
import java.util.ArrayList;

public class Galeria {
	
	private ArrayList<Image> imagenes;
	
	private ArrayList<String> nombres;
	
	private int posicionActual = 0;
	
	public Galeria() {
		imagenes = new ArrayList<Image>();
		nombres = new ArrayList<String>();
		posicionActual = 0;
	}
	
	public void addImagen(Image imagen, String nombre) {
		imagenes.add(imagen);
		nombres.add(nombre);
	}

	public int size(){
		return imagenes.size();
	}
	
	public Image getImagenSiguiente(){
		
		posicionActual++;
		if(posicionActual == size()){
			posicionActual = 0;
		}		
		return imagenes.get(posicionActual);		
	}
	
	public Image getImagenAnterior(){
		
		posicionActual--;
		if(posicionActual < 0){
			posicionActual = size() - 1;
		}		
		return imagenes.get(posicionActual);		
	}
	
	public Image getImagenActual(){
		
		if(posicionActual == size()){
			posicionActual = 0;
		}
		
		return imagenes.get(posicionActual);
	}
	
	
	public String getNombreImagenActual(){
		
		if(posicionActual == size()){
			posicionActual = 0;
		}
		
		return nombres.get(posicionActual);
	}
}