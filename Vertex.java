/******************************************************************************
 *  Compilation: javac Vertex.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines a Vertex ADT and its methods
 *
 ******************************************************************************/

public class Vertex
{
	private String name;

	// input String
	// construct a vertex object
	public Vertex(String name)
	{
		this.name = name;
	}

	// override toString()
	public String toString()
	{
		return name;
	}

	// return name of vertex as a String
	public String getName()
	{
		return name;
	}

	// override equals(Object o)
	public boolean equals(Object o)
	{
		if (o == this) return true;
		if (!(o instanceof Vertex)) return false;
		Vertex vert = (Vertex) o;
		return name.equals(vert.getName());
	}

	// override hashCode()
	public int hashCode()
	{
		final int prime = 31;
		int result = 17;
		result = prime * result + name.hashCode();
		return result;
	}

	// test client for debugging
	public static void main(String[] args)
	{
		Vertex v = new Vertex("A");
		Vertex w = new Vertex("a");
		System.out.println(v);
		System.out.println(v.getName());
		System.out.println(v.equals(w));
		Vertex u = new Vertex("A");
		System.out.println(v.equals(u));
	}
}