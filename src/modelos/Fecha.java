/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        public boolean rango(String min, String fecha, String max) throws ParseException{
            String f="";
            for(int i=0;i<fecha.length();i++)
                if(fecha.charAt(i)=='/')
                    f=f+" ";
                else
                    f=f+fecha.charAt(i);
                
            SimpleDateFormat minSDF = new SimpleDateFormat("dd MM yyyy", Locale.ROOT);
            SimpleDateFormat fSDF = new SimpleDateFormat("dd MM yyyy", Locale.ROOT);
            SimpleDateFormat maxSDF = new SimpleDateFormat("dd MM yyyy", Locale.ROOT);
                    
            Date minDate = minSDF.parse(min);
            Date fDate = fSDF.parse(f);
            Date maxDate = maxSDF.parse(max);

            Calendar minCal = minSDF.getCalendar();
            Calendar fCal = fSDF.getCalendar();
            Calendar maxCal = maxSDF.getCalendar();

            if(minCal.compareTo(fCal)<=0 && maxCal.compareTo(fCal)>=0)
                return true;
            
            return false;
        }
        
}
