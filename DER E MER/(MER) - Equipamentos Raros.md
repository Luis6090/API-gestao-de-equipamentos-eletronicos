# Diagrama Entidade-Relacionamento (DER) - Equipamentos Raros

Este documento compila as informações sobre o Diagrama Entidade-Relacionamento (DER) para o sistema de controle de inventário "Equipamentos Raros", incluindo o mapeamento inicial de entidades e uma explicação sobre o padrão Peter Chen com um exemplo textual.

## 1. Mapeamento Inicial de Entidades

Com base na descrição do projeto "Equipamentos Raros - Controle de inventário da Raro", as principais entidades que podem ser mapeadas para um sistema de controle de inventário são:

**Entidades Principais:**

*   **Usuário:** Representa os usuários do sistema que terão acesso à plataforma.
    *   Atributos: `email`, `senha`, `status_confirmacao_email`.
*   **Colaborador:** Representa os funcionários da Raro que recebem e devolvem equipamentos.
    *   Atributos: `nome`, `dados_pessoais`, `endereco` (com integração ViaCEP), `data_inicio_contrato`, `data_rescisao_contrato`, `especificacoes_equipamentos_necessarios`.
*   **Equipamento:** Representa os itens físicos que fazem parte do inventário.
    *   Atributos: `especificacoes`, `numero_serie`, `marca`, `modelo`, `data_aquisicao`, `tempo_uso`, `tipo_equipamento`.
*   **Empréstimo/Devolução (Equipamento_Colaborador):** Representa a relação entre um equipamento e um colaborador, registrando quando um equipamento foi entregue e devolvido.
    *   Atributos: `id_equipamento`, `id_colaborador`, `data_entrega`, `data_devolucao`.
*   **Parâmetros do Sistema:** Entidade para armazenar configurações e parâmetros que norteiam as decisões de compra e alertas.
    *   Atributos: `tempo_medio_reposicao`, `tempo_medio_consumo_estoque`, `tempo_medio_envio`, `taxa_media_equipamentos_defeituosos`, `estoque_minimo_seguranca` (por tipo de equipamento).
*   **Alerta:** Representa os alertas disparados pelo sistema.
    *   Atributos: `id_alerta`, `tipo_alerta`, `data_hora_geracao`, `status`, `descricao`.

**Entidades Acessórias/Relacionamentos:**

*   **Tipo_Equipamento:** Uma entidade para categorizar os equipamentos (ex: Notebook, Monitor, Celular, Mouse, Teclado). Isso pode ser um atributo na entidade `Equipamento` ou uma entidade separada com um relacionamento.
*   **Endereço:** Embora o `Colaborador` tenha um endereço, a integração com o ViaCEP sugere que os dados de endereço podem ser armazenados de forma estruturada, talvez como um objeto embutido ou uma entidade separada relacionada ao `Colaborador`.

**Relacionamentos:**

*   Um `Usuário` pode estar relacionado a um `Colaborador` (se o usuário do sistema for também um colaborador).
*   Um `Colaborador` pode ter vários `Empréstimos/Devoluções`.
*   Um `Equipamento` pode estar envolvido em vários `Empréstimos/Devoluções`.
*   `Parâmetros do Sistema` podem influenciar a gestão de `Equipamentos` e a geração de `Alertas`.
*   Um `Equipamento` pode gerar vários `Alertas`.
*   `Alertas` são baseados em `Parâmetros do Sistema`.


#### Entidades:

---

**1. Entidade: USUÁRIO**

*   **Atributos:**
    *   `email` (Identificador Único - Chave Primária) 
    *   `senha`
    *   `status_confirmacao_email`

---

**2. Entidade: COLABORADOR**

*   **Atributos:**
    *   `id_colaborador` (Identificador Único - Chave Primária)
    *   `nome`
    *   `dados_pessoais`
    *   `endereco`
    *   `data_inicio_contrato`
    *   `data_rescisao_contrato`
    *   `especificacoes_equipamentos_necessarios`

---

**3. Entidade: EQUIPAMENTO**

*   **Atributos:**
    *   `id_equipamento` (Identificador Único - Chave Primária)
    *   `especificacoes`
    *   `numero_serie`
    *   `marca`
    *   `modelo`
    *   `data_aquisicao`
    *   `tempo_uso`

---

**4. Entidade: TIPO_EQUIPAMENTO**

*   **Atributos:**
    *   `id_tipo_equipamento` (Identificador Único - Chave Primária)
    *   `nome_tipo`

---

**5. Entidade: EMPRÉSTIMO_DEVOLUÇÃO**

*   **Atributos:**
    *   `id_emprestimo_devolucao` (Identificador Único - Chave Primária)
    *   `data_entrega`
    *   `data_devolucao`

---

**6. Entidade: PARÂMETROS_SISTEMA**

*   **Atributos:**
    *   `id_parametro` (Identificador Único - Chave Primária)
    *   `tempo_medio_reposicao`
    *   `tempo_medio_consumo_estoque`
    *   `tempo_medio_envio`
    *   `taxa_media_equipamentos_defeituosos`
    *   `estoque_minimo_seguranca`

---

**7. Entidade: ALERTA**

*   **Atributos:**
    *   `id_alerta` (Identificador Único - Chave Primária)
    *   `tipo_alerta` (ex: \'Estoque Baixo\', \'Equipamento Defeituoso\', \'Devolução Atrasada\')
    *   `data_hora_geracao`
    *   `status` (ex: \'Ativo\', \'Resolvido\', \'Ignorado\')
    *   `descricao`

---
**8. Entidade: ESTOQUE**

*   **Atributos:**
    *   `id_estoque` (Identificador Único - Chave Primária)
    *   `quantidade_disponivel` 
    *   `quantidade_em_uso`
    *   `quantidade_defeituosa`
  

---

#### Relacionamentos (com Cardinalidades no padrão Peter Chen - (min, max)):

1.  **USUÁRIO -- (pode ser) -- COLABORADOR**
    *   **Descrição:** Um `USUÁRIO` *pode ser* um `COLABORADOR`. Se um usuário do sistema for também um colaborador da Raro, há uma relação direta.
    *   **Notação Peter Chen:**
        *   `USUÁRIO` -- (1,1) -- `É_UM` -- (0,1) -- `COLABORADOR` (ou um relacionamento mais direto se for 1:1)

2.  **COLABORADOR -- (realiza) -- EMPRÉSTIMO_DEVOLUÇÃO**
    *   **Descrição:** Um `COLABORADOR` *realiza* um ou muitos `EMPRÉSTIMO_DEVOLUÇÃO`. Cada empréstimo/devolução é realizado por exatamente um colaborador.
    *   **Notação Peter Chen:**
        *   `COLABORADOR` -- (1,N) -- `REALIZA` -- (1,1) -- `EMPRÉSTIMO_DEVOLUÇÃO`

3.  **EQUIPAMENTO -- (envolvido_em) -- EMPRÉSTIMO_DEVOLUÇÃO**
    *   **Descrição:** Um `EQUIPAMENTO` *está envolvido em* um ou muitos `EMPRÉSTIMO_DEVOLUÇÃO`. Cada empréstimo/devolução envolve exatamente um equipamento.
    *   **Notação Peter Chen:**
        *   `EQUIPAMENTO` -- (1,N) -- `ENVOLVE` -- (1,1) -- `EMPRÉSTIMO_DEVOLUÇÃO`

4.  **TIPO_EQUIPAMENTO -- (categoriza) -- EQUIPAMENTO**
    *   **Descrição:** Um `TIPO_EQUIPAMENTO` *categoriza* um ou muitos `EQUIPAMENTO`. Cada equipamento pertence a exatamente um tipo.
    *   **Notação Peter Chen:**
        *   `TIPO_EQUIPAMENTO` -- (1,N) -- `CATEGORIZA` -- (1,1) -- `EQUIPAMENTO`

5.  **PARÂMETROS_SISTEMA -- (influencia_gestão) -- EQUIPAMENTO**
    *   **Descrição:** `PARÂMETROS_SISTEMA` *influencia a gestão de* zero ou muitos `EQUIPAMENTO`. Esta é uma relação mais conceitual, onde os parâmetros definem regras gerais que afetam a gestão de todos os equipamentos.
    *   **Notação Peter Chen:**
        *   `PARÂMETROS_SISTEMA` -- (1,1) -- `DEFINE_REGRAS_PARA` -- (0,N) -- `EQUIPAMENTO`

6.  **ALERTA -- (referencia) -- EQUIPAMENTO**
    *   **Descrição:** Um `ALERTA` *refere-se a* um `EQUIPAMENTO` específico. Um `EQUIPAMENTO` pode gerar muitos `ALERTA`s ao longo do tempo.
    *   **Notação Peter Chen:**
        *   `ALERTA` -- (1,1) -- `REFERENCIA` -- (0,N) -- `EQUIPAMENTO`

7.  **ALERTA -- (baseado_em) -- PARÂMETROS_SISTEMA**
    *   **Descrição:** Um `ALERTA` *é baseado em* `PARÂMETROS_SISTEMA`. Muitos alertas podem ser gerados a partir do mesmo conjunto de parâmetros.
    *   **Notação Peter Chen:**
        *   `ALERTA` -- (0,N) -- `BASEADO_EM` -- (1,1) -- `PARÂMETROS_SISTEMA`
        
8.  **EQUIPAMENTO -- (possui) -- ESTOQUE**
    *   **Descrição:** Um `Equipamento` pode estar vinculado a um ou mais registros de `Estoque` (por tipo, localização, ou status).Um `Estoque` se refere sempre a um único `Equipamento`.
    *   **Notação Peter Chen:**
        *   `EQUIPAMENTO` (1,1) — `possui` — (1,1) `ESTOQUE`
        
9.  **PARÂMETROS_SISTEMA — (define_niveis) — ESTOQUE**
    *   **Descrição:** Os parâmetros do sistema definem políticas e limites como o estoque mínimo de segurança, que se aplicam à gestão dos estoques.Cada registro de estoque pode ser influenciado por apenas um conjunto de parâmetros.
    *   **Notação Peter Chen:**
        *   `PARÂMETROS_SISTEMA` (1,1) — `define_niveis` — (0,N) `ESTOQUE`
                        

---
