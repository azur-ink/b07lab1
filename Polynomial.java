
public class Polynomial {
    double [] coefficients;
    
    public Polynomial() {
    	coefficients = new double[1];
    	coefficients[0] = 0;
    }
    
    public Polynomial(double [] c) {
    	coefficients = new double[c.length];
    	for (int i=0; i<c.length; i++) {
    		coefficients[i] = c[i];
    	}
    }
    
    public Polynomial add(Polynomial p) {
    	if (coefficients.length >= p.coefficients.length) {
    	    for (int i=0; i<p.coefficients.length; i++) {
    	    	coefficients[i] += p.coefficients[i];
    	    }
    	    return new Polynomial(coefficients);
    	}
    	else {
    		for (int i=0; i<coefficients.length; i++) {
    	    	p.coefficients[i] += coefficients[i];
    	    }
    		return new Polynomial(p.coefficients);
    	}
    }
    
    public double evaluate(double x) {
    	double result = 0;
    	double power = 1;
    	for (int i=0; i<coefficients.length; i++) {
    		result += power * coefficients[i];
    		power = power * x;
    	}
    	return result;
    }
    
    public boolean hasRoot(double x) {
    	return this.evaluate(x) == 0;
    }
}