package motoland.motoland.model;


import jakarta.persistence.*;

@Entity
@Table(name = "clienti")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nume_complet", nullable = false)
    private String numeComplet;

    @Column(name = "telefon", nullable = false)
    private String telefon;

    @Column(name = "id_motocicleta", nullable = false)
    private Long idMotocicleta;

    @Column(name = "cnp")
    private String cnp;  // CNP is optional

    @Column(name = "adresa")
    private String adresa;  // Address is optional

    // Constructors
    public Client() {
    }

    public Client(String numeComplet, String telefon, Long idMotocicleta, String cnp, String adresa) {
        this.numeComplet = numeComplet;
        this.telefon = telefon;
        this.idMotocicleta = idMotocicleta;
        this.cnp = cnp;
        this.adresa = adresa;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeComplet() {
        return numeComplet;
    }

    public void setNumeComplet(String numeComplet) {
        this.numeComplet = numeComplet;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Long getIdMotocicleta() {
        return idMotocicleta;
    }

    public void setIdMotocicleta(Long idMotocicleta) {
        this.idMotocicleta = idMotocicleta;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}