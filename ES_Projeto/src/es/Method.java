package es;

/**
 * 
 * Classe que contém o conteúdo de uma linha da tabela relativa ao ficheiro excel
 *
 */
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

	/**
	 * Construtor vazio da classe method
	 */
	public Method() {}

	/**
	 * Construtor da classe Method, cria um objeto onde cada atributo corresponde ao valor de uma célula da tabela relativa ao ficheiro Excel
	 * @param id Contém o id do método do ficheiro excel
	 * @param ppackage Contém o package do método do ficheiro excel
	 * @param cclass Contém a classe do método do ficheiro excel
	 * @param name Contém o nome do método do ficheiro excel
	 * @param loc Contém o valor da métrica loc do método do ficheiro excel
	 * @param cyclo Contém o valor da métrica cyclo do método do ficheiro excel
	 * @param atfd Contém o valor da métrica atfd do método do ficheiro excel
	 * @param laa Contém o valor da métrica laa do método do ficheiro excel
	 * @param l_m Contém o valor do campo is_long_method do método do ficheiro excel
	 * @param iPlasma Contém o valor do campo iPlasma do método do ficheiro excel
	 * @param pmd Contém o valor do campo PMD do método do ficheiro excel
	 * @param f_e Contém o valor do campo is_feature_envy do método do ficheiro excel
	 */
	public Method(int id, String ppackage, String cclass, String name, int loc, int cyclo, int atfd, double laa, boolean l_m,
			boolean iPlasma, boolean pmd, boolean f_e) {
		
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

	/**
	 *
	 * Método que faz a verificação se o objeto que chama este método é Long Method segundo os valores e a lógica definidos pelo utilizador na 
	 * interface gráfica relativa à criação de uma nova regra.
	 * @param loc Valor da métrica loc definido pelo utilizador
	 * @param cyclo Valor da métrica cyclo definido pelo utilizador
	 * @param symbol Valor que indica se foi escolhido maior, menor, ou nenhum deles para o valor da métrica loc
	 * @param symbol2 Valor que indica se foi escolhido maior, menor, ou nenhum deles para o valor da métrica cyclo
	 * @param condition Valor que indica se foi escolhido e, ou, ou nenhum deles para a conjugação dos valores das métricas loc e cyclo
	 * @return Valor booleano Verdadeiro ou Falso que depende do conteúdo da regra e dos valores de loc e cyclo do método
	 */
	public boolean  is_long_method(int loc,int cyclo, Symbol symbol, Symbol symbol2, Condition condition) {
		boolean m=false;
		if(condition ==Condition.AND) {
			if(symbol==Symbol.MAIOR) {
				if(symbol2==Symbol.MAIOR) {
					if(this.loc>loc && this.cyclo>cyclo) 
						m=true;
				}else{
					if(this.loc>loc && this.cyclo<cyclo) 
						m=true;
				}
			}else{
				if(symbol2==Symbol.MAIOR) {
					if(this.loc<loc && this.cyclo>cyclo) 
						m=true;
				}else{
					if(this.loc<loc && this.cyclo<cyclo) 
						m=true;
				}
			}
		}else {
			if(symbol==Symbol.MAIOR) {
				if(this.loc>loc) m=true;
			}else{
				if(this.loc>loc) m=true;
			}
			if(symbol2==Symbol.MAIOR) {
				if(this.cyclo>cyclo) m=true;
			}else{
				if(this.cyclo>cyclo) m=true;
			}
		}
		return m;
	}

	/**
	 * Método que faz a verificação se o objeto que chama este método é Feature Envy segundo os valores e a lógica definidos pelo utilizador na 
	 * interface gráfica relativa à criação de uma nova regra.
	 * @param atfd Valor da métrica atfd definido pelo utilizador
	 * @param laa Valor da métrica laa definido pelo utilizador
	 * @param symbol Valor que indica se foi escolhido maior, menor, ou nenhum deles para o valor da métrica atfd
	 * @param symbol2 Valor que indica se foi escolhido maior, menor, ou nenhum deles para o valor da métrica laa
	 * @param condition  Valor que indica se foi escolhido e, ou, ou nenhum deles para a conjugação dos valores das métricas atfd e laa
	 * @return Valor booleano Verdadeiro ou Falso que depende do conteúdo da regra e dos valores de atfd e laa do método
	 */
	public boolean isF_e_rule(int atfd, double laa, Symbol symbol, Symbol symbol2, Condition condition) {
		boolean m=false;
		if(condition ==Condition.AND) {
			if(symbol==Symbol.MAIOR) {
				if(symbol2==Symbol.MAIOR) {
					if(this.atfd>atfd && this.laa>laa) m=true;
				}else{
					if(this.atfd>atfd && this.laa<laa) {
						m=true;
					}
				}
			}else {
				if(symbol2==Symbol.MAIOR) {
					if(this.atfd<atfd && this.laa>laa) m=true;
				}else{
					if(this.atfd<atfd && this.laa<laa) m=true;
				}
			}
		}else {
			if(symbol==Symbol.MAIOR) {
				if(this.atfd>atfd) m=true;
			}else{
				if(this.atfd>atfd) m=true;
			}
			if(symbol2==Symbol.MAIOR) {
				if(this.laa>laa) m=true;
			}else{
				if(this.laa>laa) m=true;
			}
		}
		return m;
	}

	/**
	 * Getter do atributo id 
	 * @return Valor do atributo id
	 */
	public int getId() {
		return id;
	}

	/**
	* Setter do atributo id
	* @param id Valor que o atributo id irá tomar
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter do atributo ppackage
	 * @return Valor do atributo ppackage 
	 */
	public String getPpackage() {
		return ppackage;
	}

	/**
	 * Setter do atributo ppackage 
	 * @param ppackage Valor que o atributo ppackage irá tomar
	 */
	public void setPpackage(String ppackage) {
		this.ppackage = ppackage;
	}

	/**
	 * Getter do atributo cclass
	 * @return Valor do atributo cclass
	 */
	public String getCclass() {
		return cclass;
	}

	/**
	 * Setter do atributo cclass
	 * @param cclass Valor que o atributo cclass irá tomar
	 */
	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	/**
	 * Getter do atributo name
	 * @return Valor do atributo name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter do atributo name
	 * @param name Valor que o atributo name irá tomar
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter do atributo loc
	 * @return Valor do atributo loc
	 */
	public int getLoc() {
		return loc;
	}

	/**
	 * Setter do atributo loc
	 * @param loc Valor que o atributo loc irá tomar
	 */
	public void setLoc(int loc) {
		this.loc = loc;
	}

	/**
	 * Getter do atributo cyclo
	 * @return Valor do atributo cyclo
	 */
	public int getCyclo() {
		return cyclo;
	}

	/**
	 * Setter do atributo cyclo
	 * @param cyclo Valor que o atributo cyclo irá tomar
	 */
	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}

	/**
	 * Getter do atributo atfd
	 * @return Valor do atributo atfd
	 */
	public int getAtfd() {
		return atfd;
	}

	/**
	 * Setter do atributo atfd
	 * @param atfd Valor que o atributo atfd irá tomar
	 */
	public void setAtfd(int atfd) {
		this.atfd = atfd;
	}

	/**
	 * Getter do atributo laa
	 * @return Valor do atributo laa
	 */
	public double getLaa() {
		return laa;
	}

	/**
	 * Setter do atributo laa, segundo o valor de uma String após Parsing para double do mesmo
	 * @param laa Valor que o atributo laa irá tomar
	 */
	public void setLaa(String laa) {
		this.laa = Double.parseDouble(laa);
	}

	/**
	 * Setter do atributo laa, segundo o valor de um double
	 * @param laa Valor que o atributo laa irá tomar
	 */
	public void setLaa(double laa) {
		this.laa = laa;
	}

	/**
	 * Getter do atributo l_m
	 * @return Valor do atributo l_m
	 */
	public boolean isL_m() {
		return l_m;
	}

	/**
	 * Setter do atributo l_m
	 * @param l_m Valor que o atributo l_m irá tomar
	 */
	public void setL_m(boolean l_m) {
		this.l_m = l_m;
	}

	/**
	 * Getter do atributo iPlasma
	 * @return Valor do atributo iPlasma
	 */
	public boolean isiPlasma() {
		return iPlasma;
	}
	
	/**
	 * Setter do atributo iPlasma
	 * @param iPlasma Valor que o atributo iPlasma irá tomar
	 */
	public void setiPlasma(boolean iPlasma) {
		this.iPlasma = iPlasma;
	}
	
	/**
	 * Getter do atributo pmd
	 * @return Valor do atributo pmd
	 */
	public boolean isPmd() {
		return pmd;
	}

	/**
	 * Setter do atributo pmd
	 * @param pmd Valor que o atributo pmd irá tomar
	 */
	public void setPmd(boolean pmd) {
		this.pmd = pmd;
	}

	/**
	 * Getter do atributo f_e
	 * @return Valor do atributo f_e
	 */
	public boolean isF_e() {
		return f_e;
	}

	/**
	 * Setter do atributo f_e
	 * @param f_e Valor que o atributo f_e irá tomar
	 */
	public void setF_e(boolean f_e) {
		this.f_e = f_e;
	}




}
