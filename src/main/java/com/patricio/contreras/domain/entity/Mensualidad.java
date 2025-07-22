package com.patricio.contreras.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patricio.contreras.domain.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensualidades")
public class Mensualidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto",nullable = false)
    private int monto;

    @Column(name = "fecha_vencimiento",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
    private User usuario;

    @Column(name= "enabled",nullable = false)
    private boolean enabled = true;


    @OneToOne(mappedBy = "mensualidad", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Pago pago;
}
