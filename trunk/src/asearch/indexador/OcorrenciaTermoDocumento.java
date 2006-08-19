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
	private EntradaBaseIndice termo;
	private Artigo 			  artigo;
	private int    			  frequenciaDaPalavraNoDocumento;
	
	private double 			  peso;

	public EntradaBaseIndice getTermo() {
		return termo;
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
