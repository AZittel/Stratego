package game.spielfiguren;


public enum SpielstueckTyp {
	Fahne(1),
	Spion(1),
	Aufklärer(8),
	Mineur(5),
	Unteroffizier(4),
	Leutnant(4),
	Hauptmann(4),
	Major(3),
	Oberst(2),
	General(1),
	Feldmarschall(1),
	Bombe(6);

	private int maxAmount;
	private int wertigkeit;

	/**
	 * 
	 * @param maxAmount
	 */
	private SpielstueckTyp(int maxAmount) {
		this.maxAmount = maxAmount;
		this.wertigkeit = this.ordinal();
	}

	public int getWertigkeit() {
		return wertigkeit;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

}