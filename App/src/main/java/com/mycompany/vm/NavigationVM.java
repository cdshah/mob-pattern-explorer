package com.mycompany.vm;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class NavigationVM {
	
	private String location = "";
	
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Command()
	@NotifyChange("location")
	public void processLink(@BindingParam("arg1")String arg1){
		
		if(arg1.contains("1")) {
			location = "MobPatterFinder.zul";
		} else {
			location = "timeout.zul";
		}
		
	}

}
