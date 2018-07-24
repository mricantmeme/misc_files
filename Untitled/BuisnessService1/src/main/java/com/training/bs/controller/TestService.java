package com.training.bs.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.training.bs.get.GetDetails;

@Service
public class TestService {
	
	List<GetDetails> arr=new ArrayList<>();
	/*List<GetDetails> arr=new ArrayList<>( Arrays.asList(new GetDetails("Suku","Chennai"),
			new GetDetails("Ram", "Chrome"),
			new GetDetails("Sam","Kadambathur"),
			new GetDetails("Tom","Poonamallee"),
			new GetDetails("Vim","Hyd")));*/
	
	public List<GetDetails> disp()
	{
		return arr;
	}
	
	public GetDetails getInfo(String name)
	{
		return arr.stream().filter(t -> t.getName().equals(name)).findFirst().get();
	}

	public void addName(GetDetails get) {
		arr.add(get);
		}

	public void updateName(GetDetails getName, String name) {
		for(int i=0;i< arr.size();i++)
		{
			GetDetails obj= arr.get(i);
			if(obj.getName().equals(name))
			{
				arr.set(i,getName);
			}
			
		}
	}
}
