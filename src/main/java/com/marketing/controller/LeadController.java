package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;

@Controller
public class LeadController
{
	@Autowired
	private LeadService leadservice;
	//Handler Methods
	@RequestMapping("/createLead")
	public String ViewCreateLeadPage()
	{
		return "create_lead";
	}
	//
	//@RequestMapping("/saveLead")
	//public String saveOneLead(@RequestParam("fName")String fname,@RequestParam("lastName")String lastname,@RequestParam("email") String email,@RequestParam("mobile")long mobile)
	//{
		//Lead l=new Lead();
		//l.setFirstName(fname);
		//l.setLastName(lastname);
		//l.setEmail(email);
		//l.setMobile(mobile);
		
		
		//leadservice.saveLead(l);
		//return "create_lead";
		
	//}
	
	//Data Transfer Object
	
	//@RequestMapping("/saveLead")
	//public String saveOneLead(LeadData data,ModelMap model)
	//{
		
		//	Lead lead=new Lead();
		//	lead.setFirstName(data.getFirstName());
			//lead.setLastName(data.getLastName());
		//	lead.setEmail(data.getEmail());
			//lead.setMobile(data.getMobile());
			//leadservice.saveLead(lead);
			//model.addAttribute("msg","Lead is Saved");
		//	return "create_lead";
	//}
	
	@RequestMapping("/saveLead")
	public String saveOneLead(@ModelAttribute("lead") Lead lead,ModelMap model)
	{
		leadservice.saveLead(lead);
		model.addAttribute("msg","Lead is Saved");
		return "create_lead";
	}
	@RequestMapping("/listAll")
	public String listAll(ModelMap model)
	{
		List<Lead> leads = leadservice.listLeads();
		model.addAttribute("leads",leads);
		return "lead_search_result";
		
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id")long id,ModelMap model)
	{
		leadservice.deleteLeadById(id);
		
		List<Lead> leads = leadservice.listLeads();
		model.addAttribute("leads", leads);
		return "lead_search_result";
		
	}
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id")long id,ModelMap model)
	{
		Lead lead = leadservice.getOneLead(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	@RequestMapping("/updateLead")
	public String updateOneLeadData(LeadData data,ModelMap model)
	{
		Lead lead=new Lead();
		lead.setId(data.getId());
		lead.setFirstName(data.getFirstName());
		lead.setLastName(data.getLastName());
		lead.setEmail(data.getEmail());
		lead.setMobile(data.getMobile());
		leadservice.saveLead(lead);
		
		List<Lead> leads = leadservice.listLeads();
		model.addAttribute("leads", leads);
		return "lead_search_result";
	}
	
}
