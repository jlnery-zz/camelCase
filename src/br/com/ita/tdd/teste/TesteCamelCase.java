/**
 * 
 */
package br.com.ita.tdd.teste;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.ita.tdd.src.CamelCase;
import br.com.ita.tdd.src.ParseCamelCaseException;

/**
 * @author JoaoDantas
 *
 */
public class TesteCamelCase {
	
	@Test
	public void testaCamelCasePalavraSimples() throws ParseCamelCaseException {
		String palavraSimples = "nome";
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		Assert.assertEquals("nome", resultado.get(0));
		Assert.assertEquals(1, resultado.size());
	}
	
	@Test
	public void testeCamelCasePalavraSimplesLetraMaiscula() throws ParseCamelCaseException
	{
		String palavraSimples = "Nome";
		
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		Assert.assertEquals("nome", resultado.get(0));
		Assert.assertEquals(1, resultado.size());
	}
	
	@Test
	public void testeCamelCasePalavranumeroCPFContribuinte() throws ParseCamelCaseException
	{
		String palavraSimples = "numeroCPFContribuinte";
		
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		
		List<String> esperado = Arrays.asList(new String[]{"numero", "CPF", "contribuinte"});
		
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test
	public void testeCamelCasePalavrarecupera10Primeiros() throws ParseCamelCaseException
	{
		String palavraSimples = "recupera10Primeiros";
		
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		
		List<String> esperado = Arrays.asList(new String[]{"recupera", "10", "primeiros"});
		
		Assert.assertEquals(esperado, resultado);
	}
	
	@Test
	public void testeCamelCasePalavraCPF() throws ParseCamelCaseException
	{
		String palavraSimples = "CPF";
		
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		
		List<String> esperado = Arrays.asList(new String[]{"CPF"});
		
		Assert.assertEquals(esperado, resultado);
	}
	
	
	@Test(expected=ParseCamelCaseException.class)
	public void testeCamelCasePalavranomCompostoComExcecao() throws ParseCamelCaseException
	{
		String palavraSimples = "nome#Composto";
		
		CamelCase.converterCamelCase(palavraSimples);
		
	}
	
	@Test(expected=ParseCamelCaseException.class)
	public void testeCamelCase10PrimeirosComExcecao() throws ParseCamelCaseException
	{
		String palavraSimples = "10Primeiros";
		
		CamelCase.converterCamelCase(palavraSimples);
		
	}
	
	@Test
	public void testeCamelCasePalavrarnumeroCPF() throws ParseCamelCaseException
	{
		String palavraSimples = "numeroCPF";
		
		List<String> resultado = CamelCase.converterCamelCase(palavraSimples);
		
		List<String> esperado = Arrays.asList(new String[]{"numero", "CPF"});
		
		Assert.assertEquals(esperado, resultado);
	}
	
	

}
