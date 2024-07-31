package org.HotelRecommand.client;

import java.util.*;

import org.HotelRecommand.model.AdminModel;
import org.HotelRecommand.model.AreaModel;
import org.HotelRecommand.model.BookModel;
import org.HotelRecommand.model.CityModel;
import org.HotelRecommand.model.CustomerModel;
import org.HotelRecommand.model.HotelModel;
import org.HotelRecommand.model.ReviewModel;
import org.HotelRecommand.service.AdminService;
import org.HotelRecommand.service.AreaService;
import org.HotelRecommand.service.BookServices;
import org.HotelRecommand.service.CityService;
import org.HotelRecommand.service.CustomerService;
import org.HotelRecommand.service.HotelService;
import org.HotelRecommand.service.ReviewService;

public class HotelClientApplication {

	public static void main(String[] args) {
		AdminService adserve = new AdminService();
		CityService cs = new CityService();
		AreaService as = new AreaService();
		HotelService hs = new HotelService();
		CustomerService cst = new CustomerService();
		BookServices bh = new BookServices();
		ReviewService rs = new ReviewService();

		Scanner sc = new Scanner(System.in);
		int result;
		int choice;
		do {
			int i;
			System.out.println("--------------------------------");
			System.out.println("1:Admin login");
			System.out.println("2:Customer login");
			System.out.println("3:exit\n");
			System.out.println("--------------------------------");
			System.out.println("3:Enter your choice:");
			choice = sc.nextInt();
			switch (choice) 
			{
			case 1:	
				sc.nextLine();
				System.out.println("Enter Username");
				String uname = sc.nextLine();
				System.out.println("Enter Password");
				String upass = sc.nextLine();
				AdminModel ad = new AdminModel();
				ad.setAusername(uname);
				ad.setApassword(upass);

				boolean b = adserve.CheckAdmin(ad);
				if (b) {
					System.out.println(" Admin Login Suceesfully....");

					int ch;
					do {

						System.out.println("---------------------------------");
						System.out.println("1:city details");
						System.out.println("2 :Area details");
						System.out.println("3: Hotel Details");
						System.out.println("4:exit\n");
						System.out.println("--------------------------------");
						System.out.println("\nEnter your choice..");
						ch = sc.nextInt();
						switch (ch) {
						case 1:
							int ch1;
							// **********************city details here*************************
							do {

								System.out.println("--------------------------------");
								System.out.println("1:Add city");
								System.out.println("2:View city");
								System.out.println("3:update city");
								System.out.println("4:Delete city\n");
								System.out.println("5:exit");
								System.out.println("--------------------------------");
								System.out.println("Enter your choice");
								ch1 = sc.nextInt();
								switch (ch1) {
								case 1:
									sc.nextLine();
									System.out.println("Enter city name");
									String cname = sc.nextLine();
									CityModel model = new CityModel();
									model.setCityname(cname);
									b = cs.AddNewCity(model);
									if (b) {
										System.out.println("New city Added Successfully");
									} else {
										System.out.println("city not added");
									}

									break;

								case 2:
									List<CityModel> list = cs.getAllCities();
									System.out.println("************* All Cities List **************");
									for (CityModel c : list) {
										System.out.println(c.getCityid() + "\t" + c.getCityname());
									}

									break;
								case 3:
									sc.nextLine();
									System.out.println("Enter old cityname ");
									String cityname = sc.nextLine();
									result = cs.getCityIdByName(cityname);
									if (result != 0) {

										CityModel cmodel = new CityModel();
										System.out.println("Enter new cityname");
										String ncity = sc.nextLine();
										cmodel.setCityname(ncity);
										cmodel.setCityid(result);
										b = cs.UpdateCity(cmodel);
										if (b) {
											System.out.println("\n Cityname updated....");
										} else {
											System.out.println("cityname not updated...");
										}

									} else {
										System.out.println("city not present\n Do you want to add this city(yes/no)");
										String confirm = sc.nextLine();
										if (confirm.equals("yes")) {
											model = new CityModel();
											model.setCityname(cityname);
											b = cs.AddNewCity(model);
											if (b) {
												System.out.println("new city added Successfully....");
											} else {
												System.out.println("some problem is there...");
											}
										}
									}

									break;
								case 4:
									sc.nextLine();
									System.out.println("Enter cityname to delete");
									String cityname1 = sc.nextLine();
									int result1 = cs.getCityIdByName(cityname1);
									if (result1 != 0) {
										CityModel cmodel = new CityModel();
										cmodel.setCityid(result1);
										b = cs.DeleteCity(cmodel);
										if (b) {
											System.out.println("city deleted...");
										} else {
											System.out.println("city not deleted");
										}
									} else {
										System.out.println("Some problem is there...");
									}
									break;
								}

							} while (ch1 != 5);

							break; // city case 1 completed..

						case 2:
							// *****************Area details here**************************
							int cid, aid, ch2;
							do {
								System.out.println("------------------------------------------");
								System.out.println("1: Add Area in Perticular city");
								System.out.println("2: View area citywise");
								System.out.println("3: Update Area Name");
								System.out.println("4: Delete Area in perticular city");
								System.out.println("5: Exit\n");
								System.out.println("---------------------------------------------");
								System.out.println("Enter your choice");
								ch2 = sc.nextInt();

								String cityname;
								switch (ch2) {
								case 1:
									// ************Add Area *************************
									sc.nextLine();
									System.out.println("Enter city name");
									cityname = sc.nextLine();
									result = cs.getCityIdByName(cityname);
									if (result == 0) {
										System.out.println("city not present\n Do you want to add this city(yes/no)");
										String confirm = sc.nextLine();
										if (confirm.equals("yes")) {
											CityModel cmodel = new CityModel();
											cmodel.setCityname(cityname);
											b = cs.AddNewCity(cmodel);
											if (b) {
												System.out.println("new city added Successfully....");
											} else {
												System.out.println("City Not Added...");
											}
										} else {
											System.out.println("Enter correct city name");
										}
									} else if (result != 0) {
										System.out.println("Enter Area Name"); // to add multiple areas in city
										String areaName = sc.nextLine();
										AreaModel areaModel = new AreaModel();
										areaModel.setAreaname(areaName);
										areaModel.setCid(result);
										b = as.AddNewArea(areaModel);
										if (b) {
											System.out.println("\nNew Area Added");
										} else {
											System.out.println("Area not added");
										}

									} else {
										System.out.println("some problem is there.........");
									}

									break;

								case 2:

									sc.nextLine();
									System.out.println("Enter city name");
									cityname = sc.nextLine();
									cid = cs.getCityIdByName(cityname);
									if (cid != 0) {
										List<AreaModel> list = as.getAllAreasNames(cityname);
										System.out.println("areaid \t areaname");
										System.out.println("-------------------------------------------------");
										for (AreaModel c : list) {
											System.out.println(c.getAreaid() + "\t" + c.getAreaname());
										}
									} else {
										System.out.println("Enter correct cityname");
									}

									break;

								case 3:
									sc.nextLine();
									System.out.println("Enter oldArea Name");
									String oldarea = sc.nextLine();
									System.out.println("Enter new Area name to replace with old Areaname");
									String newarea = sc.nextLine();
									AreaModel amodel = new AreaModel();
									amodel.setAreaname(oldarea);
									amodel.setAreaname(newarea);
									b = as.UpdateAreaName(oldarea, newarea);
									if (b) {
										System.out.println("Area Name Updated Successfully...");
									} else {
										System.out.println("Area name not updated..");
									}

									break;

								case 4:
									sc.nextLine();
									System.out.println("Enter Cityname");
									cityname = sc.nextLine();
									System.out.println("Enter Areaname which you want to delete");
									String areaname = sc.nextLine();
									cid = cs.getCityIdByName(cityname);

									if (cid != 0) {
										amodel = new AreaModel();
										amodel.setAreaname(areaname);
										amodel.setCid(cid);
										b = as.DeleteArea(amodel);
										if (b) {
											System.out.println("Area Deleted Succesfully...");
										} else {
											System.out.println("Enter correct city or Areaname");
										}
									} else {
										System.out.println("some problem is there...");
									}
									break;

								}

							} while (ch2 != 5);
							break;
						// area case completed....

						case 3:
							// *************************hotel details here*****************
							int h3;
							do {
								System.out.println("------------------------------------");
								System.out.println("1: Add Hotel");
								System.out.println("2:View Hotels");
								System.out.println("3:Delete Hotel");
								System.out.println("4:exit\n");
								System.out.println("------------------------------------");
								System.out.println("Enter your choice");
								h3 = sc.nextInt();
								switch (h3) {
								case 1:

									sc.nextLine();
									System.out.println("Enter city name");
									String cityname = sc.nextLine();
									System.out.println("Enter area name");
									String areaname = sc.nextLine();
									cid = cs.getCityIdByName(cityname);
									aid = as.getAreaIdByName(areaname);
									if (cid != 0 && aid != 0) {

										System.out.println("Enter Hotel Name");
										String hname = sc.nextLine();
										System.out.println("Enter hotel Address");
										String hAdd = sc.nextLine();
										System.out.println("Enter Hotel Contact");
										String Hcon = sc.nextLine();
										System.out.println("Enter hotel HotelType ");
										String type = sc.nextLine();
										HotelModel hmodel = new HotelModel();
										hmodel.setHname(hname);
										hmodel.setHadd(hAdd);
										hmodel.setHcon(Hcon);
										hmodel.setHtype(type);
										hmodel.setAreaid(aid);
										b = hs.isAddHotelDetails(hmodel);
										if (b) {
											System.out.println("Hotel Added successfully");
										} else {
											System.out.println("Hotel not added");
										}
									} else {
										System.out.println("Enter correct city and area");
									}

									break;

								case 2:
									sc.nextLine();
									System.out.println("Enter Areaname to display ");
									areaname = sc.nextLine();
									aid = as.getAreaIdByName(areaname);
									if (aid != 0) {
										List<HotelModel> hmodel = hs.getAllHotels(aid);
										System.out.println("----------------------------------------");
										for (HotelModel h : hmodel) {
											System.out.println(h.getHname() + "\t" + h.getHcon() + "\t" + h.getHadd()+ "\t" + h.getHtype() + "\t");
										}
										System.out.println("---------------------------------------------");
									}
									break;

								case 3:
									sc.nextLine();
									System.out.println("Enter city name");
									cityname = sc.nextLine();
									cid = cs.getCityIdByName(cityname);
									if (cid != 0) {
										System.out.println("Enter area name");
										areaname = sc.nextLine();
										aid = as.getAreaIdByName(areaname);
										if (aid != 0) {
											System.out.println("Enter hotel name to delete");
											String hname = sc.nextLine();
											int hid = hs.getHotelIdByNameAndArea(hname, aid);
											if (hid != 0) {
												HotelModel hmodel = new HotelModel();

												b = hs.DeleteHotelByHotelId(hid);
												if (b) {
													System.out.println("hotel deleted sucesfully..");
												} else {
													System.out.println("Hotel not deleted...");
												}
											} else {
												System.out.println("Enter correct hotel name");
											}
										} else {
											System.out.println("Enter correct Areaname");
										}
									} else {
										System.out.println("Enter correct city name");
									}
									break;
								} // hotel switch close

							} while (h3 != 4); // Hotel while close
							break;
						// ****** Hotel details completed.....*******
						}
					} while (ch != 4); // Admin do complete
				} // Admin if completed..

				else 
				{
					// Admin else
					System.out.println("Invalid Username and Password....");
				}
						
				

			case 2:
				// **********Customer login Registration start************
				int ch4, cid;
				do {
					System.out.println("--------------------------------");
					System.out.println("1: Sign-in");
					System.out.println("2: sign-up");
					System.out.println("3:exit\n");
					System.out.println("--------------------------------");
					System.out.println("Enter your choice :");
					ch4 = sc.nextInt();
					switch (ch4) {
					case 1:
						int b1, r1;
						sc.nextLine();
						System.out.println("Enter Username");
						String cuser = sc.nextLine();
						System.out.println("Enter password");
						String cpass = sc.nextLine();
						CustomerModel cm = new CustomerModel();
						cm.setCustName(cuser);
						cm.setCustNo(cpass);
                        int cstid=cst.custIdByName(cuser);
                       // System.out.println(cstid);
                        String cityName=cst.getCityNameByCustId(cstid);
                       //  System.out.println(cityName);
                        String Area= cst.getAreaNameByCustId(cstid);
                       // System.out.println(Area);
                       boolean cstch = cst.CustomerLogin(cm);
						
						if (cstch)
						{
							System.out.println("-----Customer Login succesfully-------");
							
							System.out.println("Your current city is "+cityName);
							System.out.println("Do you want to change city(yes/no)");
							String confirm = sc.nextLine();
						
							if (confirm.equals("yes")) 
							{
								List<CityModel> list = cs.getAllCities();
								System.out.println("--------------------------------");
								for (CityModel c : list) 
								{
									System.out.println(c.getCityid() + "\t" + c.getCityname());
									
								}
								System.out.println("--------------------------------");
								System.out.println("Enter city name from list");
								String cityname = sc.nextLine();
								cid = cs.getCityIdByName(cityname);
								if (cid != 0) 
								{
									System.out.println("Your current area is "+Area);
									System.out.println("\nDo you want to change areaname(yes/no)");
									String aname = sc.nextLine();
									if (aname.equals("yes")) 
									{
										List<AreaModel> alist = as.getAllAreas(cid);
										System.out.println("--------------------------------");
										for (AreaModel a : alist) 
										{
											System.out.println(a.getAreaid() + "\t" + a.getAreaname());
										}
										System.out.println("Select areaname from list");
										String areaname = sc.nextLine();
										int aid = as.getAreaIdByName(areaname);
										if (aid != 0) 
										{
											System.out.println("Enter Food type you prefer...");
											String ftype = sc.nextLine();
											if (ftype.equals("veg"))
											{
												System.out.println("All Veg Hotel List");
												List<HotelModel> li = hs.getAllvegHotels(areaname, ftype);
												System.out.println("---------------------------------------");
												for (HotelModel hi : li) {
													System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"+ hi.getHcon() + "\t" + hi.getHadd() + "\t");
												}
												int bt;
												do {

													System.out.println("1:Book hotel");
													System.out.println("2:search hotel");
													System.out.println("3:Exit\n");
													System.out.println("Enter your choice");
													bt = sc.nextInt();
													switch (bt) {
													case 1:
														sc.nextLine();
														System.out.println("Enter hotel Name");
														String hname = sc.nextLine();
														int hid = hs.getHotelIdByNameAndArea(hname, aid);
														System.out.println(hid);
														if (hid != 0) {
															System.out.println("Enter time");
															String time = sc.nextLine();
															System.out.println("Enter Date");
															String date = sc.nextLine();

															int custid = cst.custIdByName(cuser);
															BookModel bmodel = new BookModel();
															bmodel.setBooktime(time);
															bmodel.setBookdate(date);
															boolean val = bh.BookHotel(bmodel, hid, custid);
															if (val) {
																System.out.println("Table book succesfully.....");
																System.out.println(
																		"Do you want to give reviews and ratings...(yes/no)");
																String confrim = sc.nextLine();
																if (confrim.equals("yes")) {
																	System.out.println("Enter your name");
																	String cn = sc.nextLine();
																	int custid1 = cst.custIdByName(cuser);
																	System.out.println("Enter your review");
																	String review = sc.nextLine();
																	System.out.println("Enter rating between 1 to 5");
																	int rating = sc.nextInt();
																	ReviewModel rmodel = new ReviewModel();
																	rmodel.setReview(review);
																	rmodel.setRating(rating);

																	boolean re = rs.AddReview(hid, custid1, rmodel);
																	if (re) {
																		System.out.println(
																				"Thank you for review...Have a good day..");
																	} else {
																		System.out.println("visit again....");
																	}

																} else {
																	// review confrim.equals("no")
																	System.out.println("Visit again...");
																}
															} else {
																// table book succesfully...
																System.out.println("Sorry Table not available..");
															}

														}
														break;
													case 2:
														// *******************search hotel*******************
														int s;
														do {
															System.out.println(
																	"---------------------------------------------");
															System.out.println("1:Serch hotel areawise");
															System.out.println("2:Exit");
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Enter  your choice");
															s = sc.nextInt();
															switch (s) {
															case 1:
																sc.nextLine();
																List<CityModel> list1 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list1) {
																	System.out.println(
																			c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname = sc.nextLine();
																int cityid1 = cs.getCityIdByName(cname);
																System.out.println(cityid1);
																if (cityid1 != 0) {

																	List<AreaModel> alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																	} 
																	else 
																	{
																		System.out.println(
																				"Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : Alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String An = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid1 != 0)
																		{
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm)
																			{
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																	
																	}
																} 
															}else {
																	System.out.println(
																			"City not present\n Do you want to change city..(yes/no)");
																	String y1 = sc.nextLine();
																	if (y1.equals("yes")) {
																		List<CityModel> list11 = cs.getAllCities();
																		System.out.println(
																				"--------------------------------");
																		for (CityModel c1 : list11) {
																			System.out.println(c1.getCityid() + "\t"
																					+ c1.getCityname());
																		}
																		System.out.println(
																				"--------------------------------");
																		System.out.println("Enter city name from list");
																		String cname1 = sc.nextLine();
																		int cityid11 = cs.getCityIdByName(cname1);
																		System.out.println(cityid11);
																		if (cityid11 != 0) {
																			List<AreaModel> alist1 = as
																					.getAllAreas(cityid11);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out.println(
																					"Enter Area name from given list");
																			String an = sc.nextLine();
																			int Aid = as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs
																						.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																				}
																			} else 
																			{
																				System.out.println("Area Not present Choose another area");
																				List<AreaModel> alist11 = as.getAllAreas(cityid1);
																				System.out.println(
																						"----------------------------------------");
																				for (AreaModel a1 : alist11) {
																					System.out.println(a1.getAreaid() + "\t"
																							+ a1.getAreaname());
																				}
																				System.out.println(
																						"------------------------------------------");
																				System.out
																						.println("Enter Area name from given list");
																				String an1 = sc.nextLine();
																				int Aid1 = as.getAreaIdByName(an1);
																				if (Aid1 != 0) {
																					List<HotelModel> hm = hs.getAllHotels(Aid1);
																					System.out.println(
																							"-----------------------------------------");
																					for (HotelModel h : hm) {
																						System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																					}
																				
																				break;
																			}
                                                                          }
																		} else {
																			System.out.println("city not present");
																		}
																	}

																}

																break;

															}
														}while (s != 2);
													
														break;
													}
												} while (bt != 3); // book table do completed...

											} // if(veg) completed...

											else if (ftype.equals("non-veg")) {
												System.out.println("All non Veg Hotel List");
												List<HotelModel> li = hs.getAllvegHotels(areaname, ftype);
												System.out.println("---------------------------------------");
												for (HotelModel hi : li) {
													System.out.println(
															hi.getHid() + "\t" + hi.getHname() + "\t" + hi.getHcon()
																	+ "\t" + hi.getHadd() + "\t" + hi.getHtype());

												}
												int nonbt;
												do {

													System.out.println("1:Book hotel");
													System.out.println("2:Serch Hotel");
													System.out.println("3:Exit\n");
													System.out.println("Enter your choice");
													nonbt = sc.nextInt();
													switch (nonbt) {
													case 1:
														sc.nextLine();
														System.out.println("Enter hotel Name");
														String hname = sc.nextLine();
														int hid = hs.getHotelIdByNameAndArea(hname, aid);
														System.out.println(hid);
														if (hid != 0) {
															System.out.println("Enter time");
															String time = sc.nextLine();
															System.out.println("Enter Date");
															String date = sc.nextLine();
															int custid = cst.custIdByName(cuser);
															BookModel bmodel = new BookModel();
															bmodel.setBooktime(time);
															bmodel.setBookdate(date);
															boolean val = bh.BookHotel(bmodel, hid, custid);
															if (val) {
																System.out.println("Hotel book succesfully.....");
																System.out.println(
																		"Do you want to give reviews and ratings...(yes/no)");
																String confrim = sc.nextLine();
																if (confrim.equals("yes")) {
																	System.out.println("Enter your name");
																	String cn = sc.nextLine();
																	int custid1 = cst.custIdByName(cuser);
																	System.out.println("Enter your review");
																	String review = sc.nextLine();
																	System.out.println("Enter rating between 1 to 5");
																	int rating = sc.nextInt();
																	ReviewModel rmodel = new ReviewModel();
																	rmodel.setReview(review);
																	rmodel.setRating(rating);

																	boolean re = rs.AddReview(hid, custid1, rmodel);
																	if (re) {
																		System.out.println("Thank you for review ");
																	} else {
																		System.out.println(
																				"Visit again...\n Have a good day..");
																	}

																} else {
																	// if i never want to give review
																	// goes to book hotel
																	System.out.println("");
																}
															} else {
																System.out.println("Sorry Table not available..");
																System.out.println("Book Another Hotel..");
																System.out.println("All non Veg Hotel List");
																List<HotelModel> lst1 = hs.getAllvegHotels(areaname,
																		ftype);
																System.out.println(
																		"---------------------------------------");
																for (HotelModel hi : lst1) {
																	System.out.println(hi.getHid() + "\t"+ hi.getHname() + "\t" + hi.getHcon() + "\t"+ hi.getHadd() + "\t" + hi.getHtype());

																}

																do {

																	System.out.println("1:Book hotel");
																	System.out.println("2:Exit\n");
																	System.out.println("Enter your choice");
																	nonbt = sc.nextInt();
																	switch (nonbt) {
																	case 1:
																		sc.nextLine();
																		System.out.println("Enter hotel Name");
																		String hname1 = sc.nextLine();
																		int hid1 = hs.getHotelIdByNameAndArea(hname1,
																				aid);
																		System.out.println(hid1);
																		if (hid1 != 0) {
																			System.out.println("Enter time");
																			String time1 = sc.nextLine();
																			System.out.println("Enter Date");
																			String date1 = sc.nextLine();
																			int custid1 = cst.custIdByName(cuser);
																			BookModel bmodel1 = new BookModel();
																			bmodel1.setBooktime(time1);
																			bmodel1.setBookdate(date1);
																			boolean ans1 = bh.BookHotel(bmodel1, hid1,
																					custid1);
																			if (ans1) {
																				System.out.println(
																						"Hotel book succesfully.....");
																				System.out.println(
																						"Do you want to give reviews and ratings(yes/no)");
																				String confrim = sc.nextLine();
																				if (confrim.equals("yes")) {
																					System.out
																							.println("Enter your name");
																					String cn = sc.nextLine();
																					int cid1 = cst.custIdByName(cuser);
																					System.out.println(
																							"Enter your review");
																					String review = sc.nextLine();
																					System.out.println(
																							"Enter rating between 1 to 5");
																					int rating = sc.nextInt();
																					ReviewModel rmodel = new ReviewModel();
																					rmodel.setReview(review);
																					rmodel.setRating(rating);

																					boolean re = rs.AddReview(hid1,
																							cid1, rmodel);
																					if (re) {
																						System.out.println(
																								"Thank you for review ");
																					} else {
																						System.out.println(
																								"Visit again...\n Have a good day..");
																					}

																				} else {
																					// if i never want to give review
																					// goes to book hotel
																					System.out.println("");
																				}
																			} else {
																				System.out.println(
																						"Sorry Table not available");
																			}
																		} else {
																			System.out.println(
																					"Enter Correct Hotel Name");
																		}
																	}
																} while (nonbt != 2);
															} // else close
														} else {
															System.out
																	.println("Enter Correct Hotel Name and password..");
														}
														break;
													case 2:
														// *******************search hotel*******************
														int s;
														do {
															System.out.println(
																	"---------------------------------------------");
															System.out.println("1:Serch hotel areawise");
															System.out.println("2:Exit");
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Enter  your choice");
															s = sc.nextInt();
															switch (s) {
															case 1:
																sc.nextLine();
																List<CityModel> list1 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list1) 
																{
																	System.out.println(c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname = sc.nextLine();
																int cityid1 = cs.getCityIdByName(cname);
																System.out.println(cityid1);
																if (cityid1 != 0) {

																	List<AreaModel> alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) 
																	{
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println("------------------------------------------");
																	System.out.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println("-----------------------------------------");
																		for (HotelModel h : hm) 
																		{
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																	} 
																	else 
																	{
																		System.out.println("Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : Alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String an1 = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid1 != 0) 
																		{
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) 
																			{
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																		}
																	}
																} 
																else 
																{
																	System.out.println("City not present\n Do you want to change city(yes/no)");
																	String y1 = sc.nextLine();
																	if (y1.equals("yes")) {
																		List<CityModel> list11 = cs.getAllCities();
																		System.out.println("--------------------------------");
																		for (CityModel c1 : list11) 
																		{
																			System.out.println(c1.getCityid() + "\t"+c1.getCityname());
																		}
																		System.out.println("--------------------------------");
																		System.out.println("Enter city name from list");
																		String cname1 = sc.nextLine();
																		int cityid11 = cs.getCityIdByName(cname1);
																		System.out.println(cityid11);
																		if (cityid11 != 0) {

																			List<AreaModel> alist1 = as
																					.getAllAreas(cityid11);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) 
																			{
																				System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																			}
																			System.out.println("------------------------------------------");
																			System.out.println("Enter Area name from given list");
																			String an = sc.nextLine();
																			int Aid = as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println("-----------------------------------------");
																				for (HotelModel h : hm) 
																				{
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																				}
																			} 
																			else 
																			{
																				System.out.println("Area Not present Choose another area");
																				List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																				System.out.println(
																						"----------------------------------------");
																				for (AreaModel a1 : Alist1) {
																					System.out.println(a1.getAreaid() + "\t"
																							+ a1.getAreaname());
																				}
																				System.out.println(
																						"------------------------------------------");
																				System.out
																						.println("Enter Area name from given list");
																				String an1 = sc.nextLine();
																				int Aid1 = as.getAreaIdByName(an);
																				if (Aid1 != 0) {
																					List<HotelModel> hm = hs.getAllHotels(Aid);
																					System.out.println(
																							"-----------------------------------------");
																					for (HotelModel h : hm) {
																						System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																					}
																			}
																		}
																	}
																		else 
																		{
																			System.out.println("city not present");
																		}
																	}

																}

																break;

															}
														} while (s != 2);
														break;

													}
												} while (nonbt != 3); // non veg do completed..
											} // non veg close

											else {

												System.out.println("All Veg Nonveg Hotel List");
												List<HotelModel> li = hs.getAllHotels(aid);
												System.out.println("---------------------------------------");
												for (HotelModel hi : li) {
													System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"+ hi.getHcon() + "\t" + hi.getHadd() + "\t");

												}
												int bt;
												do {

													System.out.println("1:Book hotel");
													System.out.println("2:Search Hotel");
													System.out.println("3:Exit\n");
													System.out.println("Enter your choice");
													bt = sc.nextInt();
													switch (bt) {
													case 1:
														sc.nextLine();
														System.out.println("Enter hotel Name");
														String hname = sc.nextLine();
														int hid = hs.getHotelIdByNameAndArea(hname, aid);
														System.out.println(hid);
														if (hid != 0) {
															System.out.println("Enter time");
															String time = sc.nextLine();
															System.out.println("Enter Date");
															String date = sc.nextLine();
															int custid = cst.custIdByName(cuser);
															BookModel bmodel = new BookModel();
															bmodel.setBooktime(time);
															bmodel.setBookdate(date);
															boolean val = bh.BookHotel(bmodel, hid, custid);
															if (val) {
																System.out.println("Hotel book succesfully.....");
																System.out.println(
																		"Do you want to give reviews and ratings(yes/no)");
																String confrim = sc.nextLine();
																if (confrim.equals("yes")) {
																	System.out.println("Enter your name");
																	String cn = sc.nextLine();
																	int custid1 = cst.custIdByName(cuser);
																	System.out.println("Enter your review");
																	String review = sc.nextLine();
																	System.out.println("Enter rating between 1 to 5");
																	int rating = sc.nextInt();
																	ReviewModel rmodel = new ReviewModel();
																	rmodel.setReview(review);
																	rmodel.setRating(rating);

																	boolean re = rs.AddReview(hid, custid1, rmodel);
																	if (re) {
																		System.out.println(
																				"Thank you for review \n Visit again...Have a good day..");
																	} else {
																		System.out.println("----");
																	}

																} else {
																	System.out.println("Have a good day");
																}
															} else {
																System.out.println("Sorry Table not available..");
															}

														} else {
															System.out.println("Enter correct Hotel Name ");
														}
														break;
													case 2:
														// *******************search hotel*******************
														int s;
														do {
															System.out.println(
																	"---------------------------------------------");
															System.out.println("1:Serch hotel areawise");
															System.out.println("2:Exit");
															System.out.println(
																	"-----------------------------------------------");
															System.out.println("Enter  your choice");
															s = sc.nextInt();
															switch (s) {
															case 1:
																sc.nextLine();
																List<CityModel> list1 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list1) {
																	System.out.println(c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname = sc.nextLine();
																int cityid1 = cs.getCityIdByName(cname);
																System.out.println(cityid1);
																if (cityid1 != 0) 
																{

																	List<AreaModel> alist1 = as.getAllAreas(cityid1);
																	System.out.println("----------------------------------------");
																	for (AreaModel a1 : alist1)
																	{
																		System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																	}
																	System.out.println("------------------------------------------");
																	System.out.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) 
																	{
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println("-----------------------------------------");
																		for (HotelModel h : hm) 
																		{
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+"\t"+ h.getHadd() + "\t");

																		}
																	} 
																	else 
																	{
																		System.out.println("Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String an1 = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid1 != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																		}
																	}
																} 
																else 
																{
																	System.out.println("City not present\n Do you want to change city.(yes/no)");
																	String y1 = sc.nextLine();
																	if (y1.equals("yes")) 
																	{
																		List<CityModel> list11 = cs.getAllCities();
																		System.out.println(
																				"--------------------------------");
																		for (CityModel c1 : list11) {
																			System.out.println(c1.getCityid() + "\t"
																					+ c1.getCityname());
																		}
																		System.out.println("--------------------------------");
																		System.out.println("Enter city name from list");
																		String cname1 = sc.nextLine();
																		int cityid11 = cs.getCityIdByName(cname1);
																		System.out.println(cityid11);
																		if (cityid11 != 0) {

																			List<AreaModel> alist1 = as
																					.getAllAreas(cityid11);
																			System.out.println("----------------------------------------");
																			for (AreaModel a1 : alist1) 
																			{
																				System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																			}
																			System.out.println("------------------------------------------");
																			System.out.println(
																					"Enter Area name from given list");
																			String an = sc.nextLine();
																			int Aid = as.getAreaIdByName(an);
																			if (Aid != 0) 
																			{
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println("-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																				}
																			} else {
																				System.out.println(
																						"Area Not present Choose another area");
																				List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																				System.out.println(
																						"----------------------------------------");
																				for (AreaModel a1 : alist1) {
																					System.out.println(a1.getAreaid() + "\t"
																							+ a1.getAreaname());
																				}
																				System.out.println(
																						"------------------------------------------");
																				System.out
																						.println("Enter Area name from given list");
																				String an1 = sc.nextLine();
																				int Aid1 = as.getAreaIdByName(an);
																				if (Aid1 != 0) {
																					List<HotelModel> hm = hs.getAllHotels(Aid);
																					System.out.println(
																							"-----------------------------------------");
																					for (HotelModel h : hm) {
																						System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																					}	
																			}
																			}
																		} else {
																			System.out.println("city not present");
																		}
																	}

																}

																break;

															}
														} while (s != 2);
														break;
													}
												} while (bt != 3);

											} // else close
										} else 
										{
											System.out.println("Enter correct Area name");
											
											List<AreaModel> alist1 = as.getAllAreas(cid);
											System.out.println("----------------------------------------");
											for (AreaModel a1 : alist1)
											{
												System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
											}
											System.out.println("------------------------------------------");
											System.out.println("Enter Area name from given list");
											String an = sc.nextLine();
											int Aid = as.getAreaIdByName(an);
											if (Aid != 0) 
											{
												List<HotelModel> hm = hs.getAllHotels(Aid);
												System.out.println("-----------------------------------------");
												for (HotelModel h : hm) 
												{
													System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+"\t"+ h.getHadd() + "\t");

												}
											}	
										}
									} else {
										// if area name not change
										int aid = as.getAreaIdByName(Area);
										System.out.println("Enter your food type ");
										String type = sc.nextLine();
										if (type.equals("veg")) {
											System.out.println("view Veg Hotel List");
											List<HotelModel> list1 = hs.getAllvegHotels(Area, type);
											System.out.println("----------------------------------");
											for (HotelModel hi : list1) {
												System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"+ hi.getHcon() + "\t" + hi.getHadd() + "\t");
											}
											int bt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:Search Hotel");
												System.out.println("3:Exit\n");
												System.out.println("Enter your choice");
												bt = sc.nextInt();
												switch (bt) {
												case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();
														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Hotel book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) {
																	System.out.println(
																			"Thank you for review \n...Have a good day..");
																} else {
																	System.out.println("visit again...Have a good day");
																}

															} else {
																// not give review
																System.out.println("Thank you visit again");
															}
														} else {
															System.out.println("Sorry Table not available..");
														}

													} else {
														System.out.println("Enter Correct hotel name");
													}
													break;
												case 2:
													// *******************search hotel*******************
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> li3 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel c1 : li3) {
																System.out.println(
																		c1.getCityid() + "\t" + c1.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");
																	List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an1 = sc.nextLine();
																	int Aid1 = as.getAreaIdByName(an);
																	if (Aid1 != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																  }
																}
															} else {
																System.out.println(
																		"City not present\n Do you want to change city(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println("--------------------------------");
																	for (CityModel c1 : list11)
																	{
																		System.out.println(c1.getCityid() + "\t"
																				+ c1.getCityname());
																	}
																	System.out.println("--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	System.out.println(cityid11);
																	if (cityid11 != 0)
																	{

																		List<AreaModel> alist1 = as.getAllAreas(cityid11);
																		System.out.println("----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																		}
																		System.out.println("------------------------------------------");
																		System.out.println("Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) 
																			{
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																			}
																		} 
																		else
																		{
																			System.out.println("Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1 = as.getAreaIdByName(an);
																			if (Aid1 != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}	
																		 }
																		}
																	} 
																	else 
																	{
																		System.out.println("city not present");
																	}
																}else
																{
																	System.out.println("Enter correct cityname");
																}

															}

															break;

														}
													} while (s != 2);

													break;
												} // switch close
											} while (bt != 3); // do close

										}

										else if (type.equals("non veg")) {
											System.out.println("view non Veg Hotel List");
											List<HotelModel> list1 = hs.getAllvegHotels(Area, type);
											System.out.println("----------------------------------");
											for (HotelModel hi : list1) {
												System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"
														+ hi.getHcon() + "\t" + hi.getHadd() + "\t");
											}
											int bt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:Search Hotel");
												System.out.println("3:Exit\n");
												System.out.println("Enter your choice");

												bt = sc.nextInt();
												switch (bt) {
												case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();
														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Hotel book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) {
																	System.out.println("Thank you for review \n ");
																} else {
																	System.out
																			.println("Visit again...Have a good day..");
																}

															} else {
																System.out.println("visit again");
															}
														} else {
															System.out.println("Sorry Table not available..");
														}

													} else {
														System.out.println("Enter correct hotel name");
													}
													break;
												case 2:
													// *******************search hotel*******************
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> list3 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel c1 : list3) {
																System.out.println(
																		c1.getCityid() + "\t" + c1.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"
																				+ h.getHname() + "\t" + h.getHcon()
																				+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");
																	List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an1 = sc.nextLine();
																	int Aid1 = as.getAreaIdByName(an);
																	if (Aid1 != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																   }
																}
															} else {
																System.out.println(
																		"City not present\n Do you want to change city.(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println(
																			"--------------------------------");
																	for (CityModel c1 : list11) {
																		System.out.println(c1.getCityid() + "\t"
																				+ c1.getCityname());
																	}
																	System.out.println(
																			"--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	System.out.println(cityid11);
																	if (cityid11 != 0) {

																		List<AreaModel> alist1 = as
																				.getAllAreas(cityid11);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out.println(
																				"Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"
																						+ h.getHname() + "\t"
																						+ h.getHcon() + "\t"
																						+ h.getHadd() + "\t");

																			}
																		} else {
																			System.out.println(
																					"Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1 = as.getAreaIdByName(an);
																			if (Aid1 != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}
																		  }
																		}
																	} else {
																		System.out.println("city not present");
																	}
																}

															}

															break;

														}
													} while (s != 3);
													break;
												}
											} while (bt != 3);

										} else {
											System.out.println("View veg Non veg hotels");

											List<HotelModel> li = hs.getAllHotels(aid);
											System.out.println("----------------------------------");
											for (HotelModel h1 : li) {
												System.out.println(h1.getHid() + "\t" + h1.getHname() + "\t"
														+ h1.getHcon() + "\t" + h1.getHadd() + "\t");
											}
											int bt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:search hotel");												System.out.println("2:Exit\n");
												System.out.println("Enter your choice");
												bt = sc.nextInt();
												switch (bt) {
												case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();
														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Hotel book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) {
																	System.out.println(
																			"Thank you for review Have a good day..");
																} else {
																	System.out.println(
																			" Visit again...Have a good day..");
																}

															} else {
																System.out.println("Thank you..");
															}
														} else {
															System.out.println("Sorry Table not available..");
														}

													} else {
														System.out.println("Enter correct Hotel Name");
													}
													break;
												case 2:
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> li3 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel c1 : li3) {
																System.out.println(
																		c1.getCityid() + "\t" + c1.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");
																	List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an1 = sc.nextLine();
																	int Aid1 = as.getAreaIdByName(an);
																	if (Aid1 != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}	
																 	
															    	}
																}
															} else {
																System.out.println(
																		"City not present\n Do you want to change city(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println("--------------------------------");
																	for (CityModel c1 : list11)
																	{
																		System.out.println(c1.getCityid() + "\t"
																				+ c1.getCityname());
																	}
																	System.out.println("--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	System.out.println(cityid11);
																	if (cityid11 != 0)
																	{

																		List<AreaModel> alist1 = as.getAllAreas(cityid11);
																		System.out.println("----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																		}
																		System.out.println("------------------------------------------");
																		System.out.println("Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) 
																			{
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																			}
																		} 
																		else
																		{
																			System.out.println("Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1= as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}
																		}
																		
																		}
																	} 
																	else 
																	{
																		System.out.println("city not present");
																	}
																}

															}

															break;

														}
													} while (s != 2);
													break;
												}
											} while (bt != 3);

										}

									}
								} else {
									System.out.println("city not present \n Enter correct city");
									

								}
							}
							// this else for if i never want to change city
							else {
								System.out.println("Your current area is "+Area);
								System.out.println("Do you want to change Areaname");
								String achange = sc.nextLine(); // no
								if (achange.equals("no"))
								{
								 String areaname=cst.getAreaNameByCustId(cstid);	
									int aid = as.getAreaIdByName(areaname);
									System.out.println("Enter your food type ");
									String type = sc.nextLine();
									if (type.equals("veg")) {
										System.out.println("view Veg Hotel List");
										List<HotelModel> list1 = hs.getAllvegHotels(areaname, type);
										System.out.println("----------------------------------");
										for (HotelModel hi : list1) {
											System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t" + hi.getHcon()
													+ "\t" + hi.getHadd() + "\t");
										}
										int bt;
										do {

											System.out.println("1:Book Hotel");
											System.out.println("2:Search Hotel");
											System.out.println("3:Exit\n");
											System.out.println("Enter your choice");
											bt = sc.nextInt();
											switch (bt) {
											case 1:
												sc.nextLine();
												System.out.println("Enter hotel Name");
												String hname = sc.nextLine();
												int hid = hs.getHotelIdByNameAndArea(hname, aid);
												System.out.println(hid);
												if (hid != 0) {
													System.out.println("Enter time");
													String time = sc.nextLine();
													System.out.println("Enter Date");
													String date = sc.nextLine();
													int custid = cst.custIdByName(cuser);
													BookModel bmodel = new BookModel();
													bmodel.setBooktime(time);
													bmodel.setBookdate(date);
													boolean val = bh.BookHotel(bmodel, hid, custid);
													if (val) {
														System.out.println("Hotel book succesfully.....");
														System.out
																.println("Do you want to give reviews and ratings(yes/no)");
														String confrim = sc.nextLine();
														if (confrim.equals("yes")) {
															System.out.println("Enter your name");
															String cn = sc.nextLine();
															int custid1 = cst.custIdByName(cuser);
															System.out.println("Enter your review");
															String review = sc.nextLine();
															System.out.println("Enter rating between 1 to 5");
															int rating = sc.nextInt();
															ReviewModel rmodel = new ReviewModel();
															rmodel.setReview(review);
															rmodel.setRating(rating);

															boolean re = rs.AddReview(hid, custid1, rmodel);
															if (re) {
																System.out.println(
																		"Thank you for review \n Visit again...Have a good day..");
															} else {
																System.out.println("----");
															}

														} else {
															// not give review
															System.out.println("Thank you visit again");
														}
													} 
													else 
													{
														System.out.println("Sorry Table not available..");
													}

												} 
												else 
												{
												   	System.out.println("Enter Correct hotel name");
												}
												break;
											case 2:
												// *******************search hotel*******************
												int s;
												do {
													System.out.println("---------------------------------------------");
													System.out.println("1:Serch hotel areawise");
													System.out.println("2:Exit");
													System.out
															.println("-----------------------------------------------");
													System.out.println("Enter  your choice");
													s = sc.nextInt();
													switch (s) {
													case 1:
														sc.nextLine();
														List<CityModel> li2 = cs.getAllCities();
														System.out.println("--------------------------------");
														for (CityModel c1 : li2) {
															System.out.println(c1.getCityid() + "\t" + c1.getCityname());
														}
														System.out.println("--------------------------------");
														System.out.println("Enter city name from list");
														String cname = sc.nextLine();
														int cityid1 = cs.getCityIdByName(cname);
														//System.out.println(cityid1);
														if (cityid1 != 0) {

															List<AreaModel> alist1 = as.getAllAreas(cityid1);
															System.out.println("----------------------------------------");
															for (AreaModel a1 : alist1) {
																System.out.println(
																		a1.getAreaid() + "\t" + a1.getAreaname());
															}
															System.out.println("------------------------------------------");
															System.out.println("Enter Area name from given list");
															String an = sc.nextLine();
															int Aid = as.getAreaIdByName(an);
															if (Aid != 0) {
																List<HotelModel> hm = hs.getAllHotels(Aid);
																System.out.println(
																		"-----------------------------------------");
																for (HotelModel h : hm) 
																{
																	System.out.println(h.getHid() + "\t" + h.getHname()+ "\t" + h.getHcon() + "\t" + h.getHadd()+ "\t");

																}
															} else {
																System.out.println(
																		"Area Not present Choose another area");
																List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(a1.getAreaid() + "\t"
																			+ a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out
																		.println("Enter Area name from given list");
																String an1 = sc.nextLine();
																int Aid1 = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
																
															  }
															}
														} else {
															System.out.println(
																	"City not present\n Do you want to change city(yes/no)");
															String y1 = sc.nextLine();
															if (y1.equals("yes")) {
																List<CityModel> list11 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list11) {
																	System.out.println(
																			c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname1 = sc.nextLine();
																int cityid11 = cs.getCityIdByName(cname1);
																//System.out.println(cityid11);
																if (cityid11 != 0) {

																	List<AreaModel> alist1 = as.getAllAreas(cityid11);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																	} else {
																		System.out.println(
																				"Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String an1 = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid1 != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																	  }	
																	}
																} else {
																	System.out.println("city not present");
																}
															}

														}

														break;

													}
												} while (s != 2);
												break;
											} // switch close
										} while (bt != 3); // do close

									}

									else if (type.equals("non veg")) {
										System.out.println("view non Veg Hotel List");
										List<HotelModel> list1 = hs.getAllvegHotels(areaname, type);
										System.out.println("----------------------------------");
										for (HotelModel hi : list1) {
											System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t" + hi.getHcon()+ "\t" + hi.getHadd() + "\t");
										}
										int bt;
										do {

											System.out.println("1:Book Hotel");
											System.out.println("2:Search Hotel");
											System.out.println("3:Exit\n");
											System.out.println("Enter your choice");

											bt = sc.nextInt();
											switch (bt) {
											case 1:
												sc.nextLine();
												System.out.println("Enter hotel Name");
												String hname = sc.nextLine();
												int hid = hs.getHotelIdByNameAndArea(hname, aid);
												System.out.println(hid);
												if (hid != 0) {
													System.out.println("Enter time");
													String time = sc.nextLine();
													System.out.println("Enter Date");
													String date = sc.nextLine();
													int custid = cst.custIdByName(cuser);
													BookModel bmodel = new BookModel();
													bmodel.setBooktime(time);
													bmodel.setBookdate(date);
													boolean val = bh.BookHotel(bmodel, hid, custid);
													if (val) {
														System.out.println("Hotel book succesfully.....");
														System.out
																.println("Do you want to give reviews and ratings(yes/no)");
														String confrim = sc.nextLine();
														if (confrim.equals("yes")) {
															System.out.println("Enter your name");
															String cn = sc.nextLine();
															int custid1 = cst.custIdByName(cuser);
															System.out.println("Enter your review");
															String review = sc.nextLine();
															System.out.println("Enter rating between 1 to 5");
															int rating = sc.nextInt();
															ReviewModel rmodel = new ReviewModel();
															rmodel.setReview(review);
															rmodel.setRating(rating);

															boolean re = rs.AddReview(hid, custid1, rmodel);
															if (re) {
																System.out.println("Thank you for review \n ");
															} else {
																System.out.println("Visit again...Have a good day..");
															}

														} else {
															System.out.println("visit again");
														}
													} else {
														System.out.println("Sorry Table not available..");
													}

												} else {
													System.out.println("Enter correct hotel name");
												}
												break;
											case 3:
												// *******************search hotel*******************
												int s;
												do {
													System.out.println("---------------------------------------------");
													System.out.println("1:Serch hotel areawise");
													System.out.println("2:Exit");
													System.out
															.println("-----------------------------------------------");
													System.out.println("Enter  your choice");
													s = sc.nextInt();
													switch (s) {
													case 1:
														sc.nextLine();
														List<CityModel> li1 = cs.getAllCities();
														System.out.println("--------------------------------");
														for (CityModel c1 : li1) {
															System.out.println(c1.getCityid() + "\t" + c1.getCityname());
														}
														System.out.println("--------------------------------");
														System.out.println("Enter city name from list");
														String cname = sc.nextLine();
														int cityid1 = cs.getCityIdByName(cname);
														//System.out.println(cityid1);
														if (cityid1 != 0) {

															List<AreaModel> alist1 = as.getAllAreas(cityid1);
															System.out.println("----------------------------------------");
															for (AreaModel a1 : alist1) 
															{
																System.out.println(a1.getAreaid() + "\t" + a1.getAreaname());
															}
															System.out.println("------------------------------------------");
															System.out.println("Enter Area name from given list");
															String an = sc.nextLine();
															int Aid = as.getAreaIdByName(an);
															if (Aid != 0) 
															{
																List<HotelModel> hm = hs.getAllHotels(Aid);
																System.out.println("-----------------------------------------");
																for (HotelModel h : hm) {
																	System.out.println(h.getHid() + "\t" + h.getHname()+ "\t" + h.getHcon() + "\t" + h.getHadd()+ "\t");

																}
															} else {
																System.out.println(
																		"Area Not present Choose another area");
																List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(a1.getAreaid() + "\t"
																			+ a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out
																		.println("Enter Area name from given list");
																String an1 = sc.nextLine();
																int Aid1 = as.getAreaIdByName(an);
																if (Aid1 != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
															}
															}
														} else {
															System.out.println(
																	"City not present\n Do you want to change city(yes/no)");
															String y1 = sc.nextLine();
															if (y1.equals("yes")) {
																List<CityModel> list11 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list11) {
																	System.out.println(c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname1 = sc.nextLine();
																int cityid11 = cs.getCityIdByName(cname1);
																//System.out.println(cityid11);
																if (cityid11 != 0) {

																	List<AreaModel> alist1 = as.getAllAreas(cityid11);
																	System.out.println("----------------------------------------");
																	for (AreaModel a1 : alist1) 
																	{
																		System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																	}
																	System.out.println("------------------------------------------");
																	System.out.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																	} 
																	else 
																	{
																		System.out.println("Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String an1 = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																		
																	  }
																	}
																} else {
																	System.out.println("city not present");
																}
															}

														}

														break;

													}
												} while (s != 2);
												break;
											}
										} while (bt != 3);

									} else {
										System.out.println("View veg Non veg hotels");

										List<HotelModel> li = hs.getAllHotels(aid);
										System.out.println("----------------------------------");
										for (HotelModel h1 : li) {
											System.out.println(h1.getHid() + "\t" + h1.getHname() + "\t" + h1.getHcon()+ "\t" + h1.getHadd() + "\t");
										}
										int bt;
										do {

											System.out.println("1:Book hotel");
											System.out.println("2:Search Hotel");
											System.out.println("3:Exit\n");
											System.out.println("Enter your choice");
											bt = sc.nextInt();
											switch (bt) {
											case 1:
												sc.nextLine();
												System.out.println("Enter hotel Name");
												String hname = sc.nextLine();
												int hid = hs.getHotelIdByNameAndArea(hname, aid);
												System.out.println(hid);
												if (hid != 0) {
													System.out.println("Enter time");
													String time = sc.nextLine();
													System.out.println("Enter Date");
													String date = sc.nextLine();
													int custid = cst.custIdByName(cuser);
													BookModel bmodel = new BookModel();
													bmodel.setBooktime(time);
													bmodel.setBookdate(date);
													boolean val = bh.BookHotel(bmodel, hid, custid);
													if (val) {
														System.out.println("Hotel book succesfully.....");
														System.out
																.println("Do you want to give reviews and ratings(yes/no)");
														String confrim = sc.nextLine();
														if (confrim.equals("yes")) {
															System.out.println("Enter your name");
															String cn = sc.nextLine();
															int custid1 = cst.custIdByName(cuser);
															System.out.println("Enter your review");
															String review = sc.nextLine();
															System.out.println("Enter rating between 1 to 5");
															int rating = sc.nextInt();
															ReviewModel rmodel = new ReviewModel();
															rmodel.setReview(review);
															rmodel.setRating(rating);

															boolean re = rs.AddReview(hid, custid1, rmodel);
															if (re) {
																System.out.println(
																		"Thank you for review \n Visit again...Have a good day..");
															} else {
																System.out.println(" Visit again...Have a good day..");
															}

														} else {
															System.out.println("Thank you..");
														}
													} else {
														System.out.println("Sorry Table not available..");
													}

												} else {
													System.out.println("Enter correct Hotel Name");
												}
												break;
											case 2:
												// *******************search hotel*******************
												int s;
												do {
													System.out.println("---------------------------------------------");
													System.out.println("1:Serch hotel areawise");
													System.out.println("2:Exit");
													System.out
															.println("-----------------------------------------------");
													System.out.println("Enter  your choice");
													s = sc.nextInt();
													switch (s) {
													case 1:
														sc.nextLine();
														List<CityModel> list1 = cs.getAllCities();
														System.out.println("--------------------------------");
														for (CityModel c1 : list1) {
															System.out
																	.println(c1.getCityid() + "\t" + c1.getCityname());
														}
														System.out.println("--------------------------------");
														System.out.println("Enter city name from list");
														String cname = sc.nextLine();
														int cityid1 = cs.getCityIdByName(cname);
														//System.out.println(cityid1);
														if (cityid1 != 0) {

															List<AreaModel> alist1 = as.getAllAreas(cityid1);
															System.out.println(
																	"----------------------------------------");
															for (AreaModel a1 : alist1) {
																System.out.println(
																		a1.getAreaid() + "\t" + a1.getAreaname());
															}
															System.out.println(
																	"------------------------------------------");
															System.out.println("Enter Area name from given list");
															String an = sc.nextLine();
															int Aid = as.getAreaIdByName(an);
															if (Aid != 0) {
																List<HotelModel> hm = hs.getAllHotels(Aid);
																System.out.println(
																		"-----------------------------------------");
																for (HotelModel h : hm) {
																	System.out.println(h.getHid() + "\t" + h.getHname()+ "\t" + h.getHcon() + "\t" + h.getHadd()+ "\t");

																}
															} else {
																System.out.println(
																		"Area Not present Choose another area");
																List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(a1.getAreaid() + "\t"
																			+ a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out
																		.println("Enter Area name from given list");
																String an1 = sc.nextLine();
																int Aid1 = as.getAreaIdByName(an);
																if (Aid1 != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
																
															  }
															}
														} else {
															System.out.println(
																	"City not present\n Do you want to change city(yes/no)");
															String y1 = sc.nextLine();
															if (y1.equals("yes")) {
																List<CityModel> list11 = cs.getAllCities();
																System.out.println("--------------------------------");
																for (CityModel c1 : list11) {
																	System.out.println(
																			c1.getCityid() + "\t" + c1.getCityname());
																}
																System.out.println("--------------------------------");
																System.out.println("Enter city name from list");
																String cname1 = sc.nextLine();
																int cityid11 = cs.getCityIdByName(cname1);
															//	System.out.println(cityid11);
																if (cityid11 != 0) {

																	List<AreaModel> alist1 = as.getAllAreas(cityid11);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an = sc.nextLine();
																	int Aid = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																	} else {
																		System.out.println(
																				"Area Not present Choose another area");
																		List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out
																				.println("Enter Area name from given list");
																		String an1 = sc.nextLine();
																		int Aid1 = as.getAreaIdByName(an);
																		if (Aid1 != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																			}
																	 }
																	}
																} else {
																	System.out.println("city not present");
																}
															}

														}

														break;

													}
												} while (s != 2);
												break;
											}
										} while (bt != 3);

									}
								} // else close
								else if (achange.equals("yes"))
								{
									int cityid = cs.getCityIdByName(cityName);
									List<AreaModel> alist = as.getAllAreas(cityid);
									System.out.println("--------------------------------");
									for (AreaModel a : alist) {
										System.out.println(a.getAreaid() + "\t" + a.getAreaname());
									}
									System.out.println("Select areaname from list");
									String areaname = sc.nextLine();
									int aid = as.getAreaIdByName(areaname);
									if (aid != 0) {
										System.out.println("Enter Food type you prefer...");
										String ftype = sc.nextLine();
										if (ftype.equals("veg")) {
											System.out.println("All Veg Hotel List");
											List<HotelModel> li = hs.getAllvegHotels(areaname, ftype);
											System.out.println("---------------------------------------");
											for (HotelModel hi : li) {
												System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"+ hi.getHcon() + "\t" + hi.getHadd() + "\t");
											}
											int bt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:Search Hotel");
												System.out.println("3:Exit\n");
												System.out.println("Enter your choice");
												bt = sc.nextInt();
												switch (bt) {
												case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();

														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Table book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) {
																	System.out.println("Thank you for review \n Visit again...Have a good day..");
																} else {
																	System.out.println("----");
																}

															} else {
																// review confrim.equals("no")
																System.out.println("");
															}
														} else {
															// table book succesfully...
															System.out.println("Sorry Table not available..");
														}

													}
													break;
												case 2:
													// *******************search hotel*******************
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> list1 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel c6 : list1) 
															{
																System.out.println(c6.getCityid() + "\t" + c6.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															//System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println("------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println("-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");
																	List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an1 = sc.nextLine();
																	int Aid1 = as.getAreaIdByName(an);
																	if (Aid != 0) {
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) {
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																  }
																}
															} else {
																System.out.println(
																		"City not present\n Do you want to change city(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println(
																			"--------------------------------");
																	for (CityModel c15 : list11) {
																		System.out.println(c15.getCityid() + "\t"
																				+ c15.getCityname());
																	}
																	System.out.println(
																			"--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	System.out.println(cityid11);
																	if (cityid11 != 0) {

																		List<AreaModel> alist1 = as
																				.getAllAreas(cityid11);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out.println(
																				"Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																			}
																		} else {
																			System.out.println(
																					"Area Not present Choose another area");

																			System.out.println(
																					"Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1 = as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}
																		  }
																			
																		 	
																		}
																		}
																	} else {
																		System.out.println("city not present");
																	}
																}

															}
													}while (s != 2);
										          break;
												}
											} while (bt != 3); // book table do completed...

										} // if(veg) completed...

										else if (ftype.equals("non-veg")) {
											System.out.println("All non Veg Hotel List");
											List<HotelModel> li = hs.getAllvegHotels(areaname, ftype);
											System.out.println("---------------------------------------");
											for (HotelModel hi : li) {
												System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"+ hi.getHcon() + "\t" + hi.getHadd() + "\t" + hi.getHtype());

											}
											int nonbt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:Search Hotel");
												System.out.println("3:Exit\n");
												System.out.println("Enter your choice");
												nonbt = sc.nextInt();
												switch (nonbt) {
												case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();
														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Hotel book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) 
																{
																	System.out.println("Thank you for review ");
																} 
																else 
																{
																	System.out.println("Visit again...\n Have a good day..");
																}

															} else {
																// if i never want to give review
																// goes to book hotel
																System.out.println("");
															}
														} else {
															System.out.println("Sorry Table not available..");
															System.out.println("Book Another Hotel..");
															System.out.println("All non Veg Hotel List");
															List<HotelModel> lst1 = hs.getAllvegHotels(areaname, ftype);
															System.out
																	.println("---------------------------------------");
															for (HotelModel hi : lst1) 
															{
																System.out.println(hi.getHid() + "\t" + hi.getHname()+ "\t" + hi.getHcon() + "\t" + hi.getHadd()+ "\t" + hi.getHtype());

															}

															do {

																System.out.println("1:Book hotel");
																System.out.println("2:Search Hotel");
																System.out.println("3:Exit\n");
																System.out.println("Enter your choice");
																nonbt = sc.nextInt();
																switch (nonbt) 
																{
																case 1:
																	sc.nextLine();
																	System.out.println("Enter hotel Name");
																	String hname1 = sc.nextLine();
																	int hid1 = hs.getHotelIdByNameAndArea(hname1, aid);
																	System.out.println(hid1);
																	if (hid1 != 0) {
																		System.out.println("Enter time");
																		String time1 = sc.nextLine();
																		System.out.println("Enter Date");
																		String date1 = sc.nextLine();
																		int custid1 = cst.custIdByName(cuser);
																		BookModel bmodel1 = new BookModel();
																		bmodel1.setBooktime(time1);
																		bmodel1.setBookdate(date1);
																		boolean ans1 = bh.BookHotel(bmodel1, hid1,
																				custid1);
																		if (ans1) {
																			System.out.println(
																					"Hotel book succesfully.....");
																			System.out.println(
																					"Do you want to give reviews and ratings(yes/no)");
																			String confrim = sc.nextLine();
																			if (confrim.equals("yes")) {
																				System.out.println("Enter your name");
																				String cn = sc.nextLine();
																				int cid1 = cst.custIdByName(cuser);
																				System.out.println("Enter your review");
																				String review = sc.nextLine();
																				System.out.println(
																						"Enter rating between 1 to 5");
																				int rating = sc.nextInt();
																				ReviewModel rmodel = new ReviewModel();
																				rmodel.setReview(review);
																				rmodel.setRating(rating);

																				boolean re = rs.AddReview(hid1, cid1,
																						rmodel);
																				if (re) {
																					System.out.println(
																							"Thank you for review ");
																				} else {
																					System.out.println(
																							"Visit again...\n Have a good day..");
																				}

																			} else {
																				// if i never want to give review
																				// goes to book hotel
																				System.out.println("");
																			}
																		} else {
																			System.out.println(
																					"Sorry Table not available");
																		}
																	} else {
																		System.out.println("Enter Correct Hotel Name");
																	}
																	break;
																case 2:
																	// *******************search
																	// hotel*******************
																	int s;
																	do {
																		System.out.println(
																				"---------------------------------------------");
																		System.out.println("1:Serch hotel areawise");
																		System.out.println("2:Exit");
																		System.out.println(
																				"-----------------------------------------------");
																		System.out.println("Enter  your choice");
																		s = sc.nextInt();
																		switch (s) {
																		case 1:
																			sc.nextLine();
																			List<CityModel> list1 = cs.getAllCities();
																			System.out.println(
																					"--------------------------------");
																			for (CityModel c14 : list1) {
																				System.out.println(c14.getCityid()
																						+ "\t" + c14.getCityname());
																			}
																			System.out.println(
																					"--------------------------------");
																			System.out.println(
																					"Enter city name from list");
																			String cname = sc.nextLine();
																			int cityid1 = cs.getCityIdByName(cname);
																			//System.out.println(cityid1);
																			if (cityid1 != 0) {

																				List<AreaModel> alist1 = as
																						.getAllAreas(cityid1);
																				System.out.println(
																						"----------------------------------------");
																				for (AreaModel a1 : alist1) {
																					System.out.println(a1.getAreaid()
																							+ "\t" + a1.getAreaname());
																				}
																				System.out.println(
																						"------------------------------------------");
																				System.out.println(
																						"Enter Area name from given list");
																				String an = sc.nextLine();
																				int Aid = as.getAreaIdByName(an);
																				if (Aid != 0) {
																					List<HotelModel> hm = hs
																							.getAllHotels(Aid);
																					System.out.println(
																							"-----------------------------------------");
																					for (HotelModel h : hm) {
																						System.out.println(h.getHid()
																								+ "\t" + h.getHname()
																								+ "\t" + h.getHcon()
																								+ "\t" + h.getHadd()
																								+ "\t");

																					}
																				} else {
																					System.out.println(
																							"Area Not present Choose another area");

																					System.out.println(
																							"Area Not present Choose another area");
																					List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																					System.out.println(
																							"----------------------------------------");
																					for (AreaModel a1 : alist1) {
																						System.out.println(a1.getAreaid() + "\t"
																								+ a1.getAreaname());
																					}
																					System.out.println(
																							"------------------------------------------");
																					System.out
																							.println("Enter Area name from given list");
																					String an1 = sc.nextLine();
																					int Aid1 = as.getAreaIdByName(an);
																					if (Aid != 0) {
																						List<HotelModel> hm = hs.getAllHotels(Aid);
																						System.out.println(
																								"-----------------------------------------");
																						for (HotelModel h : hm) {
																							System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																						}
																				  }
																				
																				 
																				  }
																				}
																			 else {
																				System.out.println(
																						"City not present\n Do you want to change city.(yes/no)");
																				String y1 = sc.nextLine();
																				if (y1.equals("yes")) {
																					List<CityModel> list11 = cs
																							.getAllCities();
																					System.out.println(
																							"--------------------------------");
																					for (CityModel c13 : list11) {
																						System.out.println(c13
																								.getCityid() + "\t"
																								+ c13.getCityname());
																					}
																					System.out.println(
																							"--------------------------------");
																					System.out.println(
																							"Enter city name from list");
																					String cname1 = sc.nextLine();
																					int cityid11 = cs
																							.getCityIdByName(cname1);
																					System.out.println(cityid11);
																					if (cityid11 != 0) {

																						List<AreaModel> alist1 = as
																								.getAllAreas(cityid11);
																						System.out.println(
																								"----------------------------------------");
																						for (AreaModel a1 : alist1) {
																							System.out.println(a1
																									.getAreaid() + "\t"
																									+ a1.getAreaname());
																						}
																						System.out.println(
																								"------------------------------------------");
																						System.out.println(
																								"Enter Area name from given list");
																						String an = sc.nextLine();
																						int Aid = as
																								.getAreaIdByName(an);
																						if (Aid != 0) {
																							List<HotelModel> hm = hs
																									.getAllHotels(Aid);
																							System.out.println(
																									"-----------------------------------------");
																							for (HotelModel h : hm) {
																								System.out.println(h
																										.getHid() + "\t"+ h.getHname()
																										+ "\t"
																										+ h.getHcon()
																										+ "\t"
																										+ h.getHadd()
																										+ "\t");

																							}
																						} else {
																							System.out.println(
																									"Area Not present Choose another area");

																							System.out.println(
																									"Area Not present Choose another area");
																							List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																							System.out.println(
																									"----------------------------------------");
																							for (AreaModel a1 : alist1) {
																								System.out.println(a1.getAreaid() + "\t"
																										+ a1.getAreaname());
																							}
																							System.out.println(
																									"------------------------------------------");
																							System.out
																									.println("Enter Area name from given list");
																							String an1 = sc.nextLine();
																							int Aid1 = as.getAreaIdByName(an);
																							if (Aid != 0) {
																								List<HotelModel> hm = hs.getAllHotels(Aid);
																								System.out.println(
																										"-----------------------------------------");
																								for (HotelModel h : hm) {
																									System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																								}
																						  }
																						
                                                                                         
																						  }
																						}
																					} else {
																						System.out.println(
																								"city not present");
																					}
																				}

																			}

																			break;

																		
																	} while (s != 2);
																	break;
																}
															} while (nonbt != 3);
														} // else close
													} else {
														System.out.println("Enter Correct Hotel Name and password..");
													}
													break;
												case 2:
													// *******************search hotel*******************
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> list1 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel c12 : list1) {
																System.out.println(
																		c12.getCityid() + "\t" + c12.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															//System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"
																				+ h.getHname() + "\t" + h.getHcon()
																				+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");
																	break;
																}
															} else {
																System.out.println(
																		"City not present\n Do you want to change city(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println(
																			"--------------------------------");
																	for (CityModel c11 : list11) {
																		System.out.println(c11.getCityid() + "\t"
																				+ c11.getCityname());
																	}
																	System.out.println(
																			"--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	//System.out.println(cityid11);
																	if (cityid11 != 0) {

																		List<AreaModel> alist1 = as
																				.getAllAreas(cityid11);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out.println(
																				"Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm) {
																				System.out.println(h.getHid() + "\t"
																						+ h.getHname() + "\t"
																						+ h.getHcon() + "\t"
																						+ h.getHadd() + "\t");

																			}
																		} else {
																			System.out.println(
																					"Area Not present Choose another area");

																			System.out.println(
																					"Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1 = as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}
																		  }
																		
																			
																			
																		   }
																		}
																	} else {
																		System.out.println("city not present");
																	}
																}

															}

															break;

														
													} while (s != 2);
													break;
												}
											} while (nonbt != 3); // non veg do completed..
										} // non veg close

										else {

											System.out.println("All Veg Nonveg Hotel List");
											List<HotelModel> li = hs.getAllHotels(aid);
											System.out.println("---------------------------------------");
											for (HotelModel hi : li) {
												System.out.println(hi.getHid() + "\t" + hi.getHname() + "\t"
														+ hi.getHcon() + "\t" + hi.getHadd() + "\t");

											}
											int bt;
											do {

												System.out.println("1:Book hotel");
												System.out.println("2:Search Hotel");
												System.out.println("3:Exit\n");
												System.out.println("Enter your choice");
												bt = sc.nextInt();
												switch (bt)
												{
												 case 1:
													sc.nextLine();
													System.out.println("Enter hotel Name");
													String hname = sc.nextLine();
													int hid = hs.getHotelIdByNameAndArea(hname, aid);
													System.out.println(hid);
													if (hid != 0) {
														System.out.println("Enter time");
														String time = sc.nextLine();
														System.out.println("Enter Date");
														String date = sc.nextLine();
														int custid = cst.custIdByName(cuser);
														BookModel bmodel = new BookModel();
														bmodel.setBooktime(time);
														bmodel.setBookdate(date);
														boolean val = bh.BookHotel(bmodel, hid, custid);
														if (val) {
															System.out.println("Hotel book succesfully.....");
															System.out.println(
																	"Do you want to give reviews and ratings(yes/no)");
															String confrim = sc.nextLine();
															if (confrim.equals("yes")) {
																System.out.println("Enter your name");
																String cn = sc.nextLine();
																int custid1 = cst.custIdByName(cuser);
																System.out.println("Enter your review");
																String review = sc.nextLine();
																System.out.println("Enter rating between 1 to 5");
																int rating = sc.nextInt();
																ReviewModel rmodel = new ReviewModel();
																rmodel.setReview(review);
																rmodel.setRating(rating);

																boolean re = rs.AddReview(hid, custid1, rmodel);
																if (re) {
																	System.out.println(
																			"Thank you for review \n Visit again...Have a good day..");
																} else {
																	System.out.println("----");
																}

															} 
															else
															{
																System.out.println("Have a good day");
															}
														} 
														else
														{
															System.out.println("Sorry Table not available..");
														}

													}
													else
													{
														System.out.println("Enter correct Hotel Name ");
													}
													break;
												case 2:
													// *******************search hotel*******************
													int s;
													do {
														System.out.println(
																"---------------------------------------------");
														System.out.println("1:Serch hotel areawise");
														System.out.println("2:Exit");
														System.out.println(
																"-----------------------------------------------");
														System.out.println("Enter  your choice");
														s = sc.nextInt();
														switch (s) {
														case 1:
															sc.nextLine();
															List<CityModel> list1 = cs.getAllCities();
															System.out.println("--------------------------------");
															for (CityModel ct2 : list1) 
															{
																System.out.println(
																		ct2.getCityid() + "\t" + ct2.getCityname());
															}
															System.out.println("--------------------------------");
															System.out.println("Enter city name from list");
															String cname = sc.nextLine();
															int cityid1 = cs.getCityIdByName(cname);
															//System.out.println(cityid1);
															if (cityid1 != 0) {

																List<AreaModel> alist1 = as.getAllAreas(cityid1);
																System.out.println(
																		"----------------------------------------");
																for (AreaModel a1 : alist1) {
																	System.out.println(
																			a1.getAreaid() + "\t" + a1.getAreaname());
																}
																System.out.println(
																		"------------------------------------------");
																System.out.println("Enter Area name from given list");
																String an = sc.nextLine();
																int Aid = as.getAreaIdByName(an);
																if (Aid != 0) {
																	List<HotelModel> hm = hs.getAllHotels(Aid);
																	System.out.println(
																			"-----------------------------------------");
																	for (HotelModel h : hm) {
																		System.out.println(h.getHid() + "\t"
																				+ h.getHname() + "\t" + h.getHcon()
																				+ "\t" + h.getHadd() + "\t");

																	}
																} else {
																	System.out.println(
																			"Area Not present Choose another area");

																	System.out.println(
																			"Area Not present Choose another area");
																	List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																	System.out.println(
																			"----------------------------------------");
																	for (AreaModel a1 : alist1) {
																		System.out.println(a1.getAreaid() + "\t"
																				+ a1.getAreaname());
																	}
																	System.out.println(
																			"------------------------------------------");
																	System.out
																			.println("Enter Area name from given list");
																	String an1 = sc.nextLine();
																	int Aid1 = as.getAreaIdByName(an);
																	if (Aid != 0) 
																	{
																		List<HotelModel> hm = hs.getAllHotels(Aid);
																		System.out.println(
																				"-----------------------------------------");
																		for (HotelModel h : hm) 
																		{
																			System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																		}
																  }
																
																	
																  }
																}
															 else {
																System.out.println(
																		"City not present\n Do you want to change city(yes/no)");
																String y1 = sc.nextLine();
																if (y1.equals("yes")) {
																	List<CityModel> list11 = cs.getAllCities();
																	System.out.println(
																			"--------------------------------");
																	for (CityModel ct1 : list11) {
																		System.out.println(ct1.getCityid() + "\t"
																				+ ct1.getCityname());
																	}
																	System.out.println(
																			"--------------------------------");
																	System.out.println("Enter city name from list");
																	String cname1 = sc.nextLine();
																	int cityid11 = cs.getCityIdByName(cname1);
																	//System.out.println(cityid11);
																	if (cityid11 != 0) {

																		List<AreaModel> alist1 = as
																				.getAllAreas(cityid11);
																		System.out.println(
																				"----------------------------------------");
																		for (AreaModel a1 : alist1) {
																			System.out.println(a1.getAreaid() + "\t"
																					+ a1.getAreaname());
																		}
																		System.out.println(
																				"------------------------------------------");
																		System.out.println(
																				"Enter Area name from given list");
																		String an = sc.nextLine();
																		int Aid = as.getAreaIdByName(an);
																		if (Aid != 0) {
																			List<HotelModel> hm = hs.getAllHotels(Aid);
																			System.out.println(
																					"-----------------------------------------");
																			for (HotelModel h : hm)
																			{
																				System.out.println(h.getHid() + "\t"+ h.getHname() + "\t"+ h.getHcon() + "\t"+ h.getHadd() + "\t");

																			}
																		}
																		else 
																		{
																			System.out.println("Area Not present Choose another area");

																			System.out.println(
																					"Area Not present Choose another area");
																			List<AreaModel> Alist1 = as.getAllAreas(cityid1);
																			System.out.println(
																					"----------------------------------------");
																			for (AreaModel a1 : alist1) {
																				System.out.println(a1.getAreaid() + "\t"
																						+ a1.getAreaname());
																			}
																			System.out.println(
																					"------------------------------------------");
																			System.out
																					.println("Enter Area name from given list");
																			String an1 = sc.nextLine();
																			int Aid1 = as.getAreaIdByName(an);
																			if (Aid != 0) {
																				List<HotelModel> hm = hs.getAllHotels(Aid);
																				System.out.println(
																						"-----------------------------------------");
																				for (HotelModel h : hm) {
																					System.out.println(h.getHid() + "\t"+ h.getHname() + "\t" + h.getHcon()+ "\t" + h.getHadd() + "\t");

																				}
																		  }
																		
																			
																			
																		}
																	} else {
																		System.out.println("city not present");
																	}
																}

															}

															break;
                                                       }
													} while (s != 2);
													break;
												}
											} while (bt != 3);

										} // else close
									} else {
										System.out.println("Enter correct Area name");
									}

								}
							}
						 
					}
					else 
						{
							System.out.println("Invalid username and password\n");
						}
					
						break;

					case 2:

						sc.nextLine();
						System.out.println("********************* Customer Registration *******************");
						System.out.println("---------------------------------------------------------");
						System.out.println("Enter city name");
						String cname = sc.nextLine();
						System.out.println("Enter Area name");
						String areaname = sc.nextLine();
						int ctyid = cs.getCityIdByName(cname);
						int aid = as.getAreaIdByName(areaname);
						System.out.println("Enter customer name");
						String custname = sc.nextLine();
						System.out.println("Enter  mobile number");
						String mobno = sc.nextLine();
						System.out.println("Enter Email Address ");
						String email = sc.nextLine();
						System.out.println("Enter Food Type");
						String foodtyp = sc.nextLine();

						CustomerModel custmodel = new CustomerModel();
						custmodel.setCustName(custname);
						custmodel.setCustNo(mobno);
						custmodel.setCustEmail(email);
						custmodel.setFtype(foodtyp);
						custmodel.setCityid(ctyid);
						custmodel.setAreaid(aid);

						boolean a1 = cst.AddCustomer(custmodel);
						if (a1) {
							System.out.println(" Customer Registration Sucessful..");
						} else {
							System.out.println("Customer Registration unsucessful..");
						}

						break;
					}

				} while (ch4 != 3); // customer sing in
			
			}
		} while (choice != 3); // main do
	}

	
	}

