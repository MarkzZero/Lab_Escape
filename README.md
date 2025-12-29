# Lab Escape — Jogo em Java

## Descrição
Lab Escape é um jogo simples desenvolvido em Java, com foco em lógica de programação, estruturas de repetição, matrizes e interação com o usuário via JOptionPane.  
O objetivo do jogador é escapar de um laboratório abandonado navegando por um mapa representado em forma de matriz.

O projeto foi pensado com caráter acadêmico, simulando um cenário típico de prova ou trabalho prático de programação.

---

## Objetivo do Jogo
O jogador deve:
- Percorrer o laboratório representado por um mapa NxM
- Desviar de paredes (#)
- Caminhar apenas por áreas livres (.)
- Gerenciar seus movimentos até encontrar a saída

---

## Mapa do Jogo
- O mapa é uma matriz bidimensional (String[][])
- Símbolos utilizados:
  - # → Parede
  - . → Espaço livre
  - * → Elementos especiais (energia, itens ou obstáculos)
  - P → Personagem

 ---

## Mecânicas Implementadas
- Geração dinâmica do mapa
- Criação de paredes externas e internas
- Preenchimento automático de espaços vazios
- Menu interativo com JOptionPane
- Uso de botões com ícones ASCII para movimentação

  ---

## Controles
Os movimentos do personagem são feitos através de botões:

- ← mover para a esquerda
- → mover para a direita
- ↑ mover para cima
- ↓ mover para baixo

---

## Tecnologias Utilizadas
- Java SE
- Swing (JOptionPane, JTextArea)
- Programação orientada a objetos

---

## Conceitos Trabalhados
- Matrizes (NxM)
- Laços for e while
- Condições lógicas
- Encapsulamento
- Separação de responsabilidades (MVC básico)

---

## Como Executar
1. Abra o projeto em uma IDE Java (Eclipse, IntelliJ ou VS Code)
2. Compile as classes
3. Execute a classe principal do menu
4. Utilize os botões para navegar pelo jogo

---

## Observações
Este projeto não tem foco gráfico avançado, mas sim no raciocínio lógico e na estruturação de código, sendo ideal para estudo e provas práticas.

