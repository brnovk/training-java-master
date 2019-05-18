package by.training.enums;

import by.training.constans.Controllers;

public enum ActionType {
	
	PERFORMED (Controllers.PERFORMED_CONTROLLER), 
	RECYCLE (Controllers.RECYCLE_CONTROLLER), 
	RESTORE (Controllers.RESTORE_CONTROLLER), 
	REMOVE (Controllers.REMOVE_CONTROLLER);
	
	private final String controller;
	
	private ActionType(String controller) {
		this.controller = controller;
	}
	
	public String getController() {
		return controller;
	}
}
