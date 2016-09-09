package control;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


import entity.Cliente;
import persistence.ClienteDao;

public class controlEnt {

	public static String controle(Cliente c) {

		String msg = "";
		try {

			Long limit = 32l * 24l * 60l * 60l * 1000l;
			
			// Long limit = call.add(call.MONTH, 1);
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"), new Locale("pt","BR"));				
		
			   if(!(c.getDataPg()==null)){
				 if (cal.getTimeInMillis() > c.getDataPg().getTime() + limit) {
					c.setAtivo("i");
					new ClienteDao().update(c);
					msg = "Entrada nao liberada, efetuar pagamento";
				
				 }else {
			    	 msg = "Entrada Liberada";
			    }
									
			     } 
		} catch (Exception e) {			
			msg = e.getMessage();
		}		
		return msg;
		}

	
	
	

}