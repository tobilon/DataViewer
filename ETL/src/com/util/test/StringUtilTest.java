package com.util.test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Iterator;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.tools.StringUtil;

public class StringUtilTest {

	@Test
	public void testDeleteBlank() {
	   assertEquals("hello", StringUtil.deleteBlank(" h el l o  "));
	}
	
	@Test
	public void testIsPost(){
		assertEquals(true,StringUtil.isPost("417712"));
	}
	
	@Test
	public void testisFixPhone(){
		assertEquals(true,StringUtil.isFixPhone("0738-60114431"));
		assertEquals(true,StringUtil.isFixPhone("073860114431"));
	}
	
	@Test
	public void testDeleteRepeat() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("123");
		list.add("123");
		list.add("464");
		StringUtil.deleteRepeat(list);
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		assertEquals("464",list.get(0));
		assertEquals("123",list.get(1));
		
		assertEquals(2,list.size());
		
		
	}
	
	@Test
	public void testIsBorn(){
		assertEquals(true,StringUtil.isBorn("1983-10-04"));
	}


}
