package br.com.totvs.pedido;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {


    @EntityGraph(attributePaths = {"pedidoProdutos"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Query("select p from Pedido p order by p.id desc")
    @Override
    List<Pedido> findAll();
}
