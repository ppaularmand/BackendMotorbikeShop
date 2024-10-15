package motoland.motoland.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produse")
public class Produs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_produs", nullable = false)
    private String codProdus;

    @Column(name = "denumire", nullable = false)
    private String denumire;

    @Column(name = "nr_bucati", nullable = false)
    private Integer nrBucati;

    @Column(name = "pret", nullable = false)
    private Double pret;

    @Column(name = "status", nullable = false)
    private String status;

    // Many-to-Many relationship with Comanda
    @ManyToMany(mappedBy = "produse", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore // Prevents circular reference during JSON serialization
    private Set<Comanda> comenzi = new HashSet<>();

    // Constructors
    public Produs() {}

    public Produs(String codProdus, String denumire, Integer nrBucati, Double pret, String status) {
        this.codProdus = codProdus;
        this.denumire = denumire;
        this.nrBucati = nrBucati;
        this.pret = pret;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodProdus() {
        return codProdus;
    }

    public void setCodProdus(String codProdus) {
        this.codProdus = codProdus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Integer getNrBucati() {
        return nrBucati;
    }

    public void setNrBucati(Integer nrBucati) {
        this.nrBucati = nrBucati;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(Set<Comanda> comenzi) {
        this.comenzi = comenzi;
    }
}