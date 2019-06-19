/**
 * @Author: Aditya Peshave
 * Date: Apr 18, 2013
 */
package com.healthcare.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.DAO.AddressDAO;
import com.healthcare.DAO.DoctorDAO;
import com.healthcare.DAO.HospitalDAO;
import com.healthcare.DAO.HospitalInventoryItemDAO;
import com.healthcare.DAO.ManufacturerDAO;
import com.healthcare.DAO.NurseDAO;
import com.healthcare.DAO.PersonDAO;
import com.healthcare.DAO.ProcessedRequestDAO;
import com.healthcare.DAO.ProductDAO;
import com.healthcare.DAO.RequestDAO;
import com.healthcare.DAO.UserAccountDAO;
import com.healthcare.data.DefaultData;
import com.healthcare.pojo.Doctor;
import com.healthcare.pojo.Hospital;
import com.healthcare.pojo.HospitalInventoryItem;
import com.healthcare.pojo.InventoryItem;
import com.healthcare.pojo.ProcessedRequest;
import com.healthcare.pojo.Product;
import com.healthcare.pojo.Request;
import com.healthcare.pojo.UserAccount;

/**
 * @author ADi
 * 
 */
@Controller
public class DoctorController {

	@Autowired
	private DoctorDAO doctorDAO;

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private AddressDAO addressDAO;

	@Autowired
	private HospitalDAO hospitalDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private NurseDAO nurseDAO;

	@Autowired
	private RequestDAO requestDAO;

	@Autowired
	private ProcessedRequestDAO processedRequestDAO;

	@Autowired
	private ManufacturerDAO manufacturerDAO;

	@Autowired
	private HospitalInventoryItemDAO hospitalInventoryItemDAO;

	@RequestMapping(value = "/request.htm", method = RequestMethod.GET)
	public ModelAndView createRequest(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else {

			view = new ModelAndView("doctorRequest");

			ArrayList<Product> products = productDAO.getAllProducts();
			view.addObject("products", products);
			view.addObject("date", new Date().toString());
		}

		return view;
	}

	@RequestMapping(value = "/send.htm", method = RequestMethod.POST)
	public ModelAndView sendRequest(@RequestParam("quantity") String quantity,
			@RequestParam("product") String productName,
			@RequestParam("comments") String comments, HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else {
			UserAccount userAccount = (UserAccount) session
					.getAttribute("user");
			Request request = new Request();
			request.setSender(userAccount.getUsername());
			request.setDate(new Date().toString());
			Product product = productDAO.getProductByName(productName);
			request.setProduct(product);
			request.setComments(comments);
			request.setStatus(DefaultData.STATUS.PENDING.toString());
			request.setQuantity(Integer.parseInt(quantity));
			request.setReceiver(product.getManufacturer().getUserAccount()
					.getUsername());
			requestDAO.save(request);
			view = new ModelAndView("done");
		}
		return view;
	}

	@RequestMapping(value = "/inbox.htm", method = RequestMethod.GET)
	public ModelAndView inbox(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("inbox");
			UserAccount userAccount = (UserAccount) session
					.getAttribute("user");
			ArrayList<ProcessedRequest> processedRequests = processedRequestDAO
					.getOpenProcessedRequestsByUsername(userAccount
							.getUsername());
			System.out.println("Username in Doctor Controller"
					+ userAccount.getUsername());
			ArrayList<Request> requests = requestDAO.getAllRequests(userAccount
					.getUsername());
			ArrayList<Request> nurseRequests = new ArrayList<Request>();
			System.out.println("RECEIVER REQUESTS" + requests);

			for (Request request : requests) {
				if (request.getStatus().equals(
						DefaultData.STATUS.NURSE_PENDING.toString()))
					nurseRequests.add(request);
			}
			System.out.println("NURSE REQUESTS" + nurseRequests);
			view.addObject("nurseRequests", nurseRequests);
			view.addObject("processedRequests", processedRequests);
		}
		return view;
	}

	@RequestMapping(value = "/updateInventory.htm", method = RequestMethod.POST)
	public ModelAndView updateInventory(@RequestParam("selection") String id,
			HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("inbox");
			ProcessedRequest processedRequest = processedRequestDAO
					.getRequestById(Integer.parseInt(id));
			HospitalInventoryItem inventoryItem = new HospitalInventoryItem();
			inventoryItem.setAvailability(true);
			inventoryItem.setReturned(false);
			inventoryItem.setOwner(DefaultData.OWNER.HOSPITAL.toString());
			inventoryItem.setHospital(hospitalDAO.getHospital());
			inventoryItem.setProduct(processedRequest.getInventoryItem()
					.getProduct());
			inventoryItem.setQuantity(processedRequest.getInventoryItem()
					.getQuantity());
			processedRequest.getRequest().setStatus(
					DefaultData.INVENTORY_STATUS.IN_HOSPITAL_STOCK.toString());
			processedRequest.setStatus(DefaultData.STATUS.PR_RESOLVED
					.toString());
			processedRequestDAO.update(processedRequest);
			Hospital hospital = hospitalDAO.getHospital();
			hospital.getInventoryItems().add(inventoryItem);
			hospitalDAO.update(hospital);

			UserAccount userAccount = (UserAccount) session
					.getAttribute("user");
			ArrayList<ProcessedRequest> processedRequests = processedRequestDAO
					.getOpenProcessedRequestsByUsername(userAccount
							.getUsername());

			view.addObject("processedRequests", processedRequests);
		}
		return view;
	}

	@RequestMapping(value = "/processNurseRequest.htm", method = RequestMethod.POST)
	public ModelAndView processNurseRequest(
			@RequestParam("selection") String id, HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("inbox");
			Hospital hospital = hospitalDAO.getHospital();
			Request request = requestDAO.getRequestById(Integer.parseInt(id));
			ArrayList<HospitalInventoryItem> inventoryItems = hospitalInventoryItemDAO
					.getInventoryByProduct(request.getProduct());
			HospitalInventoryItem currentItem = null;
			
			
			//Check for the HospitalInventoryItem quantity.
			//If it's more than or equal to required item's quantity, proceed with
			//the selected HospitalInventoryItem.
			for (HospitalInventoryItem item : inventoryItems) {
				if (item.getQuantity() >= request.getQuantity()) {
					currentItem = item;
					break;
				}

			}
			if (currentItem == null) {
				System.out.println("Error in request");
			}

			else {
				
				HospitalInventoryItem newItem = new HospitalInventoryItem();
				newItem.setAvailability(false);
				newItem.setOwner(request.getSender());
				newItem.setQuantity(request.getQuantity());
				newItem.setProduct(request.getProduct());
				newItem.setHospital(hospital);
				int val = currentItem.getQuantity();
				System.out.println("quantity is: "+ val+"and the selected item is"
				+currentItem.getProduct()+":"+currentItem.getQuantity());
				currentItem.setQuantity(val - request.getQuantity());
				hospital.getInventoryItems().add(newItem);
				hospitalInventoryItemDAO.update(currentItem);
				hospitalInventoryItemDAO.save(newItem);
				request.setStatus(DefaultData.STATUS.NURSE_RESOLVED.toString());
				requestDAO.update(request);
				//hospitalDAO.update(hospital);

			}

			UserAccount userAccount = (UserAccount) session
					.getAttribute("user");
			ArrayList<ProcessedRequest> processedRequests = processedRequestDAO
					.getOpenProcessedRequestsByUsername(userAccount
							.getUsername());

			ArrayList<Request> requests = requestDAO.getAllRequests(userAccount
					.getUsername());
			ArrayList<Request> nurseRequests = new ArrayList<Request>();

			for (Request r : requests) {
				if (r.getStatus().equals(
						DefaultData.STATUS.NURSE_PENDING.toString()))
					nurseRequests.add(r);
			}

			view.addObject("nurseRequests", nurseRequests);
			view.addObject("processedRequests", processedRequests);
		}
		return view;

	}

	@RequestMapping(value = "/inventory.htm", method = RequestMethod.GET)
	public ModelAndView hospitalInventory(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole()
						.equals(DefaultData.ROLE.DOCTOR.toString()))
			view = new ModelAndView("redirect");
		else
		{
			view = new ModelAndView("inventory");
			ArrayList<HospitalInventoryItem> items = hospitalInventoryItemDAO
				.getAllHospitalInventoryItems();
			
			HospitalInventoryItem previous = new HospitalInventoryItem();
			
			for(HospitalInventoryItem inventoryItem: items)
			{
				items.remove(previous);
				if(inventoryItem.isReturned())
					previous = inventoryItem;		
			}
			
			view.addObject("inventory", items);
		}
		return view;
	}

}
