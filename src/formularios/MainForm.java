package formularios;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import negocio.*;
import negocio.Tablero.Direccion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;


public class MainForm {

	private JFrame frame;

	private Tablero tablero;
	
	private JPanel panel;
	
	private JLabel lblCantMov;
	
	private JLabel lblGanaste;
	
	
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
		frame.setBounds(100, 100, 450, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();		
		panel.setBounds(10, 11, 414, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel lblTotalMovimientos = new JLabel("Total Movimientos");
		lblTotalMovimientos.setBounds(28, 412, 90, 14);
		frame.getContentPane().add(lblTotalMovimientos);
		
		lblCantMov = new JLabel("0");
		lblCantMov.setBounds(138, 412, 46, 14);
		frame.getContentPane().add(lblCantMov);
		
		lblGanaste = new JLabel("New label");
		lblGanaste.setBounds(10, 310, 46, 14);
		frame.getContentPane().add(lblGanaste);
		
		JButton btnIzquierda = new JButton();		
		btnIzquierda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				tablero.mover(Direccion.IZQUIERDA);
				actualizarTablero();
			}
		});
		btnIzquierda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnIzquierda.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnIzquierda.setBackground(UIManager.getColor("control"));
			}
		});
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tablero.mover(Direccion.IZQUIERDA);
				actualizarTablero();
			}			
		});
		btnIzquierda.setIcon(new ImageIcon(getClass().getResource("1491245616_arrow_left_alt1.png")));
		btnIzquierda.setBounds(125, 310, 45, 41);
		frame.getContentPane().add(btnIzquierda);
		
		JButton btnDerecha = new JButton();
		btnDerecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnDerecha.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnDerecha.setBackground(UIManager.getColor("control"));
			}
		});		
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablero.mover(Direccion.DERECHA);
				actualizarTablero();
			}
		});
		btnDerecha.setIcon(new ImageIcon(getClass().getResource("1491245612_arrow_right_alt1.png")));
		btnDerecha.setBounds(248, 310, 45, 41);
		frame.getContentPane().add(btnDerecha);
		
		JButton btnArriba = new JButton();
		btnArriba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnArriba.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnArriba.setBackground(UIManager.getColor("control"));
			}
		});		
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(Direccion.ARRIBA);
				actualizarTablero();
			}
		});
		btnArriba.setIcon(new ImageIcon(getClass().getResource("1491245495_arrow_up_alt1.png")));
		btnArriba.setBounds(185, 272, 45, 41);
		frame.getContentPane().add(btnArriba);
		
		JButton btnAbajo = new JButton();		
		btnAbajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAbajo.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnAbajo.setBackground(UIManager.getColor("control"));
			}
		});		
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablero.mover(Direccion.ABAJO);
				actualizarTablero();
			}
		});
		btnAbajo.setIcon(new ImageIcon(getClass().getResource("1491245609_arrow_down_alt1.png")));
		btnAbajo.setBounds(185, 346, 45, 41);
		frame.getContentPane().add(btnAbajo);
		
				
		
		try {
			tablero = new Tablero(4);
			
			for (int i = 0; i < tablero.size(); i++){
								
				JButton btn = new JButton();
				panel.add(btn);
			}
			
			actualizarTablero();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private void actualizarTablero() {
		Component[] com = panel.getComponents();
		tablero.irInicio();				
		for(Component boton : com){
			
			JButton btn = (JButton)boton;
			
			Integer valor = tablero.getProximoValor();													
			String valorString = valor != 16 ? valor.toString() : "";		
			
			if(valorString.isEmpty()){
				btn.setBackground(Color.LIGHT_GRAY);
			} else {
				btn.setBackground(UIManager.getColor("control"));
			}
			
			
			btn.setText(valorString);
			
			lblCantMov.setText(tablero.getMovimientos().toString());
		}
		
		if(tablero.gano()){
			lblGanaste.setText("Ganaste!! :)");
						
			int dialogResult = JOptionPane.showConfirmDialog(frame, "Felicitaciones ganaste! Desea jugar nuevamente?", "",JOptionPane.YES_NO_OPTION);
			
			if(dialogResult == JOptionPane.NO_OPTION){
				frame.dispose();
			}
		}
			
		
		
	}
}
