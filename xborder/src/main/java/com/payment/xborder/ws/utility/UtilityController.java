package com.payment.xborder.ws.utility;

import com.payment.xborder.enums.CompanyStatus;
import com.payment.xborder.exception.BusinessException;
import com.payment.xborder.exception.ValidationException;
import com.payment.xborder.ws.AbstractRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping ("utility")
public class UtilityController extends AbstractRestController
{

   public UtilityController() {
   }

   @GetMapping ("/getAllCompanyStatus")
   public ResponseEntity<List<CompanyStatus>> getAllCompanyStatus() {
      try {
         List <CompanyStatus> companyStatuses =
               Arrays.asList(CompanyStatus.values()).stream()
                     .collect(Collectors.toList());
         return new ResponseEntity<List<CompanyStatus>>(companyStatuses,
                                                        HttpStatus.OK);
      } catch (ValidationException | BusinessException e) {
         HttpStatus httpStatus = (e instanceof ValidationException) ? HttpStatus.BAD_REQUEST
                                                                    : HttpStatus.valueOf(400);
         return new ResponseEntity<List<CompanyStatus>>(Collections.EMPTY_LIST,
                                                        httpStatus);
      }

   }

}





