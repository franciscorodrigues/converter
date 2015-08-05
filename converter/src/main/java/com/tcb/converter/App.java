package com.tcb.converter;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
/**
 * Read html file and generate pdf file.
 * @author franciscorodrigues
 *
 */
public class App 
{
    public static void main( String[] args )  {
    	
    	System.out.println(args.length);
    	if (args.length < 1){
    		System.out.println("the path of html file is empty");
	    	System.exit(0);
	    }	
	    
	    if(args.length < 2){
	    	System.out.println("the path of pdf file is empty");
	    	System.exit(0);
	    }
    	
    	try {
    	   //arquivo de entrada em html
    	   InputStream input = new FileInputStream(args[0]);
    		    
    	   //arquivo de saida em pdf
           OutputStream out = new FileOutputStream(args[1]);
               
           convertToHtml(input, out);
               
           out.close();
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (DocumentException e) {
           e.printStackTrace();
        };  
    
    }
    
    public static void convertToHtml(InputStream input, OutputStream out) 
    		   									throws DocumentException {  
           Tidy tidy = new Tidy();  
           tidy.setShowErrors(0);
           tidy.setShowWarnings(false);
           tidy.setQuiet(true);
           Document doc = tidy.parseDOM(input, null);  
           ITextRenderer rend = new ITextRenderer(); 
    
           rend.setDocument(doc, null);  
           rend.layout();         
           rend.createPDF(out);                  
    }     
 }