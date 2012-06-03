package com.util.tools;

import java.io.IOException;

import com.util.gui.FileBrower;



public class FileUtil {
	public static void writeError(String content) throws IOException{
	
		FileBrower.bw.write(content+"\n");
		FileBrower.bw.flush();
		
	}
}
