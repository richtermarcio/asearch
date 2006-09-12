package asearch.gui;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import asearch.Fachada;
import asearch.recuperador.EntradaDocumentoRecuperado;

public class TelaPrincipal extends JFrame {

    // meus atributos
    String palavraChave = "";
    String caminhoArquivo = "";
//    String[] resultado = {"artigo 1", "artigo 2", "artigo 3", "artigo 4",
//                         "artigo 5",
//                         "artigo 6", "artigo 7", "artigo 8", "artigo 9",
//                         "artigo 10", "artigo 11", "artigo 12",
//                         "artigo 13", "artigo 14", "artigo 15", "artigo 16",
//                         "artigo 17", "artigo 18", "artigo 19", "artigo 20"};
//    String arquivoParaAbrir = "";

    JPanel contentPane;
    JLabel statusBar = new JLabel();

    JTextField jTextFieldPalavraChave = new JTextField();
    JTextField jTextFieldSimilaridade = new JTextField();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JButton jButtonPalavraChaveOK = new JButton();
    JPanel jPanel2 = new JPanel();
    JButton jButtonBuscarArquivo = new JButton();
    JButton jButtonSimilaridadeOK = new JButton();
    JLabel jLabel2 = new JLabel();
    JList jListResultados = new JList();
    JPanel jPanel3 = new JPanel();
    JLabel jLabel3 = new JLabel();
    JButton jButtonAbrirArquivo = new JButton();
    JScrollPane jScrollPane = new JScrollPane();

    JFileChooser chooser = new JFileChooser();

    //Construct the frame
    public TelaPrincipal() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception {
        contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(null);
        this.setSize(new Dimension(880, 550));
        this.setTitle("ASearch");
        statusBar.setText(" ");
        jButtonPalavraChaveOK.setAction(null);
        jButtonPalavraChaveOK.addActionListener(new
            TelaPrincipal_jButtonPalavraChaveOK_actionAdapter(this));
        jButtonAbrirArquivo.addActionListener(new
            TelaPrincipal_jButtonAbrirArquivo_actionAdapter(this));

        jButtonBuscarArquivo.addActionListener(new
            TelaPrincipal_jButtonBuscarArquivo_actionAdapter(this));
        jButtonSimilaridadeOK.addActionListener(new
            TelaPrincipal_jButtonSimilaridadeOK_actionAdapter(this));
        contentPane.add(statusBar, BorderLayout.SOUTH);

        jTextFieldPalavraChave.setText("");
        jTextFieldPalavraChave.setBounds(new Rectangle(22, 30, 200, 21));
        this.getContentPane().setLayout(null);
        jTextFieldSimilaridade.setText("");
        jTextFieldSimilaridade.setBounds(new Rectangle(19, 30, 200, 21));
        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        jPanel1.setBounds(new Rectangle(37, 56, 342, 75));
        jPanel1.setLayout(null);
        jLabel1.setText("Busca por palavra-chave");
        jLabel1.setBounds(new Rectangle(147, 36, 127, 15));
        jButtonPalavraChaveOK.setBounds(new Rectangle(238, 30, 73, 21));
        jButtonPalavraChaveOK.setText("OK");
        jPanel2.setBorder(BorderFactory.createEtchedBorder());
        jPanel2.setDebugGraphicsOptions(0);
        jPanel2.setBounds(new Rectangle(403, 56, 450, 75));
        jPanel2.setLayout(null);
        jButtonBuscarArquivo.setBounds(new Rectangle(234, 30, 109, 21));
        jButtonBuscarArquivo.setText("Buscar arquivo");
        jButtonSimilaridadeOK.setBounds(new Rectangle(357, 30, 73, 21));
        jButtonSimilaridadeOK.setText("OK");
        jLabel2.setText("Busca por similaridade");
        jLabel2.setBounds(new Rectangle(546, 36, 120, 15));
        jPanel3.setBorder(BorderFactory.createEtchedBorder());
        jPanel3.setDebugGraphicsOptions(0);
        jPanel3.setBounds(new Rectangle(31, 180, 812, 331));
        jPanel3.setLayout(null);
        jLabel3.setText("Resultado");
        jLabel3.setBounds(new Rectangle(411, 161, 53, 15));

        jListResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPane = new JScrollPane(jListResultados);
        jScrollPane.setBounds(new Rectangle(24, 25, 761, 246));
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jButtonAbrirArquivo.setBounds(new Rectangle(317, 289, 165, 21));
        jButtonAbrirArquivo.setText("Abrir arquivo selecionado");
        contentPane.add(jPanel1, null);
        jPanel1.add(jTextFieldPalavraChave, null);
        jPanel1.add(jButtonPalavraChaveOK, null);
        contentPane.add(jPanel2, null);
        jPanel2.add(jTextFieldSimilaridade, null);
        jPanel2.add(jButtonBuscarArquivo, null);
        jPanel2.add(jButtonSimilaridadeOK, null);
        contentPane.add(jLabel2, null);
        contentPane.add(jPanel3, null);
        jPanel3.add(jButtonAbrirArquivo, null);
        jPanel3.add(jScrollPane, null);
        jScrollPane.getViewport().add(jListResultados, null);
        contentPane.add(jLabel3, null);
        contentPane.add(jLabel1, null);

    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    void jButtonPalavraChaveOK_actionPerformed(ActionEvent e) {
        this.palavraChave = this.jTextFieldPalavraChave.getText();
        if (this.palavraChave.length() <= 2) {
            JOptionPane.showMessageDialog(this,
                "Informe uma palavra-chave com tamanho maior que 2",
                                          "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
        	try {
				Collection<EntradaDocumentoRecuperado> resultado = Fachada.consultaQuery(this.palavraChave);
	            this.jListResultados.setListData(resultado.toArray());
			} catch (IOException e1) {
				e1.printStackTrace();
	            this.jListResultados.setListData(new Object[0]);
			}
        }
    }

    void jButtonAbrirArquivo_actionPerformed(ActionEvent e) {
        if (this.jListResultados.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Selecione um artigo para ser aberto.",
                                          "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            Object selecionado = this.jListResultados.getSelectedValue();
//            this.arquivoParaAbrir = selecionado.toString();
//            JOptionPane.showMessageDialog(this, this.arquivoParaAbrir,
//                                          "Abrir", JOptionPane.PLAIN_MESSAGE);
            
            EntradaDocumentoRecuperado entrada = (EntradaDocumentoRecuperado)selecionado;
            try {
            	System.out.println(entrada.artigo.getFile().getCanonicalPath());
				Runtime.getRuntime().exec("C:\\Program Files\\Adobe\\Acrobat 6.0\\Reader\\AcroRd32.exe " + entrada.artigo.getFile().getCanonicalPath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
    }

    void jButtonBuscarArquivo_actionPerformed(ActionEvent e) {
        int retorno = this.chooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            this.caminhoArquivo = arquivo.getAbsolutePath();
            this.jTextFieldSimilaridade.setText(this.caminhoArquivo);
        }
    }

    void jButtonSimilaridadeOK_actionPerformed(ActionEvent e) {
        this.caminhoArquivo = this.jTextFieldSimilaridade.getText();
        if (this.caminhoArquivo.equals("")) {
            JOptionPane.showMessageDialog(this,
                "Selecione um arquivo para ser comparado.",
                                          "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
        	try {
				Collection<EntradaDocumentoRecuperado> resultado = Fachada.consultaArquivo(this.caminhoArquivo);
	            this.jListResultados.setListData(resultado.toArray());
			} catch (IOException e1) {
				e1.printStackTrace();
	            this.jListResultados.setListData(new Object[0]);
			}
        }
    }
}

class TelaPrincipal_jButtonPalavraChaveOK_actionAdapter implements java.awt.
    event.ActionListener {
    TelaPrincipal adaptee;

    TelaPrincipal_jButtonPalavraChaveOK_actionAdapter(TelaPrincipal adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonPalavraChaveOK_actionPerformed(e);
    }
}

class TelaPrincipal_jButtonAbrirArquivo_actionAdapter implements java.awt.event.
    ActionListener {
    TelaPrincipal adaptee;

    TelaPrincipal_jButtonAbrirArquivo_actionAdapter(TelaPrincipal adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonAbrirArquivo_actionPerformed(e);
    }
}

class TelaPrincipal_jButtonBuscarArquivo_actionAdapter implements java.awt.
    event.ActionListener {
    TelaPrincipal adaptee;

    TelaPrincipal_jButtonBuscarArquivo_actionAdapter(TelaPrincipal adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonBuscarArquivo_actionPerformed(e);
    }
}

class TelaPrincipal_jButtonSimilaridadeOK_actionAdapter implements java.awt.
    event.ActionListener {
    TelaPrincipal adaptee;

    TelaPrincipal_jButtonSimilaridadeOK_actionAdapter(TelaPrincipal adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonSimilaridadeOK_actionPerformed(e);
    }
}
