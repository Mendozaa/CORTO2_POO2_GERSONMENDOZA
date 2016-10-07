/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosRolesFacadeLocal;
import com.sv.udb.modelo.UsuariosRoles;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import static org.eclipse.persistence.logging.SessionLog.EJB;
import org.primefaces.context.RequestContext;

/**
 *
 * @author gersonfrancisco
 */
@Named(value = "usuarioRolesBean")
@RequestScoped
public class UsuarioRolesBean implements Serializable{
    
    @EJB
    private UsuariosRolesFacadeLocal FCDEUsuaRole;    
    private UsuariosRoles objeUsuaRole;
    private List<UsuariosRoles> listUsuaRole;
    private boolean guardar;

    public UsuariosRoles getObjeUsuaRole() {
        return objeUsuaRole;
    }

    public void setObjeUsuaRole(UsuariosRoles objeUsuaRole) {
        this.objeUsuaRole = objeUsuaRole;
    }

    public List<UsuariosRoles> getListUsuaRole() {
        return listUsuaRole;
    }

    public void setListUsuaRole(List<UsuariosRoles> listUsuaRole) {
        this.listUsuaRole = listUsuaRole;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }
    
    
    public UsuarioRolesBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsuaRole = new UsuariosRoles();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            objeUsuaRole.setFechAltaRole(new Date());
            objeUsuaRole.setEstaUsuaRole(1);
            FCDEUsuaRole.create(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        System.out.print("\nHOLAAAAAAAAAAAAAAAAAAAAAAAAA");
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEUsuaRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
            this.consTodo();
            this.limpForm();
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEUsuaRole.remove(this.objeUsuaRole);
            this.listUsuaRole.remove(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsuaRole = FCDEUsuaRole.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeUsuaRole = FCDEUsuaRole.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeUsuaRole.getCodiUsuaRole()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
}
