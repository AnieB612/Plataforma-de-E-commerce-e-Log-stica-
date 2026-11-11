package ecommerce;

import java.util.Scanner;

// ============================================================
// CLASSE PRINCIPAL - menu e ligacao entre as estruturas
// ============================================================
public class Main {

    static Scanner teclado = new Scanner(System.in);
    static ABB catalogo = new ABB();        // estrutura principal
    static Fila pedidos = new Fila();       // estrutura auxiliar 1
    static Pilha historico = new Pilha();   // estrutura auxiliar 2
    static int proximoPedido = 1;           // contador do numero dos pedidos

    // Le um numero inteiro do teclado
    static int lerInt(String texto) {
        System.out.print(texto);
        return Integer.parseInt(teclado.nextLine());
    }

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println();
            System.out.println(" E-COMMERCE ");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Buscar produto (entra no historico)");
            System.out.println("3 - Remover produto");
            System.out.println("4 - Listar catalogo (percursos)");
            System.out.println("5 - Fazer pedido (entra na fila)");
            System.out.println("6 - Processar proximo pedido");
            System.out.println("7 - Listar fila de pedidos");
            System.out.println("8 - Buscar pedido na fila");
            System.out.println("9 - Voltar (historico de navegacao)");
            System.out.println("10 - Listar historico");
            System.out.println("0 - Sair");

            opcao = lerInt("Opcao: ");

            if (opcao == 1) {
                // Cadastra um produto novo na ABB
                int codigo = lerInt("Codigo: ");
                System.out.print("Nome: ");
                String nome = teclado.nextLine();
                System.out.print("Preco: ");
                double preco = Double.parseDouble(teclado.nextLine().replace(",", "."));
                catalogo.inserir(new Produto(codigo, nome, preco));

            } else if (opcao == 2) {
                // Busca na ABB e, se achar, guarda na pilha de navegacao
                Produto p = catalogo.buscar(lerInt("Codigo: "));
                if (p == null) {
                    System.out.println("Produto nao encontrado.");
                } else {
                    System.out.println(p.codigo + " - " + p.nome + " - R$ " + p.preco);
                    historico.inserir("Produto " + p.codigo + " - " + p.nome);
                }

            } else if (opcao == 3) {
                catalogo.remover(lerInt("Codigo: "));

            } else if (opcao == 4) {
                catalogo.percursos();

            } else if (opcao == 5) {
                // So aceita pedido se o produto existir na ABB
                int codigo = lerInt("Codigo do produto: ");
                if (catalogo.buscar(codigo) == null) {
                    System.out.println("Produto nao encontrado.");
                } else {
                    pedidos.inserir(new Pedido(proximoPedido, codigo));
                    System.out.println("Pedido " + proximoPedido + " criado.");
                    proximoPedido++;
                }

            } else if (opcao == 6) {
                // Tira o primeiro da fila e confere o produto na ABB
                Pedido pedido = pedidos.remover();
                if (pedido == null) {
                    System.out.println("Nenhum pedido na fila.");
                } else {
                    Produto p = catalogo.buscar(pedido.codigoProduto);
                    if (p == null) {
                        System.out.println("Pedido " + pedido.numero + " cancelado: produto nao existe mais.");
                    } else {
                        System.out.println("Pedido " + pedido.numero + " processado: " + p.nome + " - R$ " + p.preco);
                    }
                }

            } else if (opcao == 7) {
                pedidos.listar();

            } else if (opcao == 8) {
                Pedido pedido = pedidos.buscar(lerInt("Numero do pedido: "));
                if (pedido == null) {
                    System.out.println("Pedido nao esta na fila.");
                } else {
                    System.out.println("Pedido " + pedido.numero + " esta na fila (produto " + pedido.codigoProduto + ").");
                }

            } else if (opcao == 9) {
                // Botao voltar: tira a pagina atual e mostra a anterior
                if (historico.remover() == null) {
                    System.out.println("Historico vazio.");
                } else if (historico.topo == null) {
                    System.out.println("Voltou para a pagina inicial.");
                } else {
                    System.out.println("Voltou para: " + historico.topo.pagina);
                }

            } else if (opcao == 10) {
                historico.listar();
            }
        }
        System.out.println("Encerrado");
    }
}
