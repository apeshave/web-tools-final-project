package com.healthcare.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.healthcare.DAO.AddressDAO;
import com.healthcare.DAO.DoctorDAO;
import com.healthcare.DAO.HospitalDAO;
import com.healthcare.DAO.ManufacturerDAO;
import com.healthcare.DAO.NurseDAO;
import com.healthcare.DAO.PersonDAO;
import com.healthcare.DAO.ProductDAO;
import com.healthcare.DAO.UserAccountDAO;
import com.healthcare.DAO.VerificationDAO;
import com.healthcare.data.CollectiveData;
import com.healthcare.data.DefaultData;
import com.healthcare.data.Mail;
import com.healthcare.pojo.Address;
import com.healthcare.pojo.Doctor;
import com.healthcare.pojo.Hospital;
import com.healthcare.pojo.Manufacturer;
import com.healthcare.pojo.Nurse;
import com.healthcare.pojo.Person;
import com.healthcare.pojo.UserAccount;
import com.healthcare.pojo.Verification;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

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
	private DoctorDAO doctorDAO;

	@Autowired
	private VerificationDAO verificationDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;

	private DefaultData myData = new DefaultData();

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// Standard Data for the startup
	// Hospital: iHEalthCare
	// Manufacturer: m

	@PostConstruct
	public void init() {

		// Create Hospital at the start

		if (hospitalDAO.getHospital() == null) {
			Hospital hospital = new Hospital();
			hospital.setHospitalName("iHealthcare");
			hospitalDAO.save(hospital);
		}

		// Create Manufacturer

		if (manufacturerDAO.isEmpty()) {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setManufacturerName("m");
			manufacturer.setContactNo("m");
			manufacturer.setContactPersonName("m");
			Address address1 = new Address();
			Address address2 = new Address();
			address1.setCity("m");
			address1.setCountry("m");
			address1.setLine1("m");
			address1.setLine2("m");
			address1.setState("m");
			address1.setZip("m");

			address2.setZip("m");
			address2.setCity("m");
			address2.setCountry("m");
			address2.setLine1("m");
			address2.setLine2("m");
			address2.setState("m");

			Person person = new Person();
			person.setAddress(address1);
			person.setContactNo("m");
			person.setEmail("apeshave@gmail.com");
			person.setFirstName("m");
			person.setLastName("m");

			UserAccount account = new UserAccount();
			account.setPassword("m");
			account.setUsername("m");
			account.setVerified(true);
			account.setRole(DefaultData.ROLE.MANUFACTURER.toString());

			manufacturer.setPerson(person);
			manufacturer.setUserAccount(account);

			manufacturerDAO.save(manufacturer);

		}

		if (doctorDAO.isEmpty()) {
			Hospital hospital = hospitalDAO.getHospital();
			Doctor doctor = new Doctor();
			doctor.setHospital(hospital);
			hospital.getDoctors().add(doctor);
			Address address1 = new Address();
			address1.setCity("d");
			address1.setCountry("d");
			address1.setLine1("d");
			address1.setLine2("d");
			address1.setState("d");
			address1.setZip("d");

			Person person = new Person();
			person.setAddress(address1);
			person.setContactNo("d");
			person.setEmail("apeshave@gmail.com");
			person.setFirstName("d");
			person.setLastName("d");

			UserAccount account = new UserAccount();
			account.setPassword("d");
			account.setUsername("d");
			account.setVerified(true);
			account.setRole(DefaultData.ROLE.DOCTOR.toString());

			doctor.setPerson(person);
			doctor.setUserAccount(account);
			hospitalDAO.update(hospital);

		}

		if (nurseDAO.isEmpty()) {
			Hospital hospital = hospitalDAO.getHospital();
			Nurse nurse = new Nurse();
			nurse.setHospital(hospital);
			hospital.getNurses().add(nurse);
			Address address1 = new Address();
			address1.setCity("n");
			address1.setCountry("n");
			address1.setLine1("n");
			address1.setLine2("n");
			address1.setState("n");
			address1.setZip("n");

			Person person = new Person();
			person.setAddress(address1);
			person.setContactNo("n");
			person.setEmail("apeshave@gmail.com");
			person.setFirstName("n");
			person.setLastName("n");

			UserAccount account = new UserAccount();
			account.setPassword("n");
			account.setUsername("n");
			account.setVerified(true);
			account.setRole(DefaultData.ROLE.NURSE.toString());

			nurse.setPerson(person);
			nurse.setUserAccount(account);
			hospitalDAO.update(hospital);

		}

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView view = new ModelAndView("login");

		return view;
	}

	@RequestMapping(value = "/new.htm", method = RequestMethod.GET)
	public ModelAndView newUser() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {

		ModelAndView view = null;
		if (userAccountDAO.verify(username, password)) {

			UserAccount userAccount = userAccountDAO
					.getUserAccountByUsername(username);
			System.out.print("\nRole is " + userAccount.getRole());
			if (userAccount.getRole()
					.equals(DefaultData.ROLE.DOCTOR.toString())) {

				if (userAccount.isVerified()) {
					view = new ModelAndView("doctorHome");
					view.addObject("userAccount", userAccount);
				} else
					view = new ModelAndView("verify");

			}

			else if (userAccount.getRole().equals(
					DefaultData.ROLE.MANUFACTURER.toString())) {

				if (userAccount.isVerified()) {
					view = new ModelAndView("manufacturerHome");
					view.addObject("userAccount", userAccount);
					view.addObject("manufacturer",
							manufacturerDAO.getByUsername(username));
				} else
					view = new ModelAndView("verify");

			}

			else if (userAccount.getRole().equals(
					DefaultData.ROLE.NURSE.toString())) {

				if (userAccount.isVerified()) {
					view = new ModelAndView("nurseHome");
					view.addObject("userAccount", userAccount);
				} else
					view = new ModelAndView("verify");
			}

			session.setAttribute("user", userAccount);
		} else {
			view = new ModelAndView("login");
			view.addObject("error", "Invalid Username or password");
		}
		return view;
	}

	@RequestMapping(value = "/choice.htm", method = RequestMethod.GET)
	public ModelAndView selection() {

		ArrayList<String> roles = new ArrayList<String>();
		roles.add(DefaultData.ROLE.DOCTOR.toString());
		roles.add(DefaultData.ROLE.MANUFACTURER.toString());
		roles.add(DefaultData.ROLE.NURSE.toString());
		return new ModelAndView("choice", "roles", roles);
	}

	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
	public ModelAndView signup(@RequestParam("selection") String role,
			HttpSession session) {

		ModelAndView view = null;
		if (role.equals(DefaultData.ROLE.DOCTOR.toString())) {
			Doctor doctor = new Doctor();
			view = new ModelAndView("doctorSignup");
			view.addObject("doctor", doctor);
		} else if (role.equals(DefaultData.ROLE.NURSE.toString())) {

			Nurse nurse = new Nurse();
			view = new ModelAndView("nurseSignup");
			view.addObject("nurse", nurse);

		} else if (role.equals(DefaultData.ROLE.MANUFACTURER.toString())) {
			Manufacturer manufacturer = new Manufacturer();
			view = new ModelAndView("manufacturerSignup");
			view.addObject("manufacturer", manufacturer);
		} else {
			view = selection();
		}

		return view;
	}

	
	public void sendVerificationEmail(Object entity)
	{
		
		if(entity instanceof Nurse)
		{
			Nurse nurse = (Nurse)entity;
			String code = nurse.getPerson().getFirstName() + ""
					+ nurse.getPerson().getLastName() + ""
					+ new Date().toString();
			System.out.println("\n\n"+code);
			code = code.replaceAll("\\s","1");
			code = code.replaceAll(":","2");
			System.out.println("\n\n"+code);
			
			Verification verification = new Verification();
			verification.setUserAccount(nurse.getUserAccount());
			verification.setCode(code);
			verification.setPerson(nurse.getPerson());
			verification.setEmail(nurse.getPerson().getEmail());
			verificationDAO.save(verification);
			
			String email = nurse.getPerson().getEmail();
			Mail mail = new Mail();
			mail.sendMail(
			"<h1>Email Verification<h1><a " +
			"href='http://localhost:8080/healthcare/email.verify?one='" + email +
			"&two="
			+ code +"'>done</a>", "apeshave@gmail.com");
			
		}
		
		else if(entity instanceof Doctor)
		{
			 
				Doctor doctor = (Doctor)entity;
				String code = doctor.getPerson().getFirstName() + ""
						+ doctor.getPerson().getLastName() + ""
						+ new Date().toString();
				System.out.println("\n\n"+code);
				code = code.replaceAll("\\s","1");
				code = code.replaceAll(":","2");
				System.out.println("\n\n"+code);
				
				Verification verification = new Verification();
				verification.setUserAccount(doctor.getUserAccount());
				verification.setCode(code);
				verification.setPerson(doctor.getPerson());
				verification.setEmail(doctor.getPerson().getEmail());
				verificationDAO.save(verification);
				
				String email = doctor.getPerson().getEmail();
				Mail mail = new Mail();
				mail.sendMail(
				"<h1>Email Verification<h1><a " +
				"href='http://localhost:8080/healthcare/email.verify?one=" + email +
				"&two="
				+ code +"'>done</a>", "apeshave@gmail.com");
				
			}
		
			else if(entity instanceof Manufacturer)
			{
				
				Manufacturer manufacturer = (Manufacturer)entity;
				String code = manufacturer.getPerson().getFirstName() + ""
						+ manufacturer.getPerson().getLastName() + ""
						+ new Date().toString();
				System.out.println("\n\n"+code);
				code = code.replaceAll("\\s","1");
				code = code.replaceAll(":","2");
				System.out.println("\n\n"+code);
				
				Verification verification = new Verification();
				verification.setUserAccount(manufacturer.getUserAccount());
				verification.setCode(code);
				verification.setPerson(manufacturer.getPerson());
				verification.setEmail(manufacturer.getPerson().getEmail());
				verificationDAO.save(verification);
				
				String email = manufacturer.getPerson().getEmail();
				Mail mail = new Mail();
				mail.sendMail(
				"<h1>Email Verification<h1><a " +
				"href='http://localhost:8080/healthcare/email.verify?one=" + email +
				"&two="
				+ code +"'>done</a>", "apeshave@gmail.com");
				 
			}
		
	}
	
	
	@RequestMapping(value = "/nurseSave.htm", method = RequestMethod.POST)
	public ModelAndView saveNurse(@Valid Nurse nurse, BindingResult result) {

		ModelAndView view = null;
		if (!result.hasErrors()
				&& userAccountDAO
						.isUnique(nurse.getUserAccount().getUsername())) {
			
			sendVerificationEmail(nurse);
			System.out.println(nurse.getPerson().getFirstName());
			nurse.getUserAccount().setRole(DefaultData.ROLE.NURSE.toString());
			Hospital hospital = hospitalDAO.getHospital();
			nurse.setHospital(hospital);
			hospital.getNurses().add(nurse);
			hospitalDAO.update(hospital);

			view = new ModelAndView("done");
		} else {
			if (!userAccountDAO.isUnique(nurse.getUserAccount().getUsername())) {
				FieldError err = new FieldError("userAccount.username",
						"userAccount.username", "Username Already Exsists");
				result.addError(err);
			}

			view = new ModelAndView("nurseSignup");
		}

		return view;
	}

	@RequestMapping(value = "/doctorSave.htm", method = RequestMethod.POST)
	public ModelAndView saveDoctor(@Valid Doctor doctor, BindingResult result,
			HttpSession session) {

		ModelAndView view = null;

		if (!result.hasErrors()
				&& userAccountDAO.isUnique(doctor.getUserAccount()
						.getUsername())) {
			sendVerificationEmail(doctor);
			doctor.getUserAccount().setRole(DefaultData.ROLE.DOCTOR.toString());
			Hospital hospital = hospitalDAO.getHospital();
			doctor.setHospital(hospital);
			hospital.getDoctors().add(doctor);
			hospitalDAO.update(hospital);

			view = new ModelAndView("done");
		} else {
			if (!userAccountDAO.isUnique(doctor.getUserAccount().getUsername())) {
				FieldError err = new FieldError("userAccount.username",
						"userAccount.username", "Username Already Exsists");
				result.addError(err);
				session.setAttribute("item", "Doctor");
			}

			view = new ModelAndView("doctorSignup");
		}
		return view;
	}

	@RequestMapping(value = "/manufacturerSave.htm", method = RequestMethod.POST)
	public ModelAndView save(@Valid Manufacturer manufacturer,
			BindingResult result, HttpSession session) {

		ModelAndView view = null;
		
		if (!result.hasErrors()
				&& userAccountDAO.isUnique(manufacturer.getUserAccount()
						.getUsername())) {
			
			sendVerificationEmail(manufacturer);
			
			manufacturer.getUserAccount().setRole(
					DefaultData.ROLE.MANUFACTURER.toString());
			manufacturerDAO.save(manufacturer);

			view = new ModelAndView("done");
		} else {
			if (!userAccountDAO.isUnique(manufacturer.getUserAccount()
					.getUsername())) {
				FieldError err = new FieldError("userAccount.username",
						"userAccount.username", "Username Already Exsists");
				result.addError(err);
				session.setAttribute("item", "Doctor");
			}
			view = new ModelAndView("manufacturerSignup");
		}
		return view;
	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {

		session.invalidate();
		return new ModelAndView("logout");

	}

}
