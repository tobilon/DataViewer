package com.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.tools.IdcardUtils;

public class IdcardUtilsTest {


	@Test
	public void testGetProvinceByIdCard() {
		
		assertEquals("ºþÄÏ",IdcardUtils.getProvinceByIdCard("432522188310044134"));
	}

}
