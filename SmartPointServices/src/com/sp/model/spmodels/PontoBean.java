package com.sp.model.spmodels;

public class PontoBean {
	/**
	 * @author dan
	 */
		private int id;
		private String latitude;
		private String longitude;
		//mvc -> model, view, control
		
		
		
		
		
		@Override
		public String toString() {
			return "Ponto: [ id=" + id + ", latitude=" + latitude + ", longitude="
					+ longitude +  " ]";
		}
		
		public String toStringid() {
			return "Ponto [ id=" + id + " ]";
			}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		
		

	}


