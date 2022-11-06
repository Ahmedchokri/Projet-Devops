package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import com.esprit.examen.repositories.StockRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {
	

	@Mock
	FactureRepository facturerepo;

	@Autowired
	ReglementRepository reglementrepo;


	@Autowired
	private ReglementServiceImpl reglementService;
	
	@InjectMocks
	private FactureServiceImpl factureService;

	
	@Test
	public void testAddReglement() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateReglement = dateFormat.parse("20/03/2011");
		Reglement r = new Reglement(500,150,true,dateReglement);
		Reglement reglement = reglementService.addReglement(r);
		System.out.print("produit "+reglement);
		assertNotNull(reglement.getIdReglement());
		assertNotNull(reglement.getMontantPaye());
		assertTrue(reglement.getPayee());
		reglementrepo.deleteById(reglement.getIdReglement());
	}
	@Test
	public void testRetrieveAllReglements() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateReglement = dateFormat.parse("20/03/2011");
		List<Reglement> reglements = reglementService.retrieveAllReglements();
		int expected = reglements.size();
		Reglement r = new Reglement(500,150,true,dateReglement);
		Reglement rr = reglementService.addReglement(r);
		assertEquals(expected + 1,  reglementService.retrieveAllReglements().size());
		reglementrepo.deleteById(rr.getIdReglement());

	}

	@Test
	public void testretrieveReglement() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateReglement = dateFormat.parse("20/03/2011");
		Reglement r = new Reglement(500,150,true,dateReglement);
		Reglement reglement = reglementService.addReglement(r);
		Reglement reglement1 = reglementService.retrieveReglement(reglement.getIdReglement());
		assertNotNull(reglement1.getIdReglement());
		reglementrepo.deleteById(reglement1.getIdReglement());
	}
/*
	@Test
	public void getChiffreAffaireEntreDeuxDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateReglement = dateFormat.parse("20/03/2011");
		Date datedebut = dateFormat.parse("15/03/2011");
		Date datefin = dateFormat.parse("30/03/2011");
		Reglement r = new Reglement(500,150,false,dateReglement);
		Reglement reglement = reglementService.addReglement(r);
		Mockito.when(reglementrepo.getChiffreAffaireEntreDeuxDate(datedebut, datefin)).thenReturn((float) 500);
		float chiffre = reglementService.getChiffreAffaireEntreDeuxDate(datedebut, datefin);
		assertEquals(500,  (int) chiffre);
		reglementrepo.deleteById(reglement.getIdReglement());

	}*/
}
