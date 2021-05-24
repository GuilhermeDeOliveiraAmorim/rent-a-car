package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Data:");
		System.out.print("Model: ");
		String carModel = sc.nextLine();
		System.out.print("Saiu: ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Voltou: ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println(cr.getInvoice().getBasicPayment());
		System.out.println(cr.getInvoice().getTax());
		System.out.println(cr.getInvoice().getTotalPayment());
		
		sc.close();
		
	}

}