
package com.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ReturnItemReportPDF extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map model, Document document,PdfWriter writer, HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		response.setHeader("Content-Disposition", "attachment; filename=\"ReturnItemReport.pdf\""); //This code responsible for direct download PDF file
		  
		@SuppressWarnings("unchecked")
		ArrayList list = (ArrayList) model.get("result");

          document.open();
          
          Font f1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 50, Font.BOLD, new CMYKColor(0,832, 317, 315));
          Font f = new Font();
          f.setStyle(Font.BOLD);
          f.setSize(8);
          Paragraph p1 = new Paragraph("Return Item Report", f1);
          p1.setAlignment(Element.ALIGN_CENTER);
          document.add(p1);
          
          Paragraph p5 = new Paragraph("                 ");
		  document.add(p5);
		  
		  p1.setSpacingBefore(20f);
		  
          Paragraph p2 = new Paragraph();
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		  LocalDate localDate = LocalDate.now();
		  p2.add("Date : "+dtf.format(localDate));	
		  document.add(p2);
		  
		  Paragraph p3 = new Paragraph();
		  Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
	      p3.add("Time : "+sdf.format(cal.getTime()));
	      
		  document.add(p3);
		  
		  Paragraph p4 = new Paragraph("                 ");
		  document.add(p4);
		  
		  p3.setSpacingBefore(20f);
		  
		  PdfPTable t = new PdfPTable(8);
		  t.setWidthPercentage(100);
		  
	      PdfPCell cell = new PdfPCell();
	      cell.setPaddingBottom(20);
	      
	      t.addCell("ID");
		  t.addCell(" Name ");
		
		  t.addCell(" Custmer name ");
		  t.addCell(" telNo ");
		  t.addCell(" Amount ");
		  t.addCell(" Sold date ");
		  t.addCell(" Return date ");
		  

		  int count = 0;
		  
		  java.util.Iterator itr = list.iterator();
          while (itr.hasNext()) {          
	           count++;
	           ArrayList listView = (ArrayList) itr.next();
	           
	           String Id = (String) listView.get(0);
	           String name = (String) listView.get(1);
	         
	           String cuName = (String) listView.get(3);
	           String telNo = (String) listView.get(4);
	           String sellprice = (String) listView.get(5);
	           String amount = (String) listView.get(6);
	           String date = (String) listView.get(7);
	           String today = (String) listView.get(8);
	           String type = (String) listView.get(9);
	           
	           cell.setPaddingBottom(20);   
			   t.addCell(Id);
			   t.addCell(name);
			
			   t.addCell(cuName);
			   t.addCell(telNo);
			   t.addCell(amount);
			   t.addCell(date);
			   t.addCell(today);
          }
		  
		  document.add(t);
		
          document.close();
	}
}
