# sistema-banco-java
# 🏦 Projeto Java - Sistema Bancário

## 📌 Sobre
Este projeto foi desenvolvido em Java como exercício acadêmico.  
O objetivo é simular um sistema bancário simples, permitindo cadastrar contas, realizar operações financeiras e gerar relatórios, tudo através de janelas interativas com **JOptionPane**.

## 🚀 Funcionalidades
- Cadastrar **Conta Corrente** (com limite de cheque especial)
- Cadastrar **Conta Poupança** (com taxa de rendimento)
- Realizar depósitos e saques
- Consultar saldo
- Exibir extrato detalhado
- Exibir histórico de transações
- Listar todas as contas cadastradas
- Gerar relatório geral (patrimônio total, maior e menor saldo)

## 🛠️ Tecnologias utilizadas
- Java 8 ou superior (recomendado Java 17 LTS)
- Biblioteca Swing (JOptionPane) para interface gráfica simples
- IDE: Eclipse Version 2026-03 (4.39.0) ou IntelliJ IDEA


## 📂 Estrutura do Projeto
- **banco.interfaces**
  - `Operavel` → Interface com operações básicas.
- **banco.model**
  - `Cliente` → Dados do titular.
  - `ContaBancaria` → Classe abstrata base.
  - `ContaCorrente` → Implementa cheque especial.
  - `ContaPoupanca` → Implementa taxa de rendimento.
- **banco.service**
  - `BancoService` → Gerencia contas e relatórios.
- **banco.app**
  - `SistemaBanco` → Classe principal com menu interativo.

## ▶️ Como executar
1. Clone este repositório:
   ```bash
   git clone https://github.com/seuusuario/sistema-banco.git
