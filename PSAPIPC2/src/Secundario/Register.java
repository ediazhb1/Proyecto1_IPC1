package Secundario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame{
	private static JLabel label1;
	private static JLabel label2;
	public static JTextField textbox1;
	private static JLabel label3;
	public static JTextField textbox2;
	private static JLabel label4;
	public static JPasswordField textbox3;
	private static JLabel label5;
	public static JPasswordField textbox4;
	private static JButton NewButton;
	private static JLabel label6;
	private static JButton RegsButton;
	private static JLabel label7;
	Principal.Principal globals = new Principal.Principal();	
	int cont =1;

	public Register(){
		setSize(400,400);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Registro de Usuario");
		 setLocationRelativeTo(null);
		 setLayout(null);
		componentes();	
	}

	private void componentes() {
		
		label1 = new JLabel("--REGISTRO DE USUARIO--");
		label1.setBounds(110,20,180,35);
		label1.setFont(new Font("cooper black",Font.BOLD,12));
		add(label1);	
		
		label2 = new JLabel("Usuario:");
		label2.setBounds(50,50,80,55);
		add(label2);
		
		 label7 = new JLabel("El usuario ingresado ya existe");
		 label7.setBounds(50,75,150,55);
		 label7.setFont(new Font("arial black",0,8));
		 label7.setForeground(Color.red);
		 label7.setVisible(false);
		 add(label7);
			
		textbox1 = new JTextField();
		textbox1.setBounds(210,65,110,25);
		add(textbox1);
		
		label3 = new JLabel("Nombre:");
		label3.setBounds(50,90,80,55);
		add(label3);
		
		textbox2 = new JTextField();
		textbox2.setBounds(210,105,110,25);
		add(textbox2);
		
		label4 = new JLabel("Contraseña:");
		label4.setBounds(50,130,80,55);
		add(label4);
		
		label5 = new JLabel("Confirmar Contraseña:");
		label5.setBounds(50,170,150,55);
		add(label5);
			    	    
	    label6 = new JLabel("No son iguales las contraseñas");
		label6.setBounds(50,185,150,55);
		label6.setFont(new Font("arial black",0,8));
		label6.setForeground(Color.red);
		label6.setVisible(false);
		add(label6);
		 //BOTON NUEVO USUARIO
		NewButton = new JButton("REGISTRAR USUARIO");
		NewButton.setBounds(105,230,160,40);
		add(NewButton);
		NewButton.addActionListener(Crear);
		//BOTON REGRESAR LOGIN
		RegsButton = new JButton("REGRESAR");
		RegsButton.setBounds(105,290,160,40);
		add(RegsButton);
	    RegsButton.addActionListener(Regresar);
		contra();
		conftra();
	}
	private void contra() {
		textbox3 = new JPasswordField();
		textbox3.setBounds(210,145,110,25);
		add(textbox3);
	}
	private void conftra() {
	textbox4 = new JPasswordField();
	textbox4.setBounds(210,185,110,25);
    add(textbox4);
	}
	
	ActionListener Regresar=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 Frames rg = new Frames();
			 rg.setVisible(true);	
		            
		        }
		 };
		 
		 ActionListener Crear=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
			 if(textbox4.getText().length()!=0 && textbox1.getText().length()!=0 && textbox2.getText().length()!=0&& textbox3.getText().length()!=0){

				 if(cont >=1 && cont <=10) {//Limite de usuarios
					 if(textbox3.getText().equals(textbox4.getText())) { //Contraseña y Confirmar Contraseña
						label6.setVisible(false);//Son iguales las contraseñas
						GuardarDatos();	
					  
					}else {
						label6.setVisible(true);//No son iguales las contraseñas
						JOptionPane.showMessageDialog(null,"USUARIO NO REGISTRADO");
					}

				 }else {
					 JOptionPane.showMessageDialog(null,"USUARIO NO REGISTRADO, ALCANZÓ EL LIMITE");
					 NewButton.setEnabled(false); 
					 NewButton.setText("Alcanzó el límite");
				 }
			 }else {
				 JOptionPane.showMessageDialog(null,"Campos vacios, llene su información");
			 }	
			        }
		 };
		 
		 
		 
			private void GuardarDatos() {
				globals.user[cont] =textbox1.getText();
				globals.name[cont] =textbox2.getText();
				globals.pass[cont] =textbox4.getText();
				RepitenciaDatos();
			}

			private void RepitenciaDatos() {
				 for(int i =0; i<globals.user.length;i++) {

				System.out.print("cont: "+i+" "+globals.user[i]+ " ");
				System.out.print(globals.name[i]+" ");
				System.out.println(globals.pass[i]);
				 }
				NewButton.setText("REGISTRAR USUARIO" + " (" + (cont)  + ")");
				NewButton.setBounds(90,230,190,40);
				JOptionPane.showMessageDialog(null,"USUARIO REGISTRADO");
			
				
				cont+=1;
			 textbox1.setText("");
			 textbox2.setText("");
			 textbox3.setText("");
			 textbox4.setText("");
			}
	
}