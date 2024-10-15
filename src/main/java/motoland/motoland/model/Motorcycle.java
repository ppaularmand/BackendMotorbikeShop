package motoland.motoland.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motociclete")
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "serie_sasiu", nullable = false, unique = true)
    private String serieSasiu;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "an_fabricatie", nullable = false)
    private Integer anFabricatie;

    // Constructors
    public Motorcycle() {
    }

    public Motorcycle(String marca, String serieSasiu, String model, Integer anFabricatie) {
        this.marca = marca;
        this.serieSasiu = serieSasiu;
        this.model = model;
        this.anFabricatie = anFabricatie;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerieSasiu() {
        return serieSasiu;
    }

    public void setSerieSasiu(String serieSasiu) {
        this.serieSasiu = serieSasiu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(Integer anFabricatie) {
        this.anFabricatie = anFabricatie;
    }
}
