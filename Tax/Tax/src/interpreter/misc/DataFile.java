package interpreter.misc;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import evogpj.algorithm.Parameters;

public class DataFile {

	
	public String fname;
	public ArrayList<Integer> doubleLinkedList;
	public ArrayList<Integer> singleLinkedList;
	public ArrayList<Integer> materialForAnnuityList;
	
	public ArrayList<Double> meanAllSchemes;
	public ArrayList<Double> sdAllSchemes;
	public ArrayList<Double> meanTopSchemes;
	public ArrayList<Double> sdTopSchemes;
	public ArrayList<Double> bestScheme;
	
	public ArrayList<Double> meanAllCodes;
	public ArrayList<Double> sdAllCodes;
	public ArrayList<Double> meanTopCodes;
	public ArrayList<Double> sdTopCodes;
	public ArrayList<Double> bestCode;
	
	
	public boolean record;
	
	public DataFile(String fname) {
		this.fname = fname;
		this.doubleLinkedList = new ArrayList<Integer>();
		this.singleLinkedList = new ArrayList<Integer>();
		this.materialForAnnuityList = new ArrayList<Integer>();
		this.record = false;
		
		this.meanAllSchemes = new ArrayList<Double>();
		this.sdAllSchemes = new ArrayList<Double>();
		this.meanTopSchemes = new ArrayList<Double>();
		this.sdTopSchemes = new ArrayList<Double>();
		this.bestScheme = new ArrayList<Double>();
		this.meanAllCodes = new ArrayList<Double>();
		this.sdAllCodes = new ArrayList<Double>();
		this.meanTopCodes = new ArrayList<Double>();
		this.sdTopCodes = new ArrayList<Double>();
		this.bestCode = new ArrayList<Double>();
	}
	
	public void addTimeStep() {
		this.doubleLinkedList.add(0);
		this.singleLinkedList.add(0);
		this.materialForAnnuityList.add(0);
	}
	
	public void addToDoubleLinkedList(int num) {
		this.doubleLinkedList.add(num);
	}
	
	public void incDoubleLinkedList() {
		if (this.doubleLinkedList.isEmpty())
			return;
		int size = this.doubleLinkedList.size();
		this.doubleLinkedList.set(size - 1, this.doubleLinkedList.get(size - 1) + 1);
	}
	
	public void incDoubleLinkedList(int inc) {
		if (this.doubleLinkedList.isEmpty())
			return;
		int size = this.doubleLinkedList.size();
		this.doubleLinkedList.set(size - 1, this.doubleLinkedList.get(size - 1) + inc);
	}
	
	public void addToSingleLinkedList(int num) {
		this.singleLinkedList.add(num);
	}
	
	public void incSingleLinkedList() {
		if (this.singleLinkedList.isEmpty())
			return;
		int size = this.singleLinkedList.size();
		this.singleLinkedList.set(size - 1, this.singleLinkedList.get(size - 1) + 1);
	}
	
	public void incSingleLinkedList(int inc) {
		if (this.singleLinkedList.isEmpty())
			return;
		int size = this.singleLinkedList.size();
		this.singleLinkedList.set(size - 1, this.singleLinkedList.get(size - 1) + inc);
	}
	
	public void addToMaterialForAnnuityList(int num) {
		this.materialForAnnuityList.add(num);
	}
	
	public void incMaterialForAnnuityList() {
		if (this.materialForAnnuityList.isEmpty())
			return;
		int size = this.materialForAnnuityList.size();
		this.materialForAnnuityList.set(size - 1, this.materialForAnnuityList.get(size - 1) + 1);
	}
	
	public void incMaterialForAnnuityList(int inc) {
		if (this.materialForAnnuityList.isEmpty())
			return;
		int size = this.materialForAnnuityList.size();
		this.materialForAnnuityList.set(size - 1, this.materialForAnnuityList.get(size - 1) + inc);
	}
	
	public boolean canRecord() {
		return this.record;
	}
	
	public void print() {
		int k = Parameters.Defaults.NUM_CHOOSE;
		for (int i = 0 ; i < this.singleLinkedList.size() ; ++i) {
			System.out.println(this.materialForAnnuityList.get(i)/k+", "+this.singleLinkedList.get(i)/k+", "+this.doubleLinkedList.get(i)/k+", "+
					this.meanAllSchemes.get(i)+", "+this.sdAllSchemes.get(i)+", "+this.meanAllCodes.get(i)+", "+this.sdAllCodes.get(i)+", "+
					this.meanTopSchemes.get(i)+", "+this.sdTopSchemes.get(i)+", "+this.meanTopCodes.get(i)+", "+this.sdTopCodes.get(i)+", "+
					this.bestScheme.get(i)+", "+this.bestCode.get(i));
		}
	}

	
	public void writeToFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname),"utf-8"));
		String titles = "# Material for Annuity,# Single Linked,# Double Linked,Mean Scheme Fitness,SD Scheme Fitness,Mean Code Fitness," +
				"SD Code Fitness,Mean Top Scheme Fitness,SD Top Scheme Fitness,Mean Top Code Fitness,SD Top Code Fitness,Best Scheme Fitness,"+
				"Best Code Fitness";
		writer.write(titles+"\n");
		int k = Parameters.Defaults.NUM_CHOOSE;
		String str;
		for (int i = 0 ; i < this.singleLinkedList.size() ; ++i) {
			str = this.materialForAnnuityList.get(i)/k+","+this.singleLinkedList.get(i)/k+","+this.doubleLinkedList.get(i)/k+","+
					this.meanAllSchemes.get(i)+","+this.sdAllSchemes.get(i)+","+this.meanAllCodes.get(i)+","+this.sdAllCodes.get(i)+","+
					this.meanTopSchemes.get(i)+","+this.sdTopSchemes.get(i)+","+this.meanTopCodes.get(i)+","+this.sdTopCodes.get(i)+","+
					this.bestScheme.get(i)+","+this.bestCode.get(i);
			writer.write(str+"\n");
		}
		
		
		
		writer.close();
	}
	
	public ArrayList<Double> getMeanAllSchemes() {
		return meanAllSchemes;
	}

	public void setMeanAllSchemes(ArrayList<Double> meanAllSchemes) {
		this.meanAllSchemes = meanAllSchemes;
	}

	public ArrayList<Double> getSdAllSchemes() {
		return sdAllSchemes;
	}

	public void setSdAllSchemes(ArrayList<Double> sdAllSchemes) {
		this.sdAllSchemes = sdAllSchemes;
	}

	public ArrayList<Double> getMeanTopSchemes() {
		return meanTopSchemes;
	}

	public void setMeanTopSchemes(ArrayList<Double> meanTopSchemes) {
		this.meanTopSchemes = meanTopSchemes;
	}

	public ArrayList<Double> getSdTopSchemes() {
		return sdTopSchemes;
	}

	public void setSdTopSchemes(ArrayList<Double> sdTopSchemes) {
		this.sdTopSchemes = sdTopSchemes;
	}

	public ArrayList<Double> getBestScheme() {
		return bestScheme;
	}

	public void setBestScheme(ArrayList<Double> bestScheme) {
		this.bestScheme = bestScheme;
	}

	public ArrayList<Double> getMeanAllCodes() {
		return meanAllCodes;
	}

	public void setMeanAllCodes(ArrayList<Double> meanAllCodes) {
		this.meanAllCodes = meanAllCodes;
	}

	public ArrayList<Double> getSdAllCodes() {
		return sdAllCodes;
	}

	public void setSdAllCodes(ArrayList<Double> sdAllCodes) {
		this.sdAllCodes = sdAllCodes;
	}

	public ArrayList<Double> getMeanTopCodes() {
		return meanTopCodes;
	}

	public void setMeanTopCodes(ArrayList<Double> meanTopCodes) {
		this.meanTopCodes = meanTopCodes;
	}

	public ArrayList<Double> getSdTopCodes() {
		return sdTopCodes;
	}

	public void setSdTopCodes(ArrayList<Double> sdTopCodes) {
		this.sdTopCodes = sdTopCodes;
	}

	public ArrayList<Double> getBestCode() {
		return bestCode;
	}

	public void setBestCode(ArrayList<Double> bestCode) {
		this.bestCode = bestCode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

}
