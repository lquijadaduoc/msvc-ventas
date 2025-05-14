package cl.duoc.msvc_ventas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.msvc_ventas.model.Venta;

public interface VentaRepository extends JpaRepository<Venta,Integer>{
    
}
