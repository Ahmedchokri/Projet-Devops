package com.esprit.examen.services;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {
	@Autowired
	IProduitService produitService;
	
	
	
	@InjectMocks
	StockServiceImpl stockServiceImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Autowired
	@Mock
	StockRepository stockrepo;
	
	@Test
	public void testAddProduit() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("10/06/2005");
		Date dateDerniereModification = dateFormat.parse("20/03/2011");
		Produit p = new Produit("ABCD","A145S",150,dateCreation,dateDerniereModification);
		Produit produit = produitService.addProduit(p);
		System.out.print("produit "+produit);
		assertNotNull(produit.getIdProduit());
		assertNotNull(produit.getCodeProduit());
		assertTrue(produit.getLibelleProduit().length() > 0);
		produitService.deleteProduit(produit.getIdProduit());

	}
	@Test
	public void testRetrieveAllProduits() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("10/06/2005");
		Date dateDerniereModification = dateFormat.parse("20/03/2011");
		List<Produit> Products = produitService.retrieveAllProduits();
		int expected = Products.size();
		Produit p = new Produit("ABCD","A145S",150,dateCreation,dateDerniereModification);
		Produit produit = produitService.addProduit(p);
		assertEquals(expected + 1, produitService.retrieveAllProduits().size());
		produitService.deleteProduit(produit.getIdProduit());

	}
	@Test
	public void testDeleteProduit() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("10/06/2005");
		Date dateDerniereModification = dateFormat.parse("20/03/2011");
		Date dateNaissance = dateFormat.parse("30/09/2000");
		Produit p = new Produit("ABCD","A145S",150,dateCreation,dateDerniereModification);
		Produit produit = produitService.addProduit(p);
		produitService.deleteProduit(produit.getIdProduit());
		assertNull(produitService.retrieveProduit(produit.getIdProduit()));
	}
	@Test
	public void testassignProduitToStock()  throws ParseException {
		StockRepository stockrepo = Mockito.mock(StockRepository.class);
		Stock s = new Stock((long)2,"ASN8",20,50);
		Mockito.when(stockrepo.findById((long)2)).thenReturn(Optional.of(s));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCreation = dateFormat.parse("10/06/2005");
		Date dateDerniereModification = dateFormat.parse("20/03/2011");
		Produit p = new Produit("ABCD","A145S",150,dateCreation,dateDerniereModification);
		Produit produit = produitService.addProduit(p);

	///	produitService.assignProduitToStock(produit.getIdProduit(),stockServiceImpl.retrieveStock((long)2).getIdStock() );
		assertNotNull(produit.getIdProduit());
		produitService.deleteProduit(produit.getIdProduit());

	}

}
