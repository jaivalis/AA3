package agent;

import util.Coordinates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AgentsCollection implements Iterable<Agent>, Iterator<Agent> {
	public ArrayList<Predator> predators = new ArrayList<>();
	public ArrayList<Prey> preys = new ArrayList<>();
	private Iterator<Predator> predatorsIt = null;
	private Iterator<Prey> preysIt = null;

    /** Default constructor */
    public AgentsCollection() { }

    public AgentsCollection(Prey prey, ArrayList<Predator> predators) {
        this.preys = new ArrayList<>();
        this.preys.add(prey);
        this.predators = predators;
    }

    /**
     * Sets the predators coordinates to random values.
     */
    public void randomize() {
        for (Predator p : this.predators) {
            p.setCoordinates(new Coordinates());
        }
    }

    public ArrayList<Coordinates> getPredatorsCoordinates() {
        ArrayList<Coordinates> ret = new ArrayList<>();
        for (Predator p: this.predators) {
            ret.add(p.getCoordinates());
        } return ret;
    }

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
