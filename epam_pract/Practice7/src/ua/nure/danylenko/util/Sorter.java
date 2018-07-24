package ua.nure.danylenko.util;

import ua.nure.danylenko.constants.Constants;
import ua.nure.danylenko.controller.DOMController;
import ua.nure.danylenko.entity.*;

import javax.management.NotificationBroadcasterSupport;
import java.util.Collections;
import java.util.Comparator;

import static ua.nure.danylenko.constants.Constants.INVALID_XML_FILE;
import static ua.nure.danylenko.constants.Constants.VALID_XML_FILE;

/**
 * Contains static methods for sorting.
 *
 * @author S.Danylenko
 *
 */
public class Sorter {

    // //////////////////////////////////////////////////////////
    // these are comparators
    // //////////////////////////////////////////////////////////

    /**
     * Sorts planes by model
     */
    public static final Comparator<ParentPlane> SORT_PLANES_BY_MODEL = new Comparator<ParentPlane>() {
        @Override
        public int compare(ParentPlane o1, ParentPlane o2) {
            return o1.getModel().compareTo(o2.getModel());
        }
    };

    /**
     * Sorts planes by length.
     */
    public static final Comparator<ParentPlane> SORT_PLANES_BY_lENGTH = new Comparator<ParentPlane>() {
        @Override
        public int compare(ParentPlane o1, ParentPlane o2) {
            Parameters p1 = o1.getParameters();
            Parameters p2 = o2.getParameters();
            UnitAttribute a1 = p1.getLength();
            UnitAttribute a2 = p2.getLength();
            return (int)(a1.getValue() - a2.getValue());
        }
    };

    /**
     * Sorts planes by price.
     */
    public static final Comparator<ParentPlane> SORT_PLANES_BY_PRICE = new Comparator<ParentPlane>() {
        @Override
        public int compare(ParentPlane o1, ParentPlane o2) {
            Price a1 = o1.getPrice();
            Price a2 = o1.getPrice();
            return (int)(a1.getContent() - a2.getContent());
        }
    };

    // //////////////////////////////////////////////////////////
    // these methods take Test object and sort it
    // with according comparator
    // //////////////////////////////////////////////////////////

    public static final void sortPlanesByModel(Planes test) {
        Collections.sort(test.getPlane(), SORT_PLANES_BY_MODEL);
    }

    public static final void sortPlanesByLength(Planes test) {
        Collections.sort(test.getPlane(), SORT_PLANES_BY_lENGTH);
    }

    public static final void sortPlanesByPrice(Planes test) {
        Collections.sort(test.getPlane(), SORT_PLANES_BY_PRICE);
    }

    public static void main(String[] args) throws Exception {
        // Test.xml --> Test object

        if (Validator.isValid(INVALID_XML_FILE)){
            System.out.println("XML is Valid");
        } else {
            System.out.println("XML is not Valid");
        }

        DOMController domController = new DOMController(
                VALID_XML_FILE);
        domController.parse(false);
        Planes test = domController.getTest();

        System.out.println("====================================");
        System.out.println(test);
        System.out.println("====================================");

        System.out.println("==========SortByModel==========================");
        Sorter.sortPlanesByModel(test);
        System.out.println(test);
        System.out.println("====================================");

        System.out.println("============SortByLength========================");
        Sorter.sortPlanesByLength(test);
        System.out.println(test);

        System.out.println("=============SortByPrice=======================");
        Sorter.sortPlanesByPrice(test);
        System.out.println(test);
    }
}
