/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class UsuarioDTO {
    private int id;
    private String user_name;
    private String password;
    private int rol;

    public UsuarioDTO() {
        this("", "", 0);
    }

    public UsuarioDTO(int id,String user_name, String password, int rol) {
        this.id=id;
        this.user_name = user_name;
        this.password = password;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public int getRol() {
        return rol;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public UsuarioDTO(String user_name, String password, int rol) {
        this.user_name = user_name;
        this.password = password;
        this.rol = rol;
    }
}
