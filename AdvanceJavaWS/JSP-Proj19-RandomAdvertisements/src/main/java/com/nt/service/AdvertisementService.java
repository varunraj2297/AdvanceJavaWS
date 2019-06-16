package com.nt.service;

import java.util.Random;

public class AdvertisementService {

	private int counter;
	private String[] links = new String[] { 
			                    "https://www.rolls-roycemotorcars.com",
		                     	"https://www.mercedes-benz.co.in",
			                    "https://www.koenigsegg.com", 
			                    "https://www.lamborghini.com",
			                    "https://www.wmotors.ae"
								  };
	
	private String[] images=new String[] {
		                         "images/rolls-royce.jpg",
		                         "images/maybach_exelero.jpg",
		                         "images/koenigsegg-ccxr-trevita.jpg",
		                         "images/lamborghini-veneno.jpg",
		                         "images/w-motors-lykan-hypersport.jpg"
	                              };
	
	public void nextAdvertisement() {
		   Random random=null;
		   random=new Random();
		   counter=random.nextInt(5);
	}
	
	public String getLink() {
		return links[counter];
	}
	
	public String getImage() {
		return images[counter];
	}

}
