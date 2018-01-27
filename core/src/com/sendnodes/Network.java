package com.sendnodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sendnodes.entities.Player;
import com.sendnodes.nodes.Connection;
import com.sendnodes.nodes.Node;

public class Network {

	private ArrayList<Node> nodes;
	private ArrayList<Connection> connections;
	
	private Node[][] node_grid;
	private int size;
	private Random r = new Random();
	
	public void addConnection(int x1, int y1, int x2, int y2){
		Connection conn = new Connection(node_grid[x1][y1], node_grid[x2][y2], 1);
		node_grid[x1][y1].addConnection(conn);
		node_grid[x2][y2].addConnection(conn);
		connections.add(conn);
	}
	
	public Network(int size) {
		this.size = size;
		nodes = new ArrayList<Node>();
		connections = new ArrayList<Connection>();
		
		node_grid = new Node[size][size];
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				node_grid[x][y] = new Node(x, y);
			}
		}
		
		// Now all nodes are interconnected, prune connections and nodes to generate the map.
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				if (r.nextInt(2) == 1) {
					// 50% chance to remove a node and its connections.
					for (Connection null_conn : node_grid[x][y].getConnections())
						null_conn.getOtherNode(node_grid[x][y]).removeConnection(null_conn);
					node_grid[x][y] = null;
				}
			}
		}
		
		// Empty the nodes generated into the ArrayList.
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				if (node_grid[x][y] != null)
					nodes.add(node_grid[x][y]);
			}
		}
		
		
		for (int x=0; x < size; x++) {
			for (int y=0; y < size; y++) {
				// for each default node that is not on the outside, generate adjacent connections 66% of the time.
				
				if (node_grid[x][y]!=null){
					if (x > 0 && y > 0 && x < size-1 && y < size-1) {
	//					if (r.nextInt(3) != 1) {
	//						addConnection(x, y, x+1, y);
	//					}
	//					if (r.nextInt(3) != 1) {
	//						addConnection(x, y, x-1, y);
	//					}
	//					
	//					if (r.nextInt(3) != 1) {
	//						addConnection(x, y, x, y+1);
	//					}
	//					if (r.nextInt(3) != 1) {
	//						addConnection(x, y, x, y-1);
	//					}
						
						
						if (r.nextInt(3) != 1) {
							if (node_grid[x-1][y-1]!=null)
								addConnection(x, y, x-1, y-1);
						}
						if (r.nextInt(3) != 1) {
							if (node_grid[x+1][y-1]!=null)
								addConnection(x, y, x+1, y-1);
						}
	
						if (r.nextInt(3) != 1) {
							if (node_grid[x+1][y+1]!=null)
								addConnection(x, y, x+1, y+1);
						}		
	
						if (r.nextInt(3) != 1) {
							if (node_grid[x-1][y+1]!=null)
								addConnection(x, y, x-1, y+1);
						}		
					}
				}
				
			}
		}
		
		
	}
	
	// use for starting node
	public Node getRandomNode() {
		return nodes.get(r.nextInt(nodes.size()));
	}
	
	public boolean isConnected(Player player, Node node1, Node node2) {
        List<Node> queue = new ArrayList<Node>();
        
        queue.add( node1 ); // start from here
        boolean pathExists = false;
        
        while (!queue.isEmpty()) {
            Node current = queue.remove(0);
            
            if (current == node2) {
                pathExists = true;
                break;
            }
            
            for (Connection conn : current.getConnections()) {
            	Node other = conn.getOtherNode(current);
            	if (other.getOwner() == player) {
            		other.getPathBuilder().addAll(current.getPathBuilder());
            		other.getPathBuilder().add(current);
            		queue.add(other);
            	}
            	else if (other == node2) {
            		pathExists = true;
                    break;
            	}
            }
        }
        
        return pathExists;
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
	
	public boolean pipeProblem(Player player, Node node1, Node node2) {
		if (!isConnected(player, node1, node2))
			return false;
		
		
		return true;
	}
	
	public int getMapSize() {
		return size;
	}

	public Node[][] getMap() {
		return node_grid;
	}
	
	public ArrayList<Node> getMapNodesList() {
		return nodes;
	}
	
}
