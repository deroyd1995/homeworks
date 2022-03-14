package dz1;

public class VerniKorni {
    private double reshenie, reshenie2;
    //status opredelyaet chislo korney
    private int status = 0;
    public VerniKorni(){

    }

    public VerniKorni(double reshenie, double reshenie2, int status){
        this.reshenie = reshenie;
        this.reshenie2 = reshenie2;
        this.status = status;
    }

    public double getReshenie(){
        return reshenie;
    }
    public double getReshenie2(){
        return reshenie2;
    }
    public int getStatus(){
        return status;
    }


    //Reshenie kvadratnogo uravneniya, esli status 2 - dva kornya, esli 1 - odin koren, 0 - net korney
    public void Reshaem(double D, double a, double b){
        double x1=(-b - Math.sqrt(D)) / (2 * a);
        if (D>0){
            status = 2;
            reshenie = x1;
            reshenie2 = (-b + Math.sqrt(D)) / (2 * a);;
        }
        else if (D==0){
            status = 1;
            reshenie = x1;
        }
    }
}
