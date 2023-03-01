public class ComplexNumber {
	double real;
	double im;
	
	public ComplexNumber(double a, double b) {
		this.real = a;
		this.im = b;
	}
	
	public ComplexNumber add(ComplexNumber x) {
		ComplexNumber ret = new ComplexNumber(0, 0);
		ret.real = this.real + x.real;
		ret.im = this.im + x.im;
		return ret;
	}
	
	public ComplexNumber multiply(ComplexNumber x) {
		ComplexNumber ret = new ComplexNumber(0, 0);
		ret.real = (this.real*x.real) + (-1)*(this.im*x.im);
		ret.im = (this.real * x.im) + (this.im * x.real);
		return ret;
	}
	
	public double Argument() {
		return Math.sqrt(Math.pow(this.real, 2.0) + Math.pow(this.im, 2.0));
	}
	
	public boolean equals(ComplexNumber x) {
		double tR = Math.floor(this.real * 1000)/1000;
		double tI = Math.floor(this.im * 1000)/1000;
		double xR = Math.floor(x.real * 1000)/1000;
		double xI = Math.floor(x.im * 1000)/1000;
		return ((tR == xR) && (tI == xI));
	}
	
	@Override
	public String toString() {
		return (this.real + " +i" + this.im);
	}
}
