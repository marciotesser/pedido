package br.com.totvs.pedido;

import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {


    @Override
    public void validaFinalizacaoPedido(Pedido pedido) {
        if (pedido.getPedidoProdutos().isEmpty()) {
            throw new RuntimeException("Para finalizar o pedido, deve existir pelo menos um produto!");
        }
    }
}
