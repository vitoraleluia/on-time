package dev.vitoraleluia.ontime.client;

import dev.vitoraleluia.ontime.appointment.Appointment;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    @OneToMany
    private List<Appointment> appointments;

    public Client() {
    }

    public Client(String name, LocalDate dateOfBirth, String email, String password, List<Appointment> appointments) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.appointments = appointments;
    }

    public Client(ClientDTO clientDTO) {
        this.name = clientDTO.name();
        this.dateOfBirth = clientDTO.dateOfBirth();
        this.email = clientDTO.email();
        this.password = clientDTO.password();
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(dateOfBirth, client.dateOfBirth) && Objects.equals(email, client.email) && Objects.equals(password, client.password) && Objects.equals(appointments, client.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, email, password, appointments);
    }
}
