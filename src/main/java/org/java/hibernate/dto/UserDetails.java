package org.java.hibernate.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/* Example of Cascade mappings*/
@Entity (name = "User_Details")
@Table (name = "User_Details")
public class UserDetails {
	@Id @GeneratedValue
	private int userId;
	//	@Column (name="user_name")
	//	@Transient
	private String userName;
	@Temporal (TemporalType.DATE)
	private Date joinedDate;
	@Lob
	private String description;
	@OneToMany(cascade=CascadeType.PERSIST)
	private Collection<Vehicle> ManyVehicle = new ArrayList<Vehicle>();
	/*
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	private Set<Address> listOfAddresses = new HashSet();

	 * By using embedded and ElementCollection annotations we can join 2 tables. This is default
	 * JoinTable annotation is not mandatory. But if you want to change table name or other properties then use join table
	*/
	/*Using Array list to add indexes to the table*/
	//@ElementCollection(fetch=FetchType.EAGER)
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name="sequence-gen",strategy="sequence")
	@CollectionId(columns = { @Column(name="Address_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();
	
	/*	Getters are Setters*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		System.out.println(description);
		this.description = description;
	}
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	/*public Vehicle getPrimaryVehicle() {
		return PrimaryVehicle;
	}
	public void setPrimaryVehicle(Vehicle primaryVehicle) {
		PrimaryVehicle = primaryVehicle;
	}*/
	public Collection<Vehicle> getManyVehicle() {
		return ManyVehicle;
	}
	public void setManyVehicle(Collection<Vehicle> manyVehicle) {
		ManyVehicle = manyVehicle;
	}

}
/*
 Example of Many to Many mappings
@Entity (name = "User_Details")
@Table (name = "User_Details")
public class UserDetails {
	@Id @GeneratedValue
	private int userId;
	//	@Column (name="user_name")
	//	@Transient
	private String userName;
	@Temporal (TemporalType.DATE)
	private Date joinedDate;
	@Lob
	private String description;
	@OneToOne
	@JoinColumn(name="prim_vehicle_id")
	private Vehicle PrimaryVehicle;
	@ManyToMany
	private Collection<Vehicle> ManyVehicle = new ArrayList<Vehicle>();
	
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	private Set<Address> listOfAddresses = new HashSet();

	 * By using embedded and ElementCollection annotations we can join 2 tables. This is default
	 * JoinTable annotation is not mandatory. But if you want to change table name or other properties then use join table
	
	Using Array list to add indexes to the table
	//@ElementCollection(fetch=FetchType.EAGER)
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name="sequence-gen",strategy="sequence")
	@CollectionId(columns = { @Column(name="Address_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();
	
		Getters are Setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		System.out.println(description);
		this.description = description;
	}
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	public Vehicle getPrimaryVehicle() {
		return PrimaryVehicle;
	}
	public void setPrimaryVehicle(Vehicle primaryVehicle) {
		PrimaryVehicle = primaryVehicle;
	}
	public Collection<Vehicle> getManyVehicle() {
		return ManyVehicle;
	}
	public void setManyVehicle(Collection<Vehicle> manyVehicle) {
		ManyVehicle = manyVehicle;
	}

}*/

/* Example of embedding 2 Address

@Entity (name = "User_Details")
@Table (name = "User_Details")
public class UserDetails {
	@Id @GeneratedValue
	private int userId;
//	@Column (name="user_name")
//	@Transient
	private String userName;
	@Temporal (TemporalType.DATE)
	private Date joinedDate;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="Home_street")),
		@AttributeOverride(name="city", column=@Column(name="Home_City")),
		@AttributeOverride(name="state", column=@Column(name="Home_state")),
		@AttributeOverride(name="pincode", column=@Column(name="Home_pincode")),
	})
	private Address HomeAddress;


	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="Office_street")),
		@AttributeOverride(name="city", column=@Column(name="Office_City")),
		@AttributeOverride(name="state", column=@Column(name="Office_state")),
		@AttributeOverride(name="pincode", column=@Column(name="Office_pincode")),
	})
	private Address OfficeAddress;
	@Lob
	private String description;



	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		System.out.println(description);
		this.description = description;
	}
	public Address getHomeAddress() {
		return HomeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		HomeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return OfficeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		OfficeAddress = officeAddress;
	}


}
 */

/*Example of Collections of Address*/
/*@Entity (name = "User_Details")
@Table (name = "User_Details")
public class UserDetails {
	@Id @GeneratedValue
	private int userId;
	//	@Column (name="user_name")
	//	@Transient
	private String userName;
	@Temporal (TemporalType.DATE)
	private Date joinedDate;
	@Lob
	private String description;

	@OneToOne
	@JoinColumn(name="prim_vehicle_id")
	private Vehicle PrimaryVehicle;
//	@JoinColumn(name="add_userId_ForeignKey_to_vehicles")	
	@OneToMany
	@JoinTable(name="Vehicle_User_Mapping_table", joinColumns=@JoinColumn(name="User_ID"), inverseJoinColumns=@JoinColumn(name="Vehicle_ID"))
	private Collection<Vehicle> ManyVehicle = new ArrayList<Vehicle>();
	
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	private Set<Address> listOfAddresses = new HashSet();

	 * By using embedded and ElementCollection annotations we can join 2 tables. This is default
	 * JoinTable annotation is not mandatory. But if you want to change table name or other properties then use join table
	
	Using Array list to add indexes to the table
	//@ElementCollection(fetch=FetchType.EAGER)
	@ElementCollection
	@JoinTable(name="user_addresses", joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name="sequence-gen",strategy="sequence")
	@CollectionId(columns = { @Column(name="Address_ID") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();
	
		Getters are Setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		System.out.println(description);
		this.description = description;
	}
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	public Vehicle getPrimaryVehicle() {
		return PrimaryVehicle;
	}
	public void setPrimaryVehicle(Vehicle primaryVehicle) {
		PrimaryVehicle = primaryVehicle;
	}
	public Collection<Vehicle> getManyVehicle() {
		return ManyVehicle;
	}
	public void setManyVehicle(Collection<Vehicle> manyVehicle) {
		ManyVehicle = manyVehicle;
	}

}
*/