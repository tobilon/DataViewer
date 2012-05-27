package com.util.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.util.export.HxlsImp;
import com.util.export.XxlsImp;

public class XxlsImpTest {

	@Test
	public void load_null_file()
	{
		XxlsImp xls2csv;
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("","");
		} catch (Exception e) {
			System.out.println("文件不存在!");
		}		
	}
	
	@Test
	public void load_empty_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/empty.xlsx","");
			headList = xls2csv.getHeadList();
			assertEquals(0, headList.size());
		} catch (Exception e) {		
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的列数1，读取第一列“abc123”
	public void load_one_cell_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/one_cell.xlsx","");
			headList = xls2csv.getHeadList();
			assertEquals(1, headList.size());
			assertEquals("abc123",headList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//读取前两列，第一列“abc123”，第二列“efg456”
	public void load_two_cell_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/two_cell.xlsx","");
			headList = xls2csv.getHeadList();
			assertEquals(2, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//读取前两行，没有内容的单元格默认值为" ",有内容的空格被过滤了
	public void load_two_row_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/two_row.xlsx","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(2, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
			assertEquals(" ", dataList.get(0));
			assertEquals("xyz789", dataList.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的列数2，dataList保存最后一行数据
	//问题：前二个空格没有体现
	public void load_three_row_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/three_row.xlsx","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(2, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
			assertEquals("zxcv", dataList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，dataList保存最后一行数据
	//问题：第二页的headlist不是第一行而是第一空行
	//问题：第二页的前一个空格被过滤
	public void load_two_sheet_xls()
	{
		XxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new XxlsImp();
			xls2csv.process("data/excel2007/two_sheet.xlsx","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(1, headList.size());
			assertEquals("123", headList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test	
	public void load_normal_xls(){
		try {
		    XxlsImp howto = new XxlsImp();
		    //howto.process("big.xlsx","dd");
		
	    } 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
