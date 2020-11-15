/******************************************************************************
 *  Compilation: javac Edge.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines an Edge ADT and its methods
 *
 ******************************************************************************/

import java.util.Comparator;

public class Edge
{
	private Vertex v0;
	private Vertex v1;
	private double weight;

	// input two vertex objects
	// construct an unweighted Edge object
	public Edge(Vertex v0, Vertex v1)
	{
		this.v0 = v0;
		this.v1 = v1;
		weight = 0;
	}

	// input two vertex objects and a double
	// construct a weighted Edge object
	public Edge(Vertex v0, Vertex v1, double weight)
	{
		this.v0 = v0;
		this.v1 = v1;
		this.weight = weight;
	}
	
	// override toString()
	public String toString()
	{
		String s = "";
		if (weight == 0) 
			s = "(" + v0 + "," + v1 + ")";
		else
			s = "(" + v0 + " -> " + weight + " -> " + v1 + ")";
		return s;
	}

	// get the source vertex of edge
	public Vertex getVertex0()
	{
		return v0;
	}

	// get the destination vertex of edge
	public Vertex getVertex1()
	{
		return v1;
	}

	// get the weight of edge
	public Double getWeight()
	{
		return weight;
	}

	// override equals(Object o)
	public boolean equals(Object o)
	{
		if (o == this) return true;
		if (!(o instanceof Edge)) return false;
		Edge edge = (Edge) o;
		boolean v = v0.equals(edge.getVertex0());
		boolean w = v1.equals(edge.getVertex1());
		boolean wei = weight == edge.getWeight();
		return v && w && wei;
	}

	// override hashCode()
	public int hashCode()
	{
		final int prime = 31;
		int result = 17;
		Double w = (Double) weight;
		result = prime * result + v0.hashCode();
		result = prime * result + v1.hashCode();
		result = prime * result + w.hashCode();
		return result;
	}

	// input a vertex object
	// return true if edge is incident on the input vertex
	// return false otherwise
	public boolean contains(Vertex vertex)
	{
		if (vertex.equals(v0) || vertex.equals(v1)) return true;
		else return false;
	}

	// input a vertex object
	// return true if in the input vertex is the source vertex of edge
	// return false otherise
	public boolean hasAdjacent(Vertex vertex)
	{
		if (this.contains(vertex))
		{
			Vertex v0 = this.getVertex0();
			if (v0.equals(vertex)) return true;
			else return false;
		}
		else return false;
	}

	// input a vertex object
	// return the destination vertex of edge if input vertex is the source vertex
	// return null otherwise
	public Vertex getAdjacent(Vertex vertex)
	{
		if (this.hasAdjacent(vertex)) return this.getVertex1();
		else return null;
	}

	// let this edge = (v0,v1)
	// return another edge = (v1,v0)
	public Edge mirror()
	{
		Vertex v2 = this.getVertex1();
		Vertex v3 = this.getVertex0();
		double w = this.getWeight();
		Edge edge = new Edge(v2,v3,w);
		return edge;
	}

	// implement Comparator interface for edges
	public static Comparator<Edge> cmp = new Comparator<Edge>()
	{
		public int compare(Edge e1, Edge e2)
		{
			double w1 = e1.getWeight();
			double w2 = e2.getWeight();
			return Double.compare(w1,w2);
		}
	};

	// test client for debugging
	public static void main(String[] args)
	{
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("a");
		Edge edge = new Edge(a,b);
		System.out.println(edge);
		System.out.println(edge.mirror());
		System.out.println(edge.getVertex0() + " " + edge.getVertex1());
		Vertex v = new Vertex("A");
		Vertex w = new Vertex("C");
		System.out.println(edge.contains(v) + " " + edge.contains(w));
		System.out.println(edge.hasAdjacent(a));
		System.out.println(edge.getAdjacent(a));
		System.out.println(edge.hasAdjacent(b));
		System.out.println(edge.getAdjacent(b));
		
		System.out.println(edge.getWeight());
		Edge weightedEdge = new Edge(v,w,25);
		System.out.println(weightedEdge);
		System.out.println(weightedEdge.mirror());

		Edge e = new Edge(a,w);
		Edge y = new Edge(b,w);
		Edge t = new Edge(a,b);
		System.out.println(e.equals(y));
		System.out.println(edge.equals(t));
	}
}