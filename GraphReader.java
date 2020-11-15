/******************************************************************************
 *  Compilation: javac GraphReader.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines set of static methods for reading in graphs from data files
 *
 ******************************************************************************/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GraphReader
{
	// input file as a String
	// read file data as a graph and return a list of edges
	private static EdgeList getEdges(String txt)
	{
		try
		{
			EdgeList E = new EdgeList();
			File file = new File(txt);
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				String s = sc.nextLine();
				String[] tokens = s.split(" ");
				if (tokens[0].equals("%")) continue;
				else if (tokens.length == 2)
				{
					Vertex v0 = new Vertex(tokens[0]);
					Vertex v1 = new Vertex(tokens[1]);
					Edge edge = new Edge(v0,v1);
					E.add(edge);
				}
				else if (tokens.length == 3)
				{
					Vertex v0 = new Vertex(tokens[0]);
					Vertex v1 = new Vertex(tokens[1]);
					double weight = Double.parseDouble(tokens[2]);
					Edge edge = new Edge(v0,v1,weight);
					E.add(edge);
				}
				else continue;
			}
			return E;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: File not found");
			EdgeList E = new EdgeList();
			return E;
		}
	}

	// input file as a String
	// read file data as a graph and return a list of vertices
	private static VertexList getVertices(String txt)
	{
		try
		{
			VertexList V = new VertexList();
			File file = new File(txt);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) 
			{
				String s = sc.nextLine();
				String[] tokens = s.split(" ");
				if (tokens[0].equals("%")) continue;
				else if (tokens.length == 2)
				{
					for (int i = 0; i < tokens.length; i++)
					{
						Vertex vertex = new Vertex(tokens[i]);
						if (!V.contains(vertex)) V.add(vertex);
					}
				}
				else if (tokens.length == 3)
				{
					for (int i = 0; i < tokens.length-1; i++)
					{
						Vertex vertex = new Vertex(tokens[i]);
						if (!V.contains(vertex)) V.add(vertex);
					}
				}
			}
			return V;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: File not found");
			VertexList V = new VertexList();
			return V;
		}
	}

	// input file as a String and a boolean
	// input true if graph from file is directed
	// input false otherwise
	// return graph object from file data
	public static Graph makeGraph(String txt, boolean digraph)
	{
		EdgeList edges = getEdges(txt);
		VertexList vertices = getVertices(txt);
		Graph graph = new Graph(vertices,edges,digraph);
		return graph;
	}

	// test client for debugging
	public static void main(String[] args)
	{
		EdgeList E = getEdges("graphA.txt");
		System.out.println(E);
		VertexList V = getVertices("graphA.txt");
		System.out.println(V);
		Graph g = makeGraph("graphA.txt",true);
		System.out.println(g);
		System.out.println();
		Graph h = makeGraph("graphD.txt",false);
		System.out.println(h);
		EdgeList edges = h.getEdges();
		System.out.println("\nUnsorted edges: " + edges);
		edges.sort(Edge.cmp);
		System.out.println("\nSorted edges: " + edges);
	}
}