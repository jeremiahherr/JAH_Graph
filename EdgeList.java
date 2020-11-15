/******************************************************************************
 *  Compilation: javac EdgeList.java
 *
 *  Written by Jeremiah Herr
 *
 *  Wrapper method for an ArrayList of edges and its methods
 *
 ******************************************************************************/

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class EdgeList
{
	private List<Edge> edgeList;

	// construct an empty ArrayList<Edge>
	public EdgeList()
	{
		edgeList = new ArrayList<Edge>();
	}

	// input integer index
	// return edge at the specified index
	public Edge get(int i)
	{
		return edgeList.get(i);
	}

	// input edge object
	// add input edge to ArrayList<Edge>
	public void add(Edge e)
	{
		edgeList.add(e);
	}

	// input edge object
	// remove input edge from ArrayList<Edge>
	public void remove(Edge e)
	{
		edgeList.remove(e);
	}

	// return number of edges in ArrayList<Edge>
	public int size()
	{
		return edgeList.size();
	}

	// input edge object
	// return true if ArrayList<Edge> contains input edge
	// return false otherwise
	public boolean contains(Edge e)
	{
		for (int i = 0; i < edgeList.size(); i++)
		{
			Edge t = edgeList.get(i);
			if (e.equals(t)) return true;
		}
		return false;
	}

	// input edge object = (v0,v1)
	// return true if ArrayList<Edge> contains an edge = (v1,v0)
	// return false otherwise
	public boolean hasMirror(Edge e)
	{
		Edge edge = e.mirror();
		if (edgeList.contains(edge)) return true;
		return false;
	}

	// input Comparator<Edge> object
	// sort ArrayList<Edge> with Timsort
	public void sort(Comparator<Edge> c)
	{
		edgeList.sort(c);
	}

	// return true if ArrayList<Edge> is empty
	// return false otherwise
	public boolean isEmpty()
	{
		return edgeList.isEmpty();
	}

	// return String representation of ArrayList<Edge>
	public String toString()
	{
		return edgeList.toString();
	}

	// test client for debugging
	public static void main(String[] args)
	{
		EdgeList eds = new EdgeList();
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("a");
		Edge edge = new Edge(a,b);
		Vertex w = new Vertex("A");
		Vertex u = new Vertex("a");
		Edge e = new Edge(w,u);
		Edge mirror = new Edge(b,a);
		eds.add(edge);
		eds.add(mirror);
		System.out.println(eds.contains(e));
		System.out.println(eds.hasMirror(edge));
		EdgeList eds2 = new EdgeList();
		eds2.add(edge);
		System.out.println(eds2.hasMirror(edge));
	}
}