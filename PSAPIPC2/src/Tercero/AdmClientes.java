package Tercero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import Secundario.Frames;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;

public class AdmClientes extends JFrame{
	private static JPanel panel;
	private static JLabel label1;
	private static JButton CargarButton;
	public static DefaultTableModel miModelo;
	private static JTable tabla;
	private static DefaultPieDataset dtsc;
	private static DefaultCategoryDataset dataset3;
	private static JButton NuevoCliente;
	private static JButton Regresar;
	private static JButton ConsultaCliente;
	private static JButton DeleteCliente;
	public static String paths[][]= new String[100][5];

	
	
	public static int limite=0;
	
	public static int[] No;
	public static String[] namecliente;
	public static int [] Edadcliente;
	public static char [] secliente;
	public static int [] NITcliente;
	public static String[] Avatarcliente;
	public static String[] SeclienteS;

	private static int M = 0,F=0;
	public static JPanel panel2;
	private static JPanel panel3;
	private static ChartPanel cp4;
	private static ChartPanel cp5;
	public AdmClientes() {
		 setSize(275,300);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setTitle("Administración de clientes");
		 setLocationRelativeTo(null);
		 setLayout(null);
		 botones();
		 }

	private void botones() {
		panel = new JPanel();
		panel.setBounds(30,80,200,95);
		panel.setBackground(Color.YELLOW);
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
	
						  System.out.println("");
			//-----------------------------------------
						  File contenido2 = new File(pathcsv);
				            Scanner leo2 = new Scanner(contenido2);
						    while (leo2.hasNextLine()) {
				                texto2 += leo2.nextLine()+"\n";			   
				            }	
				            String column[] = texto2.split("\n");
				    		prueba(column,fila,paths);	  

				}catch (Exception e1) {
					 JOptionPane.showMessageDialog(null, "Archivo no encontrado"); 
		        }
		 }

	
	};
	
	
		private void prueba(String[] column, String[] fila, String[][] paths) {
	 		limite = column.length;		  

			int cont=0;
			for(int i =0;i<column.length;i++) {
				for(int j =0;j<5;j++) {
					paths[i][j] = fila[cont];
					cont++; 	 
	             }
			}
	      LlenarArreglo(paths,column);

		}
		
		
		 private void LlenarArreglo(String[][] paths,String[] colum) {
			  No =new int[colum.length];
			  namecliente = new String[colum.length];
			  Edadcliente= new int[colum.length];
			  secliente = new char[colum.length];
			  NITcliente = new int[colum.length];
			  Avatarcliente = new String[colum.length];			 
			  SeclienteS = new String[colum.length];


			 for(int i=0; i<colum.length;i++) {
				 No[i]=i+1;
				 namecliente[i] = paths[i][0];			 
				 Edadcliente[i] = Integer.parseInt(paths[i][1]);
				 SeclienteS[i]= paths[i][2];
				 NITcliente[i] = Integer.parseInt(paths[i][3]);
				 Avatarcliente[i] = paths[i][4];			
			 }
	
			 creartabla(No,namecliente,Edadcliente,secliente,NITcliente,Avatarcliente,SeclienteS);
		 }

	

		 private void creartabla(int[] no2, String[] namecliente2, int[] edadcliente2, char[] secliente2,int[] nITcliente2, String[] avatarcliente2, String[] seclienteS) {
			miModelo = new DefaultTableModel();
			miModelo.addColumn("No.");
			miModelo.addColumn("Nombre");
			miModelo.addColumn("Edad");
			miModelo.addColumn("Sexo");
			miModelo.addColumn("NIT");
			
			for(int i =0;i<namecliente2.length;i++) {
			miModelo.addRow(new Object[]{no2[i],namecliente2[i],edadcliente2[i],seclienteS[i],nITcliente2[i]});
			}
			
			tabla = new JTable (miModelo);
			JScrollPane scrollpane = new JScrollPane(tabla);
			tabla.setBounds(295,30,760,270);
			scrollpane.setBounds(295,30,760,270);

			add(scrollpane);
			
			agrupardatos(miModelo);

		}
		
		private void agrupardatos(DefaultTableModel modelo) {
			try {
			dtsc = new DefaultPieDataset();

			for(int i=0;i<modelo.getRowCount();i++) {
				if(modelo.getValueAt(i, 3).equals("M")) {
					M++;
				}else if(modelo.getValueAt(i, 3).equals("F")){
					F++;
				}		
			}
			dtsc.setValue("Masculino",M);
			dtsc.setValue("Femenino",F);
			
			JFreeChart ch = ChartFactory.createPieChart3D("Clientes por Sexo", dtsc, true, true, false);
	        ChartPanel cp = new ChartPanel(ch);
			cp4 = new ChartPanel(ch);
			cp4.setPreferredSize(new Dimension(500,375));
			panel2.removeAll();
			panel2.add(cp4);
			panel2.validate();
			panel2.setVisible(true);
	            
			}catch(Exception e) {
				System.out.println("ERRORRR");
			}
			
			try {
				dataset3 = new DefaultCategoryDataset();
				
				for(int i=0;i<modelo.getRowCount();i++) {
				String edad = modelo.getValueAt(i, 0).toString();	
	            Double Nos = Double.valueOf(modelo.getValueAt(i , 2).toString());
	            dataset3.addValue(Nos, "Edad", edad);
				}
				
		        JFreeChart chart = ChartFactory.createBarChart("Cantidad de clientes por rango de edad", "No.Personas", "Edad", dataset3);
		        cp5 = new ChartPanel(chart);
				cp5.setPreferredSize(new Dimension(760,365));
		        panel3.removeAll();
				panel3.add(cp5);
				panel3.validate();
				panel3.setVisible(true);
					
			}catch(Exception e) {
					System.out.println("ERRORRR");
				}
			
			 //BOTON Nuevo Cliente
			NuevoCliente = new JButton("Nuevo Cliente");
			NuevoCliente.setBounds(1100,75,160,40);
			add(NuevoCliente);
			NuevoCliente.addActionListener(NEW);	
			//Button Busqueda CLIENTE
			 ConsultaCliente = new JButton("Consultar Cliente");
			 ConsultaCliente.setBounds(1100,175,160,40);
			 add(ConsultaCliente);
			 ConsultaCliente.addActionListener(consulta1);
				//Button Busqueda CLIENTE
			 DeleteCliente = new JButton("Eliminar Cliente");
			 DeleteCliente.setBounds(1100,125,160,40);
			 add(DeleteCliente);
			 DeleteCliente.addActionListener(delconsulta);
			 //BOTON Nuevo Cliente
				Regresar = new JButton("Regresar");
				Regresar.setBounds(1100,225,160,40);
				add(Regresar);
				Regresar.addActionListener(Back1);

		    setSize(1380,750);
	        setLocationRelativeTo(null);

		}
		ActionListener delconsulta=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 try {

				 int busqueda= Integer.parseInt(JOptionPane.showInputDialog("Eliminación de Cliente (NIT): "));
				 int cont=0;
				 int suma;
				 DefaultTableModel m;
				 suma=limite;
				 int fil;					
				for(int i =0;i<paths.length;i++) {
				 if(busqueda==Integer.parseInt(paths[i][3])) {
					 cont=i;
					int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el cliente?","Eliminar",JOptionPane.YES_NO_OPTION);
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
				JOptionPane.showMessageDialog(null,"Cliente no encontrado");	 
				 }
				 
			 }
		};
	
		ActionListener consulta1=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {	
				 AgregarCliente comp = new AgregarCliente();
				 try {
				 int busqueda= Integer.parseInt(JOptionPane.showInputDialog("Consulta de Cliente (NIT): "));
				
				 for(int i =0;i<paths.length;i++) {
				 if(busqueda==Integer.parseInt(paths[i][3])) {
					 JOptionPane.showMessageDialog(null,"Nombre: "+paths[i][0]+"\n"+"Edad: "+
					 paths[i][1]+"\n"+"Sexo: "+paths[i][2]+"\n"+"NIT: "+paths[i][3]+"\n","Información Cliente",JOptionPane.INFORMATION_MESSAGE); 
				 break;
				 }
				 }
			
				 
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"Error. Usuario no encontrado");
				}
				
					 }	
		
		};
						
		ActionListener NEW=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {	
				 setVisible(true);
				 AgregarCliente addcliente = new AgregarCliente();
				 addcliente.setVisible(true); 
			 }
		};
		
		ActionListener Back1=new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {	
				 setVisible(false);
				 Administracion adms = new Administracion();
				 adms.setVisible(true); 
			 }
		};
		public void updatechart_1() {
			try {

				M=0;
				F=0;
				for(int i=0;i<miModelo.getRowCount();i++) {
					if(miModelo.getValueAt(i, 3).equals("M")) {
						M++;
					}else if(miModelo.getValueAt(i, 3).equals("F")){
						F++;
					}		
				}
				dtsc.setValue("Masculino",M);
				dtsc.setValue("Femenino",F);
				
				JFreeChart ch = ChartFactory.createPieChart3D("Clientes por Sexo", dtsc, true, true, false);
		        ChartPanel cp = new ChartPanel(ch);
		        add(cp);
		        cp.setBounds(30,320,500,375);
		        
				}catch(Exception e) {
					System.out.println("ERROR");
				}
	
		}
		public void updatechart() {
			try {
				panel3.setVisible(false);
				panel2.setVisible(false);
				M=0;
				F=0;
				for(int i=0;i<miModelo.getRowCount();i++) {
					if(miModelo.getValueAt(i, 3).equals("M")) {
						M++;
					}else if(miModelo.getValueAt(i, 3).equals("F")){
						F++;
					}		
				}
				dtsc.setValue("Masculino",M);
				dtsc.setValue("Femenino",F);
				
				JFreeChart ch = ChartFactory.createPieChart3D("Clientes por Sexo", dtsc, true, true, false);
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
			            dataset3.addValue(Nos, "Edad", edad);
						}
						
				        JFreeChart chart2 = ChartFactory.createBarChart("Cantidad de clientes por rango de edad", "No.Personas", "Edad", dataset3);
				        ChartPanel cp2 = new ChartPanel(chart2);
				        add(cp2);
				        cp2.setBounds(540,320,760,365);	
			}catch(Exception e) {
				System.out.println("ERROR");
			}
		}
		public void updatechart2_1() {
			try {
				
			        for(int i=0;i<miModelo.getRowCount();i++) {
						String edad = miModelo.getValueAt(i, 0).toString();	
			            Double Nos = Double.valueOf(miModelo.getValueAt(i , 2).toString());
			            dataset3.addValue(Nos, "Edad", edad);
						}
						
				        JFreeChart chart3 = ChartFactory.createBarChart("Cantidad de clientes por rango de edad", "No.Personas", "Edad", dataset3);
				        ChartPanel cp3 = new ChartPanel(chart3);
				        add(cp3);
				        cp3.setBounds(540,320,760,365);	
			}catch(Exception e) {
				System.out.println("ERROR");
			}
		}
}
