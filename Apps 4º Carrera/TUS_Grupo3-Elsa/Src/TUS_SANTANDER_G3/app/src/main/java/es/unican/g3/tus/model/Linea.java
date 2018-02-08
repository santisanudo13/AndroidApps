package es.unican.g3.tus.model;


/**
 * Linea
 */

public class Linea implements Comparable{
    private String name;
    private String numero;
    private int identifier;

    public Linea(String name, String numero, int identifier){
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }


    public String getNumero() {
        return numero;
    }


    public int getIdentifier() {
        return identifier;
    }

    public boolean equals(Object o) {
        boolean aRetornar;
        if(o==null || this.getClass()!=o.getClass()){
            aRetornar=false;
        }else {
            Linea linea = (Linea) o;
            aRetornar = this.identifier == linea.getIdentifier() && this.name.equals(linea.getName())
                    && this.numero.equals(linea.getNumero());
        }
        return aRetornar;
    }

    public int hashCode(){
        return 5*getIdentifier()*getName().hashCode()*getNumero().hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Linea linea = (Linea) o;

        // Se fijan valores de prioridad de ordenación según el tipo de parada
        String numeroL1 = this.getNumero();
        double numeroL1Prioridad = 0;
        if(numeroL1.matches("[0-9]{1}[C][0-9]{1,}")) {
            numeroL1Prioridad = Double.parseDouble(numeroL1.replaceAll("C", "."));
        }else if(numeroL1.matches("[E][0-9]{1,}")){
            numeroL1Prioridad = Double.parseDouble(numeroL1.replaceAll("E", "10"));
        }else if(numeroL1.matches("[N][0-9]{1,}")){
            numeroL1Prioridad = Double.parseDouble(numeroL1.replaceAll("N", "20"));
        }else if(numeroL1.matches("[0-9]{1,}")){
            numeroL1Prioridad = Double.parseDouble(numeroL1);
        }

        // Se fijan valores de prioridad de ordenación según el tipo de parada
        String numeroL2 = linea.getNumero();
        double numeroL2Prioridad = 0;
        if(numeroL2.matches("[0-9]{1}[C][0-9]{1,}")){
            numeroL2Prioridad = Double.parseDouble(numeroL2.replaceAll("C", "."));
        }else if(numeroL2.matches("[E][0-9]{1,}")){
            numeroL2Prioridad = Double.parseDouble(numeroL2.replaceAll("E", "10"));
        }else if(numeroL2.matches("[N][0-9]{1,}")){
            numeroL2Prioridad = Double.parseDouble(numeroL2.replaceAll("N", "200"));
        }else if(numeroL2.matches("[0-9]{1,}")){
            numeroL2Prioridad = Double.parseDouble(numeroL2);
        }

        // Se devuelve la ordenación calculada
        if(numeroL1Prioridad < numeroL2Prioridad){
            return -1;
        }else if(numeroL1Prioridad > numeroL2Prioridad){
            return 1;
        }else{
            return 0;
        }
    }

}
