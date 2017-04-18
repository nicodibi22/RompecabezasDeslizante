package negocio;

import java.util.Random;

public class RompecabezasDeslizante {

	public enum Direccion {
		ARRIBA, ABAJO, DERECHA, IZQUIERDA
	}
	
	private Tablero tableroJuego;
	
	private int movimientos;
	
	/**
	 * Arma un Rompecabezas Deslizantes de tama�o alto x ancho.
	 * Cada pieza del rompecabezas corresponde a un n�mero del 1 hasta cantidad de elementos.
	 * La �nica pieza que se mueve es la mayor.
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
	 * Mueve la pieza con valor mayor para la direcci�n especificada.
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
	 * true: si el Rompecabezas est� resuelto.
	 * false: si el Rompecabezas no est� resuelto.
	 */
	public boolean gano() {
		
		return tableroJuego.estaOrdenado();
	}
	
	/**
	 * Obtiene el valor de la pieza que se le indic� c�mo par�metro.
	 * @param posicion
	 * @return: El valor de la pieza que se indic� la posici�n.
	 * @throws IllegalArgumentException: La posicion debe encontrarse enter 0 
	 * y la cantidad de piezas del rompecabezas - 1
	 */
	public int getPieza(int posicion) throws IllegalArgumentException {

		if( posicion < 0 || posicion >= size()) {
			throw new IllegalArgumentException("Se ingres� un n�mero de posici�n err�neo. posicion = " + posicion);
		}
		
		return tableroJuego.getValor(posicion);
	}
}
