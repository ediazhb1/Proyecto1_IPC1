package Tercero;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ModifCliente extends JFrame{
	private static JLabel label11;
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	private static JLabel label6;
	public static JTextField textbox0;
	public static JTextField textbox1;
	public static JTextField textbox2;
	public static JTextField textbox3;
	public static JRadioButton radio;
	public static JRadioButton radio2;
	private static JButton NuevoCliente;
	public static JLabel label7;
	private static JLabel label8;
	private static JButton BackCliente;
	private static JButton ModiCliente;	

    int contcl=1;	 
	Principal.Principal globals = new Principal.Principal();

	public ModifCliente(){
	 setSize(550,450);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setTitle("Modificar clientes");
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
		 label2 = new JLabel("Antiguo NIT: ");
		 label2.setBounds(35,50,180,25);
		 add(label2);
		 this.add(label2);
		//TITULO Nombre
		 label2 = new JLabel("Nuevo Nombre: ");
		 label2.setBounds(35,100,180,25);
		 add(label2);
		 this.add(label2);
		//TITULO Edad
		 label3 = new JLabel("Nueva Edad: ");
		 label3.setBounds(35,150,180,25);
		 add(label3);
		 this.add(label3);
		//TITULO Sexo
		 label4 = new JLabel("Nuevo Sexo: ");
		 label4.setBounds(35,200,180,25);
		 add(label4);
		 this.add(label4);
		//TITULO Nit
		 label5 = new JLabel("Nuevo NIT: ");
		 label5.setBounds(35,250,180,25);
		 add(label5);
		 this.add(label5);
		//TITULO AVATAR
		 label6 = new JLabel("Nuevo Avatar: ");
		 label6.setBounds(35,300,180,25);
		 add(label6);
		//TEXTBOX Nombre
		 textbox0 = new JTextField();
		 textbox0.setBounds(130,50,110,25);
		 add(textbox0);
		//TEXTBOX Nombre
		 textbox1 = new JTextField();
		 textbox1.setBounds(130,100,110,25);
		 add(textbox1);
		//TEXTBOX Edad
		 textbox2 = new JTextField();
		 textbox2.setBounds(130,150,110,25);
		 add(textbox2);
		//TEXTBOX Nit
		 textbox3 = new JTextField();
		 textbox3.setBounds(130,240,110,25);
		 add(textbox3);
		 //RADIO Sexo
		 radio = new  JRadioButton ("M",true);
		 radio.setBounds(130,200,50,25);
		 add(radio);
		 
		 radio2 = new  JRadioButton ("F",false);
		 radio2.setBounds(180,200,50,25);
		 add(radio2);
		 //Button Selecciona Avatar
		 NuevoCliente = new JButton("Seleccionar..");
		 NuevoCliente.setBounds(130,290,110,30);
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
		 BackCliente = new JButton("Modificar Cliente");
		 BackCliente.setBounds(50,340,140,40);
		 add(BackCliente);
		 BackCliente.addActionListener(listo);
		//Button MODIFICAR CLIENTE
		 ModiCliente = new JButton("Regresar (Sin Modificar)");
		 ModiCliente.setBounds(250,340,180,40);
		 add(ModiCliente);
		 ModiCliente.addActionListener(Back2);
		 ButtonGroup RadioGrupo = new ButtonGroup();
		 RadioGrupo.add(radio);
		 RadioGrupo.add(radio2);
		

}
	ActionListener Back2=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
		 }
	};
	
	
	ActionListener anadir=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setSize(550,450);
			 setLocationRelativeTo(null);
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
			 AdmClientes acP = new AdmClientes();  
			    if(textbox1.getText().length()!=0&&textbox0.getText().length()!=0&&textbox2.getText().length()!=0){

			    	for(int i=0; i<acP.paths.length;i++) {
				    	if(textbox0.getText().equals(acP.paths[i][3])) {
				    		acP.miModelo.removeRow(i);
						    acP.paths[i][0] ="";//name
				    		acP.paths[i][1] ="";//Edad		   
				    		acP.paths[i][2] ="";//sec		   
				    		acP.paths[i][3] ="";//NIT	
				    		acP.paths[i][4] ="";//Avatar
				    		
				    		acP.paths[i][0] =textbox1.getText();
				    		acP.paths[i][1] =textbox2.getText();				   
				    		if(radio.isSelected()) {
								 acP.paths[i][2] = "M";	
							 }else if(radio2.isSelected()){
								 acP.paths[i][2] = "F";	
							 }else {
								 
							 }
				    		acP.paths[i][3] =textbox3.getText();				   
				    		acP.paths[i][4] = label7.getText();	

				   		 acP.No =new int[acP.limite];
						 acP.namecliente = new String[acP.limite];
						 acP.Edadcliente= new int[acP.limite];
						 acP.NITcliente= new int[acP.limite];
						 acP.SeclienteS  = new String[acP.limite];
						 acP.Avatarcliente = new String[acP.limite];
						 
					     for(int j =0;j<acP.limite;j++) {
						 acP.No[j]=j+1;
						 acP.namecliente[j] = acP.paths[j][0];			 
						 acP.Edadcliente[j] = Integer.parseInt(acP.paths[j][1]);
						 acP.SeclienteS[j]= acP.paths[j][2];
						 acP.NITcliente[j] = Integer.parseInt(acP.paths[j][3]);
					     acP.Avatarcliente[j] = acP.paths[j][4];
					     }
				    	 acP.miModelo.setRowCount(0);
					     for(int j =0;j<acP.namecliente.length;j++) {
								acP.miModelo.addRow(new Object[]{acP.No[j],acP.namecliente[j],acP.Edadcliente[j],acP.SeclienteS[j],acP.NITcliente[j]});
						 }
					     setVisible(false);
						 acP.updatechart();
						 acP.updatechart2_1();
				    		 
						 break;
				   }  
			   }
				
			 }else {
					 JOptionPane.showMessageDialog(null,"Campos vacios, llene su informaci�n");
			 }
		 }
	};
}
