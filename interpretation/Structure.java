package interpretation;
public class Structure implements Runnable {
    public void run() {
        Complex complex = new Complex(2424, 857);
        System.out.println(complex.toString());
    }
    public class Complex {
        public double real;
        public double imaginary;

        public Complex() {
        }

        public Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        @Override
        public String toString() {
            return String.valueOf(real) + "i" + String.valueOf(imaginary);
        }
    }
    public Complex sum(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imaginary + b.imaginary);
    }

    public boolean eq(Complex a, Complex b) {
        if ((a.real == b.real) || (a.imaginary == b.imaginary)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean grEq(Complex a, Complex b) {
        if (a.real > b.real) {
            return true;
        } else {
            if (a.real == b.real) {
                if (a.imaginary >= b.imaginary) {
                    return true;
                }
            }
        }
        return false;
    }

    public Complex compl(int a) {
        return new Complex(a, 0);
    }

    public double re(Complex a) {
        return a.real;
    }

    public int addIntInt(int a, int b) {
        return a + b;
    }

    public double addIntReal(int a, double b) {
        return a + b;
    }

    public double addRealInt(double a, int b) {
        return a + b;
    }

    public double addRealReal(double a, double b) {
        return a + b;
    }

    public class Rational {
        public int num;
        public int denom;

        public Rational(int num, int denom) {
            this.num = num;
            this.denom = denom;
        }
    }

    /*
        ADD part
     */
    public Rational addIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return new Rational(a * denom + nom, denom);
    }

    public Rational addRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return new Rational(b * denom + nom, denom);
    }

    public Rational addRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return new Rational(nomA * denomB + nomB * denomA, denomA * denomB);
    }

    /*
        SUBTRACT part
     */
    public int subtractIntInt(int a, int b) {
        return a - b;
    }

    public double subtractIntReal(int a, double b) {
        return a - b;
    }

    public double subtractRealInt(double a, int b) {
        return a - b;
    }

    public double subtractRealReal(double a, double b) {
        return a - b;
    }

    public Rational subtractIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return new Rational(a * denom - nom, denom);
    }

    public Rational subtractRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return new Rational(b * denom - nom, denom);
    }

    public Rational subtractRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return new Rational(nomA * denomB - nomB * denomA, denomA * denomB);
    }

    /*
        MULT part
     */
    public int multIntInt(int a, int b) {
        return a * b;
    }

    public double multIntReal(int a, double b) {
        return a * b;
    }

    public double multRealInt(double a, int b) {
        return a * b;
    }

    public double multRealReal(double a, double b) {
        return a * b;
    }

    public Rational multIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return new Rational(a * nom, denom);
    }

    public Rational multRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return new Rational(b * nom, denom);
    }

    public Rational multRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return new Rational(nomA * nomB, denomA * denomB);
    }

    /*
        DIV part
     */
    public int divIntInt(int a, int b) {
        return a / b;
    }

    public double divIntReal(int a, double b) {
        return a / b;
    }

    public double divRealInt(double a, int b) {
        return a / b;
    }

    public double divRealReal(double a, double b) {
        return a / b;
    }

    public Rational divIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return new Rational(a * denom, nom);
    }

    public Rational divRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return new Rational(nom, b * denom);
    }

    public Rational divRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return new Rational(nomA * denomB, denomA * nomB);
    }

    /*
        LESS part
     */
    public boolean lessIntInt(int a, int b) {
        return a < b;
    }

    public boolean lessIntReal(int a, double b) {
        return a < b;
    }

    public boolean lessRealInt(double a, int b) {
        return a < b;
    }

    public boolean lessRealReal(double a, double b) {
        return a < b;
    }

    public boolean lessIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a < (nom / denom);
    }

    public boolean lessRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) < b;
    }

    public boolean lessRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) < (nomB / denomB);
    }

    /*
       LESS_EQUAL part
    */
    public boolean lessEqualIntInt(int a, int b) {
        return a <= b;
    }

    public boolean lessEqualIntReal(int a, double b) {
        return a <= b;
    }

    public boolean lessEqualRealInt(double a, int b) {
        return a <= b;
    }

    public boolean lessEqualRealReal(double a, double b) {
        return a <= b;
    }

    public boolean lessEqualIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a <= (nom / denom);
    }

    public boolean lessEqualRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) <= b;
    }

    public boolean lessEqualRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) <= (nomB / denomB);
    }

    /*
       MORE part
    */
    public boolean moreIntInt(int a, int b) {
        return a > b;
    }

    public boolean moreIntReal(int a, double b) {
        return a > b;
    }

    public boolean moreRealInt(double a, int b) {
        return a > b;
    }

    public boolean moreRealReal(double a, double b) {
        return a > b;
    }

    public boolean moreIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a > (nom / denom);
    }

    public boolean moreRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) > b;
    }

    public boolean moreRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) > (nomB / denomB);
    }

    /*
       MORE_EQUAL part
    */
    public boolean moreEqualIntInt(int a, int b) {
        return a >= b;
    }

    public boolean moreEqualIntReal(int a, double b) {
        return a >= b;
    }

    public boolean moreEqualRealInt(double a, int b) {
        return a >= b;
    }

    public boolean moreEqualRealReal(double a, double b) {
        return a >= b;
    }

    public boolean moreEqualIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a >= (nom / denom);
    }

    public boolean moreEqualRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) >= b;
    }

    public boolean moreEqualRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) >= (nomB / denomB);
    }

    /*
       EQUAL part
    */
    public boolean equalIntInt(int a, int b) {
        return a == b;
    }

    public boolean equalIntReal(int a, double b) {
        return a == b;
    }

    public boolean equalRealInt(double a, int b) {
        return a == b;
    }

    public boolean equalRealReal(double a, double b) {
        return a == b;
    }

    public boolean equalIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a == (nom / denom);
    }

    public boolean equalRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) == b;
    }

    public boolean equalRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) == (nomB / denomB);
    }

    /*
       NOT_EQUAL part
    */
    public boolean notEqualIntInt(int a, int b) {
        return a != b;
    }

    public boolean notEqualIntReal(int a, double b) {
        return a != b;
    }

    public boolean notEqualRealInt(double a, int b) {
        return a != b;
    }

    public boolean notEqualRealReal(double a, double b) {
        return a != b;
    }

    public boolean notEqualIntRational(int a, Rational b) {
        int nom = b.num;
        int denom = b.denom;
        return a != (nom / denom);
    }

    public boolean notEqualRationalInt(Rational a, int b) {
        int nom = a.num;
        int denom = a.denom;
        return (nom / denom) != b;
    }

    public boolean notEqualRationalRational(Rational a, Rational b) {
        int nomA = a.num;
        int denomA = a.denom;
        int nomB = b.num;
        int denomB = b.denom;
        return (nomA / denomA) != (nomB / denomB);
    }

    public boolean logicalAndBoolBool(boolean a, boolean b) {
        return a && b;
    }

    public boolean logicalOrBoolBool(boolean a, boolean b) {
        return a || b;
    }

    public boolean exclusiveOrBoolBool(boolean a, boolean b) {
        return a ^ b;
    }

    public int roundReal(double a) {
        return (int) Math.round(a);
    }

    public double roundRat(Rational a) {
        return (a.num / a.denom);
    }

    public double numRat(Rational a) {
        return a.num;
    }

    public double denomRat(Rational a) {
        return a.denom;
    }

    public Rational ratInt(int a) {
        return new Rational(a, 1);
    }

    public Rational ratIntInt(int a, int b) {
        return new Rational(a, b);
    }

    public Rational normRat(Rational a) {
        int num = a.num;
        int denom = a.denom;
        if (denom < 0) {
            num = -num;
            denom = -denom;
        }
        return new Rational(num, denom);
    }

    public int[] addIntArrayEntity(int[] a, int e) {
        int len = a.length;
        int[] newArr = new int[len + 1];
        for (int i = 0; i < len; i++) {
            newArr[i] = a[i];
        }
        newArr[len] = e;
        return newArr;
    }

    public double[] addRealArrayEntity(double[] a, double e) {
        int len = a.length;
        double[] newArr = new double[len + 1];
        for (int i = 0; i < len; i++) {
            newArr[i] = a[i];
        }
        newArr[len] = e;
        return newArr;
    }

    public Rational[] addRationalArrayEntity(Rational[] a, Rational e) {
        int len = a.length;
        Rational[] newArr = new Rational[len + 1];
        for (int i = 0; i < len; i++) {
            newArr[i] = a[i];
        }
        newArr[len] = e;
        return newArr;
    }

    public String[] addStringArrayEntity(String[] a, String e) {
        int len = a.length;
        String[] newArr = new String[len + 1];
        for (int i = 0; i < len; i++) {
            newArr[i] = a[i];
        }
        newArr[len] = e;
        return newArr;
    }

    public boolean[] addBooleanArrayEntity(boolean[] a, boolean e) {
        int len = a.length;
        boolean[] newArr = new boolean[len + 1];
        for (int i = 0; i < len; i++) {
            newArr[i] = a[i];
        }
        newArr[len] = e;
        return newArr;
    }

    public int[] addIntArrayArray(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;
        int[] newArr = new int[lenA + lenB];
        int k = 0;
        for (int i = 0; i < lenA; i++) {
            newArr[i] = a[i];
            k = i;
        }
        for (int i = 0; i < lenB; i++) {
            newArr[k] = b[i];
            k++;
        }
        return newArr;
    }

    public double[] addRealArrayArray(double[] a, double[] b) {
        int lenA = a.length;
        int lenB = b.length;
        double[] newArr = new double[lenA + lenB];
        int k = 0;
        for (int i = 0; i < lenA; i++) {
            newArr[i] = a[i];
            k = i;
        }
        for (int i = 0; i < lenB; i++) {
            newArr[k] = b[i];
            k++;
        }
        return newArr;
    }

    public Rational[] addRationalArrayArray(Rational[] a, Rational[] b) {
        int lenA = a.length;
        int lenB = b.length;
        Rational[] newArr = new Rational[lenA + lenB];
        int k = 0;
        for (int i = 0; i < lenA; i++) {
            newArr[i] = a[i];
            k = i;
        }
        for (int i = 0; i < lenB; i++) {
            newArr[k] = b[i];
            k++;
        }
        return newArr;
    }

    public String[] addStringArrayArray(String[] a, String[] b) {
        int lenA = a.length;
        int lenB = b.length;
        String[] newArr = new String[lenA + lenB];
        int k = 0;
        for (int i = 0; i < lenA; i++) {
            newArr[i] = a[i];
            k = i;
        }
        for (int i = 0; i < lenB; i++) {
            newArr[k] = b[i];
            k++;
        }
        return newArr;
    }

    public boolean[] addBooleanArrayArray(boolean[] a, boolean[] b) {
        int lenA = a.length;
        int lenB = b.length;
        boolean[] newArr = new boolean[lenA + lenB];
        int k = 0;
        for (int i = 0; i < lenA; i++) {
            newArr[i] = a[i];
            k = i;
        }
        for (int i = 0; i < lenB; i++) {
            newArr[k] = b[i];
            k++;
        }
        return newArr;
    }
}