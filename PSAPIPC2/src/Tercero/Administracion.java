package Tercero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Secundario.Frames;

public class Administracion extends JFrame{
	private static JButton Clientes;
	private static JButton Productos;
	private static JButton Ventas;
	private static JButton Repor;
	private static JButton Back;

	public Administracion() {
		 setSize(400,400);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("VENTANA PRICIPAL");
		 setLocationRelativeTo(null);
		 setLayout(new GridLayout(5,0));
		 contenido();
		 }
	private void contenido() {
		//BOTON INGRESAR
		Clientes = new JButton("Administración de clientes");
		Clientes.addActionListener(Clien);
		 this.add(Clientes);
		 
		 Productos = new JButton("Administración de productos");
		 Productos.addActionListener(Prod);
		 this.add(Productos);
		 
		 Ventas = new JButton("Administración de ventas");
		 Ventas.addActionListener(Vent);
		 this.add(Ventas);
		 
		 Repor = new JButton("Reportes");
		 Repor.addActionListener(Rep);
		 this.add(Repor);
		 
		 Back = new JButton("Cerrar Sesión");
		 Back.addActionListener(close);
		 this.add(Back);
		 
	}
	
	ActionListener close =new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 Frames rg = new Frames();
			 rg.setVisible(true);	
		            
		 }
	};
	ActionListener Clien=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 AdmClientes clienchar = new AdmClientes();
			 clienchar.setVisible(true);
		 }
	};
	
	ActionListener Prod=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 AdmProdu produchar = new AdmProdu();
			 produchar.setVisible(true);
		 }
	};
	ActionListener Vent=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);

		 }
	};
	ActionListener Rep=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);

		 }
	};
}
