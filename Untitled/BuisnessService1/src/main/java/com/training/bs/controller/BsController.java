package com.training.bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.bs.get.GetDetails;

@RestController
public class BsController {
	@Autowired
	public TestService t;
	@RequestMapping("/get")
	public List<GetDetails> disp1()
	{
		return t.disp();
	}
	@RequestMapping("/get/{name}")
	public GetDetails  getData(@PathVariable String name)
	{
		return t.getInfo(name);
	}
	@RequestMapping(method=RequestMethod.POST, value="/get")
	public void postName(@RequestBody GetDetails getName)
	{
		t.addName(getName);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/get/{name}")
	public void updateName(@RequestBody GetDetails getName,@PathVariable String name)
	{
		t.updateName(getName,name);
	}

}	