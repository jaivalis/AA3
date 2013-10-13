package agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AgentsCollection implements Iterable<Agent>, Iterator<Agent>
{
	public ArrayList<Predator> predators = new ArrayList<Predator>();
	public ArrayList<Prey> preys = new ArrayList<Prey>();
	private Iterator<Predator> predatorsIt = null;
	private Iterator<Prey> preysIt = null;
	
	@Override
	public Iterator<Agent> iterator() {
		this.predatorsIt = predators.iterator();
		this.preysIt = preys.iterator();
		return this;
	}

	@Override
	public boolean hasNext() {
		return this.preysIt.hasNext() || this.predatorsIt.hasNext();
	}

	@Override
	public Agent next() {
		if(this.preysIt.hasNext()){
			return this.preysIt.next();
		}
		if(this.predatorsIt.hasNext()){
			return this.predatorsIt.next();
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub	
	}
}
