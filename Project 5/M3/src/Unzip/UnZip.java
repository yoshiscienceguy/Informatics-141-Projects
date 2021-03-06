package Unzip;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.*;

public class UnZip {
	public static void main(String[] args) throws ZipException, IOException{

		
	}
	
	public ArrayList<String> UnZipit(String input_File, String output_Folder) throws IOException{
		
		byte [] buffer = new byte[1024];

		File folder = new File(output_Folder);
		ArrayList <String> Names = new ArrayList<String>();
		
		folder.mkdir();
		
		ZipInputStream zis = new ZipInputStream(new FileInputStream(output_Folder+File.separator+input_File));
		
		ZipEntry ze = zis.getNextEntry();
		
		while(ze != null){
			String fileName = ze.getName();
			if(fileName.endsWith("txt")){
				Names.add(fileName);
			}
			if(ze.isDirectory()){
				File newDir = new File(output_Folder+File.separator+fileName);
				newDir.mkdir();
				System.out.println("Directory unzip : " + newDir.getAbsoluteFile());
			}
			else{
				File newFile = new File(output_Folder + File.separator+fileName);
				
				System.out.println("File unzip : " + newFile.getAbsoluteFile());
				new File(newFile.getParent()).mkdir();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while((len = zis.read(buffer)) > 0){
					fos.write(buffer, 0, len);
				}
				fos.flush();
				fos.close();
			}
			
			

			ze = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
		System.out.println("Done");
		return Names;
	}
}
