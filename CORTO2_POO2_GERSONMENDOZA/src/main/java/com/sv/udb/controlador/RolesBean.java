/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.RolesFacadeLocal;
import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Roles;
import com.sv.udb.modelo.Usuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author gersonfrancisco
 */
@Named(value = "rolesBean")
@Dependent
public class RolesBean {

    @EJB
    private RolesFacadeLocal FCDERole;    
    private Roles objeRole;
    private List<Roles> listRole;
    private boolean guardar;

    public Roles getObjeRole() {
        return objeRole;
    }

    public void setObjeRole(Roles objeRole) {
        this.objeRole = objeRole;
    }

    public List<Roles> getListRole() {
        return listRole;
    }

    public void setListRole(List<Roles> listRole) {
        this.listRole = listRole;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
    
    public RolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeRole = new Roles();
        this.guardar = true;        
    }
    
    public void consTodo()
    {
        try
        {
            this.listRole = FCDERole.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
}
