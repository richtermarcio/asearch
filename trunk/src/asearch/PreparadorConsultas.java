package asearch;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

import asearch.base.Artigo;
import asearch.indexador.BaseIndices;
import asearch.indexador.EntradaBaseIndice;
import asearch.indexador.Indexador;
import asearch.indexador.OcorrenciaTermoDocumento;
import asearch.preparador.Preparador;

public class PreparadorConsultas {

		public static Collection<OcorrenciaTermoDocumento> calcularPesosConsulta(String consulta) throws IOException {
			BaseIndices base = Indexador.getBase();
			
			BaseIndices base2 = new BaseIndices();
			Artigo consultaPreparada = Preparador.prepararConsulta(consulta);
			base2.indexar(consultaPreparada);
			base2.atualizarPesos(base);
						
			return toColOTD(base2.getEntradas());
		}

		public static Collection<OcorrenciaTermoDocumento> calcularPesosArtigo(String arquivo) throws IOException {
			BaseIndices base = Indexador.getBase();
			
			BaseIndices base2 = new BaseIndices();
			Artigo consultaPreparada = Preparador.prepararArtigo(arquivo);
			base2.indexar(consultaPreparada);
			base2.atualizarPesos(base);
			
			return toColOTD(base2.getEntradas());
		}

		private static Collection<OcorrenciaTermoDocumento> toColOTD(Collection<EntradaBaseIndice> entradas) {
			Vector<OcorrenciaTermoDocumento> ret = new Vector<OcorrenciaTermoDocumento>();
			for (EntradaBaseIndice entrada : entradas) {
				Collection<OcorrenciaTermoDocumento> oc = entrada.getOcorrencias();
				if (oc.size() == 1) {
					ret.add(oc.iterator().next());					
				} else {
					throw new IllegalArgumentException();
				}
			}
			return ret;
		}

		public static void main(String[] asr) throws Throwable, ClassNotFoundException {
			Indexador.carregarBaseIndices("base.asr");			
			System.out.println("" + calcularPesosConsulta("used terms"));
		}
}
