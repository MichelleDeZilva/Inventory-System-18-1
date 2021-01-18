
package com.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.DamageClass;
import com.service.DamageInsertService;
import com.service.DamageItemReportPdf;
import com.model.ReturnItem;
import com.model.SearchClass;
import com.model.StockItem;
import com.model.ViewClass;
import com.service.DamageService;
import com.service.DeletedService;
import com.service.NonAvailableReportPDF;
import com.service.NonRepairedItemReportPDF;
import com.service.NonReparedService;
import com.service.Non_AvailableService;
import com.service.ReadStockData;
import com.service.RepairedItemReportPDF;
import com.service.ReparingService;
import com.service.StockReportPDF;
import com.service.ReturnAndReparingService;
import com.service.ReturnItemReportPDF;
import com.service.Retutnservice;
import com.service.SearchService;
import com.service.StockService;
import com.service.StockViewService;
import com.service.UpdateService;
import com.service.ViewReturnService;


@Controller
public class StoreController {
	//StockItemInsert.jsp
	@RequestMapping("/stockInsert")	
	public ModelAndView insertStockAction(HttpServletRequest request,HttpServletResponse respond) throws SQLException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		
		if(val.equals("Insert")){
			String name = request.getParameter("name");
			String supplierID  = request.getParameter("supplierID");
			String description  = request.getParameter("description");
			String Spurchaseprice  = request.getParameter("purchaseprice");
			String Ssellprice  = request.getParameter("sellprice");
			String Samount  = request.getParameter("amount");
			String type = request.getParameter("type");
			double purchaseprice = Double.parseDouble(Spurchaseprice);
			double sellprice = Double.parseDouble(Ssellprice );
			int amount = Integer.parseInt(Samount );
			
			StockItem s = new StockItem();
			s.setName(name);
			s.setSupplierID(supplierID);
			s.setDescription(description);
			s.setPurchaseprice(purchaseprice);
			s.setSellprice(sellprice);
			s.setAmount(amount);
			s.setType(type);
			
			StockService ss = new StockService();
			ss.stockAndDamgeInsert(s);
		}
		else if(val.equals("Update")){
			
			
			long ID = Long.parseLong(request.getParameter("ID"));
			String Name = request.getParameter("Name");
			String SupplierID  = request.getParameter("SupplierID");
			String Description  = request.getParameter("Description");
			String SPurchaseprice  = request.getParameter("Purchaseprice");
			String SSellprice  = request.getParameter("Sellprice");
			String SAmount  = request.getParameter("Amount");
			String Type = request.getParameter("Type");
			double Purchaseprice = Double.parseDouble(SPurchaseprice);
			double Sellprice = Double.parseDouble(SSellprice );
			int Amount = Integer.parseInt(SAmount );
			
			StockItem s = new StockItem();
			s.setName(Name);
			s.setSupplierID(SupplierID);
			s.setDescription(Description);
			s.setPurchaseprice(Purchaseprice);
			s.setSellprice(Sellprice);
			s.setAmount(Amount);
			s.setType(Type);
			
			System.out.println(ID);
			s.setId(ID);
			UpdateService ss = new UpdateService();
			ss.stockAndDamgeUpdate(s);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("InsertSuccessfull.jsp");
		return mv;
	}
	//damageInsert.jsp,damageUpdate.jsp
	@RequestMapping("/damageInsert")	
	public ModelAndView insertDamageAction(HttpServletRequest request,HttpServletResponse respond) throws SQLException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		
		if(val.equals("Insert")){
			String Ids = request.getParameter("Id");
			String name = request.getParameter("name");
			String supplierID  = request.getParameter("supplierID");
			String description  = request.getParameter("description");
			String Spurchaseprice  = request.getParameter("purchaseprice");
			String Samount  = request.getParameter("amount");
			String type = request.getParameter("type");
			long Id = Long.parseLong(Ids);
			double purchaseprice = Double.parseDouble(Spurchaseprice);
			int amount = Integer.parseInt(Samount );
			double cost = purchaseprice * amount;
			DamageClass s = new DamageClass();
			s.setId(Id);
			s.setName(name);
			s.setSupplierID(supplierID);
			s.setDescription(description);
			s.setPurchaseprice(purchaseprice);
			s.setAmount(amount);
			s.setType(type);
			s.setCost(cost);
			
			DamageInsertService d = new DamageInsertService();
			d.DamgeInsert(s);
		}
		else if(val.equals("Update")){
			
			long ID = Long.parseLong(request.getParameter("ID"));
			String Name = request.getParameter("Name");
			String SupplierID  = request.getParameter("SupplierID");
			String Description  = request.getParameter("Description");
			String SPurchaseprice  = request.getParameter("Purchaseprice");
			String SAmount  = request.getParameter("Amount");
			String Type = request.getParameter("Type");
			double Purchaseprice = Double.parseDouble(SPurchaseprice);
			int Amount = Integer.parseInt(SAmount );
			double cost = Purchaseprice * Amount;
			DamageClass s = new DamageClass();
			
			s.setName(Name);
			s.setSupplierID(SupplierID);
			s.setDescription(Description);
			s.setPurchaseprice(Purchaseprice);
			s.setAmount(Amount);
			s.setType(Type);
			s.setCost(cost);
			System.out.println(ID);
			s.setId(ID);
			UpdateService up = new UpdateService();
			up.DamgeUpdate(s);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("InsertSuccessfull.jsp");
		return mv;
	}
	//ReturnItemInsert.jsp,ReturnUpdate.jsp
	@RequestMapping("/ReturnInsert")	
	public ModelAndView insertReturnAction(HttpServletRequest request,HttpServletResponse respond) throws SQLException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		if(val.equals("Insert")){
			String name = request.getParameter("name");
			String SId= request.getParameter("Id");
			String supplierID= request.getParameter("supplierID");
			String cuName= request.getParameter("cuName");
			String StelNo= request.getParameter("telNo");
			String Ssellprice= request.getParameter("sellprice");
			String Samount= request.getParameter("amount");
			String date= request.getParameter("date");
			String today= request.getParameter("today");
			String type= request.getParameter("type");
			
			long Id = Long.parseLong(SId );
			int telNo = Integer.parseInt(StelNo);
			int amount = Integer.parseInt(Samount );
			double sellprice = Double.parseDouble(Ssellprice );
			ReturnItem r = new ReturnItem();
			
			r.setName(name);
			r.setId(Id);
			r.setSupplierID(supplierID);
			r.setCuName(cuName);
			r.setTelNo(telNo);
			r.setSellprice(sellprice);
			r.setAmount(amount);
			r.setDate(date);
			r.setToday(today);
			r.setType(type);
	
			Retutnservice rs = new Retutnservice();
			rs.returnAndReparingInsert(r);
		}
		else if(val.equals("Update")){
			
			String name = request.getParameter("name");
			String SId= request.getParameter("Id");
			String supplierID= request.getParameter("supplierID");
			String cuName= request.getParameter("cuName");
			String StelNo= request.getParameter("telNo");
			String Ssellprice= request.getParameter("sellprice");
			String Samount= request.getParameter("amount");
			String date= request.getParameter("date");
			String today= request.getParameter("today");
			String type= request.getParameter("type");
			
			long Id = Long.parseLong(SId );
			int telNo = Integer.parseInt(StelNo);
			int amount = Integer.parseInt(Samount );
			double sellprice = Double.parseDouble(Ssellprice );
			ReturnItem r = new ReturnItem();
			r.setName(name);
			r.setId(Id);
			r.setSupplierID(supplierID);
			r.setCuName(cuName);
			r.setTelNo(telNo);
			r.setSellprice(sellprice);
			r.setAmount(amount);
			r.setDate(date);
			r.setToday(today);
			r.setType(type);
	
			
			System.out.println(Id);
			r.setId(Id);
			UpdateService ss = new UpdateService();
			ss.rentANDreturnUpdate(r);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("InsertSuccessfull.jsp");
		return mv;
		
	}
	//Update.jsp
	@RequestMapping("/ViewAction")	
	public ModelAndView stockViewItem(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		String val = request.getParameter("st");
		System.out.println(val);
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("ReturnView")){
			ViewClass v = new ViewClass();
			v.setSt(val);
			ReturnAndReparingService vs = new ReturnAndReparingService();
			ArrayList list =  vs.reparingViewDisplay(v);
			System.out.println(list);
			mv.setViewName("ViewReturnItem.jsp");
			mv.addObject("result",list);
		}
		else if(val.equals("Stock")){
			
			ViewClass v = new ViewClass();
			v.setSt(val);
			StockViewService vs = new StockViewService();
			ArrayList list =  vs.StockViewDisplay(v);
			System.out.println(list);
			mv.setViewName("stockView.jsp");
			mv.addObject("result",list);
		}
		
	}
	
	//stockAlView.jsp
	@RequestMapping("/stock1")	
	public ModelAndView StockViewItem(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("Add")){
			
			mv.setViewName("StockItemInsert.jsp");
		}
		else if(val.equals("Stock")){
			
			ViewClass v = new ViewClass();
			v.setSt(val);
			StockViewService vs = new StockViewService();
			ArrayList list =  vs.StockViewDisplay(v);
			System.out.println(list);
			mv.setViewName("stockView.jsp");
			mv.addObject("result",list);
		}
		else if(val.equals("Damage")){
			ViewClass v = new ViewClass();
			v.setSt(val);
			DamageService vs = new DamageService();
			ArrayList list =  vs.DamageViewDisplay(v);
			System.out.println(list);
			mv.setViewName("Damage.jsp");
			mv.addObject("result",list);
		}
	
		
	}
	//stockView.jsp
	@RequestMapping("/stock2")	
	public ModelAndView StockViewDisplay1(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("Add")){
			
			mv.setViewName("StockItemInsert.jsp");
		}
		else if(val.equals("Damage")){
			ViewClass v = new ViewClass();
			v.setSt(val);
			DamageService vs = new DamageService();
			ArrayList list =  vs.DamageViewDisplay(v);
			System.out.println(list);
			mv.setViewName("Damage.jsp");
			mv.addObject("result",list);
		}
		
		
	}
	//Damage.jsp
	@RequestMapping("/damage")
	public ModelAndView DamageViewItem(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("Add")){
			
			mv.setViewName("DamageInsert.jsp");
		}
	
		else if(val.equals("Stock")){
			
			ViewClass v = new ViewClass();
			v.setSt(val);
			StockViewService vs = new StockViewService();
			ArrayList list =  vs.StockViewDisplay(v);
			System.out.println(list);
			mv.setViewName("stockView.jsp");
			mv.addObject("result",list);
		}
		
	}
	
	//damage,stock,return,repaired,non-repaired jsp page
	@RequestMapping("/readTable")
	public ModelAndView stockRead(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("Update")){
			long Id = Long.parseLong(request.getParameter("Id"));
			String type = request.getParameter("type");
			System.out.println(Id);
			System.out.println(type);
			
			if(type.equals("Stock-item")){
				StockItem s = new StockItem();
				s.setId(Id);
				s.setType(type);
				ReadStockData rs = new ReadStockData();
				ArrayList list =rs.StockRead(s);
				mv.setViewName("StockUpdate.jsp");
				mv.addObject("result",list);
			}
			else if(type.equals("Damage-item")){
				DamageClass s = new DamageClass();
				s.setId(Id);
				s.setType(type);
				ReadStockData rs = new ReadStockData();
				ArrayList list =rs.DamageRead(s);
				mv.setViewName("DamageUpdate.jsp");
				mv.addObject("result",list);
			}
			else if(type.equals("Return-item") || type.equals("Reparing-item")|| type.equals("NonReparing-item")){
				ReturnItem r = new ReturnItem();
				r.setId(Id);
				r.setType(type);
				ReadStockData rs = new ReadStockData();
				ArrayList list =rs.RetutnRead(r);
				mv.setViewName("ReturnUpdate.jsp");
				mv.addObject("result",list);
			}
		}
		
		else if(val.equals("Delete")){
			
			long Id = Long.parseLong(request.getParameter("Id"));
			String type = request.getParameter("type");
			System.out.println(Id);
			System.out.println(type);
			
			if(type.equals("Stock-item")){
				StockItem s = new StockItem();
				s.setId(Id);
				s.setType(type);
				DeletedService d = new DeletedService();
				d.stockDeleted(s);
				ArrayList list = d.stockDeleted(s);
				
				mv.setViewName("stockView.jsp");
				mv.addObject("result",list);
				
				
			}
			else if( type.equals("Damage-item")){
				DamageClass d = new DamageClass();
				d.setId(Id);;
				d.setType(type);
				DeletedService del = new DeletedService();
				del.DamageDeleted(d);
				ArrayList list = del.DamageDeleted(d);
				
				mv.setViewName("Damage.jsp");
				mv.addObject("result",list);
				
			}
			
			else if(type.equals("Return-item") || type.equals("Reparing-item")|| type.equals("NonReparing-item")){
				ReturnItem r = new ReturnItem();
				r.setId(Id);
				r.setType(type);
				DeletedService d = new DeletedService();
				d.RentDeleted(r);
				ArrayList list = d.RentDeleted(r);

				if(type.equals("Return-item")){
					mv.setViewName("Return.jsp");
					mv.addObject("result",list);
				}
				else if(type.equals("Reparing-item")){
					mv.setViewName("Reparing.jsp");
					mv.addObject("result",list);
				}
				else if(type.equals("NonReparing-item")){
					mv.setViewName("NonRepaired.jsp");
					mv.addObject("result",list);
				}
				
			}
		}
		else if(val.equals("NonDelete")){
			int amount = Integer.parseInt(request.getParameter("amount"));
			long Id = Long.parseLong(request.getParameter("Id"));
			String type = request.getParameter("type");
			StockItem s = new StockItem();
			s.setId(Id);
			s.setType(type);
			s.setAmount(amount);
			DeletedService d = new DeletedService();
			d.NonItemDeleted(s);
			if(type.equals("Stock-item")){
				ArrayList list = d.NonItemDeleted(s);
				mv.setViewName("Non-available.jsp");
				mv.addObject("result",list);
			}
		}
		return mv;	
	}
	//StockItemManagement.jsp
	@RequestMapping("/mainPage")
	public ModelAndView MainPageView(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		
		if(val.equals("stockI")){
			mv.setViewName("StockItemInsert.jsp");
		}
		else if(val.equals("damageI")){
			mv.setViewName("DamageInsert.jsp");
		}
		else if(val.equals("returnI")){
			mv.setViewName("ReturnItemInsert.jsp");
		}
		else if(val.equals("repairedI")){
			mv.setViewName("ReturnItemInsert.jsp");
		}
		else if(val.equals("stock")){
			
			ViewClass v = new ViewClass();
			v.setSt(val);
			StockViewService vs = new StockViewService();
			ArrayList list =  vs.StockViewDisplay(v);
			System.out.println(list);
			mv.setViewName("stockView.jsp");
			mv.addObject("result",list);
		}
		
		else if(val.equals("damage")){
			ViewClass v = new ViewClass();
			v.setSt(val);
			DamageService vs = new DamageService();
			ArrayList list =  vs.DamageViewDisplay(v);
			System.out.println(list);
			mv.setViewName("Damage.jsp");
			mv.addObject("result",list);
		}
		
		
		
		else if(val.equals("return")){
			ViewClass v = new ViewClass();
			v.setSt(val);
			ViewReturnService r = new ViewReturnService();
			ArrayList list = r.returnViewDisplay(v);
			mv.setViewName("Return.jsp");
			mv.addObject("result",list);
		}
		else if(val.equals("retort")){
			mv.setViewName("ReportSewwandi.jsp");//Report change to ReportSewwandi
		}
		else if(val.equals("search")){
			mv.setViewName("Search.jsp");
		}
		
		return mv;
	}
	//stock main page
	@RequestMapping("/home")
	public ModelAndView MainFuntion(HttpServletRequest request,HttpServletResponse respond) throws SQLException, ClassNotFoundException{
		String val = request.getParameter("st");
		System.out.println(val);
		
		ModelAndView mv = new ModelAndView();
		if(val.equals("home")){
			mv.setViewName("StockItemManagement.jsp");
		}
		
		return mv;
	}
	



