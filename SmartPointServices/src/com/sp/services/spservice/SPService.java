package com.sp.services.spservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sp.dao.spdao.BusDao;
import com.sp.dao.spdao.LinhasDao;
import com.sp.dao.spdao.PontoDao;
import com.sp.model.gmaps.Gmap;
import com.sp.model.spmodels.BusBean;
import com.sp.model.spmodels.BusLinhasPontoClienteRelationship;
import com.sp.model.spmodels.LinhasBean;
import com.sp.model.spmodels.PontoBean;
public class SPService{
	/**
	 * @author dan
	 */


	//need a new API Key from google
final static String API_KEY = "";

//	public static void main(String[] args) {
//		SPService sp = new SPService();
//		
//		System.out.println(sp.dbCheck().toString());
//	}


	public static void main(String[] args) {
		System.out.println("Teste");
//		
//		PontoDao ptdao = new PontoDao();
//        
//        PontoBean pt = new PontoBean();
//	
//        List<PontoBean> listaPontos = ptdao.listAll(pt, "50");
//		for (PontoBean pontoVO : listaPontos) {
//			System.out.println(pontoVO.toString());
////			myPontoList.add(pontoVO);	
//			
//		}
		
		distanceMatrixAPI("-22.7042212", "-46.9855012", "79");
		System.out.println("fim");
	}
//
//
	public static BusLinhasPontoClienteRelationship distanceMatrixAPI(String latitude, String longitude, String linha) {
		   
		LinkedHashSet<PontoBean> myPontoList = getPontoList(linha);
		List<BusLinhasPontoClienteRelationship> blpcrList = new ArrayList<BusLinhasPontoClienteRelationship>();
			
			System.out.println(myPontoList.toString());
		   String destination; //= "-22.69590461,-47.00683616";
		   
		   
		  for (PontoBean v : myPontoList){
			  
			  Gmap gmap = new Gmap();
			 destination=(v.getLatitude()+","+v.getLongitude());
		  //https://maps.googleapis.com/maps/api/distancematrix/json?origins=-22.7042212,-46.9855012&destinations=-22.71462303,-47.01395944&key=AIzaSyBK1FNUH9AnosNUQPOPrjbZpk5M7HPP32M

	      String https_url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + latitude+","+longitude +"&destinations=" +destination+ "&key=" +  API_KEY;
	      URL url;
	      System.out.println(https_url);
	      try {

		     url = new URL(https_url);
		     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		     
			   JsonParser jp = new JsonParser();

		     JsonElement root = jp.parse(new InputStreamReader((InputStream) con.getContent())); //Convert the input stream to a json element
		     JsonObject rootobj = root.getAsJsonObject(); 
		     System.out.println(root.getAsJsonObject().toString());
		     gmap = new Gson().fromJson(rootobj.toString(), Gmap.class);
		     
		     int distancia = gmap.getRows().get(0).getElements().get(0).getDistance().getValue();
		     
				BusLinhasPontoClienteRelationship blpcr = new BusLinhasPontoClienteRelationship();
				System.out.println(v.toString());
		    		    	 blpcr.setDistancia(distancia);
		    		    	 blpcr.setPonto(v);
		    		    	 blpcrList.add(blpcr);
		    		    	 System.out.println(blpcr.getPonto().toString() + " " + blpcr.getDistancia());
	      } catch (Exception e) {
			// TODO: handle exception
		}
		  } //for
		  BusLinhasPontoClienteRelationship bestChoice = new BusLinhasPontoClienteRelationship();
		  bestChoice = sortListRelationShip(blpcrList);
		  
		  System.out.println(bestChoice.getPonto().getId());
		  return bestChoice;
	
	}//method

//	
	
	
	private static BusLinhasPontoClienteRelationship sortListRelationShip (List<BusLinhasPontoClienteRelationship> blpcrList) {
		
		BusLinhasPontoClienteRelationship bestChoice =  blpcrList.get(0);
		
		
			for (BusLinhasPontoClienteRelationship v : blpcrList) {
//				System.out.println(bestChoice.getDistancia() + " <= " + v.getDistancia());
				if (bestChoice.getDistancia() > v.getDistancia()){
					bestChoice = v;
					
				
			}
		}
		System.out.println("BestChoice Distance " + bestChoice.getDistancia().toString());
		System.out.println(bestChoice.getPonto().toString());
		System.out.println(bestChoice.toString());
		
		return bestChoice;
	}
	
	
	
	private static LinkedHashSet<PontoBean> getPontoList(String linha) {
		   
				LinkedHashSet<PontoBean> myPontoList = new LinkedHashSet<PontoBean>();

				
				  PontoDao ptdao = new PontoDao();
			        
			        PontoBean pt = new PontoBean();
				
			        List<PontoBean> listaPontos = ptdao.listAll(pt, linha);
					for (PontoBean pontoVO : listaPontos) {
						System.out.println(pontoVO.toString());
						myPontoList.add(pontoVO);	
						
					}
		
		return myPontoList;
				
	}
	
   public LinkedHashSet<String> dbCheck()
   {
	   
	   String currentLocation = "-22.7042212,-46.9855012";
	   String dest = "-22.9099312,-47.0626312";
//        System.out.println(new SPService().findPonto(currentLocation, dest));
        
	   LinkedHashSet<String> myDB = new LinkedHashSet<String>();
	   
        PontoDao ptdao = new PontoDao();
        
        PontoBean pt = new PontoBean();
        
        LinhasDao lndao = new LinhasDao();
        
        LinhasBean ln = new LinhasBean();
        
        BusDao busdao = new BusDao();
        
        BusBean ob = new BusBean();
        
		List<PontoBean> listaPontos = ptdao.listAll(pt);
		for (PontoBean pontoVO : listaPontos) {
			System.out.println(pontoVO.toString());
			myDB.add(pontoVO.toString());
			
		}
		
		List<LinhasBean> listaLinhas = lndao.listAll(ln);

		for (LinhasBean linhasVO : listaLinhas) {
			System.out.println(linhasVO.toString());
			myDB.add(linhasVO.toString());
		}
		
		List<BusBean> listaOnibus = busdao.listAll(ob);

		for (BusBean onibusVO : listaOnibus) {
			System.out.println(onibusVO.toString());
			myDB.add(onibusVO.toString());
		}
		return myDB;
   }
   
   
   //FirstRequest
   public String findPonto(String currentLocation, String destination){
	   String retorno= "";
      String https_url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + currentLocation +"&destinations=" +destination+ "&key=" +  API_KEY;
      URL url;
      try {

	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

	     //dumpl all cert info
	    System.out.println(get_https_cert(con));

	     //dump all the content
	     print_content(con);

	     
	     retorno = get_https_cert(con).toString();
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }
	return retorno;
	

   }

   public List<String> get_https_cert(HttpsURLConnection con){
	   

	  List<String> certsRet = new ArrayList<String>();
    if(con!=null){

      try {

	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");

	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	   System.out.println("Cert Type : " + cert.getType());
	   System.out.println("Cert Hash Code : " + cert.hashCode());
	   System.out.println("Cert Public Key Algorithm : "
                                    + cert.getPublicKey().getAlgorithm());
	   System.out.println("Cert Public Key Format : "
                                    + cert.getPublicKey().getFormat());
	   System.out.println("\n");
	   
	   certsRet.add("Cipher Suite" + con.getCipherSuite());
	   certsRet.add("Response Code" +  con.getResponseCode());
	   certsRet.add("Cert Type" + cert.getType());
	   certsRet.add("Public Key" + cert.getPublicKey().toString() );
	   certsRet.add("Public Key Algorithm" + cert.getPublicKey().getAlgorithm());
	   certsRet.add("Public Key Format" + cert.getPublicKey().getFormat());
	}
	
	} catch (SSLPeerUnverifiedException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}

     }
	return certsRet;
   

   }

   private String print_content(HttpsURLConnection con){
	   String result = null;
	   if(con!=null){
		
	try {

	   System.out.println("****** Content of the URL ********");
	   BufferedReader br =
		new BufferedReader(
			new InputStreamReader(con.getInputStream()));

	   String input;

	   while ((input = br.readLine()) != null){
	      System.out.println(input);
	      result += input;
	   }
	   br.close();

	} catch (IOException e) {
	   e.printStackTrace();
	}

       }
	return result;
   }

}


