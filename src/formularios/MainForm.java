package formularios;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;

import negocio.*;
import negocio.RompecabezasDeslizante.Direccion;


public class MainForm {

	private JFrame frame;
	
	private JPanel panel;
	
	private JPanel panelRompecabezas;
	
	private JPanel panelMenu;
	
	private JLabel lblCantMov;
	
	private JLabel lblImagenGaleria;
	
	private JLabel backgroundRompecabezas;
	
	private JLabel backgroundMenu;
	
	private ImageIcon fondo;
	
	private Galeria galeria;
	
	private RompecabezasDeslizante rompecabeza;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();				
		frame.setBounds(0, 0, 440, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		frame.setResizable(false);
		frame.setTitle("Rompecabezas Los Simpsons");
		frame.setIconImage(new ImageIcon(getClass().getResource("images/icono.png")).getImage());
		fondo  = new ImageIcon(getClass().getResource("images/fondo.png"));
		
		inicializarMenu();
		inicializarRompecabezas();
		
	}
	
	/**
	 * Agrega los controles necesarios para visualizar el Menú
	 */
	private void inicializarMenu() {
		
		// Agrega el panel principal al menú de selección de imágenes.
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 434, 510);
		frame.getContentPane().add(panelMenu);
		panelMenu.setLayout(null);
				
		// Agrega un mensaje informativo en el menú de selección de imágenes.
		JLabel lblMensajeMenu = new JLabel("Seleccione una imagen para jugar:");		
		lblMensajeMenu.setFont(new Font(lblMensajeMenu.getName(), Font.BOLD, 20));;
		lblMensajeMenu.setBounds(15, 10, 400, 40);
		panelMenu.add(lblMensajeMenu);
		
		inicializarBotonesMenu();
		
		// Agrega una galeria de imágenes para seleccionar
		galeria = new Galeria();		
		try {
			galeria.addImagen(ImageIO.read(getClass().getResource("images/hombrePie.jpg")), "hombrePie");
			galeria.addImagen(ImageIO.read(getClass().getResource("images/foto.jpg")), "foto");
			galeria.addImagen(ImageIO.read(getClass().getResource("images/puerco.jpg")), "puerco");
			lblImagenGaleria = new JLabel();		
			ImageIcon imagenGaleria  = new ImageIcon(galeria.getImagenActual());
			lblImagenGaleria.setIcon(imagenGaleria);
			lblImagenGaleria.setBounds(15, 60, 400, 300);
			panelMenu.add(lblImagenGaleria);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(frame, "Hubo un problema al cargar el juego =(", "", JOptionPane.CLOSED_OPTION);
			
		}
		
		// Agrega un fondo al menú de selección de imágenes.
		backgroundMenu = new JLabel();
		backgroundMenu.setBounds(0, 0, 440, 510);
		panelMenu.add(backgroundMenu);
		backgroundMenu.setIcon(fondo);
		backgroundMenu.setLayout(null);	
		
		// Setea al menú como formulario de inicio
		panelMenu.setVisible(true);
	}
	
	/**
	 * Agrega los controles necesarios para visualizar el Rompecabezas
	 */
	private void inicializarRompecabezas() {
		// Agrega el panel principal al formulario del Rompecabezas.
		panelRompecabezas = new JPanel();
		panelRompecabezas.setBounds(0, 0, 440, 510);
		frame.getContentPane().add(panelRompecabezas);
		panelRompecabezas.setLayout(null);
		
		// Agrega las etiquetas para la visualización de los movimientos
		JLabel lblTotalMovimientos = new JLabel("Movimientos:");		
		lblTotalMovimientos.setFont(new Font(lblTotalMovimientos.getName(), Font.BOLD, 20));
		lblTotalMovimientos.setBounds(15, 10, 150, 40);
		panelRompecabezas.add(lblTotalMovimientos);		
		lblCantMov = new JLabel();
		lblCantMov.setBounds(170, 10, 46, 40);
		lblCantMov.setFont(new Font(lblCantMov.getName(), Font.BOLD, 20));
		panelRompecabezas.add(lblCantMov);
		
		// Agrega el panel contenedor del Rompecabezas 4x4
		panel = new JPanel();		
		panel.setBounds(10, 50, 406, 300);
		panel.setLayout(new GridLayout(4, 4, 0, 0));
		panelRompecabezas.add(panel);
		
		inicializarBotonesRompecabezas();		
		
		// Agrega un fondo al formulario de Rompecabezas.
		backgroundRompecabezas = new JLabel();
		backgroundRompecabezas.setBounds(0, 0, 440, 510);
		panelRompecabezas.add(backgroundRompecabezas);
		backgroundRompecabezas.setIcon(fondo);
		backgroundRompecabezas.setLayout(null);
		
		armarRompecabezas();
		
		panelRompecabezas.setVisible(false);
	}
	
	/**
	 * Agrega los botones de dirección (Arriba - Izquierda - Derecha - Abajo) al formulario para manejar el Rompecabezas.
	 */
	private void inicializarBotonesRompecabezas() {
		JButton btnIzquierda = new JButton();
		btnIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnIzquierda.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnIzquierda.setBackground(UIManager.getColor("control"));
			}
		});
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rompecabeza.mover(Direccion.IZQUIERDA);
				actualizarTablero();
			}			
		});
		btnIzquierda.setIcon(new ImageIcon(getClass().getResource("images/flechaIzquierda.png")));
		btnIzquierda.setBounds(145, 392, 65, 41);
		panelRompecabezas.add(btnIzquierda);
		
		JButton btnDerecha = new JButton();
		btnDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDerecha.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDerecha.setBackground(UIManager.getColor("control"));
			}
		});		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rompecabeza.mover(Direccion.DERECHA);
				actualizarTablero();
			}
		});
		btnDerecha.setIcon(new ImageIcon(getClass().getResource("images/flechaDerecha.png")));
		btnDerecha.setBounds(279, 392, 65, 41);
		panelRompecabezas.add(btnDerecha);
		
		JButton btnArriba = new JButton();
		btnArriba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnArriba.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnArriba.setBackground(UIManager.getColor("control"));
			}
		});		
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rompecabeza.mover(Direccion.ARRIBA);
				actualizarTablero();
			}
		});
		btnArriba.setIcon(new ImageIcon(getClass().getResource("images/flechaArriba.png")));
		btnArriba.setBounds(212, 353, 65, 41);
		panelRompecabezas.add(btnArriba);
		
		JButton btnAbajo = new JButton();		
		btnAbajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAbajo.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAbajo.setBackground(UIManager.getColor("control"));
			}
		});		
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rompecabeza.mover(Direccion.ABAJO);
				actualizarTablero();
			}
		});
		btnAbajo.setIcon(new ImageIcon(getClass().getResource("images/flechaAbajo.png")));
		btnAbajo.setBounds(212, 431, 65, 41);
		panelRompecabezas.add(btnAbajo);	
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(330, 10, 89, 23);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolver.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnVolver.setBackground(UIManager.getColor("control"));
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(true);
				panelRompecabezas.setVisible(false);
			}
		});
		panelRompecabezas.add(btnVolver);
	}
	
	/**
	 * Agrega los botones del menú para poder iniciar una partida del Rompecabezas
	 */
	private void inicializarBotonesMenu() {
		JButton btnImagenAnterior = new JButton();		
		btnImagenAnterior.setIcon(new ImageIcon(getClass().getResource("images/flechaIzquierda.png")));
		btnImagenAnterior.setBounds(145, 370, 65, 41);
		btnImagenAnterior.setToolTipText("Anterior");
		btnImagenAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnImagenAnterior.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnImagenAnterior.setBackground(UIManager.getColor("control"));
			}
		});
		btnImagenAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono  = new ImageIcon(galeria.getImagenAnterior());
				lblImagenGaleria.setIcon(icono);
			}
		});
		panelMenu.add(btnImagenAnterior);
		
		JButton btnImagenSiguiente = new JButton();
		btnImagenSiguiente.setIcon(new ImageIcon(getClass().getResource("images/flechaDerecha.png")));
		btnImagenSiguiente.setBounds(239, 370, 65, 41);
		btnImagenSiguiente.setToolTipText("Siguiente");
		btnImagenSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnImagenSiguiente.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnImagenSiguiente.setBackground(UIManager.getColor("control"));
			}
		});
		btnImagenSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icono  = new ImageIcon(galeria.getImagenSiguiente());
				lblImagenGaleria.setIcon(icono);
			}
		});
		panelMenu.add(btnImagenSiguiente);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(330, 440, 89, 23);
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnJugar.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnJugar.setBackground(UIManager.getColor("control"));
			}
		});
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(false);
				panelRompecabezas.setVisible(true);
				armarRompecabezas();
				actualizarTablero();
			}
		});
		panelMenu.add(btnJugar);
	}
	
	/**
	 * Inicializa un nuevo juego RompecabezasDeslizante
	 */
	private void armarRompecabezas() {
		// Elimina los botones del panel que forma el rompecabezas
		panel.removeAll();		
		// Creo un Rompecabezas de botones.
		try {
			rompecabeza = new RompecabezasDeslizante(4, 4);
			
			for (int i = 0; i < rompecabeza.size(); i++){
								
				JButton btn = new JButton();	
				btn.setSize(100, 70);
				panel.add(btn);
			}
		} catch (Exception e) {
			
			JOptionPane.showConfirmDialog(frame, "Hubo un problema al cargar el juego =(", "", JOptionPane.CLOSED_OPTION);			
		}
	}
	
	/**
	 * Actualiza los botones del Rompecabezas con el estado actual del juego.
	 * Muestra un mensaje cuando se resuelve el Rompecabezas.
	 */
	private void actualizarTablero() {
		Component[] com = panel.getComponents();			
		JButton btnFinal = new JButton();	
		int posicion = 0;
		for(Component boton : com){
			
			JButton btn = (JButton)boton;			
			int valor = rompecabeza.getPieza(posicion);
			posicion++;
			btn.setIcon(new ImageIcon(getClass().getResource("images/" + galeria.getNombreImagenActual() + "/imagen" + valor + ".png")));			
			lblCantMov.setText(rompecabeza.getCantidadMovimientos().toString());
			if(valor == rompecabeza.size()) {
				btnFinal = btn;
			}
		}
		
		if(rompecabeza.gano()){
			
			btnFinal.setIcon(new ImageIcon(getClass().getResource("images/" + galeria.getNombreImagenActual() + "/imagen" + 17 + ".png")));			
			int dialogResult = JOptionPane.showConfirmDialog(frame, "Felicitaciones ganaste! Desea jugar nuevamente?", "",JOptionPane.YES_NO_OPTION);			
			if(dialogResult == JOptionPane.NO_OPTION){
				frame.dispose();
			} else {
				panelMenu.setVisible(true);
				panelRompecabezas.setVisible(false);
				armarRompecabezas();
			}
		}		
	}
}
