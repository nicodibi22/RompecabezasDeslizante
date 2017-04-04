package negocio;

import java.util.Random;

public class Tablero {

	public enum Direccion {
		ARRIBA, ABAJO, DERECHA, IZQUIERDA
	}

	private Celda[] casillasTablero;

	private int tamanio;

	private int cantElementos;

	private int posicionActual;

	private int ubicacionPivoteFila;

	private int ubicacionPivoteColumna;

	private int movimientos;

	public Tablero(int tamanio) throws Exception {
		if (tamanio <= 0) {
			throw new Exception("El tablero debe contener al menos 1 casillero");
		}

		// Inicializa las variables
		this.tamanio = tamanio;
		this.cantElementos = tamanio * tamanio;
		ubicacionPivoteFila = this.tamanio - 1;
		ubicacionPivoteColumna = this.tamanio - 1;
		casillasTablero = new Celda[this.cantElementos];

		for (int i = 0; i < this.cantElementos; i++) {
			casillasTablero[i] = new Celda(i + 1);
		}

		desordenarCasillas();
		irInicio();
		movimientos = 0;

	}

	public int size() {
		return cantElementos;
	}

	public void mover(Direccion direccion) {

		if (direccion == Direccion.IZQUIERDA) {
			moverIzquierda();
		} else if (direccion == Direccion.DERECHA) {
			moverDerecha();
		} else if (direccion == Direccion.ARRIBA) {
			moverArriba();
		} else {
			moverAbajo();
		}

	}

	public void irInicio() {
		
		posicionActual = 0;
	}

	public Integer getProximoValor() throws NullPointerException {

		if (posicionActual >= cantElementos) {
			posicionActual = 0;
			throw new NullPointerException();
		}

		int valor = casillasTablero[posicionActual].getValor();
		posicionActual++;
		return valor;

	}

	public Integer getMovimientos() {
		
		return movimientos;
	}

	public boolean gano() {
		
		return estaOrdenado();
	}

	private void moverIzquierda() {
		
		if (ubicacionPivoteColumna > 0) {
			moverCelda(-1, 0);
			ubicacionPivoteColumna--;
		}
	}

	private void moverDerecha() {

		if (ubicacionPivoteColumna < this.tamanio - 1) {
			moverCelda(1, 0);
			ubicacionPivoteColumna++;
		}
	}

	private void moverArriba() {
		
		if (ubicacionPivoteFila > 0) {
			moverCelda(0, -1);
			ubicacionPivoteFila--;
		}
	}

	private void moverAbajo() {
		
		if (ubicacionPivoteFila < this.tamanio - 1) {
			moverCelda(0, 1);
			ubicacionPivoteFila++;

		}
	}

	private void moverCelda(int x, int y) {
		
		Celda celdaAux = new Celda(casillasTablero[(ubicacionPivoteFila + y)
				* this.tamanio + ubicacionPivoteColumna + x].getValor());

		casillasTablero[(ubicacionPivoteFila + y) * this.tamanio
				+ ubicacionPivoteColumna + x] = casillasTablero[ubicacionPivoteFila
				* this.tamanio + ubicacionPivoteColumna];
		casillasTablero[ubicacionPivoteFila * this.tamanio
				+ ubicacionPivoteColumna] = celdaAux;

		movimientos++;
	}

	private void desordenarCasillas() {

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

	private boolean estaOrdenado() {

		for (int i = 0; i < this.tamanio; i++) {
			for (int j = 0; j < this.tamanio; j++) {
				if (casillasTablero[i * this.tamanio + j].getValor() != (i
						* this.tamanio + j) + 1) {
					return false;
				}
			}
		}
		return true;

	}

}
