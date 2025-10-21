import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * Classe que implementa o formulário de Cadastro de Clientes conforme a atividade.
 */
public class CadastroClientesForm extends JFrame {

    // --- Componentes da Interface ---
    private JTextField nomeField, enderecoField, cidadeField;
    private JComboBox<String> estadoCombo;
    private JFormattedTextField telefoneField;
    private JRadioButton ativoRadio, inativoRadio;
    private ButtonGroup statusGroup;
    private JButton gravarButton, cancelarButton, voltarButton;

    /**
     * Construtor da classe.
     */
    public CadastroClientesForm() {
        // --- Configurações da Janela Principal ---
        setTitle("Cadastrar Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Define o layout principal da janela como BorderLayout
        setLayout(new BorderLayout());

        // --- 1. Painel Norte (Botões Superiores) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // NOTA: Adicione os caminhos para seus ícones aqui
        // Ex: new ImageIcon("caminho/para/icone_gravar.png")
        gravarButton = new JButton("Gravar Dados" /*, seuIconeGravar */);
        cancelarButton = new JButton("Cancelar Cadastro" /*, seuIconeCancelar */);

        topPanel.add(gravarButton);
        topPanel.add(cancelarButton);
        add(topPanel, BorderLayout.NORTH); // Adiciona o painel ao topo da janela

        // --- 2. Painel Central (Formulário) ---
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // GridBagConstraints para controlar o layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fazer campos de texto preencherem o espaço
        gbc.anchor = GridBagConstraints.WEST; // Alinhar labels à esquerda

        // -- Linha 0: Título do Formulário
        JLabel formTitleLabel = new JLabel("Preencha os dados corretamente e clique em Gravar Dados");
        formTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        centerPanel.add(formTitleLabel, gbc);

        // Reseta o gridwidth
        gbc.gridwidth = 1;

        // -- Linha 1: Nome Completo
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(new JLabel("Nome Completo:"), gbc);
        
        nomeField = new JTextField(30); // 30 colunas de largura preferida
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(nomeField, gbc);

        // -- Linha 2: Endereço
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Endereço:"), gbc);
        
        enderecoField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(enderecoField, gbc);

        // -- Linha 3: Cidade
        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(new JLabel("Cidade:"), gbc);
        
        cidadeField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 3;
        centerPanel.add(cidadeField, gbc);

        // -- Linha 4: Estado
        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(new JLabel("Estado:"), gbc);
        
        // Lista de estados (adicionar todos se necessário)
        String[] estados = {
            "Selecione...", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };
        estadoCombo = new JComboBox<>(estados);
        gbc.gridx = 1;
        gbc.gridy = 4;
        centerPanel.add(estadoCombo, gbc);

        // -- Linha 5: Telefone
        gbc.gridx = 0;
        gbc.gridy = 5;
        centerPanel.add(new JLabel("Telefone:"), gbc);
        
        try {
            // Cria uma máscara para o telefone (xx) xxxxx-xxxx
            MaskFormatter mask = new MaskFormatter("(##) #####-####");
            mask.setPlaceholderCharacter('_');
            telefoneField = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
            telefoneField = new JFormattedTextField(); // Fallback
        }
        gbc.gridx = 1;
        gbc.gridy = 5;
        centerPanel.add(telefoneField, gbc);

        // -- Linha 6: Status
        gbc.gridx = 0;
        gbc.gridy = 6;
        centerPanel.add(new JLabel("Status:"), gbc);
        
        ativoRadio = new JRadioButton("Ativo");
        inativoRadio = new JRadioButton("Inativo");
        statusGroup = new ButtonGroup(); // Agrupa os botões
        statusGroup.add(ativoRadio);
        statusGroup.add(inativoRadio);
        
        // Painel para os botões de rádio
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioPanel.add(ativoRadio);
        radioPanel.add(inativoRadio);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        centerPanel.add(radioPanel, gbc);

        // Adiciona o painel central à janela
        add(centerPanel, BorderLayout.CENTER);

        // --- 3. Painel Sul (Botão Voltar) ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        voltarButton = new JButton("Voltar" /*, seuIconeVoltar */);
        bottomPanel.add(voltarButton);
        add(bottomPanel, BorderLayout.SOUTH); // Adiciona o painel ao final da janela

        // --- 4. Adicionar Listeners (Ações dos botões) ---
        initListeners();

        // --- Finalização da Janela ---
        pack(); // Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    /**
     * Inicializa os listeners (ações) dos botões.
     */
    private void initListeners() {
        // Ação do botão "Gravar Dados"
        gravarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama o método de validação
                validarCampos();
            }
        });

        // Ações para "Cancelar" e "Voltar" (ex: fechar a janela)
        ActionListener fecharJanelaListener = e -> System.exit(0);
        cancelarButton.addActionListener(fecharJanelaListener);
        voltarButton.addActionListener(fecharJanelaListener);
    }

    /**
     * Método principal de validação, conforme solicitado na atividade.
     */
    private void validarCampos() {
        StringBuilder erros = new StringBuilder();

        // 1. Validar Nome
        if (nomeField.getText().trim().isEmpty()) {
            erros.append("- Nome Completo\n");
        }
        
        // 2. Validar Endereço
        if (enderecoField.getText().trim().isEmpty()) {
            erros.append("- Endereço\n");
        }
        
        // 3. Validar Cidade
        if (cidadeField.getText().trim().isEmpty()) {
            erros.append("- Cidade\n");
        }
        
        // 4. Validar Estado (índice 0 é "Selecione...")
        if (estadoCombo.getSelectedIndex() == 0) {
            erros.append("- Estado\n");
        }
        
        // 5. Validar Telefone (JFormattedTextField retorna null se a máscara não estiver válida)
        if (telefoneField.getValue() == null) {
            erros.append("- Telefone\n");
        }
        
        // 6. Validar Status (verifica se algum botão foi selecionado no grupo)
        if (statusGroup.getSelection() == null) {
            erros.append("- Status\n");
        }

        // --- Exibe o resultado da validação ---
        if (erros.length() > 0) {
            // Se houver erros, mostra a mensagem de erro
            JOptionPane.showMessageDialog(this,
                    "IMPORTANTE: Todos os campos são obrigatórios.\nPor favor, preencha os seguintes campos:\n\n" + erros.toString(),
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Se estiver tudo OK, mostra mensagem de sucesso
            JOptionPane.showMessageDialog(this,
                    "Dados gravados com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            
            // Aqui você adicionaria a lógica para realmente salvar os dados
            // (ex: salvar em um banco de dados, arquivo, etc.)
        }
    }

    /**
     * Método Main para executar o programa.
     */
    public static void main(String[] args) {
        // Garante que a GUI seja criada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroClientesForm().setVisible(true);
            }
        });
    }
}