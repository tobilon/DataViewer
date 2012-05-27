package com.util.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.export.HxlsImp;
import org.junit.Test;

public class HxlsImpTest {
	
	@Test
	public void load_null_file()
	{
		HxlsImp xls2csv;
		try {
			xls2csv = new HxlsImp("");
			xls2csv.process("","data/excel2003");
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void load_empty_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/empty.xls");
			xls2csv.process("","");
			dataList = xls2csv.getDataList();
			assertEquals(0, dataList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的行数30+1，读取第一列“abc123”
	public void load_one_cell_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> headList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/one_cell.xls");
			xls2csv.process("","");
			headList = xls2csv.getHeadList();
			assertEquals(31, headList.size());
			assertEquals("abc123",headList.get(0));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的行数32，读取前两列，第一列“abc123”，第二列“efg456”
	public void load_two_cell_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> headList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/two_cell.xls");
			xls2csv.process("","");
			headList = xls2csv.getHeadList();
			assertEquals(32, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的行数32，读取前两行，没有内容的单元格默认值为" ",有内容的空格被过滤了
	public void load_two_row_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/two_row.xls");
			xls2csv.process("","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(32, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
			assertEquals(" ", dataList.get(0));
			assertEquals("xyz789", dataList.get(1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的行数32，dataList保存最后一行数据
	public void load_three_row_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/three_row.xls");
			xls2csv.process("","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(32, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
			assertEquals(" ", dataList.get(0));
			assertEquals(" ", dataList.get(1));
			assertEquals("zxcv", dataList.get(2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	//列数解析与内容无关，初始化的行数32，dataList保存最后一行数据
	public void load_two_sheet_xls()
	{
		HxlsImp xls2csv;
		ArrayList<String> headList;
		ArrayList<String> dataList;
		
		try {
			xls2csv = new HxlsImp("data/excel2003/two_sheet.xls");
			xls2csv.process("","");
			headList = xls2csv.getHeadList();
			dataList = xls2csv.getDataList();
			assertEquals(32, headList.size());
			assertEquals("abc123",headList.get(0));
			assertEquals("efg456",headList.get(1));
			assertEquals(" ", dataList.get(0));
			assertEquals("123", dataList.get(1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void load_normal_xls(){
		HxlsImp xls2csv;
		try {
			xls2csv = new HxlsImp("data/excel2003/dd.xls");
			xls2csv.process("","");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
}
