package com.mycompany.vm;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Center;

 
public class PropertyRetriever extends GenericForwardComposer {
    
      private Center mainContent;
      
      

	public Center getMainContent() {
		return mainContent;
	}







	public void setMainContent(Center mainContent) {
		this.mainContent = mainContent;
	}







	public void onClick$createProduct(Event event) {
    	
    	Executions.createComponents("MobPatternFinder.zul", mainContent, null);
    }
}
