package ecommerce;

// PILHA (lista encadeada) - historico de navegacao (botao voltar)
// Regra LIFO: o ultimo que entra e o primeiro que sai.
// ============================================================
class Pilha {
    NoPilha topo;

    // ---------- INSERIR (empilhar) ----------
    void inserir(String pagina) {
        NoPilha novo = new NoPilha(pagina);
        novo.abaixo = topo;
        topo = novo;
    }

    // ---------- REMOVER (desempilhar) ----------
    // Tira o topo e devolve o texto dele
    String remover() {
        if (topo == null) {
            return null; // pilha vazia
        }
        String pagina = topo.pagina;
        topo = topo.abaixo;
        return pagina;
    }

    // ---------- BUSCAR ----------
    // Verifica se uma pagina esta no historico
    boolean buscar(String pagina) {
        NoPilha atual = topo;
        while (atual != null) {
            if (atual.pagina.equals(pagina)) {
                return true;
            }
            atual = atual.abaixo;
        }
        return false;
    }

    // ---------- LISTAR ----------
    // Mostra do topo (mais recente) para baixo
    void listar() {
        if (topo == null) {
            System.out.println("Historico vazio.");
            return;
        }
        NoPilha atual = topo;
        while (atual != null) {
            System.out.println(atual.pagina);
            atual = atual.abaixo;
        }
    }
}
