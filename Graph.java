/******************************************************************************
 *  Compilation: javac Graph.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines a Graph ADT and its methods
 *
 ******************************************************************************/

public class Graph
{
	private EdgeList E;
	private VertexList V;
	private boolean digraph;

	// input a VertexList, EdgeList, and a boolean
	// input true if graph is directed
	// input false otherwise
	// construct a graph object as a list of edges and vertices
	public Graph(VertexList V, EdgeList E, boolean digraph)
	{
		this.V = V;
		this.E = E;
		this.digraph = digraph;
	}

	// return list of vertices in graph
	public VertexList getVertices()
	{
		return V;
	}

	// return list of edges in graph
	public EdgeList getEdges()
	{
		return E;
	}

	// override toString()
	public String toString()
	{
		String string = "V = " + V.toString();
		string += "\nE = [";
		for (int i = 0; i < E.size(); i++)
		{
			Edge e = E.get(i);
			if (i > 0) string += " ";
			string += e.toString();
			if (i < E.size()-1) string += ",";
		}
		string += "]";
		return string;
	}

	// input vertex object
	// return a list of all vertices adjacent to input vertex
	public VertexList getAdjacent(Vertex vertex)
	{
		VertexList adjacent = new VertexList();
		for (int i = 0; i < E.size(); i++)
		{
			Edge e = E.get(i);
			Vertex adj = e.getAdjacent(vertex);
			if (adj != null) adjacent.add(adj);
		}
		return adjacent;
	}

	// input vertex object
	// return a list of all vertices adjacent to input vertex
	// and the weight of the edge connecting adjacent vertices
	public TupleList getAdjacent_Weighted(Vertex vertex)
	{
		TupleList adjacent = new TupleList();
		for (int i = 0; i < E.size(); i++)
		{
			Edge e = E.get(i);
			Vertex adj = e.getAdjacent(vertex);
			if (adj != null) 
			{
				double weight = e.getWeight();
				Tuple<Vertex,Double> tuple = new Tuple<Vertex,Double>(adj,weight);
				adjacent.add(tuple);
			}
		}
		return adjacent;
	}

	// return true if graph is weighted
	// return false otherwise
	public boolean isWeighted()
	{
		for (int i = 0; i < E.size(); i++)
		{
			Edge e = E.get(i);
			if (e.getWeight() > 0) return true;
		}
		return false;
	}

	// return list of edges in graph
	// such that for every edge = (v0,v1), there is also an edge = (v1,v0)
	public EdgeList mirrorAll()
	{
		EdgeList undirected = new EdgeList();
		if (!digraph)
		{
			for (int i = 0; i < E.size(); i++)
			{
				Edge edge = E.get(i);
				undirected.add(edge);
				undirected.add(edge.mirror());
			}
		}
		return undirected;
	}

	// return true if graph is directed
	// return false otherwise
	public boolean isDigraph()
	{
		return digraph;
	}

	// test client for debugging
	public static void main(String[] args)
	{
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Vertex v2 = new Vertex("C");
		VertexList verts = new VertexList();
		verts.add(v0);
		verts.add(v1);
		verts.add(v2);

		Edge e1 = new Edge(v0,v1);
		Edge e2 = new Edge(v1,v2);
		Edge e3 = new Edge(v2,v0);
		EdgeList edgs = new EdgeList();
		edgs.add(e1);
		edgs.add(e2);
		edgs.add(e3);

		Graph g = new Graph(verts,edgs,true);
		System.out.println(g);
		System.out.println();

		System.out.println(g.getAdjacent(v0));
		System.out.println(g.isWeighted());
		System.out.println(g.getAdjacent_Weighted(v0));
		System.out.println(g.isWeighted());

		Edge e4 = new Edge(v0,v1,5);
		Edge e5 = new Edge(v1,v2,9.1);
		EdgeList eds = new EdgeList();
		eds.add(e4);
		eds.add(e5);
		Graph h = new Graph(verts,eds,true);
		System.out.println(h.isWeighted());
	}
}