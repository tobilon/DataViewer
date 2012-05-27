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
			System.out.println("�ļ�������!");
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
	//���������������޹أ���ʼ��������1����ȡ��һ�С�abc123��
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
	//��ȡǰ���У���һ�С�abc123�����ڶ��С�efg456��
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
	//��ȡǰ���У�û�����ݵĵ�Ԫ��Ĭ��ֵΪ" ",�����ݵĿո񱻹�����
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
	//���������������޹أ���ʼ��������2��dataList�������һ������
	//���⣺ǰ�����ո�û������
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
	//���������������޹أ�dataList�������һ������
	//���⣺�ڶ�ҳ��headlist���ǵ�һ�ж��ǵ�һ����
	//���⣺�ڶ�ҳ��ǰһ���ո񱻹���
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
