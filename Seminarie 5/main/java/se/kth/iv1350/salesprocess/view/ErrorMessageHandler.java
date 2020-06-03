package se.kth.iv1350.salesprocess.view;

/**
 *
 * @author fredrikpettersson
 */
public class ErrorMessageHandler {
        
    public void displayErrorMessage (String msg){
        StringBuilder eMB = new StringBuilder();
        eMB.append("ERROR\n");
        eMB.append(msg);
        System.out.println(eMB);
    }
}
