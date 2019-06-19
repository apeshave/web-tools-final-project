/**
 * @Author: Aditya Peshave
 * Date: Apr 18, 2013
 */
package com.healthcare.controller;

import java.awt.image.renderable.RenderableImage;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.healthcare.DAO.ReturnInventoryDAO;
import com.healthcare.DAO.UserAccountDAO;
import com.healthcare.data.DefaultData;
import com.healthcare.pojo.Doctor;
import com.healthcare.pojo.Hospital;
import com.healthcare.pojo.HospitalInventoryItem;
import com.healthcare.pojo.InventoryItem;
import com.healthcare.pojo.Product;
import com.healthcare.pojo.Request;
import com.healthcare.pojo.ReturnInventory;
import com.healthcare.pojo.UserAccount;

/**
 * @author ADi
 * 
 */
@Controller
public class NurseController {

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
	private ReturnInventoryDAO returnInventoryDAO;
	
	@Autowired
	private ProcessedRequestDAO processedRequestDAO;

	@Autowired
	private ManufacturerDAO manufacturerDAO;

	@Autowired
	private HospitalInventoryItemDAO hospitalInventoryItemDAO;

	@RequestMapping(value = "newRequest.htm", method = RequestMethod.GET)
	public ModelAndView newRequest(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(DefaultData.ROLE.NURSE.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("newRequest");
			ArrayList<Product> products = productDAO.getAllProducts();
			ArrayList<Doctor> doctors = doctorDAO.getAllDoctors();
			view.addObject("doctors", doctors);
			view.addObject("products", products);
		}
		return view;
	}

	@RequestMapping(value = "processReturn.htm", method = RequestMethod.POST)
	public ModelAndView returnInventory(HttpSession session,
			@RequestParam("selection") String[] selection) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(DefaultData.ROLE.NURSE.toString()))
			view = new ModelAndView("redirect");
		else {

			for (String selected : selection) {

				HospitalInventoryItem item = hospitalInventoryItemDAO
						.getHospitalInventoryItemById(Integer
								.parseInt(selected));
				ArrayList<HospitalInventoryItem> stock = hospitalInventoryItemDAO
						.getInventoryByProduct(item.getProduct());
				System.out.println("\n\n\n SElected product is:"+item.getOwner());
				System.out.println("\n\n\n HI is:"+stock);
				HospitalInventoryItem selectedItem = stock.get(0);
				selectedItem.setQuantity(selectedItem.getQuantity()
						+ item.getQuantity());
				hospitalInventoryItemDAO.update(selectedItem);
				ReturnInventory inventory = new ReturnInventory();
				HospitalInventoryItem currentItem = new HospitalInventoryItem();
				
				currentItem.setAvailability(item.isAvailability());
				currentItem.setHospital(item.getHospital());
				currentItem.setOwner(item.getOwner());
				currentItem.setProduct(item.getProduct());
				currentItem.setQuantity(item.getQuantity());
				currentItem.setReturned(true);
				item.setReturned(true);
				hospitalInventoryItemDAO.delete(item);
				inventory.setItem(currentItem);
				inventory.setDate(new Date().toString());
				returnInventoryDAO.save(inventory);
				 
			}

			view = new ModelAndView("return");
			ArrayList<HospitalInventoryItem> items = hospitalInventoryItemDAO
					.getAllHospitalInventoryItems();
			ArrayList<HospitalInventoryItem> newItems = new ArrayList<HospitalInventoryItem>();
			for (HospitalInventoryItem item : items) {
				if (item.getOwner().equals(account.getUsername()))
					newItems.add(item);
			}

			view.addObject("myItems", newItems);
		}
		return view;
	}

	@RequestMapping(value = "/return.htm", method = RequestMethod.GET)
	public ModelAndView processReturn(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(DefaultData.ROLE.NURSE.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("return");
			ArrayList<HospitalInventoryItem> items = hospitalInventoryItemDAO
					.getAllHospitalInventoryItems();
			ArrayList<HospitalInventoryItem> newItems = new ArrayList<HospitalInventoryItem>();
			for (HospitalInventoryItem item : items) {
				if (item.getOwner().equals(account.getUsername()))
					newItems.add(item);
			}

			view.addObject("myItems", newItems);
		}
		return view;
	}

	@RequestMapping(value = "createNurseRequest.htm", method = RequestMethod.POST)
	public ModelAndView createRequest(HttpSession session,
			@RequestParam("selectedProduct") String product,
			@RequestParam("selectedDoctor") String doctor,
			@RequestParam("comments") String comments,
			@RequestParam("quantity") String quantity) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(DefaultData.ROLE.NURSE.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("nurseHome");
			Request request = new Request();
			request.setComments(comments);
			request.setDate(new Date().toString());
			request.setProduct(productDAO.getProductByName(product));
			request.setQuantity(Integer.parseInt(quantity));
			request.setSender(((UserAccount) session.getAttribute("user"))
					.getUsername());
			request.setReceiver(doctor);
			request.setStatus(DefaultData.STATUS.NURSE_PENDING.toString());
			requestDAO.save(request);
			String message = "Request   for   " + product
					+ "sent   sucessfully   on" + new Date().toString() + ".";
			view.addObject("message", message);
		}
		return view;
	}

	@RequestMapping(value = "reviewRequest.htm", method = RequestMethod.GET)
	public ModelAndView reviewRequests(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(DefaultData.ROLE.NURSE.toString()))
			view = new ModelAndView("redirect");
		else {
			view = new ModelAndView("reviewRequest");
			UserAccount userAccount = (UserAccount) session
					.getAttribute("user");
			ArrayList<Request> requests = requestDAO
					.getNurseRequests(userAccount.getUsername());
			ArrayList<Request> pendingRequests = new ArrayList<Request>();
			ArrayList<Request> confirmRequests = new ArrayList<Request>();

			for (Request request : requests) {
				if (request.getStatus().equals(
						DefaultData.STATUS.NURSE_PENDING.toString()))
					pendingRequests.add(request);
				else
					confirmRequests.add(request);
			}

			System.out.println("pendin requests:" + pendingRequests);
			System.out.println("confirm requests:" + confirmRequests);
			view.addObject("pendingRequests", pendingRequests);
			view.addObject("confirmRequests", confirmRequests);
		}
		return view;
	}

}
