package overheat.app;


public class Cards{
	private String _id;
	private String name;
	private String number;
	private String face;
	private String back;
	/**
     * @return the card's name
     */
    public String get_id() {
        return _id;
    }

    /**
     * @param name the name to set
     */
    public void set_id(String _id) {
        this._id = _id;
    }
	/**
     * @return the card's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param filename
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @return the age
     */
    public String getFace() {
        return face;
    }

    /**
     * @param age the age to set
     */
    public void setFace(String face) {
        this.face = face;
    }
    /**
     * @return the age
     */
    public String getBack() {
        return back;
    }

    /**
     * @param age the age to set
     */
    public void setBack(String back) {
        this.back = back;
    }
    @Override
    public String toString()
    {
        return name + ":" +number;
    }
}