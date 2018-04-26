package org.java.hibernate.dto;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*
 *  SINGLE TABLE STRATEGY
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="VehicleType", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Vehicle")*/

/*  TABLE Per CLASS STRATEGY 
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)*/

/*  JOINED STRATEGY */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class VehicleSuper {

	@Id @GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
}
