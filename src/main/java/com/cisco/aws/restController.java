package com.cisco.aws;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class restController {

    private static final Logger logger = LoggerFactory.getLogger(restController.class);

    @GetMapping("/ep")
    public String show() {
        logger.info("=====================================================================");
        return "Request recieved from the client";
    }

    @PostMapping("/ep2")
    public String getEndpointFromFB(@RequestBody EndPoint endPoint){
        String endP = endPoint.getMac();
        //String endP = endPoint.getMac();
        OuiRetrieve oui = null; 
        FingerBank fb = new FingerBank();
        logger.info("________________________________________________");
        logger.info(endP);
        logger.info("________________________________________________");
        String result = fb.postDeviceName(endPoint);
        
        
        if(result.equals("Unknown"))
        {
        	System.out.println("Unknown hit");
        	oui = new OuiRetrieve();
        	result = oui.getOUIFromIEEE(endP);
        }
        //EndPoint ep = new EndPoint();
        //ep.setMac("11:11:11:11:11:11");
        return result;
    }
    
    @PostMapping("/ep3")
    public String getEndpointFromIEEE(@RequestBody EndPoint endPoint){
        String endP = endPoint.getMac();
        OuiRetrieve oui = new OuiRetrieve();    
        logger.info("________________________________________________");
        logger.info("MAC address inside getEndpointFromIEEE " + endP);
        logger.info("________________________________________________");
        String result = oui.getOUIFromIEEE(endP);
        //EndPoint ep = new EndPoint();
        //ep.setMac("11:11:11:11:11:11");
        return result;
    }

    @PostMapping("/ep")
    public oui create(@RequestBody Map<String, String> body) {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("=====================================================================");
        logger.info(body.toString());
        try {
            EndPoint ep = mapper.readValue(body.toString(), EndPoint.class);
            String mac = ep.getMac();
            logger.info(mac);
            oui newOui = new oui(mac, "Demo Company");
            return newOui;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
