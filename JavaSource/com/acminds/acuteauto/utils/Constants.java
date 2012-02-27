/**
 * 
 */
package com.acminds.acuteauto.utils;

/**
 * @author MANSUR
 *
 */
public class Constants {
	
	public static class Misc {
		public static int DEFAULT_QTY 	= 1;
	}
	
	public static class LocationType {
		public static int PRIMARY 	= 0;
		public static int SECONDARY = 1;
	}
	
	public static class UserType {
		public static int ADMIN		=	4;
		public static int EMPLOYEE	=	5;
		public static int CUSTOMER	=	6;
	}
	
	public static class UserStatus {
		public static int ACTIVE		=	4;
		public static int SUSPENDED		=	5;
		public static int EXPIRED		=	6;
	}
	
	public static class ItemStatus {
		public static int ACTIVE		=	9;
		public static int SUSPENDED		=	10;
		public static int OUT_OF_STOCK	=	11;
	}
	
	public static class OrderStatus {
		public static int PLACED			=	14;
		public static int UNDER_PROCESS		=	15;
		public static int READY_FOR_PICKUP	=	16;
	}
	
}
