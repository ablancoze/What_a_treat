package chollo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChollosCategory {

	private long idc;
	private long idct;
	
	public long getIdc() {
		return idc;
	}
	
	public void setIdc(long idc) {
		this.idc = idc;
	}
	
	public long getIdct() {
		return idct;
	}
	
	public void setIdct(long idct) {
		this.idct = idct;
	}
}
