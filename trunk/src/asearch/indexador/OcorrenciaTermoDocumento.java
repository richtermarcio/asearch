/**
 * 
 */
package asearch.indexador;

import java.io.Serializable;

import asearch.base.Artigo;

/**
 * @author maas
 *
 */
public class OcorrenciaTermoDocumento implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891630907769956982L;
	private EntradaBaseIndice termo;
	private Artigo 			  artigo;
	private int    			  frequenciaDaPalavraNoDocumento;
	
	private double 			  peso;
	private double			  tf;

	public EntradaBaseIndice getTermo() {
		return termo;
	}

	public void atualizarPeso() {
		tf  = (frequenciaDaPalavraNoDocumento * 1.0)/ artigo.getFrequenciaTermoMaisFrequente();
		peso = tf * termo.getIDF();
	}

	public void atualizarPeso(BaseIndices base) {
		tf  = 0.5 + (0.5 + frequenciaDaPalavraNoDocumento)/ artigo.getFrequenciaTermoMaisFrequente();
		peso = tf * base.getPalavra(termo.getTermo()).getIDF();	
	}

	public void setTermo(EntradaBaseIndice termo) {
		this.termo = termo;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public int getFrequencia() {
		return frequenciaDaPalavraNoDocumento;
	}

	public void incFrequencia() {
		this.frequenciaDaPalavraNoDocumento++;
	}
	
	public String toString() {
		return "(" + termo.getTermo() + ";" + artigo.getNomeArquivo() + ";freq=" + frequenciaDaPalavraNoDocumento + ";peso=" + peso + ")";
	}

}
