/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.publicidad.utilities;

import com.publicidad.entities.JSONData;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static float String2Float(String number){
        return Float.parseFloat(number);
    }
    public static String Float2String(Float number){
        return Float.toString(number);
    }
    public static int String2int(String number){
        return Integer.parseInt(number);
    }
    public static List test(){
        ArrayList lista = new ArrayList();
        JSONData j1 = new JSONData();
        j1.setLabel("label1");
        j1.setValue("value1");
        lista.add(j1);
        
        JSONData j2 = new JSONData();
        j2.setLabel("label2");
        j2.setValue("value2");
        lista.add(j2);
        
        JSONData j3 = new JSONData();
        j3.setLabel("label3");
        j3.setValue("value3");
        lista.add(j3);
        
        JSONData j4 = new JSONData();
        j4.setLabel("label1");
        j4.setValue("value1");
        lista.add(j4);
        
        return lista;
    }
    public static String printFecha(String fecha){
        String[] meses = {"Enero", "Febrero", "Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        String[] fechaRes = fecha.split("-");
        int indice = Integer.parseInt(fechaRes[1])-1;
        return fechaRes[2]+" de "+meses[indice]+" "+fechaRes[0];
    }
     public static String stack2string(Exception e) {
          try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "------\r\n" + sw.toString() + "------\r\n";
          }
          catch(Exception e2) {
            return "bad stack2string";
          }
    }
    public static boolean validarNumero(String numero) {
        boolean res = true;
        int nada = 0;
        if(numero.length()==0){
            return false;
        }
        for (int i=1; i<=numero.length();i++){
            if (numero.codePointAt(i-1)>=48 && numero.codePointAt(i-1)<=57 ){
                nada++;
            }else{
                res = false;
                break;
            }
        }
        return res;
    }
    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}





























