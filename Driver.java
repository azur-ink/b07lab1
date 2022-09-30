import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	
	public static void main(String [] args) throws IOException {
		Polynomial p = new Polynomial();
	    System.out.println(p.evaluate(3));

	    double [] c1 = {6,5};
	    int [] e1 = {0,3};
	    Polynomial p1 = new Polynomial(c1, e1);
	    double [] c2 = {-2,-9};
	    int [] e2 = {1,4};
	    Polynomial p2 = new Polynomial(c2, e2);
	    Polynomial s = p1.add(p2);	    
	    System.out.println("s(0.1) = " + s.evaluate(0.1));
	    
	    Polynomial m = p1.multiply(p2);
	    System.out.println("m(0.1) = " + m.evaluate(0.1));

	    if(s.hasRoot(1))
	    	System.out.println("1 is a root of s");
	    else
	    	System.out.println("1 is not a root of s");
	    
	    File f1 = new File("testIn.txt");
	    if (f1.createNewFile()) {
	    	FileWriter input = new FileWriter("testIn.txt");
	    	input.write("5-3x2+7x8");
	    	input.close();
	    	
	    	Polynomial p3 = new Polynomial(f1);
	    	p3.printPolynomial();
	    	f1.delete();
	    }
	    
	    File f2 = new File("testOut.txt");
	    if (f2.createNewFile()) {
	    	double [] c4 = {-1,2.2,-3};
	    	int [] e4 = {0,4,6};
	    	Polynomial p4 = new Polynomial(c4, e4);
	    	p4.printPolynomial();
	    	p4.saveToFile("testOut.txt");
	    	
	    	BufferedReader output = new BufferedReader(new FileReader(f2));
	    	System.out.println("output = " + output.readLine());
	    	output.close();
	    	f2.delete();
	    }
	}

}