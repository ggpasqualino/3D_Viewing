package org.ggp;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class FaceTableModel implements TableModel {

	Integer[] numFace;
	Integer[] numVerticeFace;
	Integer[][] verticeFace;
	
	String[] columnName = {"Face", "Número de Vértices", "Vértice"};
	
	
	public FaceTableModel(int numFaces) {
		criaNumFace(numFaces);
		numVerticeFace = new Integer[numFaces];
		verticeFace = new Integer[numFaces][20];
	}
	
	public void criaNumFace(int numFaces) {
		this.numFace = new Integer[numFaces];
		for (int i = 0; i < numFaces; i++) {
			this.numFace[i] = new Integer(i+1);
		}
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return Integer.class;
	}

	@Override
	public int getColumnCount() {
		return verticeFace[0].length + 2;
	}

	@Override
	public String getColumnName(int arg0) {
		switch (arg0) {
		case 0:
		case 1:
			return columnName[arg0];
		default:
			return columnName[2];
		}
	}

	@Override
	public int getRowCount() {
		return numFace.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch (arg1) {
		case 0:
			return numFace[arg0];
		case 1:
			return numVerticeFace[arg0];
		default:
			return verticeFace[arg0][arg1-2];
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		if (arg1 == 0) {
			return false;
		}
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		if (arg2 == 1) {
			numVerticeFace[arg1] = (Integer) arg0;
		} else if (arg2 > 1) {
			verticeFace[arg1][arg2-2] = (Integer) arg0;
		}
	}

}
