package Tercero;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AgregarCliente extends JFrame{
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	private static JLabel label6;
	public static JTextField textbox1;
	public static JTextField textbox2;
	public static JTextField textbox3;
	public static JRadioButton radio;
	public static JRadioButton radio2;
	private static JButton NuevoCliente;
	public static JLabel label7;
	private static JLabel label8;
	private static JButton AgregarCliente;
	private static JButton ModiCliente;	

    int contcl=1;	 
	Principal.Principal globals = new Principal.Principal();

	public AgregarCliente(){
	 setSize(550,450);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setTitle("Agregar clientes");
	 setLocationRelativeTo(null);
	 setLayout(null);
	 componen();
	 }

	private void componen() {
		//TITULO Frame
		 label1 = new JLabel("Nuevo Cliente");
		 label1.setBounds(20,20,180,25);
		 add(label1);
		 this.add(label1);
		//TITULO Nombre
		 label2 = new JLabel("Nombre: ");
		 label2.setBounds(45,55,180,25);
		 add(label2);
		 this.add(label2);
		//TITULO Edad
		 label3 = new JLabel("Edad: ");
		 label3.setBounds(45,100,180,25);
		 add(label3);
		 this.add(label3);
		//TITULO Sexo
		 label4 = new JLabel("Sexo: ");
		 label4.setBounds(45,135,180,25);
		 add(label4);
		 this.add(label4);
		//TITULO Nit
		 label5 = new JLabel("NIT: ");
		 label5.setBounds(45,185,180,25);
		 add(label5);
		 this.add(label5);
		//TITULO AVATAR
		 label6 = new JLabel("Avatar: ");
		 label6.setBounds(45,210,180,25);
		 add(label6);		 
		//TEXTBOX Nombre
		 textbox1 = new JTextField();
		 textbox1.setBounds(110,60,110,25);
		 add(textbox1);
		//TEXTBOX Edad
		 textbox2 = new JTextField();
		 textbox2.setBounds(110,100,110,25);
		 add(textbox2);
		//TEXTBOX Nit
		 textbox3 = new JTextField();
		 textbox3.setBounds(110,180,110,25);
		 add(textbox3);
		 //RADIO Sexo
		 radio = new  JRadioButton ("M",true);
		 radio.setBounds(110,140,50,25);
		 add(radio);
		 
		 radio2 = new  JRadioButton ("F",false);
		 radio2.setBounds(160,140,50,25);
		 add(radio2);
		 //Button Selecciona Avatar
		 NuevoCliente = new JButton("Seleccionar..");
		 NuevoCliente.setBounds(110,210,110,30);
		 add(NuevoCliente);
		 NuevoCliente.addActionListener(anadir);
			//URL AVATAR
		 label7 = new JLabel();
		 label7.setBounds(10,350,435,100);
		 label7.setVisible(false);
		 add(label7);
		//RUTA AVATAR
		 label8 = new JLabel();
		 label8.setBounds(250,10,250,280);
		 add(label8);
		 //Button AGREGAR CLIENTE
		 AgregarCliente = new JButton("A�adir Cliente");
		 AgregarCliente.setBounds(50,320,140,40);
		 add(AgregarCliente);
		 AgregarCliente.addActionListener(listo);
		//Button MODIFICAR CLIENTE
		 ModiCliente = new JButton("Modificar Cliente");
		 ModiCliente.setBounds(250,320,140,40);
		 add(ModiCliente);
		 ModiCliente.addActionListener(cambio);
		 
		 
		 ButtonGroup RadioGrupo = new ButtonGroup();
		 RadioGrupo.add(radio);
		 RadioGrupo.add(radio2);
		

		
		 
	}
	ActionListener cambio=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
			 ModifCliente mc = new ModifCliente();
			 mc.setVisible(true);
		 }
	};
		
	ActionListener anadir=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 label7.setVisible(true);

			 final JLabel img = new JLabel();
			 img.setPreferredSize(new Dimension(300,200));
			 img.setHorizontalAlignment(JLabel.CENTER); 
			 
			 final JFileChooser fc=new JFileChooser();
			 fc.setAccessory(img);
			 
			 FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");		 
			 fc.setFileFilter(filtro);
			 fc.addPropertyChangeListener(new PropertyChangeListener() {			

				@Override
				public void propertyChange(PropertyChangeEvent evt) {
						try {
							if(evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
								img.setText("");
								img.setIcon(new ImageIcon(fc.getSelectedFile().getPath()));
							}
						}catch(Exception e2){
							img.setText("El archivo no es imagen");
							img.setIcon(new ImageIcon());
					
						}
				}
			 });
			 
			 int abrir=fc.showOpenDialog(fc);
			 fc.setDialogTitle("Avatar");
			 if (abrir==JFileChooser.APPROVE_OPTION) {
				 String URL = fc.getSelectedFile().getPath();
				 ImageIcon imagen = new ImageIcon(URL);
				 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(label8.getWidth(), label8.getHeight(), Image.SCALE_DEFAULT));
				 label8.setIcon(icono);
				 label7.setText(URL);
			 }
		 }
		 
	};	

	ActionListener listo=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
				AdmClientes ac = new AdmClientes();
			    if(textbox1.getText().length()!=0 && textbox2.getText().length()!=0 && textbox3.getText().length()!=0&& label7.getText().length()!=0){
			     ac.miModelo.setRowCount(0);

			     ac.paths[ac.limite][0] =textbox1.getText();
				 ac.paths[ac.limite][1] =textbox2.getText();
				 if(radio.isSelected()) {
				 ac.paths[ac.limite][2] = "M";	
				 }else {
				 ac.paths[ac.limite][2] = "F";	
				 }
				 ac.paths[ac.limite][3]=textbox3.getText();
				 ac.paths[ac.limite][4] =label7.getText();
			
				 ac.No =new int[ac.limite+1];
				 ac.namecliente = new String[ac.limite+1];
				 ac.Edadcliente= new int[ac.limite+1];
				 ac.secliente = new char[ac.limite+1];
				 ac.NITcliente = new int[ac.limite+1];
				 ac.Avatarcliente = new String[ac.limite+1];			 
				 ac.SeclienteS = new String[ac.limite+1];


				 for(int i=0; i<ac.limite+1;i++) {
					 ac.No[i]=i+1;
					 ac.namecliente[i] = ac.paths[i][0];			 
					 ac.Edadcliente[i] = Integer.parseInt(ac.paths[i][1]);
					 ac.SeclienteS[i]= ac.paths[i][2];
					 ac.NITcliente[i] = Integer.parseInt(ac.paths[i][3]);
					 ac.Avatarcliente[i] = ac.paths[i][4];			
				 } 
				 
				for(int i =0;i<ac.namecliente.length;i++) {
				ac.miModelo.addRow(new Object[]{ac.No[i],ac.namecliente[i],ac.Edadcliente[i],ac.SeclienteS[i],ac.NITcliente[i]});
				}
				ac.limite++;
				 setVisible(false);
				 ac.updatechart_1();
				 ac.updatechart2_1();
			 }else {
				 JOptionPane.showMessageDialog(null,"Campos vacios, llene su informaci�n");
			 }

		 }
	};
}
	

