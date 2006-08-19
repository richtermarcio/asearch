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
	private Collection<EntradaBaseIndice> entradas = new HashSet<EntradaBaseIndice>();
	private Hashtable hashEntradas = new Hashtable();
	
	public void indexar(Artigo artigo) {
		for (String palavra : artigo.getConteudoPreparado()) {
			System.out.println("indexando palavra " + palavra);
			EntradaBaseIndice entrada = getPalavra(palavra);
			entrada.setTermo(palavra);
			entrada.adcionarOcorrência(artigo);
			entradas.add(entrada);
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
}
