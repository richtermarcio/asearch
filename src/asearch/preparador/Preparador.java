package asearch.preparador;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.pdfbox.exceptions.CryptographyException;
import org.pdfbox.exceptions.InvalidPasswordException;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.pdmodel.PDDocumentInformation;
import org.pdfbox.util.PDFTextStripper;

import asearch.base.Artigo;

public class Preparador {

	private static Constantes constantes = Constantes.getInstancia();

	public static Artigo prepararArtigo(String caminhoPDF) throws IOException {
		File arquivoPDF = new File(caminhoPDF);
		Artigo artigo = lerArtigo(arquivoPDF);
		eliminarStopWords(artigo);
		return artigo;
	}

	// elimina todas as stop words do conteúdo de um Artigo e
	// coloca o conteúdo filtrado na propriedade conteudoPreparado
	// do mesmo Artigo.

	private static void eliminarStopWords(Artigo artigo) {

		String[] arrayPalavrasPDF = artigo.getConteudo().split(
				artigo.getSeparadorPalavras());
		Collection<String> arrayListPalavrasPDF = new CopyOnWriteArrayList<String>(
				Arrays.asList(arrayPalavrasPDF));
		Collection<String> stopWords = constantes.getStopWords();

		for (String palavraPDF : arrayListPalavrasPDF) {

			// eliminação de pontuação que ficam coladas com palavras.
			arrayListPalavrasPDF.remove(palavraPDF);
			palavraPDF = palavraPDF.replaceAll("\\W", " ");
			String[] palavraPDFSplited = palavraPDF.split("\\s+");

			// verifica se é número ou palavra menor que 2.
			for (int i = 0; i < palavraPDFSplited.length; i++) {

				boolean ehStopWord = false;
				boolean ehMenorQue2 = false;
				boolean ehVazio = false;

				palavraPDFSplited[i] = palavraPDFSplited[i].replaceAll("\\d+", "");

				if (palavraPDFSplited[i].equals("")) {
					ehVazio = true;
				}

				if (palavraPDFSplited[i].length() <= 2) {
					ehMenorQue2 = true;
				}
				
				// verifica se é stop word.
				for (String stopWord : stopWords) {
					if (palavraPDFSplited[i].equalsIgnoreCase(stopWord)) {
						ehStopWord = true;
						break;
					}
				}

				if (!ehStopWord && !ehMenorQue2 && !ehVazio) {
					arrayListPalavrasPDF.add(palavraPDFSplited[i]);
				}
			}

		}

		artigo.setConteudoPreparado(arrayListPalavrasPDF);

	}

	// lê o conteudo do pdf e seus metadados.
	// possíveis melhorias: é possível também ler os bookmarks,
	// as palavras com highlighting e fontes utilizadas, essas
	// propriedades podem ser importantes na busca.

	private static Artigo lerArtigo(File arquivo) throws IOException {
		PDDocument pdf = null;
		Artigo artigo = new Artigo();
		try {

			artigo.setNomeArquivo(arquivo.getName());
			pdf = PDDocument.load(arquivo);

			// tenta decriptar com senha vazia.
			if (pdf.isEncrypted()) {
				pdf.decrypt("");
			}

			PDFTextStripper stripper = new PDFTextStripper();
			// passa todo o conteúdo pra lower case
			artigo.setConteudo(stripper.getText(pdf).toLowerCase());
			artigo.setSeparadorPalavras(stripper.getWordSeparator());

			PDDocumentInformation info = pdf.getDocumentInformation();
			artigo.setAssunto(info.getSubject());
			artigo.setAutor(info.getAuthor());

			// TODO verificar se esses toString em datas vão formatá-las.
			try { // se a data é inválida ele joga exceção :S:S:S
				if (info.getCreationDate() != null) {
					artigo.setDataCriacao(info.getCreationDate().toString());
				}
			} catch (Exception e) {
				
			}
			
			try { // se a data é inválida ele joga exceção :S:S:S
				if (info.getModificationDate() != null) {
					artigo
					.setDataModificacao(info.getModificationDate()
							.toString());
				}
			} catch (Exception e) {

			}
			artigo.setPalavrasChaves(info.getKeywords());
			artigo.setProdutor(info.getProducer());
			artigo.setTrapped(info.getTrapped());
			artigo.setTitulo(info.getTitle());

		} catch (CryptographyException e) {
			throw new IOException(" Erro de criptografia do artigo.");

		} catch (InvalidPasswordException e) {
			throw new IOException(" O artigo necessita de senha.");
		} finally {
			if (pdf != null) {
				pdf.close();
			}
		}
		return artigo;

	}

	public static void main(String[] args) {
		try {
			Artigo artigo = Preparador
					.prepararArtigo("C://Temp//bf//asearch//minweb//pdfs//teste.pdf");
			System.out.println("" + artigo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
