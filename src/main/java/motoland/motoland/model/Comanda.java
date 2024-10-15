package motoland.motoland.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comenzi")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Many-to-Many relationship with Produs
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "comanda_produs",
            joinColumns = @JoinColumn(name = "comanda_id"),
            inverseJoinColumns = @JoinColumn(name = "produs_id")
    )
    private Set<Produs> produse = new HashSet<>();

    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "pret_total", nullable = false)
    private Double pretTotal;

    @Column(name = "status", nullable = false)
    private String status;

    // Constructors
    public Comanda() {}

    public Comanda(Client client, Date data, Double pretTotal, String status) {
        this.client = client;
        this.data = data;
        this.pretTotal = pretTotal;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Produs> getProduse() {
        return produse;
    }

    public void setProduse(Set<Produs> produse) {
        this.produse = produse;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(Double pretTotal) {
        this.pretTotal = pretTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProdus(Produs produs) {
        produse.add(produs);
        produs.getComenzi().add(this);
    }

    public void removeProdus(Produs produs) {
        produse.remove(produs);
        produs.getComenzi().remove(this);
    }
}
