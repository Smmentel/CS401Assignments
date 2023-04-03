public class CardData {
	/** Object to represent card information */
	String cardNum, exp;

	public CardData(String cardNum, String exp) {
		this.cardNum = cardNum;
		this.exp = exp;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public String getExp() {
		return this.exp;
	}
}