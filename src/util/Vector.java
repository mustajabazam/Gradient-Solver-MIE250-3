package util;

import java.util.HashMap;

/** Implements a vector with *named* indices.  For example { x=1.0 y=2.0 } is a 2D
 *  vector with the first dimension named "x" and the second dimension named "y"
 *  and having respective values 1.0 and 2.0 in these dimensions.
 *  
 *  TODO: Implement all methods required to support the functionality of the project
 *        and that described in Vector.main(...) below.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Vector {

	private HashMap<String,Double> _hmVar2Value; // This maps dimension variable names to values
	
	/** Constructor of an initially empty Vector
	 * 
	 */
	public Vector() {
            
            _hmVar2Value = new HashMap<String,Double>();
            
	}
        
	/** Constructor that parses a String s like "{ x=-1 y=-2.0 z=3d }" into 
	 *  the internal HashMap representation of the Vector.  See usage in main().
	 * 
	 * @param s
	 */
	public Vector(String s) {
            
            _hmVar2Value = new HashMap<String,Double>();
            
            String[] variables = s.split("\\s"); 
            for(String var : variables){
                var = var.trim();
                String[] varVals = var.split("="); //separate the variable name from assigned value
                if (varVals.length == 2){
                    _hmVar2Value.put(varVals[0],Double.parseDouble(varVals[1]));
                }
            }
	}

	/** Removes (clears) all (key,value) pairs from the Vector representation
	 * 
	 */
	public void clear() {
            
            _hmVar2Value.clear();

	}

	/** Sets a specific var to the value val in *this*, i.e., var=val
	 * 
	 * @param var - label of Vector index to change
	 * @param val - value to change it to
	 */
	public void set(String var, double val) {
            
            this._hmVar2Value.put(var, val);

	}
        
        /** Gets the val from a key in private HashMap
	 * 
	 * @param var 
	 * @return val
	 */
	public double getVal(String var) {
            
            double val = _hmVar2Value.get(var); 
            return val;	
	}

	/** Sets all entries in *this* Vector to match entries in x
	 *  (if additional variables are in *this*, they remain unchanged) 
	 * 
	 * @param x
	 */
	public void setAll(Vector x) {
            
            _hmVar2Value.putAll(x._hmVar2Value);

	}

	///////////////////////////////////////////////////////////////////////////////
	// TODO: Add your methods here!  You'll need more than those above to make
	//       main() work below.
	///////////////////////////////////////////////////////////////////////////////
        
        /** Produce a representation of this Vector as a String. 
	 * 
         * @return String
	 */
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{ ");
            for(String key : _hmVar2Value.keySet()) // add each vector variable and value
                sb.append(String.format("%s=%6.4f ", key, _hmVar2Value.get(key)));
            sb.append("}");
            return sb.toString();
        }
        
        /*Returns a new Vector after adding Vector vec to this
        *
        *@param vec
        *@return vecSum
        */
        public Vector sum(Vector vec){
            
            Vector vecSum = new Vector();
            double val = 0.0;
            
            for(String key : this._hmVar2Value.keySet()){
                val = this._hmVar2Value.get(key) + vec._hmVar2Value.get(key);            
                vecSum.set(key, val);
            }
            return vecSum;
        }
        
        /*Returns a new Vector after multiplying original with double scalar
        *
        *@param scalar
        *@return scaMult
        */
        public Vector scalarMult(double scalar){
            
            Vector vecMult = new Vector();
            double val = 0.0;
            
            for(String key : this._hmVar2Value.keySet()){
                val = this._hmVar2Value.get(key)*scalar ; //multiply each value in vector to a scalar
                vecMult.set(key, val);
            }
            return vecMult;
        }
        
        /*Returns a new Vector after multiplying original with double scalar
        *
        *@param scalar
        *@return scaMult
        */
        public double computeL2Norm(){
            
            double lengthSq = 0.0;
            
            for(String key : this._hmVar2Value.keySet()){
                lengthSq += this._hmVar2Value.get(key) * this._hmVar2Value.get(key);
            }
            return Math.sqrt(lengthSq);
        }
        
        /*Test whether another Object o (should be Vector) is equal to *this*
        * i.e same vector points and each key and val correspond
        *
        *@param o
        *@return true or false
        */
        @Override
        public boolean equals(Object o){
            if (o instanceof Vector){
                Vector v = (Vector) o; //cast object to Vector since it is a subtype
                
                if(this._hmVar2Value.size() != v._hmVar2Value.size())
                    return false; // Vectors not equal if dont have same dimension
                
                for(String key : this._hmVar2Value.keySet()){
                    if(!(this._hmVar2Value.get(key).equals(v._hmVar2Value.get(key))))
                        return false;
                }
                return true; // all values are same
            }
            else{
                return false; // object not a Vector class type
            }
        }
        
	/** Your Vector class should implement the core functionality below and produce
	 *  **all** of the expected outputs below.  **These will be tested for grading.**
	 * 
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 *  
	 * @param args (unused -- ignore)
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Make vector: vec1[x y z] = [1 2 3]
		Vector vec1 = new Vector();
		vec1.set("x", 1.0);
		vec1.set("y", 2.0);
		vec1.set("z", 3.0);
		
		// Make vector: vec2[x y z] = [-3 -2 -1]
		Vector vec2 = new Vector();
		vec2.set("x", -3.0);
		vec2.set("y", -2.0);
		vec2.set("z", -1.0);
		
		// Make vector: vec3[x y z] = vec4[x y z] = [-1 -2 -3]
		Vector vec3 = new Vector("{ x=-1 y=-2.0 z=3d }");
		Vector vec4 = new Vector(vec3.toString());
		
		// Hint: all numbers below are formatted with String.format("%s=%6.4f ", var, val)
		//       ... you may want to use this in your Vector.toString() implementation!
		
		// Test cases: 
		System.out.println(vec1); // Should print: { x=1.0000 y=2.0000 z=3.0000 }
		System.out.println(vec2); // Should print: { x=-3.0000 y=-2.0000 z=-1.0000 }
		System.out.println(vec3); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec4); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec1.sum(vec1));        // Should print: { x=2.0000 y=4.0000 z=6.0000 }
		System.out.println(vec1.sum(vec2));        // Should print: { x=-2.0000 y=0.0000 z=2.0000 }
		System.out.println(vec1.sum(vec3));        // Should print: { x=0.0000 y=0.0000 z=6.0000 }
		System.out.println(vec1.scalarMult(0.5));  // Should print: { x=0.5000 y=1.0000 z=1.5000 }
		System.out.println(vec2.scalarMult(-1.0)); // Should print: { x=3.0000 y=2.0000 z=1.0000 }
		System.out.println(vec1.sum(vec2.scalarMult(-1.0))); // Should print: { x=4.0000 y=4.0000 z=4.0000 }
		System.out.format("%01.3f\n", vec1.computeL2Norm());           // Should print: 3.742
		System.out.format("%01.3f\n", vec2.sum(vec3).computeL2Norm()); // Should print: 6.000
		
		// If the following don't work, did you override equals()?  See Project 2 Vector and Matrix.
		System.out.println(vec3.equals(vec1)); // Should print: false
		System.out.println(vec3.equals(vec3)); // Should print: true
		System.out.println(vec3.equals(vec4)); // Should print: true
		System.out.println(vec1.sum(vec2).equals(vec2.sum(vec1))); // Should print: true
}	
}
