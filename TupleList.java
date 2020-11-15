/******************************************************************************
 *  Compilation: javac TupleList.java
 *
 *  Written by Jeremiah Herr
 *
 *  Wrapper method for an ArrayList of tuples and its methods
 *
 ******************************************************************************/

import java.util.List;
import java.util.ArrayList;

public class TupleList
{
	private List<Tuple> tupleList;

	// construct an empty ArrayList<Tuple>
	public TupleList()
	{
		tupleList = new ArrayList<Tuple>();
	}

	// input integer index
	// return tuple at the specified index
	public Tuple get(int i)
	{
		return tupleList.get(i);
	}

	// input tuple object
	// add input tuple to ArrayList<Tuple>
	public void add(Tuple t)
	{
		tupleList.add(t);
	}

	// input tuple object
	// remove input tuple from ArrayList<Tuple>
	public void remove(Tuple t)
	{
		tupleList.remove(t);
	}

	// return number of tuples in ArrayList<Tuple>
	public int size()
	{
		return tupleList.size();
	}

	// input tuple object
	// return true if ArrayList<Tuple> contains input tuple
	// return false otherwise
	public boolean contains(Tuple t)
	{
		for (int i = 0; i < tupleList.size(); i++)
		{
			Tuple p = tupleList.get(i);
			if (t.equals(p)) return true;
		}
		return false;
	}

	// return true if ArrayList<Tuple> is empty
	// return false otherwise
	public boolean isEmpty()
	{
		return tupleList.isEmpty();
	}

	// return String representation of ArrayList<Tuple>
	public String toString()
	{
		return tupleList.toString();
	}

	// test client for debugging
	public static void main(String[] args)
	{
		TupleList tuples = new TupleList();
		System.out.println(tuples.isEmpty());
		System.out.println(tuples.size());
		Tuple<String,Integer> t = new Tuple<String,Integer>("A",0);
		Tuple<String,Integer> p = new Tuple<String,Integer>("B",0);
		Tuple<String,Integer> u = new Tuple<String,Integer>("A",0);
		tuples.add(t);
		System.out.println(tuples.isEmpty());
		System.out.println(tuples.size());
		System.out.println(tuples);
		System.out.println(tuples.contains(u));
		System.out.println(tuples.contains(p));
		tuples.add(p);
		System.out.println(tuples);
		tuples.remove(t);
		System.out.println(tuples);
	}
}