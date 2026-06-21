# Simulado: Manutenção de Software

---

## Parte 1 – Código Limpo, Legibilidade e Comentários

### 1. O que é Código Limpo?

Código limpo são práticas adotadas por desenvolvedores com o objetivo de facilitar a leitura do código — tanto entre programadores quanto para stakeholders do negócio — reduzindo a complexidade de leitura e auxiliando em futuras manutenções.

---

### 2. Nomenclatura de Variáveis

**a) Exemplos de bons nomes:**

- `diasDesdeUltimoLogin`
- `valorTotalPedido`

**b) Exemplos de nomes ruins:**

- `dias`
- `x`
- `v`

**c) O que define um bom nome de variável?**

Um bom nome deve comunicar de forma clara o que a variável armazena. Por exemplo, se uma variável armazena nomes masculinos, ela deveria se chamar `nomesMasculinos`.

---

### 3. Refatoração de Método com Parâmetros

**Versão original (problema):**

```java
public void copiar(String inicio, String destino) {
    System.out.println("Copiando de " + inicio + " para" + destino);
}
```

**a) Problema identificado:**

Os nomes dos parâmetros não comunicam nada sobre o que são ou o que fazem dentro do código.

**b) Por que a nova versão é mais clara?**

Com os nomes `inicio` e `destino`, passamos a conhecer o que as variáveis armazenam e seus objetivos: copiar o conteúdo da origem (`inicio`) para o destino (`destino`).

---

### 4. Comentários Redundantes

```java
public double calcularTotalPedido(double subtotal, double frete) {
    return subtotal + frete;
}
```

**a) Por que o comentário é redundante?**

O nome da própria função já informa o que esse trecho de código faz. Um comentário que apenas repete o nome do método não agrega valor.

**b) Quando comentários são válidos?**

- Declarações de direitos autorais e autoria (exigidas por padrão da empresa)
- Comentários informativos, como descrever o valor de retorno de um método abstrato

---

### 5. Comentário Desatualizado (Mentiroso)

**Código com comentário incorreto:**

```java
// aplica desconto de 10% para compras acima de R$ 100
public double calcularDesconto(double valorCompra) {
    if (valorCompra > 200) {
        return valorCompra * 0.15;
    }
    return 0;
}
```

**Inconsistência identificada:**

O comentário diz "10% para compras acima de R$ 100", mas o código aplica **15% para compras acima de R$ 200**.

**Impacto na manutenção:**

O comentário transmite uma informação falsa. Em vez de ajudar o desenvolvedor a entender rapidamente o que o código faz, ele induz ao erro.

**Correção do comentário:**

```java
// aplica desconto de 15% para compras acima de R$ 200
public double calcularDesconto(double valorCompra) {
    if (valorCompra > 200) {
        return valorCompra * 0.15;
    }
    return 0;
}
```

---

### 6. Código Comentado

Código comentado sempre levanta mais dúvidas do que respostas para quem lê depois, pois o próximo desenvolvedor não saberá se aquele trecho é importante ou não. Além disso, o código comentado torna-se defasado ao longo do tempo, já que o sistema continua evoluindo enquanto o trecho estático permanece parado.

---

## Parte 2 – Formatação de Código

### 7. Regra do Stepdown (Metáfora do Jornal)

**Código refatorado:**

```java
public class PedidoService {
    public void fecharPedido(String cliente, double subtotal, double frete) {
        double total = calcularTotal(subtotal, frete);
        imprimirResumo(cliente, total);
    }

    private void imprimirResumo(String cliente, double total) {
        System.out.println(cliente + ": " + total);
    }

    private double calcularTotal(double subtotal, double frete) {
        return subtotal + frete;
    }
}
```

**a) O que foi feito?**

As funções foram reordenadas para que as mais abstratas (de alto nível) apareçam primeiro, e os detalhes de implementação apareçam depois. Esse princípio é chamado de **Regra do Stepdown** (ou Metáfora do Jornal): primeiro lemos o título, depois o primeiro parágrafo e, à medida que descemos, encontramos os detalhes.

---

### 8. Separação por Linhas em Branco

```java
public void cadastrarProduto(String nome, double preco) {

    String nomeFormatado = nome.trim().toUpperCase();
    boolean precoValido = preco > 0;

    if (!precoValido) {
        System.out.println("Preço inválido");
        return;
    }

    System.out.println("Produto: " + nomeFormatado);
    System.out.println("Preço: " + preco);
    System.out.println("Cadastro finalizado");
}
```

---

### 9. Quebra de Linha em Parâmetros e Condições Longas

```java
public class Relatorio {
    public void gerarRelatorio(String cliente,
                               String email,
                               double total,
                               boolean premium,
                               boolean pagamentoEmDia) {

        if (premium && pagamentoEmDia && total > 1000) {
            System.out.println("Relatório especial para " + cliente +
                    " enviado para " + email +
                    " com total de " + total);
        }
    }
}
```

---

### 10. Indentação e Estrutura de Condicionais

```java
public void verificarAcesso(boolean ativo, boolean admin) {
    if (ativo) {
        if (admin) {
            System.out.println("Acesso administrativo");
        } else {
            System.out.println("Acesso comum");
        }
    } else {
        System.out.println("Usuário inativo");
    }
}
```

---

### 11. Separação de Responsabilidades em Classes Utilitárias

```java
public class ComunicacaoUtils {
    public void enviarEmail(String email) {
        System.out.println("Enviando e-mail para " + email);
    }

    public void enviarNotificacao(String mensagem) {
        System.out.println(mensagem);
    }
}

public class FormatacaoUtils {
    public String formatarCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public String formatarNome(String nome) {
        return nome.trim().toUpperCase();
    }
}
```

---

### 12. Princípio da Responsabilidade Única (SRP)

**Código refatorado:**

```java
public void finalizarPedido(Cliente cliente, Carrinho carrinho) {
    double total = calcularTotal(carrinho);
    salvarPedido(cliente, carrinho, total);
    notificarCliente(cliente);
}

// 1. Responsável apenas por calcular o total
private double calcularTotal(Carrinho carrinho) {
    double total = 0;
    for (Item item : carrinho.getItens()) {
        total += item.getPreco() * item.getQuantidade();
    }
    return total;
}

// 2. Responsável apenas por montar e salvar a entidade
private void salvarPedido(Cliente cliente, Carrinho carrinho, double total) {
    Pedido pedido = new Pedido(cliente, carrinho.getItens(), total);
    pedidoRepository.salvar(pedido);
}

// 3. Responsável apenas pela comunicação
private void notificarCliente(Cliente cliente) {
    emailService.enviar(cliente.getEmail(), "Pedido confirmado");
}
```

**a) Responsabilidades acumuladas na versão original:**

A função `finalizarPedido` acumulava quatro responsabilidades distintas:

1. **Regra de Negócio (Cálculo):** Iteração sobre os itens do carrinho para calcular o total.
2. **Criação de Objeto:** Instanciação de um novo objeto `Pedido`.
3. **Persistência de Dados:** Acionamento do repositório para salvar no banco de dados.
4. **Comunicação Externa:** Acionamento do serviço de e-mail para notificar o cliente.

**b) Por que isso prejudica a manutenção?**

Essa função viola o **Princípio da Responsabilidade Única (SRP)**, o que gera os seguintes problemas:

- **Dificuldade de Testes:** Para testar apenas o cálculo do total, seria necessário lidar com o banco de dados e o serviço de e-mail (ou criar mocks complexos), pois tudo está acoplado.
- **Alto Acoplamento:** A classe depende de muitas outras (`Cliente`, `Carrinho`, `Item`, `Pedido`, `pedidoRepository`, `emailService`). Qualquer mudança em qualquer dependência pode quebrar a função.
- **Risco de Efeitos Colaterais:** Uma mudança simples no texto do e-mail obrigaria o desenvolvedor a mexer na mesma função que lida com cálculos financeiros, aumentando o risco de introduzir bugs.

---

### 13. Nomes de Funções que Revelam Intenção

**Versão original (problema):**

```java
public boolean verificar(Usuario usuario) {
    return usuario.getIdade() >= 18 && usuario.isAtivo();
}
```

**Problema:** O nome `verificar` é genérico demais e não especifica o que está sendo verificado.

**Versão refatorada:**

```java
public boolean verificarMaiorIdade(Usuario usuario) {
    return usuario.getIdade() >= 18 && usuario.isAtivo();
}
```

**Por que é melhor?**

O nome `verificarMaiorIdade` comunica exatamente a intenção do método: verificar se o usuário tem 18 anos ou mais e está ativo no sistema.

---

# 15. Efeito colateral escondido

```java
public boolean usuarioExiste(String email) {
    Usuario usuario = usuarioRepository.buscarPorEmail(email);
    return usuario != null;
}

public void criarUsuario(String email){
    if(usuario == null){
        usuarioRepository.salvar(new Usuario(email));
    }
}
```

---

# 16. Condicionais Aninhadas

```java
public boolean podeAcessarSistema(Usuario usuario){
    if(usuario == null) {
        return false;
    }
    if(!usuario.isAtivo()){
        return false;
    }
    if(usuario.isBloqueado()){
        return false;
    }
    if(!usuario.temPermissao("SISTEMA")){
        return false;
    }
    
    return true;
}
```
