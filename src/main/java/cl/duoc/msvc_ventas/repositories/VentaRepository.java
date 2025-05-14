package cl.duoc.msvc_ventas.repositories;

import org.springframework.data.repository.CrudRepository;

import cl.duoc.msvc_ventas.model.Venta;

public interface VentaRepository extends CrudRepository<Venta,Integer>{
    
}
