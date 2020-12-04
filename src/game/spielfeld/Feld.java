package game.spielfeld;

import java.awt.Point;

public abstract class Feld {

	public Point position;
	public int number;


	 protected Feld(Point position, int nr){
		this.number = nr;
		 this.position = position;
	 }



}