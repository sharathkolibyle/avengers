package com.cisco.aws;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OuiRetrieve {
	private StringBuilder builder = null;


    private void downloadUsingStream() throws IOException {

        LineNumberReader in = null;
        FileReader fr = null;
        BufferedReader br =null;
        Matcher m = Pattern.compile("^\\s*([0-9a-fA-f]{2})([0-9a-fA-f]{2})([0-9a-fA-f]{2})\\s+\\(base 16\\)\\s+(.*)$").matcher("");
        try {
            //fr = new FileReader("oui.txt");
        	
        	
        	InputStream is = this.getClass().getResourceAsStream("/oui.txt");
        	//System.out.println("Inputsteram"+is);
        	br = new BufferedReader(new InputStreamReader(is));
//            fr = new FileReader(new InputStreamReader(is));
//            in = new LineNumberReader(fr);
        	//System.out.println("Loaded out.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //System.out.println("Br object"+br);

        if (br != null) {
            builder = new StringBuilder();
            String s = br.readLine();
            //System.out.println("Text file is " + s);
            String ouiMapKey = null;
            String ouiManufacturer = null;
            while (s != null) {
                m.reset(s);
                if (m.matches()) {
                    ouiMapKey = m.group(1) + m.group(2) + m.group(3);
                    ouiManufacturer = m.group(4);
                    //System.out.println("Key is " + ouiMapKey + " Value is " + ouiManufacturer);
                    builder.append(ouiMapKey + "," + ouiManufacturer + "\n");
                } // if m matches
                s = br.readLine();
            } // while s not null
        }
    }


    public String getOUIFromIEEE(String mac) {
        try {
            downloadUsingStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] formatedMac = mac.split(":");
        String newMac = formatedMac[0] + formatedMac[1] + formatedMac[2];
        String[] OUI = builder.toString().split("\n");
        int index = -1;
        for (int i = 0; i < OUI.length; i++) {
            if (OUI[i].indexOf(newMac) > -1) {
                index = i;
            }
        }

        return index > -1 ? OUI[index].split(",")[1] : null;
    }
}
