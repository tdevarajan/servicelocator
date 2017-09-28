package com.xtime.servicelocator.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xtime.servicelocator.model.Dealer;

@Service
@Transactional
public class DealerService {

	@Autowired
	private javax.sql.DataSource dataSource;

	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Date dt = null;
	java.sql.Date sqlDate = null;

	public List<Dealer> retrieveDealers(String zipcode, String date, String time, String make, String service, String searchOption) {

		String SQL = "select d.dealer_code, dealer_name,address, city, state, zip_code, phone_number, dealer_service_link, dealer_website, latitude, longitude, from_time, to_time, ts.time, dealer_image " +
				" FROM t_dealers d JOIN t_service s ON d.dealer_code = s.dealer_code  JOIN t_time_slots ts ON s.service_id = ts.service_id WHERE 1=1 ";

		if (!StringUtils.isEmpty(zipcode)) {
			SQL = SQL + " AND zip_code = " + zipcode;
		}

		if (!StringUtils.isEmpty(date)) {
			try {
				dt = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SQL = SQL + " AND trunc(available_date) = trunc(to_date('" + new java.sql.Date(dt.getTime()) + "', 'YYYY-MM-dd'))";
		} else {
			SQL = SQL + " AND trunc(available_date) = trunc(to_date('" + new java.sql.Date((new Date()).getTime()) + "', 'YYYY-MM-dd'))"; 
		}

		if (!StringUtils.isEmpty(time) && Integer.parseInt(time) > 0) {
			SQL = SQL + " AND time >= " + Integer.parseInt(time);
		} else {
			if (StringUtils.isEmpty(date) || date.equals(sdf.format(new Date()))) {
				Calendar c = new GregorianCalendar();
				c.setTime(new Date());
				SQL = SQL + " AND time >= " + c.get(Calendar.HOUR_OF_DAY) ;
			} 
		}

		if (!StringUtils.isEmpty(make) && !"Select Make".equals(make)) {
			SQL = SQL + " AND s.make = '" + make + "'";
		}

		if (!StringUtils.isEmpty(service) && !"ANY".equals(service)) {
			SQL = SQL + " AND service_name = '" + service + "'";
		}

		SQL = SQL + " order by d.dealer_code, ts.time";

		System.out.println(SQL);


		Connection c = DataSourceUtils.getConnection(dataSource);

		System.out.println("****************************************************");

		PreparedStatement ps = null;
		Dealer d = null;
		List<Dealer> dealers = new ArrayList<>();        
		String dealerCode = "";
		String timeSlot = "";

		try {
			c = DataSourceUtils.getConnection(dataSource);
			ps = c.prepareStatement(String.format(SQL));
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				if (!dealerCode.equals(rs.getString("dealer_code"))) {
					d = new Dealer();
					d.setName(rs.getString("dealer_name"));
					d.setAddress(rs.getString("address"));
					d.setCity(rs.getString("city"));

					d.setZipcode(rs.getString("zip_code"));
					d.setPhone(rs.getString("phone_number"));
					d.setLongtidue(rs.getString("longitude"));
					d.setLatitude(rs.getString("latitude"));
					d.setHours(rs.getString("from_time") + " TO " + rs.getString("to_time"));

					dealerCode = rs.getString("dealer_code");
					timeSlot="" + rs.getInt("time");
					d.setAvailTimeSlots("" + rs.getInt("time") + ":00");
					d.setWebsite(rs.getString("dealer_website"));
					d.setServiceLink(rs.getString("dealer_service_link"));
					d.setDealerImage(rs.getString("dealer_image"));
					dealers.add(d);
				} else {

					if (!timeSlot.equals(""+rs.getInt("time"))) {
						d.setAvailTimeSlots(d.getAvailTimeSlots()  + ", " + rs.getInt("time")+ ":00");
						timeSlot="" + rs.getInt("time");
					}
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ignore) {
			}

		}
		return dealers;
	}

	public List<Dealer> retrievePartDealers(String zipcode, String part) {
		List<Dealer> dealers = new ArrayList<>();   	
		String SQL = "select d.dealer_code, dealer_name,address, city, state, zip_code, phone_number, dealer_service_link, dealer_website, latitude, longitude, from_time, to_time, dealer_image, part_quantity, part_name " +
				" FROM t_dealers d JOIN t_parts p ON d.dealer_code = p.dealer_code  WHERE 1=1 ";

		if (!StringUtils.isEmpty(zipcode)) {
			SQL = SQL + " AND zip_code = '" + zipcode + "'";
		}

		if (!StringUtils.isEmpty(part) && !"Select Parts".equals(part)) {
			SQL = SQL + " AND part_name = '" + part + "'";
		}


		Connection c = DataSourceUtils.getConnection(dataSource);

		System.out.println("****************************************************");
		System.out.println(SQL);
		
		PreparedStatement ps = null;
		Dealer d = null;

		try {
			c = DataSourceUtils.getConnection(dataSource);
			ps = c.prepareStatement(String.format(SQL));
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				d = new Dealer();
				d.setName(rs.getString("dealer_name"));
				d.setAddress(rs.getString("address"));
				d.setCity(rs.getString("city"));

				d.setZipcode(rs.getString("zip_code"));
				d.setPhone(rs.getString("phone_number"));
				d.setLongtidue(rs.getString("longitude"));
				d.setLatitude(rs.getString("latitude"));
				d.setHours(rs.getString("from_time") + " TO " + rs.getString("to_time"));

				d.setWebsite(rs.getString("dealer_website"));
				d.setServiceLink(rs.getString("dealer_service_link"));
				d.setDealerImage(rs.getString("dealer_image"));
				d.setPartName(rs.getString("part_name"));
				d.setPartQuantity(rs.getString("part_quantity"));
				dealers.add(d);
			}            			

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ignore) {
			}

		}

		return dealers;
	}

}