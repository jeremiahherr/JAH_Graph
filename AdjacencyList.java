/******************************************************************************
 *  Compilation: javac AdjacencyList.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines an Adjacency List ADT and its methods
 *
 ******************************************************************************/

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class AdjacencyList
{
	private Map<Vertex,VertexList> adjacencyList;
	private Map<Vertex,TupleList> weightedList;
	private VertexList vertices;
	private EdgeList edges;
	private boolean weighted;
	private boolean digraph;

	// input the name of a file as a String
	// file must store graph data
	// also input a boolean
	// input true if input graph is directed
	// input false if input graph is undirected
	// construct an adjacency list representation of a graph
	public AdjacencyList(String txt, boolean digraph)
	{
		this.digraph = digraph;
		Graph graph = GraphReader.makeGraph(txt,digraph);
		vertices = graph.getVertices();
		weighted = graph.isWeighted();
		edges = graph.getEdges();
		if (!digraph)
		{
			EdgeList undirected = graph.mirrorAll();
			graph = new Graph(vertices,undirected,digraph);
		}
		if (!weighted)
		{
			adjacencyList = new HashMap<Vertex,VertexList>();
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vertex = vertices.get(i);
				VertexList adjacent = graph.getAdjacent(vertex);
				adjacencyList.put(vertex,adjacent);
			}
		}
		else
		{
			weightedList = new HashMap<Vertex,TupleList>();
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vertex = vertices.get(i);
				TupleList adjacent = graph.getAdjacent_Weighted(vertex);
				weightedList.put(vertex,adjacent);
			}
		}
	}

	// input a VertexList and an EdgeList of a graph
	// also input a boolean
	// input true if input graph is directed
	// input false if input graph is undirected
	// construct an adjacency list representation of a graph
	public AdjacencyList(VertexList V, EdgeList E, boolean digraph)
	{
		vertices = V;
		edges = E;
		this.digraph = digraph;
		Graph graph = new Graph(V,E,digraph);
		weighted = graph.isWeighted();
		if (!digraph)
		{
			EdgeList undirected = graph.mirrorAll();
			graph = new Graph(vertices,undirected,digraph);
		}
		if (!weighted)
		{
			adjacencyList = new HashMap<Vertex,VertexList>();
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vertex = vertices.get(i);
				VertexList adjacent = graph.getAdjacent(vertex);
				adjacencyList.put(vertex,adjacent);
			}
		}
		else
		{
			weightedList = new HashMap<Vertex,TupleList>();
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vertex = vertices.get(i);
				TupleList adjacent = graph.getAdjacent_Weighted(vertex);
				weightedList.put(vertex,adjacent);
			}
		}
	}

	// constructor for declaring an empty AdjacencyList object
	public AdjacencyList()
	{

	}

	// override toString()
	public String toString()
	{
		String string = "";
		if (!weighted)
		{
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vert = vertices.get(i);
				VertexList adj = adjacencyList.get(vert);
				string += vert.getName();
				string += " -> ";
				string += adj.toString();
				string += "\n";
			}
		}
		else
		{
			for (int i = 0; i < vertices.size(); i++)
			{
				Vertex vert = vertices.get(i);
				TupleList adj = weightedList.get(vert);
				string += vert.getName();
				string += " -> ";
				string += adj.toString();
				string += "\n";
			}
		}
		return string;
	}

	// helper method to get the degree of a particular vertex
	private int getDegree(Vertex vertex)
	{
		if (!weighted)
		{
			VertexList adjacent = adjacencyList.get(vertex);
			return adjacent.size();
		}
		else
		{
			TupleList adjacent = weightedList.get(vertex);
			return adjacent.size();
		}
	}

	// return an integer array containing the degree of every vertex
	public int[] getAllDegrees()
	{
		int[] degrees = new int[vertices.size()];
		for (int i = 0; i < vertices.size(); i++)
		{
			Vertex vertex = vertices.get(i);
			degrees[i] = this.getDegree(vertex);
		}
		return degrees;
	}

	// return the sum of all the vertex degrees
	public int getDegreeSum()
	{
		int sum = 0;
		for (int i = 0; i < vertices.size(); i++)
		{
			Vertex vertex = vertices.get(i);
			sum += this.getDegree(vertex);
		}
		return sum;
	}

	// Depth-First-Search
	// input starting vertex for traversal and integer
	// input 0 returns list of vertices that were visited in pop order
	// input 1 return list of vertices in push order
	// input any other integer to return list of vertices with back edges
	public VertexList DFS(Vertex v, int choose)
	{
		VertexList visited = new VertexList();
		VertexList pushed = new VertexList();
		VertexList backEdges = new VertexList();
		Vertex previous = null;
		Stack<Vertex> stack = new Stack<Vertex>();
		if (!weighted)
		{
			stack.push(v);
			pushed.add(v);
			while (!stack.isEmpty())
			{
				v = stack.pop();
				//System.out.println("Pop " + v);
				if (!visited.contains(v))
				{
					visited.add(v);
					if (visited.size() > 1) 
					{
						previous = visited.get(visited.size()-2);
						//System.out.println("Previous vertex: " + previous);
					}
					VertexList adj = adjacencyList.get(v);
					for (int i = 0; i < adj.size(); i++)
					{
						Vertex w = adj.get(i);
					 	if (!visited.contains(w) && !pushed.contains(w))
						{
							stack.push(w);
							pushed.add(w);
							//System.out.println("Push " + w);
						}
						else if (!visited.contains(w) && !w.equals(previous))
						{
							//System.out.println(w + " has a back edge at " + v);
							backEdges.add(w);
						}
					}
				}
			}
		}
		else
		{
			stack.push(v);
			pushed.add(v);
			while (!stack.isEmpty())
			{
				v = stack.pop();
				//System.out.println("Pop " + v);
				if (!visited.contains(v))
				{
					visited.add(v);
					if (visited.size() > 1) 
					{
						previous = visited.get(visited.size()-2);
						//System.out.println("Previous vertex: " + previous);
					}
					TupleList adj = weightedList.get(v);
					for (int i = 0; i < adj.size(); i++)
					{
						Vertex w = (Vertex)(adj.get(i)).get1();
						if (!visited.contains(w) && !pushed.contains(w))
						{
							stack.push(w);
							pushed.add(w);
							//System.out.println("Push " + w);
						}
						else if (!visited.contains(w) && !w.equals(previous))
						{
							//System.out.println(w + " has a back edge at " + v);
							backEdges.add(w);
						}
					}
				}
			}
		}
		if (choose == 0) return visited;
		else if (choose == 1) return pushed;
		else return backEdges;
	}

	// Breadth-First-Search
	// input starting vertex for traversal and integer
	// input 0 returns list of vertices that were visited in dequeue order
	// input 1 returns list of vertices in enqueue order
	// input any other integer to return list of vertices with cross edges
	public VertexList BFS(Vertex v, int choose)
	{
		VertexList visited = new VertexList();
		VertexList enqueued = new VertexList();
		VertexList crossEdges = new VertexList();
		Queue<Vertex> q = new Queue<Vertex>();
		if (!weighted)
		{
			q.enqueue(v);
			enqueued.add(v);
			while (!q.isEmpty())
			{
				v = q.dequeue();
				//System.out.println("Dequeue " + v);
				if (!visited.contains(v))
				{
					visited.add(v);
					VertexList adj = adjacencyList.get(v);
					for (int i = 0; i < adj.size(); i++)
					{
						Vertex w = adj.get(i);
						if (!visited.contains(w)) 
						{
							q.enqueue(w);
							enqueued.add(w);
							//System.out.println("Enqueue " + w);
						}
					}
				}
			}
		}
		else
		{
			q.enqueue(v);
			enqueued.add(v);
			while (!q.isEmpty())
			{
				v = q.dequeue();
				if (!visited.contains(v))
				{
					visited.add(v);
					TupleList adj = weightedList.get(v);
					for (int i = 0; i < adj.size(); i++)
					{
						Vertex w = (Vertex)(adj.get(i)).get1();
						if (!visited.contains(w)) 
						{
							q.enqueue(w);
							enqueued.add(w);
						}
					}
				}
			}
		}
		if (choose == 0) return visited;
		else if (choose == 1) return enqueued;
		else return crossEdges;
	}
	
	// return true if graph is connected
	// return false otherwise
	public boolean isConnected()
	{
		for (int i = 0; i < vertices.size(); i++)
		{
			Vertex v = vertices.get(i);
			VertexList traversal = this.BFS(v,0);
			for (int j = 0; j < vertices.size(); j++)
			{
				Vertex vert = vertices.get(j);
				if (!traversal.contains(vert)) return false;
			}
		}
		return true;
	}

	// return true of graph is acyclic
	// return false otherwise
	public boolean isAcyclic()
	{
		for (int i = 0; i < vertices.size(); i++)
		{
			Vertex v = vertices.get(i);
			VertexList traversal = this.DFS(v,2);
			if (!traversal.isEmpty()) return false;
		}
		return true;
	}

	// return true if graph is weighted
	// return false otherwise
	public boolean isWeighted()
	{
		return weighted;
	}

	// return true if graph is directed
	// return false otherwise
	public boolean isDigraph()
	{
		return digraph;
	}

	// return the list of vertices in the graph
	public VertexList getVertices()
	{
		return vertices;
	}

	// return the list of edges in the graph
	public EdgeList getEdges()
	{
		return edges;
	}

	// return list of vertices adjacent to input vertex
	public VertexList getAdjacent(Vertex vertex)
	{
		return adjacencyList.get(vertex);
	}

	// return list of vertices (and their associated weights) adjacent to input vertex
	public TupleList getWeightedAdjacent(Vertex vertex)
	{
		return weightedList.get(vertex);
	}

	// test client for debugging
	public static void main(String[] args)
	{
		AdjacencyList a = new AdjacencyList("graphA.txt",true);
		System.out.println(a);
		System.out.println("Graph a is connected: " + a.isConnected());
		System.out.println("Graph a is weighted: " + a.isWeighted());
		System.out.println("Graph a is digraph: " + a.isDigraph());
		System.out.println("Graph a is acyclic: " + a.isAcyclic() + "\n");

		AdjacencyList b = new AdjacencyList("graphB.txt",false);
		System.out.println(b);
		System.out.println("Graph b is connected: " + b.isConnected());
		System.out.println("Graph b is weighted: " + b.isWeighted());
		System.out.println("Graph b is digraph: " + b.isDigraph());
		System.out.println("Graph b is acyclic: " + b.isAcyclic() + "\n");

		AdjacencyList c = new AdjacencyList("graphC.txt",false);
		System.out.println(c);
		System.out.println("Graph c is connected: " + c.isConnected());
		System.out.println("Graph c is weighted: " + c.isWeighted());
		System.out.println("Graph c is digraph: " + c.isDigraph());
		System.out.println("Graph c is acyclic: " + c.isAcyclic() + "\n");

		AdjacencyList d = new AdjacencyList("graphD.txt",false);
		System.out.println(d);
		System.out.println("Graph d is connected: " + d.isConnected());
		System.out.println("Graph d is weighted: " + d.isWeighted());
		System.out.println("Graph d is digraph: " + d.isDigraph());
		System.out.println("Graph d is acyclic: " + d.isAcyclic() + "\n");

		AdjacencyList e = new AdjacencyList("graphE.txt",false);
		System.out.println(e);
		System.out.println("Graph e is connected: " + e.isConnected());
		System.out.println("Graph e is weighted: " + e.isWeighted());
		System.out.println("Graph e is digraph: " + e.isDigraph());
		System.out.println("Graph e is acyclic: " + e.isAcyclic() + "\n");

		AdjacencyList usa = new AdjacencyList("contiguous-usa.dat.txt",false);
		System.out.println(usa);
		System.out.println("Graph usa is connected: " + usa.isConnected());
		System.out.println("Graph usa is weighted: " + usa.isWeighted());
		System.out.println("Graph usa is digraph: " + usa.isDigraph());
		System.out.println("Graph usa is acyclic: " + usa.isAcyclic() + "\n");

		System.out.println(a.getDegreeSum());
		System.out.println(b.getDegreeSum());
		System.out.println(c.getDegreeSum());
		System.out.println(d.getDegreeSum());
		System.out.println();
		System.out.println(Arrays.toString(a.getAllDegrees()));
		System.out.println(Arrays.toString(b.getAllDegrees()));
		System.out.println(Arrays.toString(c.getAllDegrees()));
		System.out.println(Arrays.toString(d.getAllDegrees()));
	}
}