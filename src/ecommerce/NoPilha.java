package ecommerce;


// NO DA PILHA
// ============================================================
class NoPilha {
    String pagina;     // texto da pagina visitada
    NoPilha abaixo;    // elemento que esta embaixo dele

    NoPilha(String pagina) {
        this.pagina = pagina;
    }
}
