package com.util.entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.export.AccessImp;
import com.util.export.AccessRead;
import com.util.export.HxlsImp;
import com.util.export.XxlsImp;
import com.util.tools.Log;

public class Entry {

	private ArrayList<String> list = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
	   Log.info("start now");
       Entry entry = new Entry();
       entry.dbFileBrower();
       entry.dbFileReadHead();
	}
	
	private void dbFileBrower(){
		File file = new File(".");
		String[] listfile = file.list();
		
		for(int loop = 0; loop < listfile.length; loop++){
			
			if(listfile[loop].endsWith(".mdb") ||
			   listfile[loop].endsWith(".xls") ||
			   listfile[loop].endsWith(".xlsx")){
				list.add(listfile[loop]);
			}
		}
		
	}
	
	private void dbFileReadHead() throws SQLException, IOException{
		Iterator it = list.iterator();
		
		String name = null;
		while(it.hasNext()){
			name = (String) it.next();
			System.out.println(name);
			if(name.endsWith(".mdb")){
				AccessRead access = new AccessImp();
				access.read(name);
			}
			else if(name.endsWith(".xls")){
				System.out.println(name);
				HxlsImp xls2csv;
				try {
					xls2csv = new HxlsImp(name);
					xls2csv.process();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}	
			else{
				XxlsImp howto = new XxlsImp();
				try {
					howto.process(name);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}


}
