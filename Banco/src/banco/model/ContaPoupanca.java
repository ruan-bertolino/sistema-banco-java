package banco.model;

import javax.swing.JOptionPane;

public class ContaPoupanca extends ContaBancaria {
    private double taxaRendimento; // porcentagem de rendimento

    // Construtor
    public ContaPoupanca(String numeroConta, Cliente titular, double saldoInicial, double taxaRendimento) {
        super(numeroConta, titular, saldoInicial);
        this.taxaRendimento = taxaRendimento;
    }

    // Método que calcula o rendimento estimado 
    public double calcularRendimento() {
        return getSaldo() * (taxaRendimento / 100);
    }

    // Método que aplica o rendimento ao saldo
    public void aplicarRendimento() {
        double rendimento = calcularRendimento();
        depositar(rendimento);
        registrarTransacao("Rendimento aplicado: R$ " + String.format("%.2f", rendimento));
    }

    // Implementando o método abstrato gerarExtrato
    @Override
    public void gerarExtrato() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Extrato da Conta Poupança ===\n");
        sb.append("Número da conta: ").append(getNumeroConta()).append("\n");
        sb.append("Titular: ").append(getTitular().getNome()).append("\n");
        sb.append("Saldo atual: R$ ").append(String.format("%.2f", getSaldo())).append("\n");
        sb.append("Taxa de rendimento: ").append(taxaRendimento).append("%\n");
        sb.append("Rendimento estimado próximo mês: R$ ")
          .append(String.format("%.2f", calcularRendimento())).append("\n\n");
        sb.append("Histórico de transações:\n");
        for (String transacao : getHistorico()) {
            sb.append(transacao).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    @Override
    public void exibirSaldo() {
        JOptionPane.showMessageDialog(null,
            "Saldo atual da Conta Poupança: R$ " + String.format("%.2f", getSaldo()) +
            "\nTaxa de rendimento: " + taxaRendimento + "%");
    }

    // Getter para taxa de rendimento
    public double getTaxaRendimento() {
        return taxaRendimento;
    }
}
