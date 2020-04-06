package com.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

@Entity
@Table(name="stock_profile")
public class StockProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String company;
	private String profession;
	private String sector;
	private String address;
	private String registration;
	private static StructType structType = DataTypes.createStructType(new StructField[] {
					DataTypes.createStructField("company", DataTypes.StringType, false),
					DataTypes.createStructField("profession", DataTypes.StringType, false),
					DataTypes.createStructField("sector", DataTypes.StringType, false),
					DataTypes.createStructField("address", DataTypes.StringType, false),
					DataTypes.createStructField("registration", DataTypes.StringType, false), });
	
	public StockProfile(String profession, String company, String sector, String address, String registration) {
		super();
		this.profession = profession;
		this.company = company;
		this.sector = sector;
		this.address = address;
		this.registration = registration;
	}
	
	public StockProfile() {}

	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public static StructType getStructType() {
	    return structType;
	}

	@Override
	public String toString() {
		return "StockProfile [id=" + id + ", company=" + company + ", profession=" + profession + ", sector=" + sector
				+ ", address=" + address + ", registration=" + registration + "]";
	}
	
}
