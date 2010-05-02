package cs585_hw3.team33.browse.list;

import java.io.Serializable;


public class Result implements Serializable {
	private static final long serialVersionUID = -8254321738401760890L;
	
	public int id;
	public int x,y;
	public String text;
	
	public Result(int id, int x, int y, String text) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.text = text;
	}
}
