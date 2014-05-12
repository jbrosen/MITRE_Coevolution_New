package tests;

import interpreter.PrintGraph;
import interpreter.assets.Annuity;
import interpreter.assets.Assets;
import interpreter.assets.Cash;
import interpreter.assets.Material;
import interpreter.assets.PartnershipAsset;
import interpreter.assets.Share;
import interpreter.entities.Entity;
import interpreter.entities.Partnership;
import interpreter.misc.Actions;
import interpreter.misc.Graph;
import interpreter.misc.PartnerData;
import interpreter.misc.Transaction;
import interpreter.misc.Transfer;
import interpreter.misc.Transfer_NEO;
import interpreter.misc.writeFile;
import interpreter.taxCode.TaxCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import evogpj.algorithm.SymbRegMOO_CO;
import evogpj.evaluation.TaxCodeFitness;
import evogpj.evaluation.TaxFitness;
import evogpj.genotype.ListGenotype;
import evogpj.gp.Individual;



public class testCases {
	Graph graph = new Graph();
	TaxCode taxCode = new TaxCode();
	
	
	ArrayList<Entity> nodesList = graph.getNodes();
	Transfer t = new Transfer(nodesList, taxCode);
	PrintGraph g = new PrintGraph(nodesList);
	
	
	
	
	
	//@Test
	public void test1() {
		
		Cash c1 = new Cash(300);
		Material HouseB = new Material(300,"HouseB",1);
		Actions a1 = new Actions("P1","C",HouseB);
		Actions a2 = new Actions("C","P1",c1);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//@Test
	public void test2() {
		
		
		Cash c1 = new Cash(400);
		Material HouseAB = new Material(400,"HouseAB",1);
		Actions a1 = new Actions("P1","C",HouseAB);
		Actions a2 = new Actions("C","P1",c1);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//@Test
	public void test3() {
		Cash c1 = new Cash(600);
		PartnershipAsset pa = new PartnershipAsset(30,"P1");
		Actions a1 = new Actions("A","C",pa);
		Actions a2 = new Actions("C","A",c1);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//@Test
	public void test4() {
		Cash c1 = new Cash(300);
		Material HouseP2 = new Material(300,"HouseP2",1);
		Actions a1 = new Actions("P2","C",HouseP2);
		Actions a2 = new Actions("C","P2",c1);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//@Test
	public void test5() {
		Cash c1 = new Cash(300);
		Material HouseE = new Material(300,"HouseE",1);
		Actions a1 = new Actions("P1","E",c1);
		Actions a2 = new Actions("E","P1",HouseE);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//check when house>cash.
	//@Test
	public void test6() {
		Cash c1 = new Cash(500);
		Material HouseE = new Material(500,"HouseE",1);
		Actions a1 = new Actions("P1","E",c1);
		Actions a2 = new Actions("E","P1",HouseE);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//test shares.
	//taxpayer C gives Cash
	//Partnership P1 gives a share
	//@Test
	public void test7() {
		Cash c1 = new Cash(500);
		Share s = new Share(30);
		Actions a1 = new Actions("C","P1",c1);
		Actions a2 = new Actions("P1","C",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//+++ doesn't work because cash values not added up
	//test shares.
	//partnership to another partnership
	//Partnership P2 gives a share
	//@Test
	public void test8() {
		Cash c1 = new Cash(500);
		Share s = new Share(30);
		Actions a1 = new Actions("P1","P3",c1);
		Actions a2 = new Actions("P3","P1",s);
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//test shares.
	//taxpayer C gives share
	//Partnership P1 gives cash
	//this should be illegal
	//@Test
	public void test9() {
		Cash c1 = new Cash(300);
		Share s = new Share(30);
		Actions a1 = new Actions("P1","C",c1);
		Actions a2 = new Actions("C","P1",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//P1 and P2 exchange share and house.
	//@Test
	public void test10() {
		Material HouseB = new Material(300,"HouseB",1);
		Share s = new Share(30);
		Actions a1 = new Actions("P1","P2",HouseB);
		Actions a2 = new Actions("P2","P1",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	// taxpayer E and P1 exchange house and share
	//@Test
	public void test11() {
		Material HouseE = new Material(500,"HouseE",1);
		Share s = new Share(30);
		Actions a1 = new Actions("E","P1",HouseE);
		Actions a2 = new Actions("P1","E",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//tax payer A and E exchange house and share
	//should be illegal
	//@Test
	public void test12() {
		Material HouseE = new Material(500,"HouseE",1);
		Share s = new Share(30);
		Actions a1 = new Actions("E","A",HouseE);
		Actions a2 = new Actions("A","E",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	// exchange PAsset and Share
	//illegal
	//@Test
	public void test13() {
		PartnershipAsset pa = new PartnershipAsset(30.0,"P1");
		Share s = new Share(30);
		Actions a1 = new Actions("A","P1",pa);
		Actions a2 = new Actions("P1","A",s);

		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	// A contributes a house.P1 gives a share.
	// A already has a PASSET.It shouldn't get a new PAsset.
	//this is illegal
	
	//@Test
	public void test14() {
		Material HouseA = new Material(300,"HouseA",1);
		Share s = new Share(30);
		Actions a1 = new Actions("A","P1",HouseA);
		Actions a2 = new Actions("P1","A",s);
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	//checking annuity.
	//transfer annuity and house between two tax payers
//	@Test
	public void test15() {
		Material HouseA = new Material(300,"HouseA",1);
		Annuity a  = new Annuity(600,30);
		Actions a1 = new Actions("A","C",HouseA);
		Actions a2 = new Actions("C","A",a);
		
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//checking annuity.
	//transfer annuity and house between P1 and E
//	@Test
	public void test16() {
		Material HouseE = new Material(500,"HouseE",1);
		Annuity a  = new Annuity(500,30);
		Actions a1 = new Actions("E","P1",HouseE);
		Actions a2 = new Actions("P1","E",a);
		
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//checking annuity.
	//transfer annuity and Passet between A and E
//	@Test
	public void test17() {
		PartnershipAsset pa = new PartnershipAsset(30.0,"P1");
		Annuity a  = new Annuity(700,30);
		Actions a1 = new Actions("A","E",pa);
		Actions a2 = new Actions("E","A",a);
		
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
	
	//checking annuity.
	//transfer annuity and share between D and E
	//should be illegal
//	@Test
	public void test18() {
		Annuity a  = new Annuity(500,30);
		Share s = new Share(30);

		Actions a1 = new Actions("C","P1",a);
		Actions a2 = new Actions("P1","C",s);
		
		
		Transaction t1 = new Transaction(a1,a2);
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		
	}
//	@Test
	public void allTax() {
		TaxCode tc = new TaxCode();
		tc.setAnnuityThreshold(0);
		t.setTaxCode(tc);
		
//		NewCo sells m1 to Brown for c1
		Material m1 = new Material(200,"Hotel",1);
		Cash c1 = new Cash(200);
		
		Actions a21 = new Actions("NewCo","Brown",m1);
		Actions a22 = new Actions("Brown","NewCo",c1);
		Transaction t2 = new Transaction(a21,a22);
		if (t.doTransfer(t2)) {
			g.printGraph(t2);
		}
	}
	

	
//	@Test
	public void fitnessTest() {
		writeFile wf = new writeFile("C:\\Users\\Jacob\\Documents\\MIT\\SCOTE\\code\\Tax\\Tax\\src\\interpreter\\output.txt");
		ArrayList<Integer> alist = new ArrayList<Integer>();
		ListGenotype lg = new ListGenotype(alist);
		Individual ind1 = new Individual(lg);
		
		TaxCodeFitness tcf = new TaxCodeFitness(graph);
		tcf.eval(ind1);
	}
	
	/*
	 * This should generate zero tax based on the initial GA
	 * Transaction(FamilyTrust,NewCo,Annuity(100,30),Annuity(100,30))Transaction(Jones,JonesCo,PartnershipAsset(99,FamilyTrust),Annuity(300,30))
	 */
//	@Test
	public void noTaxTest() {
		TaxCode tc = new TaxCode();
		tc.setAnnuityThreshold(0);
		t.setTaxCode(tc);
		
		
		ArrayList<Entity> nodes = graph.getNodes();
		for (Entity e : nodesList) {
			if (e.getName()=="Jones")
				System.out.println(e.getTotalTax());
		}
		
	}
	
	/*
	 * This creates a three-way cycle that causes a stackoverflow error
	 */
//	@Test
	public void cycleTest() {
		
		TaxCode tc = new TaxCode();
		tc.setAnnuityThreshold(0);
		t.setTaxCode(tc);
		
//		FamilyTrust gets Partnership in JonesCo from Jones for Annuity
		PartnershipAsset p1 = new PartnershipAsset(99,"JonesCo");
		Annuity a1 = new Annuity(200,30);
		
		Actions A11 = new Actions("FamilyTrust","Jones",a1);
		Actions A12 = new Actions("Jones","FamilyTrust",p1);
		Transaction t1 = new Transaction(A11,A12);
		if (t.doTransfer(t1))
			g.printGraph(t1);
		else
			System.out.println("1: ILLEGAL");
		
		
//		Jones sells PShip in FT to NewCo
		PartnershipAsset p2 = new PartnershipAsset(99,"FamilyTrust");
		Annuity a2 = new Annuity(198,30);
		Actions A21 = new Actions("NewCo","Jones",a2);
		Actions A22 = new Actions("Jones","NewCo",p2);
		Transaction t2 = new Transaction(A21,A22);
		if (t.doTransfer(t2))
			g.printGraph(t2);
		else
			System.out.println("2: ILLEGAL");
	}
	
//	@Test
	public void testFitnesses() {
		TaxFitness tf = new TaxFitness(graph);
		TaxCodeFitness tcf = new TaxCodeFitness(graph);
		
		ArrayList<String> transactions = new ArrayList<String>();
		transactions.add("Transaction(JonesCo,FamilyTrust,PartnershipAsset(99,NewCo),Annuity(200,30))");
//		transactions.add("Transaction(Brown,NewCo,Cash(200),Material(200,Hotel,1))");
		System.out.println("ANSWER #1: "+tf.getFitnessOfIndividual(transactions, 50));
		
		System.out.println("ANSWER #2: "+tcf.getFitnessOfIndividual(50, transactions));
	}

	
	/*
	 * DIFFERENCES IN OLD AND NEW TRANSFER
	 * Transaction(JonesCo,Brown,Material(200,Hotel,1),Cash(200))
	 * Old: 78.48, New: Double.MIN_VALUE
	 * PROBLEM: Even if the transaction is deemed infeasible, the Cash object is still taken away
	 * from FROM and given to the other entity. Why doesn't this happen in the older version? I 
	 * probably fucked something up. Fix it when you come back from class
	 * SOLVED
	 * 
	 * Transaction(JonesCo,Brown,Material(200,Hotel,1),Material(200,Hotel,1))Transaction(Brown,FamilyTrust,Material(200,Hotel,1),PartnershipAsset(99,JonesCo))
	 * Transaction(Brown,JonesCo,Cash(200),Material(200,Hotel,1))
	 * OLD: Double.MIN_VALUE, New: -78.408
	 * 
	 * Transaction(NewCo,FamilyTrust,Annuity(100,30),Annuity(300,30))Transaction(Brown,Jones,Annuity(200,30),PartnershipAsset(99,JonesCo))
	 * Transaction(FamilyTrust,NewCo,Annuity(300,30),Material(200,Hotel,1))
	 * 
	 */
	
	
	/*
	 * Action1: from: NewCo to: Jones Asset: Material and Action2: from: Jones to: NewCo Asset: Annuity
	Action1: from: JonesCo to: FamilyTrust Asset: PartnershipAsset and Action2: from: FamilyTrust to: JonesCo Asset: Annuity
	Action1: from: Jones to: Brown Asset: Material and Action2: from: Brown to: Jones Asset: Cash
	Action1: from: Brown to: JonesCo Asset: Material and Action2: from: JonesCo to: Brown Asset: Annuity
	Action1: from: Jones to: Brown Asset: PartnershipAsset and Action2: from: Brown to: Jones Asset: AnnuityAction1: from: FamilyTrust to: Jones Asset: PartnershipAsset and Action2: from: Jones to: FamilyTrust Asset: Annuity
	Action1: from: JonesCo to: Jones Asset: Annuity and Action2: from: Jones to: JonesCo Asset: PartnershipAsset
	 */
	

	
	
//	@Test
	public void parseTaxFitnessOutput() {
		String fname = "C:\\Users\\Jacob\\Documents\\MIT\\SCOTE\\MITRE_coevolution\\Tax\\Tax\\src\\interpreter\\agg1.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fname));
			String line = null;
			ArrayList<String> trans = new ArrayList<String>();
			ArrayList<ArrayList<String>> finalTransactions = new ArrayList<ArrayList<String>>();
			try{
				while ((line = reader.readLine()) != null ) {
					if (line.endsWith(")")) {
						trans.add(line);
//						System.out.println("HI"+line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (String tran : trans) {
//				graph.setTransactions(new ArrayList<Transaction>());
				graph = new Graph();
				String[] splitTrans = tran.split("\\)\\)");
				ArrayList<String> finTran = new ArrayList<String>();
				for (int i = 0 ; i < splitTrans.length ; ++i) {
					finTran.add(splitTrans[i].trim()+"))");
//					System.out.println("HI"+finTran.get(i));
				}
				TaxCode tc = new TaxCode();
				
				graph.createAction(finTran);
				Transfer_NEO tn = new Transfer_NEO(graph, tc);
				ArrayList<Transaction> transactions = graph.getTransactions();
				ArrayList<String> legalTransactions = new ArrayList<String>();
				
//				for (Transaction t1 : transactions) {
////					System.out.println(t1.toString());
//					if (tn.doTransfer(t1)) {
//						legalTransactions.add(t1);
//					}
//				}
				
				for (int i = 0 ; i < transactions.size() ; ++i) {
					if (tn.doTransfer(transactions.get(i))) {
						legalTransactions.add(finTran.get(i));
					}
				}
//				System.out.print("\n");
				finalTransactions.add(legalTransactions);
				
			}
			
//			print  out the legal tranactions
//			for (ArrayList<String> tt : finalTransactions) {
//				for (String t : tt) {
//					System.out.print(t);
//				}
//				if (tt.size()>0)
//					System.out.print("\n");
//			}
			
			ArrayList<String> combinedTrans = new ArrayList<String>();
			for (ArrayList<String> tr : finalTransactions) {
				String total = "";
				for (String s : tr) {
					total += s;
				}
				combinedTrans.add(total);
			}
			
			
			HashMap<String,Integer> transactionCount = new HashMap<String,Integer>();
			for (String tr : combinedTrans) {
				if (transactionCount.containsKey(tr)) {
					transactionCount.put(tr,transactionCount.get(tr)+1);
				}
				else {
					transactionCount.put(tr, 1);
				}
			}
			
			for (String s : transactionCount.keySet()) {
				System.out.println(s+", "+transactionCount.get(s));
			}
			
			System.out.println(combinedTrans.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
//	@Test
	public void testShare() {
		ArrayList<String> ret = new ArrayList<String>();
		TaxCode tc = new TaxCode();
		
//		ret.add("Transaction(Brown,JonesCo,Cash(200),Share(50))");
//		ret.add("Transaction(NewCo,FamilyTrust,Annuity(200,30),Share(25))");
//		ret.add("Transaction(FamilyTrust,JonesCo,Annuity(100,30),PartnershipAsset(49.5,NewCo))");
		ret.add("Transaction(Brown,Jones,Cash(66),PartnershipAsset(33,JonesCo))");
		
		graph.createAction(ret);
		ArrayList<Transaction> trans = graph.getTransactions();
		
		
		
		for (Entity e : nodesList) {
			if (e.getName() == "JonesCo") {
				ArrayList<PartnerData> pd = ((Partnership)e).getPartnerData();
				for (PartnerData p : pd) {
					System.out.println("HERE: "+p.getName()+", "+p.getShare());
				}
			}
			else if (e.getName() == "Jones") {
				System.out.println("TAX: "+e.getTotalTax());
				for (Assets a : e.getPortfolio()) {
					if (a.toString().equals("PartnershipAsset")) {
						System.out.println("JONES "+((PartnershipAsset)a).printPAsset());
					}
				}
			}
		}
		System.out.println("BREAK\n\n\n");
		Transfer_NEO tn = new Transfer_NEO(graph, tc);
		double auditScore = 0.0;
		for (Transaction tran : trans) {
			if (tn.doTransfer(tran)) {
				g.printGraph(tran);
				auditScore += tran.getAuditScore();
			}
		}
		for (Entity e : nodesList) {
			if (e.getName() == "Brown") {
				for (Assets a : e.getPortfolio()) {
					if (a.toString().equals("PartnershipAsset")) {
						System.out.println("BROWN "+((PartnershipAsset)a).printPAsset());
					}
				}
			}
			else if (e.getName() == "Jones") {
				System.out.println("TAX: "+e.getTotalTax());
				for (Assets a : e.getPortfolio()) {
					if (a.toString().equals("PartnershipAsset")) {
						System.out.println("JONES "+((PartnershipAsset)a).printPAsset());
					}
				}
			}
			else if (e.getName() == "JonesCo") {
				ArrayList<PartnerData> pd = ((Partnership)e).getPartnerData();
				for (PartnerData p : pd) {
					System.out.println("HERE: "+p.getName()+", "+p.getShare());
				}
				
			}
		}
		
		
	}
	
//	@Test
	public void testNewTransfer() {
		TaxCode tc = new TaxCode();
//		tc.setAnnuityThreshold(0);
//		tc.setChildSalePrevention(1);
//		tc.setAnnuityForMaterial(true);
		SymbRegMOO_CO srm = new SymbRegMOO_CO();
		/*
		 * Why does this not generate any tax?
		 * Transaction(Brown,Jones,Annuity(300,30),PartnershipAsset(99,JonesCo))
		 */
		
		
//		Transaction(JonesCo,NewCo,Cash(300),Material(200,Hotel,1))Transaction(Brown,NewCo,Cash(300),PartnershipAsset(99,NewCo))
//		Transaction(NewCo,NewCo,PartnershipAsset(99,NewCo),Material(200,Hotel,1))Transaction(NewCo,Brown,Material(200,Hotel,1),Cash(300))
//		Transaction(FamilyTrust,Brown,Annuity(200,30),PartnershipAsset(99,FamilyTrust))Transaction(JonesCo,NewCo,Annuity(200,30),PartnershipAsset(99,NewCo))
//		Transaction(FamilyTrust,NewCo,Cash(300),Cash(100))Transaction(FamilyTrust,NewCo,Annuity(200,30),Material(200,Hotel,1))
//		Transaction(Brown,Jones,Annuity(300,30),Annuity(300,30))
		ArrayList<String> ret = new ArrayList<String>();
//		Transaction(NewCo,Jones,Cash(100),Material(200,Hotel,1))Transaction(JonesCo,Brown,PartnershipAsset(99,NewCo),Annuity(200,30))
		
//		ret.add("Transaction(Jones,Brown,PartnershipAsset(99,JonesCo),Annuity(200,30))");
//		ret.add("Transaction(JonesCo,Jones,PartnershipAsset(99,NewCo),Annuity(200,30))");
//		ret.add("Transaction(Brown,Jones,Annuity(200,30),PartnershipAsset(50,JonesCo))");
		ret.add("Transaction(Jones,Brown,PartnershipAsset(99,JonesCo),Annuity(200,30))");
		
		
		
		graph.createAction(ret);
		ArrayList<Transaction> trans = graph.getTransactions();
//		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		
		Transfer_NEO tn = new Transfer_NEO(graph, tc);
		double auditScore = 0.0;
		for (Transaction tran : trans) {
			if (tn.doTransfer(tran)) {
				g.printGraph(tran);
				auditScore += tran.getAuditScore();
			}
		}
		
		for (Entity e : nodesList) {
			if (e.getName() == "Brown") {
				System.out.println("CASH: "+e.getTotalCash());
			}
			else if (e.getName() == "Jones") {
				for (Assets a : e.getPortfolio()) {
					if (a.getName() == "JonesCo") {
						System.out.println("JonesCo starts out at "+a.getCurrentFMV());
					}
				}
			}
		}
		
		Transaction finTran = graph.getFinalTransaction();
		if (tn.doTransfer(finTran)) {
			g.printGraph(finTran);
			auditScore += finTran.getAuditScore();
		}
//		System.out.println(finTran.toString()+", "+auditScore);
		for (Entity e : nodesList) {
			if (e.getName()=="Jones") {
				System.out.println(e.getTotalTax());
				for (Assets a : e.getPortfolio()) {
					if (a.getName() == "JonesCo") {
						System.out.println("JonesCo FMV for Jones: "+a.getCurrentFMV());
					}
				}
			}
//			else if (e.getName() == "JonesCo") {
//				for (Entity ee : e.getPartners()) {
//					System.out.println(ee.getName());
//				}
//			}
			else if (e.getName()=="Brown") {
				for (Assets a : e.getPortfolio()) {
					if (a.getName() == "JonesCo") {
						System.out.println("JonesCo FMV for Brown: "+a.getCurrentFMV());
					}
				}
			}
//			else if (e.getName() == "FamilyTrust") {
//				for (Assets a : e.getPortfolio()) {
//					if (a.getName() == "JonesCo") {
//						for (String s : a.getInsideBasisMap().keySet()) {
//							System.out.println(s+", "+a.getInsideBasisMap().get(s));
//						}
//					}
//				}
//			}
//			else if (e.getName() == "Brown") {
//				for (Assets a : e.getPortfolio()) {
//					System.out.println(a.getName());
//				}
//			}
		}
		
	}
	
	
//	@Test
	public void ibob() {
		TaxCode tc = new TaxCode();
		tc.setAnnuityThreshold(0);
		tc.setChildSalePrevention(1);
		tc.setAnnuityForMaterial(true);
		t.setTaxCode(tc);
		ArrayList<String> ret = new ArrayList<String>();
//		Transaction(Jones,FamilyTrust,PartnershipAsset(99,JonesCo),Annuity(100,30))
		ret.add("Transaction(Brown,Jones,Annuity(200,30),PartnershipAsset(99,JonesCo))");
		ret.add("Transaction(FamilyTrust,NewCo,Annuity(300,30),Material(200,Hotel,1))");
//		ret.add("Transaction(Brown,JonesCo,Cash(200),Material(200,Hotel,1))");
//		ret.add("Transaction(JonesCo,Brown,Material(200,Hotel,1),Cash(200))");
		
		
		
		graph.createAction(ret);
		ArrayList<Transaction> trans = graph.getTransactions();

		for (Transaction r : trans) {
			if (t.doTransfer(r)) {
				g.printGraph(r);
			}
		}
		for (Entity e : nodesList) {
			if (e.getName() == "Brown") {
				System.out.println("CASH: "+e.getTotalCash());
			}
			for (Assets ass : e.getPortfolio()) {
				if (ass.toString() == "Cash")
					System.out.println(e.getName()+" HAS THE CASH");
			}
		}
		
		/*
		 * Phenotypes that generate Double.MIN_VALUE or 0.0 tax that are NOT iBob
		 * Transaction(NewCo,Brown,Material(200,Hotel,1),Annuity(300,30)) - Double.MIN_VALUE
		 * Problem with this one is that the point of exhanging an annuity is that when its between two entities that a 
		 * taxpayer owns, they dont care whether or not the money ever actually makes it there
		 * Transaction(Jones,Brown,PartnershipAsset(99,JonesCo),Annuity(300,30)) - Double.MIN_VALUE
		 * Transaction(Jones,JonesCo,Annuity(200,30),PartnershipAsset(99,NewCo)) - 0, should be illegal/infeasible
		 * NOTE in the PartnershipAsset block of the Transfer class, you only look through the getPartners function for a link,
		 * not the getPartnershipIn field. This becomes illegal when you use that field as well
		 * Transaction(FamilyTrust,NewCo,Annuity(300,30),Material(200,Hotel,1)) - 0
		 * Would this be more suspicious?
		 * The second transaction here is illegal but it doesn't matter
		 * Transaction(JonesCo,Brown,PartnershipAsset(99,NewCo),Annuity(200,30)) - Double.MIN_VALUE
		 * Again, can be solved by needing to trust that the annuity will be repaid. How do you code this in?
		 * Transaction(Jones,NewCo,PartnershipAsset(99,NewCo),Material(200,Hotel,1))Transaction(Brown,JonesCo,PartnershipAsset(99,JonesCo),Annuity(200,30))
		 * Transaction(Jones,Jones,Material(200,Hotel,1),Cash(300))Transaction(NewCo,FamilyTrust,Cash(100),PartnershipAsset(99,FamilyTrust))
		 * Transaction(JonesCo,FamilyTrust,PartnershipAsset(99,NewCo),Annuity(200,30))Transaction(Brown,Jones,Material(200,Hotel,1),Material(200,Hotel,1))
		 * Transaction(FamilyTrust,Brown,Material(200,Hotel,1),Cash(100))Transaction(Jones,FamilyTrust,Annuity(100,30),Cash(300))
		 * FOUND IBOB!!
		 * Transaction(JonesCo,FamilyTrust,PartnershipAsset(99,NewCo),Annuity(200,30))Transaction(Jones,FamilyTrust,PartnershipAsset(99,JonesCo),Cash(300))
		 * FOUND IBOB AGAIN!!
		 * Transaction(FamilyTrust,NewCo,Annuity(200,30),Material(200,Hotel,1))
		 * 
		 */
		
		Transaction finTran = graph.getFinalTransaction();
		if (t.doTransfer(finTran))
			g.printGraph(finTran);
		
		for (Entity e : nodesList) {
			if (e.getName()=="Jones") {
				System.out.println(e.getTotalTax());
//				System.out.println("PARTNERS");
//				for (Entity ee : e.getPartners())
//					System.out.print(ee.getName()+", ");
//				System.out.println("CHILDREN");
//				for (Entity ee : e.getPartnershipIn())
//					System.out.print(ee.getName()+", ");
			}
		}
	}
	
//	@Test
	public void realiBOB() {
		Annuity a1 = new Annuity(200,30);
		PartnershipAsset p1 = new PartnershipAsset(99,"NewCo");
		Actions a11 = new Actions("FamilyTrust","JonesCo",a1);
		Actions a12 = new Actions("JonesCo","FamilyTrust",p1);
		Transaction t1 = new Transaction(a11,a12);
		if (t.doTransfer(t1))
			g.printGraph(t1);
		
		Material m1 = new Material(200,"Hotel",1);
		Cash c1 = new Cash(200);
		
		Actions a21 = new Actions("NewCo","Brown",m1);
		Actions a22 = new Actions("Brown","NewCo",c1);
		Transaction t2 = new Transaction(a21,a22);
		
		if (t.doTransfer(t2)) {
			g.printGraph(t2);
		}
	}
	
//	@Test
	public void test19() {
		TaxCode tc = new TaxCode();
		tc.setAnnuityThreshold(0);
		t.setTaxCode(tc);
		
		Annuity a = new Annuity(200,30);
		PartnershipAsset p1 = new PartnershipAsset(99,"NewCo");
		
		Actions a1 = new Actions("NewCo","JonesCo",a);
		Actions a2 = new Actions("JonesCo","NewCo",p1);
		Transaction t1 = new Transaction(a1,a2);
		
		if (t.doTransfer(t1))
			g.printGraph(t1);
		
	}
	
	
	
	
	//NOT IBOB--long output for jacob
//	@Test
	public void test20() {
		
		Cash c1 = new Cash(300);
		Material HouseB = new Material(300,"HouseB",1);
		Actions a1 = new Actions("P1","C",HouseB);
		Actions a2 = new Actions("C","P1",c1);
		
		Cash c2 = new Cash(600);
		PartnershipAsset pa = new PartnershipAsset(30,"P1");
		Actions a3 = new Actions("A","C",pa);
		Actions a4 = new Actions("C","A",c1);
		
		Cash c3 = new Cash(300);
		Material HouseP2 = new Material(300,"HouseP2",1);
		Actions a5 = new Actions("P2","C",HouseP2);
		Actions a6 = new Actions("C","P2",c3);

		Cash c4 = new Cash(300);
		Material HouseE = new Material(300,"HouseE",1);
		Actions a7 = new Actions("P1","E",c4);
		Actions a8 = new Actions("E","P1",HouseE);
		
		Cash c5 = new Cash(500);
		Share s = new Share(30);
		Actions a9 = new Actions("C","P1",c5);
		Actions a10 = new Actions("P1","C",s);
		
		Cash c6 = new Cash(500);
		Share s1 = new Share(30);
		Actions a11 = new Actions("P1","P3",c6);
		Actions a12 = new Actions("P3","P1",s1);
		
		
		//Material HouseB = new Material(300,"HouseB",1);
		Share s4 = new Share(30);
		Actions a13 = new Actions("P1","P2",HouseB);
		Actions a14= new Actions("P2","P1",s4);
		
		Material HouseA = new Material(300,"HouseA",1);
		Share s5 = new Share(30);
		Actions a15 = new Actions("A","P1",HouseA);
		Actions a16 = new Actions("P1","A",s5);
		
		
		Transaction t1 = new Transaction(a1,a2);
		Transaction t2 = new Transaction(a3,a4);
		Transaction t3 = new Transaction(a5,a6);
		Transaction t4 = new Transaction(a7,a8);
		Transaction t5 = new Transaction(a9,a10);
		Transaction t6 = new Transaction(a11,a12);
		Transaction t7 = new Transaction(a13,a14);
		Transaction t8 = new Transaction(a15,a16);

		
		if(t.doTransfer(t1)){
			g.printGraph(t1);
		}
		if(t.doTransfer(t2)){
			g.printGraph(t2);
		}
		if(t.doTransfer(t3)){
			g.printGraph(t3);
		}
		if(t.doTransfer(t4)){
			g.printGraph(t4);
		}
		if(t.doTransfer(t5)){
			g.printGraph(t5);
		}
		if(t.doTransfer(t6)){
			g.printGraph(t6);
		}
		if(t.doTransfer(t7)){
			g.printGraph(t7);
		}
		if(t.doTransfer(t8)){
			g.printGraph(t8);
		}
		
	}
	

}
