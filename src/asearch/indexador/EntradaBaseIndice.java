/**
 * 
 */
package asearch.indexador;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

import asearch.base.Artigo;

/**
 * @author maas
 *
 */
public class EntradaBaseIndice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6034529797928854302L;
	private String termo;
	private Collection<OcorrenciaTermoDocumento> ocorrencias = new HashSet<OcorrenciaTermoDocumento>();
	private Hashtable hashOcorrencias = new Hashtable();
	
	private int ocorrenciasNaBase;
	
	public OcorrenciaTermoDocumento adcionarOcorrência(Artigo artigo) {
		OcorrenciaTermoDocumento ocorrencia = getOcorrencia(artigo);
		ocorrencia.setTermo(this);
		ocorrencia.setArtigo(artigo);
		ocorrencia.incFrequencia();
		ocorrencias.add(ocorrencia);
		++ocorrenciasNaBase;
		return ocorrencia;
	}
	
	public OcorrenciaTermoDocumento getOcorrencia(Artigo artigo) {
		OcorrenciaTermoDocumento entrada = (OcorrenciaTermoDocumento) hashOcorrencias.get(artigo); 
		if (entrada == null) {
			entrada = new OcorrenciaTermoDocumento();
			hashOcorrencias.put(artigo, entrada);
		}
		return entrada;
	}
	
	public String getTermo() {
		return termo;
	}
	public void setTermo(String termo) {
		this.termo = termo;
	}
	public Collection<OcorrenciaTermoDocumento> getOcorrencias() {
		return ocorrencias;
	}
	public void setOcorrencias(Collection<OcorrenciaTermoDocumento> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	public int getOcorrenciasNaBase() {
		return ocorrencias.size();
	}

	public double getIDF() {
		return Math.log((double)Indexador.getBase().getArtigos().size() / getOcorrenciasNaBase());	
	}
	
	public String toString() {
		String ret = "\ttermo: " + termo+"\n\t ocorrencias na base: "+ocorrenciasNaBase;
		return ret + ocorrencias;
	}

}
