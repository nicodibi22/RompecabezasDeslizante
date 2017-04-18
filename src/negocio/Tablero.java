package negocio;

class Tablero {

	private Celda[] casillasTablero;

	private int altura;
	
	private int anchura;

	private int cantElementos;
	
	private int ubicacionPivoteFila;

	private int ubicacionPivoteColumna;

	/**
	 * Arma un tablero de tamaño alto x ancho.
	 * @param alto
	 * @param ancho
	 * @throws IllegalArgumentException: El alto y el ancho deben ser mayor a 0 (cero).
	 */
	public Tablero(int alto, int ancho) throws IllegalArgumentException {
		if (alto <= 0 || ancho <= 0) {
			throw new IllegalArgumentException("El tablero debe contener al menos 1 casillero. alto = " + alto + " - ancho = " + ancho);
		}

		// Inicializa las variables		
		altura = alto;
		anchura = ancho;
		cantElementos = alto * ancho;
		ubicacionPivoteFila = this.altura - 1;
		ubicacionPivoteColumna = this.anchura - 1;
		casillasTablero = new Celda[cantElementos];
		
		for (int i = 0; i < cantElementos; i++) {
			
			casillasTablero[i] = new Celda(i + 1);
		}
	}

	public int size() {
		
		return cantElementos;
	}
	
	/**
	 * Devuelve el valor numérico de la posición a la que se quiere acceder
	 * @return: Valor de la celda indicada.
	 */
	public int getValor(int posicion) {

		return casillasTablero[posicion].getValor();
	}		

	public boolean moverIzquierda() {
		
		if (ubicacionPivoteColumna > 0) {
			moverCelda(-1, 0);
			ubicacionPivoteColumna--;
			return true;
		}
		return false;
	}

	public boolean moverDerecha() {

		if (ubicacionPivoteColumna < this.anchura - 1) {
			moverCelda(1, 0);
			ubicacionPivoteColumna++;
			return true;
		}
		return false;
	}

	public boolean moverArriba() {
		
		if (ubicacionPivoteFila > 0) {
			moverCelda(0, -1);
			ubicacionPivoteFila--;
			return true;
		}
		return false;
	}

	public boolean moverAbajo() {
		
		if (ubicacionPivoteFila < this.altura - 1) {
			moverCelda(0, 1);
			ubicacionPivoteFila++;
			return true;
		}
		return false;
	}

	/**
	 * Mueve la celda sobre el eje X y el eje Y de acuerdo a los parámetros.
	 * @param x
	 * @param y
	 */
	private void moverCelda(int x, int y) {
		
		Celda celdaAux = new Celda(casillasTablero[(ubicacionPivoteFila + y)
				* this.altura + ubicacionPivoteColumna + x].getValor());

		casillasTablero[(ubicacionPivoteFila + y) * this.altura
				+ ubicacionPivoteColumna + x] = casillasTablero[ubicacionPivoteFila
				* this.altura + ubicacionPivoteColumna];
		casillasTablero[ubicacionPivoteFila * this.altura
				+ ubicacionPivoteColumna] = celdaAux;
	}

	/**
	 * Verifica que las piezas estémn ordenadas numéricamente del 1 
	 * a la cantidad de elementos del tablero.
	 * @return
	 * true: El tablero está ordenado.
	 * false: El tablero no está ordenado.
	 */
	public boolean estaOrdenado() {

		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.altura; j++) {
				if (casillasTablero[i * this.altura + j].getValor()!= (i
						* this.altura + j + 1)) {
					return false;
				}
			}
		}
		return true;
	}

}
