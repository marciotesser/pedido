package br.com.totvs.pedido;

import br.com.totvs.pedido.dto.PedidoDto;
import br.com.totvs.pedido.form.PedidoForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public void validaFinalizacaoPedido(Pedido pedido) {
        if (pedido.getPedidoProdutos().isEmpty()) {
            throw new RuntimeException("Para finalizar o pedido, deve existir pelo menos um produto!");
        }
    }

    @Override
    public ResponseEntity<Pedido> salvarPedido(PedidoForm pedidoForm) {
        var pedido = pedidoForm.converter();
        pedido.getPedidoProdutos().forEach(pedidoProduto -> pedidoProduto.setPedido(pedido));
        this.pedidoRepository.save(pedido);
        return ResponseEntity.ok().body(pedido);
    }

    @Override
    public ResponseEntity<Pedido> atualizarPedido(Long id, Pedido pedido) {
        var optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            var pedidoRet = this.pedidoRepository.save(pedido);
            return ResponseEntity.ok().body(pedidoRet);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Pedido> finalizarPedido(Long id, Pedido pedido) {
        validaFinalizacaoPedido(pedido);
        pedido.setSituacao(PedidoSituacao.FINALIZADO);
        return atualizarPedido(id, pedido);
    }

    @Override
    public ResponseEntity<Pedido> carregarPedido(Long id) {
        var optional = pedidoRepository.findById(id);
        return optional.map(pedido -> ResponseEntity.ok().body(pedido))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Object> excluirPedido(Long id) {
        var optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            pedidoRepository.delete(optional.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public List<PedidoDto> retornarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return PedidoDto.converter(pedidos);
    }


}
