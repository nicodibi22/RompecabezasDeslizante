package negocio;

import java.util.Random;

public class RompecabezasDeslizante {

	public enum Direccion {
		ARRIBA, ABAJO, DERECHA, IZQUIERDA
	}
	
	private Tablero tableroJuego;
	
	private int movimientos;
	
	/**
	 * Arma un Rompecabezas Deslizantes de tamaño alto x ancho.
	 * Cada pieza del rompecabezas corresponde a un número del 1 hasta cantidad de elementos.
	 * La única pieza que se mueve es la mayor.
	 * @param alto
	 * @param ancho
	 * @throws Exception
	 */
	public RompecabezasDeslizante(int alto, int ancho) throws Exception {
		
		tableroJuego = new Tablero(alto, ancho);		
		desordenarPiezas();
		movimientos = 0;
	}
	
	public Integer size() {
		return tableroJuego.size();
	}
	
	/**
	 * Mueve la pieza con valor mayor para la dirección especificada.
	 * @param direccion: ARRIBA - ABAJO - DERECHA - IZQUIERDA
	 */
	public void mover(Direccion direccion) {

		boolean huboMovimiento;
		
		if (direccion == Direccion.IZQUIERDA) {
			huboMovimiento = tableroJuego.moverIzquierda();
		} else if (direccion == Direccion.DERECHA) {
			huboMovimiento = tableroJuego.moverDerecha();
		} else if (direccion == Direccion.ARRIBA) {
			huboMovimiento = tableroJuego.moverArriba();
		} else {
			huboMovimiento = tableroJuego.moverAbajo();
		}

		if(huboMovimiento){
			movimientos++;
		}
	}
	
	/**
	 * Obtiene la cantidad de movimientos que se utilizaron en la partida actual.
	 * @return Total de movimientos de la partida actual.
	 */
	public Integer getCantidadMovimientos() {
		
		return movimientos;
	}
		
	private void desordenarPiezas() {

		Random numAleatorio = new Random();
		Direccion direccion;
		for (int i = 0; i < 500; i++) {

			int num = numAleatorio.nextInt(4);

			switch (num) {
			case 0:
				direccion = Direccion.IZQUIERDA;
				break;
			case 1:
				direccion = Direccion.DERECHA;
				break;
			case 2:
				direccion = Direccion.ARRIBA;
				break;
			default:
				direccion = Direccion.ABAJO;
				break;
			}

			mover(direccion);
		}

	}
	
	/**
	 * Verifica si el Rompecabezas se encuentra resuelto o no.
	 * @return 
	 * true: si el Rompecabezas está resuelto.
	 * false: si el Rompecabezas no está resuelto.
	 */
	public boolean gano() {
		
		return tableroJuego.estaOrdenado();
	}
	
	/**
	 * Obtiene el valor de la pieza que se le indicó cómo parámetro.
	 * @param posicion
	 * @return: El valor de la pieza que se indicó la posición.
	 * @throws IllegalArgumentException: La posicion debe encontrarse enter 0 
	 * y la cantidad de piezas del rompecabezas - 1
	 */
	public int getPieza(int posicion) throws IllegalArgumentException {

		if( posicion < 0 || posicion >= size()) {
			throw new IllegalArgumentException("Se ingresó un número de posición erróneo. posicion = " + posicion);
		}
		
		return tableroJuego.getValor(posicion);
	}
}
