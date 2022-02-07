package Tercero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultHighLowDataset;

public class AdmProdu extends JFrame{
	private static JPanel panel;
	private static JLabel label1;
	private static JButton CargarButton;
	public static DefaultTableModel miModelo;
	public static JTable tabla;
	private static DefaultPieDataset dtsc;
	private static DefaultCategoryDataset dataset3;
	private static JButton NuevoProd;
	private static JButton Regresar;
	private static JButton ConsultaProd;
	private static JButton DeleteProd;
	private static ChartPanel cp4;
	private static ChartPanel cp5;
	public static String paths[][]= new String[100][4];

	public static int limite=0;
	public static int[] No;
	public static String[] nameprod;
	public static float [] precprod;
	public static int [] cantprod;
	public static String[] Avatarprod;
	public static JPanel panel2;
	private static JPanel panel3;

	private static int M = 0,F=0;

	public AdmProdu(){
	 setSize(275,300);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setTitle("Administración de Productos");
	 setLocationRelativeTo(null);
	 setLayout(null);
	 botones();
	}
	
	private void botones() {
		panel = new JPanel();
		panel.setBounds(30,80,200,95);
		panel.setBackground(Color.ORANGE);
		//TITULO LOGIN
		label1 = new JLabel("Click para subir el archivo");
		label1.setBounds(60,90,180,25);
		add(label1);
		//BOTON CARGAR DATOS
		CargarButton = new JButton("CARGAR ARCHIVO");
		CargarButton.setBounds(50,120,160,40);
		add(CargarButton);
		CargarButton.addActionListener(Crear);	
		this.add(panel);
		panel2 = new JPanel();
		panel2.setBounds(30,320,500,375);
		add(panel2);
		panel3 = new JPanel();
		panel3.setBounds(540,320,760,365);
		add(panel3);
			}
	
	ActionListener Crear=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {	
			String pathcsv = JOptionPane.showInputDialog(null,"Ingrese la ruta del archivo CSV");
	        String texto = "";
	        String texto2 = "";
	        Principal.Principal globals = new Principal.Principal();
				try {
					  File contenido = new File(pathcsv);
			            Scanner leo = new Scanner(contenido);
			            while (leo.hasNextLine()) {
			                texto += leo.nextLine()+",";			   
			            }	
			            String fila[] = texto.split(",");
	
			//-----------------------------------------
						  File contenido2 = new File(pathcsv);
				            Scanner leo2 = new Scanner(contenido2);
						    while (leo2.hasNextLine()) {
				                texto2 += leo2.nextLine()+"\n";			   
				            }	
				            String column[] = texto2.split("\n"); 
						prueba1(column,fila); 
						 
				}catch (IOException e1) {
		            System.out.println("La ruta del archivo es invalido");
		        }
		 }
	};
	private void prueba1(String[] column, String[] fila) {
 		limite = column.length;		  

		int cont=0;
		for(int i =0;i<column.length;i++) {
			for(int j =0;j<4;j++) {
				paths[i][j] = fila[cont];
				cont++; 	 
             }
		}
      CambioTipo(paths,column);

	}
	 private void CambioTipo(String[][] paths,String[] colum) {
		 No = new int[colum.length];
		 nameprod= new String[colum.length];
		 precprod = new float [colum.length];
		 cantprod = new int [colum.length];
		 Avatarprod= new String[colum.length];	

		 for(int i=0; i<colum.length;i++) {
			 No[i]=i+1;
			 nameprod[i] = paths[i][0];			 
			 precprod[i] = Float.parseFloat(paths[i][1]);
			 cantprod[i]= Integer.parseInt(paths[i][2]);
			 Avatarprod[i] = paths[i][3];			
		 }
		 creartabla(nameprod,precprod,cantprod,Avatarprod,No);
	 }

	private void creartabla(String[] nameprod, float[] precprod2,int[] cantprod2, String[] avatarcliente2, int[] no) {
		miModelo = new DefaultTableModel();
		miModelo.addColumn("No.");
		miModelo.addColumn("Nombre");
		miModelo.addColumn("Precio");
		miModelo.addColumn("Cantidad");
		
		for(int i =0;i<nameprod.length;i++) {
		miModelo.addRow(new Object[]{no[i],nameprod[i],precprod2[i],cantprod2[i]});
		}
		
		tabla = new JTable (miModelo);
		JScrollPane scrollpane = new JScrollPane(tabla);
		tabla.setBounds(295,30,450,270);
		scrollpane.setBounds(295,30,780,270);

		add(scrollpane);
		agrupardatos(miModelo);

	}

	private void agrupardatos(DefaultTableModel miModelo2) {
		try {
			dtsc = new DefaultPieDataset();

			for(int i=0;i<miModelo2.getRowCount();i++) {
				String producto = miModelo2.getValueAt(i, 1).toString();	
	            int cant = Integer.valueOf(miModelo2.getValueAt(i , 3).toString());
	            dtsc.setValue(producto,cant);
				}

			JFreeChart ch = ChartFactory.createPieChart("Productos más vendidos", dtsc, true, true, false);
			cp4 = new ChartPanel(ch);
			cp4.setPreferredSize(new Dimension(500,370));
			panel2.removeAll();
			panel2.add(cp4);
			panel2.validate();
			panel2.setVisible(true);
	        //add(cp4);
	        //cp4.setBounds(50,320,470,370);

			}catch(Exception e){
				System.out.println("ERRORRR");
			}	
		try {
			dataset3 = new DefaultCategoryDataset();
			
			for(int i=0;i<miModelo2.getRowCount();i++) {
			String edad = miModelo2.getValueAt(i, 0).toString();	
            Double Nos = Double.valueOf(miModelo2.getValueAt(i , 2).toString());
            dataset3.addValue(Nos, "Precio", edad);
			}
			
	        JFreeChart chart = ChartFactory.createBarChart("Cantidad de productos por rango de precio", "No.Productos", "Precio", dataset3);
	        cp5 = new ChartPanel(chart);
			cp5.setPreferredSize(new Dimension(760,360));
	        panel3.removeAll();
			panel3.add(cp5);
			panel3.validate();
			panel3.setVisible(true);
	        //add(cp);
	        //cp.setBounds(540,320,760,360);
				
		}catch(Exception e) {
				System.out.println("ERRORRR");
			}
		//BOTON Nuevo Cliente
		NuevoProd = new JButton("Nuevo Producto");
		NuevoProd.setBounds(1100,75,160,40);
		add(NuevoProd);
		NuevoProd.addActionListener(NEW);	
		 //BOTON Nuevo Cliente
		Regresar = new JButton("Regresar");
		Regresar.setBounds(1100,225,160,40);
		add(Regresar);
		Regresar.addActionListener(Back1);
		//Button Busqueda CLIENTE
		 ConsultaProd = new JButton("Consultar Producto");
		 ConsultaProd.setBounds(1100,175,160,40);
		 add(ConsultaProd);
		 ConsultaProd.addActionListener(consulta1);
		//Button Busqueda CLIENTE
		 DeleteProd = new JButton("Eliminar Producto");
		 DeleteProd.setBounds(1100,125,160,40);
		 add(DeleteProd);
		 DeleteProd.addActionListener(delProd);
		 setSize(1360,750);
	        setLocationRelativeTo(null);
	}
	
	
	
	
	ActionListener delProd=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
			 String busqueda= JOptionPane.showInputDialog("Eliminación de Producto (Nombre): ");
			 int cont=0;
			 int suma;
			 DefaultTableModel m;
			 suma=limite;
			 int fil;
			 try {
				
			for(int i =0;i<paths.length;i++) {
			 if(busqueda.equals(paths[i][0])) {
				cont=i;
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el producto?","Eliminar",JOptionPane.YES_NO_OPTION);
				if(resp==JOptionPane.YES_OPTION) {
				  m=(DefaultTableModel) tabla.getModel();
				  m.removeRow(cont);	
					}
				break;
			  }	
			 
			}
			
			updatechart();
			updatechart2();
			 }catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Error. Producto no encontrado");
 
			 }
			 
		 }
	};
	
	
	ActionListener consulta1=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {	
			 AgregarProd comp = new AgregarProd();
			 String busqueda= JOptionPane.showInputDialog("Consulta de Producto (Nombre): ");
			 try {
			 for(int i =0;i<paths.length;i++) {
			 if(busqueda.equals(paths[i][0])) {
				 JOptionPane.showMessageDialog(null,"Nombre: "+paths[i][0]+"\n"+"Precio: "+
				 paths[i][1]+"\n"+"Cantidad: "+paths[i][2]+"\n","Información Producto",JOptionPane.INFORMATION_MESSAGE); 
			 break;
			 }
			 }
				 
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null,"Error. Producto no encontrado");
			}
		
			 }
		 
	
	};
	

	ActionListener NEW=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {	
			 setVisible(true);
			 AgregarProd addprod = new AgregarProd();
			 addprod.setVisible(true); 
		 }
	};
	ActionListener Back1=new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {	
			 setVisible(false);
			 Administracion admins = new Administracion();
			 admins.setVisible(true); 
		 }
	};
	public void updatechart01() {
		try {
			panel2.setVisible(false);
			panel3.setVisible(false);
			for(int i=0;i<miModelo.getRowCount();i++) {
				String producto = miModelo.getValueAt(i, 1).toString();	
	            int cant = Integer.valueOf(miModelo.getValueAt(i , 3).toString());
	            dtsc.setValue(producto,cant);
				}

			JFreeChart ch = ChartFactory.createPieChart("Productos más vendidos", dtsc, true, true, false);
	        ChartPanel cp = new ChartPanel(ch);
	        add(cp);
	        cp.setBounds(30,320,500,375);
	            
	        
			}catch(Exception e) {
				System.out.println("ERROR");
			}

	}
	public void updatechart0() {
		try {

			for(int i=0;i<miModelo.getRowCount();i++) {
				String producto = miModelo.getValueAt(i, 1).toString();	
	            int cant = Integer.valueOf(miModelo.getValueAt(i , 3).toString());
	            dtsc.setValue(producto,cant);
				}

			JFreeChart ch = ChartFactory.createPieChart("Productos más vendidos", dtsc, true, true, false);
	        ChartPanel cp = new ChartPanel(ch);
	        add(cp);
	        cp.setBounds(30,320,500,375);
	            
	        
			}catch(Exception e) {
				System.out.println("ERROR");
			}

	}
	public void updatechart() {
		try {
			panel2.setVisible(false);
			panel3.setVisible(false);

			dtsc = new DefaultPieDataset();

			for(int i=0;i<miModelo.getRowCount();i++) {
				String producto = miModelo.getValueAt(i, 1).toString();	
	            int cant = Integer.valueOf(miModelo.getValueAt(i , 3).toString());
	            dtsc.setValue(producto,cant);
				}

			JFreeChart ch = ChartFactory.createPieChart("Productos más vendidos", dtsc, true, true, false);
	        ChartPanel cp = new ChartPanel(ch);
	        add(cp);
	        cp.setBounds(30,320,500,375);
	            
	        
			}catch(Exception e) {
				System.out.println("ERROR");
			}

	}
	public void updatechartp() {
		try {
			for(int i=0;i<miModelo.getRowCount();i++) {
				String producto = miModelo.getValueAt(i, 1).toString();	
	            int cant = Integer.valueOf(miModelo.getValueAt(i , 3).toString());
	            dtsc.setValue(producto,cant);
				}
	    	miModelo.setRowCount(0);
			JFreeChart ch = ChartFactory.createPieChart("Productos más vendidos", dtsc, true, true, false);
	        ChartPanel cp = new ChartPanel(ch);
	        add(cp);
	        cp.setBounds(30,320,500,375);
	            
	        
			}catch(Exception e) {
				System.out.println("ERROR");
			}

	}
	public void updatechart2() {
		try {
			panel3.setVisible(false);
			panel2.setVisible(false);

			dataset3 = new DefaultCategoryDataset();
			
			for(int i=0;i<miModelo.getRowCount();i++) {
			String edad = miModelo.getValueAt(i, 0).toString();	
            Double Nos = Double.valueOf(miModelo.getValueAt(i , 2).toString());
            dataset3.addValue(Nos, "Precio", edad);
			}
			
	        JFreeChart chart = ChartFactory.createBarChart("Cantidad de productos por rango de precio", "No.Productos", "Precio", dataset3);
	        ChartPanel cp = new ChartPanel(chart);
	        add(cp);
	        cp.setBounds(540,320,760,365);
		}catch(Exception e) {
			System.out.println("ERROR");
		}
	}
	public void updatechart2_1() {
		try {	

			for(int i=0;i<miModelo.getRowCount();i++) {
			String edad = miModelo.getValueAt(i, 0).toString();	
            Double Nos = Double.valueOf(miModelo.getValueAt(i , 2).toString());
            dataset3.addValue(Nos, "Precio", edad);
			}
			
	        JFreeChart chart = ChartFactory.createBarChart("Cantidad de productos por rango de precio", "No.Productos", "Precio", dataset3);
	        ChartPanel cp = new ChartPanel(chart);
	        add(cp);
	        cp.setBounds(540,320,760,365);
		}catch(Exception e) {
			System.out.println("ERROR");
		}
	}
	
}
