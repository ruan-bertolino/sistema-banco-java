package banco.model;

import javax.swing.JOptionPane;

public class ContaCorrente extends ContaBancaria {
    private double limiteChequeEspecial;

    // Construtor
    public ContaCorrente(String numeroConta, Cliente titular, double saldoInicial, double limiteChequeEspecial) {
        super(numeroConta, titular, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // Sobrescrevendo o método sacar para considerar o cheque especial
    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && (getSaldo() + limiteChequeEspecial) >= valor) {
            if (valor <= getSaldo()) {
                super.sacar(valor);
            } else {
                double restante = valor - getSaldo();
                super.sacar(getSaldo()); // zera saldo
                limiteChequeEspecial -= restante; // usa cheque especial
                registrarTransacao("Saque utilizando cheque especial de R$ " + String.format("%.2f", restante));
                
                // ALERTA 
                JOptionPane.showMessageDialog(null, "Cheque especial ativado!");
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente, mesmo com cheque especial!");
            return false;
        }
    }

    @Override
    public void gerarExtrato() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Extrato da Conta Corrente ===\n");
        sb.append("Número da conta: ").append(getNumeroConta()).append("\n");
        sb.append("Titular: ").append(getTitular().getNome()).append("\n");
        sb.append("Saldo atual: R$ ").append(String.format("%.2f", getSaldo())).append("\n");
        sb.append("Limite de cheque especial disponível: R$ ").append(String.format("%.2f", limiteChequeEspecial)).append("\n\n");
        sb.append("Histórico de transações:\n");
        for (String transacao : getHistorico()) {
            sb.append(transacao).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void exibirSaldo() {
        JOptionPane.showMessageDialog(null,
            "Saldo atual da Conta Corrente: R$ " + String.format("%.2f", getSaldo()) +
            "\nLimite de cheque especial disponível: R$ " + String.format("%.2f", limiteChequeEspecial));
    }
}
