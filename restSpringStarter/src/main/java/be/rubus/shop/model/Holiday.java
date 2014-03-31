package be.rubus.shop.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "HOLIDAY")
public class Holiday {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "HOLIDAY_DATE")
    @NotNull(message = "Begin datum is verplicht")
    private Date holidayDate;
	
	//Temporal vraagteken
	
	@Column(name = "NAME")
	@NotNull(message = "Naam van persoon is verplicht")
	private String name;


	public Integer getId() {
		return id;
	}


	public Date getHolidayDate() {
		return holidayDate;
	}


	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((holidayDate == null) ? 0 : holidayDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Holiday other = (Holiday) obj;
		if (holidayDate == null) {
			if (other.holidayDate != null)
				return false;
		} else if (!holidayDate.equals(other.holidayDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	
}