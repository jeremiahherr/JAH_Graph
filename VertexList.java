/******************************************************************************
 *  Compilation: javac VertexList.java
 *
 *  Written by Jeremiah Herr
 *
 *  Wrapper method for an ArrayList of vertices and its methods
 *
 ******************************************************************************/

import java.util.List;
import java.util.ArrayList;

public class VertexList
{
	private List<Vertex> vertexList;

	// construct an empty ArrayList<Vertex>
	public VertexList()
	{
		vertexList = new ArrayList<Vertex>();
	}

	// input integer index
	// return vertex at the specified index
	public Vertex get(int i)
	{
		return vertexList.get(i);
	}

	// input vertex object
	// add input vertex to ArrayList<Vertex>
	public void add(Vertex v)
	{
		vertexList.add(v);
	}

	// input vertex object
	// remove input vertex from ArrayList<Vertex>
	public void remove(Vertex v)
	{
		vertexList.remove(v);
	}

	// return number of vertices in ArrayList<Vertex>
	public int size()
	{
		return vertexList.size();
	}

	// input vertex object
	// return true if ArrayList<Vertex> contains input vertex
	// return false otherwise
	public boolean contains(Vertex v)
	{
		for (int i = 0; i < vertexList.size(); i++)
		{
			Vertex w = vertexList.get(i);
			if (v.equals(w)) return true;
		}
		return false;
	}

	// return true if ArrayList<Vertex> is empty
	// return false otherwise
	public boolean isEmpty()
	{
		return vertexList.isEmpty();
	}

	// return String representation of ArrayList<Vertex>
	public String toString()
	{
		return vertexList.toString();
	}

	// test client for debugging
	public static void main(String[] args)
	{
		VertexList vertices = new VertexList();
		Vertex v = new Vertex("A");
		Vertex w = new Vertex("a");
		Vertex u = new Vertex("A");
		Vertex y = new Vertex("C");
		System.out.println(vertices.isEmpty());
		vertices.add(v);
		vertices.add(w);
		System.out.println(vertices.isEmpty());
		System.out.println(vertices.size());
		System.out.println(vertices.contains(u));
		System.out.println(vertices.contains(v));
		System.out.println(vertices.contains(y));
		System.out.println(vertices);
	}
}