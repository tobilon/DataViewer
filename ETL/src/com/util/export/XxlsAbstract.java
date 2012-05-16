package com.util.export;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * XSSF and SAX (Event API)
 */
public abstract class XxlsAbstract extends DefaultHandler {
	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;
	protected String filename=null;
	protected String dirname;

	private int sheetIndex = -1;
	private List<String> rowlist = new ArrayList<String>();
	
	private int curRow = 0;
	private int curCol = 0;

	//excel��¼�в���������������������Ԫ���б�Ϊ��������һ��Ԫ�ؽ��в�����Ԫ��ΪString����
//	public abstract void optRows(int curRow, List<String> rowlist) throws SQLException ;
	
	//excel��¼�в�����������sheet����������������Ԫ���б�Ϊ��������sheet��һ��Ԫ�ؽ��в�����Ԫ��ΪString����
	public abstract void optRows(int sheetIndex,int curRow, List<String> rowlist) throws SQLException;
	
	//ֻ����һ��sheet������sheetIdΪҪ������sheet��������1��ʼ��1-3
	public void processOneSheet(String filename,int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		
		XMLReader parser = fetchSheetParser(sst);

		// rId2 found by processing the Workbook
		// ���� rId# �� rSheet# ����sheet
		InputStream sheet2 = r.getSheet("rId"+sheetId);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	/**
	 * ���� excel �ļ�
	 */
	public void process(String filename,String dirname) throws Exception {
		this.filename = filename;
		this.dirname = dirname;
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();

		XMLReader parser = fetchSheetParser(sst);

		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// c => ��Ԫ��
		if (name.equals("c")) {
			// �����һ��Ԫ���� SST ����������nextIsString���Ϊtrue
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// �ÿ�
		lastContents = "";
	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// ����SST������ֵ�ĵ���Ԫ�������Ҫ�洢���ַ���
		// ��ʱcharacters()�������ܻᱻ���ö��
		if (nextIsString) {
			try {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
						.toString();
			} catch (Exception e) {

			}
		}

		// v => ��Ԫ���ֵ�������Ԫ�����ַ�����v��ǩ��ֵΪ���ַ�����SST�е�����
		// ����Ԫ�����ݼ���rowlist�У�����֮ǰ��ȥ���ַ���ǰ��Ŀհ׷�
		if (name.equals("v")) {
			String value = lastContents.trim();
			value = value.equals("")?" ":value;
			rowlist.add(curCol, value);
			curCol++;
		}else {
			//�����ǩ����Ϊ row ����˵���ѵ���β������ optRows() ����
			if (name.equals("row")) {
				try {
					optRows(sheetIndex,curRow,rowlist);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rowlist.clear();
				curRow++;
				curCol = 0;
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//�õ���Ԫ�����ݵ�ֵ
		lastContents += new String(ch, start, length);
	}
}

