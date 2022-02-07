package Tercero;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ModifProdu extends JFrame{
	private static JLabel label11;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	private static JLabel label6;
	public static JLabel label7;
	private static JLabel label8;
	public static JTextField textbox0;
	public static JTextField textbox1;
	public static JTextField textbox2;
	public static JTextField textbox3;
	private static JButton modiProd;
	private static JButton cancelProd;
	private static JButton returnProd;

	public ModifProdu(){
		 setSize(450,450);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Modificar Productos");
		 setLocationRelativeTo(null);
		 setLayout(null);
		 componen();
		 }
	private void componen() {
		//TITULO Frame
		 label11 = new JLabel("Modificar Producto");
		 label11.setBounds(20,20,180,25);
		 add(label11);
		//TITULO Nombre
		 label2 = new JLabel("Antiguo Nombre: ");
		 label2.setBounds(25,55,180,25);
		 add(label2);
		//TITULO Nombre
		 label3 = new JLabel("Nuevo Nombre: ");
		 label3.setBounds(25,105,180,25);
		 add(label3);
		//TITULO Precio
		 label4 = new JLabel("Nuevo Precio: ");
		 label4.setBounds(25,155,180,25);
		 add(label4);
		//TITULO Cantidd
		 label5 = new JLabel("Nueva Cantidad: ");
		 label5.setBounds(25,205,180,25);
		 add(label5);
		//TITULO Imagen
		 label6 = new JLabel("Nuevo avatar: ");
		 label6.setBounds(25,255,180,25);
		 add(label6);
		//URL AVATAR
		 label7 = new JLabel();
		 label7.setBounds(10,350,435,100);
		 label7.setVisible(false);
		 add(label7);
		//RUTA AVATAR
		 label8 = new JLabel();
		 label8.setBounds(260,10,250,280);
		 add(label8);
		//TEXTBOX Nombre
		 textbox0 = new JTextField();
		 textbox0.setBounds(135,50,110,25);
		 add(textbox0);
		//TEXTBOX Precio
		 textbox1 = new JTextField();
		 textbox1.setBounds(135,100,110,25);
		 add(textbox1);
		//TEXTBOX Precio
		 textbox2 = new JTextField();
		 textbox2.setBounds(135,150,110,25);
		 add(textbox2);
		//TEXTBOX Cantidad
		 textbox3 = new JTextField();
		 textbox3.setBounds(135,200,110,25);
		 add(textbox3);
		 //Button Selecciona Imagen
		 modiProd = new JButton("Seleccionar..");
		 modiProd.setBounds(130,250,110,30);
		 add(modiProd);
		 modiProd.addActionListener(sele);	
		 //Button Modificar prod
		 cancelProd = new JButton("Modificar Producto");
		 cancelProd.setBounds(30,320,150,40);
		 add(cancelProd);
		 cancelProd.addActionListener(modif);	
		 //Button regresar
		 returnProd = new JButton("Regresar (Sin Modificar)");
		 returnProd.setBounds(220,320,190,40);
		 add(returnProd);
		 returnProd.addActionListener(back);	
	}
	ActionListener back=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 setVisible(false);
		 }
	};
		
	ActionListener sele=new ActionListener() {
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
	ActionListener modif=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 AdmProdu acP3 = new AdmProdu(); 
			 if(textbox1.getText().length()!=0&&textbox0.getText().length()!=0&&textbox2.getText().length()!=0){
				for(int j=0; j<acP3.paths.length;j++) { 
				    if(textbox0.getText().equals(acP3.paths[j][0])) {
				    	acP3.miModelo.removeRow(j);
				   	 acP3.No=new int[acP3.limite];
			    	 acP3.nameprod=new String[acP3.limite];
			    	 acP3.precprod= new float[acP3.limite];
			    	 acP3.cantprod=new int[acP3.limite];
			    	 acP3.Avatarprod=new String[acP3.limite];
			    	 
			    acP3.paths[j][0] ="";//name
	    		acP3.paths[j][1] ="";//precio	   
	    		acP3.paths[j][2] ="";//cantidad	
	    		acP3.paths[j][3] ="";//avatar
		    	
		    	acP3.paths[j][0] =textbox1.getText();//name
	    		acP3.paths[j][1] =textbox2.getText();//precio	   
	    		acP3.paths[j][2] =textbox3.getText();//cantidad	
	    		acP3.paths[j][3] =label7.getText();//avatar
		    	
		    
		    	  for(int i =0;i<acP3.limite;i++) {
					 acP3.No[i]=i+1;
					 acP3.nameprod[i] = acP3.paths[i][0];			 
					 acP3.precprod[i] = Float.parseFloat(acP3.paths[i][1]);
					 acP3.cantprod[i]= Integer.parseInt(acP3.paths[i][2]);
					 acP3.Avatarprod[i] = acP3.paths[i][3];
					}
			    	 acP3.miModelo.setRowCount(0);

			 for(int i =0;i<acP3.No.length;i++) {
					acP3.miModelo.addRow(new Object[]{acP3.No[i],acP3.nameprod[i],acP3.precprod[i],acP3.cantprod[i]});
			}
			 break;
				 }
		     }
				setVisible(false);
				 acP3.updatechart01();
				 acP3.updatechart2_1();
		 }else {
			 JOptionPane.showMessageDialog(null,"Campos vacios, llene su información");
		 }
		 }
		 };
	
}
