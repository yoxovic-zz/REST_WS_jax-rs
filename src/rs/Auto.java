package rs;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "auto_id", "marka", "model", "godiste", "kubikaza", "boja", "cena"  })
public class Auto {
	private int auto_id;
	private String marka;
	private String model;
	private int godiste;
	private float kubikaza;
	private String boja;
	private float cena;
	
	public Auto() {
		super();
	}

	public Auto(int auto_id, String marka, String model, int godiste, float kubikaza, String boja, float cena) {
		super();
		this.auto_id = auto_id;
		this.marka = marka;
		this.model = model;
		this.godiste = godiste;
		this.kubikaza = kubikaza;
		this.boja = boja;
		this.cena = cena;
	}


	@XmlElement
	public int getAuto_id() {
		return auto_id;
	}

	public void setAuto_id(int auto_id) {
		this.auto_id = auto_id;
	}
	
	@XmlElement
	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	@XmlElement
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@XmlElement
	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	
	@XmlElement
	public float getKubikaza() {
		return kubikaza;
	}

	public void setKubikaza(float kubikaza) {
		this.kubikaza = kubikaza;
	}
	
	@XmlElement
	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}
	
	@XmlElement
	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

}