package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.print("Entre com o nome do departamento: ");
			String departmentName = input.nextLine();

			System.out.println("Entre com os dados do empregado:");
			System.out.print("Nome: ");
			String name = input.nextLine();
			System.out.print("Nível: ");
			String workerLevel = input.nextLine();
			System.out.print("Salário base: ");
			double baseSalary = input.nextDouble();

			Worker worker = new Worker(name, WorkerLevel.valueOf(workerLevel), baseSalary,
					new Department(departmentName));

			System.out.print("Quantos contratos terá o empregado: ");
			int numberOfContracts = input.nextInt();

			for (int i = 1; i <= numberOfContracts; i++) {
				System.out.println("\nEntre com os dados do " + i + "º contrato:");

				System.out.print("Data (DD/MM/YYYY): ");
				Date contractDate = sdf.parse(input.next());
				System.out.print("Valor por hora: ");
				double valuePerHour = input.nextDouble();
				System.out.print("Duração (total de horas): ");
				int hours = input.nextInt();

				HourContract hourContract = new HourContract(contractDate, valuePerHour, hours);
				worker.addContract(hourContract);
			}

			System.out.print("\nEntre com o mês e ano para calcular o salário (MM/YYYY): ");
			String monthAndYear = input.next();
			int month = Integer.parseInt(monthAndYear.substring(0, 2));
			int year = Integer.parseInt(monthAndYear.substring(3));

			System.out.println("Nome: " + worker.getName());
			System.out.println("Departamento: " + worker.getDepartment().getName());
			System.out.println("Renda em " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
			
		} catch (RuntimeException e) {
			System.out.println("Você inseriu um tipo de dado inapropriado para a operação!");
			System.out.println("Reinicie o programa para tentar novamente.");
		} catch (ParseException e) {
			System.out.println("Não foi possível converter a data do contrato.");
			System.out.println(
					"Certifique-se de seguir o parametro (DD/MM/YYYY) sendo DD > 0 e <=31, MM > 0 e <=12, YYYY > 1970");
			System.out.println("Reinicie o programa para tentar novamente.");
		} finally {
			input.close();
		}
	}
}
