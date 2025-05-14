package cl.duoc.msvc_ventas.model.interfaces;

import java.time.LocalDate;

public interface DetalleVentaInterface {
    Integer getNumeroVenta();
    Integer getIdProducto();
    String getNombreProducto();
    LocalDate getFechaVenta();
    Integer getPrecioUnitario();
    Integer getPrecioTotal();
}
