package ecommerce;

// CLASSE PEDIDO
// Cada pedido tambem e um no da fila (tem o campo proximo).
// ============================================================
class Pedido {
    int numero;          // numero do pedido
    int codigoProduto;   // codigo do produto comprado (chave da ABB)
    Pedido proximo;      // proximo pedido da fila

    Pedido(int numero, int codigoProduto) {
        this.numero = numero;
        this.codigoProduto = codigoProduto;
    }
}
