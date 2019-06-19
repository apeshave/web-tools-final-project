/**
 * @Author: Aditya Peshave
 * Date: Apr 18, 2013
 */
package com.healthcare.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.DAO.InventoryItemDAO;
import com.healthcare.DAO.ManufacturerDAO;
import com.healthcare.DAO.ProcessedRequestDAO;
import com.healthcare.DAO.ProductDAO;
import com.healthcare.DAO.RequestDAO;
import com.healthcare.data.DefaultData;
 
import com.healthcare.pojo.InventoryItem;
import com.healthcare.pojo.Manufacturer;
import com.healthcare.pojo.ProcessedRequest;
import com.healthcare.pojo.Product;
import com.healthcare.pojo.Request;
import com.healthcare.pojo.UserAccount;

/**
 * @author ADi
 * 
 */
@Controller
public class ManufacturerController {

	@Autowired
	private ManufacturerDAO manufacturerDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private RequestDAO requestDAO;
	@Autowired
	private InventoryItemDAO inventoryItemDAO;
	@Autowired
	private ProcessedRequestDAO processedRequestDAO;

	@RequestMapping(value = "/home.htm", method = RequestMethod.POST)
	public ModelAndView save(@Valid Manufacturer manufacturer,
			BindingResult result) {

		return new ModelAndView("manufacturerHome");
	}

	@RequestMapping(value = "/addProduct.htm", method = RequestMethod.GET)
	public ModelAndView addProduct(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");
		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {
			Product newProduct = new Product();
			view = new ModelAndView("addProduct", "product", newProduct);
		}
		return view;
	}

	@RequestMapping(value = "/saveProduct.htm", method = RequestMethod.POST)
	public ModelAndView saveProduct(@Valid Product product,
			BindingResult result, HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");
		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {

			if (result.hasErrors()) {
				
				view = new ModelAndView("addProduct");
			} else {
				System.out.println((session.getAttribute("user")));
				System.out.println((session.getAttribute("manufacturer")));

				System.out
						.println(((UserAccount) (session.getAttribute("user")))
								.getUsername());
				Manufacturer manufacturer = manufacturerDAO
						.getByUsername(((UserAccount) (session
								.getAttribute("user"))).getUsername());
				product.setManufacturer(manufacturer);
				product.setManufacturerName(manufacturer.getManufacturerName());
				productDAO.save(product);
				System.out.println(product.getDescription());
				view = new ModelAndView("manufacturerHome", "product", product);
			}
		}

		return view;

	}

	@RequestMapping(value = "/manageRequests.htm", method = RequestMethod.GET)
	public ModelAndView manageProduct(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {
			String manufacturerName = ((UserAccount) session
					.getAttribute("user")).getUsername();
			System.out.println("inside ManageRequest" + manufacturerName);
			ArrayList<Request> requests = new ArrayList<Request>();
			requests = requestDAO.getRequestsByReceiver(manufacturerName);
			System.out.println("inside ManageRequest\nRequest" + requests);

			view = new ModelAndView("manageRequests");
			view.addObject("requests", requests);
		}
		return view;
	}

	@RequestMapping(value = "/process.htm", method = RequestMethod.POST)
	public ModelAndView process(@RequestParam("selection") String[] selection,
			HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {

			String[] noProcess = new String[selection.length];
			InventoryItem confirmItem = null;
			ProcessedRequest processedRequest = new ProcessedRequest();
			InventoryItem selected = null;

			for (String s : selection) {
				Request request = requestDAO
						.getRequestById(Integer.parseInt(s));
				processedRequest.setRequest(request);
				processedRequest.setStatus(DefaultData.STATUS.PR_PENDING
						.toString());
				processedRequest.setProcessedDate(new Date().toString());
				ArrayList<InventoryItem> inventoryItems = inventoryItemDAO
						.getInventoryItemByProduct(request.getProduct());
				int requirement = request.getQuantity();
				for (InventoryItem inventoryItem : inventoryItems) {
					if (requirement <= inventoryItem.getQuantity()) {
						confirmItem = new InventoryItem();
						confirmItem.setQuantity(requirement);
						confirmItem.setProduct(inventoryItem.getProduct());
						confirmItem.setManufacturer(inventoryItem
								.getManufacturer());
						confirmItem.setAvailability(true);
						selected = inventoryItem;
						break;
					}

				}

			}
			if (confirmItem == null) {
				System.out.println("Ivalid request");
			} else {
				selected.setQuantity(selected.getQuantity()
						- processedRequest.getRequest().getQuantity());
				inventoryItemDAO.update(selected);
				processedRequest.setInventoryItem(confirmItem);
				confirmItem
						.setStatus(DefaultData.INVENTORY_STATUS.SENT_TO_HOSPITAL
								.toString());
				inventoryItemDAO.save(confirmItem);
				processedRequest.getRequest().setStatus(
						DefaultData.STATUS.RESOLVED.toString());
				requestDAO.update(processedRequest.getRequest());
				processedRequestDAO.save(processedRequest);
			}
			view = new ModelAndView("done");
		}

		return view;
	}

	@RequestMapping(value = "/manageInventory.htm", method = RequestMethod.GET)
	public ModelAndView manageInventory(HttpSession session) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {
			ArrayList<Product> products = new ArrayList<Product>();
			Manufacturer manufacturer = (Manufacturer) manufacturerDAO
					.getByUsername(((UserAccount) session.getAttribute("user"))
							.getUsername());
			products = productDAO.getProductListByManufacturer(manufacturer
					.getManufacturerName());
			view = new ModelAndView("manageInventory");
			view.addObject("products", products);
			InventoryItem inventoryItem = new InventoryItem();
			view.addObject("inventoryItem", inventoryItem);
		}
		return view;

	}

	@RequestMapping(value = "/manufacture.htm", method = RequestMethod.POST)
	public ModelAndView manufactureProduct(
			HttpSession session,
			HttpServletRequest request,
			@ModelAttribute("inventoryItem") @Valid InventoryItem inventoryItem,
			BindingResult result) {

		ModelAndView view = null;
		UserAccount account = (UserAccount) session.getAttribute("user");

		if (account == null
				|| !account.getRole().equals(
						DefaultData.ROLE.MANUFACTURER.toString()))
			view = new ModelAndView("redirect");
		else {
			Manufacturer manufacturer = manufacturerDAO
					.getByUsername(((UserAccount) (session.getAttribute("user")))
							.getUsername());
			if (!result.hasErrors()) {

//				if(inventoryItem.getQuantity()<= 0)
//				{
//					String err = "Quantity can not be 0. (Minimum 10)";
//					view = new ModelAndView("manageInventory");
//					ArrayList<Product> products = productDAO
//							.getProductListByManufacturer(manufacturer
//									.getManufacturerName());
//					view.addObject("error", err);
//					view.addObject("products", products);
//				}
//				else if(inventoryItem.getQuantity()%1 != 0)
//				{
//					String err = "Quantity can not be 0. (Minimum 10)";
//					view = new ModelAndView("manageInventory");
//					err = "Fractions not allowed.(Minimum 10)";
//					ArrayList<Product> products = productDAO
//							.getProductListByManufacturer(manufacturer
//									.getManufacturerName());
//					view.addObject("error", err);
//					view.addObject("products", products);
//					
//				}
//				
//				else if(inventoryItem.getQuantity() < 10)
//				{
//						
//					view = new ModelAndView("manageInventory");
//					ArrayList<Product> products = productDAO
//							.getProductListByManufacturer(manufacturer
//									.getManufacturerName());
//					String err = "Quantity can not be 0. (Minimum 10)";
//					view.addObject("error", err);
//					view.addObject("products", products);
//					
//				}
//				else
//				{
					inventoryItem.setAvailability(true);
					inventoryItem.setManufacturer(manufacturer);
					inventoryItem.setProduct(productDAO.getProductByName(request
							.getParameter("pro")));
					inventoryItem
							.setStatus(DefaultData.INVENTORY_STATUS.IN_MANUFACTURER_STOCK
									.toString());
					manufacturer.getInventory().add(inventoryItem);
					manufacturerDAO.update(manufacturer);
					view = new ModelAndView("manufacturerHome", "inventory", inventoryItem);
				} 
			
				
			else 
			{
				FieldError error = result.getFieldError();
				view = new ModelAndView("manageInventory");
				String code = error.getCode();
				
				result.rejectValue("quantity", code, "Please Enter a valid No");
				ArrayList<Product> products = productDAO
						.getProductListByManufacturer(manufacturer
								.getManufacturerName());
				
				String err = "";
				
				view = new ModelAndView("manufacturerHome", "inventory", inventoryItem);
				view.addObject("error", err);
				view.addObject("products", products);
			}
		}

		return view;

	}

}
