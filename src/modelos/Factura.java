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
public class Factura {
    private int cod_fac;
    private int cod_ped;
    private int cod_cad;
    private String fecha;
    private float tarifa;
    private float importe;
    private int cod_u;

    /**
     * @return the cod_fac
     */
    public int getCod_fac() {
        return cod_fac;
    }

    /**
     * @param cod_fac the cod_fac to set
     */
    public void setCod_fac(int cod_fac) {
        this.cod_fac = cod_fac;
    }

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
     * @return the cod_cad
     */
    public int getCod_cad() {
        return cod_cad;
    }

    /**
     * @param cod_cad the cod_cad to set
     */
    public void setCod_cad(int cod_cad) {
        this.cod_cad = cod_cad;
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
     * @return the tarifa
     */
    public float getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * @return the importe
     */
    public float getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(float importe) {
        this.importe = importe;
    }

    /**
     * @return the cod_u
     */
    public int getCod_u() {
        return cod_u;
    }

    /**
     * @param cod_u the cod_u to set
     */
    public void setCod_u(int cod_u) {
        this.cod_u = cod_u;
    }
    
    
}
