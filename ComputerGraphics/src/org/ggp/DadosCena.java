package org.ggp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cg.Cena;
import cg.Display;
import cg.Face;
import cg.PlanoProjecao;
import cg.Poliedro;
import cg.Ponto;
import cg.Vetor3D;

public class DadosCena extends JFrame {

	private JPanel contentPane;
	private JPanel panelObservador;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField tfPontoObservador;
	private JTextField tfPontoPlano1;
	private JTextField tfPontoPlano2;
	private JTextField tfPontoPlano3;
	private JTextField tfNumVertice;
	private JTextField tfNumFaces;
	private JScrollPane scrollPaneTbVertices;
	private JTable tbVertices;
	private JTable tbFaces;
	private JRadioButton rdbtnPerspectiva;
	private JRadioButton rdbtnParalela;
	private JRadioButton rdbtnWireframe;
	private JRadioButton rdbtnSuperfcie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DadosCena frame = new DadosCena();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DadosCena() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{146, 0};
		gbl_panel.rowHeights = new int[]{73, 73, 47, 87, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Proje\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		rdbtnPerspectiva = new JRadioButton("Perspectiva");
		rdbtnPerspectiva.addActionListener(new paraPerspectiva());
		panel_1.add(rdbtnPerspectiva);
		rdbtnPerspectiva.setSelected(true);
		buttonGroup.add(rdbtnPerspectiva);
		
		rdbtnParalela = new JRadioButton("Paralela");
		rdbtnParalela.addActionListener(new paraParalela());
		panel_1.add(rdbtnParalela);
		buttonGroup.add(rdbtnParalela);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo do Desenho", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		rdbtnWireframe = new JRadioButton("Wireframe   ");
		panel_2.add(rdbtnWireframe);
		rdbtnWireframe.setSelected(true);
		buttonGroup_1.add(rdbtnWireframe);
		
		rdbtnSuperfcie = new JRadioButton("Superf\u00EDcie");
		panel_2.add(rdbtnSuperfcie);
		buttonGroup_1.add(rdbtnSuperfcie);
		
		panelObservador = new JPanel();
		panelObservador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ponto do Observador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelObservador = new GridBagConstraints();
		gbc_panelObservador.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_panelObservador.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelObservador.insets = new Insets(0, 0, 5, 0);
		gbc_panelObservador.gridx = 0;
		gbc_panelObservador.gridy = 2;
		panel.add(panelObservador, gbc_panelObservador);
		panelObservador.setLayout(new BoxLayout(panelObservador, BoxLayout.Y_AXIS));
		
		tfPontoObservador = new JTextField();
		tfPontoObservador.setToolTipText("x;y;z");
		panelObservador.add(tfPontoObservador);
		tfPontoObservador.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Pontos do Plano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 3;
		panel.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		tfPontoPlano1 = new JTextField();
		panel_4.add(tfPontoPlano1);
		tfPontoPlano1.setToolTipText("x;y;z");
		tfPontoPlano1.setColumns(10);
		
		tfPontoPlano2 = new JTextField();
		panel_4.add(tfPontoPlano2);
		tfPontoPlano2.setToolTipText("x;y;z");
		tfPontoPlano2.setColumns(10);
		
		tfPontoPlano3 = new JTextField();
		panel_4.add(tfPontoPlano3);
		tfPontoPlano3.setToolTipText("x;y;z");
		tfPontoPlano3.setColumns(10);
		
		JButton btnRenderizar = new JButton("Renderizar");
		btnRenderizar.addActionListener(new Renderizacao());
		GridBagConstraints gbc_btnRenderizar = new GridBagConstraints();
		gbc_btnRenderizar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRenderizar.gridx = 0;
		gbc_btnRenderizar.gridy = 4;
		panel.add(btnRenderizar, gbc_btnRenderizar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Objeto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "V\u00E9rtices", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_5, BorderLayout.WEST);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblNmeroDeVrtices = new JLabel("N\u00FAmero de V\u00E9rtices");
		GridBagConstraints gbc_lblNmeroDeVrtices = new GridBagConstraints();
		gbc_lblNmeroDeVrtices.insets = new Insets(0, 0, 5, 0);
		gbc_lblNmeroDeVrtices.gridx = 0;
		gbc_lblNmeroDeVrtices.gridy = 0;
		panel_5.add(lblNmeroDeVrtices, gbc_lblNmeroDeVrtices);
		
		tfNumVertice = new JTextField();
		tfNumVertice.addActionListener(new criarTabelaVertice());
		GridBagConstraints gbc_tfNumVertice = new GridBagConstraints();
		gbc_tfNumVertice.insets = new Insets(0, 0, 5, 0);
		gbc_tfNumVertice.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumVertice.gridx = 0;
		gbc_tfNumVertice.gridy = 1;
		panel_5.add(tfNumVertice, gbc_tfNumVertice);
		tfNumVertice.setColumns(10);
		
		scrollPaneTbVertices = new JScrollPane();
		GridBagConstraints gbc_scrollPaneTbVertices = new GridBagConstraints();
		gbc_scrollPaneTbVertices.gridheight = 2;
		gbc_scrollPaneTbVertices.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneTbVertices.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneTbVertices.gridx = 0;
		gbc_scrollPaneTbVertices.gridy = 2;
		panel_5.add(scrollPaneTbVertices, gbc_scrollPaneTbVertices);
		
		tbVertices = new JTable();
		scrollPaneTbVertices.setViewportView(tbVertices);
		scrollPaneTbVertices.getViewport().setPreferredSize(new Dimension(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Faces", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_6, BorderLayout.CENTER);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblNmeroDeFaces = new JLabel("N\u00FAmero de Faces");
		GridBagConstraints gbc_lblNmeroDeFaces = new GridBagConstraints();
		gbc_lblNmeroDeFaces.insets = new Insets(0, 0, 5, 0);
		gbc_lblNmeroDeFaces.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNmeroDeFaces.gridx = 0;
		gbc_lblNmeroDeFaces.gridy = 0;
		panel_6.add(lblNmeroDeFaces, gbc_lblNmeroDeFaces);
		
		tfNumFaces = new JTextField();
		tfNumFaces.addActionListener(new criarTabelaFace());
		GridBagConstraints gbc_tfNumFaces = new GridBagConstraints();
		gbc_tfNumFaces.insets = new Insets(0, 0, 5, 0);
		gbc_tfNumFaces.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNumFaces.gridx = 0;
		gbc_tfNumFaces.gridy = 1;
		panel_6.add(tfNumFaces, gbc_tfNumFaces);
		tfNumFaces.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_6.add(scrollPane, gbc_scrollPane);
		
		tbFaces = new JTable();
		scrollPane.setViewportView(tbFaces);
		scrollPane.getViewport().setPreferredSize(new Dimension(0, 0));
	}
	
	class paraParalela implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			panelObservador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direção de Projeção", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			tfPontoObservador.setToolTipText("i;j;k");
		}		
	}
	
	class paraPerspectiva implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			panelObservador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ponto do Observador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));		
			tfPontoObservador.setToolTipText("x;y;z");
		}		
	}
	
	class criarTabelaVertice implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int parseInt = Integer.parseInt(tfNumVertice.getText());
				tbVertices.setModel(new VerticeTableModel(parseInt));
			} catch (Exception e2) {
				tfNumVertice.setText("");
			}			
		}		
	}

	class criarTabelaFace implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int parseInt = Integer.parseInt(tfNumFaces.getText());
				tbFaces.setModel(new FaceTableModel(parseInt));				
			} catch (Exception e2) {
				e2.printStackTrace();
				tfNumFaces.setText("");
			}			
		}		
	}

	class Renderizacao implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Ponto[] pontosPlano =  getPontosPlano();
			PlanoProjecao planoProjecao = new PlanoProjecao(pontosPlano[1], pontosPlano[0], pontosPlano[2]);
			Ponto pontoObservador = getObservador();
			ArrayList<Ponto> vertices = getVertices();
			ArrayList<Face> faces = getFaces(vertices);
			Poliedro objeto = new Poliedro(faces, vertices);
			Cena cena = null;
			if (rdbtnPerspectiva.isSelected()) {				
				cena = new Cena(pontoObservador, planoProjecao);
				cena.adicionarObjeto(objeto);
				cena.renderizar(Cena.TipoProjecao.PERSPECTIVA);
			}
			if (rdbtnParalela.isSelected()) {
				Vetor3D direcaoProjecao = new Vetor3D(pontoObservador.getX(), pontoObservador.getY(), pontoObservador.getZ());
				cena = new Cena(direcaoProjecao, planoProjecao);
				cena.adicionarObjeto(objeto);
				cena.renderizar(Cena.TipoProjecao.PARALELA);
			}
			Display display = null;
			if (rdbtnWireframe.isSelected()) {
				display = new Display(cena, Display.TipoRenderizacao.WIREFRAME);
			}
			if (rdbtnSuperfcie.isSelected()) {
				display = new Display(cena, Display.TipoRenderizacao.SUPERFICIE);
			}
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(320, 240);
			frame.setContentPane(display);					
			frame.pack();
			frame.setVisible(true);			
		}

		private ArrayList<Face> getFaces(ArrayList<Ponto> vertices) {
			FaceTableModel model = (FaceTableModel) tbFaces.getModel();
			Integer[][] verticesFace = model.verticeFace;
			Integer[] numVerticeFace = model.numVerticeFace;
			ArrayList<Face> faces = new ArrayList<Face>();
			for (int i = 0; i < numVerticeFace.length; i++) {
				ArrayList<Integer> indiceVertices = new ArrayList<Integer>();
				System.out.println(i);
				for (int j = 0; j < numVerticeFace[i]; j++) {
					System.out.println(j);
					indiceVertices.add(verticesFace[i][j]-1);
				}
				faces.add(new Face(indiceVertices, vertices));
			}
			return faces;
		}

		private ArrayList<Ponto> getVertices() {
			ArrayList<Ponto> vertices = new ArrayList<Ponto>();
			VerticeTableModel model = (VerticeTableModel) tbVertices.getModel(); 
			Double[] valuesX = model.valuesX;
			Double[] valuesY = model.valuesY;
			Double[] valuesZ = model.valuesZ;
			for (int i = 0; i < valuesZ.length; i++) {
				vertices.add(new Ponto(valuesX[i], valuesY[i], valuesZ[i]));
			}
			return vertices;
		}

		private Ponto getObservador() {
			Ponto observador = null;
			Double[] valores = new Double[3];
			try {
				String[] partes = tfPontoObservador.getText().split(";");
				for (int i = 0; i < partes.length; i++) {
					valores[i] = Double.parseDouble(partes[i]);
				}
				observador = new Ponto(valores[0], valores[1], valores[2]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return observador;
		}

		private Ponto[] getPontosPlano() {
			Ponto[] plano = new Ponto[3];
			Double[] valores = new Double[3];
			try {
				String[] partes = tfPontoPlano1.getText().split(";");
				for (int i = 0; i < partes.length; i++) {
					valores[i] = Double.parseDouble(partes[i]);
				}
				plano[0] = new Ponto(valores[0], valores[1], valores[2]);
				partes = tfPontoPlano2.getText().split(";");
				for (int i = 0; i < partes.length; i++) {
					valores[i] = Double.parseDouble(partes[i]);
				}
				plano[1] = new Ponto(valores[0], valores[1], valores[2]);
				partes = tfPontoPlano3.getText().split(";");
				for (int i = 0; i < partes.length; i++) {
					valores[i] = Double.parseDouble(partes[i]);
				}
				plano[2] = new Ponto(valores[0], valores[1], valores[2]);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			return plano;
		}
		
	}
}
