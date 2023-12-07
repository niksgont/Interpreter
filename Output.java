public class Output {
    public static void main(String[] args) {
        VariableS s = new VariableS(20, "anel");
        int b = 10;
        int result = increment(b);
        System.out.println("Result: " + result);
    }
    public static int increment(int a) {
        return a + 1;
    }
}

class VariableS {
    private int anel;
    private String salkenova;

    public VariableS(int anel, String salkenova) {
        this.anel = anel;
        this.salkenova = salkenova;
    }

    public int getAnel() {
        return anel;
    }

    public void setAnel(int anel) {
        this.anel = anel;
    }

    public String getSalkenova() {
        return salkenova;
    }

    public void setSalkenova(String salkenova) {
        this.salkenova = salkenova;
    }
}
