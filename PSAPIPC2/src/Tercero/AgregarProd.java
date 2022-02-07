package Tercero;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AgregarProd extends JFrame{
	private static JLabel label1;
	private static JLabel label2;
	private static JLabel label3;
	private static JLabel label4;
	private static JLabel label5;
	public static JLabel label7;
	private static JLabel label8;
	public static JTextField textbox1;
	public static JTextField textbox2;
	public static JTextField textbox3;
	private static JButton NuevoProd;
	private static JButton AgregarProd;
	private static JButton ModiProd;
	private static JButton ListoModiProd;

	
    int contcl=1;	 
	Principal.Principal globals = new Principal.Principal();

	public AgregarProd(){
		 setSize(550,450);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Agregar Productos");
		 setLocationRelativeTo(null);
		 setLayout(null);
		 componen();
		 }
	private void componen() {
		//TITULO Frame
		 label1 = new JLabel("Nuevo Producto");
		 label1.setBounds(20,20,180,25);
		 add(label1);
		//TITULO Nombre
		 label2 = new JLabel("Nombre: ");
		 label2.setBounds(45,55,180,25);
		 add(label2);
		//TITULO Precio
		 label3 = new JLabel("Precio: ");
		 label3.setBounds(45,105,180,25);
		 add(label3);
		//TITULO Cantidad
		 label4 = new JLabel("Cantidad: ");
		 label4.setBounds(45,155,180,25);
		 add(label4);
		//TITULO Imagen
		 label5 = new JLabel("Imagen: ");
		 label5.setBounds(45,205,180,25);
		 add(label5);
		//URL AVATAR
		 label7 = new JLabel();
		 label7.setBounds(10,350,435,100);
		 label7.setVisible(false);
		 add(label7);
		//RUTA AVATAR
		 label8 = new JLabel();
		 label8.setBounds(250,10,250,280);
		 add(label8);
		//TEXTBOX Nombre
		 textbox1 = new JTextField();
		 textbox1.setBounds(110,50,110,25);
		 add(textbox1);
		//TEXTBOX Precio
		 textbox2 = new JTextField();
		 textbox2.setBounds(110,100,110,25);
		 add(textbox2);
		//TEXTBOX Cantidad
		 textbox3 = new JTextField();
		 textbox3.setBounds(110,150,110,25);
		 add(textbox3);
		 //Button Selecciona Imagen
		 NuevoProd = new JButton("Seleccionar..");
		 NuevoProd.setBounds(110,210,110,30);
		 add(NuevoProd);
		 NuevoProd.addActionListener(anadir);
		 //Button AGREGAR CLIENTE
		 AgregarProd = new JButton("Anadir Producto");
		 AgregarProd.setBounds(50,320,140,40);
		 add(AgregarProd);
		 AgregarProd.addActionListener(listo);	
		//Button MODIFICAR CLIENTE
		 ModiProd = new JButton("Modificar Producto");
		 ModiProd.setBounds(250,320,160,40);
		 add(ModiProd);
		 ModiProd.addActionListener(modi);	

	}
	ActionListener modi=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
				ModifProdu modificar = new ModifProdu();
				setVisible(false);
				modificar.setVisible(true);
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
			 AdmProdu acP3 = new AdmProdu();
			    if(textbox1.getText().length()!=0 && textbox2.getText().length()!=0 && textbox3.getText().length()!=0&& label7.getText().length()!=0){
				     acP3.miModelo.setRowCount(0);

				     acP3.paths[acP3.limite][0] =textbox1.getText();
					 acP3.paths[acP3.limite][1] =textbox2.getText();
					 acP3.paths[acP3.limite][2]=textbox3.getText();
					 acP3.paths[acP3.limite][3]=label7.getText();
				
					 acP3.No =new int[acP3.limite+1];
					 acP3.nameprod = new String[acP3.limite+1];
					 acP3.precprod= new float[acP3.limite+1];
					 acP3.cantprod = new int[acP3.limite+1];
					 acP3.Avatarprod = new String[acP3.limite+1];


					 for(int i=0; i<acP3.limite+1;i++) {
						 acP3.No[i]=i+1;
						 acP3.nameprod[i] = acP3.paths[i][0];			 
						 acP3.precprod[i] = Float.parseFloat(acP3.paths[i][1]);
						 acP3.cantprod[i]= Integer.parseInt(acP3.paths[i][2]);
						 acP3.Avatarprod[i] = acP3.paths[i][3];			
					 } 
					 
					for(int i =0;i<acP3.nameprod.length;i++) {
					acP3.miModelo.addRow(new Object[]{acP3.No[i],acP3.nameprod[i],acP3.precprod[i],acP3.cantprod[i]});
					}
					 
					 setVisible(false);
					 acP3.updatechart0();
					 acP3.updatechart2_1();
					 acP3.limite++;
			 }else {
				 JOptionPane.showMessageDialog(null,"Campos vacios, llene su informaci�n");
			 }
		 }
	};
}

	

