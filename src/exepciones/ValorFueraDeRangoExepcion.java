package exepciones;

public class ValorFueraDeRangoExepcion extends RuntimeException{
    private String mensaje;

    //Constructor

    public ValorFueraDeRangoExepcion(String mensaje){
        this.mensaje = mensaje;
    }

    //Metodo

    public String getMensaje(){
        return this.mensaje;
    }
}
