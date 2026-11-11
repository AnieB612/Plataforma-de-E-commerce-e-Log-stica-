package ecommerce;



// FILA (lista encadeada) - pedidos aguardando processamento
// Regra FIFO: o primeiro que entra e o primeiro que sai.
// ============================================================
class Fila {
    Pedido inicio;  // proximo a ser processado
    Pedido fim;     // ultimo que chegou

    // ---------- INSERIR ----------
    // Coloca o pedido no final da fila
    void inserir(Pedido novo) {
        if (inicio == null) {
            inicio = novo;   // fila vazia: o novo e o primeiro e o ultimo
        } else {
            fim.proximo = novo;
        }
        fim = novo;
    }

    // ---------- REMOVER ----------
    // Tira o pedido do inicio da fila e devolve ele
    Pedido remover() {
        if (inicio == null) {
            return null; // fila vazia
        }
        Pedido removido = inicio;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null; // a fila ficou vazia
        }
        removido.proximo = null;
        return removido;
    }

    // ---------- BUSCAR ----------
    // Procura um pedido pelo numero. Retorna null se nao achar.
    Pedido buscar(int numero) {
        Pedido atual = inicio;
        while (atual != null) {
            if (atual.numero == numero) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    // ---------- LISTAR ----------
    void listar() {
        if (inicio == null) {
            System.out.println("Nenhum pedido na fila.");
            return;
        }
        Pedido atual = inicio;
        while (atual != null) {
            System.out.println("Pedido " + atual.numero + " (produto " + atual.codigoProduto + ")");
            atual = atual.proximo;
        }
    }
}
