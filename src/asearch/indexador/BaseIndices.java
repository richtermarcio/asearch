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
 * @author aurelio
 *
 */
public class BaseIndices implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5167408274116070341L;
	private Collection<EntradaBaseIndice> entradas = new HashSet<EntradaBaseIndice>();
	private Hashtable hashEntradas = new Hashtable();
	private Collection<Artigo> artigos = new HashSet<Artigo>();
	
	public static boolean LOG = false;
	
	public static void log(String str) {
		if (LOG) {
			System.out.println(str);
		}
	}
	
	public void indexar(Artigo artigo) {
		if (!artigos.contains(artigo)) {
			int maxFreq = 0;
			for (String palavra : artigo.getConteudoPreparado()) {
				log("indexando palavra " + palavra);
				EntradaBaseIndice entrada = getPalavra(palavra);
				entrada.setTermo(palavra);
				OcorrenciaTermoDocumento ocorrencia = entrada
						.adcionarOcorrência(artigo);
				entradas.add(entrada);

				artigo.addPalavra(ocorrencia);

				int freq = ocorrencia.getFrequencia();
				if (freq > maxFreq) {
					maxFreq = freq;
				}
			}

			artigo.setFrequenciaTermoMaisFrequente(maxFreq);
			artigos.add(artigo);
		}
	} 
	
	//du bist schön  
	private EntradaBaseIndice getPalavra(String palavra) {
		EntradaBaseIndice entrada = (EntradaBaseIndice) hashEntradas.get(palavra); 
		if (entrada == null) {
			entrada = new EntradaBaseIndice();
			hashEntradas.put(palavra, entrada);
		}
		return entrada;
	}
	
	public String toString() {
		String ret =  "BaseIndices@@"+hashCode()+":\n"+
		   "entradas:\n";
		for (EntradaBaseIndice entrada : entradas) {
			ret += entrada + "\n";
		}
		ret += "\n";
		return ret;
	}

	public Collection<Artigo> getArtigos() {
		return artigos;
	}

	public void atualizarPesos() {
		for (EntradaBaseIndice entrada : entradas) {
			for (OcorrenciaTermoDocumento ocorrencia : entrada.getOcorrencias()) {
				ocorrencia.atualizarPeso();
			}
		}
	}

	public Collection<EntradaBaseIndice> getEntradas() {
		return entradas;
	}
}
