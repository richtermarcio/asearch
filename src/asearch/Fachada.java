/**
 * 
 */
package asearch;

import java.io.IOException;
import java.util.Collection;

import asearch.recuperador.EntradaDocumentoRecuperado;
import asearch.recuperador.RecuperadorDocumentos;

/**
 * @author maas
 *
 */
public class Fachada {
	public static Collection<EntradaDocumentoRecuperado> consultaQuery(
			String str) throws IOException {
		return OrdenadorDocumentos.ordenarDocumentos(RecuperadorDocumentos
				.recuperarDocumento(PreparadorConsultas
						.calcularPesosConsulta(str)));
	}

	public static Collection<EntradaDocumentoRecuperado> consultaArquivo(
			String filename) throws IOException {
		return OrdenadorDocumentos.ordenarDocumentos(RecuperadorDocumentos
				.recuperarDocumento(PreparadorConsultas
						.calcularPesosArtigo(filename)));
	}
}
