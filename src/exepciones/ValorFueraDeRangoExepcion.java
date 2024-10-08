package exepciones;

public class ValorFueraDeRangoExepcion extends RuntimeException{
    private String mensaje;

    public ValorFueraDeRangoExepcion(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
