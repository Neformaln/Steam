package test.steam.services;

import test.webdriver.elements.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 15.06.2017.
 */
public class CommonFunctions {
    public Properties readProperties(String path){
        File ff = new File(getClass().getClassLoader().getResource(path).getFile());
        FileInputStream fis= null;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(ff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return property;
    }

    public List<Integer> getListOfDiscounts(List<Label> list){
        List<Integer> discounts = new ArrayList<>();
        Pattern pat = Pattern.compile("[0-9]+");
        for (Label el : list) {
            Matcher matcher = pat.matcher(el.getText());
            while (matcher.find()) {
                discounts.add(new Integer(matcher.group()));
            }
        }

        return discounts;
    }

    public int getIndMaxDiscount(List<Integer> discounts){
        int max = discounts.get(0);
        int ind =0;
        int  maxInd=0;
        for (Integer discount : discounts) {
            if (discount > max) {
                max = discount;
                maxInd=ind;
            }
            ind ++;
        }

        return maxInd;
    }

    public boolean isFullDownLoad(String filePath,long size){
        File f = new File(filePath);
        if (f.exists()) {
            return f.length()==size;
        } else {
            return false;
        }

    }
}
