/******************************************************************************
 *  Compilation: javac Tuple.java
 *
 *  Written by Jeremiah Herr
 *
 *  Defines a Tuple ADT and its methods
 *
 ******************************************************************************/

public class Tuple<First,Second>
{
	private First first;
	private Second second;

	// input two objects
	// construct a Tuple object
	public Tuple(First first, Second second)
	{
		this.first = first;
		this.second = second;
	}

	// return the first object in tuple
	public First get1()
	{
		return first;
	}

	// return the second object in tuple
	public Second get2()
	{
		return second;
	}

	// override toString()
	public String toString()
	{
		String string = "(" + first + ", " + second + ")";
		return string;
	}

	// override equlas(Object o)
	public boolean equals(Object o)
	{
		if (o == this) return true;
		if (!(o instanceof Tuple)) return false;

		Tuple t = (Tuple) o;
		First f = this.get1();
		Second s = this.get2();
		if (f.equals(t.get1()) && s.equals(t.get2())) return true;
		else return false;
	}

	// override hashCode()
	public int hashCode()
	{
		final int prime = 31;
		int result = 17;
		result = prime * result + first.hashCode();
		result = prime * result + second.hashCode();
		return result;
	}

	// test client for debugging
	public static void main(String[] args)
	{
		Tuple<String,Integer> tuple = new Tuple<String,Integer>("A",0);
		Tuple<String,Integer> p = new Tuple<String,Integer>("B",0);
		Tuple<String,Integer> u = new Tuple<String,Integer>("A",0);
		System.out.println(tuple.get1() + " " + tuple.get2());
		System.out.println(tuple);
		System.out.println(tuple.equals(p) + " " + tuple.equals(u));
		System.out.println(tuple.hashCode() + " " + p.hashCode() + " " + u.hashCode());
	}
}