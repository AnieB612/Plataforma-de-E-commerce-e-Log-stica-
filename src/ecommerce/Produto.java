package ecommerce;


class Produto {
    int codigo;          // chave de busca da arvore
    String nome;
    double preco;
    Produto esquerda;    // filho com codigo menor
    Produto direita;     // filho com codigo maior

    Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }
}
