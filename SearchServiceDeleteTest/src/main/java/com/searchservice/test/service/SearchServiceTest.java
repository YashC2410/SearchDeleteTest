package com.searchservice.test.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class SearchServiceTest {
	SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	public boolean insertDeleteRecord(int clientId, String tableName) {
		boolean success = false;
		System.out.println("Adding Record For Client ID: "+clientId);
	      try {
	    	 FileWriter recordWriter = new FileWriter("src\\main\\resources\\DeleteRecord.txt",true);
	    	 String newRecord = String.format("%0$s %18s %20s", clientId,tableName,formatter.format(Calendar.getInstance().getTime()))+"\n";
	    	 BufferedWriter bw = new BufferedWriter(recordWriter);
	         bw.write(newRecord);
	         bw.close();
	         recordWriter.close();
	         System.out.println("Record Added Succesfully");
	         success = true;
		} catch (IOException e) {
			System.out.println("Adding Record Failed Due to Exceptions");
		}
	      return success;
	}
	
	public boolean writeFile(int clientId, String tableName) {
		boolean success = false;
		System.out.println("Adding Record For Client ID: "+clientId);
	      try {
	    	 FileWriter recordWriter = new FileWriter("src\\main\\resources\\DeleteRecord.txt",true);
	    	 String newRecord = String.format("%0$s %18s %20s", clientId,tableName,formatter.format(Calendar.getInstance().getTime()))+"\n";
	    	 BufferedWriter bw = new BufferedWriter(recordWriter);
	         bw.write(newRecord);
	         bw.close();
	         recordWriter.close();
	         System.out.println("Record Added Succesfully");
	         success = true;
		} catch (IOException e) {
			System.out.println("Adding Record Failed Due to Exceptions");
		}
	      return success;
	     
	}
	
	public boolean undoFile(int clientID) {
		boolean success = false;
		System.out.println("Performing Unod Record For Client ID: "+clientID);
		int lineNumber = 0;
		int undoRecord = 0;
		File existingDeleteFile = new File("src\\main\\resources\\DeleteRecord.txt");
		File newDeleteFile = new File("src\\main\\resources\\DeleteRecordTemp.txt");
		try {
		
		BufferedReader br = new BufferedReader(new FileReader(existingDeleteFile));
		PrintWriter pw = new PrintWriter(new FileWriter(newDeleteFile));
		 String currentRecord;
	        while ((currentRecord = br.readLine()) != null) {
	        	if(lineNumber > 0) {
	        		 String[] currentRecordData = currentRecord.split(" ");
	        		 if(!(currentRecordData[0].equalsIgnoreCase(String.valueOf(clientID)))) {
	        			 pw.println(currentRecord);
	        		 }
	        		 else {
	        			 undoRecord ++;
	        		 }
	        	}
	        	else {
	        		pw.println(currentRecord);
	        	}
	        	lineNumber++;
	        }
	        pw.flush();
	        pw.close();
	        br.close();
	        
	        existingDeleteFile.delete();
	        File deleteRecordFile = new File("src\\main\\resources\\DeleteRecord.txt");
	        newDeleteFile.renameTo(deleteRecordFile);
	        if(undoRecord > 0) {
	        	System.out.println("Undo Record Performed Succesfully");
		        System.out.println("Total Number of Records Found and Deleted: "+undoRecord);
		        success = true;
	        }
	        else {
	        	System.out.println("No Records Were Found For Client ID: "+clientID);
	        }       
		}catch(Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean deleteOperation() {
		boolean success = false;
		System.out.println("Checking For Records With Request Time Equal to or More than 15 days");
		File existingFile = new File("C:\\Users\\user\\Documents\\DeleteRecord.txt");
		File newFile = new File("C:\\Users\\user\\Documents\\DeleteRecordTemp.txt");
		int lineNumber = 0;
		int deleteRecordCount = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(existingFile));
			PrintWriter pw = new PrintWriter(new FileWriter(newFile));
			  String st;
		        while ((st = br.readLine()) != null) {
		        	//System.out.println(st);
		        	if(lineNumber!=0) {
		        	String[] data =  st.split(" ");
		        	int position = data.length - 2;
		        	String date = "";
		        	for(int i = position ; i<data.length;i++) {
		        		if( i!= data.length -1) {
		        		date += data[i] + " ";
		        		}
		        		else {
		        			date += data[i];
		        		}
		        	}
		          Date requestDate = formatter.parse(date);
		          Date currentDate = formatter.parse(formatter.format(Calendar.getInstance().getTime()));
		          long diffInMillies = Math.abs(requestDate.getTime() - currentDate.getTime());
				  long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		          if(!(diff >= 15)) {
		        	 pw.println(st);
		          }
		          else {
		        	  deleteRecordCount++;
		          }
		        }
		        else {
		        	pw.println(st);	
		        }
		        lineNumber++;	
		    }
		        pw.flush();
		        pw.close();
		        br.close();
		        existingFile.delete();
		        File dummy = new File("C:\\Users\\user\\Documents\\DeleteRecord.txt");
		        newFile.renameTo(dummy);
		        if(deleteRecordCount > 0) {
		        	System.out.println("Total Number of Records Found and Deleted: "+deleteRecordCount);
		        }
		        else {
		        	System.out.println("No Records Were Found and Deleted");
		        }
		        success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return success;
	}
	
}
