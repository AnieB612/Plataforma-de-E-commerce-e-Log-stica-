package ecommerce;



// ARVORE BINARIA DE BUSCA (ABB) - catalogo de produtos
// Regra: menor codigo vai para a esquerda, maior para a direita.
// ============================================================
class ABB {
    Produto raiz;

    // ---------- INSERIR ----------
    // Metodo publico: so chama o recursivo comecando pela raiz
    void inserir(Produto novo) {
        raiz = inserir(raiz, novo);
    }

    // Metodo recursivo: desce na arvore ate achar o lugar vazio
    private Produto inserir(Produto atual, Produto novo) {
        if (atual == null) {
            return novo; // achou o lugar, o novo vira esse no
        }
        if (novo.codigo < atual.codigo) {
            atual.esquerda = inserir(atual.esquerda, novo);
        } else if (novo.codigo > atual.codigo) {
            atual.direita = inserir(atual.direita, novo);
        } else {
            System.out.println("Ja existe produto com esse codigo.");
        }
        return atual;
    }

    // ---------- BUSCAR ----------
    // Anda pela arvore comparando o codigo. Retorna null se nao achar.
    Produto buscar(int codigo) {
        Produto atual = raiz;
        while (atual != null) {
            if (codigo == atual.codigo) {
                return atual;
            } else if (codigo < atual.codigo) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
        return null;
    }

    // ---------- REMOVER ----------
    void remover(int codigo) {
        raiz = remover(raiz, codigo);
    }

    private Produto remover(Produto atual, int codigo) {
        if (atual == null) {
            System.out.println("Produto nao encontrado.");
            return null;
        }
        if (codigo < atual.codigo) {
            atual.esquerda = remover(atual.esquerda, codigo);
        } else if (codigo > atual.codigo) {
            atual.direita = remover(atual.direita, codigo);
        } else {
            // Achou o no que sera removido. Tem 3 casos:

            // Caso 1: no folha (sem filhos) -> simplesmente some
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }
            // Caso 2: so um filho -> o filho ocupa o lugar do no removido
            if (atual.esquerda == null) {
                return atual.direita;
            }
            if (atual.direita == null) {
                return atual.esquerda;
            }
            // Caso 3: dois filhos -> pega o menor da subarvore direita
            // (sucessor), copia os dados dele para este no e depois
            // remove o sucessor la de baixo.
            Produto sucessor = atual.direita;
            while (sucessor.esquerda != null) {
                sucessor = sucessor.esquerda;
            }
            atual.codigo = sucessor.codigo;
            atual.nome = sucessor.nome;
            atual.preco = sucessor.preco;
            atual.direita = remover(atual.direita, sucessor.codigo);
        }
        return atual;
    }

    // ---------- PERCURSOS ----------
    // In-ordem: esquerda, raiz, direita (sai em ordem crescente de codigo)
    void emOrdem(Produto atual) {
        if (atual != null) {
            emOrdem(atual.esquerda);
            System.out.println(atual.codigo + " - " + atual.nome + " - R$ " + atual.preco);
            emOrdem(atual.direita);
        }
    }

    // Pre-ordem: raiz, esquerda, direita
    void preOrdem(Produto atual) {
        if (atual != null) {
            System.out.println(atual.codigo + " - " + atual.nome + " - R$ " + atual.preco);
            preOrdem(atual.esquerda);
            preOrdem(atual.direita);
        }
    }

    // Pos-ordem: esquerda, direita, raiz
    void posOrdem(Produto atual) {
        if (atual != null) {
            posOrdem(atual.esquerda);
            posOrdem(atual.direita);
            System.out.println(atual.codigo + " - " + atual.nome + " - R$ " + atual.preco);
        }
    }

    // Mostra os tres percursos de uma vez
    void percursos() {
        if (raiz == null) {
            System.out.println("Catalogo vazio.");
            return;
        }
        System.out.println("--- In-ordem ---");
        emOrdem(raiz);
        System.out.println("--- Pre-ordem ---");
        preOrdem(raiz);
        System.out.println("--- Pos-ordem ---");
        posOrdem(raiz);
    }
}
