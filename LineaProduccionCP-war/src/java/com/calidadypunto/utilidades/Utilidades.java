/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.utilidades;

/**
 *
 * @author lenovo
 */
public class Utilidades {
    public static String rellenarCerosIzquierda(String caracterRelleno, String str, int repetir){
        if(str.length() < repetir)
            str = new String(new char[repetir - str.length()]).replace("\0", caracterRelleno) + str;
        return str;
    }
}
