package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.appointment.Appointment;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments;

    public Client() {
    }

    public Client(String name, String email, String phoneNumber, List<Appointment> appointments) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.appointments = appointments;
    }

    public Client(ClientRegistrationDTO clientDTO) {
        this.name = clientDTO.name();
        this.email = clientDTO.email();
        this.phoneNumber = clientDTO.phoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(email, client.email) && Objects.equals(appointments, client.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, appointments);
    }
}
