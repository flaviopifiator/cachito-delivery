/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Cusipuma
 */
public class Pedido {
    private int cod_ped;
    private int cod_zona;
    private int cod_cliente;
    private int cod_detalle;
    private int estado;
    private String fecha;
    private String observacion;
    private int cod_usuario;
    private String hora;

    /**
     * @return the cod_ped
     */
    public int getCod_ped() {
        return cod_ped;
    }

    /**
     * @param cod_ped the cod_ped to set
     */
    public void setCod_ped(int cod_ped) {
        this.cod_ped = cod_ped;
    }

    /**
     * @return the cod_zona
     */
    public int getCod_zona() {
        return cod_zona;
    }

    /**
     * @param cod_zona the cod_zona to set
     */
    public void setCod_zona(int cod_zona) {
        this.cod_zona = cod_zona;
    }

    /**
     * @return the cod_cliente
     */
    public int getCod_cliente() {
        return cod_cliente;
    }

    /**
     * @param cod_cliente the cod_cliente to set
     */
    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    /**
     * @return the cod_detalle
     */
    public int getCod_detalle() {
        return cod_detalle;
    }

    /**
     * @param cod_detalle the cod_detalle to set
     */
    public void setCod_detalle(int cod_detalle) {
        this.cod_detalle = cod_detalle;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the cod_usuario
     */
    public int getCod_usuario() {
        return cod_usuario;
    }

    /**
     * @param cod_usuario the cod_usuario to set
     */
    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
}
