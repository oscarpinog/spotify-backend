package com.seek.codificacion.repositories;



//@Repository
//public interface PedidoEjecucionRepository extends JpaRepository<PedidoEjecucionEntity, Integer> {
//	
//	@Query(value ="SELECT * FROM PEDIDOS_EJECUCION p WHERE p.NUM_PEDIDO_EJECUCION = ?1 ",nativeQuery = true)
//	PedidoEjecucionEntity getPedEjecByNumPedidoEje(BigDecimal numPedidoEjecucion);
//	
//	@Query(value ="SELECT COUNT(*) FROM PEDIDOS_EJECUCION p	RIGHT JOIN EMBARQUES e ON (p.ID_PED_EJECUCION=e.ID_PED_EJEC_FK)WHERE p.ID_PED_EJECUCION = ?1 AND e.NUM_EMBARQUE = ?2",nativeQuery = true)
//	int existEmbarque(int idPedEje, int numEmbarque);
//	
//	@Query(value="SELECT * FROM PEDIDOS_EJECUCION p INNER JOIN EMBARQUES e ON (p.ID_PED_EJECUCION = e.ID_PED_EJEC_FK) WHERE e.ID_EMBARQUE = ?1", nativeQuery = true)
//	Optional<PedidoEjecucionEntity> findPedidoEjecucionByIdEmbarque(int idEmbarque);
//}
