package es;

public class Method {

	private int id;
	private String ppackage;
	private String cclass;
	private String name;
	private int loc;
	private int cyclo;
	private int atfd;
	private double laa;
	private boolean l_m;
	private boolean iPlasma;
	private boolean pmd;
	private boolean f_e;

	public Method() {}

	public Method(int id, String ppackage, String cclass, String name, int loc, int cyclo, int atfd, double laa, boolean l_m,
			boolean iPlasma, boolean pmd, boolean f_e) {
		super();
		this.id = id;
		this.ppackage = ppackage;
		this.cclass = cclass;
		this.name = name;
		this.loc = loc;
		this.cyclo = cyclo;
		this.atfd = atfd;
		this.laa = laa;
		this.l_m = l_m;
		this.iPlasma = iPlasma;
		this.pmd = pmd;
		this.f_e = f_e;
	}

	public boolean  is_long_method(int loc,int cyclo) {
		boolean m=false;
		if(this.loc>loc && this.cyclo>cyclo) {
			m=true;
		}

	return m;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPpackage() {
		return ppackage;
	}

	public void setPpackage(String ppackage) {
		this.ppackage = ppackage;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getCyclo() {
		return cyclo;
	}

	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}

	public int getAtfd() {
		return atfd;
	}

	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}

	public double getLaa() {
		return laa;
	}

	public void setLaa(String laa) {
		this.laa = Double.parseDouble(laa);
	}

	public void setLaa(double laa) {
		this.laa = laa;
	}

	public boolean isL_m() {
		return l_m;
	}

	public void setL_m(boolean l_m) {
		this.l_m = l_m;
	}

	public boolean isiPlasma() {
		return iPlasma;
	}

	public void setiPlasma(boolean iPlasma) {
		this.iPlasma = iPlasma;
	}

	public boolean isPmd() {
		return pmd;
	}

	public void setPmd(boolean pmd) {
		this.pmd = pmd;
	}

	public boolean isF_e() {
		return f_e;
	}

	public void setF_e(boolean f_e) {
		this.f_e = f_e;
	}




}
