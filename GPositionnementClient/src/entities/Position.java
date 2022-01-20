package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

/**
 * Entity implementation class for Entity: Position
 *
 */

public class Position implements Serializable {

	private int id;
	private String latitude;
	private String Longitude;
	private Date date;
	private SmartPhone smartPhone;
	private static final long serialVersionUID = 9839;

	public Position() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}   
	public String getLongitude() {
		return this.Longitude;
	}

	public void setLongitude(String Longitude) {
		this.Longitude = Longitude;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public SmartPhone getSmartPhone() {
		return smartPhone;
	}
	public void setSmartPhone(SmartPhone smartPhone) {
		this.smartPhone = smartPhone;
	}
   
	
}
