package be.vdab.meetingroomreservations.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "MEETINGROOM")
public class MeetingRoom {

	@Id
	@Column(name = "MEETINGROOM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer meetingRoomId;

	@Column(name = "NAME")
	@NotNull(message = "Naam van vergaderzaal is verplicht")
	private String name;
	
	@OneToMany(targetEntity = Reservation.class, mappedBy = "meetingRoom") // TargetEntity aangepast
	@JsonIgnore
    private List<Reservation> reservations = new ArrayList<Reservation>();

	public Integer getMeetingRoomId() {
		return meetingRoomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMeetingRoomId(Integer id) {
		this.meetingRoomId = id;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	@Override
	public String toString() {
		return "MeetingRoom [meetingRoomId=" + meetingRoomId + ", name=" + name
				+ ", reservations=" + reservations + "]";
	}

}
