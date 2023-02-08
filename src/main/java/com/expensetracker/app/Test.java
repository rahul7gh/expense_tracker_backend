package com.expensetracker.app;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Test {

	public static void main(String[] args) 
	{
		Demo demo = new Demo(10);
		List<Demo> demoList=new ArrayList<>(List.of(demo,new Demo(30),new Demo(400),new Demo(20)));
		System.out.println(demoList.contains(new Demo(10)));
		System.out.println(demoList.contains(demo));
		
	}
}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Demo
{
	Integer a;
	
	@Override
	public boolean equals(Object obj) {
		Demo d=(Demo)obj;
		System.err.println("checking");
		return d.getA().equals(a); 
	}
}