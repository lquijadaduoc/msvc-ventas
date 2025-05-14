package cl.duoc.msvc_ventas.model;

import cl.duoc.msvc_ventas.model.claves.DetalleVentaId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_venta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleVenta {
    
    @EmbeddedId
    private DetalleVentaId id;

    private Integer cantidad;
    private Integer precio;

    @ManyToOne
    @MapsId("numeroVenta") 
    @JoinColumn(name = "numero_venta", insertable = false, updatable = false)
    private Venta venta;
}
