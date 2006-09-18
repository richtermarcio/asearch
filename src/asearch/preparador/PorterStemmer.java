package asearch.preparador;

public class PorterStemmer {
	
	public static String eliminaPlural(String palavra){

		if(palavra.endsWith("sses")){
			palavra = palavra.substring(0, palavra.length() - 4) + "ss";
			return palavra;
		} else if(palavra.endsWith("ies")){
			palavra = palavra.substring(0, palavra.length() - 3) + "i";
			return palavra;
		} else if(palavra.endsWith("ss")){
			return palavra;
		} else if(palavra.endsWith("s")){
			palavra = palavra.substring(0, palavra.length() - 1);
		}
		
		return palavra;
	}
	
	public static boolean ehVogal(String palavra, char letra, int posicao){
		if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u'){
			return true;
		} else if(letra == 'y' && posicao != 0 && ehConsoante(palavra, palavra.charAt(posicao - 1), posicao - 1)){
			return true;
		}
		return false;
	}
	
	public static boolean ehConsoante(String palavra, char letra, int posicao) {
		return !ehVogal(palavra, letra, posicao);
	}
	
	public static int medida(String palavra) {
		char[] letras = palavra.toCharArray();
		int medida = 0;
		boolean ultimoFoiVogal = false;

		for (int i = 0; i < letras.length; i++) {
			if (ehVogal(palavra, letras[i], i)) {
				ultimoFoiVogal = true;
			} else if (ehConsoante(palavra, letras[i], i)) {
				if (ultimoFoiVogal) {
					medida++;
				}
				ultimoFoiVogal = false;
			}
		}
		return medida;
	}
	
	public static String eliminaPassadoOuGerundio(String palavra){
		
		if(palavra.endsWith("eed")){
			if(medida(palavra.substring(0, palavra.length() - 3)) > 0){
				palavra = palavra.substring(0, palavra.length() - 3) + "ee";
			}
			return palavra;
		} else if(palavra.endsWith("ed") && temVogal(palavra.substring(0, palavra.length() - 2))){
			palavra = palavra.substring(0, palavra.length() - 2);
			palavra = completaPassadoOuGerundio(palavra);
			return palavra;
		} else if(palavra.endsWith("ing") && temVogal(palavra.substring(0, palavra.length() - 3))){
			palavra = palavra.substring(0, palavra.length() - 3);
			palavra = completaPassadoOuGerundio(palavra);
			return palavra;
		}
		
		return palavra;
	}
	
	public static String eliminaYFim(String palavra){
		if(palavra.endsWith("y") && temVogal(palavra.substring(0, palavra.length() - 1))){
			palavra = palavra.substring(0, palavra.length() - 1) + "i";
		}
		return palavra;
	}
	
	public static String eliminaSufixos1(String palavra){
		if(palavra.length() >= 3){
			switch (palavra.charAt(palavra.length() - 2)) {
			case 'a':
				if(palavra.endsWith("ational") && medida(palavra.substring(0, palavra.length() - 7)) > 0){
					palavra = palavra.substring(0, palavra.length() - 7) + "ate";
					break;
				} else if(palavra.endsWith("tional") && medida(palavra.substring(0, palavra.length() - 6)) > 0){
					palavra = palavra.substring(0, palavra.length() - 6) + "tion";
					break;
				}
				break;
			case 'c':
				if(palavra.endsWith("enci") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "ence";
					break;
				} else if(palavra.endsWith("anci") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "ance";
					break;
				}
				break;
			case 'e':
				if(palavra.endsWith("izer") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "ize";
					break;
				}
				break;
			case 'l':
				if(palavra.endsWith("abli") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "able";
					break;
				} else if(palavra.endsWith("alli") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "al";
					break;
				} else if(palavra.endsWith("entli") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "ent";
					break;
				} else if(palavra.endsWith("eli") && medida(palavra.substring(0, palavra.length() - 3)) > 0){
					palavra = palavra.substring(0, palavra.length() - 3) + "e";
					break;
				} else if(palavra.endsWith("ousli") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "ous";
					break;
				}
				break;
			case 'o':
				if(palavra.endsWith("ization") && medida(palavra.substring(0, palavra.length() - 7)) > 0){
					palavra = palavra.substring(0, palavra.length() - 7) + "ize";
					break;
				} else if(palavra.endsWith("ation") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "ate";
					break;
				} else if(palavra.endsWith("ator") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
					palavra = palavra.substring(0, palavra.length() - 4) + "ate";
					break;
				}
				break;
			case 's':
				if(palavra.endsWith("alism") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "al";
					break;
				} else if(palavra.endsWith("iveness") && medida(palavra.substring(0, palavra.length() - 7)) > 0){
					palavra = palavra.substring(0, palavra.length() - 7) + "ive";
					break;
				} else if(palavra.endsWith("fulness") && medida(palavra.substring(0, palavra.length() - 7)) > 0){
					palavra = palavra.substring(0, palavra.length() - 7) + "ful";
					break;
				} else if(palavra.endsWith("ousness") && medida(palavra.substring(0, palavra.length() - 7)) > 0){
					palavra = palavra.substring(0, palavra.length() - 7) + "ous";
					break;
				}
				break;
			case 't':
				if(palavra.endsWith("aliti") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "al";
					break;
				} else if(palavra.endsWith("iviti") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "ive";
					break;
				} else if(palavra.endsWith("biliti") && medida(palavra.substring(0, palavra.length() - 6)) > 0){
					palavra = palavra.substring(0, palavra.length() - 6) + "ble";
					break;
				}
				break;
			default:
				break;
			}
		}
		
		return palavra;
		
	}
	
	public static String eliminaSufixos3(String palavra){
		
		if(palavra.length() >= 2){
			switch (palavra.charAt(palavra.length() - 2)) {
			case 'a':
				if(palavra.endsWith("al") && medida(palavra.substring(0, palavra.length() - 2)) > 1){
					palavra = palavra.substring(0, palavra.length() - 2);
					break;
				}
				
			case 'c':
				if(palavra.endsWith("ance") && medida(palavra.substring(0, palavra.length() - 4)) > 1){
					palavra = palavra.substring(0, palavra.length() - 4);
					break;
				} else if(palavra.endsWith("ence") && medida(palavra.substring(0, palavra.length() - 4)) > 1){
					palavra = palavra.substring(0, palavra.length() - 4);
					break;
				}
				break;
			case 'e':
				if(palavra.endsWith("er") && medida(palavra.substring(0, palavra.length() - 2)) > 1){
					palavra = palavra.substring(0, palavra.length() - 2);
					break;
				}
				break;
			case 'i':
				if(palavra.endsWith("ic") && medida(palavra.substring(0, palavra.length() - 2)) > 1){
					palavra = palavra.substring(0, palavra.length() - 2);
					break;
				} 
				break;
			case 'l':
				if(palavra.endsWith("able") && medida(palavra.substring(0, palavra.length() - 4)) > 1){
					palavra = palavra.substring(0, palavra.length() - 4);
					break;
				} else if(palavra.endsWith("ible") && medida(palavra.substring(0, palavra.length() - 4)) > 1){
					palavra = palavra.substring(0, palavra.length() - 4);
					break;
				} 
				break;
			case 'n':
				if(palavra.endsWith("ant") && medida(palavra.substring(0, palavra.length() - 3)) > 1){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				} else if(palavra.endsWith("ement") && medida(palavra.substring(0, palavra.length() - 5)) > 1){
					palavra = palavra.substring(0, palavra.length() - 5);
					break;
				} else if(palavra.endsWith("ment") && medida(palavra.substring(0, palavra.length() - 4)) > 1){
					palavra = palavra.substring(0, palavra.length() - 4);
					break;
				} else if(palavra.endsWith("ent") && medida(palavra.substring(0, palavra.length() - 3)) > 1){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			case 'o':
				if(palavra.endsWith("ion") && medida(palavra.substring(0, palavra.length() - 3)) > 1 && (palavra.charAt(palavra.length() - 1) != 's' || palavra.charAt(palavra.length() - 1) != 't')){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				} else if(palavra.endsWith("ou") && medida(palavra.substring(0, palavra.length() - 2)) > 1){
					palavra = palavra.substring(0, palavra.length() - 2);
					break;
				}
				break;
			case 's':
				if(palavra.endsWith("ism") && medida(palavra.substring(0, palavra.length() - 3)) > 1 ){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			case 't':
				if(palavra.endsWith("ate") && medida(palavra.substring(0, palavra.length() - 3)) > 1 ){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				} else if(palavra.endsWith("iti") && medida(palavra.substring(0, palavra.length() - 3)) > 1){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			case 'u':
				if(palavra.endsWith("ous") && medida(palavra.substring(0, palavra.length() - 3)) > 1 ){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			case 'v':
				if(palavra.endsWith("ive") && medida(palavra.substring(0, palavra.length() - 3)) > 1 ){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			case 'z':
				if(palavra.endsWith("ize") && medida(palavra.substring(0, palavra.length() - 3)) > 1 ){
					palavra = palavra.substring(0, palavra.length() - 3);
					break;
				}
				break;
			default:
				break;
			}
		}
		
		return palavra;
		
	}
	
	public static String eliminaSufixos2(String palavra){
		
			if(palavra.length() > 1 && palavra.charAt(palavra.length() - 1) == 'e'){
				if(palavra.endsWith("icate") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra  = palavra.substring(0, palavra.length() - 5) + "ic";
					return palavra;
				} else if(palavra.endsWith("ative") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5);
					return palavra;
				} else if(palavra.endsWith("alize") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
					palavra = palavra.substring(0, palavra.length() - 5) + "al";
					return palavra;
				}
			} else if(palavra.endsWith("iciti") && medida(palavra.substring(0, palavra.length() - 5)) > 0){
				palavra = palavra.substring(0, palavra.length() - 5) + "ic";
				return palavra;
			} else if(palavra.endsWith("ical") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
				palavra = palavra.substring(0, palavra.length() - 4) + "ic";
				return palavra;
			}  else if(palavra.endsWith("ful") && medida(palavra.substring(0, palavra.length() - 3)) > 0){
				palavra = palavra.substring(0, palavra.length() - 3);
				return palavra;
			} else if(palavra.endsWith("ness") && medida(palavra.substring(0, palavra.length() - 4)) > 0){
				palavra = palavra.substring(0, palavra.length() - 4);
				return palavra;
			}

		return palavra;
	}
	
	
	public static boolean terminaComConsoanteDupla(String palavra){
		if((palavra.length() > 2) && (palavra.charAt(palavra.length() - 1) == palavra.charAt(palavra.length() - 2))){
			return true;
		}
		return false;
	} 
	
	private static String completaPassadoOuGerundio(String palavra) {
		
		if(palavra.endsWith("at") || palavra.endsWith("bl") || palavra.endsWith("iz")){
			palavra = palavra + "e";
			return palavra;
		} else if(terminaComConsoanteDupla(palavra) && !(palavra.endsWith("ll") || palavra.endsWith("ss") || palavra.endsWith("zz"))){
			palavra = palavra.substring(0, palavra.length() - 1);
			return palavra;
		} else if(medida(palavra) == 1 && terminaComCVC(palavra)){
			palavra = palavra + "e";
			return palavra;
		}
		
		return palavra;
	}

	public static boolean temVogal(String palavra){
		char[] letras = palavra.toCharArray();
		for (int i = 0; i < letras.length; i++) {
			if(ehVogal(palavra, letras[i], i)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean terminaComCVC(String palavra) {
		if (palavra.length() >= 3) {
			char[] letras = palavra.substring(palavra.length() - 3,
					palavra.length()).toCharArray();

			if (ehConsoante(palavra, letras[0], 0)
					&& ehVogal(palavra, letras[1], 1)
					&& ehConsoante(palavra, letras[2], 2)
					&& letras[2] != 'w' && letras[2] != 'x' && letras[2] != 'y')
				return true;
		}
		return false;
	}

	public static String ultimaEliminacao(String palavra){
		if(palavra.endsWith("e") ){
			if(medida(palavra) > 1){
				palavra = palavra.substring(0, palavra.length() - 1);
				return palavra;
			} else if (medida(palavra) == 1 && !terminaComCVC(palavra.substring(0, palavra.length() - 1))){
				palavra = palavra.substring(0, palavra.length() - 1);
				return palavra;
			}
		}
		
		if(medida(palavra) > 1 && terminaComConsoanteDupla(palavra) && palavra.charAt(palavra.length() - 1) == 'l'){
			palavra = palavra.substring(0, palavra.length() - 1);
		}
		
		return palavra;
	}
	
	public static String stem(String palavra){
		palavra = PorterStemmer.eliminaPlural(palavra);
		palavra = PorterStemmer.eliminaPassadoOuGerundio(palavra);
		palavra = PorterStemmer.eliminaYFim(palavra);
		palavra = PorterStemmer.eliminaSufixos1(palavra);
		palavra = PorterStemmer.eliminaSufixos2(palavra);
		palavra = PorterStemmer.eliminaSufixos3(palavra);
		palavra = PorterStemmer.ultimaEliminacao(palavra);
		return palavra;
	}
	
	

	public static void main(String[] args) {
		// PLURAIS
		
		/*System.out.println(PorterStemmer.eliminaPlural("caresses"));
		System.out.println(PorterStemmer.eliminaPlural("ponies"));
		System.out.println(PorterStemmer.eliminaPlural("ties"));
		System.out.println(PorterStemmer.eliminaPlural("caress"));
		System.out.println(PorterStemmer.eliminaPlural("cats"));*/
		
		// GERUNDIOS E PASSADOS
		
		/*System.out.println(PorterStemmer.eliminaPassadoOuGerundio("feed"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("agreed"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("plastered"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("bled"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("motoring"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("sing"));*/
		
		// COMPLETANDO GERUNDIOS E PASSADOS
		
		/*System.out.println(PorterStemmer.eliminaPassadoOuGerundio("conflated"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("troubled"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("sized"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("hopping"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("tanned"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("falling"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("hissing"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("fizzed"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("failing"));
		System.out.println(PorterStemmer.eliminaPassadoOuGerundio("filing"));*/
		
		
		// COMPLETANDO Y
		
		/*System.out.println(PorterStemmer.eliminaYFim("happy"));
		System.out.println(PorterStemmer.eliminaYFim("sky"));*/
		
		// ELIMINA SUFIXOS 1
		
		/*System.out.println(PorterStemmer.eliminaSufixos1("relational"));
		System.out.println(PorterStemmer.eliminaSufixos1("conditional"));
		System.out.println(PorterStemmer.eliminaSufixos1("rational"));
		System.out.println(PorterStemmer.eliminaSufixos1("valenci"));
		System.out.println(PorterStemmer.eliminaSufixos1("hesitanci"));
		System.out.println(PorterStemmer.eliminaSufixos1("digitizer"));
		System.out.println(PorterStemmer.eliminaSufixos1("conformabli"));
		System.out.println(PorterStemmer.eliminaSufixos1("radicalli"));
		System.out.println(PorterStemmer.eliminaSufixos1("differentli"));
		System.out.println(PorterStemmer.eliminaSufixos1("vileli"));
		System.out.println(PorterStemmer.eliminaSufixos1("analogousli"));
		System.out.println(PorterStemmer.eliminaSufixos1("vietnamization"));
		System.out.println(PorterStemmer.eliminaSufixos1("predication"));
		System.out.println(PorterStemmer.eliminaSufixos1("operator"));
		System.out.println(PorterStemmer.eliminaSufixos1("feudalism"));
		System.out.println(PorterStemmer.eliminaSufixos1("decisiveness"));
		System.out.println(PorterStemmer.eliminaSufixos1("hopefulness"));
		System.out.println(PorterStemmer.eliminaSufixos1("callousness"));
		System.out.println(PorterStemmer.eliminaSufixos1("formaliti"));
		System.out.println(PorterStemmer.eliminaSufixos1("sensitiviti"));
		System.out.println(PorterStemmer.eliminaSufixos1("sensibiliti"));*/
		
		// ELIMINA SUFIXOS 2
		
		/*System.out.println(PorterStemmer.eliminaSufixos2("triplicate"));
		System.out.println(PorterStemmer.eliminaSufixos2("formative"));
		System.out.println(PorterStemmer.eliminaSufixos2("formalize"));
		System.out.println(PorterStemmer.eliminaSufixos2("electriciti"));
		System.out.println(PorterStemmer.eliminaSufixos2("electrical"));
		System.out.println(PorterStemmer.eliminaSufixos2("hopeful"));
		System.out.println(PorterStemmer.eliminaSufixos2("goodness"));*/
		
		//ELIMINA SUFIXOS 3
		
		/*System.out.println(PorterStemmer.eliminaSufixos3("revival"));
		System.out.println(PorterStemmer.eliminaSufixos3("allowance"));
		System.out.println(PorterStemmer.eliminaSufixos3("inference"));
		System.out.println(PorterStemmer.eliminaSufixos3("airliner"));
		System.out.println(PorterStemmer.eliminaSufixos3("gyroscopic"));
		System.out.println(PorterStemmer.eliminaSufixos3("adjustable"));
		System.out.println(PorterStemmer.eliminaSufixos3("defensible"));
		System.out.println(PorterStemmer.eliminaSufixos3("irritant"));
		System.out.println(PorterStemmer.eliminaSufixos3("replacement"));
		System.out.println(PorterStemmer.eliminaSufixos3("adjustment"));
		System.out.println(PorterStemmer.eliminaSufixos3("dependent"));
		System.out.println(PorterStemmer.eliminaSufixos3("adoption"));
		System.out.println(PorterStemmer.eliminaSufixos3("homologou"));
		System.out.println(PorterStemmer.eliminaSufixos3("communism"));
		System.out.println(PorterStemmer.eliminaSufixos3("activate"));
		System.out.println(PorterStemmer.eliminaSufixos3("angulariti"));
		System.out.println(PorterStemmer.eliminaSufixos3("homologous"));
		System.out.println(PorterStemmer.eliminaSufixos3("effective"));
		System.out.println(PorterStemmer.eliminaSufixos3("bowdlerize"));*/
		
		// ULTIMA ELIMINACAO
		
		/*System.out.println(PorterStemmer.ultimaEliminacao("probate"));
		System.out.println(PorterStemmer.ultimaEliminacao("rate"));
		System.out.println(PorterStemmer.ultimaEliminacao("cease"));
		System.out.println(PorterStemmer.ultimaEliminacao("controll"));
		System.out.println(PorterStemmer.ultimaEliminacao("roll"));*/
				
	}

}
