package com.util.test;

import static org.junit.Assert.*;

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

}
