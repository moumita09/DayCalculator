package com.daycalculator.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaysCalculator {




    @GetMapping("/")
    public String main(Model model) {

        return "calculatorForm"; 
    }
   
    
   
    @PostMapping("/dates")
    public String calculateDays(
            @RequestParam(name = "startDate") String startDate, 
           @RequestParam(name = "endDate") String endDate, Model model) {

    	SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
    	
    	Date start = null;
    	Date end = null;
		try {
			start = df.parse(startDate);
			 end = df.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
        System.out.println("Start Date "+ start);
        long diff = end.getTime() - start.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            model.addAttribute("startDate1",startDate);
            model.addAttribute("endDate1",endDate);
            if(diffDays < 0) {
            	 model.addAttribute("DAys", "Start Date is After End Date");
            }else {
            	 model.addAttribute("DAys", diffDays);
            }
        return "calculatorForm";
    }

}