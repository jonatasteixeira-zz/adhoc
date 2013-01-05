package node;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;



public class Node {
	private Point position;
	private ArrayList<Node> neighbor;
	private String name;
	private Random random;
	private int visibility;
	private int movement;
	private int dirx = 0;
	private int diry = 0;
	private Action action;



	public Node () {
		this.position = new Point();
		this.neighbor = new ArrayList<Node>();
		this.random = new Random();
		this.action = new Action();
		this.name = new String();
		this.visibility = 100;
		this.movement = this.random.nextInt(500) + 10;
	}

	public Node (int x, int y) {
		this();
		this.position.setLocation(x, y);
	}

	public Node (String name) {
		this();
		this.setName(name);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public ArrayList<Node> getNeighbor() {
		return neighbor;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public void createNeighborhood(Node n) {
			this.addNeighbor(n);
			n.addNeighbor(this);
	}
	
	public void destroyNeighborhood(Node n) {
			this.removeNeighbor(n);
			n.removeNeighbor(this);
	}
	
	public void removeNeighbor(Node n) {
		if (neighbor.contains(n))
			this.neighbor.remove(n);
	}

	public void addNeighbor(Node n) {
		if (! neighbor.contains(n))
			this.neighbor.add(n);
	}


	public void updatePosition(int sizeScreenx, int sizeScreeny) {
		int movex = random.nextInt(this.movement);
		int movey = random.nextInt(this.movement);

		if (movex == 1)
			dirx = 1;
		else if (movex == 2)
			dirx = -1;
		else if (movex == 3)
			dirx = 0;

		if (movey == 1)
			diry = 1;
		else if (movey == 2)
			diry = -1;
		else if (movey == 3)
			diry = 0;

		if ((this.position.x + dirx >= 0) && (this.position.x + dirx <= sizeScreenx))
			this.position.x += dirx;

		if ((this.position.y + diry >= 0) && (this.position.y + diry <= sizeScreeny))
			this.position.y += diry;

		updateNeighborhood();
	}

	public void updateNeighborhood() {
		for(int i = 0; i < neighbor.size(); i++) {
			// distance = sqrt(a² + b²);
			int px = neighbor.get(i).getPosition().x;
			int py = neighbor.get(i).getPosition().y;
			int visibility = Math.max(neighbor.get(i).getVisibility(), this.visibility);
			
			double x = Math.pow((px - this.position.x) , 2);
			double y = Math.pow((py - this.position.y) , 2);
			double distance = x + y;
			
			if ((distance > (visibility * visibility / 4)))
				neighbor.remove(i);
		}

		
	}
}





