//Deck is shuffled and made
//Boards are ready

public class Card{
    private String m_num;
    private String m_suit;
    private String m_color;
    private boolean foundSomething;
    private String faced;

    public Card(int rank, int suit){
        this.setNum(rank);
        this.setSuit(suit);
        this.setColor(getSuit());
        this.faced = "OO";
    }

    public void setNum(int rank) {
        switch (rank) {
	case 0:
	    m_num = "A";
	    break;
	case 1:
	    m_num = "2";
	    break;
	case 2:
	    m_num = "3";
	    break;
	case 3:
	    m_num = "4";
	    break;
	case 4:
	    m_num = "5";
	    break;
	case 5:
	    m_num = "6";
	    break;
	case 6:
	    m_num = "7";
	    break;
	case 7:
	    m_num = "8";
	    break;
	case 8:
	    m_num = "9";
	    break;
	case 9:
	    m_num = "T";
	    break;
	case 10:
	    m_num = "J";
	    break;
	case 11:
	    m_num = "Q";
	    break;
	case 12:
	    m_num = "K";
	    break;
        }
    }

    public void setSuit(int suit) {
        switch(suit){
	case 0:
	    m_suit= "D";
	    break;
	case 1:
	    m_suit = "H";
	    break;
	case 2:
	    m_suit = "S";
	    break;
	case 3:
	    m_suit = "C";
	    break;
        }
    }

    public void setColor(String suit) {
        if(this.m_suit == "D" || this.m_suit == "H")
            m_color = "Red";
        else
            m_color = "Black";
    }

    public String getNum() {
        return m_num;
    }

    public String getSuit() {
        return m_suit;
    }

    public String getColor() {
        return m_color;
    }

    public void setFoundSomething(boolean foundSomething) {
        this.foundSomething = foundSomething;
    }

    public boolean isFoundSomething() {
        return foundSomething;
    }

    public String getFaced() {
        return faced;
    }

    public void setFaced(String faced) {
        this.faced = faced;
    }

    public String toString(){
        return m_num + m_suit;
    }

    public String display(){
        if(foundSomething)
            return toString();
        else
            return faced;
    }
}
