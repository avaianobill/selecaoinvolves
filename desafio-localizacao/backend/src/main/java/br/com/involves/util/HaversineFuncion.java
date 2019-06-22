package br.com.involves.util;

public class HaversineFuncion {
	
	private static final double PI_EM_RADIANOS = 0.017453292519943295;// (3,14 / 180)
	public static final double R = 6371; // 6371KM
	
	public static double haversine(double lat1, double lon1, double lat2, double lon2) {

		double distanciaEntreLatitudesEmRadianos = (lat2 - lat1) * PI_EM_RADIANOS;
		double distanciaEntreLongitudesEmRadianos = (lon2 - lon1) * PI_EM_RADIANOS;
		double lat1EmRadianos = lat1  * PI_EM_RADIANOS;
		double lat2EmRadianos = lat2 * PI_EM_RADIANOS;


		double a = Math.pow(Math.sin(distanciaEntreLatitudesEmRadianos / 2),2) 
				+ Math.pow(Math.sin(distanciaEntreLongitudesEmRadianos / 2),2) 
					* Math.cos(lat1EmRadianos) * Math.cos(lat2EmRadianos);
		double c = 2 * Math.asin(Math.sqrt(a));
		return (R * c);
	}
	
}