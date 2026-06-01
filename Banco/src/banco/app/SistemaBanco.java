package banco.app;

import javax.swing.JOptionPane;
import banco.model.Cliente;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

public class SistemaBanco {
    public static void main(String[] args) {
        BancoService banco = new BancoService();
        boolean continuar = true;

        while (continuar) {
            String opcao = JOptionPane.showInputDialog(
                "==== MENU BANCO ====\n" +
                "1 - Cadastrar Conta Corrente\n" +
                "2 - Cadastrar Conta Poupança\n" +
                "3 - Depositar\n" +
                "4 - Sacar\n" +
                "5 - Consultar Saldo\n" +
                "6 - Exibir Extrato da Conta\n" +
                "7 - Exibir Histórico de Transações\n" +
                "8 - Listar Todas as Contas\n" +
                "9 - Relatório Geral do Banco\n" +
                "0 - Encerrar Sistema"
            );

            if (opcao == null || opcao.equals("0")) {
                continuar = false;
            } else {
                switch (opcao) {
                    case "1": // Cadastrar Conta Corrente
                        String nomeCC = JOptionPane.showInputDialog("Nome do titular:");
                        String cpfCC = JOptionPane.showInputDialog("CPF do titular:");
                        String telCC = JOptionPane.showInputDialog("Telefone do titular:");
                        Cliente clienteCC = new Cliente(nomeCC, cpfCC, telCC);
                        String numCC = JOptionPane.showInputDialog("Número da conta:");
                        String entradaSaldoCC = JOptionPane.showInputDialog("Saldo inicial:");
                        double saldoCC = Double.parseDouble(entradaSaldoCC.replace(",", "."));
                        String entradaLimite = JOptionPane.showInputDialog("Limite cheque especial:");
                        double limite = Double.parseDouble(entradaLimite.replace(",", "."));
                        ContaCorrente cc = new ContaCorrente(numCC, clienteCC, saldoCC, limite);
                        banco.cadastrarContaCorrente(cc);
                        break;

                    case "2": // Cadastrar Conta Poupança
                        String nomeCP = JOptionPane.showInputDialog("Nome do titular:");
                        String cpfCP = JOptionPane.showInputDialog("CPF do titular:");
                        String telCP = JOptionPane.showInputDialog("Telefone do titular:");
                        Cliente clienteCP = new Cliente(nomeCP, cpfCP, telCP);
                        String numCP = JOptionPane.showInputDialog("Número da conta:");
                        String entradaSaldoCP = JOptionPane.showInputDialog("Saldo inicial:");
                        double saldoCP = Double.parseDouble(entradaSaldoCP.replace(",", "."));
                        String entradaTaxa = JOptionPane.showInputDialog("Taxa de rendimento (%):");
                        double taxa = Double.parseDouble(entradaTaxa.replace(",", "."));
                        ContaPoupanca cp = new ContaPoupanca(numCP, clienteCP, saldoCP, taxa);
                        banco.cadastrarContaPoupanca(cp);
                        break;

                    case "3": // Depositar
                        String numDep = JOptionPane.showInputDialog("Número da conta:");
                        String entradaDep = JOptionPane.showInputDialog("Valor do depósito:");
                        double valorDep = Double.parseDouble(entradaDep.replace(",", "."));
                        var contaDep = banco.buscarConta(numDep);
                        if (contaDep != null) contaDep.depositar(valorDep);
                        else JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;

                    case "4": // Sacar
                        String numSaq = JOptionPane.showInputDialog("Número da conta:");
                        String entradaSaq = JOptionPane.showInputDialog("Valor do saque:");
                        double valorSaq = Double.parseDouble(entradaSaq.replace(",", "."));
                        var contaSaq = banco.buscarConta(numSaq);
                        if (contaSaq != null) contaSaq.sacar(valorSaq);
                        else JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;

                    case "5": // Consultar saldo
                        String numSaldo = JOptionPane.showInputDialog("Número da conta:");
                        var contaSaldo = banco.buscarConta(numSaldo);
                        if (contaSaldo != null) contaSaldo.exibirSaldo();
                        else JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;

                    case "6": // Extrato
                        String numExt = JOptionPane.showInputDialog("Número da conta:");
                        var contaExt = banco.buscarConta(numExt);
                        if (contaExt != null) contaExt.gerarExtrato();
                        else JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;

                    case "7": // Histórico
                        String numHist = JOptionPane.showInputDialog("Número da conta:");
                        var contaHist = banco.buscarConta(numHist);
                        if (contaHist != null) contaHist.exibirHistorico();
                        else JOptionPane.showMessageDialog(null, "Conta não encontrada!");
                        break;

                    case "8": // Listar todas as contas
                        banco.listarTodasAsContas();
                        break;

                    case "9": // Relatório geral
                        banco.exibirRelatorioGeral();
                        break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado. Obrigado por usar o Banco!");
    }
}
