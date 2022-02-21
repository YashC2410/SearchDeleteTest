package com.readwrite.excel.SampleTesting;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opencsv.exceptions.CsvException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, CsvException, EncryptedDocumentException, InvalidFormatException
    {
    	ReadWriteUtil test = new ReadWriteUtil();
        //test.testingReadFile();
    	//test.testWriteFile();
    	test.deteletTest();
    }
}
