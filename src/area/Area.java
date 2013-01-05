package area;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JPanel;

import node.Node;

@SuppressWarnings("serial")
public class Area extends JPanel implements Runnable, MouseListener, KeyListener {
	
	private ArrayList<Node> nodes;
	
	private Comparator<Node> comparatorX;
	private Comparator<Node> comparatorY;
	
//	private int groups;
	private Random random;
	private Image offscreen;
	private volatile Thread thread = null;
	
	private static final int NUMBER_OF_NODES = 100;
	
	public Area () {
		this.setPreferredSize(new Dimension(500,500));
		this.random = new Random();
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
	}
	
	public Area (Dimension size) {
		this();
		this.setPreferredSize(size);
		createNodes(NUMBER_OF_NODES);
	}
		
	public void createNodes(int max) {
		this.nodes = new ArrayList<Node>();
		
		this.comparatorX = new Comparator<Node>() {  
			public int compare(Node node1, Node node2) {
				if ((node1.getPosition().x) < (node2.getPosition().x))
					return -1;
				else if ((node1.getPosition().x) > (node2.getPosition().x)) 
					return +1;
				else
					return 0; 
			}
		};

		this.comparatorY = new Comparator<Node>() {  
			public int compare(Node node1, Node node2) {
				if ((node1.getPosition().y) < (node2.getPosition().y))
					return -1;
				else if ((node1.getPosition().y) > (node2.getPosition().y)) 
					return +1;
				else
					return 0; 
			}
		};
		
		for (int i = 0; i < max; i++) {
			int px = (random.nextInt() % this.getPreferredSize().width);
			int py = (random.nextInt() % this.getPreferredSize().height);
			
			if (px < 0)	px *= -1;
			if (py < 0)	py *= -1;
			
			nodes.add(new Node(px,py));
		}
		
		Collections.sort (nodes, comparatorX);
		
		this.startAnimation();
	}
		
	public void drawNodes(Graphics2D g) {
		g.setColor(new Color(250,250,250));
				
		for (Node n1 : nodes) {
			int p1x = n1.getPosition().x;
			int p1y = n1.getPosition().y;
			int visi1 = n1.getVisibility();

			ArrayList<Node> area = new ArrayList<Node>();
			ArrayList<Node> axisY = new ArrayList<Node>();

			int index = nodes.indexOf(n1);
			int i = 1;
			
			boolean next = !(index + i > (nodes.size() - 1));
			boolean prev = !(index - i < 0);
			
			axisY.add(nodes.get(index));
			
			while (next || prev) {
				if (next) {
					int distance = Math.abs(nodes.get(index + i).getPosition().x - nodes.get(index).getPosition().x);
					int visibility = Math.min(nodes.get(index + i).getVisibility(), nodes.get(index).getVisibility());
					
					if ( distance <= visibility) {
						axisY.add(nodes.get(index + i));
						next = !((index + i + 1) > (nodes.size() - 1));
					} else
						next = false;
				}
				if (prev) {
					int distance = Math.abs(nodes.get(index - i).getPosition().x - nodes.get(index).getPosition().x);
					int visibility = Math.min(nodes.get(index - i).getVisibility(), nodes.get(index).getVisibility());
					
					if (distance <= visibility) { 
						axisY.add(nodes.get(index - i));
						prev = !((index - i - 1) < 0);
					} else
						prev = false;
				}
				i++;
			}
			
			Collections.sort (axisY, comparatorY);
			index = axisY.indexOf(n1);
			i = 1;
			
			next = !(index + i > (axisY.size() - 1));
			prev = !(index - i < 0);
			
			area.add(axisY.get(index));
			
			while (next || prev) {
				if (next) {
					int distance = Math.abs(axisY.get(index + i).getPosition().y - axisY.get(index).getPosition().y);
					int visibility = Math.min(axisY.get(index + i).getVisibility(), axisY.get(index).getVisibility()) / 2;
					
					if ( distance <= visibility) {
						area.add(axisY.get(index + i));
						next = !((index + i + 1) > (axisY.size() - 1));
					} else
						next = false;
				}
				if (prev) {
					int distance = Math.abs(axisY.get(index - i).getPosition().y - axisY.get(index).getPosition().y);
					int visibility = Math.min(axisY.get(index - i).getVisibility(), axisY.get(index).getVisibility()) / 2;
					
					if ( distance <= visibility) {
						area.add(axisY.get(index - i));
						prev = !((index - i - 1) < 0);
					} else
						prev = false;
				}
				i++;
			}
						
			g.setColor(new Color(0,0,0));
			g.fillOval(n1.getPosition().x - 1, n1.getPosition().y -1 , 2, 2);
			g.drawOval(p1x - (visi1 / 2), p1y - (visi1 / 2), visi1, visi1);

			for (Node n2 : area) {
				int p2x = n2.getPosition().x;
				int p2y = n2.getPosition().y;
				
				double x = Math.pow((p1x - p2x) , 2);
				double y = Math.pow((p1y - p2y) , 2);
				double distance = x + y;

				int visibility = Math.min(n1.getVisibility(), n2.getVisibility());
				
				if ((distance != 0) && (distance <= (visibility * visibility / 4))) {
					n1.createNeighborhood(n2);
					g.drawLine(n1.getPosition().x, n1.getPosition().y, n2.getPosition().x, n2.getPosition().y);
				}
			}
		}
	}
	
	
	public void drawVision(Graphics2D g) {
		for (Node node : nodes) {
			int visibility = node.getVisibility();
			int px = node.getPosition().x;
			int py = node.getPosition().y;
			
			g.setColor(new Color(255,255,255));
			g.fillOval(px - (visibility / 2), py - (visibility / 2), visibility, visibility);
		
		}
	}
	
	public void update(Graphics g) {
		if (offscreen == null )
			offscreen =  createImage(this.getPreferredSize().width, this.getPreferredSize().height);
		else
			offscreen = null;

		if (offscreen == null) return;

		Graphics gg = offscreen.getGraphics();
		gg.setColor(Color.white);
		gg.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);
		paint( gg );
		gg.dispose();
		g.drawImage( offscreen, 0, 0, null );
	}

	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//firula
		drawVision(g2d);
		drawNodes(g2d);
	}

	private void startAnimation() {
		if(this.isAlive())
			this.stop();
		this.start();
	}
	
	public void run() {
		Thread me = Thread.currentThread();
		while(this.thread == me) {
		    step();
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		repaint();
	}
	
	private void step() {
		for (Node n: nodes)
			n.updatePosition(this.getPreferredSize().width, this.getPreferredSize().height);
		Collections.sort (nodes, comparatorX);
	}

	public void start() {
		this.thread = new Thread(this);
		this.thread.setPriority(Thread.MAX_PRIORITY);
		this.thread.start();
	}

	public void stop() {
		this.thread = null;
	}

	public boolean isAlive() {
		if(this.thread == null)
			return false;
		return this.thread.isAlive();
	}
	
	/* Here isn't implemented the transmission velocity of data in the wave */
	public void communication() {
		for (Node node : nodes) {
			if (node.getAction().isSpeaking()) {
				node.getAction().sendMensage();
			}
		}
	}

	public void mouseEntered(MouseEvent e) 	{ }
	public void mouseExited(MouseEvent e) 	{ }
	public void mousePressed(MouseEvent e) 	{ }
	public void mouseReleased(MouseEvent e) { }
	public void keyPressed(KeyEvent e) 		{ }
	public void keyReleased(KeyEvent e)		{ }

	public void mouseClicked(MouseEvent e) {
		System.out.println("x: " + e.getX() + " - y: " + e.getY());
	}
	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'p' )
			this.stop();
		else if (e.getKeyChar() == 's' )
			this.start();
	}
}