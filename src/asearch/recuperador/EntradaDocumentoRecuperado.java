/**
 * 
 */
package asearch.recuperador;

import java.io.IOException;

import asearch.base.Artigo;

/**
 * @author maas
 *
 */
public class EntradaDocumentoRecuperado {
	public Artigo artigo;
	public double relevancia;
	
	public String toString() {
		try {
			return artigo.getFile().getCanonicalPath() + " [" + relevancia +"]";
		} catch (IOException e) {
			return "IOE";
		}
	}
}
