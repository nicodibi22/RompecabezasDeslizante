package negocio;

import java.util.Random;

public class RompecabezasDeslizante {

	public enum Direccion {
		ARRIBA, ABAJO, DERECHA, IZQUIERDA
	}
	
	private Tablero tableroJuego;
	
	private int movimientos;
	
	public RompecabezasDeslizante(int lado) throws Exception {
		
		tableroJuego = new Tablero(lado);		
		desordenarCasillas();
		movimientos = 0;
	}
	
	public Integer size() {
		return tableroJuego.size();
	}
	
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
	
	public Integer getCantidadMovimientos() {
		
		return movimientos;
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
	
	public boolean gano() {
		
		return tableroJuego.estaOrdenado();
	}
	
	public String getPieza(int posicion) {

		return tableroJuego.getValor(posicion);

	}
}
