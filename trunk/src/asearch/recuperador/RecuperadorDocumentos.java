/**
 * 
 */
package asearch.recuperador;

import java.io.IOException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import asearch.PreparadorConsultas;
import asearch.base.Artigo;
import asearch.indexador.BaseIndices;
import asearch.indexador.Indexador;
import asearch.indexador.OcorrenciaTermoDocumento;

/**
 * @author maas
 *
 */
public class RecuperadorDocumentos {
	
	public static Collection<EntradaDocumentoRecuperado> recuperarDocumento(Collection<OcorrenciaTermoDocumento> pesosConsulta) {
		Hashtable<String, Double> pesos = getHashPesos(pesosConsulta);
		
		BaseIndices base = Indexador.getBase();
		Collection<EntradaDocumentoRecuperado> recuperados = new Vector<EntradaDocumentoRecuperado>();
		
//		System.out.println("PESOS CONSULTA:\n " + pesosConsulta + "\n");

		for (Artigo artigo : base.getArtigos()) {
			EntradaDocumentoRecuperado info = new EntradaDocumentoRecuperado();
			info.artigo = artigo;
			
			double up = 0.0;
			double downX = 0.0;
			double downY = 0.0;
			
			for (OcorrenciaTermoDocumento termo : artigo.getPalavras()) {
				double x = termo.getPeso();
				double y = 1.0;
				
				Double d = pesos.get(termo.getTermo().getTermo());
				if (d==null) {
					y = 0.0;
				} else {
					y = d;
				}
				
				up += x*y;
				downX += x*x;
				downY += y*y;
			}
			
//			System.out.println("up= "+up+ " downX: "+downX+" downY: "+downY);
			info.relevancia = up / (Math.sqrt(downX) * Math.sqrt(downY));
			recuperados.add(info);
		} 
		Iterator it = recuperados.iterator();
		
		EntradaDocumentoRecuperado oc = null;
		
		while(it.hasNext()) {
			oc = (EntradaDocumentoRecuperado) it.next();
			if (Double.isNaN(oc.relevancia)) {
				it.remove();
			}
		}
				
		return recuperados;
	}

	private static Hashtable<String, Double> getHashPesos(Collection<OcorrenciaTermoDocumento> pesosConsulta) {
		Hashtable<String, Double> hash = new Hashtable<String, Double>();
		
		for (OcorrenciaTermoDocumento indice : pesosConsulta) {
			hash.put(indice.getTermo().getTermo(), indice.getPeso());
		}
		return hash;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Indexador.carregarBaseIndices("base.asr");	
		Indexador.atualizarPesos();
		System.out.println("" + recuperarDocumento(PreparadorConsultas.calcularPesosConsulta("generated computation probability")));		
	}
}
