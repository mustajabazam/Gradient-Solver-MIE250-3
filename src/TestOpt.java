import java.io.File;

import opt.Minimizer;
import poly.Polynomial;
import util.Vector;

/** This is a small example of test cases.  To test correctness of your implementation,  
 *  see if the output on your tests matches the results of the same tests on the solution
 *  in TestOptSoln.  Note the imports in this file!
 * 
 *  NOTE: You need to write your own test cases for full credit -- we will ask to see
 *        your additional test cases during code review.
 *
 * @author ssanner@mie.utoronto.ca
 *
 */
public class TestOpt {

	public static void main(String[] args) throws Exception {
		// You must run more test cases than this!
		RunMinimizer("files/poly1.txt", 0.001, 200, 0.10, "{ x=1.0 }");
		RunMinimizer("files/poly2.txt", 0.001, 200, 0.10, "{ x=1.0 y=1.0 }");
                
                
                System.out.println("*****MY TEST CASES*****");
                
		Term tm1 = new Term ("x^2*y^4*z");
		System.out.println("ANS:1.000x^2*y^4*z" + tm1);
		
                Vector test1 = new Vector ("{ x=2.000 y=-9 z=8 }");
                Vector test2 = new Vector ("{ x=1.000 y=2 z=-10 }");
                System.out.println("ANS:{ x=3.0000 y=-7.0000 z=-2.0000 }: " + test1.sum(test2));     
                System.out.println("ANS: 10.246950765959598 "+ test2.computeL2Norm());
                
                Polynomial testp  = new Polynomial("x^6 + y^8 + -8*x^7*y + 8");
		Polynomial testp2 = new Polynomial(testp.toString());
                
		Polynomial dtestp = testp.differentiate("x");
		Polynomial dtestp2 = testp.differentiate("y");
                
                System.out.println(dtestp);
                System.out.println(dtestp2);          
                
	}	

	public static void RunMinimizer(String polyfile, double eps, int max_iter, double alpha, String sx0) 
			throws Exception {
		
		Minimizer m = new Minimizer();

		// If the following file does not load, verify that it exists,
		// and check that it is the correct path relative to your
		// NetBeans/Eclipse project settings for working directory
		Polynomial p = Polynomial.ReadPolynomial(new File(polyfile));
		
		m.setEps(eps);
		m.setMaxIter(max_iter);
		m.setStepSize(alpha);
		m.setX0(new Vector(sx0));
		
		System.out.println("========================================");
		System.out.println("OPTIMIZING: " + p);
		System.out.println("========================================");
		m.printParams(System.out);
		m.minimize(p);
		m.printResults(System.out);
	}
}
