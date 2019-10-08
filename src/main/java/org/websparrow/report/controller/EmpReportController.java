package org.websparrow.report.controller;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.websparrow.report.service.EmployeeReportService;

@RestController
@RequestMapping("/employee")
public class EmpReportController {

	@Autowired
	private EmployeeReportService employeeReportService;


  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView model = new ModelAndView();

    model.setViewName("home");
    return model;
  }

  @PostMapping("/export")
  public void export(ModelAndView model, HttpServletResponse response) throws IOException, JRException {
    JasperPrint jasperPrint = null;

    response.setContentType("application/x-download");
    response.setHeader("Content-Disposition", String.format("attachment; filename=\"users.pdf\""));

    OutputStream out = response.getOutputStream();
    jasperPrint = employeeReportService.generateReport();
    JasperExportManager.exportReportToPdfStream(jasperPrint, out);
  }
	/*@GetMapping("/report")
	public String empReport() {

		return employeeReportService.generateReport();
	}*/
}
