package be.rubus.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MEETINGROOM")
public class MeetingRoom {

	@Id
	@Column(name = "MEETINGROOM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer meetingRoom_id;

	@Column(name = "NAME")
	@NotNull(message = "Naam van vergaderzaal is verplicht")
	private String name;
	
	@OneToMany(mappedBy = "meetingRoom") // , cascade = CascadeType.ALL
    private List<Reservation> reservations = new ArrayList<Reservation>();

	public Integer getId() {
		return meetingRoom_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.meetingRoom_id = id;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
