package es;

public class Rule {
	
	private String [] metrics;
	private String [] thresholds; //ordered based on vector metrics
	private String [] signals; //ordered based on vector metrics
	private String [] logical; //ordered based on vector metrics, length = length.size -1
	private String  name;
	
	public Rule(String [] metrics, String name, String [] thresholds, String [] signals, String [] logical) {
		this.metrics = metrics;
		this.thresholds = thresholds;
		this.signals = signals;
		this.logical = logical;
		this.name = name;
	}

	public String[] getMetrics() {
		return metrics;
	}

	public String[] getThresholds() {
		return thresholds;
	}

	public String[] getSignals() {
		return signals;
	}

	public String[] getLogical() {
		return logical;
	}

	public String getName() {
		return name;
	}
	
	public String getExpression() {
		String end="";
		for(int i = 0; i < metrics.length; i++) {
			String s = metrics[i] + " " + signals [i] + " " + thresholds[i] + " ";
			if (i < logical.length)
				s= s + logical[i] + " ";
			end = end + s;
			
		}
		return end;
	}
	
}