package com.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.StringUtil;

public class StringUtilTest {

	@Test
	public void testDeleteBlank() {
	   assertEquals("hello", StringUtil.deleteBlank(" h el l o  "));
	}

}
