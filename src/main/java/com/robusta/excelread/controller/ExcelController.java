package com.robusta.excelread.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.robusta.excelread.constant.ActionType;
import com.robusta.excelread.dao.Excel;
import com.robusta.excelread.function.RobustaFunctions;
import com.robusta.excelread.model.JsonResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Component
@Path("/excel")
@Api(value = "/excel") // Enables Swagger Documentation
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class ExcelController {

	
	@POST
    @Path("/")
	// success response
    @ApiOperation(value = "Excel Service Operation")
    public JsonResponse excel(Excel excel) {
		if((excel.getAction().isEmpty() && 
				excel.getFilePath().isEmpty() && 
				excel.getColIndex() == null && 
				excel.getRowIndex() == null) || 
			(excel.getAction().equals(ActionType.SETCELLDATA.toString()) && excel.getNewValue() == null )) {
			return new JsonResponse("failed","please enter all requirement");
		}
		
		
		RobustaFunctions robustaFunctions = new RobustaFunctions();
        try {
			return new JsonResponse("success",robustaFunctions.excelOperations(excel));
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResponse("failed",e.toString());
		}
    }

}
