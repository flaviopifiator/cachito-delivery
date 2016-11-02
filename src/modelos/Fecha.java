/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabe50
 */
public class Fecha {
    
        public String getFecha(){
            Calendar fecha = new GregorianCalendar();

            String dia=Integer.toString(fecha.get(Calendar.DAY_OF_MONTH))
                    ,mes=Integer.toString(fecha.get(Calendar.MONTH)+1)
                    ,anio=Integer.toString(fecha.get(Calendar.YEAR));

            if(fecha.get(Calendar.DAY_OF_MONTH)<10)
                dia="0"+Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));

            if(fecha.get(Calendar.MONTH)+1<10)
                mes="0"+Integer.toString(fecha.get(Calendar.MONTH));

            return dia+"/"+mes+"/"+anio;
    }
        
        public String getHora(){
            Calendar fecha = new GregorianCalendar();

            String hora=Integer.toString(fecha.get(Calendar.HOUR))
                    ,min=Integer.toString(fecha.get(Calendar.MINUTE));

            if (fecha.get(Calendar.MINUTE)<10)
                min="0"+Integer.toString(fecha.get(Calendar.MINUTE));
            if(Calendar.PM==1)
                hora=Integer.toString(fecha.get(Calendar.HOUR)+12);
            else
                if(fecha.get(Calendar.HOUR)<10)
                    hora="0"+Integer.toString(fecha.get(Calendar.HOUR));


            return hora+":"+min;
    }
        
}
