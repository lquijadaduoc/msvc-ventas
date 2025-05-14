package cl.duoc.msvc_ventas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
    private Integer numeroVenta;
    private Integer idProducto;
    private Integer cantidad;
    private Integer precio;

    @ManyToOne
    private Venta venta;
}
