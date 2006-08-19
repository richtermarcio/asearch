package asearch.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import asearch.indexador.OcorrenciaTermoDocumento;

public class Artigo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4359259001771750119L;
	private int frequenciaTermoMaisFrequente;
	private String nomeArquivo;
	private String conteudo;
	private String dataCriacao;
	private String autor;
	private String palavrasChaves;
	private String dataModificacao;
	private String produtor;
	private String assunto;
	private String trapped;
	private String separadorPalavras;
	private String titulo;
	private Collection<String> conteudoPreparado;
	private Collection<OcorrenciaTermoDocumento> palavras = new HashSet<OcorrenciaTermoDocumento>();
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(String dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getPalavrasChaves() {
		return palavrasChaves;
	}
	public void setPalavrasChaves(String palavrasChaves) {
		this.palavrasChaves = palavrasChaves;
	}
	public String getProdutor() {
		return produtor;
	}
	public void setProdutor(String produtor) {
		this.produtor = produtor;
	}
	public String getTrapped() {
		return trapped;
	}
	public void setTrapped(String trapped) {
		this.trapped = trapped;
	}
	public String getSeparadorPalavras() {
		return separadorPalavras;
	}
	public void setSeparadorPalavras(String separadorPalavras) {
		this.separadorPalavras = separadorPalavras;
	}
	public void setTitulo(String title) {
		this.titulo = title;
	}
	public String getTitulo() {
		return titulo;
	}
	public Collection<String> getConteudoPreparado() {
		return conteudoPreparado;
	}
	public void setConteudoPreparado(Collection<String> conteudoPreparado) {
		this.conteudoPreparado = conteudoPreparado;
	}
	
	public String toString() {
		return "Artigo@"+ hashCode() + ":: " +		
			   "\nnomeArquivo: " + nomeArquivo +
			   "\nconteudo: " + conteudo +
			   "\ndataCriacao: " + dataCriacao +
			   "\nautor: " + autor +
			   "\npalavrasChaves: " + palavrasChaves +
			   "\ndataModificacao: " + dataModificacao +
			   "\nprodutor: " + produtor +
			   "\nassunto: " + assunto +			   
			   "\ntrapped: " + trapped +
			   "\nseparadorPalavras: " + separadorPalavras +
			   "\ntitulo: " + titulo + 
			   "\nfrequenciaTermoMaisFrequente: " + frequenciaTermoMaisFrequente + 
			   "\npalavras: " + palavras + "\n";		
		
	}
	public int getFrequenciaTermoMaisFrequente() {
		return frequenciaTermoMaisFrequente;
	}
	public void setFrequenciaTermoMaisFrequente(int frequenciaTermoMaisFrequente) {
		this.frequenciaTermoMaisFrequente = frequenciaTermoMaisFrequente;
	}
	public Collection<OcorrenciaTermoDocumento> getPalavras() {
		return palavras;
	}
	public void addPalavra(OcorrenciaTermoDocumento ocorrencia) {
		if (!palavras.contains(ocorrencia)) {
			palavras.add(ocorrencia);
		}
	}

}
