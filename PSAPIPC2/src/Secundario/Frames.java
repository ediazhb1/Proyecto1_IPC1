package Secundario;

import javax.swing.*;

import Tercero.Administracion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
public class Frames extends JFrame{
	 private static JLabel label1;
	 private static JLabel Userlabel;
	 private static JTextField textbox1;
	 private static JLabel Passlabel;
	 private static JPasswordField textbox2;
	 private static JButton LogButton;
	 private static JButton RegButton;
	 private static JButton ForgButton;
	 String[] prueba = new String[1];
	 String[] prueba2 = new String[1];

	 Principal.Principal globals = new Principal.Principal();	

	 public Frames() {
	 setSize(400,400);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setTitle("AUTENTICACIÓN1");
	 setLocationRelativeTo(null);
	 setLayout(null);
	 contenido();
	 }
	  
	 private void contenido() {
		//TITULO LOGIN
		 label1 = new JLabel("---BIENVENIDO---");
		 label1.setBounds(140,20,180,25);
		 add(label1);
		 this.add(label1);
		 //TITULO USUARIO
		 Userlabel = new JLabel("Usuario:");
		 Userlabel.setBounds(80,60,80,25);
		 add(Userlabel);
		 //TEXTBOX USUARIO
		 textbox1 = new JTextField();
		 textbox1.setBounds(180,60,110,25);
		 this.add(textbox1);
		 //TITULO CONTRASEÑA
		 Passlabel = new JLabel("Contraseña:");
		 Passlabel.setBounds(80,100,80,25);
		 add(Passlabel);
		//TEXTBOX CONTRASEÑA
		 textbox2 = new JPasswordField();
		 textbox2.setBounds(180,100,110,25);
		 this.add(textbox2);
		 
		 botones();	
	}

	public void botones() {
		//BOTON INGRESAR
		 LogButton = new JButton("Iniciar Sesión");
		 LogButton.setBounds(130,150,140,40);
		 LogButton.addActionListener(ingresar);
		 this.add(LogButton);
		//BOTON REGRESAR
		 RegButton = new JButton("Registrese");
		 RegButton.setBounds(130,210,140,40);
		 RegButton.addActionListener(Registrarse);
		 add(RegButton);
		//BOTON OLVIDAR
		 ForgButton = new JButton("Recuperar Contraseña");
		 ForgButton.setBounds(115,270,170,40);
		 ForgButton.addActionListener(olvidar);
		 add(ForgButton);
	}

	
	
	
	
	ActionListener ingresar=new ActionListener() {
	 @Override
	 public void actionPerformed(ActionEvent e) {
		//globals.user[cont] =textbox1.getText();
		 prueba[0]=textbox1.getText();
		 prueba2[0]=textbox2.getText();

		 String passs = String.valueOf(textbox2.getPassword());
		 if( textbox1.getText().length()!=0 && passs.length()!=0){
			 System.out.println("El textbox tiene: " + prueba[0]);
			
			 if((prueba[0].equals(globals.user[0])) &&(prueba2[0].equals(globals.pass[0]))) {			 
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true); 
			 }else if(prueba[0].equals(globals.user[1])&&(prueba2[0].equals(globals.pass[1]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[2])&&(prueba2[0].equals(globals.pass[2]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[3])&&(prueba2[0].equals(globals.pass[3]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[4])&&(prueba2[0].equals(globals.pass[4]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[5])&&(prueba2[0].equals(globals.pass[5]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[6])&&(prueba2[0].equals(globals.pass[6]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);
			 }else if(prueba[0].equals(globals.user[7])&&(prueba2[0].equals(globals.pass[7]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[8])&&(prueba2[0].equals(globals.pass[8]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[9])&&(prueba2[0].equals(globals.pass[9]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else if(prueba[0].equals(globals.user[10])&&(prueba2[0].equals(globals.pass[10]))){
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true);  
			 }else {
				 JOptionPane.showMessageDialog(null,"Usuario no registrado");
			 }
			 
   		
		}else {
			 JOptionPane.showMessageDialog(null,"Campos vacios, llene su información");
	 	}
		 }
	 };	 
	 
	 ActionListener Registrarse=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 Register rg = new Register();
			 rg.setVisible(true);
			 
		        }
		 };
		ActionListener olvidar=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 try {
				String nameforg = JOptionPane.showInputDialog("Escriba el nombre con el que se registró");
				for(int i =1; i<globals.user.length;i++) {
					if(nameforg.equals(globals.name[i])) {
					JOptionPane.showMessageDialog(null, "Usuario: " + globals.user[i] + "\n"+ "Contraseña: " +globals.pass[i] );
					break;
				}
				}
				 }catch(Exception e2) {
					 JOptionPane.showMessageDialog(null, "Usuario no encontrado"); 
				 }
			 }		
		};
		}

