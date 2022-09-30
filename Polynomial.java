import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
    double [] coefficients;
    int [] exponents;
    
    public Polynomial() {
    	coefficients = null;
    	exponents = null;
    }
    
    public Polynomial(double [] c, int [] e) {
    	coefficients = new double[c.length];
    	exponents = new int[c.length];
    	for (int i=0; i<c.length; i++) {
    		coefficients[i] = c[i];
    		exponents[i] = e[i];
    	}
    }
    
    public Polynomial(File f) throws IOException{
    	if (f.exists()) {
    		BufferedReader input = new BufferedReader(new FileReader(f));
    		String line = input.readLine();
    		String [] split = line.split("\\+|\\-");
    		input.close();

    		double [] c = new double[split.length];
    		int [] e = new int[split.length];
    		int indicator;
    		int index;
    		/*for (String x: split) {
    			System.out.println(x);
    		}*/
    		
    		if (split.length == 1 && Double.parseDouble(split[0]) == 0) {
    			new Polynomial();
    		}
    		
    		for (int i=0; i<split.length; i++) {
    			index = line.indexOf(split[i]);
    			//System.out.println(index);
    			if (index == 0 || line.charAt(index - 1) == '+') {
    				indicator = 1;
    			}
    			else{
    				indicator = -1;
    			}
    			
    			if (split[i].indexOf('x') != -1) {
    				//System.out.println("case 1");
    				c[i] = Double.parseDouble(split[i].substring(0, split[i].indexOf('x')));
    				e[i] = Integer.parseInt(split[i].substring(split[i].indexOf('x') + 1));
    				//System.out.println(c[i] + "*x^" + e[i]);
    			}
    			else {
    				c[i] = Double.parseDouble(split[i]) * indicator;
    				e[i] = 0;
    				//System.out.println(c[i] + "*x^" + e[i]);
    			}
    		}
    		
        	coefficients = new double[c.length];
        	exponents = new int[c.length];
        	for (int i=0; i<c.length; i++) {
        		coefficients[i] = c[i];
        		exponents[i] = e[i];
        	}		
    	}
    }
    
    public Polynomial add(Polynomial p) {
    	if (coefficients == null) {
    		return p;
    	}
    	if (p.coefficients == null) {
    		return this;
    	}
    	
    	int temp_len = 0;
    	for (int x: exponents) {
    		temp_len = Math.max(x, temp_len);
    	}
    	for (int x: p.exponents) {
    		temp_len = Math.max(x, temp_len);
    	}
    	temp_len ++;
    	
    	double [] temp_c = new double[temp_len];
    	for (int i=0; i<temp_len; i++) {
    		temp_c[i] = 0;
    	}
    	
    	for (int i=0; i<coefficients.length; i++) {
    		temp_c[exponents[i]] += coefficients[i];
    	}
    	for (int i=0; i<p.coefficients.length; i++) {
    		temp_c[p.exponents[i]] += p.coefficients[i];
    	}
    	
    	int final_len = 0;
    	for (int i=0; i<temp_len; i++) {
    		if (temp_c[i] != 0) {
    			final_len ++;
    		}
    	}
    	
    	double [] c = new double[final_len];
    	int [] e = new int[final_len];
    	int index = 0;
    	for (int i=0; i<temp_len; i++) {
    		if (temp_c[i] != 0) {
    			c[index] = temp_c[i];
    			e[index] = i;
    			index ++;
    		}
    	}
    	
    	Polynomial sum = new Polynomial(c,e);
    	return sum;
    }
    
    public double evaluate(double x) {
    	if (coefficients == null) {
    		return 0;
    	}
    	double result = 0;
    	for (int i=0; i<coefficients.length; i++) {
    		result += coefficients[i] * Math.pow(x, exponents[i]);
    	}
    	return result;
    }
    
    public boolean hasRoot(double x) {
    	if (coefficients == null) {
    		return true;
    	}
    	return this.evaluate(x) == 0;
    }
    
    public Polynomial multiply(Polynomial p) {
    	if (coefficients == null || p.coefficients == null) {
    		return null;
    	}
    	Polynomial result = new Polynomial();
    	double [] temp_c = new double [1];
    	int [] temp_e = new int [1];
    	for (int i=0; i<p.coefficients.length; i++) {
    		for (int j=0; j<coefficients.length; j++) {
    			temp_c[0] = p.coefficients[i] * coefficients[j]; 
    			temp_e[0] = p.exponents[i] + exponents[j];
    			result = result.add(new Polynomial(temp_c, temp_e));
    		}
    	}
    	return result;
    }
    
    public void saveToFile (String fileName) throws IOException {
    	File f = new File(fileName);
    	FileWriter output = new FileWriter(f);
    	if (f.exists()) {
    		if (coefficients == null) {
    			output.write('0');
    			output.close();
    			return;
    		}
    		String line = "";
    		for (int i=0; i<coefficients.length; i++) {
    			if (coefficients[i] > 0 && i != 0 ) {
    				line = line + '+';
    			}
    			if (exponents[i] != 0) {
    			    line = line + coefficients[i] + 'x' + exponents[i];
    			}
    			else {
    				line = line + coefficients[i];
    			}
    		}
    		output.write(line);
    		output.close();
    	}
    }
    
    public void printPolynomial() {
    	if (coefficients == null) {
    		System.out.println(0);
    	}
    	else {
    		String line = "(" + coefficients[0] + ")*x^" + exponents[0];
    		for (int i=1; i<coefficients.length; i++) {
    			line = line + " + (" + coefficients[i] + ")*x^" + exponents[i];
    		}
    		System.out.println(line);
    	}
    }
    
}