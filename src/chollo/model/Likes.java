package chollo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Likes {
	
	private long idu;
	
	private long idc;
	
	private long like;

	public long getIdu() {
		return idu;
	}

	public void setIdu(long idu) {
		this.idu = idu;
	}

	public long getIdc() {
		return idc;
	}

	public void setIdc(long idc) {
		this.idc = idc;
	}

	public long getLike() {
		return like;
	}

	public void setLike(long like) {
		this.like = like;
	}

}
