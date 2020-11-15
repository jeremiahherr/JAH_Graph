/******************************************************************************
 *  Compilation: javac Experiment.java
 *
 *  Execution: java Experiment
 *
 *  Written by Jeremiah Herr
 *
 *  Client program for carrying out experiment to compare
 *  the efficiency of Prim's and Kruskal's algorithms
 *
 *  Note to self: more graphs at: https://lgylym.github.io/big-graph/dataset.html
 *
 ******************************************************************************/

import java.io.*;

public class Experiment
{	
	// perform a small experiment on a selection of graphs
	// to compare the efficiencies of Prim's and Kruskal's algorithms
	public static void main(String[] args)
	{
		String output = "";

		// graph fabricated by me
		AdjacencyList d = new AdjacencyList("graphD.txt",false); 
		FindMST mstD = new FindMST(d);
		output += "Graph #1: undirected" + "\n";
		output += d + "\n";
		output += "MST according to Prim's algorithm: \n" + mstD.prim() + "\n";
		output += mstD.getPrimOperationCount() + "\n";
		output += "MST according to Kruskal's algorithm: \n" + mstD.kruskal() + "\n";
		output += mstD.getKruskalOperationCount() + "\n\n";

		// graph fabricated by me
		AdjacencyList e = new AdjacencyList("graphE.txt",false);
		FindMST mstE = new FindMST(e);
		output += "Graph #2: undirected" + "\n";
		output += e + "\n";
		output += "MST according to Prim's algorithm: \n" + mstE.prim() + "\n";
		output += mstE.getPrimOperationCount() + "\n";
		output += "MST according to Kruskal's algorithm: \n" + mstE.kruskal() + "\n";
		output += mstE.getKruskalOperationCount() + "\n\n";

		// graph fabricated by me
		AdjacencyList f = new AdjacencyList("graphF.txt",false);
		FindMST mstF = new FindMST(f);
		output += "Graph #3: undirected" + "\n";
		output += f + "\n";
		output += "MST according to Prim's algorithm: \n" + mstF.prim() + "\n";
		output += mstF.getPrimOperationCount() + "\n";
		output += "MST according to Kruskal's algorithm: \n" + mstF.kruskal() + "\n";
		output += mstF.getKruskalOperationCount() + "\n\n";

		// graph below taken from: http://konect.uni-koblenz.de/networks/moreno_kangaroo
		AdjacencyList g = new AdjacencyList("moreno_kangaroo/out.moreno_kangaroo_kangaroo",false);
		FindMST mstG = new FindMST(g);
		output += "Graph #4: undirected" + "\n";
		output += g + "\n";
		output += "MST according to Prim's algorithm: \n" + mstG.prim() + "\n";
		output += mstG.getPrimOperationCount() + "\n";
		output += "MST according to Kruskal's algorithm: \n" + mstG.kruskal() + "\n";
		output += mstG.getKruskalOperationCount() + "\n\n";

		// graph below taken from: http://konect.uni-koblenz.de/networks/moreno_bison
		AdjacencyList b = new AdjacencyList("moreno_bison/out.moreno_bison_bison",false);
		FindMST mstB = new FindMST(b);
		output += "Graph #5: undirected" + "\n";
		output += b + "\n";
		output += "MST according to Prim's algorithm: \n" + mstB.prim() + "\n";
		output += mstB.getPrimOperationCount() + "\n";
		output += "MST according to Kruskal's algorithm: \n" + mstB.kruskal() + "\n";
		output += mstB.getKruskalOperationCount() + "\n\n";

		// print out the results of the experiment to the terminal
		System.out.println(output);

		// store experiment results in a text file
		try
		{
			String name = "Experiment_Results.txt";
			File file = new File(name);
			FileWriter writer = new FileWriter(file);
			writer.write(output);
			writer.flush();
			writer.close();
		}
		catch (IOException err)
		{
			System.out.println("Error: Something unexpected happened. You're welcome!");
		}
	}
}