/**
 * 
 */
package br.com.ita.tdd.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoaoDantas
 *
 */
public class CamelCase {

	private static final String REGEX_MAISCULAS = "[A-Z]";
	private static final String REGEX_MINUSCULAS = "[a-z]";
	private static final String REGEX_NUMEROS = "[0-9]";

	public static List<String> converterCamelCase(String original) throws ParseCamelCaseException {
		List<String> resultado = new ArrayList<String>();
		validaString(original);
		int indiceFim = 0;
		for (int i = 0; i < original.length(); i++) {
			if (indiceFim > 0 && i <= indiceFim)
				continue;
			indiceFim = tokenizaFimPalavra(original, i);
			String palavraTokenizada = original.substring(i, indiceFim + 1);
			resultado.add(parseiaPalavraComCaseCorreto(palavraTokenizada));
		}
		System.out.println(resultado);
		return resultado;
	}

	/**
	 * @param original
	 * @throws ParseCamelCaseException 
	 */
	private static void validaString(String original) throws ParseCamelCaseException {
		if(original.matches(".*\\W+.*"))
		{
			throw new ParseCamelCaseException("caracteres especiais não são permitidos, somente letras e números");
		}
		if(Character.toString(original.charAt(0)).matches(REGEX_NUMEROS))
		{
			throw new ParseCamelCaseException("caracteres especiais não são permitidos, somente letras e números");
		}
		
	}

	/**
	 *  Método que tem por finalidade parsear o case do token para lista
	 *  Por exemplo: CPF -> CPF
	 *  Nome -> nome
	 * 
	 * @param palavra
	 * @return
	 */
	private static String parseiaPalavraComCaseCorreto(String palavra) {
		if (palavra.matches("[A-Z][A-Z]+")) {
			return palavra;
		} else {
			return palavra.toLowerCase();
		}
	}

	/**
	 * Percorre a palavra verificando o índice do limite do token
	 * 
	 * @param palavra
	 * @param startIndex
	 * @return
	 */
	private static int tokenizaFimPalavra(String palavra, int startIndex) {
		String letra = Character.toString(palavra.charAt(startIndex));
		Boolean atualEhMaiscula = letra.matches(REGEX_MAISCULAS);
		Boolean atualehNumero = letra.matches(REGEX_NUMEROS);
		Boolean atualEhMinuscula = letra.matches(REGEX_MINUSCULAS);
		for (int j = startIndex + 1; j < palavra.length(); j++) {
			int index = verificarIndiceFimToken(palavra, startIndex, atualEhMaiscula, atualehNumero, atualEhMinuscula,
					j);
			if (index > 0)
				return index;
		}
		return palavra.length() - 1;
	}

	/**
	 * 
	 * Retorna o indice efetivo em um determinando startIndex da palavra
	 * 
	 * @param palavra
	 * @param startIndex
	 * @param atualEhMaiscula
	 * @param atualehNumero
	 * @param atualEhMinuscula
	 * @param actualIndex
	 */
	private static int verificarIndiceFimToken(String palavra, int startIndex, Boolean atualEhMaiscula,
			Boolean atualehNumero, Boolean atualEhMinuscula, int actualIndex) {
		String letraCorrente;
		letraCorrente = Character.toString(palavra.charAt(actualIndex));
		Boolean correnteEhMaiscula = letraCorrente.matches(REGEX_MAISCULAS);
		Boolean correnteEhNumero = letraCorrente.matches(REGEX_NUMEROS);
		if (atualEhMaiscula) {
			int index = trataAtualMaiscula(palavra, startIndex, actualIndex, correnteEhMaiscula, correnteEhNumero);
			if (index > 0) return index;
		} else if (atualehNumero && correnteEhMaiscula)
			return actualIndex - 1;
		else if (atualEhMinuscula && (correnteEhMaiscula || correnteEhNumero))
			return actualIndex - 1;

		return 0;
	}

	/**
	 * Retorna o indice para um token iniciado por letra maiscula
	 * 
	 * @param palavra
	 * @param startIndex
	 * @param actualIndex
	 * @param correnteEhMaiscula
	 * @param correnteEhNumero
	 */
	private static int trataAtualMaiscula(String palavra, int startIndex, int actualIndex, Boolean correnteEhMaiscula,
			Boolean correnteEhNumero) {
		if (correnteEhNumero) {
			return actualIndex - 1;
		} else {
			if (palavra.length() - 1 >= actualIndex + 1) {
				String letraSubsequente = Character.toString(palavra.charAt(actualIndex + 1));
				if (letraSubsequente.matches(REGEX_MINUSCULAS) && startIndex > 0 && correnteEhMaiscula) {
					return actualIndex - 1;
				}
			}
		}
		return 0;
	}

}
