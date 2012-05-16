package com.util.export;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class XxlsImp extends XxlsAbstract {

	private ArrayList<String> dataList = new ArrayList<String>();
	private ArrayList<String> tableHeadList = new ArrayList<String>();
	
	
	@Override
	public void optRows(int sheetIndex,int curRow, List<String> rowlist) throws SQLException {
		//±íÍ· 
		if(curRow == 0){
			tableHeadList.clear();
		    for (int i = 0 ;i< rowlist.size();i++){
		    	
			   // System.out.print("'"+rowlist.get(i)+"',");
			    tableHeadList.add(rowlist.get(i));
		    }
		    UsersExport.anasys(dirname,filename,tableHeadList, dataList,true);
		}
		else {
			dataList.clear();
		    for (int i = 0 ;i< rowlist.size();i++){
		    	
			   // System.out.print("'"+rowlist.get(i)+"',");
			    dataList.add(rowlist.get(i));
		    }
		    UsersExport.anasys(dirname,filename,tableHeadList, dataList,false);
		}
		//System.out.println();
		
	}

	public static void main(String[] args) throws Exception {
		XxlsImp howto = new XxlsImp();
		howto.process("big.xlsx","dd");
//		howto.processAllSheets("F:/new.xlsx");
	}

}
