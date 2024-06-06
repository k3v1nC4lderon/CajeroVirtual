/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

/**
 *
 * @author ETHAN PIERCE
 */
public class Validacion {
    String usuario;
    int password;
    Cuentas c  = new Cuentas();;
    public Validacion(String usuario, int password){
        this.usuario = usuario;
        this.password = password;
        
        
    }
    
    public boolean existe(){
        return c.comprobarUsuario(this.usuario, this.password);
    }
    
}
