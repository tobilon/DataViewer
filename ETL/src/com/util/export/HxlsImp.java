package com.util.export;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class HxlsImp extends HxlsAbstract{
	
	private ArrayList<String> dataList = new ArrayList<String>();
	private ArrayList<String> tableHeadList = new ArrayList<String>();
	//private String fileName;
	

	public HxlsImp(String filename) throws IOException,
			FileNotFoundException, SQLException {
		
		super(filename);
		//this.fileName=filename;
	}

	@Override
	public void optRows(int sheetIndex,int curRow, List<String> rowlist) throws SQLException {
		//±íÍ· 
		if(curRow == 0){
			tableHeadList.clear();
			dataList.clear();
		    for (int i = 0 ;i< rowlist.size();i++){
		    	
			  //  System.out.print("'"+rowlist.get(i)+"',");
			    tableHeadList.add(rowlist.get(i));
			    dataList.add(rowlist.get(i));
		    }
		    CusteomerExport.anasys(dirname,filename,tableHeadList, dataList,true);
		}
		else {
			dataList.clear();
		    for (int i = 0 ;i< rowlist.size();i++){
		    	
			 //   System.out.print("'"+rowlist.get(i)+"',");
			    dataList.add(rowlist.get(i));
			    
		    }
		    CusteomerExport.anasys(dirname,filename,tableHeadList, dataList,false);
		    
		}
		
		//System.out.println();
	}
	
	public ArrayList<String> getDataList()
	{
		return dataList;
	}
	
	public ArrayList<String> getHeadList()
	{
		return tableHeadList;
	}
}
