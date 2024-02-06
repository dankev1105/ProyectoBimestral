package Negocio;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Fecha {
    private int dia, mes, anio;

    public Fecha(String fecha) {
        StringTokenizer tokens = new StringTokenizer(fecha, "/");
        try{
            this.anio = Integer.parseInt(tokens.nextToken());
            this.mes = Integer.parseInt(tokens.nextToken());
            this.dia = Integer.parseInt(tokens.nextToken());    
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Formato Incorrecto");
        }  
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public boolean comprobarFecha() {
        if (this.mes >= 1 && this.mes <= 12) {
            if (this.dia >= 1 && this.dia <= diasEnMes(this.mes, this.anio)) {
                if (this.anio >= 1 && this.anio <= 9999) {
                    return true;
                }
            }
        }
        return false;
    }

    private int diasEnMes(int mes, int anio) {
        switch (mes) {
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10: // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0)) {
                    return 29; // Año bisiesto
                } else {
                    return 28; // No es un año bisiesto
                }
            default:
                return 0; // Mes inválido
        }
    }

    @Override
    public String toString() {
        if(comprobarFecha()){
            return this.anio + " / " + this.mes+" / "+this.dia; 
        }else{
            return "";
        }
    } 
}