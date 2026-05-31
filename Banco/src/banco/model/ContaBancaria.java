package banco.model;

import banco.interfaces.Operavel;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria implements Operavel {
    private String numeroConta;
    private Cliente titular;
    private double saldo;
    private List<String> historico;

    // Construtor
    public ContaBancaria(String numeroConta, Cliente titular, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historico = new ArrayList<>();
        registrarTransacao("Conta criada com saldo inicial: R$ " + String.format("%.2f", saldoInicial));
    }

    // Método para registrar transações
    protected void registrarTransacao(String descricao) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        historico.add(agora.format(formatter) + " - " + descricao);
    }

    // Depósito
    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            registrarTransacao("Depósito de R$ " + String.format("%.2f", valor));
        } else {
            JOptionPane.showMessageDialog(null, "Valor inválido para depósito!");
        }
    }

    // Saque
    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            registrarTransacao("Saque de R$ " + String.format("%.2f", valor));
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente ou valor inválido!");
            return false;
        }
    }

    // Exibir histórico
    public void exibirHistorico() {
        StringBuilder sb = new StringBuilder("Histórico da conta " + numeroConta + ":\n");
        for (String transacao : historico) {
            sb.append(transacao).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Métodos abstratos que as subclasses devem implementar
    public abstract void gerarExtrato();
    public abstract void exibirSaldo();

    // Getters
    public String getNumeroConta() { return numeroConta; }
    public Cliente getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    protected List<String> getHistorico() { return historico; }
}
