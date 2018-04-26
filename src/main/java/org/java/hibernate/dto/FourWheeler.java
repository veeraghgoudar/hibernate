package org.java.hibernate.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@DiscriminatorValue("Car")
@Entity
public class FourWheeler extends VehicleSuper {
	
	private String SteeringWheel;

	
	public String getSteeringWheel() {
		return SteeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		SteeringWheel = steeringWheel;
	}
	
}
