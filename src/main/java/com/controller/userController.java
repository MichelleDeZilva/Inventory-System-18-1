

package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.model.cashier;
import com.model.dailyAttendance;
import com.model.markAttendance;
import com.model.search1;
import com.model.search3;
import com.model.search4;
import com.model.user;
import com.model.view;
import com.mysql.fabric.Response;
import com.service.attendancePDFview;
import com.service.calculateWorkHours;
import com.service.chackPriviladgeService;
import com.service.deleteService;
import com.service.getDailyAttendance;
import com.service.getEmployeeService;
import com.service.loginLogoutPDFview;
import com.service.loginService;
import com.service.markAttendanceService;
import com.service.recordLogOutTime;
import com.service.recordLoginTime;
import com.service.refferIncomeService;
import com.service.search1Service;
import com.service.search2Service;
import com.service.search3Service;
import com.service.search4Service;
import com.service.update1Service;
import com.service.userService;
import com.service.viewService;

@Controller
public class userController {
	
	@RequestMapping("/insert1")
	public ModelAndView insert(HttpServletRequest request,HttpServletResponse respond)
	{
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("type");	
		
		user obj = new user();
		
		obj.setUid(uid);
		obj.setUname(uname);
		obj.setPassword(password);
		obj.setEmail(email);
		obj.setType(type);
		
		userService us = new userService();
		us.addUserService(obj);
		
		
		System.out.println(uname);
		System.out.println(type);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userSuccess.jsp");
		
		return mv;
	}
	
	@RequestMapping("/search1")
	public ModelAndView search(HttpServletRequest request,HttpServletResponse respond) throws ClassNotFoundException, SQLException
	{
		String search = request.getParameter("search");
		System.out.println(search);
		search1 obj = new search1();
		obj.setSearch(search);
		
		search1Service US = new search1Service();//Create Search1 service object
		
		ArrayList search_list = US.searchUser(obj);//send search1 class(model) object and get array list

		ModelAndView mv = new ModelAndView();
		mv.setViewName("userResult.jsp");
		mv.addObject("result",search_list);
		return mv;
	}
	
	
	@RequestMapping("/deleteupdate")
	public ModelAndView deleteUpdate(HttpServletRequest request,HttpServletResponse respond) throws ClassNotFoundException, SQLException
	{
		String value = request.getParameter("ud");
		ModelAndView mv = new ModelAndView();
		
		if(value.equals("delete"))
		{
			String uid = request.getParameter("uid");
			String dsearch = request.getParameter("dsearch");
		
			deleteService ds = new deleteService();
			ds.deleteUserService(uid);
			
			//-------------------------------------
			//Re direct page , searched mode
			search1 obj = new search1();
			obj.setSearch(dsearch);
			
			search1Service US = new search1Service();//Create Search1 service object
			
			ArrayList search_list = US.searchUser(obj);//send search1 class(model) object and get array list

			
			mv.setViewName("userResult.jsp");
			mv.addObject("result",search_list);
			
		}
		else if(value.equals("update"))
		{
			String uid = request.getParameter("uid");
			
			search1 obj = new search1();
			obj.setSearch(uid);
			
			search2Service US = new search2Service();//Create Search1 service object
			
			ArrayList search_list = US.searchUser(obj);//send search1 class(model) object and get array list

			
			mv.setViewName("updateUser.jsp");
			mv.addObject("result",search_list);
			
			
		}
		
		return mv;
	}
	
	@RequestMapping("/update1")
	public ModelAndView update1(HttpServletRequest request,HttpServletResponse respond)throws ClassNotFoundException, SQLException
	{
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("type");	
		
		user obj = new user();
		
		obj.setUid(uid);
		obj.setUname(uname);
		obj.setPassword(password);
		obj.setEmail(email);
		obj.setType(type);
		
		//System.out.println(uname);
		//System.out.println(type);
		
		update1Service us = new update1Service();
		us.updateUserService(obj);
		
		//-----------------------------------------------------------------------
		//Re direct page , searched mode
		String dsearch = request.getParameter("dsearch");
		ModelAndView mv = new ModelAndView();
		search1 obj1 = new search1();
		obj1.setSearch(dsearch);
		
		search1Service US = new search1Service();//Create Search1 service object
		
		ArrayList search_list = US.searchUser(obj1);//send search1 class(model) object and get array list

		
		mv.setViewName("userResult.jsp");
		mv.addObject("result",search_list);
		
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse respond)throws ClassNotFoundException, SQLException
	{
		String uname = request.getParameter("uName");
		String password = request.getParameter("password");
		
		ModelAndView mv = new ModelAndView();
		
		user obj2 = new user();
		
		obj2.setUname(uname);
		obj2.setPassword(password);
		
		loginService ls = new loginService();
		boolean result = ls.loginUserService(obj2);
		System.out.println(result);
		//-------------------------
		//Get if user or admin
		user obj3 = new user();
		
		obj3.setUname(uname);
		obj3.setPassword(password);
	
		chackPriviladgeService cp = new chackPriviladgeService();
		String check = cp.chackPriviladge(obj3);
		
		//----------------------------
		
		if(result == true && check.equals("Admin"))
		{
			recordLoginTime LT = new recordLoginTime();
			LT.logingRecord(uname);
			
			HttpSession session = request.getSession(true);  
			session.setMaxInactiveInterval(6000);
			
			 
	        session.setAttribute("currentSessionUser",obj3); 
	       
	        
	   
	       
			
			mv.setViewName("index1.jsp");//logged-in Admin page  
			
		}
		else if(result == true && check.equals("User"))
		{
			recordLoginTime LT = new recordLoginTime();
			LT.logingRecord(uname); 
	        
			HttpSession session = request.getSession(true);  
			 session.setMaxInactiveInterval(6000);//in seconds
	        session.setAttribute("currentSessionUser",obj3); 
	       
			mv.setViewName("index3.jsp");//logged-in User page    
		}
		else
		{
			String erorr = "Eneter Correct Credentials!!";
			mv.addObject("result",erorr);
			mv.setViewName("LoginError.jsp");
		}
		
		return mv;
	}
	
	@RequestMapping("/logOut")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse respond)
	{
		
		
		HttpSession session=request.getSession(false);
		session.removeAttribute("currentSessionUser");
		session.invalidate();
     
		recordLogOutTime RL = new recordLogOutTime();
		RL.logOutRecord();
		
		ModelAndView mv = new ModelAndView();
		String logout= "Successfully logout!!";
		mv.addObject("result",logout);
		mv.setViewName("LogOut.jsp");
		
		return mv;
	}
	
	


}
