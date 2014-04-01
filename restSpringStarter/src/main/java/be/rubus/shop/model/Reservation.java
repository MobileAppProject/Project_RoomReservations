package be.rubus.shop.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="RESERVATION")
public class Reservation {

	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	@JoinColumn(name = "MEETINGROOM_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private MeetingRoom meetingRoom;

	@Column(name = "BEGIN_DATE")
    @NotNull(message = "Begin datum is verplicht")
	@Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;
	
	@Column(name = "END_DATE")
    @NotNull(message = "Eind datum is verplicht")
	@Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
	
	@Column(name = "PERSON_NAME")
	@NotNull(message = "Naam van persoon is verplicht")
	private String personName;

	@Column(name = "DESCRIPTION")
	@NotNull(message = "Beschrijving is verplicht")
	private String description;

	public Integer getReservationId() {
		return reservationId;
	}


	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", meetingRoom="
				+ meetingRoom + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", personName=" + personName + ", description="
				+ description + "]";
	}


	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}


	public MeetingRoom getMeetingRoom() {
		return meetingRoom; //TODO: this fixes the infinite loop in http://localhost:8080/restSprintStarter/data/meetingrooms 
		//but creates a nullpointerException in http://localhost:8080/restSprintStarter/data/reservations
	}

	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beginDate == null) ? 0 : beginDate.hashCode());
		result = prime * result
				+ ((meetingRoom == null) ? 0 : meetingRoom.hashCode());
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
		Reservation other = (Reservation) obj;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		if (meetingRoom == null) {
			if (other.meetingRoom != null)
				return false;
		} else if (!meetingRoom.equals(other.meetingRoom))
			return false;
		return true;
	}

}
