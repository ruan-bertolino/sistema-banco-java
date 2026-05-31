package banco.service;

import banco.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class BancoService {
    private List<ContaCorrente> contasCorrentes;
    private List<ContaPoupanca> contasPoupanca;

    public BancoService() {
        contasCorrentes = new ArrayList<>();
        contasPoupanca = new ArrayList<>();
    }

    // Cadastrar contas
    public void cadastrarContaCorrente(ContaCorrente cc) {
        if (buscarConta(cc.getNumeroConta()) == null) {
            contasCorrentes.add(cc);
        } else {
            JOptionPane.showMessageDialog(null, "Número de conta já existe!");
        }
    }

    public void cadastrarContaPoupanca(ContaPoupanca cp) {
        if (buscarConta(cp.getNumeroConta()) == null) {
            contasPoupanca.add(cp);
        } else {
            JOptionPane.showMessageDialog(null, "Número de conta já existe!");
        }
    }

    // Buscar conta por número
    public ContaBancaria buscarConta(String numeroConta) {
        for (ContaCorrente cc : contasCorrentes) {
            if (cc.getNumeroConta().equals(numeroConta)) return cc;
        }
        for (ContaPoupanca cp : contasPoupanca) {
            if (cp.getNumeroConta().equals(numeroConta)) return cp;
        }
        return null;
    }

    // Listar todas as contas
    public void listarTodasAsContas() {
        StringBuilder sb = new StringBuilder("=== Contas cadastradas ===\n");
        for (ContaCorrente cc : contasCorrentes) {
            sb.append("Corrente - ").append(cc.getNumeroConta())
              .append(" | Titular: ").append(cc.getTitular().getNome())
              .append(" | Saldo: R$ ").append(String.format("%.2f", cc.getSaldo()))
              .append("\n");
        }
        for (ContaPoupanca cp : contasPoupanca) {
            sb.append("Poupança - ").append(cp.getNumeroConta())
              .append(" | Titular: ").append(cp.getTitular().getNome())
              .append(" | Saldo: R$ ").append(String.format("%.2f", cp.getSaldo()))
              .append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Calcular patrimônio total
    public double calcularPatrimonioTotal() {
        double total = 0;
        for (ContaCorrente cc : contasCorrentes) total += cc.getSaldo();
        for (ContaPoupanca cp : contasPoupanca) total += cp.getSaldo();
        return total;
    }

 // Relatório geral completo
    public void exibirRelatorioGeral() {
        double total = calcularPatrimonioTotal();
        ContaBancaria maior = null, menor = null;

        // Percorre contas correntes
        for (ContaCorrente cc : contasCorrentes) {
            if (maior == null || cc.getSaldo() > maior.getSaldo()) maior = cc;
            if (menor == null || cc.getSaldo() < menor.getSaldo()) menor = cc;
        }
        // Percorre contas poupança
        for (ContaPoupanca cp : contasPoupanca) {
            if (maior == null || cp.getSaldo() > maior.getSaldo()) maior = cp;
            if (menor == null || cp.getSaldo() < menor.getSaldo()) menor = cp;
        }

        StringBuilder sb = new StringBuilder("=== Relatório Geral do Banco ===\n");
        sb.append("Total de contas correntes: ").append(contasCorrentes.size()).append("\n");
        sb.append("Total de contas poupança: ").append(contasPoupanca.size()).append("\n");
        sb.append("Patrimônio total do banco: R$ ").append(String.format("%.2f", total)).append("\n");

        if (maior != null) {
            sb.append("Conta com maior saldo: ").append(maior.getNumeroConta())
              .append(" | Titular: ").append(maior.getTitular().getNome())
              .append(" | Saldo: R$ ").append(String.format("%.2f", maior.getSaldo())).append("\n");
        }
        if (menor != null) {
            sb.append("Conta com menor saldo: ").append(menor.getNumeroConta())
              .append(" | Titular: ").append(menor.getTitular().getNome())
              .append(" | Saldo: R$ ").append(String.format("%.2f", menor.getSaldo())).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

}
