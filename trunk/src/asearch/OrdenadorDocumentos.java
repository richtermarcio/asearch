package asearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import asearch.indexador.Indexador;
import asearch.recuperador.EntradaDocumentoRecuperado;
import asearch.recuperador.RecuperadorDocumentos;

public class OrdenadorDocumentos {

	public static Collection<EntradaDocumentoRecuperado> ordenarDocumentos(Collection<EntradaDocumentoRecuperado> documentos) {
		EntradaDocumentoRecuperado[] documentosArr = documentos.toArray(new EntradaDocumentoRecuperado[0]);
		Arrays.sort(documentosArr, new ComparaDocumentos());

		return Arrays.asList(documentosArr);
	}
	
	private static class ComparaDocumentos implements Comparator<EntradaDocumentoRecuperado> {
		public int compare(EntradaDocumentoRecuperado arg0, EntradaDocumentoRecuperado arg1) {
			return (int)Math.signum((arg1.relevancia - arg0.relevancia));
		}
	}

	public static void main(String[] args) throws Exception {
		Indexador.carregarBaseIndices("base.asr");	
		Indexador.atualizarPesos();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		while (true) {
			System.out.print("consulta: ");
			String consulta = br.readLine();

			long before = System.currentTimeMillis();
			printResultado(OrdenadorDocumentos
					.ordenarDocumentos(RecuperadorDocumentos
							.recuperarDocumento(PreparadorConsultas
									.calcularPesosConsulta(consulta))));

			System.out.println("Response time: "
					+ (System.currentTimeMillis() - before) / 1000.0 + "s");
		}
		
	}

	private static void printResultado(Collection<EntradaDocumentoRecuperado> arquivos) {
		for (EntradaDocumentoRecuperado recuperado : arquivos) {
			System.out.println("FILE: " + recuperado.artigo.getNomeArquivo() + "\n" +
							   "RELEVANCIA: " + recuperado.relevancia + "\n\n");
		}
	}
}
