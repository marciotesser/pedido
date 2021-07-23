package br.com.totvs.pedido;

import br.com.totvs.pedido.dto.PedidoDto;
import br.com.totvs.pedido.form.PedidoForm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {

    void validaFinalizacaoPedido(Pedido pedido);

    ResponseEntity<Pedido> salvarPedido(PedidoForm pedidoForm);

    ResponseEntity<Pedido> atualizarPedido(Long id, Pedido pedido);

    ResponseEntity<Pedido> finalizarPedido(Long id, Pedido pedido);

    ResponseEntity<Pedido> carregarPedido(Long id);

    ResponseEntity<Object> excluirPedido(Long id);

    List<PedidoDto> retornarPedidos();
}
