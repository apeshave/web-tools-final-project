/**
 * @Author: Aditya Peshave
 * Date: Apr 14, 2013
 */
package com.healthcare.data;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

/**
 * @author ADi
 *
 */
@Repository
public class DefaultData {
	
	public static enum ROLE {
        DOCTOR {@Override public String toString() { return "Doctor";}},
        NURSE {@Override public String toString() { return "Nurse";}}, 
        MANUFACTURER {@Override public String toString() { return "Manufacturer";}},
    }
	
	public static enum STATUS {
		NURSE_PENDING {@Override public String toString() { return "Nurse Pending";}},
        NURSE_RESOLVED {@Override public String toString() { return "Nurse Resolved";}}, 
		PENDING {@Override public String toString() { return "Pending";}},
        RESOLVED {@Override public String toString() { return "Resolved";}}, 
        PR_PENDING{@Override public String toString() { return "Processed Request Pending";}},
        PR_RESOLVED{@Override public String toString() { return "Processed Request Resolved";}},
    }
	
	public static enum INVENTORY_STATUS {
        IN_MANUFACTURER_STOCK {@Override public String toString() { return "In Manufacturer stock";}},
        SENT_TO_HOSPITAL {@Override public String toString() { return "Sent to hospital";}}, 
        IN_HOSPITAL_STOCK {@Override public String toString() { return "In Hospital Stock";}}
    }
	
	public static enum OWNER {
        HOSPITAL {@Override public String toString() { return "Hospital stock";}},
        NURSE {@Override public String toString() { return "Nurse";}}, 
        DOCTOR {@Override public String toString() { return "Doctor";}}
    }
	
	
		
	public ArrayList<String> getRoles() {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(ROLE.NURSE.toString());
		arrayList.add(ROLE.DOCTOR.toString());
		arrayList.add(ROLE.MANUFACTURER.toString());
		return arrayList;
	}
	

}


