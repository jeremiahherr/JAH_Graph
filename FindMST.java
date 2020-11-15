/******************************************************************************
 *  Compilation: javac FindMST.java
 *  Execution java FindMST
 *
 *  Written by Jeremiah Herr
 *
 *  Implementation of Prim's algorithm and Kruskal's algorithm
 *  for finding the MST of a given graph represented as an adjacency list
 *
 ******************************************************************************/

public class FindMST
{
	private AdjacencyList graph;
	private String primOps = "";
	private String kruskalOps = "";

	// constructor for creating a FindMST object
	// the object stores an adjacency list representation of a graph
	// then you can find MST's for the input graph by calling methods
	// that implement Prim's or Kruskal's algorithms
	public FindMST(AdjacencyList graph)
	{
		this.graph = graph;
	}

	// an implementation of Prim's algorithm for finding an mst of a graph
	public AdjacencyList prim()
	{
		int compareOps = 0; // count number of comparison operations performed
		int insertOps = 0; // count number of insertion operations performed
		int deleteOps = 0; // count number of deletion operations performed
		int searchOps = 0; // count number of search operations performed
		int totalOps; // count total number of operations performed by this implemenation of Prim's algorithm

		if (!primApplies(graph)) // check whether the input graph is supported by this implementation of Prim's algorithm
		{
			System.out.println("The input graph is not weighted and connected");
			AdjacencyList empty = new AdjacencyList();
			return empty;
		}
		else // otherwise perform Prim's algorithm on the input graph
		{
			VertexList vertices = graph.getVertices(); // grab the set of vertices, V, from the input graph
			VertexList have = new VertexList(); // create a list of vertices, H, that we have visited so far
			TupleList adjacent = new TupleList(); // create a list of vertices and associated weights, A, that are adjacent to some vertex in H
			TupleList pieces = new TupleList(); // create a list of edge pieces as we find them
			Vertex v0 = vertices.get(0); // choose a starting vertex, v0
			while (have.size() < vertices.size()) // keep going until we have every vertex in our mst
			{
				compareOps++; // count the compare operation that occured on the previous line
				searchOps++; // count the search operation that occurs on the next line
				if (!have.contains(v0)) // if the current vertex is not in H
				{
					insertOps++; // count the insertion operation that occurs on the next line
					have.add(v0); // add v0 to H
					TupleList adj = graph.getWeightedAdjacent(v0); // grab the list of vertices and weights, a, adjacent to v0
					for (int j = 0; j < adj.size(); j++) 
					{
						Tuple tuple = adj.get(j);
						Vertex v = (Vertex) tuple.get1();
						searchOps++; // count the search operation that occurs on the next line
						if (!have.contains(v))
						{
							insertOps++; // count the insertion operation that occurs on the next line
							adjacent.add(tuple); // add each vertex in a and not in H to A
						}
					}
				}
				Tuple min = getMinimum(adjacent); // choose a vertex, v1, adjacent to v0 such that it forms the minimum edge that is incident on v0
				deleteOps++; // count the deletion operation that occurs on the next line
				adjacent.remove(min); // remove v1 from A
				Vertex v1 = (Vertex) min.get1(); // represent v1 as a vertex object (instead of as a tuple object)
				searchOps++; // count the search operation that occurs on the next line
				if (!have.contains(v1)) // if v1 is not in H
				{
					insertOps++; // count the insertion operation that occurs on the next line
					pieces.add(min); // add grabbed edge piece to list
					v0 = v1; // set v1 as the new current vertex, v0
				}
			}
			EdgeList edges = graph.getEdges(); // grab the set of edges, E, from input graph
			EdgeList mst = new EdgeList(); // create an array of weights that are in the mst
			for (int i = 0; i < pieces.size(); i++)
			{
				// dissect each edge piece from our list
				Tuple tuple = pieces.get(i);
				Vertex v = (Vertex) tuple.get1();
				double weight = (Double) tuple.get2();
				for (int j = 0; j < edges.size(); j++)
				{
					// find an edge in E that contains our edge piece
					// and then add that edge to our mst
					Edge edge = edges.get(j);
					double ew = edge.getWeight();
					searchOps += 2; // count the search operation that occurs on the next next line
					compareOps++; // count the compare operation that occurs on the next line
					if (edge.contains(v) && (weight == ew) && !mst.contains(edge)) 
					{
						insertOps++; // count the insertion operation that occurs on the next line
						mst.add(edge);
						break;
					}
				}
			}
			// get total number of operations performed
			totalOps = compareOps + insertOps + deleteOps + searchOps;
			String s = "Prim's algorithm operation count:" + "\n";
			s += "# of comparison operations: " + compareOps + "\n";
			s += "# of insertion operations: " + insertOps + "\n";
			s += "# of deletion operations: " + deleteOps + "\n";
			s += "# of search operations: " + searchOps + "\n";
			s += "# of total operations: " + totalOps + "\n";
			primOps = s;
			boolean digraph = graph.isDigraph();
			// create the adjacency list representation of our mst
			AdjacencyList mstAL = new AdjacencyList(have,mst,digraph);
			return mstAL;
		}
	}

	// helper method to check whether the input graph is supported by this implementation of Prim's algorithm
	private boolean primApplies(AdjacencyList graph)
	{
		return graph.isConnected() && graph.isWeighted();
	}

	// helper method for Prim's algorithm that chooses a vertex, v1,
	// adjacent to v0 such that it forms the minimum edge that is incident on v0
	private Tuple getMinimum(TupleList adjacents)
	{
		Tuple min = adjacents.get(0); // choose some vertex, v0, in A and the weight of the edge that places v0 in A
		double minWeight = (Double) min.get2(); // set the chosen weight as the minimum weight found so far
		for (int i = 1; i < adjacents.size(); i++) // loop through every other vertex in A
		{
			Tuple next = adjacents.get(i); // choose another vertex, v1, in A and the weight of the edge that places v1 in A
			if (minWeight > (Double) next.get2()) // if the weight of v1 > the minimum weight so far
			{
				minWeight = (Double) next.get2(); // set the weight of v1 as the new minimum weight so far
				min = next; // set v1 as the vertex associated with the minimum weight found so far
			}
		}
		return min;
	}

	// an implementation of Kruskal's algorithm for finding an mst of a graph
	public AdjacencyList kruskal()
	{
		int acyclicOps = 0; // count number of operations performed to determine whether a graph is acyclic
		int compareOps = 0; // count number of comparison operations performed
		int insertOps = 0; // count number of insertion operations performed
		int deleteOps = 0; // count number of deletion operations performed
		int totalOps; // count total number of operations performed by this implemenation of Kruskal's algorithm

		if (!kruskalApplies(graph)) // check whether the input graph is supported by this implementation of Kruskal's algorithm
		{
			System.out.println("The input graph is not weighted");
			AdjacencyList empty = new AdjacencyList();
			return empty;
		}
		else // otherwise perform Kruskal's algorithm on the input graph
		{
			EdgeList mst = new EdgeList(); // create a list of edges that have been added to our mst
			AdjacencyList mstAL; // declare an adjacency list representation of our mst
			EdgeList edges = graph.getEdges(); // grab the set of edges, E, from the input graph
			VertexList vertices = graph.getVertices(); // grab the set of vertices, V, from the input graph
			boolean digraph = graph.isDigraph();
			int n = vertices.size(); // let n = number of vertices in the input graph
			edges.sort(Edge.cmp); // sort E by weight in ascending order
			for (int i = 0; i < edges.size(); i++) // loop through every edge in E
			{
				// NOTE: any mst has n-1 edges
			    compareOps++; // count the compare operation that occurs on the next line
				if (mst.size() == (n-1)) break; // if our mst has (n-1) edges, break out of the loop
				Edge min = edges.get(i); // grab the edge with the next smallest weight
				insertOps++; // count the insertion operation that occurs on the next line
				mst.add(min); // add the edge to our mst
				mstAL = new AdjacencyList(vertices,mst,digraph); // represent our mst so far as an adjacency list
				int e = mst.size(); // get number of edges in our mst so far
				int m = n + e; // let m = number of operations performed by one DFS traversal
				if (!mstAL.isAcyclic()) 
				{
					acyclicOps += m+1; // estimate number of operations assuming the algorithm finds a cycle after performing one DFS
					// NOTE: the above calculation will under-estimate the actual number of DFS traversals
					deleteOps++; // count the deletion operation that occurs on the next line
					mst.remove(min); // if adding this edge creates a cycle, remove it from our mst
				}
				else
				{
					acyclicOps += n*(m+1); // estimate number of operations assuming the algorithm performs a DFS for every vertex in V
				}
			}
			totalOps = compareOps + insertOps + deleteOps + acyclicOps; // get total number of operations performed
			String s = "Kruskal's algorithm operation count:" + "\n";
			s += "# of comparison operations: " + compareOps + "\n";
			s += "# of insertion operations: " + insertOps + "\n";
			s += "# of deletion operations: " + deleteOps + "\n";
			s += "# of operations to determine acyclicicty: " + acyclicOps + "\n";
			s += "# of total operations: " + totalOps + "\n";
			kruskalOps = s;
			mstAL = new AdjacencyList(vertices,mst,digraph); // represent our final mst as an adjacency list
			return mstAL;
		}
	}

	// helper method to check whether the input graph is supported by this implementation of Kruskal's algorithm
	private boolean kruskalApplies(AdjacencyList graph)
	{
		return graph.isWeighted();
	}

	// return data on prim's algorithm performance
	public String getPrimOperationCount()
	{
		return primOps;
	}

	// return data on kruskal's algorithm performance
	public String getKruskalOperationCount()
	{
		return kruskalOps;
	}

	// test client for debugging
	public static void main(String[] args)
	{
		AdjacencyList a = new AdjacencyList("graphA.txt",true);
		System.out.println(a);
		FindMST mst = new FindMST(a);
		mst.prim();
		mst.kruskal();

		AdjacencyList d = new AdjacencyList("graphD.txt",false);
		System.out.println(d);
		FindMST mstD = new FindMST(d);
		System.out.println("MST of d according to prim: \n" + mstD.prim() + "\n");
		System.out.println("MST of d according to kruskal: \n" + mstD.kruskal() + "\n");

		AdjacencyList e = new AdjacencyList("graphE.txt",false);
		System.out.println(e);
		FindMST mstE = new FindMST(e);
		System.out.println("MST of e according to prim: \n" + mstE.prim() + "\n");
		System.out.println("MST of e according to kruskal: \n" + mstE.kruskal() + "\n");

		AdjacencyList f = new AdjacencyList("graphF.txt",false);
		System.out.println(f);
		FindMST mstF = new FindMST(f);
		System.out.println("MST of f according to prim: \n" + mstF.prim() + "\n");
		System.out.println("MST of f according to kruskal: \n" + mstF.kruskal() + "\n");
	}
}