/**
 * 
 */
package asearch.recuperador;

import asearch.base.Artigo;

/**
 * @author maas
 *
 */
public class EntradaDocumentoRecuperado {
	public Artigo artigo;
	public double relevancia;
	
	public String toString() {
		return relevancia + " ## " + artigo.getTitulo() + " " + artigo.getAutor()+ " ==> " + artigo.getNomeArquivo(); //;
	}
}
