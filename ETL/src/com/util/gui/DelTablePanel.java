package com.util.gui;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.util.db.CustomerReport;
import com.util.db.DbCustomerOperator;

public class DelTablePanel extends JPanel implements ActionListener{

	static JTable table = null;
	final JPopupMenu popup = new JPopupMenu();
	final JMenuItem menuItem = new JMenuItem("��������ɾ��");
	final CustomerReport report = new CustomerReport();


	public DelTablePanel() {
		String[] headers = { "��¼��", "�ļ���", "�ļ�����", "�ܸ��� ", "�ɹ�����", "�ϲ�����",
				"����ʱ��" };
		Object[][] cellData = null;

		DefaultTableModel model = new DefaultTableModel(cellData, headers) {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		popup.add(menuItem);
		menuItem.addActionListener(this);
		
		table = new JTable(model);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {

				// �Ƿ��Ҽ�����
				if (e.getClickCount() == 1
						&& SwingUtilities.isRightMouseButton(e)) {
					table.setColumnSelectionAllowed(false);
					table.setRowSelectionAllowed(true);
				
					popup.show(table,   e.getX(),   e.getY());
					
				} else {
					table.setColumnSelectionAllowed(false);
					table.setRowSelectionAllowed(false);
				}

			}
		});

		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane js = new JScrollPane(table);
		this.add(js);
	}

	public void fillTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);// ���ԭ����
		table.setRowHeight(20);

		// �������
		ArrayList<CustomerReport> list = new ArrayList<CustomerReport>();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			String[] arr = new String[7];
			CustomerReport report = (CustomerReport) it.next();

			arr[0] = String.valueOf(report.getId());
			arr[1] = report.getFilename();
			arr[2] = report.getUsertype();
			arr[3] = String.valueOf(report.getUsernum());
			arr[4] = String.valueOf(report.getUserImport());
			arr[5] = String.valueOf(report.getUserMerge());
			arr[6] = report.getTime();
			// ������ݵ����
			tableModel.addRow(arr);

		}
		// ���±��
		table.invalidate();

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		report.setId(Long.valueOf((String) table.getValueAt(row, 0)));
		report.setFilename((String)table.getValueAt(row, 1));
		report.setUsertype((String)table.getValueAt(row, 2));
		report.setUsernum(Long.valueOf((String)table.getValueAt(row, 3)));
		report.setUserImport(Long.valueOf((String)table.getValueAt(row, 4)));
		report.setUserMerge(Long.valueOf((String)table.getValueAt(row, 5)));
		report.setTime((String)table.getValueAt(row, 6));
		 
	    DbCustomerOperator op = new DbCustomerOperator();
	    op.deleteOracle(report);
	    
	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	    tableModel.removeRow(row);
		
		
	}

}
