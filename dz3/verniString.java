package dz3;

public class verniString {
    private String result;
    verniString() {
    }

    public verniString(String result){
        this.result = result;
    }

    //возвращаем строку
    public String getResult(){
        return result;
    }

    //операции над строкой
    public void Vozvrashaem(String s){
        result = s;
    }


}
